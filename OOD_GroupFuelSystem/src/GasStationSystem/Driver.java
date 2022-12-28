package GasStationSystem;

import java.util.*;

import static java.lang.System.exit;

public class Driver {
    public static void main(String[] args) {

        Random random = new Random();
        //Creating a scanner to get user inputs
        Scanner sc = new Scanner(System.in);

        //Creating a Date
        DateTime today = new DateTime(12,2,2022);

        //Creating List of Customers of Respective Queues
        ArrayList<Customer> PetrolDisp1Customers = new ArrayList<>();
        ArrayList<Customer> PetrolDisp2Customers = new ArrayList<>();
        ArrayList<Customer> PetrolDisp3Customers = new ArrayList<>();
        ArrayList<Customer> PetrolDisp4Customers = new ArrayList<>();

        ArrayList<Customer> DieselDisp1Customers = new ArrayList<>();
        ArrayList<Customer> DieselDisp2Customers = new ArrayList<>();
        ArrayList<Customer> DieselDisp3Customers = new ArrayList<>();

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

        //Creating the Common Queue
        CommonQueue commonQueue = new CommonQueue();
        int commonCustomers = 0;

        //list of diesel dispensers and petrol dispensers
        ArrayList<DieselDispenseManager> dieselDispenseManagerArrayList = new ArrayList<>();
        dieselDispenseManagerArrayList.add(dieselDispenser1);
        dieselDispenseManagerArrayList.add(dieselDispenser2);
        dieselDispenseManagerArrayList.add(dieselDispenser3);

        ArrayList<PetrolDispenseManager> petrolDispenseManagerArrayList = new ArrayList<>();
        petrolDispenseManagerArrayList.add(petrolDispenser1);
        petrolDispenseManagerArrayList.add(petrolDispenser2);
        petrolDispenseManagerArrayList.add(petrolDispenser3);
        petrolDispenseManagerArrayList.add(petrolDispenser4);

        //Creating the Gas Station
        GasStationOwner gasStationOwner = new GasStationOwner(dieselDispenseManagerArrayList, petrolDispenseManagerArrayList);

        //Assuming there are already customers in the few queues we are creating existing customers and assigning them to queues.
        List<Customer> petrolDispenserQueue1CustomersList = Arrays.asList(new Customer("0001", "Car", 11, petrolDispenserQueue1), new Customer("0002", "Van", 22, petrolDispenserQueue1), new Customer("0003", "Car", 53, petrolDispenserQueue1)
                , new Customer("0004", "Car", 44, petrolDispenserQueue1), new Customer("0005", "Car", 15, petrolDispenserQueue1), new Customer("0006", "Car", 86, petrolDispenserQueue1),
                new Customer("0007", "Van", 7, petrolDispenserQueue1), new Customer("0008", "Car", 48, petrolDispenserQueue1), new Customer("0009", "Car", 9, petrolDispenserQueue1), new Customer("0010", "Car", 70, petrolDispenserQueue1));

        List<Customer> petrolDispenserQueue2CustomersList = Arrays.asList(new Customer("0021", "Car", 56, petrolDispenserQueue2), new Customer("0022", "Other", 45, petrolDispenserQueue2), new Customer("0023", "Car", 35, petrolDispenserQueue2)
                , new Customer("0024", "Other", 78, petrolDispenserQueue2), new Customer("0025", "Car", 69, petrolDispenserQueue2), new Customer("0026", "Van", 60, petrolDispenserQueue2),
                new Customer("0026", "Van", 17, petrolDispenserQueue2), new Customer("0028", "Car", 68, petrolDispenserQueue2), new Customer("0029", "Car", 89, petrolDispenserQueue2), new Customer("0030", "Car", 27, petrolDispenserQueue2));


        petrolDispenserQueue1.setListOfCustomers(new ArrayList<>(petrolDispenserQueue1CustomersList));
        petrolDispenserQueue2.setListOfCustomers(new ArrayList<>(petrolDispenserQueue2CustomersList));

        //Getting customer details and creating a customer object
        System.out.println("\n Welcome to Gas and Service Station Colombo\n");

        while (true) {

            //Getting user input to create a customer
            System.out.println("\n Please enter your vehicle number: ");
            String vehicleNumber = sc.next();

            //Getting customer vehicle type to decide the queue
            System.out.println("\n Please input your vehicle type \n 1.Car  2.Van  3.Motor Bike  4.Three wheel  5.Public transport  6.Other");

            int vehicleType = 0;
            while (true) {
                String vehicleTyp = sc.next();
                try {
                    vehicleType = Integer.parseInt(vehicleTyp);
                    if (vehicleType <= 6 && vehicleType >= 1) {
                        break;
                    } else {
                        System.out.println("\n Please enter a correct vehicle type number");
                    }
                } catch (Exception e) {
                    System.out.println("\n Error! Please enter a correct vehicle type number");
                }
            }

            String typeOfVehicle = "";
            if (vehicleType == 1) {
                typeOfVehicle = "Car";
            } else if (vehicleType == 2) {
                typeOfVehicle = "Van";
            } else if (vehicleType == 3) {
                typeOfVehicle = "Motor Bike";
            } else if (vehicleType == 4) {
                typeOfVehicle = "Three Wheel";
            } else if (vehicleType == 5) {
                typeOfVehicle = "Public Transport";
            } else if (vehicleType == 6) {
                typeOfVehicle = "Other";
            }

            Customer customer = new Customer(vehicleNumber, typeOfVehicle);

            //Displaying all the queue positions
            System.out.println("\nPetrol Queue 1 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue1));
            System.out.println("Petrol Queue 2 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue2));
            System.out.println("Petrol Queue 3 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue3));
            System.out.println("Petrol Queue 4 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue4));
            System.out.println("Diesel Queue 1 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue1));
            System.out.println("Diesel Queue 2 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue2));
            System.out.println("Diesel Queue 3 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue3));


            System.out.println("\n You can join these queues");
//      if the 1st queue, customer can join has available positions then he must join that queue
            if (vehicleType == 1 || vehicleType == 2) {

                if (petrolDispenserQueue1.getListOfCustomers().size() == 10) {
                    if (petrolDispenserQueue2.getListOfCustomers().size() == 10) {
                        System.out.println("\n Oops! There are no available positions in respective queues. Please join the common queue");
                        System.out.println("\n Would you like to join the common queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                commonQueue.addCustomer(customer);
                                customer.setQueue(commonQueue);
                                commonQueue.setNoOfCustomers(commonCustomers + 1);
                                System.out.println("Successfully joined the common queue, thanks for your patience.");

//                            Assigning the common queue customer to a normal queue
                                Queue.moveUpQueue(petrolDispenserQueue2.getListOfCustomers());
                                petrolDispenserQueue2.getListOfCustomers().add(customer);
                                petrolDispenser2.addCustomer(customer);
                                customer.setQueue(petrolDispenserQueue2);
                                System.out.println("Successfully joined the queue.");
                                int ticketNo = random.nextInt(1, 100);
                                customer.setTicketId(ticketNo);
                                System.out.println("Your ticket Id is " + ticketNo);

                            } else if (ans == 2) {
                                System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                                System.out.println("Next customer please");
                                continue;
//                                System.exit(0);
                            }
                        } catch (Exception e) {
                            System.out.println("Error!");
                        }
                    } else {
                        System.out.println("Petrol Queue 2 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue2));
                        System.out.println("Would you like to join this queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                petrolDispenser2.addCustomer(customer);
                                petrolDispenserQueue2.addCustomer(customer);
                                customer.setQueue(petrolDispenserQueue2);
                                System.out.println("Successfully joined the queue.");
                                int ticketNo = random.nextInt(1, 10000);
                                customer.setTicketId(ticketNo);
                                System.out.println("Your ticket Id is " + ticketNo); //pass the ticket id
                            } else if (ans == 2) {
                                System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                                System.out.println("Next customer please");
                                continue;
//                                System.exit(0);
                            }
                        } catch (Exception e) {
                            System.out.println("Error!");
                        }
                    }
                } else {
                    System.out.println("Petrol Queue 1 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue1));
                    System.out.println("Would you like to join this queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            petrolDispenser1.addCustomer(customer);
                            petrolDispenserQueue1.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue1);
                            System.out.println("Successfully joined the queue.");
                            int ticketNo = random.nextInt(1, 10000);
                            customer.setTicketId(ticketNo);
                            System.out.println("Your ticket Id is " + ticketNo); //pass the ticket id
                        } else if (ans == 2) {
                            System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                            System.out.println("Next customer please");
                            continue;
//                            System.exit(0);
                        }
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                }
            } else if (vehicleType == 3) {
//            System.out.println("Petrol Queue 4 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue4));
                if (petrolDispenserQueue4.getListOfCustomers().size() == 10) {
                    System.out.println("Oops! There are no available positions in respective queues. Please join the common queue");
                    System.out.println("Would you like to join the common queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            commonQueue.addCustomer(customer);
                            customer.setQueue(commonQueue);
                            commonQueue.setNoOfCustomers(commonCustomers + 1);
                            System.out.println("Successfully joined the common queue, thanks for your patience.");
                            System.out.println("Next customer please");

                            //Assigning the common queue customer to a normal queue
                            Queue.moveUpQueue(petrolDispenserQueue4.getListOfCustomers());
                            petrolDispenserQueue4.getListOfCustomers().add(customer);
                            petrolDispenser4.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue4);
                            System.out.println("Successfully joined the queue.");
                            int ticketNo = random.nextInt(1, 10090);
                            customer.setTicketId(ticketNo);
                            System.out.println("Your ticket Id is " + ticketNo);

                        } else if (ans == 2) {
                            System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                            System.out.println("Next customer please");
//                            System.exit(0);
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                } else {
                    System.out.println("Petrol Queue 4 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue4));
                    System.out.println("Would you like to join this queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            petrolDispenser4.addCustomer(customer);
                            petrolDispenserQueue4.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue4);
                            System.out.println("Successfully joined the queue.");
                            int ticketNo = random.nextInt(1, 10000);
                            customer.setTicketId(ticketNo);
                            System.out.println("Your ticket Id is " + ticketNo); //pass the ticket id
                        } else if (ans == 2) {
                            System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                            System.out.println("Next customer please");
//                            System.exit(0);
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                }
            } else if (vehicleType == 4) {
//            System.out.println("Petrol Queue 3 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue3));
                if (petrolDispenserQueue3.getListOfCustomers().size() == 10) {
                    System.out.println("Oops! There are no available positions in respective queues. Please join the common queue");
                    System.out.println("Would you like to join the common queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            commonQueue.addCustomer(customer);
                            customer.setQueue(commonQueue);
                            commonQueue.setNoOfCustomers(commonCustomers + 1);
                            System.out.println("Successfully joined the common queue, thanks for your patience.");

                            //Assigning the common queue customer to a normal queue
                            Queue.moveUpQueue(petrolDispenserQueue3.getListOfCustomers());
                            petrolDispenserQueue3.getListOfCustomers().add(customer);
                            petrolDispenser3.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue3);
                            System.out.println("Successfully joined the queue.");
                            int ticketNo = random.nextInt(1, 34783);
                            customer.setTicketId(ticketNo);
                            System.out.println("Your ticket Id is " + ticketNo);

                        } else if (ans == 2) {
                            System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                            System.out.println("Next customer please");
//                            System.exit(0);
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                } else {
                    System.out.println("Petrol Queue 3 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue3));
                    System.out.println("Would you like to join this queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            petrolDispenser3.addCustomer(customer);
                            petrolDispenserQueue3.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue3);
                            System.out.println("Successfully joined the queue.");
                            int ticketNo = random.nextInt(1, 10000);
                            customer.setTicketId(ticketNo);
                            System.out.println("Your ticket Id is " + ticketNo); //pass the ticket id
                        } else if (ans == 2) {
                            System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                            System.out.println("Next customer please");
//                            System.exit(0);
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                }
            } else if (vehicleType == 5) {
//            System.out.println("Diesel Queue 1 available number of position: "+Queue.displayAvailablePositions(dieselDispenserQueue1));
                if (dieselDispenserQueue1.getListOfCustomers().size() == 10) {
                    System.out.println("Oops! There are no available positions in respective queues. Please join the common queue");
                    System.out.println("Would you like to join the common queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            commonQueue.addCustomer(customer);
                            customer.setQueue(commonQueue);
                            commonQueue.setNoOfCustomers(commonCustomers + 1);
                            System.out.println("Successfully joined the common queue, thanks for your patience.");

                            //Assigning the common queue customer to a normal queue
                            Queue.moveUpQueue(dieselDispenserQueue1.getListOfCustomers());
                            dieselDispenserQueue1.getListOfCustomers().add(customer);
                            dieselDispenser1.addCustomer(customer);
                            customer.setQueue(dieselDispenserQueue1);
                            System.out.println("Successfully joined the queue.");
                            int ticketNo = random.nextInt(1, 6000);
                            customer.setTicketId(ticketNo);
                            System.out.println("Your ticket Id is " + ticketNo);

                        } else if (ans == 2) {
                            System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                            System.out.println("Next customer please");
//                            System.exit(0);
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                } else {
                    System.out.println("Diesel Queue 1 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue1));
                    System.out.println("Would you like to join this queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            dieselDispenser1.addCustomer(customer);
                            dieselDispenserQueue1.addCustomer(customer);
                            customer.setQueue(dieselDispenserQueue1);
                            System.out.println("Successfully joined the queue.");
                            int ticketNo = random.nextInt(1, 2000);
                            customer.setTicketId(ticketNo);
                            System.out.println("Your ticket Id is " + ticketNo); //pass the ticket id
                        } else if (ans == 2) {
                            System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                            System.out.println("Next customer please");
                            continue;
//                            System.exit(0);
                        }
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                }
            } else {
                System.out.println("Please input fuel type \n1.Petrol  2.Diesel");
                int fuelType = sc.nextInt();
                if (fuelType == 1) {
//                System.out.println("Petrol Queue 2 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue2));
                    if (petrolDispenserQueue2.getListOfCustomers().size() == 10) {
                        System.out.println("Oops! There are no available positions in respective queues. Please join the common queue");
                        System.out.println("Would you like to join the common queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                commonQueue.addCustomer(customer);
                                customer.setQueue(commonQueue);
                                commonQueue.setNoOfCustomers(commonCustomers + 1);
                                System.out.println("Successfully joined the common queue, thanks for your patience.");

                                //Assigning the common queue customer to a normal queue
                                Queue.moveUpQueue(petrolDispenserQueue2.getListOfCustomers());
                                petrolDispenserQueue2.getListOfCustomers().add(customer);
                                petrolDispenserQueue2.addCustomer(customer);
                                customer.setQueue(petrolDispenserQueue2);
                                System.out.println("Successfully joined the queue.");
                                int ticketNo = random.nextInt(1, 6000);
                                customer.setTicketId(ticketNo);
                                System.out.println("Your ticket Id is " + ticketNo);

                            } else if (ans == 2) {
                                System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                                System.out.println("Next customer please");
                                continue;
//                                System.exit(0);
                            }
                        } catch (Exception e) {
                            System.out.println("Error!");
                        }
                    } else {
                        System.out.println("Petrol Queue 2 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue2));
                        System.out.println("Would you like to join this queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                petrolDispenser2.addCustomer(customer);
                                petrolDispenserQueue2.addCustomer(customer);
                                customer.setQueue(petrolDispenserQueue2);
                                System.out.println("Successfully joined the queue.");
                                int ticketNo = random.nextInt(1, 50000);
                                customer.setTicketId(ticketNo);
                                System.out.println("Your ticket Id is " + ticketNo); //pass the ticket id
                            } else if (ans == 2) {
                                System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                                System.out.println("Next customer please");
                                continue;
//                                System.exit(0);
                            }
                        } catch (Exception e) {
                            System.out.println("Error!");
                        }
                    }
                } else if (vehicleType == 6) {
//                System.out.println("Diesel Queue 2 available number of position: " + Queue.displayAvailablePositions(dieselDispenserQueue2));
//                System.out.println("Diesel Queue 3 available number of position: " + Queue.displayAvailablePositions(dieselDispenserQueue3));
                    if (dieselDispenserQueue2.getListOfCustomers().size() == 10) {
                        if (dieselDispenserQueue3.getListOfCustomers().size() == 10) {
                            System.out.println("Oops! There are no available positions in respective queues. Please join the common queue");
                            System.out.println("Would you like to join the common queue? 1:Yes, 2:No\n");
                            try {
                                int ans = sc.nextInt();
                                if (ans == 1) {
                                    commonQueue.addCustomer(customer);
                                    customer.setQueue(commonQueue);
                                    commonQueue.setNoOfCustomers(commonCustomers + 1);
                                    System.out.println("Successfully joined the common queue, thanks for your patience.");

                                    //Assigning the common queue customer to a normal queue
                                    dieselDispenserQueue3.getListOfCustomers().remove(0);
                                    dieselDispenserQueue3.getListOfCustomers().add(customer);
                                    dieselDispenser3.addCustomer(customer);
                                    customer.setQueue(dieselDispenserQueue3);
                                    System.out.println("Successfully joined the queue.");
                                    int ticketNo = random.nextInt(154, 4500);
                                    customer.setTicketId(ticketNo);
                                    System.out.println("Your ticket Id is " + ticketNo);

                                } else if (ans == 2) {
                                    System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                                    System.out.println("Next customer please");
                                    continue;
                                    //System.exit(0);
                                }
                            } catch (Exception e) {
                                System.out.println("Error!");
                            }
                        } else {
                            System.out.println("Diesel Queue 3 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue3));
                            System.out.println("Would you like to join this queue? 1:Yes, 2:No\n");
                            try {
                                int ans = sc.nextInt();
                                if (ans == 1) {
                                    dieselDispenser3.addCustomer(customer);
                                    dieselDispenserQueue3.addCustomer(customer);
                                    customer.setQueue(dieselDispenserQueue3);
                                    System.out.println("Successfully joined the queue.");
                                    int ticketNo = random.nextInt(1, 10000);
                                    customer.setTicketId(ticketNo);
                                    System.out.println("Your ticket Id is " + ticketNo); //pass the ticket id
                                } else if (ans == 2) {
                                    System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                                    System.out.println("Next customer please");
                                    continue;
//                                    System.exit(0);
                                }
                            } catch (Exception e) {
                                System.out.println("Error!");
                            }
                        }
                    } else {
                        System.out.println("Diesel Queue 2 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue2));
                        System.out.println("Would you like to join this queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                dieselDispenser2.addCustomer(customer);
                                dieselDispenserQueue2.addCustomer(customer);
                                customer.setQueue(dieselDispenserQueue2);
                                System.out.println("Successfully joined the queue.");
                                int ticketNo = random.nextInt(1, 100);
                                customer.setTicketId(ticketNo);
                                System.out.println("Your ticket Id is " + ticketNo); //pass the ticket id
                            } else if (ans == 2) {
                                System.out.println("Thank you for using the fuel system, you may leave the queue now.");
                                System.out.println("Next customer please");
                                continue;
//                                System.exit(0);
                            }
                        } catch (Exception e) {
                            System.out.println("Error!");
                        }
                    }
                }
            }

            System.out.println("Enter the fuel amount: ");
            double fuelAmount;
            while (true) {
                String fuel = sc.next();
                try {
                    fuelAmount = Double.parseDouble(fuel);
                    break;
                } catch (Exception e) {
                    System.out.println("Error! Please enter a correct fuel amount");
                }
            }

//        Issuing fuel
            customer.setFuelAmount(fuelAmount);
            if (customer.getQueue() == petrolDispenserQueue1) {
                petrolDispenser1.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == petrolDispenserQueue2) {
                petrolDispenser2.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == petrolDispenserQueue3) {
                petrolDispenser3.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == petrolDispenserQueue4) {
                petrolDispenser4.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == dieselDispenserQueue1) {
                dieselDispenser1.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == dieselDispenserQueue2) {
                dieselDispenser2.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == dieselDispenserQueue3) {
                dieselDispenser3.dispenseFuel(fuelAmount);
            }
            //Removing customer
            customer.getQueue().removeCustomer(customer);
            System.out.println("Customer removed from queue successfully");
            System.out.println("For next customer enter 1 else if u want to exit enter 2:");
            int userInput1 = 0;
            while (true) {
                String userInput = sc.next();
                try {
                    userInput1 = Integer.parseInt(userInput);
                    if (userInput1 == 1 || userInput1 == 2) {
                        break;
                    } else {
                        System.out.println("Please enter a valid input");
                    }
                } catch (Exception e) {
                    System.out.println("Error! Please enter a valid input");
                }
            }
            if (userInput1 == 2) {
                break;
            }
        }
        //Printing the statistics
        //calculating the total income per dispenser
        System.out.println("Total income per dispenser");
        System.out.println("Petrol Dispenser 1: " + petrolDispenser1.displayingTotalIncome());
        System.out.println("Petrol Dispenser 2: " + petrolDispenser2.displayingTotalIncome());
        System.out.println("Petrol Dispenser 3: " + petrolDispenser3.displayingTotalIncome());
        System.out.println("Petrol Dispenser 4: " + petrolDispenser4.displayingTotalIncome());
        System.out.println("Diesel Dispenser 1: " + dieselDispenser1.displayingTotalIncome());
        System.out.println("Diesel Dispenser 2: " + dieselDispenser2.displayingTotalIncome());
        System.out.println("Diesel Dispenser 3: " + dieselDispenser3.displayingTotalIncome());

        // Displaying the Remaining stock at close of each repository
        System.out.println("Remaining stock at close in petrol repository");
        petrolRepository.displayingRemainingStock();
        System.out.println("Remaining stock at close in diesel repository");
        dieselRepository.displayingRemainingStock();

        // Total income of the gas station per day per fuel type
        gasStationOwner.setDate(new DateTime(25,12,2022));
        gasStationOwner.displayTotalIncome();

       // The total fuel dispensed per vehicle category type per fuel type

        System.out.println("The total fuel dispensed per vehicle category type per fuel type");
        ArrayList<Customer> petrolCustomers = new ArrayList<>();
        petrolCustomers.addAll(petrolDispenser1.getListOfCustomers());
        petrolCustomers.addAll(petrolDispenser2.getListOfCustomers());
        petrolCustomers.addAll(petrolDispenser3.getListOfCustomers());
        petrolCustomers.addAll(petrolDispenser4.getListOfCustomers());
        System.out.println("\nPetrol");
        System.out.println(petrolCustomers);
        gasStationOwner.dispensedFuelPerVehicleCategory(petrolCustomers);
        ArrayList<Customer> dieselCustomers = new ArrayList<>();
        dieselCustomers.addAll(dieselDispenser1.getListOfCustomers());
        dieselCustomers.addAll(dieselDispenser2.getListOfCustomers());
        dieselCustomers.addAll(dieselDispenser3.getListOfCustomers());
        System.out.println("\nDiesel");
        System.out.println(dieselCustomers);
        gasStationOwner.dispensedFuelPerVehicleCategory(dieselCustomers);
        System.out.println();

        //total number of vehicles served by each dispenser along with the amounts of fuel
        System.out.println("Petrol dispenser 1: ");
        petrolDispenser1.displayingTotalFuelServedPerVehicleType();
        System.out.println("\nPetrol dispenser 2: ");
        petrolDispenser2.displayingTotalFuelServedPerVehicleType();
        System.out.println("\nPetrol dispenser 3: ");
        petrolDispenser3.displayingTotalFuelServedPerVehicleType();
        System.out.println("\nPetrol dispenser 4: ");
        petrolDispenser4.displayingTotalFuelServedPerVehicleType();
        System.out.println("\nDiesel dispenser 1: ");
        dieselDispenser1.displayingTotalFuelServedPerVehicleType();
        System.out.println("\nDiesel dispenser 2: ");
        dieselDispenser2.displayingTotalFuelServedPerVehicleType();
        System.out.println("\nDiesel dispenser 3: ");
        dieselDispenser3.displayingTotalFuelServedPerVehicleType();

        // Displaying vehicle received the largest amount of fuel for the day and type of fuel received
        System.out.println();
        gasStationOwner.displayingVehicleWithLargestFuelDispensed();

        System.out.println("");


    }

}