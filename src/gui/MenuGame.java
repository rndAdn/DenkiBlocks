package gui;

import moteur.player.Profile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MenuGame extends BasicGameState {
	public static final int ID = 3;

	public Button button[] = new Button[4];
	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
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
		g.drawString("Hello "+ PlayLevel.joueur.name+"!", 100, 50);
		for (Button aButton : button) {
			aButton.render(g);
		}
		button[0].setName("Level n°" + PlayLevel.joueur.current_Level);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		for (Button aButton : button) {
			aButton.update(container);
		}
		if (button[0].isClicked()) {
			PlayLevel.setLevel(PlayLevel.joueur.current_Level);
			game.enterState(PlayLevel.ID);
		}
		else if(button[1].isClicked()) {
			PlayLevel.setLevel(2);
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