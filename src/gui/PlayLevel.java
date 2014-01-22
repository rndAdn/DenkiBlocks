package gui;

import moteur.map.Map;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PlayLevel extends BasicGameState {
	public static final int ID = 5;
	public Titre titre;
	Map currentMap;
	PlayLevel(){
	}



	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		titre = new Titre("",(container.getWidth()/2)-200/2,0);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		Fenetre.image_bg.draw(0, 0, container.getWidth(), container.getHeight());
		titre.setName("Joueur :" + Fenetre.profileActif.name + " Niveau :" + Fenetre.profileActif.max_level);
		titre.render(g);
		int xfirst = (container.getWidth()/2)-(currentMap.getWidth()/2)*32;
		int yfirst = (container.getHeight()/2)-(currentMap.getHeight()/2)*32;
		for(int i =0;i<currentMap.getHeight(); i++){
			for(int j =0;j<currentMap.getWidth(); j++){
				currentMap.getCases()[i][j].getImage_Bg().draw(xfirst+j*32,i*32+yfirst);
			}

		}
		for(int i =0;i<currentMap.getHeight(); i++){
			for(int j =0;j<currentMap.getWidth(); j++){
				if (currentMap.getCases()[i][j].getImage_Fg() != null)currentMap.getCases()[i][j].getImage_Fg().draw(xfirst+j*32,i*32+yfirst);
			}

		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();

		currentMap = Fenetre.profileActif.map;
		if (currentMap.checkAllFusionne()){

			Fenetre.profileActif.current_Level++;
			Fenetre.profileActif.max_level = Fenetre.profileActif.current_Level>Fenetre.profileActif.max_level?Fenetre.profileActif.current_Level:Fenetre.profileActif.max_level;
			System.out.println("profile curr "+Fenetre.profileActif.current_Level);
			Fenetre.profileActif.setLevel(Fenetre.profileActif.current_Level);
			game.enterState(NiveauSuivant.ID);
					
		}

		if (input.isKeyPressed(Keyboard.KEY_UP)) {
			currentMap.moveBlock("haut");

		}
		if (input.isKeyPressed(Keyboard.KEY_DOWN)) {
			currentMap.moveBlock("bas");
		}
		if (input.isKeyPressed(Keyboard.KEY_LEFT)) {
			currentMap.moveBlock("gauche");
		}
		if (input.isKeyPressed(Keyboard.KEY_RIGHT)) {
			currentMap.moveBlock("droite");

		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			container.setPaused(!container.isPaused());
			game.enterState(Pause.ID);
		}
	}
}