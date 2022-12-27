package GasStationSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Driver {
    public static void main(String[] args) {

        //Creating a scanner to get user inputs
        Scanner sc = new Scanner(System.in);

        ArrayList<Customer> PetrolDisp1Customers = new ArrayList<>();
        ArrayList<Customer> PetrolDisp2Customers = new ArrayList<>();
        ArrayList<Customer> PetrolDisp3Customers = new ArrayList<>();
        ArrayList<Customer> PetrolDisp4Customers = new ArrayList<>();

        ArrayList<Customer> DieselDisp1Customers = new ArrayList<>();
        ArrayList<Customer> DieselDisp2Customers = new ArrayList<>();
        ArrayList<Customer> DieselDisp3Customers = new ArrayList<>();

        ArrayList<String> listOfVehicleTypes = new ArrayList<>();

        //Creating repositories
        Repository petrolRepository = new Repository(25000, "Petrol");
        Repository dieselRepository = new Repository(25000, "Diesel");

        //Creating dispenser objects
        PetrolDispenseManager petrolDispenser1 = new PetrolDispenseManager(PetrolDisp1Customers, petrolRepository, true, 1, 0.0, 0.0);
        PetrolDispenseManager petrolDispenser2 = new PetrolDispenseManager(PetrolDisp2Customers, petrolRepository, true, 2, 0.0, 0.0);
        PetrolDispenseManager petrolDispenser3 = new PetrolDispenseManager(PetrolDisp3Customers, petrolRepository, true, 3, 0.0, 0.0);
        PetrolDispenseManager petrolDispenser4 = new PetrolDispenseManager(PetrolDisp4Customers, petrolRepository, true, 4, 0.0, 0.0);

        DieselDispenseManager dieselDispenser1 = new DieselDispenseManager(DieselDisp1Customers, dieselRepository, true, 1, 0.0, 0.0);
        DieselDispenseManager dieselDispenser2 = new DieselDispenseManager(DieselDisp2Customers, dieselRepository, true, 2, 0.0, 0.0);
        DieselDispenseManager dieselDispenser3 = new DieselDispenseManager(DieselDisp3Customers, dieselRepository, true, 3, 0.0, 0.0);

        //Creating queue objects
        NormalQueue petrolDispenserQueue1 = new NormalQueue(PetrolDisp1Customers, 1, "Petrol");
        NormalQueue petrolDispenserQueue2 = new NormalQueue(PetrolDisp2Customers, 2, "Petrol");
        NormalQueue petrolDispenserQueue3 = new NormalQueue(PetrolDisp3Customers, 3, "Petrol");
        NormalQueue petrolDispenserQueue4 = new NormalQueue(PetrolDisp4Customers, 4, "Petrol");

        NormalQueue dieselDispenserQueue1 = new NormalQueue(DieselDisp1Customers, 1, "Diesel");
        NormalQueue dieselDispenserQueue2 = new NormalQueue(DieselDisp2Customers, 2, "Diesel");
        NormalQueue dieselDispenserQueue3 = new NormalQueue(DieselDisp3Customers, 3, "Diesel");

        CommonQueue commonQueue = new CommonQueue();
        int commonCustomers = 0;

        /*//Assuming there are already customers in the few queues we are creating existing customers and assigning them to queues.
        List<Customer> petrolDispenserQueue1CustomersList = Arrays.asList(new Customer("0001", "Car", 1, petrolDispenserQueue1),new Customer("0002", "Van", 2, petrolDispenserQueue1),new Customer("0003", "Car", 3, petrolDispenserQueue1)
        ,new Customer("0004", "Car", 4, petrolDispenserQueue1),new Customer("0005", "Car", 5, petrolDispenserQueue1), new Customer("0006", "Car", 6, petrolDispenserQueue1),
                new Customer("0007", "Van", 7, petrolDispenserQueue1),new Customer("0008", "Car", 8, petrolDispenserQueue1),new Customer("0009", "Car", 9, petrolDispenserQueue1),new Customer("0010", "Car", 10, petrolDispenserQueue1));

        petrolDispenserQueue1.setListOfCustomers(new ArrayList<>(petrolDispenserQueue1CustomersList));
*/
        //Getting customer details and creating a customer object
        System.out.println("Welcome to Gas and Service Station Colombo\n");


        //Getting user input to create a customer
        System.out.println("Please enter your vehicle number: ");
        String vehicleNumber = sc.nextLine();

        //Getting customer vehicle type to decide the queue
        System.out.println("Please input your vehicle type \n 1.Car  2.Van  3.Motor Bike  4.Three wheel  5.Public transport  6.Other");

        int vehicleType = sc.nextInt();
        String typeOfVehicle = "";
        if (vehicleType == 1){
            typeOfVehicle = "Car";
        } else if(vehicleType == 2){
            typeOfVehicle = "Van";
        } else if(vehicleType == 3){
            typeOfVehicle = "Motor Bike";
        } else if(vehicleType == 4){
            typeOfVehicle = "Three Wheel";
        } else if(vehicleType == 5){
            typeOfVehicle = "Public Transport";
        } else if(vehicleType == 6){
            typeOfVehicle = "Other";
        }

        Customer customer = new Customer(vehicleNumber, typeOfVehicle);

        //Displaying all the queue positions
        System.out.println("Petrol Queue 1 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue1));
        System.out.println("Petrol Queue 2 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue2));
        System.out.println("Petrol Queue 3 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue3));
        System.out.println("Petrol Queue 4 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue4));
        System.out.println("Diesel Queue 1 available number of positions: "+Queue.displayAvailablePositions(dieselDispenserQueue1));
        System.out.println("Diesel Queue 2 available number of positions: "+Queue.displayAvailablePositions(dieselDispenserQueue2));
        System.out.println("Diesel Queue 3 available number of positions: "+Queue.displayAvailablePositions(dieselDispenserQueue3));


        System.out.println("You can join these queues");
        if (vehicleType==1 || vehicleType==2){
            System.out.println("Petrol Queue 1 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue1));
            System.out.println("Petrol Queue 2 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue2));
            if (petrolDispenserQueue1.getListOfCustomers().size()==10){
                if (petrolDispenserQueue2.getListOfCustomers().size()==10){
                    System.out.println("Oops! There are no available positions in respective queues. Please join the common queue");
                    System.out.println("Would you like to join the common queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1){
                            commonQueue.addCustomer(customer);
                            customer.setQueue(commonQueue);
                            commonQueue.setNoOfCustomers(commonCustomers+1);
                            System.out.println("Successfully joined the common queue, thanks for your patience.");
                        } else if (ans == 2){
                            System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                            exit();
                        }
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
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

    private static void exit() {
    }
}