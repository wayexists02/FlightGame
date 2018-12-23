package game.data;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int x, y;
	private int size;
	private int nFrame;
	private int nStay;
	private int speed = 10;
	private boolean player;
	
	public Bullet(int x, int y, boolean player) {
		this.x = x; this.y = y;
		this.player = player;
		nStay = nFrame = 10;
		size = 10;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getSize() { return size; }
	public boolean isPlayer() { return player; }
	public void erase(Graphics g) {
		g.setColor(new Color(10, 10, 10));
		g.fillOval(x, y, size, size);
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, size, size);
	}
	public void move() {
		if (--nStay == 0) {
			nStay = nFrame;
			if (player)
				y -= speed;
			else
				y += speed;
			return;
		}
	}
}
