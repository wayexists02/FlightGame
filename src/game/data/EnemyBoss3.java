package game.data;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyBoss3 extends EnemyPlane {
	public EnemyBoss3(int x, int y) {
		super(x, y);
		size = 300;
		score = 3000;
		count = 300;
		nStay = nFrame = 4;
		
		gun = new Gun[7];
		gun[0] = new Gun(x - size/2, y);
		gun[1] = new Gun(x - size/2 + 50, y);
		gun[2] = new Gun(x - size/2 + 100, y);
		gun[3] = new Gun(x - size/2 + 150, y);
		gun[4] = new Gun(x - size/2 + 200, y);
		gun[5] = new Gun(x - size/2 + 250, y);
		gun[6] = new Gun(x - size/2 + 300, y);
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
		g.fillOval(x - size/2, y - size/2, size, size);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(220, 220, 240));
		g.fillOval(x - size/2, y - size/2, size, size);
		g.fillOval(x - 50, y - 50, 100, 100);
	}
}
