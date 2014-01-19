package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class LevelChooser extends BasicGameState {
	public static final int ID = 4;
	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		System.out.print("LevelChooser");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("LevelChooser", 100, 50);
		g.drawString("1. Level n°1 ", 120, 150);
		g.drawString("2. Level n°2 ", 120, 250);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();

		if (input.isKeyPressed(Keyboard.KEY_NUMPAD1)) {
			Game.setLevel(1);
			game.enterState(Game.ID);
		}
		else if(input.isKeyPressed(Keyboard.KEY_NUMPAD2)) {
			Game.setLevel(2);
			game.enterState(Game.ID);
		}
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			System.out.println("UP HighScore");
			game.enterState(Launch.ID);
		}
	}
}