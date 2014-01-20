package gui;

import moteur.map.Map;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class ChoixNiveau extends BasicGameState {
	public static final int ID = 4;

	Button[] levelB;
	Map[] map= new Map[3];

	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		levelB = new Button[3];

		for (int i = 0 ; i<map.length;i++){
			map[i] = new Map(i+1);
			levelB[i] = new Button(map[i].getName(),120,150+(i*53),100,50);
		}
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Choix Niveau", 100, 50);
		for (int i = 0 ; i<map.length;i++){
			levelB[i].render(g);
		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();

		for (int i = 0 ; i<map.length;i++){
			levelB[i].update(container);
		}
		for (int i = 0 ; i<map.length;i++){
			if (levelB[i].isClicked()) {
				PlayLevel.setLevel(i+1);
				game.enterState(PlayLevel.ID);
			}

		}
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(MenuGame.ID);
		}
	}
}