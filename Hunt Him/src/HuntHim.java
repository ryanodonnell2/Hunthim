import java.awt.Dimension;

import javax.swing.JFrame;

public class HuntHim {
	JFrame frame;
	GamePanel GamePanel;
	static final int width = 500;
	static final int height = 800;
	
	public static void main(String[] args) {
		System.out.println("1");
		new HuntHim().setup();
		
	}
	
	HuntHim(){
		 frame = new JFrame();
		 GamePanel = new GamePanel();
	}
	void setup() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(new Dimension(width, height));
		frame.add(GamePanel);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.addKeyListener(GamePanel);
		frame.pack();
		GamePanel.startGame();
	}
}
