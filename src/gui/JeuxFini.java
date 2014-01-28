package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class JeuxFini extends BasicGameState {
	public static final int ID = 9;

	public Titre titre;
	public Button button;

	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		titre = new Titre("",(container.getWidth()/2)-200/2,50);
		button = new Button("menu",(container.getWidth()/2)-200/2,150+75);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0,0,container.getWidth(),container.getHeight());
		titre.setName("BRAVO "+Fenetre.profilActif.name+" Vous avez fini");
		titre.render(g);
		button.render(g);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE) || button.isClicked()) {
			game.enterState(MenuGame.ID);
		}
	}
}