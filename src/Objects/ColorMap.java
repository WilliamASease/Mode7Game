package Objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import DevTools.DataReader;

public class ColorMap 
{
	Color[] colors;
	byte[][] map;
	
	public ColorMap(String path)
	{
		System.gc();
		String[] raw = DataReader.readRawText("imagedata/" + path + ".PNG");
		ArrayList<Integer> bank = new ArrayList<Integer>();
		HashMap<Integer, Integer> builderMap = new HashMap<Integer, Integer>();
		map = new byte[Integer.parseInt(raw[0].split(" ")[0])]
				[Integer.parseInt(raw[0].split(" ")[1])];
		int x = 0;
		int y = 0;
		for(int i = 1; i < raw.length; i++)
		{
			int cur = Integer.parseInt(raw[i]);
			if (!builderMap.containsKey(cur))
			{
				builderMap.put(cur, bank.size());
				bank.add(cur);
			}
			if (builderMap.get(cur) > 0xFF)
			{
				System.err.println("Object had more than 31 Colors. Crashing.");
				System.err.println("for" + path);
				System.exit(0);
			}
			map[x][y] = (byte) (builderMap.get(cur) & 0xFF);
			//map[x][y] = (builderMap.get(cur));
			x++;
			if (x >= map.length)
			{
				y++;
				x = 0;
			}
		}
		colors = new Color[bank.size()];
		for (int i = 0; i < bank.size(); i++) colors[i] = new Color(bank.get(i));
		bank.clear();
		builderMap.clear();
	}
	
	public Color sample(int x, int y)
	{
		if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) return null;
		return colors[map[x][y]];
	}
	
	public int getLength() { return map.length; }
	public int getHeight() { return map[0].length; }
	
	public boolean[][] getReferences(int row, int entries)
	{
		boolean[][] out = new boolean[getLength()][getHeight()];
		int[] refs = new int[entries];
		for (int i = 0; i < entries; i++) refs[i] = map[i][row];
		for (int i = 0; i < getLength(); i++)
			for (int j = 0; j < getHeight(); j++)
				for (int k = 0; k < refs.length; k++)
					if (map[i][j] == refs[k])
						out[i][j] = true;
		return out;
	}
}
