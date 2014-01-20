package gui;

import moteur.player.Profile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class NewProfil extends BasicGameState {
	public static final int ID = 2;
	Button play;

	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		play = new Button("PLAY",120,200,200,50);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("New Profile", 100, 50);
		play.render(g);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();
		play.update(container);

		if (play.isClicked()) {
			PlayLevel.joueur = new Profile("rr");
			game.enterState(MenuGame.ID);
		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(StartGame.ID);
		}
	}
}