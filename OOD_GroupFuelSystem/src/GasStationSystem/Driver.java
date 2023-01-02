package GasStationSystem;

import java.util.*;

public class Driver {
    public static void main(String[] args) {

        Random random = new Random();

        //Creating a scanner to get user inputs
        Scanner sc = new Scanner(System.in);

        //Updating the date
        DateTime today = new DateTime(1,1,2023);

        //Creating List of Customers of Respective Queues from the DataBase
        ArrayList<Customer> PetrolDisp1Customers = new ArrayList<>();
        Customer.readDataFromCustomerTable("petrol_dispenser_1_customers",PetrolDisp1Customers);

        ArrayList<Customer> PetrolDisp2Customers = new ArrayList<>();
        Customer.readDataFromCustomerTable("petrol_dispenser_2_customers",PetrolDisp2Customers);

        ArrayList<Customer> PetrolDisp3Customers = new ArrayList<>();
        Customer.readDataFromCustomerTable("petrol_dispenser_3_customers",PetrolDisp3Customers);

        ArrayList<Customer> PetrolDisp4Customers = new ArrayList<>();
        Customer.readDataFromCustomerTable("petrol_dispenser_4_customers",PetrolDisp4Customers);

        ArrayList<Customer> DieselDisp1Customers = new ArrayList<>();
        Customer.readDataFromCustomerTable("diesel_dispenser_1_customers",DieselDisp1Customers);

        ArrayList<Customer> DieselDisp2Customers = new ArrayList<>();
        Customer.readDataFromCustomerTable("diesel_dispenser_2_customers",DieselDisp2Customers);

        ArrayList<Customer> DieselDisp3Customers = new ArrayList<>();
        Customer.readDataFromCustomerTable("diesel_dispenser_3_customers",DieselDisp3Customers);


        //Creating repositories
        Repository petrolRepository = new Repository();
        Repository.readDataFromRepositoryTables("petrol_repository", petrolRepository);

        Repository dieselRepository = new Repository();
        Repository.readDataFromRepositoryTables("diesel_repository", dieselRepository);


        //Creating dispenser objects
        PetrolDispenseManager petrolDispenser1 = new PetrolDispenseManager();
        Thread pd1 = new Thread(petrolDispenser1);
        petrolDispenser1.setRepository(petrolRepository);
        PetrolDispenseManager.readDataFromPetrolDispenserTables("petroldispenser_1", petrolDispenser1);
        petrolDispenser1.setListOfCustomers(PetrolDisp1Customers);

        PetrolDispenseManager petrolDispenser2 = new PetrolDispenseManager();
        Thread pd2 = new Thread(petrolDispenser2);
        petrolDispenser2.setRepository(petrolRepository);
        PetrolDispenseManager.readDataFromPetrolDispenserTables("petroldispenser_2", petrolDispenser2);
        petrolDispenser2.setListOfCustomers(PetrolDisp2Customers);

        PetrolDispenseManager petrolDispenser3 = new PetrolDispenseManager();
        Thread pd3 = new Thread(petrolDispenser3);
        petrolDispenser3.setRepository(petrolRepository);
        PetrolDispenseManager.readDataFromPetrolDispenserTables("petroldispenser_3", petrolDispenser3);
        petrolDispenser3.setListOfCustomers(PetrolDisp3Customers);

        PetrolDispenseManager petrolDispenser4 = new PetrolDispenseManager();
        Thread pd4 = new Thread(petrolDispenser4);
        petrolDispenser4.setRepository(petrolRepository);
        PetrolDispenseManager.readDataFromPetrolDispenserTables("petroldispenser_4", petrolDispenser4);
        petrolDispenser4.setListOfCustomers(PetrolDisp4Customers);

        DieselDispenseManager dieselDispenser1 = new DieselDispenseManager();
        Thread dd1 = new Thread(dieselDispenser1);
        dieselDispenser1.setRepository(dieselRepository);
        DieselDispenseManager.readDataFromDieselDispenserTables("dieseldispenser_1", dieselDispenser1);
        dieselDispenser1.setListOfCustomers(DieselDisp1Customers);

        DieselDispenseManager dieselDispenser2 = new DieselDispenseManager();
        Thread dd2 = new Thread(dieselDispenser2);
        dieselDispenser2.setRepository(dieselRepository);
        DieselDispenseManager.readDataFromDieselDispenserTables("dieseldispenser_1", dieselDispenser2);
        dieselDispenser2.setListOfCustomers(DieselDisp2Customers);

        DieselDispenseManager dieselDispenser3 = new DieselDispenseManager();
        Thread dd3 = new Thread(dieselDispenser3);
        dieselDispenser3.setRepository(dieselRepository);
        DieselDispenseManager.readDataFromDieselDispenserTables("dieseldispenser_1", dieselDispenser3);
        dieselDispenser3.setListOfCustomers(DieselDisp3Customers);


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


        //List of diesel dispensers and petrol dispensers
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
        GasStationOwner gasStationOwner = new GasStationOwner(dieselDispenseManagerArrayList, petrolDispenseManagerArrayList, today);
        Thread o1 = new Thread(gasStationOwner);

        //Assigning the existing customers to dispensers
        petrolDispenser1.setListOfCustomers(PetrolDisp1Customers);
        petrolDispenser2.setListOfCustomers(PetrolDisp2Customers);
        petrolDispenser3.setListOfCustomers(PetrolDisp3Customers);
        petrolDispenser4.setListOfCustomers(PetrolDisp4Customers);

        dieselDispenser1.setListOfCustomers(DieselDisp1Customers);
        dieselDispenser2.setListOfCustomers(DieselDisp2Customers);
        dieselDispenser3.setListOfCustomers(DieselDisp3Customers);


        //Getting customer details and creating a customer object
        System.out.println("\n Welcome to Gas and Service Station Colombo\n");

        while (true) {
            //Getting user input to create a customer
            System.out.println("\nPlease enter your vehicle number: ");
            String vehicleNumber = sc.next();

            //Getting customer vehicle type to decide the queue
            System.out.println("\nPlease input your vehicle type \n 1.Car  2.Van  3.Motor Bike  4.Three wheel  5.Public transport  6.Other");

            int vehicleType = 0;
            while (true) {
                String vehicleTyp = sc.next();
                try {
                    vehicleType = Integer.parseInt(vehicleTyp);
                    if (vehicleType <= 6 && vehicleType >= 1) {
                        break;
                    } else {
                        System.out.println("\nPlease enter a correct vehicle type number");
                    }
                } catch (Exception e) {
                    System.out.println("\nError! Please enter a correct vehicle type number");
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


            System.out.println("\nYou can join these queues");
//      if the 1st queue, customer can join has available positions then he must join that queue
            if (vehicleType == 1 || vehicleType == 2) {
                if (petrolDispenserQueue1.getListOfCustomers().size() >= 10) {
                    if (petrolDispenserQueue2.getListOfCustomers().size() >= 10) {
                        System.out.println("\nOops! There are no available positions in respective queues. Please join the common queue");
                        System.out.println("\nWould you like to join the common queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                Customer.joinQueue(commonQueue, customer);
//                                commonQueue.addCustomer(customer);
                                customer.setQueue(commonQueue);
                                commonQueue.setNoOfCustomers(commonCustomers + 1);
                                System.out.println("\nSuccessfully joined the common queue, thanks for your patience.");

//                            Assigning the common queue customer to a normal queue
                                Queue.moveUpQueue(petrolDispenserQueue2.getListOfCustomers());
//                                petrolDispenserQueue2.getListOfCustomers().add(customer);
                                Customer.joinQueue(petrolDispenserQueue2, customer);
                                petrolDispenser2.addCustomer(customer);
                                customer.setQueue(petrolDispenserQueue2);
                                System.out.println("\nSuccessfully joined the petrol queue 2.");
                                int ticketNo = random.nextInt(1, 100);
                                customer.setTicketId(ticketNo);
                                System.out.println("\nYour ticket Id is " + ticketNo);

                            } else if (ans == 2) {
                                System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                                System.out.println("\nNext customer please");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("\nError!");
                        }
                    } else {
                        System.out.println("\nPetrol Queue 2 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue2));
                        System.out.println("\nWould you like to join this queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                petrolDispenser2.addCustomer(customer);
                                Customer.joinQueue(petrolDispenserQueue2, customer);
//                                petrolDispenserQueue2.addCustomer(customer);
                                customer.setQueue(petrolDispenserQueue2);
                                System.out.println("\nSuccessfully joined the queue.");
                                int ticketNo = random.nextInt(1, 10000);
                                customer.setTicketId(ticketNo);
                                System.out.println("\nYour ticket Id is " + ticketNo); //pass the ticket id
                            } else if (ans == 2) {
                                System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                                System.out.println("\nNext customer please");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("\nError!");
                        }
                    }
                } else {
                    System.out.println("\nPetrol Queue 1 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue1));
                    System.out.println("\nWould you like to join this queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            Customer.joinQueue(petrolDispenserQueue1, customer);
                            petrolDispenser1.addCustomer(customer);
//                            petrolDispenserQueue1.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue1);
                            System.out.println("\nSuccessfully joined the queue.");
                            int ticketNo = random.nextInt(1, 10000);
                            customer.setTicketId(ticketNo);
                            System.out.println("\nYour ticket Id is " + ticketNo); //pass the ticket id
                        } else if (ans == 2) {
                            System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                            System.out.println("\nNext customer please");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nError!");
                    }
                }
            } else if (vehicleType == 3) {
//            System.out.println("Petrol Queue 4 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue4));
                if (petrolDispenserQueue4.getListOfCustomers().size() >= 10) {
                    System.out.println("\nOops! There are no available positions in respective queues. Please join the common queue");
                    System.out.println("\nWould you like to join the common queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            Customer.joinQueue(commonQueue, customer);
                            commonQueue.addCustomer(customer);
//                            customer.setQueue(commonQueue);
                            commonQueue.setNoOfCustomers(commonCustomers + 1);
                            System.out.println("\nSuccessfully joined the common queue, thanks for your patience.");
                            System.out.println("\nNext customer please");

                            //Assigning the common queue customer to a normal queue
                            Queue.moveUpQueue(petrolDispenserQueue4.getListOfCustomers());
                            petrolDispenserQueue4.getListOfCustomers().add(customer);
                            petrolDispenser4.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue4);
                            System.out.println("\nSuccessfully joined the petrol queue 4.");
                            int ticketNo = random.nextInt(1, 10090);
                            customer.setTicketId(ticketNo);
                            System.out.println("\nYour ticket Id is " + ticketNo);

                        } else if (ans == 2) {
                            System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                            System.out.println("\nNext customer please");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nError!");
                    }
                } else {
                    System.out.println("\nPetrol Queue 4 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue4));
                    System.out.println("\nWould you like to join this queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            petrolDispenser4.addCustomer(customer);
                            Customer.joinQueue(petrolDispenserQueue4, customer);
//                            petrolDispenserQueue4.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue4);
                            System.out.println("\nSuccessfully joined the queue.");
                            int ticketNo = random.nextInt(1, 10000);
                            customer.setTicketId(ticketNo);
                            System.out.println("\nYour ticket Id is " + ticketNo); //pass the ticket id
                        } else if (ans == 2) {
                            System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                            System.out.println("\nNext customer please");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nError!");
                    }
                }
            } else if (vehicleType == 4) {
//            System.out.println("Petrol Queue 3 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue3));
                if (petrolDispenserQueue3.getListOfCustomers().size() >= 10) {
                    System.out.println("\nOops! There are no available positions in respective queues. Please join the common queue");
                    System.out.println("\nWould you like to join the common queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            Customer.joinQueue(commonQueue, customer);
//                            commonQueue.addCustomer(customer);
                            customer.setQueue(commonQueue);
                            commonQueue.setNoOfCustomers(commonCustomers + 1);
                            System.out.println("\nSuccessfully joined the common queue, thanks for your patience.");

                            //Assigning the common queue customer to a normal queue
                            Queue.moveUpQueue(petrolDispenserQueue3.getListOfCustomers());
                            Customer.joinQueue(petrolDispenserQueue3, customer);
//                            petrolDispenserQueue3.getListOfCustomers().add(customer);
                            petrolDispenser3.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue3);
                            System.out.println("\nSuccessfully joined the petrol queue 3.");
                            int ticketNo = random.nextInt(1, 34783);
                            customer.setTicketId(ticketNo);
                            System.out.println("\nYour ticket Id is " + ticketNo);

                        } else if (ans == 2) {
                            System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                            System.out.println("\nNext customer please");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nError!");
                    }
                } else {
                    System.out.println("\nPetrol Queue 3 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue3));
                    System.out.println("\nWould you like to join this queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            Customer.joinQueue(petrolDispenserQueue3, customer);
//                            petrolDispenser3.addCustomer(customer);
                            petrolDispenserQueue3.addCustomer(customer);
                            customer.setQueue(petrolDispenserQueue3);
                            System.out.println("\nSuccessfully joined the queue.");
                            int ticketNo = random.nextInt(1, 10000);
                            customer.setTicketId(ticketNo);
                            System.out.println("\nYour ticket Id is " + ticketNo); //pass the ticket id
                        } else if (ans == 2) {
                            System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                            System.out.println("\nNext customer please");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nError!");
                    }
                }
            } else if (vehicleType == 5) {
//            System.out.println("Diesel Queue 1 available number of position: "+Queue.displayAvailablePositions(dieselDispenserQueue1));
                if (dieselDispenserQueue1.getListOfCustomers().size() >= 10) {
                    System.out.println("\nOops! There are no available positions in respective queues. Please join the common queue");
                    System.out.println("\nWould you like to join the common queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            commonQueue.addCustomer(customer);
                            customer.setQueue(commonQueue);
                            commonQueue.setNoOfCustomers(commonCustomers + 1);
                            System.out.println("\nSuccessfully joined the common queue, thanks for your patience.");

                            //Assigning the common queue customer to a normal queue
                            Queue.moveUpQueue(dieselDispenserQueue1.getListOfCustomers());
                            Customer.joinQueue(dieselDispenserQueue1, customer);
//                            dieselDispenserQueue1.getListOfCustomers().add(customer);
                            dieselDispenser1.addCustomer(customer);
                            customer.setQueue(dieselDispenserQueue1);
                            System.out.println("\nSuccessfully joined the diesel queue 1.");
                            int ticketNo = random.nextInt(1, 6000);
                            customer.setTicketId(ticketNo);
                            System.out.println("\nYour ticket Id is " + ticketNo);

                        } else if (ans == 2) {
                            System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                            System.out.println("\nNext customer please");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nError!");
                    }
                } else {
                    System.out.println("\nDiesel Queue 1 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue1));
                    System.out.println("\nWould you like to join this queue? 1:Yes, 2:No\n");
                    try {
                        int ans = sc.nextInt();
                        if (ans == 1) {
                            dieselDispenser1.addCustomer(customer);
                            Customer.joinQueue(dieselDispenserQueue1, customer);
//                            dieselDispenserQueue1.addCustomer(customer);
                            customer.setQueue(dieselDispenserQueue1);
                            System.out.println("\nSuccessfully joined the queue.");
                            int ticketNo = random.nextInt(1, 2000);
                            customer.setTicketId(ticketNo);
                            System.out.println("\nYour ticket Id is " + ticketNo); //pass the ticket id
                        } else if (ans == 2) {
                            System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                            System.out.println("\nNext customer please");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nError!");
                    }
                }
            } else {
                System.out.println("\nPlease input fuel type \n1.Petrol  2.Diesel");
                int fuelType = sc.nextInt();
                if (fuelType == 1) {
//                System.out.println("Petrol Queue 2 available number of positions: "+Queue.displayAvailablePositions(petrolDispenserQueue2));
                    if (petrolDispenserQueue2.getListOfCustomers().size() >= 10) {
                        System.out.println("\nOops! There are no available positions in respective queues. Please join the common queue");
                        System.out.println("\nWould you like to join the common queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
//                                commonQueue.addCustomer(customer);
                                Customer.joinQueue(commonQueue, customer);
                                customer.setQueue(commonQueue);
                                commonQueue.setNoOfCustomers(commonCustomers + 1);
                                System.out.println("\nSuccessfully joined the common queue, thanks for your patience.");

                                //Assigning the common queue customer to a normal queue
                                Queue.moveUpQueue(petrolDispenserQueue2.getListOfCustomers());
//                                petrolDispenserQueue2.getListOfCustomers().add(customer);
                                Customer.joinQueue(petrolDispenserQueue2, customer);
                                petrolDispenser2.addCustomer(customer);
                                customer.setQueue(petrolDispenserQueue2);
                                System.out.println("\nSuccessfully joined the petrol queue 2.");
                                int ticketNo = random.nextInt(1, 6000);
                                customer.setTicketId(ticketNo);
                                System.out.println("\nYour ticket Id is " + ticketNo);

                            } else if (ans == 2) {
                                System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                                System.out.println("\nNext customer please");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("\nError!");
                        }
                    } else {
                        System.out.println("\nPetrol Queue 2 available number of positions: " + Queue.displayAvailablePositions(petrolDispenserQueue2));
                        System.out.println("\nWould you like to join this queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                petrolDispenser2.addCustomer(customer);
                                Customer.joinQueue(petrolDispenserQueue2, customer);
//                                petrolDispenserQueue2.addCustomer(customer);
                                customer.setQueue(petrolDispenserQueue2);
                                System.out.println("\nSuccessfully joined the queue.");
                                int ticketNo = random.nextInt(1, 50000);
                                customer.setTicketId(ticketNo);
                                System.out.println("\nYour ticket Id is " + ticketNo); //pass the ticket id
                            } else if (ans == 2) {
                                System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                                System.out.println("\nNext customer please");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("\nError!");
                        }
                    }
                } else if (vehicleType == 6) {
//                System.out.println("Diesel Queue 2 available number of position: " + Queue.displayAvailablePositions(dieselDispenserQueue2));
//                System.out.println("Diesel Queue 3 available number of position: " + Queue.displayAvailablePositions(dieselDispenserQueue3));
                    if (dieselDispenserQueue2.getListOfCustomers().size() >= 10) {
                        if (dieselDispenserQueue3.getListOfCustomers().size() >= 10) {
                            System.out.println("\nOops! There are no available positions in respective queues. Please join the common queue");
                            System.out.println("\nWould you like to join the common queue? 1:Yes, 2:No\n");
                            try {
                                int ans = sc.nextInt();
                                if (ans == 1) {
//                                    commonQueue.addCustomer(customer);
                                    Customer.joinQueue(commonQueue, customer);
                                    customer.setQueue(commonQueue);
                                    commonQueue.setNoOfCustomers(commonCustomers + 1);
                                    System.out.println("\nSuccessfully joined the common queue, thanks for your patience.");

                                    //Assigning the common queue customer to a normal queue
                                    dieselDispenserQueue3.getListOfCustomers().remove(0);
//                                    dieselDispenserQueue3.getListOfCustomers().add(customer);
                                    Customer.joinQueue(dieselDispenserQueue3, customer);
                                    dieselDispenser3.addCustomer(customer);
                                    customer.setQueue(dieselDispenserQueue3);
                                    System.out.println("\nSuccessfully joined the diesel queue 3.");
                                    int ticketNo = random.nextInt(154, 4500);
                                    customer.setTicketId(ticketNo);
                                    System.out.println("\nYour ticket Id is " + ticketNo);

                                } else if (ans == 2) {
                                    System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                                    System.out.println("\nNext customer please");
                                    continue;
                                }
                            } catch (Exception e) {
                                System.out.println("\nError!");
                            }
                        } else {
                            System.out.println("\nDiesel Queue 3 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue3));
                            System.out.println("\nWould you like to join this queue? 1:Yes, 2:No\n");
                            try {
                                int ans = sc.nextInt();
                                if (ans == 1) {
                                    dieselDispenser3.addCustomer(customer);
//                                    dieselDispenserQueue3.addCustomer(customer);
                                    Customer.joinQueue(dieselDispenserQueue3, customer);
                                    customer.setQueue(dieselDispenserQueue3);
                                    System.out.println("\nSuccessfully joined the queue.");
                                    int ticketNo = random.nextInt(1, 10000);
                                    customer.setTicketId(ticketNo);
                                    System.out.println("\nYour ticket Id is " + ticketNo); //pass the ticket id
                                } else if (ans == 2) {
                                    System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                                    System.out.println("\nNext customer please");
                                    continue;
                                }
                            } catch (Exception e) {
                                System.out.println("\nError!");
                            }
                        }
                    } else {
                        System.out.println("\nDiesel Queue 2 available number of positions: " + Queue.displayAvailablePositions(dieselDispenserQueue2));
                        System.out.println("\nWould you like to join this queue? 1:Yes, 2:No\n");
                        try {
                            int ans = sc.nextInt();
                            if (ans == 1) {
                                dieselDispenser2.addCustomer(customer);
                                Customer.joinQueue(dieselDispenserQueue2, customer);
//                                dieselDispenserQueue2.addCustomer(customer);
                                customer.setQueue(dieselDispenserQueue2);
                                System.out.println("\nSuccessfully joined the queue.");
                                int ticketNo = random.nextInt(1, 100);
                                customer.setTicketId(ticketNo);
                                System.out.println("\nYour ticket Id is " + ticketNo); //pass the ticket id
                            } else if (ans == 2) {
                                System.out.println("\nThank you for using the fuel system, you may leave the queue now.");
                                System.out.println("\nNext customer please");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("\nError!");
                        }
                    }
                }
            }

            System.out.println("\nEnter the fuel amount: ");
            double fuelAmount;
            while (true) {
                String fuel = sc.next();
                try {
                    fuelAmount = Double.parseDouble(fuel);
                    break;
                } catch (Exception e) {
                    System.out.println("\nError! Please enter a correct fuel amount");
                }
            }

//        Issuing fuel
            customer.setFuelAmount(fuelAmount);
            if (customer.getQueue() == petrolDispenserQueue1) {
                Customer.updateCustomerLists(customer, "petrol_dispenser_1_customers");
                petrolDispenser1.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == petrolDispenserQueue2) {
                Customer.updateCustomerLists(customer, "petrol_dispenser_2_customers");
                petrolDispenser2.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == petrolDispenserQueue3) {
                Customer.updateCustomerLists(customer, "petrol_dispenser_3_customers");
                petrolDispenser3.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == petrolDispenserQueue4) {
                Customer.updateCustomerLists(customer, "petrol_dispenser_4_customers");
                petrolDispenser4.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == dieselDispenserQueue1) {
                Customer.updateCustomerLists(customer, "diesel_dispenser_1_customers");
                dieselDispenser1.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == dieselDispenserQueue2) {
                Customer.updateCustomerLists(customer, "diesel_dispenser_2_customers");
                dieselDispenser2.dispenseFuel(fuelAmount);
            } else if (customer.getQueue() == dieselDispenserQueue3) {
                Customer.updateCustomerLists(customer, "diesel_dispenser_3_customers");
                dieselDispenser3.dispenseFuel(fuelAmount);
            }
            //Removing customer
            customer.getQueue().removeCustomer(customer);
            System.out.println("\nCustomer removed from queue successfully");
            System.out.println("\nFor next customer enter 1 else if u want to exit enter 2:");
            int userInput1 = 0;
            while (true) {
                String userInput = sc.next();
                try {
                    userInput1 = Integer.parseInt(userInput);
                    if (userInput1 == 1 || userInput1 == 2) {
                        break;
                    } else {
                        System.out.println("\nPlease enter a valid input");
                    }
                } catch (Exception e) {
                    System.out.println("\nError! Please enter a valid input");
                }
            }
            if (userInput1 == 2) {
                break;
            }
        }
        // Updating the database/writing to database
        PetrolDispenseManager.updateDispenserTables(petrolDispenser1, "petroldispenser_1");
        PetrolDispenseManager.updateDispenserTables(petrolDispenser2, "petroldispenser_2");
        PetrolDispenseManager.updateDispenserTables(petrolDispenser3, "petroldispenser_3");
        PetrolDispenseManager.updateDispenserTables(petrolDispenser4, "petroldispenser_4");
        DieselDispenseManager.updateDispenserTables(dieselDispenser1, "dieseldispenser_1");
        DieselDispenseManager.updateDispenserTables(dieselDispenser2, "dieseldispenser_2");
        DieselDispenseManager.updateDispenserTables(dieselDispenser3, "dieseldispenser_3");




        //Printing the statistics
        System.out.println("\n-------------------------PRINTING THE STATISTICS-------------------------");

        // Total income of the gas station per day per fuel type
        //gasStationOwner.displayTotalIncome();

        //CALLING GAS STATION OWNER
        // Starting the gas station thread
        o1.start();
        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // Displaying the Remaining stock at close of each repository
        System.out.println("\n--Remaining Stock Per Fuel Type--");
        System.out.println("Petrol Repository: "+petrolRepository.displayingRemainingStock());
        System.out.println("Diesel Repository: "+dieselRepository.displayingRemainingStock());

        // The total fuel dispensed per vehicle category per fuel type
        System.out.println("\nThe total fuel dispensed per vehicle category per fuel type");
        ArrayList<Customer> petrolCustomers = new ArrayList<>();
        petrolCustomers.addAll(petrolDispenser1.getListOfCustomers());
        petrolCustomers.addAll(petrolDispenser2.getListOfCustomers());
        petrolCustomers.addAll(petrolDispenser3.getListOfCustomers());
        petrolCustomers.addAll(petrolDispenser4.getListOfCustomers());
        System.out.println("\nPetrol:");
        Repository.checkFuelDispensedPerVehicleCatergory(petrolCustomers);

        ArrayList<Customer> dieselCustomers = new ArrayList<>();
        dieselCustomers.addAll(dieselDispenser1.getListOfCustomers());
        dieselCustomers.addAll(dieselDispenser2.getListOfCustomers());
        dieselCustomers.addAll(dieselDispenser3.getListOfCustomers());
        System.out.println("\nDiesel:");
        Repository.checkFuelDispensedPerVehicleCatergory(dieselCustomers);
        System.out.println();

        //calculating the total income per dispenser AND
        //total number of vehicles served by each dispenser along with the amounts of fuel
        System.out.println("\n----------------------DISPENSER STATISTICS----------------------------");
        System.out.println("\nPetrol Dispenser 1: \n");
        PetrolDispenseManager.displayingTotalIncome(petrolDispenser1, "petroldispenser_1");
        //petrolDispenser1.displayingTotalFuelServedPerVehicleType();
        pd1.start();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\nPetrol Dispenser 2: ");
        PetrolDispenseManager.displayingTotalIncome(petrolDispenser2, "petroldispenser_2");
        //petrolDispenser2.displayingTotalFuelServedPerVehicleType();
        pd2.start();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\nPetrol Dispenser 3: ");
        PetrolDispenseManager.displayingTotalIncome(petrolDispenser3, "petroldispenser_3");
        //petrolDispenser3.displayingTotalFuelServedPerVehicleType();
        pd3.start();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\nPetrol Dispenser 4: ");
        PetrolDispenseManager.displayingTotalIncome(petrolDispenser4, "petroldispenser_4");
        //petrolDispenser4.displayingTotalFuelServedPerVehicleType();
        pd4.start();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\nDiesel Dispenser 1: ");
        DieselDispenseManager.displayingTotalIncome(dieselDispenser1, "dieseldispenser_1");
        //dieselDispenser1.displayingTotalFuelServedPerVehicleType();
        dd1.start();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\nDiesel Dispenser 2: ");
        DieselDispenseManager.displayingTotalIncome(dieselDispenser2, "dieseldispenser_2");
        //dieselDispenser2.displayingTotalFuelServedPerVehicleType();
        dd2.start();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("\nDiesel Dispenser 3: ");
        DieselDispenseManager.displayingTotalIncome(dieselDispenser3, "dieseldispenser_3");
        //dieselDispenser3.displayingTotalFuelServedPerVehicleType();
        dd3.start();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }


//        //total number of vehicles served by each dispenser along with the amounts of fuel
//        System.out.println("\nPetrol dispenser 1: ");
//        petrolDispenser1.displayingTotalFuelServedPerVehicleType();
//
//        System.out.println("\nPetrol dispenser 2: ");
//        petrolDispenser2.displayingTotalFuelServedPerVehicleType();
//
//        System.out.println("\nPetrol dispenser 3: ");
//        petrolDispenser3.displayingTotalFuelServedPerVehicleType();
//
//        System.out.println("\nPetrol dispenser 4: ");
//        petrolDispenser4.displayingTotalFuelServedPerVehicleType();
//
//        System.out.println("\nDiesel dispenser 1: ");
//        dieselDispenser1.displayingTotalFuelServedPerVehicleType();
//
//        System.out.println("\nDiesel dispenser 2: ");
//        dieselDispenser2.displayingTotalFuelServedPerVehicleType();
//        System.out.println("\nDiesel dispenser 3: ");
//        dieselDispenser3.displayingTotalFuelServedPerVehicleType();

    }

}
