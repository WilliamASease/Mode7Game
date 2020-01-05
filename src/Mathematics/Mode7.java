package Mathematics;

public class Mode7 
{
	public static int[] rotateSimple(int i, int j, int x0, int y0, int rot, int scalar, double[] sin, double[] cos)
	{
		int h = x0 - 300 + i;
		int v = y0 - 450 + j;
		int[] out = new int[2];
		int hm = h - x0;
		int vm = v - y0;
		vm = vm * scalar;
		out[0] = (int) (cos[rot] * hm + sin[rot] * vm) + x0;
		out[1] = (int) (-sin[rot] * hm + cos[rot] * vm) + y0; 
		return out;
	}
	
	public static int[] rotateInverse(int x, int y, int x0, int y0, int rot, int scalar, double[] sin, double[] cos)
	{
		int[] out = new int[2];
		y = y - y0;
		x = x - x0;
		rot = 360000 - rot;
		int hm = (int) (cos[rot] * x + sin[rot] * y);
		int vm = (int) (-sin[rot] * x + cos[rot] * y);
		vm = vm / scalar;
		int h = x0 + hm;
		int v = y0 + vm;
		out[0] = -x0 + 300 + h;
		out[1] = -y0 + 450 + v;
		return out;
	}
	
	public static int[] rotateSimpleZoom(int i, int j, int x0, int y0, int rot, int scalar, double[] sin, double[] cos)
	{
		int h = x0 - 300 + i;
		int v = y0 - 450 + j;
		int[] out = new int[2];
		int hm = h - x0;
		int vm = v - y0;
		hm = hm * scalar;
		vm = vm * scalar;
		out[0] = (int) (cos[rot] * hm + sin[rot] * vm) + x0;
		out[1] = (int) (-sin[rot] * hm + cos[rot] * vm) + y0;
		return out;
	}
	
	public static int[] calculateSimple(int h, int v, int x0, int y0, int rot, double[] sin, double[] cos)
	{
		int[] out = new int[2];
		return out;
	}
	
	public static int[] calculateComplex(int h, int v, int x0, int y0, double a, double b, double c, double d)
	{
		return new int[0];
	}
}
