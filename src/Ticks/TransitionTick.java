package Ticks;

import Central.Data;
import Objects.TFPair;

public class TransitionTick extends TickInstruction
{

	public final int SLOW = 0;
	public final int MED = 1;
	public final int FAST = 2;
	
	public TransitionTick(Data db) { super(db); }
	@SuppressWarnings("rawtypes")
	public TFPair nextPair;
	public int tickCount;
	public int tickSpeed;
	String data;
	public int[] animLengths = new int[] {150, 60, 6};

	public void tick() 
	{
		tickCount++;
		if (tickCount >= animLengths[tickSpeed])
		{
			for(int i = 0; i < db.keys.length; i++) db.keys[i] = false;
			db.pair = nextPair;
			db.pair.ti.begin(data);
			db.pair.ti.tick();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void transitionTo(TFPair next, String data, int type)
	{
		tickCount = 0;
		nextPair = next;
		db.pair = db.transition;
		tickSpeed = type % 3;
		this.data = data;
	}

	@Override
	public void begin(String data) {}
}
