public class Block                      // part of q6 and q7
{
    int quantity;                           // internal values
    float value;

    public Block(int quantity, float value) // constructor method
    {
        this.quantity = quantity;               // set all variables
        this.value = value;
    }

    public int sell (int quantity)          // sell method
    {
        this.quantity -= quantity;              // subtract the quantity being sold from the available quantity
        return this.quantity;                   // return the remainder
    }
}