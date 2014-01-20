package moteur.player;

import moteur.map.Map;

public class Profile {

	public Map map;
	public String name;
	public int current_Level = 3;

	public Profile(String name){
		this.name = name;
		initialisation(name);

	}

	public void initialisation(String name){
		// Si le profile existe on le charge
		// Sinon on creer le nouveau profil et son fichier


	}


}
