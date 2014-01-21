package gui;

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
	Profile[] profilesJoueurs;
	public Titre titre;

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		titre = new Titre("Choix Profile",(container.getWidth()/2)-200/2,50);
		ArrayList<String> name = folderToAListe();
		profiles = new Button[name.size()];
		profilesJoueurs = new Profile[name.size()];
		for (int i = 0 ; i<profilesJoueurs.length;i++){
			profilesJoueurs[i] = new Profile(name.get(i));
			profiles[i] = new Button(profilesJoueurs[i].name,(container.getWidth()/2)-100/2,100+(i*(50+5)));
			profiles[i].setWidthAndHeight(100, 50);
		}

	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0,0,container.getWidth(),container.getHeight());
		titre.render(g);
		for (int i = 0 ; i<profilesJoueurs.length;i++){
			profiles[i].render(g);
		}

	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();

		for (int i = 0 ; i<profilesJoueurs.length;i++){
			profiles[i].update(container);
		}
		for (int i = 0 ; i<profilesJoueurs.length;i++){
			if (profiles[i].isClicked()) {
				profilesJoueurs[i].map = new Map(profilesJoueurs[i].current_Level);
				PlayLevel.joueur = profilesJoueurs[i];
				game.enterState(MenuGame.ID);
			}

		}
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(StartGame.ID);
		}

	}

	private ArrayList<String> folderToAListe(){
		File f = new File(Fenetre.PROFILE_FOLDER);
		ArrayList<File> files = new ArrayList<>(Arrays.asList(f.listFiles()));
		ArrayList<String> tmp = new ArrayList<>();
		for (File g : files){
			tmp.add(g.getName().substring(0,g.getName().length()-4));

		}
		Collections.sort(tmp);
		return tmp;
	}	
}