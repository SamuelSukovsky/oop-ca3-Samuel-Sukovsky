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
        // identifierCount("App.java");                         // question 3
        // System.out.println(nestedTags("nestedTags.txt"));    // question 4
        // airportFlights;                                      // question 5
        // sharesProfitCalculation();                           // question 6
        // multipleCompanyShares();                             // question 7
        // arithmeticCalculator();                              // question 8
         backtrackingMaze("maze.txt");                        // question 9
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

        printMap(map);                                  // print the map
                                                        // get the starting coordinates
        System.out.println("Enter x and y coordinates: ");
        coordinates.push(new Coordinates(keyboard.nextInt(), keyboard.nextInt()));
                                                        // put them on the stack
        int order = 1;                                  // variable to count the placement order
        while (!coordinates.isEmpty())                  // while there are coordinates to place
        {
            Coordinates a = coordinates.pop();              // get the next coordinate
            if(map[a.y][a.x] == 0)                          // if the coordinates cell is empty
            {
                map[a.y][a.x] = order;                          // fill it
                order++;                                        // increment the tile count

                if (a.y > 0)                                    // put all empty adjecent tiles on the stack
                {
                    if (map[a.y - 1][a.x] == 0)
                    {
                        coordinates.push(new Coordinates(a.x, a.y - 1));
                    }
                }
                if (a.y < 9)
                {
                    if(map[a.y + 1][a.x] == 0)
                    {
                        coordinates.push(new Coordinates(a.x, a.y + 1));
                    }
                }
                if (a.x > 0)
                {
                    if(map[a.y][a.x - 1] == 0)
                    {
                        coordinates.push(new Coordinates(a.x - 1, a.y));
                    }
                }
                if (a.x < 9)
                {
                    if(map[a.y][a.x + 1] == 0)
                    {
                        coordinates.push(new Coordinates(a.x + 1, a.y));
                    }
                }
            }
        }

        printMap(map);                                  // print the map
    }

    public void printMap(int[][] map)               // part of q2 and q9
    {
        for (int i = 0; i < map.length; i++)                    // print the map
        {
            for (int j = 0; j < map[0].length; j++)
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

    public void sharesProfitCalculation()           // question 6
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

    public void multipleCompanyShares()             // question 7
    {
        Map<String, Deque<Block>> map = new TreeMap<>();// get a hashmap
        Scanner keyboard = new Scanner(System.in);      // get keyboard input
        boolean run = true;                             // set up variables
        int quantity;
        int remain;
        float value;
        float profit;
        String action;
        String company = null;
        String input = null;

        while (run)                                     // while running
        {
            System.out.println("Input command:");           // ask for a command
            action = keyboard.nextLine();                   // store the input as an action
            if (action.contains(" "))                       // if there are multiple words
            {                                                   // split the first and second words off and store the rest as input
                company = action.substring(action.indexOf(" ") + 1);
                action = action.substring(0, action.indexOf(" "));

                input = company.substring(company.indexOf(" ") + 1);
                company = company.substring(0, company.indexOf(" "));
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
                    if (map.containsKey(company))                   // if the company has shares already
                    {                                                   // add the new shares block to it
                        map.get(company).add(new Block(quantity, value));
                    }
                    else                                            // otherwise
                    {
                        Deque<Block> shares = new ArrayDeque<>();       // create a new arrayDeque
                        shares.add(new Block(quantity, value));         // add the line index
                        map.put(company, shares);                       // store the token in the map
                    }
                }
                case "sell" ->                                  // case sell;
                {                                                   // get quantity and value from input
                    quantity = Integer.parseInt(input.substring(0, input.indexOf(" ")));
                    value = Float.parseFloat(input.substring(input.indexOf(" ") + 1));
                    profit = 0;                                     // reset profit value
                    while (quantity > 0)                            // until the desired quantity is sold
                    {                                                   // if there are shares to sell
                        if (map.containsKey(company) && !map.get(company).isEmpty())
                        {
                            remain = map.get(company).peek().sell(quantity);// get the amount of shares left in the block after selling the quantity
                            if (remain > 0)                                 // if some remained
                            {                                                   // calculate profit and add it
                                profit += quantity * value - quantity * map.get(company).peek().value;
                            }
                            else                                            // otherwise
                            {                                                   // calculate profit and add it
                                profit += (quantity + remain) * value - (quantity + remain) * map.get(company).peek().value;
                                map.get(company).pop();                         // pop the empty block from the stack
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
                            if (map.containsKey(company))                   // if there is an empty entry for the company
                            {
                                map.remove(company);                            // delete it
                            }
                            quantity = 0;                                   // end sell loop
                        }
                    }
                }
            }
        }
    }

    public void arithmeticCalculator()              // question 8
    {
        Deque<Float> values = new ArrayDeque<>();       // create a stack of float values
        Deque<Character> operators = new ArrayDeque<>();// create a stack of operators
        Scanner keyboard = new Scanner(System.in);      // get keyboard input

        System.out.println("Enter an arithmetic operation: ");
                                                        // save input into a string, removing spaces
        String input = keyboard.nextLine().replaceAll(" ", "");
        String num = "0";                               // create number to be input
        String ops = "+-*/()";                          // a list of all operators
        boolean lastNum = false;

        while (input.length() > 0)                      // while the input hasn't been fully processed
        {
            if (!ops.contains(input.substring(0, 1)))       // if the first character of input isn't an operator
            {
                num += input.charAt(0);                         // add the character to the next number
                input = input.substring(1);                     // cut the first character out
                lastNum = true;                                 // the last character processed is a number
            }
            else                                            // otherwise
            {                                                   // if the operator is '(' and the previous character was a number or ')'
                if (input.charAt(0) == '(' && (operators.peek() == ')' || lastNum))
                {
                    operators.push('*');                            // push '*' into operators
                }                                                   // (inputing "2(2)(2)" is handled as 2 * (2) * (2) )
                if (input.charAt(0) == '-' || lastNum)          // if the operator is '-' or there is a number to push
                {
                    values.push(Float.parseFloat(num));             // push the number on the values stack
                    num = "0";                                      // reset the number to 0
                }                                                   // (the default num is 0 in order to resolve negative numbers correctly)
                                                                    // (inputing "-2" is resolved as 0 - 2; = -2)
                                                                // if there are operators on stack and the new operator isn't a bracket
                if (!operators.isEmpty() && operators.peek() != '(')
                {
                    if ("+-".contains(input.substring(0, 1)))       // if the new operator is '+' or '-' (low priority)
                    {
                        resolve(values, operators);                     // resolve the previous operation
                    }                                               // else if the new operator is '*' or '/' (high priority) and the previous operator isn't low priority
                    else if ("*/".contains(input.substring(0, 1)) && !"+-".contains("" + operators.peek()))
                    {
                        resolve(values, operators);                     // resolve the previous operation
                    }
                    else if (input.charAt(0) == ')')                // else if the operator is a closing bracket
                    {
                        while (operators.peek() != '(')                 // continue resolving operations until the opening bracket is reached
                        {
                            resolve(values, operators);
                        }
                        resolve(values, operators);                     // resolve the bracket (removes it)
                    }
                }

                operators.push(input.charAt(0));            // add the new operator on stack
                input = input.substring(1);                 // cut the first character from the input
                lastNum = false;                            // the last character processed wasn't a number
                // System.out.println(values);
                // System.out.println(operators);
            }
        }
        if(lastNum)                                 // if the last character processed was a number
        {
            values.push(Float.parseFloat(num));         // push the final number on the stack
        }

        while (!operators.isEmpty())                // while there are operations left
        {
            resolve(values, operators);                 // resolve operation
        }

        System.out.println(values.pop());           // print the final value
    }

    public void resolve (Deque<Float> values, Deque<Character> operators)
    {                                           // part of q8
        char sign = operators.pop();                // pop the operator from the stack
        if (!"()".contains("" + sign))              // if it isn't brackets
        {
            float b = values.pop();                     // pop the last two values
            float a = values.pop();
            // System.out.println(a + " " + sign + " " + b);
            float calc = switch(sign)                   // perform the calculation
            {
                case '+' -> a + b;
                case '-' -> a - b;
                case '*' -> a * b;
                default -> a / b;
            };
            // System.out.println(calc);
            values.push(calc);                          // push the result on the values stack
        }
        // System.out.println(values);
        // System.out.println(operators);
    }

                                                    // question 9
    public void backtrackingMaze(String path) throws FileNotFoundException
    {
        File file = new File(path);                     // get source file
        Scanner input = new Scanner(file);              // read the file
        Scanner keyboard = new Scanner(System.in);      // get keyboard input
                                                        // create a coordinate stack
        Deque<Coordinates> coordinates = new ArrayDeque<>();

        int[][] map = new int[10][10];                  // create a 10*10 map of 0s

        for (int i = 0; i < map.length; i++)            // take integers from a file and fill the map with them
        {
            Scanner line = new Scanner(input.nextLine());
            for (int j = 0; j < map[0].length; j++)
            {
                map[i][j] = line.nextInt();
            }
        }

        printMap(map);                                  // print the map

        System.out.println("Enter starting x and y coordinates: ");
                                                        // get starting coordinates put them on the stack
        coordinates.push(new Coordinates(keyboard.nextInt(), keyboard.nextInt()));

        while (!coordinates.isEmpty())                  // while there are coordinates to place
        {
            Coordinates a = coordinates.pop();              // get the next coordinate
            if (map[a.y][a.x] == 0)                         // if the coordinates cell is empty
            {
                map[a.y][a.x] = 2;                              // fill it

                if (a.y > 0 && a.y < 9 && a.x > 0 && a.x < 9)   // if the cell isn't on the edge
                {                                                   // put all empty adjecent tiles on the stack
                    if (map[a.y - 1][a.x] == 0)
                    {
                        coordinates.push(new Coordinates(a.x, a.y - 1));
                    }

                    if (map[a.y + 1][a.x] == 0)
                    {
                        coordinates.push(new Coordinates(a.x, a.y + 1));
                    }

                    if (map[a.y][a.x - 1] == 0)
                    {
                        coordinates.push(new Coordinates(a.x - 1, a.y));
                    }

                    if (map[a.y][a.x + 1] == 0)
                    {
                        coordinates.push(new Coordinates(a.x + 1, a.y));
                    }
                }
                else                                            // otherwise
                {
                    coordinates.clear();                            // empty the stack
                    printMap(map);                                  // print the map
                    System.out.println("Found a way out!");         // print success message
                }
            }
        }
    }
}