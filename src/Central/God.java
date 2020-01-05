package Central;

public class God 
{
	public static void main(String[] args)
	{
		GameLoop gl = new GameLoop();
		System.gc();
		while (true) gl.tickDevDebug();
	}
}
