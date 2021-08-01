package service;

import java.util.*;

import model.*;

public class ReservationService {
    private static ReservationService reservationService = null;

    public static Collection<IRoom> rooms = new HashSet<>();
    Collection<Reservation> reservations = new HashSet<>();

    ReservationService(){
    }

    public static ReservationService getInstance(){
        if(reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(String roomNumber, double price, RoomType type){
        Room roomAdded = new Room(roomNumber, price, type);

        List<String> roomNumbers = new ArrayList<String>();
        for(IRoom room : rooms){
            roomNumbers.add(room.getRoomNumber());
        }

        if(!roomNumbers.contains(roomAdded.getRoomNumber())){
            rooms.add(roomAdded);
            System.out.println("You have added a room:\nRoom number: " + roomNumber + "\nprice: " + price + "\ntype: " + type);
        }else{
            System.out.println("A room with this room number already exists. Try another room number.");
        }
    }

    public static IRoom getARoom(String roomNumber){
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
                return rooms;
            }else{
                return null;
            }
        }else{
            return findAvailableRooms(checkIn, checkOut);
        }
    }

    public Collection<IRoom> findAvailableRooms(Date checkIn, Date checkOut){
        Collection<IRoom> availableRooms = new HashSet<>();

        if(reservations.size() == 0){
            availableRooms = rooms;
            return availableRooms;
        }else {
            for (Reservation reservation : reservations) {
                for(IRoom room : rooms){
                    if((room.getRoomNumber().equals(reservation.getRoom().getRoomNumber()))

                    && ((checkIn.before(reservation.getCheckIn()))&&(checkOut.before(reservation.getCheckIn()))
                       || (checkIn.after(reservation.getCheckOut())&&checkOut.after(reservation.getCheckOut())))

                    || (!reservation.getRoom().getRoomNumber().contains(room.getRoomNumber()))){

                        availableRooms.add(room);
                    }else if(room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())){
                        availableRooms.remove(room);
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
                }
            }
            System.out.println(customersReservations);
            return customersReservations;
        }
    }

    public Collection<Reservation> printAllReservations(){
        if(reservations.isEmpty()){
            return null;
        }else {
            return reservations;
        }
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
