package game.data;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyPlane3 extends EnemyPlane {
	public EnemyPlane3(int x, int y) {
		super(x, y);
		size = 60;
		score = 150;
		count = 3;
		nStay = nFrame = 1;
		
		gun = new Gun[1];
		gun[0] = new Gun(x, y);
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
				y += 1;
				for (int i = 0; i < gun.length; i++)
					gun[i].move(2, 1);
				break;
			case 6 : case 7 : case 8 : case 9 : case 10 :
				x -= 2;
				y += 1;
				for (int i = 0; i < gun.length; i++)
					gun[i].move(-2, 1);
				break;
			case 11 : case 12 : case 13 :
				y += 1;
				for (int i = 0; i < gun.length; i++)
					gun[i].move(0, 1);
				break;
			case 14 :
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
		g.fillOval(x - size/2, y, size, 10);
		g.fillOval(x - 10, y - size/2, 20, 10);
		g.fillOval(x - 5, y - size/2, 10, size);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x - size/2, y, size, 10);
		g.fillOval(x - 10, y - size/2, 20, 10);
		g.fillOval(x - 5, y - size/2, 10, size);
	}
}
