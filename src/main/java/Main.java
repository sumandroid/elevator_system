import controllers.ElevatorControllerImpl;
import controllers.IElevatorController;
import domain.models.Elevator;
import domain.models.Request;
import parsers.StringToRequestParser;
import services.ElevatorService;
import services.ElevatorServiceImpl;
import workers.ElevatorWorker;

import java.util.Scanner;

public class Main {

    public static void main(String []args){
        Elevator elevator = new Elevator();
        ElevatorService elevatorService = new ElevatorServiceImpl(elevator);
        IElevatorController elevatorController = new ElevatorControllerImpl(elevatorService);

        Scanner scanner = new Scanner(System.in);

        Thread thread = new Thread(new ElevatorWorker(elevator));
        thread.start();
        while(true){
            String command = scanner.nextLine();
            if(command.equalsIgnoreCase("exit")){
                thread.interrupt();
                break;
            }
            try{
                Request request = StringToRequestParser.parseString(command);
                elevatorController.process(request);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("Exiting now");
    }
}
