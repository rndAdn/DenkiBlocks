package gui;

import moteur.player.Profil;
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
	Profil[] profilesJoueurs;
	public Titre titre;

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		titre = new Titre("Choix Profile",(container.getWidth()/2)-200/2,50);
		loadAllProfile();
		profiles = new Button[profilesJoueurs.length];

		for (int i = 0 ; i<profilesJoueurs.length;i++){
			profiles[i] = new Button(profilesJoueurs[i].name,(container.getWidth()/2)-100/2,100+(i*(50+5)));
			profiles[i].setWidthAndHeight(100, 50);
		}

	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Fenetre.image_bg.draw(0, 0, container.getWidth(), container.getHeight());
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
				Fenetre.profilActif = profilesJoueurs[i];
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

	private void loadAllProfile(){
		ArrayList<String> name = folderToAListe();
		profilesJoueurs = new Profil[name.size()];
		for (int i = 0 ; i<profilesJoueurs.length;i++){
			profilesJoueurs[i] = new Profil(name.get(i));
		}
	}

}