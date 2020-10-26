import java.util.ArrayList;
/**
 * Class Item - an item in an adventure game.
 * 
 * This class is part of the "Ruins of Adventure" application. 
 * "Ruins of Adventure" is a fairly simple, text based adventure game.
 * 
 * Items each store their name, description, and weight. This class is also 
 * responsible for the effects each item as, as detailed by the switch case in the
 * use method.
 *
 * @author Zach Theis
 * @version 2020.10.26
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
    
    /**
     * Uses an item in the player's inventory, to various effects contained in a 
     * switch statement.
     * @param item The name of the item to be used
     * @param player The player character
     * @param roomNPCs The arraylist of existing NPCs
     */
    public void use(String item, Character player, ArrayList<Character> roomNPCs)
    {
        ArrayList<Item> playerInventory = player.getInventory();
        Item useItem = null;
        Character target = null;
        int index = 0;
        Room currentRoom = player.getLocation();
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
                        System.out.println("You slice a thick cut of meat off" +
                            " of the beast.\nThe knife is now too dull to use.");
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
                case "ring":
                    System.out.println("You don't need to use that. As long " +
                        "as you have it, trapdoors have no power over you!");
                    break;
                case "stake":
                    while(target == null && index < roomNPCs.size())
                    {
                        if(roomNPCs.get(index).getName().equals("vampire") &&
                            roomNPCs.get(index).getLocation() == currentRoom)
                        {
                            target = roomNPCs.get(index);
                        }
                        index++;
                    }
                    if(target != null)
                    {
                        Item ashes = new Item("ashes", 
                            "the ashy remains of a vampire", 3);
                        currentRoom.addItem(ashes);
                        System.out.println("You stake the vampire in the heart."
                            + "\nHe turns into a pile of ashes on the ground.");
                        player.dropItem(useItem);
                        target.setRoom(currentRoom.getExit("dead"));
                    }
                    else
                    {
                        System.out.println("You can't use that here.");
                    }
                    break;
                case "sword":
                    boolean hasShield = false;
                    while(!hasShield && index < playerInventory.size())
                    {
                        if(playerInventory.get(index).getName().equals("shield"))
                        {
                            hasShield = true;
                        }
                        index++;
                    }
                    while(target == null && index < roomNPCs.size())
                    {
                        if(roomNPCs.get(index).getName().equals("ogre") &&
                            roomNPCs.get(index).getLocation() == currentRoom)
                        {
                            target = roomNPCs.get(index);
                        }
                        index++;
                    }
                    if(target != null && !hasShield)
                    {
                        System.out.println("You swing your sword at the ogre..."
                        + "\n...but it overpowers you with it's massive club!"
                        + "\nYou limp away, defeated.");
                        player.harm();
                        Room nextRoom = currentRoom.getExit("east");
                        player.setRoom(nextRoom);
                        System.out.println(nextRoom.getLongDescription());
                    }
                    else if(target != null && hasShield)
                    {
                        Item head = new Item("head", 
                            "the head of the slain ogre", 5);
                        currentRoom.addItem(head);
                        System.out.println("You swing your sword at the ogre..."
                            + "\n...and block his club with your shield!\n" +
                            "The ogre's head and body fall to the ground," +
                            " separately.");
                        target.setRoom(currentRoom.getExit("dead"));
                    }
                    else
                    {
                        System.out.println("You can't use that here.");
                    }
                    break;
                case "potion":
                    System.out.println("You feel vitality return to your body.");
                    player.heal();
                    player.dropItem(useItem);
                    break;
                case "rose":
                    System.out.println("What a sweet smelling rose!");
                    break;
                case "shield":
                    System.out.println("You can now safely face the ogre.");
                    break;
                case "amulet":
                    if(currentRoom.getName().equals("parlor"))
                    {
                        Item ring = new Item("ring", 
                            "a magical ring with the symbol of a feather", 4);
                        System.out.println("The amulet emits a bring light, " +
                        "revealing a golden ring on the table!\nHaving served" +
                        " its purpose, the amulet shatters.");
                        player.dropItem(useItem);
                        currentRoom.addItem(ring);
                    }
                    else
                    {
                        System.out.println("You can't use that here.");
                    }
                    break;
                case "ashes":
                    System.out.println("Use these to prove the vampire is slain.");
                    break;
                case "head":
                    System.out.println("Use this to prove you've killed the ogre.");
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