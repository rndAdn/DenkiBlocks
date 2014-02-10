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

	private int nombre_de_niveau = 10;
	private Map map;
	private String name;
	private int current_Level;
	private int niveaux_debloque;
	private String[] color_themes = {"red","normal"};
	private int [] highScore = new int[10];

	public Profil(String name){
		this.name = name;
		initialisation(name);


	}

	public Profil(){
		this.name = "";
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
			this.color_themes = profil.color_themes;
			this.highScore = profil.highScore;

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
			this.highScore = new int[]{0};
		}



	}

	public boolean setLevel(int lvl){
		if (lvl > nombre_de_niveau) return false;
		this.map = new Map(lvl,color_themes);
		return true;

	}


	 public void saveHighScore(){
		String s = "";
		 for(int i = 0; i<highScore.length;i++){
			s+=highScore[i]+",";
		 }
		 try {
			 FileManager.changeHighScore(s,this);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }


	/**GET**/
	public int getNombre_de_niveau() {
		return nombre_de_niveau;
	}

	public Map getMap() {
		return map;
	}

	public String getName() {
		return name;
	}

	public int getCurrent_Level() {
		return current_Level;
	}

	public int getNiveaux_debloque() {
		return niveaux_debloque;
	}

	public String[] getColor_themes() {
		return color_themes;
	}

	public int[] getHighScore() {
		return highScore;
	}

	/**SET**/

	public void setHighscoreI(int i, int val){
		System.out.println(this.name+" i = "+i+" val = "+val);
		this.highScore[i-1] = val;
	}


	public void setNombre_de_niveau(int nombre_de_niveau) {
		this.nombre_de_niveau = nombre_de_niveau;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCurrent_Level(int current_Level) {
		this.current_Level = current_Level;
	}

	public void setNiveaux_debloque(int niveaux_debloque) {
		this.niveaux_debloque = niveaux_debloque;
	}

	public void setColor_themes(String[] color_themes) {
		this.color_themes = color_themes;
	}

	public void setHighScore(int[] highScore) {
		this.highScore = highScore;
	}
}
