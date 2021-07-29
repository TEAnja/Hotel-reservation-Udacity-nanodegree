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
        System.out.println("You have added a room:\nRoom number: " + roomNumber + "\nprice: " + price + "\ntype: " + type);
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
            if(!rooms.isEmpty()) {
                System.out.println(rooms);
                return rooms;
            }else{
                //System.out.println("There are no rooms yet.");
                return null;
            }
        } else {
            return findAvailableRooms(checkIn, checkOut);
        }
    }

    public Collection<IRoom> findAvailableRooms(Date checkIn, Date checkOut){
        Collection<IRoom> availableRooms = new HashSet<>();

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
        if(reservations.isEmpty()){
            System.out.println("No reservations have been made yet.");
            return null;
        }else {
            for (Reservation reservation : reservations) {
                if (reservation.getCustomer().equals(customer)) {
                    customersReservations.add(reservation);
                    System.out.println("ReservationService/getCustomerReservations");
                }
                System.out.println("There are no reservations made on this email.");
                return null;
            }
            System.out.println(customersReservations);
            return customersReservations;
        }
    }

    public Collection<Reservation> printAllReservations(){
        if(reservations.isEmpty()){
            System.out.println("There are no reservations.");
        }
        return reservations;
    }

    public Collection<IRoom> allRooms(){
        if(rooms.isEmpty()){
            System.out.println("There are no rooms yet.");
            return null;
        }else {
            for(IRoom room :rooms){
                System.out.println("Room number: " + room.getRoomNumber() + "\nRoom price: " + room.getRoomPrice() + "\nRoom typle: " + room.getRoomType() + "\n");
            }
        }
        return rooms;
    }
}
