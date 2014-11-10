import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.graphics.GTurtle;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class LaggyTurtle extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 500;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LaggyTurtle().start();
	}
	
	GTurtle mrglitch;
	ArrayList<GRect> seaweed = new ArrayList<GRect>();
	double lvy;
	double lacc;
	int rectWidth = 50;
	RandomGenerator rg = new RandomGenerator();
	double rx;
	double rvx;
	int gap = 150;
	private int addscore = 1;
	private GLabel messageLabel;
	private GLabel scoreLabel;
	private GLabel label;
	Color c = rg.nextColor();
	Color set4 = new Color(9,130,210);
	Font start = new Font("Comic Sans", Font.BOLD, 15);
	Font end = new Font("Comic Sans", Font.BOLD, 50);
	
	public void run()
	{
		addMouseListeners();
		//Place mrglitch in center of the screen
		setup();
		
		messageLabel = new GLabel("Click to start", 150, 400);
		messageLabel.setFont(start);
		messageLabel.setColor(Color.white);
		add(messageLabel);
		waitForClick();
		remove(messageLabel);
		
		scoreLabel = new GLabel("Score: " + addscore, 10, 15); 
		scoreLabel.setFont(start);
		scoreLabel.setColor(Color.black);
		add(scoreLabel);
		
		while(true)
		{
			mrglitch.move(0, lvy);
			if(mrglitch.getY() < 0)
			{
				mrglitch.setLocation(mrglitch.getX(),1);
			}
			
			lvy = lvy + lacc;
			System.out.println(mrglitch.getY());
			pause(1);
			for(int i = 0; i < seaweed.size(); i++)
			{
				seaweed.get(i). move(rvx,0);
			}
			if(seaweed.get(0).getX() < -100)
			{
				addscore += 1;
				remove(seaweed.remove(0));
				remove(seaweed.remove(0));
				addNewPipes();
			}
			if(hasLost())
			{
				GLabel label = new GLabel("You lose... :(", 100, 200);
				scoreLabel.setFont(start);
				removeAll();
				add(label);
				add(scoreLabel);
				waitForClick();
				removeAll();
				for(int i = 0; i < 6; i++)
				{
				try
				{
					remove(seaweed.remove(0));
				}
				catch(Exception e)
				{}
				}
				addscore = 1;
				run();
			}
			scoreLabel.setLabel("Score: " + addscore);
			add(scoreLabel);
		}
		
	}
	
	private boolean hasLost()
	{
		if(mrglitch.getY() > 500)
		{
			return true;
		}
		if(getElementAt(mrglitch.getX(), mrglitch.getY()) == null)
		{
			return false;
		}
		return true;
	}
	
	private void addNewPipes()
	{
		rx = 500;
		int rh = rg.nextInt(100,300);
		GRect bottom = new GRect(rx, getHeight() - rh, rectWidth, rh);
		GRect top = new GRect(rx, 0, rectWidth, getHeight() - rh - gap);
		bottom.setFilled(true);
		bottom.setColor(Color.green);
		top.setFilled(true);
		top.setColor(Color.green);
		add(top);
		add(bottom);
		seaweed.add(4, bottom);
		seaweed.add(5,top);
		
	}
	
	private void setup(){
	
		setBackground(set4);
		
		mrglitch = new GTurtle(getWidth() / 2, getHeight() / 2);
		add(mrglitch);
		lvy = -30;
		lacc = 6;
		rvx = -20;
		
		for(int i = 0; i < 3; i++)
		{
			rx = 500 + 200 * i;
			int rh = rg.nextInt(100,300);
			GRect bottom = new GRect(rx, getHeight() - rh, rectWidth, rh);
			GRect top = new GRect(rx, 0, rectWidth, getHeight() -rh - gap);
			bottom.setFilled(true);
			bottom.setColor(Color.green);
			top.setFilled(true);
			top.setColor(Color.green);
			add(top);
			add(bottom);
			seaweed.add(bottom);
			seaweed.add(top);
		}
	}
	public void mousePressed(MouseEvent me)
	{
		lvy = -30;
	}
}
