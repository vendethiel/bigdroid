package clonewars;

import java.util.ArrayList;
import java.util.Random;

public class ZoneFactory {
    private int currentZoneLevel = 1;
    private int minRooms;
    private int maxRooms;

    public ZoneFactory(int minRooms, int maxRooms) {
        this.minRooms = minRooms;
        this.maxRooms = maxRooms;
    }

    public Zone build() {
        Random random = new Random();
        int roomNumber = random.nextInt(maxRooms - minRooms) + minRooms;
        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < roomNumber; i++) {
            rooms.add(new Room());
        }
        return new Zone(currentZoneLevel++, rooms);
    }
}
