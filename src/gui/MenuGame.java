package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MenuGame extends BasicGameState {
	public static final int ID = 3;
	public Button current_level;
	public Button levelChooserB;
	public Button highScoreB;
	public Button quitter;
	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.current_level = new Button("Level n°", 120,150,200,50);
		this.levelChooserB = new Button("Choix Niveau", 120,205,200,50);
		this.highScoreB = new Button("HighScore", 120,260,200,50);
		this.quitter = new Button("Quiter", 120,515,200,50);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Hello "+ PlayLevel.joueur.name+"!", 100, 50);
		//g.drawString("1. Level n°1 ", 120, 150);
		current_level.namer("Level n°" + PlayLevel.joueur.current_Level);
		current_level.render(g);
		levelChooserB.render(g);
		highScoreB.render(g);
		quitter.render(g);
		//g.drawString("2. LevelChooser ", 120, 250);
		//g.drawString("3. HighScore ", 120, 350);
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();
		current_level.update(container);
		levelChooserB.update(container);
		highScoreB.update(container);
		quitter.update(container);
		if (current_level.isClicked()) {
			PlayLevel.setLevel(PlayLevel.joueur.current_Level);
			game.enterState(PlayLevel.ID);
		}
		else if(levelChooserB.isClicked()) {
			PlayLevel.setLevel(2);
			game.enterState(ChoixNiveau.ID);
		}
		else if(highScoreB.isClicked()) {
			game.enterState(HighScore.ID);
		}
		else if(quitter.isClicked()) {
			System.out.println("Quit Game");
			System.exit(0);
		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(StartGame.ID);
		}

	}
}