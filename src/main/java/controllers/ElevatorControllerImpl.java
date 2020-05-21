package controllers;

import domain.models.Request;
import services.ElevatorService;

public class ElevatorControllerImpl implements IElevatorController{

    private ElevatorService elevatorService;

    public ElevatorControllerImpl(ElevatorService elevatorService){
        this.elevatorService = elevatorService;
    }

    @Override
    public void process(Request request) {
        elevatorService.moveElevator(request);
    }
}
