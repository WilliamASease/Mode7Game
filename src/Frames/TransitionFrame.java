package Frames;

import java.awt.Color;
import java.awt.Graphics;

import Central.Data;

public class TransitionFrame extends FrameInstruction{

	public TransitionFrame(Data db) {super(db);}


	public void render() 
	{
		if (db.transition.ti.tickSpeed == 0) slow(db.mainPanel.getGraphics());
		if (db.transition.ti.tickSpeed == 1) med(db.mainPanel.getGraphics());
		if (db.transition.ti.tickSpeed == 2) fast(db.mainPanel.getGraphics());
	}
	
	public void slow(Graphics g)
	{
		Color c = new Color(db.dice.nextInt());
		db.sprts.rect(db.transition.ti.tickCount * 4, 0, 2, 450, c);
		db.sprts.rect(598 - db.transition.ti.tickCount * 4, 0, 2, 450, c);
	}
	
	public void med(Graphics g)
	{
		Color c = new Color(db.dice.nextInt());
		db.sprts.rect(db.transition.ti.tickCount * 10, 0, 10, 450, c);
	}
	
	public void fast(Graphics g)
	{
		Color c = new Color(db.dice.nextInt());
		db.sprts.rect(db.transition.ti.tickCount * 100, 0, 100, 450, c);
	}

}
