package game.data;

public class Gun {
	private int x, y;
	
	public Gun(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
}
