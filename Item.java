import java.util.ArrayList;
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemName;
    private String itemDescription;
    private int itemWeight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
        // initialise instance variables
        itemName = name;
        itemDescription = description;
        itemWeight = weight;
    }

    /**
     * Gets the name of the item
     * @return The string of the item
     */
    public String getName()
    {
        return itemName;
    }
    
    /**
     * Gets the description of the item
     * @return The string to be returned
     */
    public String getDescription()
    {
        return itemDescription;
    }
    
    /**
     * Returns the weight of the item
     * @return The weight of the item to be returned
     */
    public int getWeight()
    {
        return itemWeight;
    }
    
    public void use(String item, Character player, Room currentRoom)
    {
        ArrayList<Item> playerInventory = player.getInventory();
        Item useItem = null;
        
        for(Item searchItem : playerInventory)
        {
            if(searchItem.getName().equals(item))
            {
                useItem = searchItem;
            }
        }
        if(useItem != null)
        {
            switch(item)
            {
                case "knife":
                    if(currentRoom.getName().equals("dining hall"))
                    {
                        Item steak = new Item("steak", "a think, juicy steak",
                            2);
                        System.out.println("You slice a thick cut of meat off of the" +
                            " beast. The knife is now too dull to use.");
                            player.takeItem(steak);
                            player.dropItem(useItem);
                    }
                    else
                    {
                        System.out.println("You can't use that here!");
                    }
                    break;
                case "key":
                    System.out.println("Just walk up to a locked door. "+ 
                        "The key will do the rest!");
                    break;
                case "feather":
                    System.out.println("You don't need to use that. As long " +
                        "as you have it, trapdoors have no power over you!");
                    break;
                default:
                    System.out.println("You don't have that!");
                    break;
            }
        }
        else
        {
            System.out.println("You don't have that!");
        }
    }
}