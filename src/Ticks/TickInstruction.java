package Ticks;
import Central.Data;

public abstract class TickInstruction 
{
	Data db;
	public TickInstruction(Data db) { this.db = db; }
	
	public abstract void tick();
	public void begin(String data) {}
}
