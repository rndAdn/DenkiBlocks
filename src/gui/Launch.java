package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Launch extends BasicGameState {
	public static final int ID = 0;
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
		g.drawString("Launch ", 100, 50);
		g.drawString("1. Continuer ", 150, 150);
		g.drawString("2. Nouvelle Partie ", 150, 250);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();


		if (input.isKeyPressed(Keyboard.KEY_NUMPAD1)) {
			game.enterState(ProfilChooser.ID);
		}
		else if(input.isKeyPressed(Keyboard.KEY_NUMPAD2)) {
			game.enterState(NewProfil.ID);
		}
	}
}