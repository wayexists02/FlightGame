package game.data;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyBoss1 extends EnemyPlane {
	public EnemyBoss1(int x, int y) {
		super(x, y);
		size = 100;
		score = 500;
		count = 50;
		nStay = nFrame = 3;
		
		gun = new Gun[4];
		gun[0] = new Gun(x - size/2 + 10, y + 10);
		gun[1] = new Gun(x - size/2 + 30, y + 10);
		gun[2] = new Gun(x + size/2 - 10, y + 10);
		gun[3] = new Gun(x + size/2 - 30, y + 10);
	}

	@Override
	public void move() {
		erase(gui.getImage().getGraphics());
		if (--nStay == 0) {
			nStay = nFrame;
			int num = (int)(Math.random() * 15);
			switch (num) {
			case 0 :
				break;
			case 1 : case 2 : case 3 : case 4 : case 5 :
				x += 2;
				for (int i = 0; i < gun.length; i++)
					gun[i].move(2, 0);
				break;
			case 6 : case 7 : case 8 : case 9 : case 10 :
				x -= 2;
				for (int i = 0; i < gun.length; i++)
					gun[i].move(-2, 0);
				break;
			case 11 : case 12 :
				y += 1;
				for (int i = 0; i < gun.length; i++)
					gun[i].move(0, 1);
				break;
			case 13 : case 14 :
				y -= 1;
				for (int i = 0; i < gun.length; i++)
					gun[i].move(0, -1);
				break;
			}
		}
		draw(gui.getImage().getGraphics());
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x - size/2, y + 10, size, 15);
		g.fillOval(x - 20, y - size/2, 40, 10);
		g.setColor(new Color(100, 100, 100));
		g.fillOval(x - 5, y - size/2, 10, size);
		g.fillRect(x - size/2 + 10, y + 10, 5, 20);
		g.fillRect(x - size/2 + 30, y + 10, 5, 20);
		g.fillRect(x + size/2 - 10, y + 10, 5, 20);
		g.fillRect(x + size/2 - 30, y + 10, 5, 20);
	}
	@Override
	public void erase(Graphics g) {
		g.setColor(new Color(10, 10, 10));
		g.fillOval(x - size/2, y + 10, size, 15);
		g.fillOval(x - 20, y - size/2, 40, 10);
		g.setColor(new Color(10, 10, 10));
		g.fillOval(x - 5, y - size/2, 10, size);
		g.fillRect(x - size/2 + 10, y + 10, 5, 20);
		g.fillRect(x - size/2 + 30, y + 10, 5, 20);
		g.fillRect(x + size/2 - 10, y + 10, 5, 20);
		g.fillRect(x + size/2 - 30, y + 10, 5, 20);
	}
}
