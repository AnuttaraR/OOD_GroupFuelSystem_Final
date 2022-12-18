package GasStationSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        //Creating a scanner to get user inputs
        Scanner sc = new Scanner(System.in);

        //Creating dispenser objects
        PetrolDispenseManager petrolDispenser1 = new PetrolDispenseManager();
        PetrolDispenseManager petrolDispenser2 = new PetrolDispenseManager();
        PetrolDispenseManager petrolDispenser3 = new PetrolDispenseManager();
        PetrolDispenseManager petrolDispenser4 = new PetrolDispenseManager();

        DieselDispenseManager dieselDispenser1 = new DieselDispenseManager();
        DieselDispenseManager dieselDispenser2 = new DieselDispenseManager();
        DieselDispenseManager dieselDispenser3 = new DieselDispenseManager();

        //Creating queue objects
        NormalQueue petrolDispenserQueue1 = new NormalQueue();
        NormalQueue petrolDispenserQueue2 = new NormalQueue();
        NormalQueue petrolDispenserQueue3 = new NormalQueue();
        NormalQueue petrolDispenserQueue4 = new NormalQueue();

        NormalQueue dieselDispenserQueue1 = new NormalQueue();
        NormalQueue dieselDispenserQueue2 = new NormalQueue();
        NormalQueue dieselDispenserQueue3 = new NormalQueue();

        CommonQueue commonQueue = new CommonQueue();

        //Assuming there are already customers in the few queues we are creating existing customers and assigning them to queues.
        List<Customer> petrolDispenserQueue1CustomersList = Arrays.asList(new Customer("0001", "Car", 1, petrolDispenserQueue1),new Customer("0002", "Van", 2, petrolDispenserQueue1),new Customer("0003", "Car", 3, petrolDispenserQueue1)
        ,new Customer("0004", "Car", 4, petrolDispenserQueue1),new Customer("0005", "Car", 5, petrolDispenserQueue1), new Customer("0006", "Car", 6, petrolDispenserQueue1),
                new Customer("0007", "Van", 7, petrolDispenserQueue1),new Customer("0008", "Car", 8, petrolDispenserQueue1),new Customer("0009", "Car", 9, petrolDispenserQueue1),new Customer("0010", "Car", 10, petrolDispenserQueue1));

        petrolDispenserQueue1.setListOfCustomers(new ArrayList<>(petrolDispenserQueue1CustomersList));

        //Getting customer details and creating a customer object
        System.out.println("Welcome to Gas and Service Station Colombo");
        System.out.println("Please enter your vehicle number: ");
        String vehicleNumber = sc.nextLine();

        //Displaying all the queue positions
        System.out.println("Petrol Queue 1 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue1));
        System.out.println("Petrol Queue 2 available number of position: "+Queue.displayAvailablePositions(petrolDispenserQueue2));
        System.out.println("Petrol Queue 3 available number of position: "+Queue.displayAvailablePositions(petrolDispenserQueue3));
        System.out.println("Petrol Queue 4 available number of position: "+Queue.displayAvailablePositions(petrolDispenserQueue4));
        System.out.println("Diesel Queue 1 available number of position: "+Queue.displayAvailablePositions(dieselDispenserQueue1));
        System.out.println("Diesel Queue 2 available number of position: "+Queue.displayAvailablePositions(dieselDispenserQueue2));
        System.out.println("Diesel Queue 3 available number of position: "+Queue.displayAvailablePositions(dieselDispenserQueue3));

        //Getting customer vehicle type to decide the queue
        System.out.println("Please input your vehicle type \n 1.Car  2.Van  3.Motor Bike  4.Three wheel  5.Public transport  6.Other");
        int vehicleType = sc.nextInt();

        System.out.println("You can join these queues");
        if (vehicleType==1 || vehicleType==2){
            System.out.println("Petrol Queue 1 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue1));
            System.out.println("Petrol Queue 2 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue2));
            if (petrolDispenserQueue1.getListOfCustomers().size()==10){
                if (petrolDispenserQueue2.getListOfCustomers().size()==10){
                    System.out.println("Oops! There are no available positions in respective queues. Please join the common queue");
                    //Completed upto this part
                }else{

                }
            }
        } else if(vehicleType==3){
            System.out.println("Petrol Queue 4 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue4));
        }else if(vehicleType==4){
            System.out.println("Petrol Queue 3 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue3));
        } else if (vehicleType==5) {
            System.out.println("Diesel Queue 1 available number of position: "+Queue.displayAvailablePositions(dieselDispenserQueue1));
        }else {
            System.out.println("Please input fuel type \n1.Petrol  2.Diesel");
            int fuelType = sc.nextInt();
            if (fuelType==1){
                System.out.println("Petrol Queue 2 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue2));
            }else {
                System.out.println("Diesel Queue 2 available number of position: " + Queue.displayAvailablePositions(dieselDispenserQueue2));
                System.out.println("Diesel Queue 3 available number of position: " + Queue.displayAvailablePositions(dieselDispenserQueue3));
            }
        }
    }
}