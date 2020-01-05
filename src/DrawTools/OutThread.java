package DrawTools;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Central.Data;

public class OutThread extends Thread implements Runnable {
	Data db;
	Color[][] strm;
	Graphics2D g2d;
	
	int[] xTick = new int[600 * 500 * 2];
	int[] yTick = new int[600 * 500 * 2];
	int ticker = 0;

	public OutThread(Data db, Color[][] strm, Graphics g) {
		this.db = db;
		this.strm = strm;
		this.g2d = (Graphics2D) g;
		g2d.scale((double)db.FRAME_SIZE[0] / (double)600, (double)db.FRAME_SIZE[1] / (double)450);
	}

	public void run() {
		while (true)
		{
			while(db.hold) {try {sleep(1);} catch(Exception e){}}
			for (int i = 0; i < ticker; i++)
				{
					g2d.setColor(strm[xTick[i]][yTick[i]]);
					g2d.fillRect(xTick[i], yTick[i], 1, 1);
				}
			ticker = 0;
			db.hold = true;
		}
	}
	
	public void put(int x, int y)
	{
		xTick[ticker] = x;
		yTick[ticker] = y;
		ticker++;
	}
}
