package moteur.file;

import gui.Fenetre;
import moteur.map.*;
import moteur.player.Profil;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.util.ArrayList;


public class FileManager {

	/**
	 * Cette fonction charge le fichier dans une ArrayList :
	 * @param path Fichier a charger
	 * @return ArrayList_File
	 */
	private static ArrayList<String> fileToArrayList(String path){
		BufferedReader reader;
		ArrayList<String> list  = new ArrayList<>();
		File file= new File(path);
		String ligne;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("error fichier 'FileManager -> fileToArrayList' initialisation reader");
			return null;
		}
		do{
			try {
				ligne = reader.readLine();
			} catch (IOException e) {
				System.err.println("error fichier 'FileManager -> fileToArrayList' readline");
				return null;
			}
			if (ligne != null && !(ligne.startsWith("#") ||ligne.equalsIgnoreCase("")))list.add(ligne.trim());
		} while(ligne != null);
		return list;
	}

	/**
	 * Cette fonction charge le fichier dans une ArrayList :
	 * @param file Fichier a charger
	 * @return ArrayList_File
	 */
	private static ArrayList<String> fileToArrayList(File file){
		BufferedReader reader;
		ArrayList<String> list  = new ArrayList<>();
		String ligne;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("error fichier 'FileManager -> fileToArrayList' initialisation reader");
			return null;
		}
		do{
			try {
				ligne = reader.readLine();
			} catch (IOException e) {
				System.err.println("error fichier 'FileManager -> fileToArrayList' readline");
				return null;
			}
			if (ligne != null && !(ligne.startsWith("#") ||ligne.equalsIgnoreCase("")))list.add(ligne.trim());
		} while(ligne != null);
		return list;
	}




	public static Image loadSolImg(String theme_decor) {
		Image img = null;
		try {
				img = new Image(Fenetre.IMAGE_FOLDER+ "decors/" +theme_decor+ "/sol.png");
			}catch (SlickException e) {
			e.printStackTrace();
		}
		return img ;
	}



	public static Image[] loadBlockImgT(String theme_block) {
		Image[] img = new Image[16];

		for (int i = 0;i<16;i++){
			String name = Integer.toBinaryString(i);
			while (name.length()<4)name = "0"+name;
			try {
				img[i] = new Image(Fenetre.IMAGE_FOLDER + "blocks/"+theme_block+"/"+name+".png");
			}catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return img ;
	}

	public static Image[] loadObstacleImgT(String theme_decor) {
		Image[] img = new Image[16];

		for (int i = 0;i<16;i++){
			String name = Integer.toBinaryString(i);
			while (name.length()<4)name = "0"+name;
			try {
				img[i] = new Image(Fenetre.IMAGE_FOLDER + "decors/"+theme_decor+ "/obstacle/"+name+".png");
			}catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return img ;
	}




	/**
	 * Cette fonction charge un profile à partir de son nom (dossier profiles : "data/profile/"):
	 * @param file Profile a charger
	 * @return loaded_Profil
	 */
	public static Profil loadProfile(File file){

		//TODO : Ajouter la lecture des highScore
		ArrayList<String> profile_List = fileToArrayList(file);
		Profil profile = new Profil();
		profile.name = profile_List.get(0);
		int cur = 1;
		try{
			cur = Integer.parseInt(profile_List.get(1));
		}catch (NumberFormatException ignored){}
		profile.current_Level = cur;
		profile.niveaux_debloque = cur;
		String [] highscoreStr=profile_List.get(2).split(",");
		int [] highscoreInt = new int[highscoreStr.length];
		for (int i = 0; i<highscoreStr.length;i++){
			highscoreInt[i] = Integer.parseInt(highscoreStr[i]);
		}
		profile.highScore = highscoreInt;
		String []theme = profile_List.get(3).split(",");
		profile.color_themes = theme;
		return profile;
	}


	/**
	 * Cette fonction charge un niveau à partir de son chemin :
	 * @param path Niveau a charger
	 * @return loaded_Map
	 */
	public static Map loadMap(String path){
		Map map = new Map();

		ArrayList<String> map_List = fileToArrayList(path);

		map.setName(map_List.get(0));

		String size[] = map_List.get(1).split(" ");

		if (size.length !=2){
			System.err.println("error fichier 'FileManager -> loadMap' initialisation reader");
			return null;
		}
		Case[][] tmp_Cases;
		try{
			map.setHeight(Integer.parseInt(size[0]));
			map.setWidth(Integer.parseInt(size[1]));

		}catch (NumberFormatException e){
			System.err.println("error fichier 'FileManager -> loadMap' parsInt width & height");
			return null;
		}

		ArrayList<String> subMap = new ArrayList<>(map_List.subList(2,map.getHeight()+2));
		tmp_Cases = arrayToCases(subMap);
		map.setCases(tmp_Cases);

		return map;
	}


	private static Case[][] arrayToCases(ArrayList<String> liste){

		Case casesTab[][] = new Case[liste.size()][];
		for(int i =0;i<liste.size(); i++){
			String [] tmp = liste.get(i).split(" ");
			casesTab[i] = new Case[tmp.length];
			for(int j =0;j<casesTab[i].length; j++){
				switch (Integer.parseInt(tmp[j])){
					case 0:
						casesTab[i][j] = new Vide();
						break;
					case 1:
						casesTab[i][j] = new Obstacle();
						break ;
					case 2:
						casesTab[i][j] = new Block();
						break;
				}

			}

		}

		return casesTab;

	}
}
