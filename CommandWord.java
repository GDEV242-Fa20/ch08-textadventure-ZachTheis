/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Zach Theis
 * @version 2020.10.25
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), TAKE("take"), DROP("drop"),
    USE("use"), TALK("talk"), INVENTORY("inventory"), UNKNOWN("?");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
