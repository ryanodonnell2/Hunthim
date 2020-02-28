import java.awt.Graphics;
import java.awt.Color;

public class Hunter {
	boolean Shown = false;
	int x;
	int location;

	Hunter(boolean being_Shown, int startingLocation) {
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

	void Draw(Graphics g) {
		if (Shown == true) {
			g.setColor(Color.YELLOW);
			g.fillRect(x, 40, 50, 50);
		}
	}
}
