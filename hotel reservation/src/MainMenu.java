import api.HotelResource;
import model.Customer;
import model.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class MainMenu {
    public static Scanner input;
    public static int exit = 6;

    public static void main(String[] args){
        mainMenu();
    }

    public static void displayMenu(){
        System.out.println("Main Menu");
        System.out.println("1 - Find and reserve a room");
        System.out.println("2 - See your reservations");
        System.out.println("3 - Create an account");
        System.out.println("4 - Admin Menu");
        System.out.println("5 - Exit");
    }

    public static void mainMenu(){
        displayMenu();
        input = new Scanner(System.in);
        int inputNumber = input.nextInt();
        while(inputNumber < exit){
            switch(inputNumber){
                case 1: //fint and reserve a room
                    findAndReserveARoom();
                    break;
                case 2: //see your reservations
                    seeMyReservations();
                    break;
                case 3: //create an account
                    createAnAccount();
                    break;
                case 4: //open admin menu
                    AdminMenu.adminMenu();
                    return;
                case 5: //exit application
                    input.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            displayMenu();
            inputNumber = input.nextInt();
        }
        System.out.println("Invalid Input");
        displayMenu();
        inputNumber = input.nextInt();
    }

    private static void findAndReserveARoom(){
        Date checkIn = new Date();
        Date checkOut = new Date();

        SimpleDateFormat formatting = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your arrival date (dd/mm/yyyy)");
        String checkInDate = scanner.next();
        System.out.println(checkInDate);

        try{
            checkIn = formatting.parse(checkInDate);
        } catch (ParseException e){
            e.printStackTrace();
        }

        System.out.println("Please enter your departure date (dd/mm/yyyy)");
        String checkOutDate = scanner.next();
        try {
            checkOut = formatting.parse(checkOutDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(checkIn);
        System.out.println(checkInDate);

        System.out.println("Available rooms for your arrival and departure days:");
        System.out.println(HotelResource.findARoom(checkIn, checkOut));

        System.out.println("Select a room number");
        String roomNumber = scanner.next();
        IRoom room = HotelResource.getRoom(roomNumber);
        System.out.println(room);
        System.out.println(roomNumber);

        System.out.println("Please enter your email");
        String email = scanner.next();

        HotelResource.bookARoom(email, room, checkIn, checkOut);
        System.out.println("Your reservetion from " + checkIn +
                           " to " + checkOut + ", room " +
                           roomNumber + " has been made (email: " +
                           email + ").");
    }

    private static void seeMyReservations(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email");
        String email = scanner.next();
        System.out.println(HotelResource.getCustomersReservations(email));
    }

    private static Customer createAnAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first name");
        String firstName = scanner.next();
        System.out.println("Enter your last name");
        String lastName = scanner.next();
        System.out.println("Enter your email adress");
        String email = scanner.next();

        HotelResource.createACustomer(email, firstName, lastName);
        return new Customer(firstName, lastName, email);
    }


}
