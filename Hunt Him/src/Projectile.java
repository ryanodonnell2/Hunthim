import java.awt.Color;
import java.awt.Graphics;

public class Projectile {

	int xCordinate;
	int yCordinate;
	boolean shown = false;

	Projectile(int location, int StartY) {
		xCordinate = (450 / (3 * location)) - 75;
		yCordinate = StartY;
			}

	void update(int x, boolean show) {
		xCordinate = 450 / 3 * x - 75;
		if(show == false) {
			shown = false;
			yCordinate = 30;
		}
		else {
			shown = true;  
		}
		if(shown) {
			yCordinate += 5;
		}
	}

	void draw(Graphics g) {
		if (shown) {
			g.setColor(Color.BLUE);
			g.fillRect(xCordinate, yCordinate, 50, 50);
		}
	}
}
