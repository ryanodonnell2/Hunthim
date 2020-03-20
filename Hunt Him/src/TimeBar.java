import java.awt.Color;
import java.awt.Graphics;

public class TimeBar {
	
	int WidthMax;
	int height;
	int timeLength;
	double currentTime;
	double timeleft;
	double curwidth;
	double percentOfBarLeft;
	double width;
	
	TimeBar(int MaxWidth, int Height, int timeToEmpty){
		WidthMax = MaxWidth;
		height = Height;
		timeLength = timeToEmpty;
		timeleft = timeLength;
		currentTime = 0;
		percentOfBarLeft = timeleft/timeLength;
	}
	
	void currentTime(double time) {
		currentTime = time;
	}
	
	void DrawBar(Graphics g) {
		timeleft = timeLength-currentTime;
		curwidth = ( ((double)WidthMax) /((double)timeLength)*timeleft);
		width = curwidth;
		if(curwidth>=WidthMax/2) {
			g.setColor(Color.GREEN);
		}
		else if(curwidth<WidthMax/2) {
			g.setColor(Color.YELLOW);
		}
		else {
			g.setColor(Color.RED);
		}
		if(curwidth>=0) {
		g.fillRect(0, 30, (int)width, height);
		}
	}
	
	boolean timeLeft() {
		timeleft = timeLength-currentTime;
		if(timeleft <= 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	void changeTimeTillEmpty(int time) {
		timeLength = time;
	}
	
}
