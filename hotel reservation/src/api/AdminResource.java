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
    public static final ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(String roomNumber, double price, RoomType type){
        reservationService.addRoom(roomNumber, price, type);
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.allRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public Collection<Reservation> getAllReservations(){
        return reservationService.printAllReservations();
    }
}
