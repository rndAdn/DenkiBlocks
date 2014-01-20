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
import java.util.Collection;
import java.util.Collections;


public class ChoixNiveau extends BasicGameState {
	public static final int ID = 4;

	Button[] levelB;
	Map[] map= new Map[3];

	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		File f = new File(FileManager.LEVEL_FOLDER);
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		ArrayList<String> name = new ArrayList<>();

		for (File g : files){
			name.add(g.getName().substring(0,g.getName().length()-4));
		}
		Collections.sort(name);
		map = new Map[name.size()];
		levelB = new Button[name.size()];
		for (int i = 0 ; i<map.length;i++){
			map[i] = new Map(name.get(i));
			levelB[i] = new Button(map[i].getName(),120,150+(i*53),200,50);
		}
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Choix Niveau", 100, 50);
		for (int i = 0 ; i<map.length;i++){
			levelB[i].render(g);
		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();

		for (int i = 0 ; i<map.length;i++){
			levelB[i].update(container);
		}
		for (int i = 0 ; i<map.length;i++){
			if (levelB[i].isClicked()) {
				PlayLevel.setLevel(i+1);
				game.enterState(PlayLevel.ID);
			}

		}
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(MenuGame.ID);
		}
	}
}