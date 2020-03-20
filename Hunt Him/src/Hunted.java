import java.awt.Color;
import java.awt.Graphics;

public class Hunted {
	boolean Shown = false;
	int x;
	int location = 2;
	int cycle = 0;
	int y = 300;
	Color c = Color.RED;
	Color brown = new Color(170, 95, 34);
	boolean hint = false;

	Hunted(boolean being_Shown, int Location) {
		Shown = being_Shown;
		location = Location+1;
		x = (450 / (3 * location)) - 75;
	}

	void Update(boolean being_Shown) {
		Shown = being_Shown;
		x = 450 / 3 * location - 75;
	}
	
	void ChangeLoaction(int Location) {
		location = Location+1;
	}

	int currentLocation() {
		return location;
	}
	
	void cycleUpdate() {
		cycle++;
	}
	
	void cycleReset() {
		cycle = 0;
	}

	void hint(boolean show) {
		hint = show;
	}
	
	void Draw(Graphics g) {
		if(hint) {
			g.drawImage(GamePanel.barrel2Img, 450 / 3 * location - 75, 300, 50, 50, null);
		}
		else {
			if(Shown) {
				g.drawImage(GamePanel.huntedImg, 450 / 3 * location - 75, 300, 50, 50, null);
			}
		}
	}
}
