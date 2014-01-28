package gui;

import moteur.player.Profil;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class HighScore extends BasicGameState {
	public static final int ID = 6;

	public Titre titre;
	public static Profil[] ALLPROFILE;

	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		titre = new Titre("HighScore",(container.getWidth()/2)-200/2,50);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0,0,container.getWidth(),container.getHeight());
		for(int i = 0; i<ALLPROFILE.length; i++){
			g.drawString(ALLPROFILE[i].name, 20,100+(i*20));
			for(int j = 0; j<ALLPROFILE[i].highScore.length; j++){
				g.drawString(""+ALLPROFILE[i].highScore[j], 150+(j*50),100+(i*20));
			}
		}
		titre.render(g);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(MenuGame.ID);
		}
	}
}