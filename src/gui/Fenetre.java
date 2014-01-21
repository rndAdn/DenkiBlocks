package gui;

import moteur.player.Profile;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Fenetre extends StateBasedGame{
	private GameState[] jeu = {new StartGame(),new ChoixProfil(),new NewProfil(),new MenuGame(),new ChoixNiveau(), new PlayLevel(),new HighScore(),new Pause(),new NiveauSuivant()}; // le premier état du jeu (voir GameState.java)
	public static final String IMAGE_FOLDER = "data/images/";
	public static final String PROFILE_FOLDER = "data/profile/";
	public static final String LEVEL_FOLDER = "data/level/";
	public static final String MENU_FOLDER = "data/images/menu/";
	public static  Image image_bg;
	public static Profile profileActif;
	public Fenetre() {
		super("Denki Block");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException{

		if (container instanceof AppGameContainer) {
			image_bg = new Image("data/images/background.jpg");
			for (int i = 0; i< jeu.length;i++){
				addState(jeu[i]);
			}
			enterState(0);
			container.setShowFPS(true);
		}
	}


}