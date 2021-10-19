import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Snake extends JComponent{
	
	private int[][] snake;
	private static String direction;
	private static String direction2;
	private LinkedList<Coordinate> body;
	private Timer t;
	private static boolean go = false;
	private Coordinate apple;
	private int growth = 5;
	
	private ArrayList<Coordinate> j = new ArrayList<>();
	
	Snake()
	{
		initialize();
	}
	
	public void initialize()
	{
		setFocusable(true);
		addKeyListener(new Key());
		snake = new int[40][40];
		
		body = new LinkedList<>();
		body.add(new Coordinate(15, 19, true));
		body.add(new Coordinate(14, 19));
		body.add(new Coordinate(13, 19));
		body.add(new Coordinate(12, 19));
		body.add(new Coordinate(11, 19));
		snake[25][19] = -1;
		apple = new Coordinate(25, 19);
		
		bodyToBoard();
		
		
		direction = "right";
		direction2 = "right";
		
		t = new Timer(70, (ActionEvent e) -> {
			if(go)
				update();
		});
		t.start();
	}
	
	public void update()
	{
		direction = direction2;
		move();
		hit();
		if(j.size() > 0)
		{
			body.add(j.remove(0));
		}
		repaint();
	}
	
	public void hit()
	{
		for(int i = 0; i < body.size(); i++)
		{
			for(int j = i + 1; j < body.size(); j++)
			{
				if(body.get(i).equals(body.get(j)))
				{
					die();
				}
			}
		}
	}
	
	public void move()
	{
		if(direction.equals("up"))
			moveUp();
		if(direction.equals("left"))
			moveLeft();
		if(direction.equals("down"))
			moveDown();
		if(direction.equals("right"))
			moveRight();
		if(snake[apple.getX()][apple.getY()] > 0)
		{
			eat();
		}
	}
	
	public void moveRight()
	{
		try
		{
			body.add(1, new Coordinate(body.get(0).getX(), body.get(0).getY()));
			body.remove(body.size()-1);
			body.set(0, new Coordinate(body.get(0).getX() + 1, body.get(0).getY()));
		}
		catch(Exception e)
		{
			die();
		}
		bodyToBoard();
	}
	
	public void moveDown()
	{
		try
		{
			body.add(1, new Coordinate(body.get(0).getX(), body.get(0).getY()));
			body.remove(body.size()-1);
			body.set(0, new Coordinate(body.get(0).getX(), body.get(0).getY() + 1));
		}
		catch(Exception e)
		{
			die();
		}
		bodyToBoard();
	}
	
	public void moveLeft()
	{
		try
		{
			body.add(1, new Coordinate(body.get(0).getX(), body.get(0).getY()));
			body.remove(body.size()-1);
			body.set(0, new Coordinate(body.get(0).getX() - 1, body.get(0).getY()));
		}
		catch(Exception e)
		{
			die();
		}
		bodyToBoard();
	}
	
	public void moveUp()
	{
		try
		{
			body.add(1, new Coordinate(body.get(0).getX(), body.get(0).getY()));
			body.remove(body.size()-1);
			body.set(0, new Coordinate(body.get(0).getX(), body.get(0).getY() - 1));
		}
		catch(Exception e)
		{
			die();
		}
		bodyToBoard();
	}
	
	public void eat()
	{
		increaseLength();
		int x = -1;
		int y = -1;
		boolean go = true;
		while(go)
		{
			x = (int)(Math.random()*40);
			y = (int)(Math.random()*39);
			
			go = false;
			boolean go2 = false;
			for(int i = 0; i < body.size(); i++)
			{
				if(body.get(i).equals(new Coordinate(x, y)))
				{
					go2 = true;
				}
			}
			if(go2)
				go = true;
		}
		apple = new Coordinate(x, y);
		snake[x][y] = -1;
		System.out.println(body.size());
	}
	
	public void increaseLength()
	{
		
		int lastX = body.get(body.size()-1).getX();
		int secondX = body.get(body.size()-2).getX();
		int lastY = body.get(body.size()-1).getY();
		int secondY = body.get(body.size()-2).getY();
		
		if(secondX > lastX)
		{
			for(int i = 0; i < growth; i++)
			j.add(new Coordinate(body.get(body.size()-1).getX()-1, body.get(body.size()-1).getY()));
			
			

			
		}
		else if(secondX < lastX)
		{
			//System.out.println("TWO");
			for(int i = 0; i < growth; i++)
			j.add(new Coordinate(body.get(body.size()-1).getX()+1, body.get(body.size()-1).getY()));
			
			
		}
		else if(lastY > secondY)
		{
			for(int i = 0; i < growth; i++)
			j.add(new Coordinate(body.get(body.size()-1).getX(), body.get(body.size()-1).getY()+1));
			
			

		}
		else if(lastY < secondY)
		{
			//System.out.println("FOUR");
			for(int i = 0; i < growth; i++)
			j.add(new Coordinate(body.get(body.size()-1).getX(), body.get(body.size()-1).getY()-1));
			
			

		}
		
			//System.out.println("nom");
		

	}
	
	public void die()
	{
		System.out.println(body.size() + "\n----------------");
		t.stop();
		go = false;
		initialize();
	}
	
	public void paintComponent(Graphics g)
	{
		g.fillRect(0,0,800,800);
		g.setColor(Color.DARK_GRAY);
		for(int x = 0; x < 800; x+= 20)
		{
			for(int y = 0; y < 800; y += 20)
			{
				//g.drawRect(x, y, 20, 20);
			}
		}
		
		
		for(int i = 0; i < snake.length; i++)
		{
			for(int j = 0; j < snake[0].length; j++)
			{
				if(snake[i][j] > 0)
				{
					g.setColor(Color.GREEN);
					g.fillRect(i * 20, j * 20, 20, 20);
				}
				else if(snake[i][j] < 0)
				{
					g.setColor(Color.RED);
					g.fillOval(i * 20, j * 20, 20, 20);
				}
			}
		}
	}
	
	public void bodyToBoard()
	{
		try
		{
			for(int i = 0; i < snake.length; i++)
			{
				for(int j = 0; j < snake[0].length; j++)
				{
					if(snake[i][j] > 0)
						snake[i][j] = 0;
				}
			}
			for(Coordinate c : body)
			{
				if(c.getHead())
				{
					snake[c.getX()][c.getY()] = 2;
				}
				else
				{
					snake[c.getX()][c.getY()] = 1;
				}
			}
		}
		catch(Exception e)
		{
			die();
		}
	}
	

	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake");
		frame.setSize(815, 835);
		
		Snake s = new Snake();
		s.setPreferredSize(new Dimension(800, 860));
		frame.add(s);
		
		frame.setVisible(true);

	}
	
	static class Key implements KeyListener
	{


		public void keyTyped(KeyEvent event) {
			String key = KeyStroke.getKeyStrokeForEvent(event).toString();
			//key = key.replace("pressed ", "");
			go = true;
			if(key.equals("typed w"))
			{
				if(!direction.equals("down"))
				{
					direction2 = "up";
				}
			}
			if(key.equals("typed a"))
			{
				if(!direction.equals("right"))
				{
					direction2 = "left";
				}
			}
			if(key.equals("typed s"))
			{
				if(!direction.equals("up"))
				{
					direction2 = "down";
				}
			}
			if(key.equals("typed d"))
			{
				if(!direction.equals("left"))
				{
					direction2 = "right";
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}

}
