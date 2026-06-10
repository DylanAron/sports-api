#!/bin/bash
# sports-api 启动/停止脚本（Linux 环境）

APP_NAME=sports-api
JAR_FILE=${APP_NAME}.jar
LOG_DIR=logs
STDOUT_LOG=${LOG_DIR}/stdout.log
PID_FILE=${APP_NAME}.pid

GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

stop() {
    local pid
    if [[ -f "$PID_FILE" ]]; then
        pid=$(cat "$PID_FILE")
    else
        pid=$(pgrep -f "${JAR_FILE}" 2>/dev/null | head -1)
    fi

    if [[ -z "$pid" ]]; then
        echo -e "${YELLOW}[INFO] ${APP_NAME} 未运行${NC}"
        return
    fi

    echo -e "${YELLOW}[INFO] 正在停止 ${APP_NAME} (PID: $pid) ...${NC}"
    kill "$pid" 2>/dev/null

    for i in $(seq 1 10); do
        if ! kill -0 "$pid" 2>/dev/null; then
            break
        fi
        sleep 1
    done

    if kill -0 "$pid" 2>/dev/null; then
        echo -e "${YELLOW}[INFO] 强制停止 ${APP_NAME} ...${NC}"
        kill -9 "$pid" 2>/dev/null
    fi

    rm -f "$PID_FILE"
    echo -e "${GREEN}[INFO] ${APP_NAME} 已停止${NC}"
}

if [[ "$1" == "stop" ]]; then
    stop
    exit 0
fi

# === 启动 ===

if ! command -v java &>/dev/null; then
    echo -e "${RED}[ERROR] java 未找到，请安装 JDK 21+${NC}"
    exit 1
fi

mkdir -p "$LOG_DIR"

if [[ -f "$PID_FILE" ]]; then
    pid=$(cat "$PID_FILE")
    if kill -0 "$pid" 2>/dev/null; then
        echo -e "${YELLOW}[WARN] ${APP_NAME} 已在运行 (PID: $pid)${NC}"
        exit 1
    fi
    rm -f "$PID_FILE"
fi

JAVA_OPTS="-Xms512m -Xmx1024m"
JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError"
JAVA_OPTS="$JAVA_OPTS -XX:HeapDumpPath=${LOG_DIR}/heapdump.hprof"

echo -e "${GREEN}[INFO] 正在启动 ${APP_NAME} ...${NC}"
nohup java $JAVA_OPTS -jar "$JAR_FILE" --spring.profiles.active=prod \
    >> "$STDOUT_LOG" 2>&1 &

pid=$!
echo $pid > "$PID_FILE"
echo -e "${GREEN}[INFO] ${APP_NAME} 已启动 (PID: $pid)${NC}"
echo -e "${GREEN}[INFO] 日志目录: $(realpath "$LOG_DIR")${NC}"
echo -e "${GREEN}[INFO] Stdout: $(realpath "$STDOUT_LOG")${NC}"
