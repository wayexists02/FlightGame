package game.data;

import game.FighterGUI;

import java.awt.Graphics;

public abstract class EnemyPlane {
	protected int x, y;
	protected int size;
	protected int nFrame;
	protected int nStay;
	protected int score;
	protected int count;
	protected Gun[] gun = null;
	protected FighterGUI gui = FighterGUI.getInstance();
	
	public EnemyPlane(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public void setLocation(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public int getCount() { return count; }
	public int getSize() { return size; }
	public int getScore() { return score; }
	public void bitted() { count--; }
	public Gun[] getGun() { return gun; }
	public abstract void move();
	public abstract void draw(Graphics g);
	public abstract void erase(Graphics g);
}
