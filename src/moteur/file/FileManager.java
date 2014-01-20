package moteur.file;

import moteur.map.*;
import moteur.player.Profile;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileManager {
	public static final String BG_IMAGE_PATH = "data/images/";
	public static final String PROFILE_FOLDER_PATH = "data/profile/";
	public static final String LEVEL_FOLDER = "data/level/";

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


	/**
	 * Cette fonction charge un profile à partir de son nom (dossier profiles : "data/profile/"):
	 * @param file Profile a charger
	 * @return loaded_Profil
	 */

	public static Image loadBgImg() {
		Image img = null;
		try {
				img = new Image(BG_IMAGE_PATH + "sol.png");
			}catch (SlickException e) {
			e.printStackTrace();
		}
		return img ;
	}


	public static Image loadBlockImg(int h,int d,int b, int g) {
		Image img = null;
		try {
			img = new Image(BG_IMAGE_PATH + "/blocks/blue/" + h + "" + d + "" + b + "" + g + ".png");
		}catch (SlickException e) {
			e.printStackTrace();
		}
		return img ;
	}
	public static Image loadObstacleImg(int h,int d,int b, int g) {
		Image img = null;
		try {
			img = new Image(BG_IMAGE_PATH + "/obstacle/" + h + "" + d + "" + b + "" + g + ".png");
		}catch (SlickException e) {
			e.printStackTrace();
		}
		return img ;
	}

	public static Profile loadProfile(File file){
		ArrayList<String> profile_List = fileToArrayList(file);
		Profile profile = new Profile();
		profile.name = profile_List.get(0);
		int cur = 1;
		try{
			cur = Integer.parseInt(profile_List.get(1));
		}catch (NumberFormatException e){}
		profile.current_Level = cur;
		profile.map = new Map(cur);

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

	/*Test main open map*/

	/*public static void main(String [] args){
		String path = "data/level/1.lvl";
		int lvl = 1;
		Map map = new Map(path);

		/*for(int i =0;i<map.getHeight(); i++){
			for(int j =0;j<map.getWidth(); j++){
				System.out.print(map.getCases()[i][j]+" ");
			}
			System.out.println();

		}*/
		/*Fenetre jFrame = new Fenetre();
		jFrame.setVisible(true);
		Pan p;
		p = new Pan(map);
		jFrame.add(p);
		Scanner sc = new Scanner(System.in);
		while (sc.nextInt() != 0){
			//map.MoveDown();
			p.repaint();
		}


	}

	public static class Fenetre extends JFrame{

		public Fenetre() {
			initialisation();
		}


		private void initialisation(){
			this.setTitle("TortueGenial");
			setSize(32*21,32*21);


			setVisible(true);

		}
	}

	public static class Pan extends JPanel{
		public Map map;

		public Pan(Map map){this.map = map;}
		/*@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			for(int i =0;i<map.getHeight(); i++){
				for(int j =0;j<map.getWidth(); j++){
					//g2.drawImage(map.getCases()[i][j].getImage_Bg(),j*32,i*32,32,32,null);
				}

			}
			for(int i =0;i<map.getHeight(); i++){
				for(int j =0;j<map.getWidth(); j++){
					//if (map.getCases()[i][j].getImage_Fg() != null)g2.drawImage(map.getCases()[i][j].getImage_Fg(),j*32,i*32,32,32,null);
				}

			}


		}

	}  */
}
