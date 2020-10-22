import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> inventory;
    private Room currentRoom;
    private final int WEIGHTLIMIT = 50;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        inventory = new ArrayList<>();
        
    }

    /**
     * Sets the current location for the player
     * @param location The current location of the player
     */
    public void setRoom(Room location)
    {
        currentRoom = location;
    }
    
    public Room getLocation()
    {
        return currentRoom;
    }
    
    public ArrayList getInventory()
    {
        return inventory;
    }
    
    public void takeItem(Item item)
    {
        inventory.add(item);
    }
    
    public void dropItem(Item item)
    {
        currentRoom.addItem(item);
        inventory.remove(item);
    }
}
