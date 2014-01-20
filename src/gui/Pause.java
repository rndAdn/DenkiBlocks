package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Pause extends BasicGameState {
	public static final int ID = 7;
	public Button reprendre;
	public Button restartlevel;
	public Button menu;
	public Button quitter;
	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.reprendre = new Button("Reprendre", 120,150,200,50);
		this.restartlevel = new Button("Restart", 120,205,200,50);
		this.menu = new Button("Menu", 120,260,200,50);
		this.quitter = new Button("Quiter", 120,515,200,50);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Menu Pause "+ PlayLevel.joueur.name+"!", 100, 50);
		//g.drawString("1. Level n°1 ", 120, 150);

		reprendre.render(g);
		restartlevel.render(g);
		menu.render(g);
		quitter.render(g);
		//g.drawString("2. LevelChooser ", 120, 250);
		//g.drawString("3. HighScore ", 120, 350);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();
		reprendre.update(container);
		restartlevel.update(container);
		menu.update(container);
		quitter.update(container);
		if (reprendre.isClicked()) {
			game.enterState(PlayLevel.ID);
		}
		else if(restartlevel.isClicked()) {
			PlayLevel.setLevel(PlayLevel.joueur.current_Level);
			game.enterState(PlayLevel.ID);
		}
		else if(menu.isClicked()) {
			game.enterState(MenuGame.ID);
		}
		else if(quitter.isClicked()) {
			System.out.println("Quit Game");
			System.exit(0);
		}
		/*else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(PlayLevel.ID);
		}*/

	}
}