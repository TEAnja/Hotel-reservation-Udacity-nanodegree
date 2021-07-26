package service;

import java.util.HashSet;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class ReservationService {
    Collection<IRoom> rooms = new HashSet<>();
    Collection<Reservation> reservations = new HashSet<>();

    public void addRoom(String roomNumber, double price, RoomType type){
        Room room = new Room(roomNumber, price, type);
        rooms.add(room);
    }

    public IRoom getARoom(String roomNumber){
        for (IRoom room : rooms){
            if(room.getRoomNumber().equals(roomNumber)){
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkIn, Date checkOut){
        Reservation reservation = new Reservation(customer, room, checkIn, checkOut);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkIn, Date checkOut){
        if (reservations.isEmpty()){
            return rooms;
        } else {
            return findAvailableRooms(checkIn, checkOut);
        }
    }

    public Collection<IRoom> findAvailableRooms(Date checkIn, Date checkOut){
        List<IRoom> availableRooms = new ArrayList<>();

        for(Reservation reservation : reservations){
            if(!reservation.getCheckIn().after(checkIn) && !reservation.getCheckOut().before(checkOut)){
                for(IRoom room : rooms){
                    if(!reservation.getRoom().equals(room)){
                        availableRooms.add(room);
                    }
                }
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomerReservations(Customer customer){
        List<Reservation> customersReservations = new ArrayList<>();
        for (Reservation reservation : reservations){
            if(reservation.getCustomer().equals(customer)){
                customersReservations.add(reservation);
            }
        }
        return customersReservations;
    }

    public Collection<Reservation> printAllReservations(){
        if(reservations.isEmpty()){
            System.out.println("There are no reservations.");
        }
        return reservations;
    }

    public Collection<IRoom> allRooms(){
        return rooms;
    }
}
