import java.awt.Color;
import java.awt.Graphics;

public class Barrel {
	int y = 300;
	
	Barrel(int barrelY) {
		y = barrelY;
	}

	void draw(Graphics g){
		Color brown = new Color(130, 60, 4);
		g.setColor(brown);
		g.drawImage(GamePanel.barrelImg, 450 / 3 * 1 - 75, 300, 50, 50, null);
		g.drawImage(GamePanel.barrelImg, 450 / 3 * 2 - 75, 300, 50, 50, null);
		g.drawImage(GamePanel.barrelImg, 450 / 3 * 3 - 75, 300, 50, 50, null);
	}
}
