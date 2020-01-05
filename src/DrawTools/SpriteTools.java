package DrawTools;

import java.awt.Color;

import Central.Data;
import Entities.Entity;
import Objects.ColorMap;

public class SpriteTools 
{
	public Data db;
	public SpriteTools(Data db) {this.db = db;}
	
	public void paintPixel(int x, int y, Color c)
	{
		if (x < 0 || x >= 600 || y < 0 || y >= 450) return;
		Color t = db.current[x][y];
		if (t != null && (t == c || t.equals(c))) return;
		db.current[x][y] = c;
		db.ot.put(x, y);
	}
	
	public void placeSprite(int x, int y, ColorMap spr, boolean withNull)
	{
		x -= spr.getLength() / 2;
		y -= spr.getHeight();
		placeSpriteNaive(x, y, spr, withNull);
	}
	
	public void placeSpriteNaive(int x, int y, ColorMap spr, boolean withNull)
	{
		Color nullCol = null;
		if (withNull) nullCol = spr.sample(0, 0);
		int a, b;
		a = b = 0;
		for (int i = x; i < x + spr.getLength(); i++)
		{
			b = 0;
			for (int j = y; j < y + spr.getHeight(); j++)
			{
				Color c = spr.sample(a, b);
				if (!c.equals(nullCol)) paintPixel(i, j, c);
				b++;
			}
			a++;
		}
	}
	
	public void rect(int x, int y, int width, int height, Color c)
	{
		for (int i = x; i < x + width; i++)
			for (int j = y; j < y + height; j++)
				paintPixel(i, j, c);
	}
	
	public void fillTextBox(ColorMap textBox, String[] in)
	{
		int x = 0;
		int y = 350;
		placeSpriteNaive(x, y, textBox, false);
		for (int i = 0; i < 3; i++) stringOut(in[i], x + 10, y + 5 + (15 * i));
	}
	
	public void fillPartyBox(int pos, Entity member, ColorMap partyBox)
	{
		int x = 150 * pos;
		int xOff = x + 60;
		int yLineScale = 20;
		placeSpriteNaive(x, 0, partyBox, false);
		placeSpriteNaive(x + 5, 5, member.sprite, true);
		stringOut(member.name, xOff, yLineScale * 1);
		stringOut("lvl " + member.lvl, xOff, yLineScale * 2);
		stringOut("HP " + member.hp[member.lvl], xOff, yLineScale * 3);
		stringOut("MP " + member.mp[member.lvl], xOff, yLineScale * 4);
	}
	
	public void stringOut(String in, int x, int y)
	{
		//if (in == null) return;
		in = in.toLowerCase();
		for (int i = 0; i < in.length(); i++) if (in.charAt(i) == ' ') continue; else placeSpriteNaive(x + (11 * i), y, db.mcmb.grab("" + in.charAt(i)), true);
	}
}
