package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StartGame extends BasicGameState {
	public static final int ID = 0;

	public Button button[] = new Button[3];
	private final int tailleX = 250;
	private final int tailleY = 75;

	@Override
	public int getID() {
		return ID;
	}
	private StateBasedGame game; // stored for later use

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;

		for (int i = 0;i<button.length;i++){
			button[i] = new Button("",(container.getWidth()/2)-tailleX/2,150+(i*(tailleY+15)),tailleX,tailleY);
		}
		button[0].namer("Continuer");
		button[1].namer("Nouvelle Partie");
		button[2].namer("Quiter");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setBackground(new Color(1,140,22));
		g.drawString("Denki Block", 200, 50);
		for (int i = 0;i<button.length;i++){
			button[i].render(g);
		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();
		for (int i = 0;i<button.length;i++){
			button[i].update(container);
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