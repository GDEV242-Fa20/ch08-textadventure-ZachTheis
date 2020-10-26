import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Zach Theis
 * @version 2020.10.25
 */

public class Game 
{
    private Parser parser;
    private Character player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Character();
        createGameState();
        //createCharacters();
        //createItems();
    }

    /**
     * Create all the rooms and link their exits together. Also create Items and 
     * Characters and add them to the appropriate rooms
     */
    private void createGameState()
    {
        //Declare all rooms
        Room outside, courtyard, trainingYard, gateHouse, barracks, grandHall, 
        garden, chapel, diningHall, kitchen, parlor, audienceChamber, throneRoom, 
        treasury, bedchamber, tower, dungeon, cellar, wineCellar, larder, cave;
                    
        // initialize each room, adding exits to each
        outside = new Room("outside", "outside the castle gate");
        courtyard = new Room("courtyard", "in the courtyard");
        trainingYard = new Room("training yard", "in the training yard");
        gateHouse = new Room("gate house", "in the gate house");
        barracks = new Room(true, "barracks", "in the barracks");
        grandHall = new Room(true, "grand hall", "in the grand hall");
        garden = new Room("garden", "in the gardens");
        chapel = new Room(true, "chapel", "in the chapel");
        diningHall = new Room("dining hall", "in the dining hall");
        kitchen = new Room("kitchen", "in the kitchen");
        parlor = new Room("parlor", "in the parlor");
        audienceChamber = new Room("audience chamber", "in the audience chamber");
        throneRoom = new Room("throne room", "in the throne room");
        treasury = new Room("treasury", "in the treasury", true);
        bedchamber = new Room("bedchamber", "in the royal bedchamber");
        tower = new Room("tower", "in the wizard's tower");
        dungeon = new Room("dungeon", "trapped in the dungeon");
        cellar = new Room("cellar", "in the cellar");
        wineCellar = new Room("wine cellar", "in the wine cellar");
        larder = new Room("larder", "in the larder");
        cave = new Room("cave", "in a dank cave");
        
        // set room exits
        outside.setExit("north", courtyard);

        courtyard.setExit("north", grandHall);
        courtyard.setExit("east", trainingYard);
        courtyard.setExit("south", outside);
        
        trainingYard.setExit("east", barracks);
        trainingYard.setExit("south", gateHouse);
        trainingYard.setExit("west", courtyard);
        
        barracks.setExit("west", trainingYard);
        
        gateHouse.setExit("north", trainingYard);

        grandHall.setExit("north", garden);
        grandHall.setExit("east", diningHall);
        grandHall.setExit("south", courtyard);
        grandHall.setExit("west", parlor);  
        grandHall.setExit("up", audienceChamber);
        grandHall.setExit("down", cellar);

        garden.setExit("north", chapel);
        garden.setExit("south", grandHall);

        diningHall.setExit("north", kitchen);
        diningHall.setExit("west", grandHall);
        
        kitchen.setExit("south", diningHall);
        
        parlor.setExit("east", grandHall);
        
        audienceChamber.setExit("north", throneRoom);
        audienceChamber.setExit("west", tower);
        audienceChamber.setExit("down", grandHall);
        
        throneRoom.setExit("north", treasury);
        throneRoom.setExit("east", bedchamber);
        throneRoom.setExit("south", audienceChamber);
        
        treasury.setExit("south", throneRoom);
        treasury.setExit("hidden", dungeon);
        
        bedchamber.setExit("west", throneRoom);
        
        tower.setExit("east", audienceChamber);  
        
        cellar.setExit("up", grandHall);
        cellar.setExit("east", wineCellar);
        cellar.setExit("west", cave);
        
        wineCellar.setExit("north", larder);
        wineCellar.setExit("west", cellar);
        
        larder.setExit("south", wineCellar);
        
        cave.setExit("east", cellar);
        
        dungeon.setExit("hidden", outside);
        
        //Declare all items
        Item sword, shield, knife, beast, potion, rose, wine, stake, amulet, 
        treasure, feather, key1, key2, key3, ashes;
        
        //initialize all items
        sword = new Item("sword", "a gleaming steel sword", 5);
        shield = new Item("shield", "a sturdy steel shield", 6);
        knife = new Item("knife", "a sharp, heavy kitchen knife", 1);
        beast = new Item("beast", "a gigantic roast beast of some kind", 100);
        potion = new Item("potion", "a glowing red potion", 2);
        rose = new Item("rose", "a beautiful red rose", 1);
        wine = new Item("wine", "a bottle of dark wine", 3);
        stake = new Item("stake", "a wooden stake", 5);
        amulet = new Item("amulet", "a magical amulet, set with a gem", 2);
        treasure = new Item("treasure", "the friends you made along the way", 15);
        feather = new Item("feather", "a magical feather", 4);
        key1 = new Item("key", "a single-use key", 1);
        key2 = new Item("key", "a single-use key", 1);
        key3 = new Item("key", "a single-use key", 1);
            
        //add items to rooms
        barracks.addItem(sword);
        barracks.addItem(shield);
        barracks.addItem(key2);
        
        gateHouse.addItem(key1);
        
        kitchen.addItem(knife);
        
        diningHall.addItem(beast);
        diningHall.addItem(potion);
        
        garden.addItem(rose);
        
        chapel.addItem(wine);
        chapel.addItem(stake);
        
        tower.addItem(amulet);
        
        bedchamber.addItem(feather);
        
        treasury.addItem(treasure);
        
        //Declare all characters
        Character knight, skeleton, ogre, wizard, vampire, priest;
        
        //initialize all characters
        knight = new Character(barracks, "a knight in somewhat less-than-" +
            "shining armor");
        skeleton = new Character(dungeon, "an animate - and very talkative - " +
            "skeleton");
        ogre = new Character(cave, "a smelly, brutish ogre.");
        wizard = new Character(tower, "a robed, bearded wizard");
        vampire = new Character(wineCellar, "a very stereotypical vampire");
        priest = new Character(chapel, "a holy man, complete with mitre and" +
            " rosary");
            
        
        
        //add character to rooms
        

        player.setRoom(outside);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getLocation().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                lookAround();
                break;
                
            case TAKE:
                pickUpItem(command);
                break;
                
            case DROP:
                putDownItem(command);
                break;
                
            case INVENTORY:
                getInventory();
                break;
                
            case USE:
                useItem(command, player, player.getLocation());
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getLocation().getExit(direction);
        ArrayList<Item> playerInventory = player.getInventory();
        Item playerKey = null;
        int index = 0;
        if (nextRoom == null) {
            System.out.println("You can't go that way!");
        }
        else if(nextRoom.isLocked())
        {
            for(Item searchItem : playerInventory)
            {
                if(searchItem.getName().equals("key"))
                {
                    playerKey = searchItem;
                }
            }
            if(playerKey != null)
            {
                player.dropItem(playerKey);
                player.setRoom(nextRoom);
                System.out.println("You used a key to unlock the door. I wonder" +
                    " why it was a single-use key?");
                System.out.println(player.getLocation().getLongDescription());
            }
            else
            {
                System.out.println("The door is locked!");
            }
        }
        else {
            if(nextRoom.isTrapped() && !player.listItems().contains("feather"))
            {   
                nextRoom = player.getLocation().getExit("hidden");
                System.out.println("Oh no! You fell down a trap door!");
            }
            player.setRoom(nextRoom);
            System.out.println(player.getLocation().getLongDescription());
        }
    }
    
    /**
     * Looks around your current location to see all exits, NPCs, and items.
     */
    private void lookAround()
    {
        System.out.println(player.getLocation().getLongDescription());
    }

    /**
     * Picks up an item from the current room and adds it to the player's inventory,
     * printing an error message if the item isn't in the room.
     * @param command The processed command containing the name of the item.
     */
    private void pickUpItem(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Take what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        ArrayList<Item> roomInventory = player.getLocation().getItems();
        Item takenItem = null;
        
        if(!player.getLocation().listItems().contains(itemName))
        {
            System.out.println("That isn't in the room!");
        }
        else
        {
            for(Item roomItem : roomInventory)
            {   
                if(roomItem.getName().equals(itemName))
                {
                    if(roomItem.getWeight() + player.getWeightCarried() > 
                        player.getWeightLimit())
                    {
                        System.out.println("You can't carry that much weight!");
                    }
                    else 
                    {
                        player.takeItem(roomItem);
                        takenItem = roomItem;
                        System.out.println("You took the " + itemName);
                    }
                }
            }
            if(takenItem != null)
            {
                roomInventory.remove(takenItem);
            }
        }
    }
    
    /**
     * Puts down an item from the player's inventory, adding it to the room. If the
     * player doesn't have the specified item, an error message indicates that.
     * @param command The processed command containing the name of the item to be
     * dropped.
     */
    private void putDownItem(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Take what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        ArrayList<Item> playerInventory = player.getInventory();
        Item droppedItem = null;
        if(!player.listItems().contains(itemName))
        {
            System.out.println("You don't have that!");
        }
        else
        {
        for(Item playerItem : playerInventory)
            {
                if(playerItem.getName().equals(itemName))
                {
                    player.getLocation().addItem(playerItem);
                    droppedItem = playerItem;
                    System.out.println("You dropped the " + itemName);
                }
            }
        }
        if(droppedItem != null)
        {
            playerInventory.remove(droppedItem);
        }
    }
    
    private void useItem(Command command, Character player, Room currentRoom)
    {
        String itemName = command.getSecondWord();
        ArrayList<Item> playerInventory = player.getInventory();
        Item useItem = null;
        if(!command.hasSecondWord())
        {
            System.out.println("Use what?");
        }
        else
        {
            for(Item item : playerInventory)
            {
                if(item.getName().equals(itemName))
                {
                    useItem = item;
                }
            }
            if(useItem != null)
            {
                useItem.use(itemName, player, player.getLocation());
            }
            else
            {
                System.out.println("You don't have that!");
            }
        }
    }
    
    private void getInventory()
    {
        System.out.println(player.listItems());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}