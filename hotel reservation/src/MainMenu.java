import api.HotelResource;
import model.Customer;
import model.IRoom;
//import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
//import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

public class MainMenu {
    //private static final ReservationService reservationService = ReservationService.getInstance();
    public static Scanner input;
    public static int exit = 6;
    //public static Calendar calendar = Calendar.getInstance();

    public static void main(String[] args){
        mainMenu();
    }

    public static void displayMenu(){
        System.out.println("\nMain Menu");
        System.out.println("1 - Find and reserve a room");
        System.out.println("2 - See your reservations");
        System.out.println("3 - Create an account");
        System.out.println("4 - Admin Menu");
        System.out.println("5 - Exit");
    }

    public static void mainMenu(){
        displayMenu();
        input = new Scanner(System.in);
        try{
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
                        System.out.println("Thank you for choosing our service. Good bye!");
                        input.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
                mainMenu();
                inputNumber = input.nextInt();
            }
            System.out.println("Invalid Input");
            mainMenu();
        }catch(Exception e){
            System.out.println("Invalid input");
            mainMenu();
        }
    }

    private static void findAndReserveARoom() {
        Date checkIn = null;
        Date checkOut = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your email");
        String email = scanner.next();
        Customer customer = HotelResource.getCustomer(email);

        if (customer != null){
            System.out.println("Please enter your arrival date (dd/mm/yyyy)");
            while(checkIn == null) {
                String checkInDate = scanner.next();
                boolean isDateValid = validateDate(checkInDate);
                if(!isDateValid){
                    System.out.println("Please enter a valid date (dd/mm/yyyy).");
                }else{
                    checkIn = parseDate(checkInDate);
                }
            }

            System.out.println("Please enter your departure date (dd/mm/yyyy)");
            while(checkOut == null || checkIn.after(checkOut)) {
                String checkOutDate = scanner.next();
                boolean isDateValid = validateDate(checkOutDate);
                if (!isDateValid) {
                    System.out.println("Please enter a valid date (dd/mm/yyyy).");
                } else {
                    checkOut = parseDate(checkOutDate);
                    if (checkOut.after(checkIn)) {
                        checkOut = parseDate(checkOutDate);
                    } else {
                        System.out.println("Departure date must be after arrival date. Please enter a valid departure date.");

                    }
                }
            }

            if(HotelResource.findARoom(checkIn, checkOut) == null || HotelResource.findARoom(checkIn, checkOut).isEmpty()) {
                System.out.println("\nThere are no available rooms.");
            }else{
                System.out.println("Available rooms for your arrival and departure days:");
                for(IRoom room : HotelResource.findARoom(checkIn, checkOut)){
                    System.out.println("Room number " + room.getRoomNumber() + ", room type: " + room.getRoomType() + "; price: " + room.getRoomPrice());
                }

                System.out.println("Would you like to make a reservation? y/n");
                try {
                    while (true) {
                        String yesNo = input.next();
                        if(yesNo.equalsIgnoreCase("n")) {
                            mainMenu();
                        }if(yesNo.equalsIgnoreCase("y")){
                            break;
                        } else {
                            System.out.println("Insert 'y' for yes and 'n' for no.");
                        }
                    }
                }catch(Exception e){
                    System.out.println("Insert 'y' for yes or 'n' for no.");
                }

                System.out.println("Select a room number");
                //String roomNumber = scanner.next();
                //-----

                try{
                    while(true){
                        String roomNumber = scanner.next();
                        if(HotelResource.findARoom(checkIn, checkOut).contains(HotelResource.getRoom(roomNumber))){
                            IRoom room = HotelResource.getRoom(roomNumber);
                            //System.out.println(room);
                            //System.out.println(roomNumber);

                            HotelResource.bookARoom(email, room, checkIn, checkOut);
                            System.out.println("Your reservetion from " + checkIn +
                                    " to " + checkOut + ", room '" +
                                    roomNumber + "' has been made (your email: " +
                                    email + ").");
                            //mainMenu();
                            break;
                        }else{
                            System.out.println("Please select a room number from available rooms.");
                        }
                    }
                }catch(Exception e){
                    System.out.println("Please select a room number from available rooms.");
                }

                //-----
//                IRoom room = HotelResource.getRoom(roomNumber);
//                System.out.println(room);
//                System.out.println(roomNumber);
//
//                HotelResource.bookARoom(email, room, checkIn, checkOut);
//                System.out.println("Your reservetion from " + checkIn +
//                        " to " + checkOut + ", room '" +
//                        roomNumber + "' has been made (your email: " +
//                        email + ").");
            }
        }else{
            System.out.println("Please create an account before reserving a room.");
        }
    }

    private static void seeMyReservations(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email");
        String email = scanner.next();
        HotelResource.getCustomersReservations(email);
    }

    private static Customer createAnAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first name");
        String firstName = scanner.next();
        System.out.println("Enter your last name");
        String lastName = scanner.next();
        System.out.println("Enter your email address");
        String email = scanner.next();

        HotelResource.createACustomer(email, firstName, lastName);
        System.out.println("You have created an account: " + firstName + " " + lastName + " " + email);
        return null;//new Customer(firstName, lastName, email);
    }

    public static Date parseDate(String stringToDate){
        try{
            //Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stringToDate);
            return new SimpleDateFormat("dd/MM/yyyy").parse(stringToDate);
        }catch (ParseException e){
            System.out.println("Enter date in a valid format.");
            return null;
        }
    }

    public static boolean validateDate(String date){
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            df.parse(date);
            return true;
        }catch(ParseException e) {
            return false;
        }
    }


}
