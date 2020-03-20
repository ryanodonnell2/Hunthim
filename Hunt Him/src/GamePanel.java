import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
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
	int score = 0;
	Font titleFont;
	Font subtitleFont;
	Font titleFontunbold;
	Font subtitleFontunbold;
	Timer timer;
	long count = 0;
	long prevcount = 0;
	Hunter hunter = new Hunter(false, 1);
	Hunted hunted = new Hunted(false, 1);
	Projectile projectile = new Projectile(1, 50);
	Barrel barrel = new Barrel(300);
	Random r = new Random();
	
	int preyLocation = 2;
	
	
	boolean firing = false;
	boolean targetShown = false;
	int targetShownTime = 0;
	boolean hit = false;
	
	
	boolean showingInstructions = false;
	String Instr = "Press Space to See Instructions";
	String InstrNote = "You get 3 lives, if you miss you ";
	String p2 = "lose a life. If you don't find him in";
	String p3 = "by the time the bar vanishes you lose a life.";
	String bottomText = Instr;
	
	
	int lives = 3;
	
	
	int livesAdding = 1;
	int firingLocation = 0;
	int showingTime = 50;
	int center = Width/2;
	int strlen;
	
	
	boolean alreadyFound = false;
	int prevLevel = 0;
	int level = 0;
	int LevelUpRequired = 5;
	double rtime = 100;
	double showTime = r.nextInt((int) rtime)+rtime/10;
	int TimeShowing = 0;
	boolean alreadyShown = false;
	
	long timeFound = System.currentTimeMillis();
	long tempTimefound = 0;
	long timeSinceLastFind = 0;
	TimeBar bar = new TimeBar(Width, 5, 300);
	double timetofind = 300;
	
	String text = "";
	
	
	public static BufferedImage barrelImg;
	
	public static BufferedImage barrel2Img;

    public static BufferedImage hunterImg;

    public static BufferedImage huntedImg;
    
    public static BufferedImage boardImg;

	public void startGame() {
		timer.start();
	}

	GamePanel() {
		titleFont = new Font("Arial", Font.BOLD, 48);
		subtitleFont = new Font("Arial", Font.BOLD, 24);
		titleFontunbold = new Font("Arial", Font.PLAIN, 48);
		subtitleFontunbold = new Font("Arial", Font.PLAIN, 24);
		timer = new Timer(1000 / 60, this);
		score = 0;
		timeFound = System.currentTimeMillis();
		timeSinceLastFind = 0;
		try {

            barrelImg = ImageIO.read(this.getClass().getResourceAsStream("Barrel.jpg"));
            
            barrel2Img = ImageIO.read(this.getClass().getResourceAsStream("Barrel2.jpg"));

            hunterImg = ImageIO.read(this.getClass().getResourceAsStream("Hunter.jpg"));

            huntedImg = ImageIO.read(this.getClass().getResourceAsStream("Hunted.png"));
            
            boardImg = ImageIO.read(this.getClass().getResourceAsStream("board.png"));


    } catch (IOException e) {

            e.printStackTrace();

    }
	}

	void updateMenuState() {
		timeFound = System.currentTimeMillis();
		score = 0;
		level = 0;
		rtime = 100;
		bar.changeTimeTillEmpty(300);
		lives = 3;
		targetShownTime = 0;
		targetShown = false;
	}

	void updateGameState() {
		
		tempTimefound = System.currentTimeMillis()-timeFound;
		timeSinceLastFind = tempTimefound/100;
		bar.currentTime(timeSinceLastFind);
		
		hunter.Update(true);
		hunter.ChangeLoaction(button);
		hunted.Update(false);
		hunted.ChangeLoaction(preyLocation);
		projectile.update(hunter.currentLocation(), firing);
		
		if(alreadyShown == false && currentState == 2) {
			showTime = r.nextInt((int) rtime);
			alreadyShown = true;
		}
		if(TimeShowing<showTime) {
			hunted.Update(true);
			hunted.hint(true);
			TimeShowing++;
		}
		else {
			hunted.hint(false);
			hunted.Update(false);
		}
		
		if(projectile.yCordinate > hunted.y) {
			if(projectile.xCordinate == hunted.x) {
				targetShown = true;
				if(alreadyFound == false) {
				score ++;
				timeFound = System.currentTimeMillis();
				alreadyFound = true;
				}
			}
			else {
				lives--;
			}
			firing = false;
		}
		if(targetShownTime<showingTime && targetShown) {
			hunted.Update(true);
			targetShownTime++;
		}
		else if (targetShownTime>=showingTime && targetShown) {
			hit = true;
			preyLocation = r.nextInt(3);
			targetShownTime = 0;
			targetShown = false;
			alreadyFound = false;
			alreadyShown = false;
			TimeShowing = 0;
		}
		else {
			targetShownTime = 0;
			targetShown = false;
		}
		if(score%LevelUpRequired == 0 ) {
			level = score/LevelUpRequired;
		}
		if(level != prevLevel) {
			if(lives < 25) {
			lives += livesAdding;
			if(rtime >= 3.5) {
			rtime/=2;
			}
			else {
				rtime = 1;
			}
			}
			if(level%2 == 0) {
				bar.changeTimeTillEmpty(bar.timeLength/2);
				showingTime/=2;
			}
			if(level%3 == 0) {
				LevelUpRequired+=2;
			}
			prevLevel = level;
		}
		if(bar.timeLeft() == false) {
			lives--;
			timeFound = System.currentTimeMillis();
			tempTimefound = System.currentTimeMillis()-timeFound;
			timeSinceLastFind = tempTimefound/100;
			bar.currentTime(timeSinceLastFind);
			System.out.println("Life Subtracted");
		}
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		count = System.currentTimeMillis();
		if (prevcount != count) {
			frame++;
			prevcount = count;
		}
		if (frame % 200 == 0) {
			frame = 0;
		}
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Width, Height);
		g.setColor(Color.RED);
		for (int i = -1; i < 8; i++) {
			g.fillRect(0, 200 * i + frame, Width, 100);
		}
		g.setFont(titleFont);
		g.setColor(new Color(0, 0, 0));
		g.drawString("HUNT HIM", 137, 193);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to start", 135, 333);
		center = Width/2;
		strlen = g.getFontMetrics().stringWidth(bottomText)/2; 
		g.drawString(bottomText, center - strlen, 433); 
		if(showingInstructions) {
		center = Width/2;
		strlen = g.getFontMetrics().stringWidth(p2)/2; 
		g.drawString(p2, center - strlen, 460); 
		center = Width/2;
		strlen = g.getFontMetrics().stringWidth(p3)/2; 
		g.drawString(p3, center - strlen, 485); 
		}

	}

	void drawGameState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Width, Height);
		hunter.Draw(g);
		barrel.draw(g);
		hunted.Draw(g);
		projectile.draw(g);
		g.setColor(Color.BLACK);
		g.setFont(subtitleFont);
		text = "Score: "+score;
		g.drawString(text, 250-(g.getFontMetrics().stringWidth(text)/2), 20);
		text = "Level: "+(level+1);
		g.drawString(text, 400-(g.getFontMetrics().stringWidth(text)/2), 20);
		g.drawString("Lives left: "+lives, (g.getFontMetrics().stringWidth(text)/2)-25, 20);
		if(lives<=0) {
			currentState = 3;
			lives = 3;
		}
		bar.DrawBar(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Width, Height);
		g.setFont(subtitleFont);
		g.setColor(Color.BLACK);
		String text = "You lose! You found him " + score + " times!";
		strlen = g.getFontMetrics().stringWidth(text)/2; 
		g.drawString(text, center - strlen, 200); 
		text = "Press Enter to continue";
		strlen = g.getFontMetrics().stringWidth(text)/2; 
		g.drawString(text, center - strlen, 300); 
	}

	void animateGuess(Graphics g) {
		hunter.Update(true);
		hunter.ChangeLoaction(button);
		hunter.Draw(g);
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			preyLocation = r.nextInt(3);
			if (currentState == 3) {
				currentState = 1;
			} else {
				currentState++;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == 49 || arg0.getKeyCode() == 97) {
			button = 1;

		}
		if (arg0.getKeyCode() == 50 || arg0.getKeyCode() == 98) {
			button = 2;

		}
		if (arg0.getKeyCode() == 51 || arg0.getKeyCode() == 99) {
			button = 3;

		}
		if (arg0.getKeyCode() == 37) {
			if (button > 1) {
				button--;
			} else {
				button = 3;
			}
		}
		if (arg0.getKeyCode() == 39) {
			if (button < 3) {
				button++;
			} else {
				button = 1;
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			if(currentState == 2) {
				firing = true;
			}
			else if(currentState == 1) {
				if(showingInstructions) {
					bottomText = Instr;
					showingInstructions = false;
				}
				else {
					bottomText = InstrNote;
					showingInstructions = true;
				}
			}
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
	}

}
