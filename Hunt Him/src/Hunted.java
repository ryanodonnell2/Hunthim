import java.awt.Color;
import java.awt.Graphics;

public class Hunted {
	boolean Shown = false;
	int x;
	int location = 2;
	int cycle = 0;
	int y = 300;

	Hunted(boolean being_Shown, int Location) {
		Shown = being_Shown;
		location = Location+1;
		x = (450 / (3 * location)) - 75;
		System.out.println(x);
	}

	void Update(boolean being_Shown) {
		Shown = being_Shown;
		x = 450 / 3 * location - 75;
	}
	
	void ChangeLoaction(int Location) {
		location = Location+1;
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
		if(Shown) {
			g.setColor(Color.RED);
			g.fillRect(x, y, 50, 50);
		}
	}
}
