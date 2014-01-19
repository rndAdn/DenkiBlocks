package gui;

import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Fenetre extends StateBasedGame{
	private GameState jeu; // le premier état du jeu (voir GameState.java)
	private AppGameContainer container; // le conteneur du jeu

	public Fenetre() {
		super("Mon premier jeu");
	} // le constructeur de la classe

	@Override
	public void initStatesList(GameContainer container) throws SlickException{

		if (container instanceof AppGameContainer) {
			this.container = (AppGameContainer) container; // on stocke le conteneur du jeu ! }

			jeu = new Game(1); //le jeu en lui-même !
			addState(jeu);
			container.setShowFPS(true); //on ne veut pas voir le FPS ?? mettre alors "false" ! addState(jeu); //on ajoute le GameState au conteneur !
		}
	}

	public static void main(String[] args){
		try{
			AppGameContainer container = new AppGameContainer(new Fenetre());
			container.setDisplayMode(1024, 1024*9/16, false); // fenêtre de 1280*768 fullscreen =false !!*
			container.setTargetFrameRate(60); // on règle le FrameRate
			container.start(); //on démarre le container
		}
		catch (SlickException e) {e.printStackTrace();} // l'exception de base de slick !!
	}
} // fin de classe