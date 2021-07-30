import api.AdminResource;
import model.RoomType;
import service.ReservationService;

import java.util.Scanner;

public class AdminMenu {
    public static Scanner input;
    public static int backToMain = 6;
    public static int validRoomType = 3;
    private final static AdminResource adminResource = new AdminResource();//TU PREJ NI BLO FINAL111

    public static void main(String[] args){
        adminMenu();
    }

    public static void displayAdminMenu(){
        System.out.println("\n1 - See all Customers");
        System.out.println("2 - See all Rooms");
        System.out.println("3 - See all Reservations");
        System.out.println("4 - Add a Room");
        System.out.println("5 - Back to Main Menu");
    }

    public static void adminMenu(){
        displayAdminMenu();
        input = new Scanner(System.in);
        try {
            int inputNumber = input.nextInt();
            while (inputNumber < backToMain) {
                //System.out.println("\n");
                switch (inputNumber) {
                    case 1: //see all customers
                        seeAllCustomers();
                        break;
                    case 2: //see all rooms
                        seeAllRooms();
                        break;
                    case 3: //see all reservations
                        seeAllReservations();
                        break;
                    case 4: //add a room
                        addARoom();
                        break;
                    case 5: //back to main menu
                        MainMenu.mainMenu();
                        return;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
                AdminMenu.displayAdminMenu();
                inputNumber = input.nextInt();
            }
            System.out.println("Invalid Input");
            AdminMenu.adminMenu();
            //inputNumber = input.nextInt();
        }catch(Exception e){
            System.out.println("Invalid input");
            adminMenu();
        }
    }

    private static void seeAllCustomers(){
        adminResource.getAllCustomers();
    }

    private static void seeAllRooms(){
        adminResource.getAllRooms();
    }

    private static void seeAllReservations(){
        adminResource.getAllReservations();
    }

    private static void addARoom(){
        Scanner scanner = new Scanner(System.in);
        String roomNumber = null;
        Double price = null;
        Integer intType = null;
        RoomType roomType = null;

        System.out.println("Please enter a new room number.");
        while(true) {
            try {
                Integer roomNumberInput = Integer.parseInt(scanner.next());
                roomNumber = Integer.toString(roomNumberInput);
                if(ReservationService.getARoom(roomNumber) == null){
                    break;
                }else{
                    System.out.println("This room number already exists. Please try another one.");
                }
            } catch (NumberFormatException ignore) {
                System.out.println("Please enter a number.");
            }
        }

        System.out.println("Enter new room price.");
        while(true) {
            try {
                price = Double.parseDouble(scanner.next());
                break;
            } catch (NumberFormatException ignore) {
                System.out.println("Please enter a number.");
            }
        }

        System.out.println("Enter new room type: 1 - Single bed or 2 - Double bed");
        while(true) {
            try{
                intType = Integer.parseInt(scanner.next());
                if (intType < validRoomType) {
                    try{
                        while(roomType == null){
                            switch(intType){
                                case 1:
                                    roomType = RoomType.SINGLE;
                                    break;
                                case 2:
                                    roomType = RoomType.DOUBLE;
                                    break;
                                default:
                                    System.out.println("Please select '1' for a single or '2' for a double room.");
                                    break;
                            }
                            adminResource.addRoom(roomNumber, price, roomType);
                            break;
                        }
                        return;
                    }catch(Exception e) {
                        System.out.println("Please select '1' for a single or '2' for a double room.");
                    }
                }else{
                    System.out.println("Please select '1' for a single or '2' for a double room.");
                }
            }catch (Exception e){
                System.out.println("Please select '1' for a single or '2' for a double room.");
            }
        }
    }
}
