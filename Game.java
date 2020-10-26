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
    private ArrayList<Character> nonPCs;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Character();
        nonPCs = new ArrayList<>();
        createGameState();
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
        treasury, bedchamber, tower, dungeon, cellar, wineCellar, larder, cave,
        graveyard;
                    
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
        graveyard = new Room("graveyard", "where I send dead NPCs"); 
        
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
        wineCellar.setExit("dead", graveyard);
        
        larder.setExit("south", wineCellar);
        
        cave.setExit("east", cellar);
        cave.setExit("dead", graveyard);
        
        dungeon.setExit("hidden", outside);
        
        //Declare all items
        Item sword, shield, knife, beast, potion, rose, wine, stake, amulet, 
        treasure, key1, key2, key3, key4;
        
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
        treasure = new Item("treasure", "the friends you made along the way", 0);
        key1 = new Item("key", "a single-use key", 1);
        key2 = new Item("key", "a single-use key", 1);
        key3 = new Item("key", "a single-use key", 1);
        key4 = new Item("key", "a single-use key", 1);
            
        //add items to rooms
        barracks.addItem(key2);
        
        gateHouse.addItem(key1);
        
        kitchen.addItem(knife);
        
        diningHall.addItem(beast);
        diningHall.addItem(potion);
        
        garden.addItem(rose);
        
        chapel.addItem(stake);
        
        tower.addItem(amulet);
        
        bedchamber.addItem(key4);
        
        treasury.addItem(treasure);
        
        larder.addItem(key3);
        
        //Declare all characters
        Character knight, skeleton, ogre, wizard, vampire, priest;
        
        //initialize all characters
        
        knight = new Character(barracks, "knight", "a knight in somewhat less-" +
            "than-shining armor", "Hello there, hero! How brave of you to... brave"
            + " these ruins!\nI am Sir Loin, sent here to recover the kings's lost" +
            "treasure.\nI'm just so hungry though. Do you think you could find" +
            "me something to eat?");
        
        skeleton = new Character(dungeon, "skeleton", "an animate - and very" + 
            "talkative - skeleton", "Ooo.. got caught by the old trap door, huh?" + 
            "Shame. The cell door is locked, and the lock rused shut.\nI could get"
            + " you out, but it'll cost you, let's say...\nsome of your vital " +
            "life energy. What? Guy's gotta eat!\n\nThe skeleton makes an " +
            "arcane gesture and a swirling violet portal opens in the wall." +
            "As you approach it, you feel some of your vitality leave you.");
        
        ogre = new Character(cave, "ogre", "a smelly, brutish ogre.", "Me hate you!"
            + " Me smash you! Me smash you good!");
        
        wizard = new Character(tower, "wizard", "a robed, bearded wizard",
            "Greetings, adventurer. I am Fistandilthianthis the wise!\nI sense" + 
            "that you seek the lost treasure. It is in the treasury, just north" +
            "of the throne room, but you will need a magic ring to enter.\nI" + 
            "can give you an amulet that will allow you to see the ring, but only" 
            + "if you slay the ogre that lurks beneath this castle.\nYou should" +
            "seek out weapons before you face him, as he is quite dangerous." +
            "\nGood luck!");
        
        vampire = new Character(wineCellar, "vampire", "a very stereotypical " +
            "vampire", "Ah! Good evening. I velcome you to my humble abode.\n" +
            "Vhat? Drink your blood? Vhy vould I vish to do such a thing when " +
            "I have so much vine?");
        
        priest = new Character(chapel, "priest", "a holy man, complete with " +
            "mitre and rosary", "Hello, my child. Welcome to the last sanctuary" +
            "within this accursed place.\nSadly, I have nothing I can offer you" +
            " save for a bottle of blessed wine, but it is the last one I " +
            "have.\nIf the vampire in the wine cellar were to be slain, I would" +
            "gladly let you have this bottle.");
        
        //add characters to array
        nonPCs.add(knight);
        nonPCs.add(skeleton);
        nonPCs.add(ogre);
        nonPCs.add(wizard);
        nonPCs.add(vampire);
        nonPCs.add(priest);
        
        //add items to NPC inventories//
        knight.setTrade("steak", sword);
        knight.setTrade("wine", shield);
        wizard.setTrade("head", amulet);
        priest.setTrade("ashes", wine);

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
        printNPCDescription(player.getLocation());
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
                useItem(command, player, player.getLocation(), nonPCs);
                break;
                
            case TALK:
                talkTo(command);
                break;
            
            case TRADE:
                tradeItem(command);
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
        String nonPCDescription = null;
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
                nextRoom.setLocked(false);
                System.out.println("You used a key to unlock the door. I wonder" +
                    " why it was a single-use key?");
                System.out.println(player.getLocation().getLongDescription());
                printNPCDescription(nextRoom);
            }
            else
            {
                System.out.println("The door is locked!");
            }
        }
        else {
            if(nextRoom.isTrapped() && !player.listItems().contains("ring"))
            {   
                nextRoom = player.getLocation().getExit("hidden");
                System.out.println("Oh no! You fell down a trap door!");
            }
            player.setRoom(nextRoom);
            System.out.println(player.getLocation().getLongDescription());
            printNPCDescription(nextRoom);
        }
    }
    
    /**
     * Looks around your current location to see all exits, NPCs, and items.
     */
    private void lookAround()
    {
        System.out.println(player.getLocation().getLongDescription());
        printNPCDescription(player.getLocation());
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
            if(takenItem.getName().equals("treasure"))
            {  
                System.out.println("You have done it!");
                System.out.println("You have found the lost treasure!");
                System.out.println("Surely the bards will sing of your " +
                "adventure for years to come!\n\nThanks for playing!");
                
            }
            else if(takenItem != null)
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
    
    private void useItem(Command command, Character player, Room currentRoom, 
        ArrayList<Character> nonPlayerCharacters)
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
                useItem.use(itemName, player, player.getLocation(), nonPCs);
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
    
    private void talkTo(Command command)
    {
        String talkString = "They aren't here";
        String talkName = command.getSecondWord();
        if(!command.hasSecondWord())
        {
            talkString = "Talk to who?";
        }
        else
        {
            for(Character roomNPC : nonPCs)
            {
                if(roomNPC.getName().equals(talkName) && 
                    roomNPC.getLocation() == player.getLocation())
                {
                    talkString = roomNPC.getDialogue();
                }
            }
        }
        System.out.println(talkString);
    }
    
    public void tradeItem(Command command)
    {
        String itemName = command.getSecondWord();
        Item tradeItem = null;
        Character tradePartner = null;
        ArrayList<Item> playerInventory = player.getInventory();
        for(Character nonPC : nonPCs)
        {
            if(nonPC.getTradeList() != null && 
                nonPC.getTradeList().containsKey(itemName))
                {
                    tradePartner = nonPC;
                }
        }
        if(!command.hasSecondWord())
        {
            System.out.println("Trade what item?");
        }
        else if(tradePartner == null)
        {
            System.out.println("No one here want to trade for that.");
        }
        else
        {
            for(Item item : playerInventory)
            {
                if(item.getName().equals(tradeItem))
                {
                    tradeItem = item;
                }
            }
            if(tradeItem != null)
            {
                System.out.println(tradePartner.getTradeDialogue());
                player.dropItem(tradeItem);
                player.takeItem(tradePartner.getTrade(itemName));
                tradePartner.setDialogue(tradePartner.getTradeDialogue());
            }
        }
        
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
    
    private void printNPCDescription(Room room)
    {
        String nonPCDescription = "There is ";
        boolean alone = true;
        for(Character roomNPC : nonPCs)
        {
            if(roomNPC.getLocation() == room)
            {
                nonPCDescription += roomNPC.getDescription();
                alone = false;
            }
        }
        if(alone)
        {
            System.out.println("You are alone here.");
        }
        else
        {
            System.out.println(nonPCDescription + " in the room.");
        }
    }
}