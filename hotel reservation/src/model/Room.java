package model;

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
}
