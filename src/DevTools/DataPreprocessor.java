package DevTools;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class DataPreprocessor 
{
	static String dataFolder = "./data/";
	static String dataInFolder = "./datain/";
	
	public static void main(String[] args) throws Exception
	{
		buildLookupTables(0, 360000);
		//buildImage("maps/TESTMAP.PNG");
		//buildImage("maps/TESTBACK.PNG");
		//buildImage("sprites/Protag.PNG");
		//buildImage("enemysprites/goober.PNG");
		buildAllImages();
	}
	
	public static void buildLookupTables(double min, double max) throws Exception
	{
		System.out.println("Bulding Sin Table...");
		PrintWriter out = new PrintWriter(dataFolder + "/trig/sin.txt");
		out.println((int)max);
		for (double i = min; i < max; i += 1)
			out.println(Math.sin(i / 1000 * 0.0174533));
		System.out.println("Done!");
		out.close();
		
		System.out.println("Bulding Cos Table...");
		out = new PrintWriter(dataFolder + "/trig/cos.txt");
		out.println((int)max);
		for (double i = min; i < max; i += 1)
			out.println(Math.cos(i / 1000 * 0.0174533));
		System.out.println("Done!");
		out.close();
	}
	
	public static void buildImage(String brd) throws Exception
	{
		System.out.println("Building " + brd);
		PrintWriter out = new PrintWriter(dataFolder + "/imagedata/" + brd + ".txt");
		BufferedImage in = ImageIO.read(new File(dataInFolder + brd));
		out.println(in.getWidth() + " " + in.getHeight());
		for (int i = 0; i < in.getHeight(); i++)
			for (int j = 0; j < in.getWidth(); j++)
			{
				out.print(in.getRGB(j, i));
				if (!(j + 1 == in.getWidth() && i + 1 == in.getHeight())) out.print("\n");
			}
		out.close();
		System.out.println("Done!");
	}
	
	public static void buildAllImages() throws Exception
	{
		File[] Folders = new File(dataInFolder).listFiles();
		for (int i = 0; i < Folders.length; i++)
		{	
			File[] toProcess = Folders[i].listFiles();
			for (int j = 0; j < toProcess.length; j++)
			{
				buildImage(Folders[i].getName() + "/" + toProcess[j].getName());
			}
		}
	}
}
