package DevTools;

import Central.*;

public class Commander 
{
	Data db;
	public Commander(Data db) { this.db = db; }
	public void error(String cmd) {db.console.update(cmd, "Red"); }
	public void note(String cmd) {db.console.update(cmd, "Blue"); }
	public void reply(String cmd) {db.console.update(cmd, "Red"); }
	public void replyall(String which)
	{
		String[] out = DataReader.readRawText("console/" + which);
		for (int i = 0; i < out.length; i++) reply(out[i]);
	}
	
	public void fire(String cmd)
	{
		switch (cmd.split(" ")[0].toLowerCase())
		{
			case "echo":
				reply(cmd.substring(5));
				break;
			case "help":
				replyall("help");
				break;
			case "kill":
				System.exit(1);
				break;
			case "about":
				replyall("about");
				break;
			case "":
				break;
			case "devfunc":
				devfunc();
				reply("The greatest story ever told...");
				break;
			default:
				error("That's not a command");
		}
	}
	
	public void devfunc()
	{
		db.transition.ti.transitionTo(db.cutscene, "intro", db.transition.ti.SLOW);
	}
}
