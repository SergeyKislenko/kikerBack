package ru.game.kiker.utils;

import ru.game.kiker.model.entity.OnlineGame;
import ru.game.kiker.model.entity.TimeLine;

import java.util.ArrayList;

public class PrepareTimeLine {
    public static ArrayList<TimeLine> prepareTimeLine(OnlineGame game, Integer scoreFirst, Integer scoreSecond, String type) {
        ArrayList<TimeLine> timeLine = game.getTimeline() == null ? new ArrayList<TimeLine>() : game.getTimeline();
        Integer status = -1;
        Integer team = -1;

        if (type.equals("plus")) {
            status = 1;
        } else if (type.equals("minus")) {
            status = 0;
        }

        if (game.getFirstScore() != scoreFirst) {
            team = 0;
        } else if (game.getSecondScore() != scoreSecond) {
            team = 1;
        }

        TimeLine tm = new TimeLine(team, status);
        timeLine.add(tm);
        return timeLine;
    }
}
