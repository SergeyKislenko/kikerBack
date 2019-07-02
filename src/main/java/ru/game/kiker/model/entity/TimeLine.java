package ru.game.kiker.model.entity;

import org.joda.time.DateTime;


public class TimeLine {
    private Integer team;
    private Integer status;
    private String date;

    public TimeLine(Integer team, Integer status) {
        this.team = team;
        this.status = status;
        this.date = new DateTime().toString();
    }

    public TimeLine prepareTimeLine(){
        return null;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "team=" + team +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
