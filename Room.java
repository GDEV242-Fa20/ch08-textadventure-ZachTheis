import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Zach Theis
 * @version 2020.10.25
 */

public class Room 
{
    private String name;
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    private ArrayList<Item> items; //stores the items in this room.
    private boolean trapped; //indicates if this room has a trap door.
    private boolean locked;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description) 
    {
        this.name = name;
        this.description = description;
        trapped = false;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }
    
    public Room(String name, String description, boolean trapped) 
    {
        this.name = name;
        this.description = description;
        this.trapped = trapped;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }
    
    public Room(boolean locked, String name, String description) 
    {
        this.name = name;
        this.description = description;
        trapped = false;
        this.locked = locked;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public String getName()
    {
        return name;
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     The items in this room are: knife
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + listItems() + ".\n" + 
            getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            if(!exit.equals("hidden"))
            {
                returnString += " " + exit;
            }
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public boolean isTrapped()
    {
        return trapped;
    }
    
    public boolean isLocked()
    {
        return locked;
    }
    
    /**
     * Gets the full list of items stored in the room
     * @return The ArrayList of items
     */
    public ArrayList getItems()
    {
        return items;
    }
    
    public String listItems()
    {
        String returnString = "The items in this room are:";
        boolean empty = true;
        if(items != null)
        {
            for(Item item : items)
            {
                returnString += " " + item.getName();
                empty = false;
            }
        }
        else
        {
            returnString = "There are no items here.";
        }
        if(empty)
        {
            return "There are no items here";
        }
        else
        {
            return returnString;
        }
    }
    
    public void addItem(String name, String description, int weight)
    {
        items.add(new Item(name, description, weight));
    }
    
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    public void removeItem(Item item)
    {
        items.remove(item);
    }
}

