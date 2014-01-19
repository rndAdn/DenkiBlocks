package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class HighScore extends BasicGameState {
	public static final int ID = 6;
	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		System.out.print("HighScore");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Hello HighScore", 100, 50);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();


		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			System.out.println("UP HighScore");
			game.enterState(Launch.ID);
		}
	}
}