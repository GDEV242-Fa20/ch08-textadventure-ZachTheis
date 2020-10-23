import java.util.ArrayList;
/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{
    // instance variables - replace the example below with your own
    private ArrayList<String> numbers;

    /**
     * Constructor for objects of class test
     */
    public test()
    {
        // initialise instance variables
        numbers.add("index 0");
        numbers.add("index 1");
        numbers.add("index 2");
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String testArray(int index)
    {
        return numbers.get(index);
    }
}
