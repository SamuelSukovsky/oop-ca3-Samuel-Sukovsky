/**                                                 Nov 2023
 * A Stack is a LIFO Queue (Last-In First-Out)
 * It operates like a stack of plates - add at top and remove from top.
 * In this sample we use a Stack that uses underlying ArrayDeque.
 * ("Deque" stands for Double-Ended Queue)
 * We use a reference of Interface type Deque ('deck').
 *
 * (Use of the Java Stack class is no longer recommended, so, although
 * it is easier to read the code, we will not use it).
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;

public class App
{
    public static void main(String[] args) throws FileNotFoundException
    {
        App app = new App();
        app.start();
    }

    public void start() throws FileNotFoundException
    {
        // parkingLot();                                        // question 1
        // floodFill();                                         // question 2
        // identifierCount("source.java");                      // question 3
        // System.out.println(nestedTags("nestedTags.txt"));    // question 4
        // airportFlights;                                      // question 5
        // sharesTaxCalculation();                              // question 6
        //
    }

    public void parkingLot()                        // question 1
    {
        Scanner keyboard = new Scanner(System.in);      // get keyboard input
        Deque<Integer> parkingLot = new ArrayDeque<>(); // array decks
        Deque<Integer> street = new ArrayDeque<>();
        int action = 1;                                 // vehicle id variable

        while (action != 0)                             // while input != 0
        {
            System.out.print("Enter car number: ");
            action = keyboard.nextInt();                    // get input

            if (action > 0)                                 // if adding a car
            {
                parkingLot.push(action);                        // add the car
                System.out.println(street.toString());          // print parking lot and street
                System.out.println(parkingLot.toString());
                System.out.println();
            }                                               // else if removing a car that is parked
            else if (action < 0 && parkingLot.contains(-action))
            {                                                   // while the next car in line isn't the requested car
                while (parkingLot.peek() != -action && !parkingLot.isEmpty())
                {
                    street.push(parkingLot.pop());                  // move the car to the street
                    System.out.println(street.toString());          // print parking lot and street
                    System.out.println(parkingLot.toString());
                    System.out.println();
                }                                               // afterwards (once the next car is the requested one)
                parkingLot.pop();                               // remove the car
                System.out.println(street.toString());          // print parking lot and street
                System.out.println(parkingLot.toString());
                System.out.println();
                while (!street.isEmpty())                       // until the street is empty
                {
                    parkingLot.push(street.pop());                  // park the next car back
                    System.out.println(street.toString());          // print the parking lot and the street
                    System.out.println(parkingLot.toString());
                    System.out.println();
                }
            }
        }
    }

    public void floodFill()                         // question 2
    {
        Scanner keyboard = new Scanner(System.in);      // get keyboard input
        Deque<Coordinates> coordinates = new ArrayDeque<>();
                                                        // create a coordinate stack
        int[][] map = new int[10][10];                  // create a 10*10 map of 0s

        for (int i = 0; i < 10; i++)                    // print the map
        {
            for (int j = 0; j < 10; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
                                                        // get the starting coordinates
        System.out.println("Enter x and y coordinates: ");
        coordinates.push(new Coordinates(keyboard.nextInt(), keyboard.nextInt()));
                                                        // put them on the stack
        int order = 1;                                  // variable to count the placement order
        while (!coordinates.isEmpty())                  // while there are coordinates to place
        {
            Coordinates a = coordinates.pop();              // get the next coordinate
            if(map[a.x][a.y] == 0)                          // if the coordinates cell is empty
            {
                map[a.x][a.y] = order;                          // fill it
                order++;                                        // increment the tile count

                if (a.y > 0)                                    // put all empty adjecent tiles on the stack
                {
                    if (map[a.x][a.y - 1] == 0)
                    {
                        coordinates.push(new Coordinates(a.x, a.y - 1));
                    }
                }
                if (a.y < 9)
                {
                    if(map[a.x][a.y + 1] == 0)
                    {
                        coordinates.push(new Coordinates(a.x, a.y + 1));
                    }
                }
                if (a.x > 0)
                {
                    if(map[a.x - 1][a.y] == 0)
                    {
                        coordinates.push(new Coordinates(a.x - 1, a.y));
                    }
                }
                if (a.x < 9)
                {
                    if(map[a.x + 1][a.y] == 0)
                    {
                        coordinates.push(new Coordinates(a.x + 1, a.y));
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++)                    // print the map
        {
            for (int j = 0; j < 10; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
                                                    // question 3
    public void identifierCount(String path) throws FileNotFoundException
    {                                                   // get a hashmap
        Map<String, HashSet<Integer>> map = new TreeMap<>();
        File file = new File(path);                     // get the source file
        Scanner input = new Scanner(file);              // read the file
        int line = 1;                                   // line countdown
        String token;

        while (input.hasNextLine())                     // while there are lines in the source
        {                                                   // read the line with a delimiter
            Scanner in = new Scanner(input.nextLine()).useDelimiter("[^A-Za-z0-9_]+");
            while(in.hasNext())                             // while the line has tokens
            {
                token = in.next();                              // read the next token
                if (map.containsKey(token))                     // if the map already has that token
                {
                    map.get(token).add(line);                       // add the line index to it
                }
                else                                            // otherwise
                {
                    HashSet<Integer> set = new HashSet<>();         // create a new hashset
                    set.add(line);                                  // add the line index
                    map.put(token, set);                            // store the token in the map
                }
            }
            line++;                                         // increment line count
        }
        for (String a : map.keySet())                   // for each token
        {
            System.out.println(a + ": " + map.get(a));      // print the token and its indexes
        }
    }
                                                    // question 4
    public boolean nestedTags(String path) throws FileNotFoundException
    {
        File file = new File(path);                     // get source file
        Scanner input = new Scanner(file);              // read the file
        Deque<String> tags = new ArrayDeque<>();        // create tags stack
        Deque<String> closingTags = new ArrayDeque<>(); // create closing tags stack
        String tag;                                     // temporary tag storage

        while (input.hasNext())                         // while the file has text
        {
            tag = input.next();                             // get the next tag
            tags.push(tag.substring(1, tag.length() - 1));  // store it without brackets
        }

        while(!tags.isEmpty())                          // while there are tags to be processed
        {
            tag = tags.pop();                               // take the next tag
            if (tag.charAt(0) == '/')                       // if it's a closing tag
            {
                closingTags.push(tag);                          // put it into closing tags
            }                                               // othewise if it doesn't have a matching closing tag
            else if (closingTags.isEmpty() || !tag.equals(closingTags.pop().substring(1)))
            {
                return false;                                   // return false
            }
        }
        return true;                                    // return true
    }

    public void airportFlights()                    // question 5
    {
        Scanner keyboard = new Scanner(System.in);      // get keyboard input
        Deque<String> takeoff = new ArrayDeque<>();     // create inbound and outbound queues
        Deque<String> landing = new ArrayDeque<>();
        String input;
        String flight = "";

        do                                              // do
        {
            System.out.println("Takeoff: " + takeoff);      // print out both queues and ask for input
            System.out.println("Landing: " + landing);
            System.out.print("Enter command: ");
            input = keyboard.nextLine();                    // get input
            if (input.contains(" "))                        // if the input is multiple words
            {                                                   // split input into command and flight ID
                flight = input.substring(input.indexOf(" ") + 1);
                input = input.substring(0, input.indexOf(" "));
            }
            switch (input)                                  // switch over input
            {
                case "takeoff" ->
                {                                               // case takeoff
                    takeoff.add(flight);                            // add the ID to the takeoff queue
                }
                case "land" ->
                {                                               // case land
                    landing.add(flight);                            // add the ID to the landing queue
                }
                case "next" ->
                {                                               // case next
                    if(!landing.isEmpty())                          // if there are planes to land
                    {
                        System.out.println(landing.pop());              // remove the next plane
                    }
                    else if (!takeoff.isEmpty())                    // else if there are planes to take off
                    {
                        System.out.println(takeoff.pop());              // remove the next plane
                    }
                    else                                            // otherwise
                    {                                                   // print message
                        System.out.println("No inbound or outbound flights.");
                    }
                }
            }
            System.out.println();                           // separate line
        } while (!input.equals("quit"));                // break if the input is quit
    }

    public void sharesTaxCalculation()              // question 6
    {
        Scanner keyboard = new Scanner(System.in);      // get keyboard input
        Deque<Block> shares = new ArrayDeque<>();       // create array deque fo the queue
        boolean run = true;                             // set up variables
        int quantity;
        int remain;
        float value;
        float profit;
        String input = null;
        String action;

        while (run)                                     // while running
        {
            System.out.println("Input command:");           // ask for a command
            action = keyboard.nextLine();                   // store the input as an action
            if (action.contains(" "))                       // if there are multiple words
            {                                                   // split the first word off and store the rest as input
                input = action.substring(action.indexOf(" ") + 1);
                action = action.substring(0, action.indexOf(" "));
            }

            switch (action)                                 // switch using action
            {
                case "quit" ->                                  // case quit;
                {
                    run = false;                                    // end program
                }
                case "buy" ->                                   // case buy;
                {                                                   // get quantity and value from input
                    quantity = Integer.parseInt(input.substring(0, input.indexOf(" ")));
                    value = Float.parseFloat(input.substring(input.indexOf(" ") + 1));
                    shares.add(new Block(quantity, value));         // add the purchase to the queue
                }
                case "sell" ->                                  // case sell;
                {                                                   // get quantity and value from input
                    quantity = Integer.parseInt(input.substring(0, input.indexOf(" ")));
                    value = Float.parseFloat(input.substring(input.indexOf(" ") + 1));
                    profit = 0;                                     // reset profit value
                    while (quantity > 0)                            // until the desired quantity is sold
                    {
                        if (!shares.isEmpty())                          // if there are shares to sell
                        {
                            remain = shares.peek().sell(quantity);          // get the amount of shares left in the block after selling the quantity
                            if (remain > 0)                                 // if some remained
                            {                                                   // calculate profit and add it
                                profit += quantity * value - quantity * shares.peek().value;
                            }
                            else                                            // otherwise
                            {                                                   // calculate profit and add it
                                profit += (quantity + remain) * value - (quantity + remain) * shares.peek().value;
                                shares.pop();                                   // pop the empty block from the stack
                            }
                            quantity = -remain;                             // set the quantity left to the difference
                                                                            // (quantity remains positive if not enough shares were sold
                                                                            // yet, or become 0/negative if enough were sold already)

                            if (quantity <= 0)                              // if enough was sold
                            {                                                   // print how much was sold and the resulting profit
                                System.out.println("Sold " + input.substring(0, input.indexOf(" ")) + " shares for a profit of $" + profit);
                            }
                        }
                        else                                            // if there aren't any shares left to sell
                        {                                                   // calculate and print how many shares were actually sold, and the profit made
                            remain = Integer.parseInt(input.substring(0, input.indexOf(" "))) - quantity;
                            System.out.println("Sold " + remain + " shares for a profit of $" + profit);
                            quantity = 0;                                   // end sell loop
                        }
                    }
                }
            }
        }
    }

    public void stack2()
    {
        /*
        // PUSH (add) elements on to stack - always at the top
        System.out.println("Pushing, A, B, C on to the stack:");
        myStack.push("A");    // top->      A]
        myStack.push("B");    // top->    B|A]
        myStack.push("C");    // top->  C|B|A]

        // PEEK - use myStack.peek() to get a reference to, but not remove, the top element
        String topElement = myStack.peek();
        System.out.println("stack.peek() = " + topElement ); // top->  C|B|A]

        // POP - stack.pop() - return a reference to the top element and remove it from stack
        //                     note that the object itself is not deleted,
        //                     only the reference to it is removed from the stack
        String str = myStack.pop();  // remove from head (top).   stack is now : top->  B|A]
        System.out.println("value popped = " + str);

        // PUSH is used to add an element on to the head/top of the stack
        System.out.println("Pushing W on stack.");
        myStack.push("W");    // top->  W|B|A]

        System.out.println("Popping (removing) all elements: - repeatedly pop() until stack is empty ");
        while (!myStack.isEmpty()) {
            System.out.println(myStack.pop());
        }
        System.out.println("Stack has been emptied.");

        // POPping an element from an empty stack will throw an exception
        // myStack.pop();   // this line causes exception.
        */

        //TODO add code to this starter code as described below.
        // 1. declare a stack to store objects of type String

        Scanner in = new Scanner(System.in);

        String word = "";
        System.out.println("Stack sample stack2().");
        System.out.println("Please enter a list of words, \"q\" to exit");

        while (!word.equals("q"))
        {
            word = in.next();
            if (!word.equals("q"))
            {
                //TODO 2. add the word to stack  (push())
            }
        }

        System.out.println("Your words in reverse order are: ");
        //TODO 3. Write code to retrieve words from the stack - one by one -
        // and display them as they are removed.  (see loop is sample function above)

    }

    public void stack3()
    {
        //TODO Write code that will take a decimal digit (e.g. 6)
        // and will output the binary sequence for that number.
        // A stack will be of use in this case!

        /* Tip: decimal 6 is binary 110
                6 % 2 = 0 <- (the last binary digit) - store it  (% = Modulus operator)
                6 / 2 = 3   (integer division) - get the new value
                3 % 2 = 1 <- (the second-last binary digit) - store it
                3 / 2 = 1   (integer division, remainder is discarded)
                1 % 2 = 1 <- (the next binary digit) - store it
                1 / 2 = 0   (if zero, we are finished)
        */
        System.out.println("Stack sample stack3().");
        int value = 6;

        // Try your program using the number 153.
        // Check that it gives the right binary number

    }

    //TODO  Implement the algorithm to balance brackets as described
    // in the textbook extract in section 15.6.1 (see PDF) in moodle.

}