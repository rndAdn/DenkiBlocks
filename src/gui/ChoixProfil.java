package gui;

import moteur.file.FileManager;
import moteur.map.Map;
import moteur.player.Profile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class ChoixProfil extends BasicGameState {
	public static final int ID = 1;
	Button[] profiles;
	Profile[] joueur;

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		File f = new File(FileManager.PROFILE_FOLDER_PATH);
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		ArrayList<String> name = new ArrayList<>();
		for (File g : files){
			name.add(g.getName().substring(0,g.getName().length()-4));
		}
		Collections.sort(name);
		profiles = new Button[name.size()];
		joueur = new Profile[name.size()];
		for (int i = 0 ; i<profiles.length;i++){
			joueur[i] = new Profile(name.get(i));
		}
		for (int i = 0 ; i<joueur.length;i++){
			profiles[i] = new Button(joueur[i].name,120,150+(i*53),200,50);
		}

	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Choix Profile ", 100, 50);
		for (int i = 0 ; i<joueur.length;i++){
			profiles[i].render(g);
		}

	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();

		for (int i = 0 ; i<joueur.length;i++){
			profiles[i].update(container);
		}
		for (int i = 0 ; i<joueur.length;i++){
			if (profiles[i].isClicked()) {
				joueur[i].map = new Map(joueur[i].current_Level);
				PlayLevel.joueur = joueur[i];
				game.enterState(MenuGame.ID);
			}

		}



		if (input.isKeyPressed(Keyboard.KEY_NUMPAD1)) {

		}
		else if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(StartGame.ID);
		}

	}
}