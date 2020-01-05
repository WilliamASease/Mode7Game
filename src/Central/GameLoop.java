package Central;

import DevTools.*;

public class GameLoop extends Thread
{
	Data db = new Data();
	public void tick()
	{
		long t = System.currentTimeMillis() + 34;
		db.ih.update();
		db.pair.ti.tick();
		db.pair.fi.render();
		//db.ot.invoke();
		//db.px.paintToScreen();
		while (t > System.currentTimeMillis()) {};
	}
	
	///This tick shows the MS of each stage and enables the DevLog.
	public void tickDevDebug()
	{
		if (db.pp == null) db.pp = new PerformancePanel();
		if (db.commander == null) db.commander = new Commander(db);
		if (db.console == null) db.console = new Console(db, 10);
		long t = System.currentTimeMillis();
		db.ih.update();
		long ti = System.currentTimeMillis() - t;
		long tit = System.currentTimeMillis();
		db.pair.ti.tick();
		long tt = System.currentTimeMillis() - tit;
		long ttt = System.currentTimeMillis();
		while (!db.hold) { try {sleep(1);} catch (InterruptedException e) {}}
		db.pair.fi.render();
		db.hold = false;
		//db.px.paintToScreen();
		long tr = System.currentTimeMillis() - ttt;
		db.pp.update((int)ti, (int)tt, (int)tr);
		db.pp.map();
		while (t + 34 > System.currentTimeMillis()) {};
	}
}
