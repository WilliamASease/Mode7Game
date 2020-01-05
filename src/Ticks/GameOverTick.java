package Ticks;

import Central.Data;

public class GameOverTick extends TickInstruction
{

	public GameOverTick(Data db) {super(db);}
	public int rot = 1;
	public int expand = 0;
	public boolean done = false;

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if (rot + 700 < db.sin.length)
		{
			rot+= 700;
			expand+= 1;
		}
		else endit();
	}

	public void begin(String data) {	}
	
	public void endit()
	{ done = true; }

}
