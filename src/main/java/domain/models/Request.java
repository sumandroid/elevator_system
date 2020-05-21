package domain.models;

import constants.Direction;

public class Request {
    private Integer floor;
    private long timeStamp = System.currentTimeMillis();

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

}
