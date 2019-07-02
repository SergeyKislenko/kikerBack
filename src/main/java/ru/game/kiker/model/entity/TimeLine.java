package ru.game.kiker.model.entity;

import org.joda.time.DateTime;


public class TimeLine {
    private Integer team;
    private Integer status;
    private DateTime date;

    public TimeLine(Integer team, Integer status) {
        this.team = team;
        this.status = status;
        this.date = new DateTime();
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

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
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
