package game.data;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyBoss2 extends EnemyPlane{
	public EnemyBoss2(int x, int y) {
		super(x, y);
		size = 200;
		score = 1000;
		count = 100;
		nStay = nFrame = 4;
		
		gun = new Gun[5];
		gun[0] = new Gun(x - size/2, y - size/2);
		gun[1] = new Gun(x - size/2 + 50, y - size/2);
		gun[2] = new Gun(x - size/2 + 100, y - size/2);
		gun[3] = new Gun(x - size/2 + 150, y - size/2);
		gun[4] = new Gun(x - size/2 + 200, y - size/2);
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
	public void erase(Graphics g) {
		g.setColor(new Color(10, 10, 10));
		g.fillOval(x - size/2, y - size/2, size, 20);
		g.fillOval(x - size/2 + 10, y - size/2, size - 20, 40);
		g.fillOval(x - size/2 + 20, y - size/2, size - 40, 60);
		g.fillOval(x - size/2 + 30, y - size/2, size - 60, 80);
		g.fillOval(x - size/2 + 40, y - size/2, size - 80, 100);
		g.fillOval(x - size/2 + 50, y - size/2, size - 100, 120);
		g.fillOval(x - size/2 + 60, y - size/2, size - 120, 140);
		g.fillOval(x - size/2 + 70, y - size/2, size - 140, 160);
		g.fillOval(x - size/2 + 80, y - size/2, size - 160, 180);
		g.fillOval(x - size/2 + 90, y - size/2, size - 180, 200);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(220, 220, 240));
		g.fillOval(x - size/2, y - size/2, size, 20);
		g.fillOval(x - size/2 + 10, y - size/2, size - 20, 40);
		g.fillOval(x - size/2 + 20, y - size/2, size - 40, 60);
		g.fillOval(x - size/2 + 30, y - size/2, size - 60, 80);
		g.fillOval(x - size/2 + 40, y - size/2, size - 80, 100);
		g.fillOval(x - size/2 + 50, y - size/2, size - 100, 120);
		g.fillOval(x - size/2 + 60, y - size/2, size - 120, 140);
		g.fillOval(x - size/2 + 70, y - size/2, size - 140, 160);
		g.fillOval(x - size/2 + 80, y - size/2, size - 160, 180);
		g.fillOval(x - size/2 + 90, y - size/2, size - 180, 200);
	}
}
