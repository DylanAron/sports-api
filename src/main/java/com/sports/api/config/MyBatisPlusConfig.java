package com.sports.api.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new SimplePaginationInterceptor());
        return interceptor;
    }

    /**
     * 简化分页拦截器，利用 MyBatis-Plus 的 Page 对象和 RowBounds 机制
     */
    static class SimplePaginationInterceptor implements InnerInterceptor {

        @Override
        public void beforeQuery(Executor executor, MappedStatement ms, Object parameter,
                                RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
            // MyBatis-Plus 的 Page 类通过 RowBounds 传递，自动处理分页
            // 这里留空，让 MP 的默认行为处理分页
        }

        @Override
        public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
            // nothing
        }

        @Override
        public void setProperties(Properties properties) {
            // nothing
        }
    }
}
