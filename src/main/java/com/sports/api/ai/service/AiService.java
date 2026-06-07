package com.sports.api.ai.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AiService {

    private static final List<String> INSPIRATIONAL_ESSAYS = List.of(
            "人生如赛场，每一次奔跑都是对极限的挑战。汗水洒落，浇灌的是心中不灭的梦想。在这条名为人生的跑道上，没有终点，只有不断超越自我的过程。当疲惫袭来，请记住：真正的胜利不在于击败对手，而在于战胜昨日的自己。",
            "体育精神告诉我们，跌倒不可怕，可怕的是失去再次站起的勇气。每一次训练、每一次比赛，都是对意志的锤炼。挥洒的每一滴汗水，都在书写着属于自己的传奇。坚持，不是因为看到希望才坚持，而是因为坚持才能看到希望。",
            "运动场上，没有一蹴而就的成功，只有日复一日的坚持。清晨的第一缕阳光，见证着你的付出；夜晚的最后一盏灯光，陪伴着你的努力。所有的惊艳，都源于无数个平凡日子里的默默积累。",
            "人生就像一场马拉松，重要的不是起点，而是坚持到终点的决心。在漫长的赛道上，你会遇到无数的挑战和困难，但正是这些磨砺，塑造了更强大的你。保持节奏，调整呼吸，相信自己，终点就在前方。",
            "团队合作是体育的魅力所在。一个人可以走得很快，但一群人可以走得很远。在球场上，每一次传球都是信任的传递；每一次配合都是默契的体现。生活中也是如此，学会协作，才能成就更大的梦想。"
    );

    public String generateInspirationalEssay() {
        Random random = new Random();
        return INSPIRATIONAL_ESSAYS.get(random.nextInt(INSPIRATIONAL_ESSAYS.size()));
    }
}
