package ru.game.kiker.model.entity;

import java.util.HashMap;

public class Team {
    private Object peoples;
    private Long score;

    public Team(HashMap map) {
        this.peoples = map.get("peoples");
        this.score = (Long) map.get("score");
    }

    public Object getPeoples() {
        return peoples;
    }

    public void setPeoples(Object peoples) {
        this.peoples = peoples;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Team{" +
                "peoples=" + peoples +
                ", score=" + score +
                '}';
    }
}
