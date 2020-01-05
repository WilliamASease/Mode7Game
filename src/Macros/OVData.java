package Macros;

import java.util.ArrayList;

import DevTools.DataReader;
import Objects.BoardEntity;

public class OVData 
{
	public static ArrayList<BoardEntity> spriteMaster = readMasterList("spritemaster");
	public static ArrayList<BoardEntity> eventPosMaster = readMasterList("eventmaster");
	
	public static ArrayList<BoardEntity> readMasterList(String in)
	{
		String[] dat = DataReader.readRawText("otherdata/" + in);
		ArrayList<BoardEntity> out = new ArrayList<BoardEntity>();
		for (int i = 0; i < dat.length; i++)
		{
			String[] t = dat[i].split(" ");
			out.add(new BoardEntity(Integer.parseInt(t[0]), Integer.parseInt(t[1]), t[2]));
		}
		return out;
	}
}
