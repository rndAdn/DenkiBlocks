package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StartGame extends BasicGameState {
	public static final int ID = 0;

	public Button continuer;
	public Button newgame;
	public Button quitter;

	@Override
	public int getID() {
		return ID;
	}
	private StateBasedGame game; // stored for later use

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.continuer = new Button("Continuer", 120,150,100,50);
		this.newgame = new Button("Nouvelle Partie", 120,205,100,50);
		this.quitter = new Button("Quiter", 120,515,100,50);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Denki Block", 100, 50);
		continuer.render(g);
		newgame.render(g);
		quitter.render(g);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();
		continuer.update(container);
		newgame.update(container);
		quitter.update(container);

		if (continuer.isClicked()) {
			game.enterState(ChoixProfil.ID);
		}
		else if(newgame.isClicked()) {
			game.enterState(NewProfil.ID);
		}
		else if(quitter.isClicked()) {
			System.out.println("Quit Game");
			System.exit(0);
		}
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			System.out.println("Quit Game");
			System.exit(0);
		}
	}
}