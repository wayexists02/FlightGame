package game;

import java.awt.Color;
import java.awt.Graphics;

public class FireThread implements Runnable {
	private int x, y;
	private FighterGUI gui = FighterGUI.getInstance();
	
	public FireThread(int x, int y) {
		this.x = x; this.y = y;
	}
	
	@Override
	public void run() {
		Graphics g = gui.getImage().getGraphics();
		try {
			g.setColor(new Color(250, 100, 100));
			g.fillOval(x - 10, y - 10, 20, 20);
			Thread.sleep(100);
			g.setColor(new Color(10, 10, 10));
			g.fillOval(x - 10, y - 10, 20, 20);
			
			Thread.sleep(100);
			
			g.setColor(new Color(250, 150, 150));
			g.fillOval(x - 20, y - 20, 40, 40);
			Thread.sleep(100);
			g.setColor(new Color(10, 10, 10));
			g.fillOval(x - 20, y - 20, 40, 40);
		}
		catch (InterruptedException ex) {  }
	}
}
