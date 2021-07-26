package api;

import service.ReservationService;
import service.CustomerService;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;

import java.util.Collection;

public class AdminResource {
    public static final CustomerService customerService = new CustomerService();
    public static final ReservationService reservationService = new ReservationService();

    public Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(String roomNumber, double price, RoomType type){
        reservationService.addRoom(roomNumber, price, type);
    }

    public static Collection<IRoom> getAllRooms(){
        return reservationService.allRooms();
    }

    public static Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public static Collection<Reservation> getAllReservations(){
        return reservationService.printAllReservations();
    }
}
