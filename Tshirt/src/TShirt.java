public class TShirt {
    private String color;
    private Integer size;

    public TShirt(int size, String color)
    {
        this.size = size;
        this.color = color;
    }
    public int getSize()
    {
        return this.size;
    }
    public String getColor()
    {
        return this.color;
    }
    public void dye(String color)
    {
        this.color = color;
        if(this.color.compareTo(""))
    }
}
