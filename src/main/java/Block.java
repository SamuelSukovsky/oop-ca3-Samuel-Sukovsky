public class Block
{
    int quantity;
    float value;

    public Block(int quantity, float value)
    {
        this.quantity = quantity;
        this.value = value;
    }

    public int sell (int quantity)
    {
        this.quantity -= quantity;
        return this.quantity;
    }
}
