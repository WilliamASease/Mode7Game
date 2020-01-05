package DevTools;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PerformancePanel extends JPanel{
	boolean drawn = false;
	int scan = 0;
	int ti, tt, tr, H, W;
	JFrame mf;
	
	public PerformancePanel()
	{
		mf = new JFrame();
		mf.add(this);
		mf.setSize(500, 500);
		setSize(mf.getWidth(), mf.getHeight());
		mf.setLocation(5, 300);
		mf.setVisible(true);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		W = mf.getWidth();
		H = mf.getHeight();
		g.setColor(Color.BLUE);
		g.drawString("Input Time ms", 25, 30);
		g.setColor(Color.GREEN);
		g.drawString("Logic Time ms", 130, 30);
		g.setColor(Color.RED);
		g.drawString("Render Time ms", 250, 30);
		//g.setColor(Color.);
		//g.drawString("Projected FPS", 210, 30);
		g.setColor(Color.BLACK);
		g.drawLine(50, 50, 50, H - 50);
		g.drawLine(50, H - 50, W - 50, H - 50);
		for (int i = H - 50; i >= 50; i -= 50)
			g.drawString("" + (H - i - 50), 20, i);
		for (int i = 50; i <= W - 50; i += 50)
			g.drawString("" + (i - 50), i, W - 40);
	}

	public void map() 
	{
		Graphics g = this.getGraphics();
		g.setColor(Color.WHITE);
		g.drawLine(scan + 50, W - 50, scan + 50, 50);
		g.setColor(Color.BLACK);
		g.drawLine(scan + 50, H - 50 - 34, scan + 50, H - 50 - 34);
		g.setColor(Color.BLUE);
		g.drawLine(scan + 50, H - 50 - ti, scan + 50, H - 50 - ti);
		g.setColor(Color.GREEN);
		g.drawLine(scan + 50, H - 50 - tt, scan + 50, H - 50 - tt);
		g.setColor(Color.RED);
		g.drawLine(scan + 50, H - 50 - tr, scan + 50, H - 50 - tr);
		scan++;
		if (scan > W - 50 * 2) scan = 0;
	}
	
	public void update(int ti, int tt, int tr)
	{
		this.ti = ti;
		this.tt = tt;
		this.tr = tr;
	}
}
