package workers;

import constants.Direction;
import domain.models.Elevator;
import domain.models.Request;

public class ElevatorWorker implements Runnable {

    private Elevator elevator;

    public ElevatorWorker(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        while (true) {
            if (!elevator.getCurrentQueue().isEmpty()) {
                Request request = elevator.getCurrentQueue().poll();
                if (request != null) {
                    processRequest(request);
                }
            } else {
                processNextQueue();
            }
        }
    }

    synchronized private void processRequest(Request request) {
        elevator.setGoalFloor(request.getFloor());
        if(elevator.getCurrentFloor() <= request.getFloor()){
            elevator.setDirection(Direction.UP);
        }else{
            elevator.setDirection(Direction.DOWN);
        }
        System.out.println("Moving " + elevator.getDirection() + " floor " + elevator.getGoalFloor());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        elevator.setCurrentFloor(elevator.getGoalFloor());
        if(elevator.getCurrentQueue().isEmpty() && elevator.getUpQueue().isEmpty() && elevator.getDownQeue().isEmpty()){
            elevator.setDirection(Direction.NONE);
        }
    }

    synchronized private void processNextQueue() {
        if (getUpQueueMin() < getDownQueueMin()) {
            elevator.setCurrentQueue(elevator.getUpQueue());
            elevator.resetUpQueue();
            elevator.setDirection(Direction.UP);
        } else if (getDownQueueMin() < getUpQueueMin()) {
            elevator.setCurrentQueue(elevator.getDownQeue());
            elevator.resetDownQueue();
            elevator.setDirection(Direction.DOWN);
        }
    }

    synchronized private long getUpQueueMin() {
        long min = Long.MAX_VALUE;
        for (Request r : elevator.getUpQueue()) {
            if (r.getTimeStamp() < min) {
                min = r.getTimeStamp();
            }
        }
        return min;
    }

    synchronized private long getDownQueueMin() {
        long min = Long.MAX_VALUE;
        for (Request r : elevator.getDownQeue()) {
            if (r.getTimeStamp() < min) {
                min = r.getTimeStamp();
            }
        }
        return min;
    }
}
