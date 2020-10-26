import java.util.ArrayList;
import java.util.HashMap;
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
    private int weightLimit = 15;
    private String name;
    private String description;
    private String dialogue;
    private String tradeDialogue;
    private HashMap<String, Item> tradeList;
    private int health = 3;

    /**
     * Constructor for objects of class Player
     */
    public Character()
    {
        // initialise instance variables
        inventory = new ArrayList<>();
    }
    
    public Character(Room location, String name, String description, String dialogue)
    {
        inventory = new ArrayList();
        currentRoom = location;
        this.name = name;
        this.description = description;
        this.dialogue = dialogue;
    }
    
    public Character(Room location, String name, String description, 
        String dialogue, String tradeDialogue)
    {
        inventory = new ArrayList();
        tradeList = new HashMap();
        currentRoom = location;
        this.name = name;
        this.description = description;
        this.dialogue = dialogue;
        this.tradeDialogue = tradeDialogue;
    }
    
    public Room getLocation()
    {
        return currentRoom;
    }
    
    public ArrayList getInventory()
    {
        return inventory;
    }
    
    public int getWeightLimit()
    {   
        return weightLimit;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getWeightCarried()
    {
        int total = 0;
        for(Item item : inventory)
        {
            total += item.getWeight();
        }
        return total;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getDialogue()
    {
        return dialogue;
    }
    
    public String getTradeDialogue()
    {
        return tradeDialogue;
    }
    
    public HashMap getTradeList()
    {
        return tradeList;
    }
    
    public Item getTrade(String price)
    {
        return tradeList.get(price);
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public void heal()
    {
        health++;
    }
    
    public void harm()
    {
        health--;
    }
    
    /**
     * Sets the current location for the player
     * @param location The current location of the player
     */
    public void setRoom(Room location)
    {
        currentRoom = location;
    }
    
    public void setTrade(String toGet, Item toGive)
    {
        tradeList.put(toGet,toGive);
    }
    
    public void setDialogue(String newDialogue)
    {
        dialogue = newDialogue;
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
