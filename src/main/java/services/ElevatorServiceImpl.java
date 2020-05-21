package services;

import constants.Direction;
import domain.models.Elevator;
import domain.models.Request;

public class ElevatorServiceImpl implements ElevatorService {

    private Elevator elevator;

    public ElevatorServiceImpl(Elevator elevator){
        this.elevator = elevator;
    }

    @Override
    public void moveElevator(Request request) {
        if(elevator.getDirection() == Direction.UP){
            if(elevator.getCurrentFloor() <= request.getFloor()){
                elevator.getCurrentQueue().add(request);
            }else{
                elevator.getDownQeue().add(request);
            }
        }else if(elevator.getDirection() == Direction.DOWN){
            if(elevator.getCurrentFloor() <= request.getFloor()){
                elevator.getCurrentQueue().add(request);
            }else{
                elevator.getUpQueue().add(request);
            }
        }else{
            if(elevator.getCurrentFloor() <= request.getFloor()){
                elevator.setDirection(Direction.UP);
            }else{
                elevator.setDirection(Direction.DOWN);
            }
            elevator.getCurrentQueue().add(request);
        }
    }


}
