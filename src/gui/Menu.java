package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Menu extends BasicGameState {
	public static final int ID = 3;
	@Override
	public int getID() {
		return ID;
	}
	private StateBasedGame game; // stored for later use

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Hello Menu", 100, 50);
		g.drawString("1. Level n°1 ", 120, 150);
		g.drawString("2. LevelChooser ", 120, 250);
		g.drawString("3. HighScore ", 120, 350);
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
			game.enterState(LevelChooser.ID);
		}
		else if(input.isKeyPressed(Keyboard.KEY_NUMPAD3)) {
			game.enterState(HighScore.ID);
		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			System.out.println("UP HighScore");
			game.enterState(Launch.ID);
		}

	}
}