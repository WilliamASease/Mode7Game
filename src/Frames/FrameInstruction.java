package Frames;

import Central.Data;

public abstract class FrameInstruction 
{
	Data db;
	public FrameInstruction(Data db) { this.db = db; }
	public abstract void render();
}
