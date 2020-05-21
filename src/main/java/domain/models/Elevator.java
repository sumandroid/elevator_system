package domain.models;

import constants.Constants;
import constants.Direction;

import java.util.*;

public class Elevator {
    volatile private Integer currentFloor;
    volatile private Direction direction = Direction.NONE;
    boolean[] floors;
    volatile PriorityQueue<Request> upQueue = new PriorityQueue<>(new RequestComparator());
    volatile PriorityQueue<Request> downQeue = new PriorityQueue<>(new RequestComparator().reversed());
    volatile PriorityQueue<Request> currentQueue = upQueue;
    volatile private Integer goalFloor;


    public Elevator(){
        floors = new boolean[Constants.maxFloors];
        this.currentFloor = 0;
    }

    public Integer getCurrentFloor(){
        return this.currentFloor;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public PriorityQueue<Request> getCurrentQueue() {
        return currentQueue;
    }

    public void setCurrentQueue(PriorityQueue<Request> currentQueue) {
        this.currentQueue = currentQueue;
    }

    public PriorityQueue<Request> getUpQueue() {
        return upQueue;
    }

    public void resetUpQueue(){
        this.upQueue = new PriorityQueue<>(new RequestComparator());
    }

    public void resetDownQueue(){
        this.downQeue = new PriorityQueue<>(new RequestComparator().reversed());
    }

    public PriorityQueue<Request> getDownQeue() {
        return downQeue;
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getGoalFloor() {
        return goalFloor;
    }

    public void setGoalFloor(Integer goalFloor) {
        this.goalFloor = goalFloor;
    }

    private class RequestComparator implements Comparator<Request>{

        @Override
        public int compare(Request o1, Request o2) {
            return o1.getFloor().compareTo(o2.getFloor());
        }
    }

}
