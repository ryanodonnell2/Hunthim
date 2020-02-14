import java.awt.Color;
import java.awt.Graphics;

public class Hunted {
	boolean Shown = false;
	int x;
	int location;
	int cycle = 0;

	Hunted(boolean being_Shown, int Location) {
		Shown = being_Shown;
		x = 450 / 3 * location - 75;
	}

	void Update(boolean being_Shown) {
		Shown = being_Shown;
		x = 450 / 3 * location - 75;
	}
	
	void ChangeLoaction(int Location) {
		location = Location;
	}

	int currentLocation() {
		System.out.println(location);
		return location;
	}
	
	void cycleUpdate() {
		cycle++;
	}
	
	void cycleReset() {
		cycle = 0;
	}

	void Draw(Graphics g) {
		if (Shown == true) {
			g.setColor(Color.RED);
			g.fillRect(x, 100, 50, 50);
		}
	}
}
