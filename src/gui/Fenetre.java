package gui;

import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Fenetre extends StateBasedGame{
	private GameState[] jeu = {new StartGame(),new ChoixProfil(),new NewProfil(),new MenuGame(),new ChoixNiveau(), new PlayLevel(),new HighScore(),new Pause(),new NiveauSuivant()}; // le premier état du jeu (voir GameState.java)

	public static Image image_bg = null;
	public Fenetre() {
		super("Denki Block");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException{

		if (container instanceof AppGameContainer) {
			image_bg = new Image("data/images/gamebg.jpg");
			for (int i = 0; i< jeu.length;i++){
				addState(jeu[i]);
			}
			enterState(0);
			container.setShowFPS(true);
		}
	}


}