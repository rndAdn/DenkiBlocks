package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StartGame extends BasicGameState {
	public static final int ID = 0;

	public Button button[] = new Button[3];
	public Titre titre;

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		titre = new Titre("Denki Block",(container.getWidth()/2)-200/2,50);
		for (int i = 0;i<button.length;i++){
			button[i] = new Button("",(container.getWidth()/2)-250/2,150+(i*(75+15)));
		}
		button[0].setName("Continuer");
		button[1].setName("Nouvelle Partie");
		button[2].setName("Quiter");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0, 0, container.getWidth(), container.getHeight());
		titre.render(g);
		for (Button aButton : button) {
			aButton.render(g);
		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();
		for (Button aButton : button) {
			aButton.update(container);
		}

		if (button[0].isClicked()) {
			game.enterState(ChoixProfil.ID);
		}
		else if(button[1].isClicked()) {
			game.enterState(NewProfil.ID);
		}
		else if(button[2].isClicked()) {
			System.out.println("Quit Game");
			System.exit(0);
		}
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			System.out.println("Quit Game");
			System.exit(0);
		}
	}
}