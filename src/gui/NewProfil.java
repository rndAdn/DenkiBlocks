package gui;

import moteur.player.Profil;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class NewProfil extends BasicGameState {
	public static final int ID = 2;
	private Button play;
	private TextField textfield;

	private Titre titre;


	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		titre = new Titre("Nouveau Profile",(container.getWidth()/2)-200/2,50);
		textfield = new TextField(container, container.getDefaultFont(), 100, 100, 200, 30);
		play = new Button("PLAY",320,200);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0,0,container.getWidth(),container.getHeight());
		titre.render(g);
		textfield.render(container,g);
		play.render(g);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		play.update(container);

		if (play.isClicked()) {
			//TODO : Tester Si le nom de profile existe déjà
			Fenetre.profilActif = new Profil(textfield.getText());
			game.enterState(MenuGame.ID);
		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(StartGame.ID);
		}
	}
}