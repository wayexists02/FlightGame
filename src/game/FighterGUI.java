package game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FighterGUI extends JFrame implements KeyListener {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	
	private Image img = null;
	private boolean isStopped = false;
	
	private static FighterGUI inst = null;
	public static FighterGUI getInstance() {
		if (inst == null)
			inst = new FighterGUI();
		return inst;
	}
	private FighterGUI() {
		super("Fighter2 Ver 0.1");
		setSize(WIDTH, HEIGHT);
		setBackground(new Color(10, 10, 10));
		setResizable(false);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		int scrWidth = (int)(tk.getScreenSize().getWidth());
		int scrHeight = (int)(tk.getScreenSize().getHeight());
		
		int x = (scrWidth - getWidth())/2;
		int y = (scrHeight - getHeight())/2;
		
		setLocation(x, y);
		
		addKeyListener(this);
	}
	public void start() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		img = createImage(getWidth(), getHeight());
		
		Thread backgroundThread = new Thread(new FighterGUIBackground(this));
		backgroundThread.start();
	}
	
	@Override
	public void paint(Graphics g) {
		if (img == null || isStopped) return;
		
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public Image getImage() {
		return img;
	}
	public void stop() {
		isStopped = true;
	}
	public void endMessage() {
		Graphics g = img.getGraphics();
		g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("END GAME", WIDTH/2 - 50, HEIGHT/2);
		repaint();
	}
	public void eraseScore(int score, int life) {
		Graphics g = img.getGraphics();
		g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		g.setColor(new Color(10, 10, 10));
		g.drawString("Life : " + life, 20, 100);
		g.drawString("Score : " + score, 20, 150);
	}
	public void drawScore(int score, int life) {
		Graphics g = img.getGraphics();
		g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("Life : " + life, 20, 100);
		g.drawString("Score : " + score, 20, 150);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_Q)
			System.exit(0);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
