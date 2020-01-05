package DevTools;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;

public class DataReader 
{
	public static double[] getLookup(String type)
	{
		Scanner in;
		try { in = new Scanner(new File("./data/trig/" + type + ".txt")); } 
		catch (FileNotFoundException e) { in = null; System.exit(1);}
		double[] out = new double[Integer.parseInt(in.nextLine()) + 1];
		int fill = 0;
		while (in.hasNext())
		{
			out[fill] = Double.parseDouble(in.nextLine());
			fill++;
		}
		in.close();
		return out;
	}
	
	public static String[] readRawText(String path)
	{
		Scanner in;
		File tgt = new File("./data/" + path + ".txt");
		try 
		{ 
			in = new Scanner(tgt);
			String[] out = new String[(int) Files.lines(tgt.toPath()).count()];
			int fill = 0;
			while (in.hasNext())
			{
				out[fill] = in.nextLine();
				fill++;
			}
				in.close();
				return out;
		}
		catch (Exception e) { 
			in = null;
			System.err.println(e.getMessage());
			System.err.println("Couldn't find " + path + ".txt. Crashing.");
			System.exit(1);
			}
		return null;
	}
}
