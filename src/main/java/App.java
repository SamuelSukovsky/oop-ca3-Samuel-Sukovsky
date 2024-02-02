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
        // parkingLot();
        // floodFill();
        // identifierCount("source.java");
        // System.out.println(nestedTags("nestedTags.txt"));
        airportFlights();
        // stack3();
    }

    public void parkingLot()
    {
        Scanner keyboard = new Scanner(System.in);
        Deque<Integer> parkingLot = new ArrayDeque<>();
        Deque<Integer> street = new ArrayDeque<>();
        int action = 1;

        while (action != 0)
        {
            System.out.print("Enter car number: ");
            action = keyboard.nextInt();

            if (action > 0)
            {
                parkingLot.push(action);
                System.out.println(street.toString());
                System.out.println(parkingLot.toString());
                System.out.println();
            }
            else if (action < 0 && parkingLot.contains(-action))
            {
                while (parkingLot.peek() != -action && !parkingLot.isEmpty())
                {
                    street.push(parkingLot.pop());
                    System.out.println(street.toString());
                    System.out.println(parkingLot.toString());
                    System.out.println();
                }
                parkingLot.pop();
                System.out.println(street.toString());
                System.out.println(parkingLot.toString());
                System.out.println();
                while (!street.isEmpty())
                {
                    parkingLot.push(street.pop());
                    System.out.println(street.toString());
                    System.out.println(parkingLot.toString());
                    System.out.println();
                }
            }
        }
    }

    public void floodFill()
    {
        Scanner keyboard = new Scanner(System.in);
        Deque<Coordinates> coordinates = new ArrayDeque<>();

        int[][] map = new int[10][10];

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Enter x and y coordinates: ");
        coordinates.push(new Coordinates(keyboard.nextInt(), keyboard.nextInt()));

        int order = 1;
        while (!coordinates.isEmpty())
        {
            Coordinates a = coordinates.pop();
            if(map[a.x][a.y] == 0)
            {
                map[a.x][a.y] = order;
                order++;

                if (a.y > 0)
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

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void identifierCount(String path) throws FileNotFoundException
    {
        Map<String, HashSet<Integer>> map = new TreeMap<>();
        File file = new File(path);
        Scanner input = new Scanner(file);
        int line = 1;
        String token;

        while (input.hasNextLine())
        {
            Scanner in = new Scanner(input.nextLine()).useDelimiter("[^A-Za-z0-9_]+");
            while(in.hasNext())
            {
                token = in.next();
                if (map.containsKey(token))
                {
                    map.get(token).add(line);
                }
                else
                {
                    HashSet<Integer> set = new HashSet<>();
                    set.add(line);
                    map.put(token, set);
                }
            }
            line++;
        }
        for (String a : map.keySet())
        {
            System.out.println(a + ": " + map.get(a));
        }
    }

    public boolean nestedTags(String path) throws FileNotFoundException
    {
        File file = new File(path);
        Scanner input = new Scanner(file);
        Deque<String> tags = new ArrayDeque<>();
        Deque<String> closingTags = new ArrayDeque<>();
        String tag;

        while (input.hasNextLine())
        {
            while (input.hasNext())
            {
                tag = input.next();
                tags.push(tag.substring(1, tag.length() - 1));
            }
        }

        while(!tags.isEmpty())
        {
            tag = tags.pop();
            if (tag.charAt(0) == '/')
            {
                closingTags.push(tag);
            }
            else if (closingTags.isEmpty() || !tag.equals(closingTags.pop().substring(1)))
            {
                return false;
            }
        }
        return true;
    }

    public void airportFlights()
    {
        Scanner keyboard = new Scanner(System.in);
        Deque<String> takeoff = new ArrayDeque<>();
        Deque<String> landing = new ArrayDeque<>();
        String input;
        String flight = "";

        do
        {
            System.out.println("Takeoff: " + takeoff);
            System.out.println("Landing: " + landing);
            System.out.print("Enter command: ");
            input = keyboard.nextLine();
            if (input.contains(" "))
            {
                flight = input.substring(input.indexOf(" ") + 1);
                input = input.substring(0, input.indexOf(" "));
            }
            switch (input)
            {
                case "takeoff" ->
                {
                    takeoff.add(flight);
                }
                case "land" ->
                {
                    landing.add(flight);
                }
                case "next" ->
                {
                    if(!landing.isEmpty())
                    {
                        System.out.println(landing.pop());
                    }
                    else if (!takeoff.isEmpty())
                    {
                        System.out.println(takeoff.pop());
                    }
                    else
                    {
                        System.out.println("No inbound or outbound flights.");
                    }
                }
            }
            System.out.println();
        } while (!input.equals("quit"));
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