package Entities;
import java.util.ArrayList;

import Macros.SpellBook;
import Objects.ColorMap;
import Ticks.BattleTick;

public abstract class Entity 
{
	public int lvl = 0;
	public int xp = 0;
	public int[] hp = new int[1]; public int getHP() { return hp[lvl]; } public void heal(int hpt) { hp[lvl]+= hpt; } public void hurt(int hpt) { hp[lvl] -= hpt; }
	public int[] mp = new int[1]; public int getMP() { return mp[lvl]; } public void fill(int mpt) { mp[lvl]+= mpt; } public void drain(int mpt) { mp[lvl] -= mpt; }
	public int[] atk = new int[1]; public int getATK() { return atk[lvl]; }
	public int[] def = new int[1]; public int getDEF() { return def[lvl]; }
	public int[] mag = new int[1]; public int getMAG() { return mag[lvl]; }
	public int[] availableSpells;
	public int activeSpell;
	public String verb = "YOU FORGOT THE VERB";
	public String action = "YOU FORGOT THE ACTION";
	public String result =  "YOU FORGOT THE RESULT";
	public String name = "YOU FORGOT THE NAME";
	public ColorMap sprite;
	
	public void simulateTurn(Entity cstr, ArrayList<Entity> targets)
	{
		cstr.cast(targets.get(0), SpellBook.UNARMED);
	}
	
	public void cast(Entity tgt, int spell)
	{
		action = "";
		result = "";
		ArrayList<Entity> all = BattleTick.getInstance().all;
		ArrayList<Integer> prevHP = new ArrayList<Integer>(all.size());
		for (int i = 0; i < all.size(); i++) prevHP.add(all.get(i).getHP());
		switch (spell)
		{
			case SpellBook.UNARMED: unarmed(tgt); break;
			case SpellBook.SMALLHEAL: smallheal(tgt); break;
			default:
				System.err.println("Cast fell through to default!!!! tgt = " + tgt.name + " spell = " + spell);
		}
		action = name + " " + verb + " " + tgt.name + "!";
		for (int i = 0; i < all.size(); i++) checkDamage(all.get(i), prevHP.get(i));
		for (int i = 0; i < all.size(); i++) checkKill(all.get(i));
	}
	
	public boolean payXP(int XP)
	{
		xp += XP;
		return false;
	}
	
	public void checkDamage(Entity man, int prev)
	{
		if (prev > man.getHP()) result += man.name + " lost " + (prev - man.getHP()) + "HP!" + " ";
		if (prev < man.getHP()) result += man.name + " gained " + (man.getHP() - prev) + "HP!" + " ";
		
	}
	
	public void checkKill(Entity tgt)
	{
		if (tgt.getHP() > 0) return;
		result += " " + tgt.name + " died!";
		BattleTick cur = BattleTick.getInstance();
		cur.kill(tgt);
	}
	
	public void unarmed(Entity tgt)
	{
		verb = "punched";
		tgt.hurt(getATK());
	}
	
	public void smallheal(Entity tgt)
	{
		verb = "healed";
		tgt.heal(getMAG());
		drain(2);
	}
}
