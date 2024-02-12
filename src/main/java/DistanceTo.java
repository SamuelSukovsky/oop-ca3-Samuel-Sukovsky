public class DistanceTo implements Comparable<DistanceTo>
{                                           // part of q10
    private String target;                      // values
    private int distance;
    public DistanceTo(String city, int dist)    // constructor method
    {
        target = city;
        distance = dist;
    }
    public String getTarget()                   // getter methods
    {
        return target;
    }
    public int getDistance()
    {
        return distance;
    }
    public int compareTo(DistanceTo other)      // compare to method
    {
        return distance - other.distance;           // subtract the other's distance from this path's distance
    }
}
