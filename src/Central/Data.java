package Central;
import Frames.*;
import Objects.*;
import Ticks.*;
import DevTools.*;
import DrawTools.OutThread;
import DrawTools.SpriteTools;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class Data 
{
	public Random dice = new Random();
	public double[] sin = DataReader.getLookup("sin");
	public double[] cos = DataReader.getLookup("cos");
	public MasterColorMapBank mcmb = new MasterColorMapBank();
	public Color[][] current = new Color[600][450];
	
	public PerformancePanel pp;
	public Console console;
	public Commander commander;
	
	public final int[] FRAME_SIZE = new int[] {1200, 900};
	public JFrame mainFrame = getMainFrame();
	public JPanel mainPanel = getMainPanel(mainFrame);
	public InputHandler ih = new InputHandler(this);
	public boolean[] keys = new boolean[ih.KEYS.length];
	public SpriteTools sprts = new SpriteTools(this);
	
	public TFPair<OverWorldTick, OverWorldFrame> overWorld = new TFPair<OverWorldTick, OverWorldFrame>(new OverWorldTick(this), new OverWorldFrame(this));
	public TFPair<BattleTick, BattleFrame> battle = new TFPair<BattleTick, BattleFrame>(new BattleTick(this), new BattleFrame(this));
	public TFPair<TransitionTick, TransitionFrame> transition = new TFPair<TransitionTick, TransitionFrame>(new TransitionTick(this), new TransitionFrame(this));
	public TFPair<CutsceneTick, CutsceneFrame> cutscene = new TFPair<CutsceneTick, CutsceneFrame>(new CutsceneTick(this), new CutsceneFrame(this));
	public TFPair<GameOverTick, GameOverFrame> gameover = new TFPair<GameOverTick, GameOverFrame>(new GameOverTick(this), new GameOverFrame(this));
	
	@SuppressWarnings("rawtypes")
	public TFPair pair = overWorld;
	public OutThread ot = new OutThread(this, current, mainPanel.getGraphics());
	public boolean hold;
	public Data() { ot.start(); }
	
	public JFrame getMainFrame()
	{
		JFrame out = new JFrame();
		out.setTitle("Untitled Java Game");
		out.setSize(FRAME_SIZE[0], FRAME_SIZE[1]);
		out.setLocation(500, 100);
		out.setVisible(true);
		out.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return out;
	}
	
	public JPanel getMainPanel(JFrame mf)
	{
		JPanel out = new JPanel();
		RepaintManager.currentManager(out);
		out.setDoubleBuffered(false);
		out.setIgnoreRepaint(true);
		mf.add(out);
		out.setSize(mf.getWidth(), mf.getHeight());
		out.setVisible(true);
		return out;
	}
}
