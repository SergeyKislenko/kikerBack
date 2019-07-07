package ru.game.kiker.model.entity;

import java.util.ArrayList;

public class OnlineGame {
    private Integer firstScore;
    private Integer secondScore;
    private Boolean status;
    private Long idTable;
    private ArrayList<TimeLine> timeLine;

    public OnlineGame(Object firstScore, Object secondScore, Boolean status, Long idTable, Object timeLine) {
        this.firstScore = (Integer) firstScore;
        this.secondScore = (Integer) secondScore;
        this.status = status;
        this.idTable = idTable;
        this.timeLine = (ArrayList<TimeLine>) timeLine;
    }

    public ArrayList<TimeLine> getTimeline() {
        return timeLine;
    }

    public void setTimeline(ArrayList<TimeLine> timeLine) {
        this.timeLine = timeLine;
    }


    public Integer getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(Integer firstScore) {
        this.firstScore = firstScore;
    }

    public Integer getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(Integer secondScore) {
        this.secondScore = secondScore;
    }

    public ArrayList<TimeLine> getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(ArrayList<TimeLine> timeLine) {
        this.timeLine = timeLine;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    @Override
    public String toString() {
        return "OnlineGame{" +
                "firstScore=" + firstScore +
                ", secondScore=" + secondScore +
                ", status=" + status +
                ", idTable='" + idTable + '\'' +
                ", timeLine='" + timeLine + '\'' +
                '}';
    }
}
