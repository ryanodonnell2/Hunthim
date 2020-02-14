import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;

    int y;

    int width;

    int height;
    
    boolean isAlive = true;
    Rectangle collisionBox;
    GameObject(int x, int y, int width, int height, int ColisX, int ColisY, int ColisWidth, int ColisHeight){
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	collisionBox = new Rectangle(ColisX, ColisY, ColisWidth, ColisHeight);
    }
    void update(int CurX, int CurY, int ColisX, int ColisY, int ColisWidth, int ColisHeight) {
    	collisionBox.setBounds(ColisX, ColisY, ColisWidth, ColisHeight);
    }
    void draw(Graphics g) {
    	
    }
}