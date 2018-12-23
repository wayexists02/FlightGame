package game.listener;

import game.FighterController;
import game.FighterGUI;
import game.data.PlayerPlane;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class PlayerListener extends MouseMotionAdapter {
	private PlayerPlane player = null;
	private FighterGUI gui = FighterGUI.getInstance();
	private FighterController controller = FighterController.getInstance();
	
	public PlayerListener(PlayerPlane player) {
		this.player = player;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (controller.isStopped())
			return;
		
		int x = e.getX();
		int y = e.getY();
		
		if (x <= 0 || x >= FighterGUI.WIDTH || y <= 0 || y >= FighterGUI.HEIGHT)
			return;
		
		player.erase(gui.getImage().getGraphics());
		player.setLocation(x, y);
		player.draw(gui.getImage().getGraphics());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		//fire bullet
		controller.fire();
		
		if (x <= 0 || x >= FighterGUI.WIDTH || y <= 0 || y >= FighterGUI.HEIGHT)
			return;
		
		player.erase(gui.getImage().getGraphics());
		player.setLocation(x, y);
		player.draw(gui.getImage().getGraphics());
	}
}
