package gui;

import moteur.player.Profile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class NiveauSuivant extends BasicGameState {
	public static final int ID = 8;

	public Titre titre;

	public Button button[] = new Button[4];
	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		titre = new Titre("",(container.getWidth()/2)-200/2,50);
		for (int i = 0;i<button.length;i++){
			button[i] = new Button("",(container.getWidth()/2)-200/2,150+(i*(75+15)));
		}

		button[1].setName("Recommencer");
		button[2].setName("Choix Niveau");
		button[3].setName("Menu");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0,0,container.getWidth(),container.getHeight());
		titre.setName("BRAVO "+ Fenetre.profileActif.name+"!");

		titre.render(g);
		for (Button aButton : button) {
			aButton.render(g);
		}
		button[0].setName("Level n°" + Fenetre.profileActif.current_Level);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		for (Button aButton : button) {
			aButton.update(container);
		}
		if (button[0].isClicked()) {
			Fenetre.profileActif.setLevel(Fenetre.profileActif.current_Level);
			game.enterState(PlayLevel.ID);
		}
		else if(button[1].isClicked()) {
			Fenetre.profileActif.setLevel(Fenetre.profileActif.current_Level-1);
			game.enterState(PlayLevel.ID);
		}
		else if(button[2].isClicked()) {
			game.enterState(ChoixNiveau.ID);
		}
		else if(button[3].isClicked()) {
			game.enterState(MenuGame.ID);
		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(StartGame.ID);
		}

	}
}