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
    
}