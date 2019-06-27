package ru.game.kiker.model.entity;

import java.util.HashMap;

public class OnlineGame {
    private Team firstTeam;
    private Team secondTeam;
    private Boolean status;
    private Long idTable;

    public OnlineGame(Object firstTeam, Object secondTeam, Boolean status, Long idTable) {
        this.firstTeam = new Team((HashMap) firstTeam);
        this.secondTeam = new Team((HashMap) secondTeam);
        this.status = status;
        this.idTable = idTable;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
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
                "firstTeam=" + firstTeam +
                ", secondTeam=" + secondTeam +
                ", status=" + status +
                ", idTable='" + idTable + '\'' +
                '}';
    }
}