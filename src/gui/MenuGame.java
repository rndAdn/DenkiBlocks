package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MenuGame extends BasicGameState {
	public static final int ID = 3;

	private Titre titre;

	private Button button[] = new Button[4];
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
		button[1].setName("Choix Niveau");
		button[2].setName("HighScore");
		button[3].setName("Quiter");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0,0,container.getWidth(),container.getHeight());
		titre.setName("Hello "+ Fenetre.profilActif.getName()+"!");

		titre.render(g);
		for (Button aButton : button) {
			aButton.render(g);
		}
		button[0].setName("Level n°" + Fenetre.profilActif.getCurrent_Level());
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		for (Button aButton : button) {
			aButton.update(container);
		}
		if (button[0].isClicked()) {
			Fenetre.profilActif.setLevel(Fenetre.profilActif.getCurrent_Level());
			game.enterState(PlayLevel.ID);
		}
		else if(button[1].isClicked()) {
			game.enterState(ChoixNiveau.ID);
		}
		else if(button[2].isClicked()) {
			game.enterState(HighScore.ID);
		}
		else if(button[3].isClicked()) {
			System.out.println("Quit Game");
			System.exit(0);
		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(StartGame.ID);
		}

	}
}