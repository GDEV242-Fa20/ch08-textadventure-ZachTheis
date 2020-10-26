import java.util.ArrayList;
import java.util.HashMap;
/**
 * Write a description of class Player here.
 *
 * @author Zach Theis
 * @version 2020.10.25
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
    
    /**
     * Overloaded constructor, for adding characters that have pre-assigned fields
     * This is used for most NPCs.
     * @param location The room in which the character can be found
     * @param name The character's name
     * @param description The character's description
     * @param dialogue What the character says when the player talks to them
     */
    public Character(Room location, String name, String description, 
        String dialogue)
    {
        inventory = new ArrayList();
        currentRoom = location;
        this.name = name;
        this.description = description;
        this.dialogue = dialogue;
    }
    
    /**
     * Overloaded constructor which adds trade dialogue and initializes a 
     * trade list. This is used exclusively for NPCs with items to trade.
     * @param location The room in which the character can be found
     * @param name The character's name
     * @param description The character's description
     * @param dialogue What the character says when the player talks to them
     * @param tradeDialogue What they character says when you trade with them
     */
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
    
    /**
     * Gets a character's location
     * @return The character's current room.
     */
    public Room getLocation()
    {
        return currentRoom;
    }
    
    /**
     * Gets the full inventory of items carried by a character
     * @return The character's inventory
     */
    public ArrayList getInventory()
    {
        return inventory;
    }
    
    /**
     * Gets the character's weight limit. This is set to 15 initially.
     * There is currently no way to change this, but future designs may wish to.
     * @return The maximum weight the character can carry.
     */
    public int getWeightLimit()
    {   
        return weightLimit;
    }
    
    /**
     * Gets the character's name
     * @return The name to be returned
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets the total amount of weight the character has in their inventory
     * @return The total of the weight fields of all items in the inventory
     */
    public int getWeightCarried()
    {
        int total = 0;
        for(Item item : inventory)
        {
            total += item.getWeight();
        }
        return total;
    }
    
    /**
     * Get's the charater's description
     * @return The description to return
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Get's the character's programmed dialogue
     * @return The string containing the dialogue
     */
    public String getDialogue()
    {
        return dialogue;
    }
    
    /**
     * Get's the secondary dialogue stored in characters with items to trade
     * @return The string containing the trade dialogue.
     */public String getTradeDialogue()
    {
        return tradeDialogue;
    }
    
    /**
     * Gets the full collection of items the character will trade
     * @return the HashMap containing items paired with the names of items the 
     * Player can give them.
     */
    public HashMap getTradeList()
    {
        return tradeList;
    }
    
    /**
     * Get's a single item from the trade list
     * @price The item name keyed to the desired item the player is trading for
     */
    public Item getTrade(String price)
    {
        return tradeList.get(price);
    }
    
    /**
     * Checks the current health of the player
     * @return The health value
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Adds 1 to the player's current health value
     */
    public void heal()
    {
        health++;
    }
    
    /**
     * Subtracts 1 from the player's current health value
     */
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
    
    /**
     * Sets an item that a character will trade for and the name of the item
     * they will take for it.
     * @param toGet The name of the item they will take in trade
     * @param toGive The item they will give in trade
     */
    public void setTrade(String toGet, Item toGive)
    {
        tradeList.put(toGet,toGive);
    }
    
    /**
     * Sets new dialoge for an NPC
     * @param newDialogue The new dialoge to be returned when the player talks to
     * the NPC
     */
    public void setDialogue(String newDialogue)
    {
        dialogue = newDialogue;
    }
    
    /**
     * Lists the items in your inventory, including name, description, and weight
     */
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
    
    /**
     * Adds an item to the character's inventory
     * @param item The item to be added
     */
    public void takeItem(Item item)
    {
        inventory.add(item);
    }
    
    /**
     * Removes an item from the character's inventory
     * @param The item to be removed
     */
    public void dropItem(Item item)
    {
        inventory.remove(item);
    }
}
