import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Character
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> inventory;
    private Room currentRoom;
    private final int WEIGHTLIMIT = 50;

    /**
     * Constructor for objects of class Player
     */
    public Character()
    {
        // initialise instance variables
        inventory = new ArrayList<>();
        
    }

    public Character(Item item, Room location)
    {
        currentRoom = location;
        inventory.add(item);
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
    
    public String listItems()
    {
        String itemString = "The items in your inventory are:\n";
        if(inventory != null)
        {
            for(Item item : inventory)
            {
                itemString += item.getName() + ": " + item.getDescription() + 
                    ", it weighs " + item.getWeight() + " lbs.\n";
            }
            return itemString;
        }
        else
        {
            return "You don't have anything.";
        }
    }
    
    public void takeItem(Item item)
    {
        inventory.add(item);
    }
    
    public void dropItem(Item item)
    {
        inventory.remove(item);
    }
}
