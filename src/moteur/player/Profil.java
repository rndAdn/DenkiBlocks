package moteur.player;

import gui.Fenetre;
import moteur.file.FileManager;
import moteur.map.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Profil {

	// TODO : Ajouter les highScores des maps

	public int nombre_de_niveau = 5;
	public Map map;
	public String name;
	public int current_Level;
	public int niveaux_debloque;
	public String[] color_themes = {"bleu","normal"};

	public Profil(String name){
		this.name = name;
		initialisation(name);

	}

	public Profil(){
		this.name = "";
		map = new Map(1);
		current_Level = 1;
		niveaux_debloque = 1;

	}


	public void initialisation(String name){
		File f = new File(Fenetre.PROFILE_FOLDER+name+".pfl");
		// Si le fichier de profil existe on le charge
		if(f.exists()) {
			Profil profil = FileManager.loadProfile(f);
			this.name = profil.name;
			this.current_Level = profil.current_Level;
			this.niveaux_debloque = profil.current_Level;
			this.map = profil.map;
		}
		else{
			// On cree le fichier du nouveau profil
			//TODO : verfification nom correct
			File destination = new File(Fenetre.PROFILE_FOLDER+name+".pfl");

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
			this.niveaux_debloque =1;
			this.map = new Map(1);
		}
		Map.color_themes = color_themes;
		Map.solImage =  FileManager.loadSolImg(color_themes[1]);
		Map.blockImages = FileManager.loadBlockImgT(color_themes[0]);
		Map.oblstacleImages= FileManager.loadObstacleImgT(color_themes[1]);


	}

	public boolean setLevel(int lvl){
		if (lvl > nombre_de_niveau) return false;
		this.map = new Map(lvl);
		return true;

	}




}
