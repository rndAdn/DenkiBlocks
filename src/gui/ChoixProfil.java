package gui;

import moteur.map.Map;
import moteur.player.Profile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class ChoixProfil extends BasicGameState {
	public static final int ID = 1;
	Button[] profiles;
	Profile[] joueur = new Profile[3];

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		profiles = new Button[3];


		joueur[0] = new Profile("Renaud.A");
		joueur[1] = new Profile("Profil 2");
		joueur[2] = new Profile("Profil 3");
		for (int i = 0 ; i<joueur.length;i++){
			profiles[i] = new Button(joueur[i].name,120,150+(i*53),100,50);
		}

	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Choix Profile ", 100, 50);
		for (int i = 0 ; i<joueur.length;i++){
			profiles[i].render(g);
		}

	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();

		for (int i = 0 ; i<joueur.length;i++){
			profiles[i].update(container);
		}
		for (int i = 0 ; i<joueur.length;i++){
			if (profiles[i].isClicked()) {
				joueur[i].map = new Map(joueur[i].current_Level);
				PlayLevel.joueur = joueur[i];
				game.enterState(MenuGame.ID);
			}

		}



		if (input.isKeyPressed(Keyboard.KEY_NUMPAD1)) {

		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(StartGame.ID);
		}

	}
}