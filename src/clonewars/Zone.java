package clonewars;

import java.util.ArrayList;

public class Zone {
    private int level;
    private ArrayList<Room> rooms;
    private int currentRoom = 0;
    private EnemyFactory enemyFactory;

    public Zone(int level, ArrayList<Room> rooms) {
        this.level = level;
        this.rooms = rooms;
        this.enemyFactory = new EnemyFactory(level, level + 2);
    }

    public Fight startFight(Character character) {
        return new Fight(character, enemyFactory.random());
    }

    public boolean goNextRoom() {
        if (isLastRoom()) {
            // end of the road
            return false;
        } else {
            currentRoom++;
            return true;
        }
    }

    public boolean isLastRoom() {
        return currentRoom == rooms.size() - 1;
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoom);
    }

    public int getLevel() {
        return level;
    }

    public int getRoomNumber() {
        return currentRoom;
    }
}
