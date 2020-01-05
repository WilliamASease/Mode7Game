package DevTools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Central.*;

@SuppressWarnings("serial")
public class Console extends JPanel
{
	JFrame mf;
	JTextField input = new JTextField();
	LinkedList<ColoredText> log = new LinkedList<ColoredText>();
	int logl;

	public Console(Data db, int logLength)
	{
		logl = logLength;
		mf = new JFrame();
		mf.setSize(400, logLength * 30);
		this.setSize(mf.getWidth(), mf.getHeight());
		mf.add(this);
		mf.setTitle("Console");
		log.add(new ColoredText("---CONSOLE INIT---", "Black"));
		log.add(new ColoredText("Untitled Java Game 2019 William Sease", "Blue"));
		mf.add(input, BorderLayout.PAGE_END);
		input.addActionListener(getInputActionListener(this, db.commander));
		mf.setVisible(true);
		this.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		int vert = this.getHeight() - 5;
		for (int i = log.size() - 1; i >= 0; i--)
		{
			g.setColor(log.get(i).color);
			g.drawString(log.get(i).text, 5, vert);
			vert = vert - 15;
		}
	}
	
	public void update(String in, String color)
	{
		log.addLast(new ColoredText(in, color));
		if (log.size() > logl)
			log.remove(0);
		repaint();
	}
	
	
	public ActionListener getInputActionListener(Console c, Commander cmd)
	{
		return new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String sub = c.input.getText();
				c.update("> " + sub, "Black");
				c.input.setText("");
				cmd.fire(sub);
			}
		};
	}
	
	public class ColoredText
	{
		String text;
		Color color;
		public ColoredText(String text, String ctext)
		{
			this.text = text;
			switch(ctext)
			{
				case "red":
				case "Red":
					color = Color.red;
					break;
				case "blue":
				case "Blue":
					color = Color.blue;
					break;
				case "green":
				case "Green":
					color = Color.green;
					break;
				default:
					color = Color.black;
			}
		}
	}
	
	
}
