package moteur.player;

import moteur.file.FileManager;
import moteur.map.Map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
		else{
			File destination = new File(FileManager.PROFILE_FOLDER_PATH+name+".pfl");

			BufferedWriter output = null;
			try {
				output = new BufferedWriter(new FileWriter(destination));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {

				assert output != null;
				output.write(name + "\r\n");
				output.write(1 + "\r\n");
				output.flush();

			} catch (IOException ioe) {
				System.out.println("erreur : " + ioe);
			}
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.name = name;
			this.current_Level =1;
			this.map = new Map(1);
		}
		// Si le profile existe on le charge
		// Sinon on creer le nouveau profil et son fichier


	}


}
