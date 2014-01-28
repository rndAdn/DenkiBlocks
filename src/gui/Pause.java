package gui;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Pause extends BasicGameState {
	public static final int ID = 7;
	public Button button[] = new Button[4];
	public Titre titre;
	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		titre = new Titre("Pause",(container.getWidth()/2)-200/2,50);
		for (int i = 0;i<button.length;i++){
			button[i] = new Button("",(container.getWidth()/2)-200/2,150+(i*(75+15)));
		}
		button[0].setName("Reprendre");
		button[1].setName("Restart");
		button[2].setName("Menu");
		button[3].setName("Quiter");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0,0,container.getWidth(),container.getHeight());
		titre.render(g);
		for (Button aButton : button) {
			aButton.render(g);
		}

	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		for (Button aButton : button) {
			aButton.update(container);
		}
		if (button[0].isClicked()) {
			game.enterState(PlayLevel.ID);
		}
		else if(button[1].isClicked()) {
			Fenetre.profilActif.setLevel(Fenetre.profilActif.current_Level);
			game.enterState(PlayLevel.ID);
		}
		else if(button[2].isClicked()) {
			game.enterState(MenuGame.ID);
		}
		else if(button[3].isClicked()) {
			System.out.println("Quit Game");
			System.exit(0);
		}

	}
}