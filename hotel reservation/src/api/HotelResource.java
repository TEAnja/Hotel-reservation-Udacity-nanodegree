package api;

import java.util.Date;
import java.util.Collection;

import service.CustomerService;
import service.ReservationService;

import model.Customer;
import model.IRoom;
import model.Reservation;

public class HotelResource {
    static CustomerService customerService = new CustomerService();
    static ReservationService reservationService = new ReservationService();

    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkIn, Date checkOut){
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkIn, checkOut);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomerReservations(customer);
    }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }

}
