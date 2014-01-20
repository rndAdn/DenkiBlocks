package gui;

import moteur.map.Map;
import moteur.player.Profile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import sun.tools.jar.resources.jar;


public class PlayLevel extends BasicGameState {
	public static final int ID = 5;
	public static Profile joueur;
	PlayLevel(){
	}

	public static void setLevel(int lvl){
		joueur.map = new Map(lvl)  ;
	}

	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Joueur :" + PlayLevel.joueur.name + " Niveau :" + PlayLevel.joueur.current_Level, 100, 25);
		for(int i =0;i<joueur.map.getHeight(); i++){
			for(int j =0;j<joueur.map.getWidth(); j++){
				joueur.map.getCases()[i][j].getImage_Bg().draw(j*32,i*32+50);
			}

		}
		for(int i =0;i<joueur.map.getHeight(); i++){
			for(int j =0;j<joueur.map.getWidth(); j++){
				if (joueur.map.getCases()[i][j].getImage_Fg() != null)joueur.map.getCases()[i][j].getImage_Fg().draw(j * 32, i * 32+50);
			}

		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();


		if (input.isKeyPressed(Keyboard.KEY_UP)) {
			joueur.map.moveUp();

		}
		if (input.isKeyPressed(Keyboard.KEY_DOWN)) {
			joueur.map.moveDown();
		}
		if (input.isKeyPressed(Keyboard.KEY_LEFT)) {
			joueur.map.moveLeft();
		}
		if (input.isKeyPressed(Keyboard.KEY_RIGHT)) {
			joueur.map.moveRight();

		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(Pause.ID);
		}
	}
}