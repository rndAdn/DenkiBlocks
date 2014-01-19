package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class NewProfil extends BasicGameState {
	public static final int ID = 2;
	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		System.out.print("LevelChooser");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("New Profile", 100, 50);
		g.drawString("1. Play", 120, 200);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();


		if (input.isKeyPressed(Keyboard.KEY_NUMPAD1)) {
			game.enterState(Menu.ID);
		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(Launch.ID);
		}
	}
}