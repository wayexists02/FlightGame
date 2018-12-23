package game;
/**
 * 제작 시작일 : 2014년 7월 23일
 * 제작자 : 이재영
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import game.data.*;
import game.listener.PlayerListener;

import javax.swing.SwingUtilities;

public class FighterController {
	private FighterGUI gui = null;
	private PlayerPlane player = null;
	private List<EnemyPlane> arPlane = null;
	private List<Bullet> arBullet = null;
	
	private boolean isStopped = false;
	private boolean god_mod = false;
	private boolean isFire = false;
	private boolean boss3 = false;
	private int life = 6;
	private int score = 0;
	
	private static FighterController inst = null;
	public static FighterController getInstance() {
		if (inst == null)
			inst = new FighterController();
		return inst;
	}
	private FighterController() {
		gui = FighterGUI.getInstance();
		player = new PlayerPlane(gui.getWidth()/2, gui.getHeight() - 20);
		
		arPlane = new ArrayList<EnemyPlane>();
		arBullet = new ArrayList<Bullet>();
	}
	public void start() {
		EnemyPlane boss3Plane = null;
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				gui.addMouseMotionListener(new PlayerListener(player));
				gui.start();
			}
		});
		
		while (gui.getImage() == null) {
			Thread.yield();
		}
		
		resurraction();
		
		long start_time = System.currentTimeMillis();
		
		while (!isStopped) {
			try {
				long current_time = System.currentTimeMillis();
				
				//비행기 생성
				if (current_time - start_time <= 180000) {
					if ((int)(Math.random() * 300) == 0) {
						EnemyPlane plane = new EnemyPlane1((int)(Math.random() * gui.getWidth()), 20);
						arPlane.add(plane);
					}
				}
				else if (current_time - start_time <= 600000) {
					if ((int)(Math.random() * 500) == 0) {
						EnemyPlane plane = new EnemyPlane1((int)(Math.random() * gui.getWidth()), 20);
						arPlane.add(plane);
					}
					if ((int)(Math.random() * 300) == 0) {
						EnemyPlane plane = new EnemyPlane2((int)(Math.random() * gui.getWidth()), 20);
						arPlane.add(plane);
					}
					if ((int)(Math.random() * 10000) == 0) {
						EnemyPlane plane = new EnemyBoss2((int)(Math.random() * gui.getWidth()), 20);
						arPlane.add(plane);
					}
				}
				else {
					if ((int)(Math.random() * 700) == 0) {
						EnemyPlane plane = new EnemyPlane1((int)(Math.random() * gui.getWidth()), 20);
						arPlane.add(plane);
					}
					if ((int)(Math.random() * 500) == 0) {
						EnemyPlane plane = new EnemyPlane2((int)(Math.random() * gui.getWidth()), 20);
						arPlane.add(plane);
					}
					if ((int)(Math.random() * 300) == 0) {
						EnemyPlane plane = new EnemyPlane3((int)(Math.random() * gui.getWidth()), 20);
						arPlane.add(plane);
					}
					if ((int)(Math.random() * 10000) == 0) {
						EnemyPlane plane = new EnemyBoss2((int)(Math.random() * gui.getWidth()), 150);
						arPlane.add(plane);
					}
					if ((int)(Math.random() * 30000) == 0) {
						boss3Plane = new EnemyBoss3((int)(Math.random() * gui.getWidth()), 200);
						arPlane.add(boss3Plane);
						boss3 = true;
					}
				}
				if ((int)(Math.random() * 5000) == 0) {
					EnemyPlane plane = new EnemyBoss1((int)(Math.random() * gui.getWidth()), 100);
					arPlane.add(plane);
				}
				if (boss3 && (int)(Math.random() * 300) == 0) {
					EnemyPlane plane = new Interceptor(boss3Plane.getX() - boss3Plane.getSize()/2 + (int)(Math.random() * boss3Plane.getSize()),
							boss3Plane.getY());
					arPlane.add(plane);
				}
				
				//총알과 비행기 이동
				for (int i = 0; i < arPlane.size(); i++) {
					EnemyPlane plane = arPlane.get(i);
					plane.erase(gui.getImage().getGraphics());
					plane.move();
					plane.draw(gui.getImage().getGraphics());
					
					if (plane.getY() >= gui.getHeight()) {
						plane.erase(gui.getImage().getGraphics());
						arPlane.remove(plane);
					}
					
					//발포
					if (Math.random() <= 0.001) {
						for (int j = 0; j < plane.getGun().length; j++) {
							Bullet bullet = new Bullet(plane.getGun()[j].getX(), plane.getGun()[j].getY(), false);
							arBullet.add(bullet);
						}
					}
					
					//비행기끼리의 충돌 처리
					if (god_mod)
						continue;
					
					if (player.getX() <= plane.getX() + plane.getSize()/2 && player.getX() >= plane.getX() - plane.getSize()/2
							&& player.getY() <= plane.getY() + plane.getSize()/2 && player.getY() >= plane.getY() - plane.getSize()/2) {
						new Thread(new FireThread(plane.getX(), plane.getY())).start();
						new Thread(new FireThread(player.getX(), player.getY())).start();
						resurraction();
						plane.erase(gui.getImage().getGraphics());
						arPlane.remove(plane);
					}
				}
				
				for (int i = 0; i < arBullet.size(); i++) {
					Bullet bullet = arBullet.get(i);
					bullet.erase(gui.getImage().getGraphics());
					bullet.move();
					
					if (bullet.isPlayer())
						if (bullet.getY() <= 0)
							arBullet.remove(bullet);
					else
						if (bullet.getY() >= gui.getHeight())
							arBullet.remove(bullet);
					
					//비행기와 총알 충돌 처리
					if (bullet.isPlayer()) {
						for (int j = 0; j < arPlane.size(); j++) {
							EnemyPlane plane = arPlane.get(j);
							if (bullet.getX() <= plane.getX() + plane.getSize()/2 && bullet.getX() >= plane.getX() - plane.getSize()/2
								&& bullet.getY() <= plane.getY() + plane.getSize()/2 && bullet.getY() >= plane.getY() - plane.getSize()/2) {
								plane.bitted();
								new Thread(new BitThread(bullet.getX(), bullet.getY())).start();
								if (plane.getCount() <= 0) {
									new Thread(new FireThread(plane.getX(), plane.getY())).start();
									plane.erase(gui.getImage().getGraphics());
									gui.eraseScore(score, life);
									score += plane.getScore();
									if (plane == boss3Plane) boss3 = false;
									arPlane.remove(plane);
								}
								arBullet.remove(bullet);
							}
						}
					}
					else {
						if (god_mod)
							continue;
						
						if (bullet.getX() <= player.getX() + player.getSize()/2 && bullet.getX() >= player.getX() - player.getSize()/2
							&& bullet.getY() <= player.getY() + player.getSize()/2 && bullet.getY() >= player.getY() - player.getSize()/2) {
								new Thread(new FireThread(player.getX(), player.getY())).start();
								arBullet.remove(bullet);
								resurraction();
						}
					}
					bullet.draw(gui.getImage().getGraphics());
				}
				gui.drawScore(score, life);
				gui.repaint();
				
				if (life <= 0) {
					isStopped = true;
					gui.endMessage();
				}
				
				Thread.sleep(1000/800);
			}
			catch (InterruptedException ex) {  }
		}
		
		try {
			gui.stop();
			Thread.sleep(5000);
		}
		catch (InterruptedException ex) {  }
		
		System.exit(0);
	}
	
	public int getLife() { return life; }
	public int getScore() { return score; }
	public boolean isStopped() { return isStopped; }
	public void resurraction() {
		gui.eraseScore(score, life);
		life--;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					god_mod = true;
					Thread.sleep(3000);
					god_mod = false;
				}
				catch (InterruptedException ex) {  }
			}
		}).start();
	}
	public void fire() {
		if (isFire || isStopped)
			return;
		
		int x = player.getX();
		int y = player.getY();
		
		arBullet.add(new Bullet(x, y, true));
		isFire = true;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					isFire = false;
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
