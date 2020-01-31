import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
public class GamePanel extends JPanel implements KeyListener, ActionListener {
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int Height = 800;
	final int Width = 500;
	int currentState = 1;
	int frame = 0;
	int button = 1;
	int location = 0;
	Font titleFont;
	Font subtitleFont;
	Font titleFontunbold;
	Font subtitleFontunbold;
	Timer timer;
	long count = 0;
	long prevcount = 0;
	boolean buttonPressed = false;
	Hunter hunter = new Hunter(false, 100);
	
	public void startGame() {
		timer.start();
		System.out.println("2");
	}
	
	GamePanel() {
		System.out.println("1");
		titleFont = new Font("Arial", Font.BOLD, 48);
		subtitleFont = new Font("Arial", Font.BOLD, 24);
		titleFontunbold = new Font("Arial", Font.PLAIN, 48);
		subtitleFontunbold = new Font("Arial", Font.PLAIN, 24);
		timer = new Timer(1000 / 60, this);
	}
	
	void updateMenuState() {
			
	}

	void updateGameState() {
			hunter.Update(true, 450/3*button-75);

	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		count = System.currentTimeMillis();
		if(prevcount != count) {
			frame++;
			prevcount = count;
		}
		if(frame%200 == 0) {
			frame = 0;
		}
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Width, Height);
		g.setColor(Color.RED);
		for (int i = -1; i < 8; i++) {
			g.fillRect(0, 200*i+frame, Width, 100);
		}
		g.setFont(titleFont);
		g.setColor(new Color(0,0,0));
		g.drawString("HUNT HIM", 137, 193);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 135, 333);
		g.drawString("Press SPACE for instructions", 100, 433);
		
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Width, Height);
		hunter.Draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Width, Height);
	}

	
	boolean guessCheck() {
		if(button==location) {
			return true;
		}
		else {
			return false;
		}
	}
	
	void animateGuess(Graphics g) {
		if(guessCheck()) {
			hunter.Update(true, 450/3*button-75);
			hunter.Draw(g);
		}
		else {
			hunter.Update(true, 450/3*button-75);
			hunter.Draw(g);
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == 3) {
				currentState = 1;
			} else {
				currentState++;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == 49) {
			button = 1;
			buttonPressed = true;
		}
		if(arg0.getKeyCode() == 50) {
			button = 2;
			buttonPressed = true;
		}
		if(arg0.getKeyCode() == 51) {
			button = 3;
			buttonPressed = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (currentState == 1) {
			
			updateMenuState();
			currentState = 1;
			
		} else if (currentState == 2) {

			updateGameState();
			currentState = 2;

		} else if (currentState == 3) {
			
			updateEndState();
			currentState = 3; 

		}
		
	}
	@Override
	public void paintComponent(Graphics g) {

		if (currentState == 1) {

			drawMenuState(g);

		} else if (currentState == 2) {

			drawGameState(g);

		} else if (currentState == 3) {

			drawEndState(g);

		}
	}
	public void keyTyped1(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println('3');
	}
	
		
	
	
}
