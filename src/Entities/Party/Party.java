package Entities.Party;

import java.util.ArrayList;
import Central.Data;
import Entities.Entity;

public class Party 
{
	public ArrayList<Entity> members = new ArrayList<Entity>();
	public Protag mc;
	Soldier sd;
	Mage mg;
	Thief tf;
	
	public Party(Data db)
	{
		mc = new Protag(db);
		sd = new Soldier(db);
		mg = new Mage(db);
		tf = new Thief(db);
		members.add(mc);
	}
}
