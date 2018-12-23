package game;

import java.awt.*;

public class FighterGUIBackground implements Runnable {
	private FighterGUI frm = null;
	
	private boolean isStopped = false;
	
	public FighterGUIBackground(FighterGUI frm) {
		this.frm = frm;
	}
	
	
	@Override
	public void run() {
		while (!isStopped) {
			try {
				Thread thread = new Thread(new Star(frm));
				thread.start();
				
				Thread.sleep(1000/50);
			}
			catch (InterruptedException ex) {
				
			}
		}
	}
	
	public void stop() {
		isStopped = true;
	}
}

class Star implements Runnable {
	private FighterGUI frm = null;
	private int x;
	private int speed;
	private int size = 5;
	
	public Star(FighterGUI frm) {
		this.frm = frm;
		x = (int)(Math.random() * frm.getWidth());
		speed = (int)(Math.random() * 5 + 5);
	}
	
	@Override
	public void run() {
		Graphics g = frm.getImage().getGraphics();
		
		for (int y = 0; y <= frm.getHeight(); y += speed) {
			try {
				g.setColor(Color.WHITE);
				g.fillOval(x, y, size, size);
				
				frm.repaint();
				Thread.sleep(1000/100);
				
				g.setColor(new Color(10, 10, 10));
				g.fillOval(x, y, size, size);
			}
			catch (InterruptedException ex) {  }
		}
	}
}