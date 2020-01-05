package Entities.Enemies;
//import Central.Data;
import Entities.Entity;

public class Enemy extends Entity
{	
	
	//public Enemy(Data db) { sprite = db.mcmb.grab(name);}
	public Enemy() {}
	
	public Enemy copy()
	{
		Enemy out = new Enemy();
		out.hp = new int[] {hp[0]};
		out.mp = new int[] {mp[0]};
		out.atk = new int[] {atk[0]};
		out.def = new int[] {def[0]};
		out.mag = new int[] {mag[0]};
		out.name = name;
		out.lvl = lvl;
		out.availableSpells = availableSpells;
		out.sprite = sprite;
		out.action = "Actions not implemented for enemies.";
		out.result = "Results not implemented for enemies.";
		return out;
	}
}
