package ru.game.kiker.model.entity;

import java.util.ArrayList;

public class OnlineGame {
    private Integer firstScore;
    private Integer secondScore;
    private Boolean status;
    private Long idTable;
    private ArrayList<TimeLine> timeLine;
    private ArrayList<String> firstTeam;
    private ArrayList<String> secondTeam;

    public OnlineGame() {

    }

    public OnlineGame(Object firstScore, Object secondScore, Boolean status, Long idTable, Object timeLine, Object firstTeam, Object secondTeam) {
        this.firstScore = ((Long) firstScore).intValue();
        this.secondScore = ((Long) secondScore).intValue();
        this.status = status;
        this.idTable = idTable;
        this.timeLine = (ArrayList<TimeLine>) timeLine;
        this.firstTeam = (ArrayList<String>) firstTeam;
        this.secondTeam = (ArrayList<String>) secondTeam;
    }

    public OnlineGame createEmptyGame(Long idTable) {
        OnlineGame og = new OnlineGame();
        og.setFirstScore(0);
        og.setSecondScore(0);
        og.setStatus(false);
        og.setIdTable(idTable);
        og.setTimeline(null);
        og.setFirstTeam(null);
        og.setSecondTeam(null);
        return og;
    }

    public ArrayList<String> getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(ArrayList<String> firstTeam) {
        this.firstTeam = firstTeam;
    }

    public ArrayList<String> getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(ArrayList<String> secondTeam) {
        this.secondTeam = secondTeam;
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
