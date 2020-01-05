package Macros;

import DevTools.DataReader;

public class SpellBook 
{
	public static final int UNARMED = 0;
	public static final int SMALLHEAL = 1;
	
	public static final String[] SPELLNAMES = DataReader.readRawText("otherdata/spells");
	public static final String[] SPELLDESCS  = DataReader.readRawText("otherdata/spelldesc");
	public static final int NUMBER_OF_SPELLS = SPELLNAMES.length;
}
