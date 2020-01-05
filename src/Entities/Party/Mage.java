package Entities.Party;
import Central.Data;

public class Mage extends PartyMember
{
	public Mage(Data db)
	{
	lvl = 0;
	hp = new int[] {5, 10, 20, 40, 80, 120};
	mp = new int[] {0, 1, 5, 7, 9, 12, 15};
	atk = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
	def = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
	mag = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
	name = "Wizard";
	sprite = db.mcmb.grab("mage_battle");
	}
}