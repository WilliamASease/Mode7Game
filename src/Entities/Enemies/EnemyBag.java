package Entities.Enemies;

import java.io.File;
import java.util.ArrayList;

import Central.Data;
import DevTools.DataReader;

public class EnemyBag 
{
	ArrayList<Enemy> bag;
	Data db;
	public EnemyBag(Data db)
	{
		this.db = db;
		bag = new ArrayList<Enemy>();
		File[] enData = new File("./data/enemies").listFiles();
		for (int i = 0; i < enData.length; i++)
		{
			Enemy temp = new Enemy();
			String loc = enData[i].getName().substring(0, enData[i].getName().length() - 4);
			String[] raw = DataReader.readRawText("enemies/" + loc);
			String[] Line = raw[0].split(" ");
			temp.hp[0] = Integer.parseInt(Line[0]);
			temp.mp[0] = Integer.parseInt(Line[1]);
			temp.atk[0] = Integer.parseInt(Line[2]);
			temp.def[0] = Integer.parseInt(Line[3]);
			temp.mag[0] = Integer.parseInt(Line[4]);
			temp.xp = Integer.parseInt(Line[5]);
			temp.name = raw[1];
			String[] spellLine = raw[2].split(" ");
			int[] avSpells = new int[spellLine.length];
			for (int j = 0; j < avSpells.length; j++) avSpells[j] = Integer.parseInt(spellLine[j]);
			temp.availableSpells = avSpells;
			temp.sprite =  db.mcmb.grab(temp.name);
			bag.add(temp);
		}
	}
	
	public Enemy fetch(int lvlbnd)
	{
		Enemy t = bag.get(db.dice.nextInt(bag.size()));
		while (t.lvl > lvlbnd) t = bag.get(db.dice.nextInt(bag.size()));
		return t.copy();
	}
	
	public Enemy fetch(String specific)
	{
		for (int i = 0; i < bag.size(); i++) if (bag.get(i).name.equals(specific)) return bag.get(i).copy();
		return null;
	}
}
