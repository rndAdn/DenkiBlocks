package gui;

import moteur.file.FileManager;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class ChoixNiveau extends BasicGameState {
	public static final int ID = 4;

	Button[] levelB;
	String[] allMapTab;

	@Override
	public int getID() {
		return ID;
	}


	public void init(GameContainer container, StateBasedGame game) throws SlickException {


		ArrayList<String> nomLevel = folderToAListe();

		levelB = new Button[nomLevel.size()];
		allMapTab = new String[nomLevel.size()];
		for (int i = 0 ; i<allMapTab.length;i++){
			allMapTab[i] = nomLevel.get(i);
			levelB[i] = new Button("Niveau "+allMapTab[i],(container.getWidth()/2)-100/2,100+(i*(50+5)));
			levelB[i].setWidthAndHeight(100, 50);
		}
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0,0,container.getWidth(),container.getHeight());
		g.drawString("Choix Niveau", 100, 50);
		for (int i = 0 ; i<allMapTab.length;i++){
			if (i+1 > PlayLevel.joueur.current_Level){
				levelB[i].setBlockedImage();
			}
			levelB[i].render(g);
		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();

		for (int i = 0 ; i<allMapTab.length;i++){
			levelB[i].update(container);
		}
		for (int i = 0 ; i<allMapTab.length;i++){
			if (levelB[i].isClicked() && (i+1)<= PlayLevel.joueur.current_Level) {
				PlayLevel.setLevel(i+1);
				game.enterState(PlayLevel.ID);
			}

		}
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(MenuGame.ID);
		}
	}

	private ArrayList<String> folderToAListe(){
		File f = new File(FileManager.LEVEL_FOLDER);
		ArrayList<File> files = new ArrayList<>(Arrays.asList(f.listFiles()));
		ArrayList<String> tmp = new ArrayList<>();
		for (File g : files){
			tmp.add(g.getName().substring(0,g.getName().length()-4));

		}
		Collections.sort(tmp);
		return tmp;
	}
}