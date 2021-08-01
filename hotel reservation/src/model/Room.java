package model;

import java.util.Objects;

public class Room implements IRoom{
    final String roomNumber;
    final double price;
    final RoomType type;

    public Room(String roomNumber, double price, RoomType type){
        this.roomNumber = roomNumber;
        this.price = price;
        this.type = type;
    }

    @Override
    public String getRoomNumber(){
        return roomNumber;
    }

    @Override
    public double getRoomPrice(){
        return price;
    }

    @Override
    public boolean isFree(){
        return price == 0;
    }

    @Override
    public RoomType getRoomType(){
        return type;
    }

    @Override
    public String toString(){
        return roomNumber;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Room)) return false;
        Room room  = (Room) o;
        return Objects.equals(this.getRoomNumber(), room.getRoomNumber())
        && Objects.equals(this.getRoomPrice(), room.getRoomPrice())
        && Objects.equals(this.getRoomType(), room.getRoomType());
    }

    @Override
    public int hashCode(){
        return Objects.hash(roomNumber, price, type);
    }
}
