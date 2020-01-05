package Objects;

import java.io.File;
import java.util.HashMap;

public class MasterColorMapBank 
{
	public HashMap<String, ColorMap> bank = new HashMap<String, ColorMap>();
	
	public MasterColorMapBank()
	{
		File[] Folders = new File("./data/imagedata").listFiles();
		for (int i = 0; i < Folders.length; i++)
		{	
			File[] toProcess = Folders[i].listFiles();
			for (int j = 0; j < toProcess.length; j++)
			{
				bank.put(toProcess[j].getName().substring(0, toProcess[j].getName().length() - 8), 
						new ColorMap(Folders[i].getName() + "/" + toProcess[j].getName().substring(0, toProcess[j].getName().length() - 8)));
			}
		}
	}
	
	public ColorMap grab(String key) 
	{ 
		if (!bank.containsKey(key))
		{
			System.err.println("MasterColorMapBank: No such thing as a " + key + ". Crashing.");
			System.exit(1);
		}
		return bank.get(key); 
	}	
}
