import api.AdminResource;
import model.RoomType;

import java.util.Optional;
import java.util.Scanner;

public class AdminMenu {
    public static Scanner input;
    public static int backToMain = 6;
    private static AdminResource adminResource = new AdminResource();

    public static void main(String[] args){
        adminMenu();
    }

    public static void displayAdminMenu(){
        System.out.println("1 - See all Customers");
        System.out.println("2 - See all Rooms");
        System.out.println("3 - See all Reservations");
        System.out.println("4 - Add a Room");
        System.out.println("5 - Back to Main Menu");
    }

    public static void adminMenu(){
        displayAdminMenu();
        input = new Scanner(System.in);
        int inputNumber = input.nextInt();
        while(inputNumber < backToMain){
            switch(inputNumber){
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
                    return;
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
        AdminMenu.displayAdminMenu();
        inputNumber = input.nextInt();
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
        System.out.println("Please enter new room number.");
        String roomNumber = scanner.next();
        System.out.println("Enter new room price.");
        Double price = scanner.nextDouble();
        System.out.println("Enter new room type: 1 - Single bed or 2 - Double bed");
        String typeStr = scanner.next();
        RoomType type = RoomType.valueOf(typeStr);
        adminResource.addRoom(roomNumber, price, type);
    }

}
