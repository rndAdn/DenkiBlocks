package moteur.player;

import moteur.file.FileManager;
import moteur.map.Map;

import java.io.File;

public class Profile {

	public Map map;
	public String name;
	public int current_Level;

	public Profile(String name){
		this.name = name;
		initialisation(name);

	}
	public Profile(){
		this.name = "";
		map = new Map(1);
		current_Level = 1;

	}

	public void initialisation(String name){
		File f = new File(FileManager.PROFILE_FOLDER_PATH+name+".pfl");
		if(f.exists()) {
			Profile profil = FileManager.loadProfile(f);
			this.name = profil.name;
			this.current_Level = profil.current_Level;
			this.map = profil.map;
		}
		// Si le profile existe on le charge
		// Sinon on creer le nouveau profil et son fichier


	}


}
