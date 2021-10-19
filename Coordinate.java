
public class Coordinate {
	private int x;
	private int y;
	private boolean head = false;
	
	Coordinate()
	{
		x = 0;
		y = 0;
	}
	
	Coordinate(int x, int y, boolean head)
	{
		this.x = x;
		this.y = y;
		this.head = head;
	}
	
	Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean getHead()
	{
		return head;
	}
	
	public void setHead(boolean head)
	{
		this.head = head;
	}
	
	public boolean equals(Object obj)
	{
		Coordinate c = (Coordinate) obj;
		if(c.getX() == x && c.getY() == y)
			return true;
		return false;
	}
}
