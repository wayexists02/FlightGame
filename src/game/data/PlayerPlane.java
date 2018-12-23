package game.data;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerPlane {
	private int x, y;
	private int size = 50;
	
	public PlayerPlane(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getSize() { return size; }
	public void setLocation(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public void erase(Graphics g) {
		g.setColor(new Color(10, 10, 10));
		g.fillRect(x - size/2, y - 10, size, 10);
		g.fillRect(x - 10, y + size/2 - 8, 20, 8);
		g.fillOval(x - 5, y - size/2, 10, size);
	}
	public void draw(Graphics g) {
		g.setColor(new Color(50, 50, 200));
		g.fillRect(x - size/2, y - 10, size, 10);
		g.fillRect(x - 10, y + size/2 - 8, 20, 8);
		g.setColor(new Color(50, 50, 250));
		g.fillOval(x - 5, y - size/2, 10, size);
	}
}
