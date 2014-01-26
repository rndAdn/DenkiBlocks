package moteur.map;

import gui.Fenetre;
import moteur.file.FileManager;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public class Map{

	private Case[][] cases;
	private int width;
	private int height;
	private String name;
	private Block[] blocksTab = new Block[5];
	static public String[] color_themes;
	public static Image solImage;
	public static Image [] blockImages;
	public static Image [] oblstacleImages;

	public Map(){
		this.cases = null;
		this.width = 0;
		this.height = 0;
		this.name = "";

	}

	public Map(int level){
		initialisationMap(Fenetre.LEVEL_FOLDER+level+".lvl");
	}
	public Map(String path){
		initialisationMap(Fenetre.LEVEL_FOLDER+path+".lvl");
	}

	private void initialisationMap(String path){
		Map map = FileManager.loadMap(path);
		this.cases = map.cases;
		this.width = map.getWidth();
		this.height = map.getHeight();
		this.name = map.getName();
		loadImages();
		ArrayList<Block> liste = new ArrayList<>();
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if(this.cases[i][j] instanceof Block) {
					liste.add((Block)this.cases[i][j]);
				}

			}
		}
		blocksTab = liste.toArray(blocksTab);


	}

	/**
	 * Charge les images de chaque case
	 */
	private void loadImages(){
		if (solImage==null || blockImages == null|| oblstacleImages == null)return;

		/*Chargement du sol*/
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				this.getCases()[i][j].setImage_Bg(solImage);
			}

		}
		/*Chargement des blocks*/
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block){
					this.getCases()[i][j].setImage_Fg(blockImages[0]);
				}

			}

		}
		/*Chargement des obstacles*/
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Obstacle){
					this.getCases()[i][j].setImage_Fg(oblstacleImages[0]);
				}

			}

		}
		checkFusionBlocks();
		majImageObstacles();
	}

	/**
	 * Verifie si de nouveaux Blocks sont fusionnés
	 * met à jour les images des Blocks fusionés
	 */
	private void checkFusionBlocks(){
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block){
					if (i < this.getHeight()-1 && this.cases[i+1][j] instanceof Block){
						if (!this.cases[i][j].listeBlock.contains(this.cases[i+1][j])){
							this.cases[i][j].listeBlock.add((Block)this.cases[i+1][j]);
							this.cases[i+1][j].listeBlock = this.cases[i][j].listeBlock;
						}
						this.cases[i][j].bas = this.cases[i+1][j];
					}
					if (i > 0 && this.cases[i-1][j] instanceof Block){
						if (!this.cases[i][j].listeBlock.contains(this.cases[i-1][j])){
							this.cases[i][j].listeBlock.add((Block)this.cases[i-1][j]);
							this.cases[i-1][j].listeBlock = this.cases[i][j].listeBlock;
						}
						this.cases[i][j].haut = this.cases[i-1][j];
					}
					if (j < this.getWidth()-1 && this.cases[i][j+1] instanceof Block){
						if (!this.cases[i][j].listeBlock.contains(this.cases[i][j+1])){
							this.cases[i][j].listeBlock.add((Block)this.cases[i][j+1]);
							this.cases[i][j+1].listeBlock = this.cases[i][j].listeBlock;
						}
						this.cases[i][j].droite = this.cases[i][j+1];
					}
					if (j >0 && this.cases[i][j-1] instanceof Block){
						if (!this.cases[i][j].listeBlock.contains(this.cases[i][j-1])){
							this.cases[i][j].listeBlock.add((Block)this.cases[i][j-1]);
							this.cases[i][j-1].listeBlock = this.cases[i][j].listeBlock;
						}
						this.cases[i][j].gauche = this.cases[i][j-1];
					}

				}
			}
		}
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.getCases()[i][j] instanceof Block){
					int total=0;
					total += getCases()[i-1][j] instanceof Block ?8:0;
					total += getCases()[i][j+1] instanceof Block ?4:0;
					total += getCases()[i+1][j] instanceof Block ?2:0;
					total += getCases()[i][j-1] instanceof Block ?1:0;
					this.getCases()[i][j].setImage_Fg(blockImages[total]);
				}

			}


		}
	}

	/**
	 * Cette fonction met à jour les images des obstacles au chargepent du niveau
	 */
	private void majImageObstacles(){
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Obstacle){
					if (i < this.getHeight()-1 && this.cases[i+1][j] instanceof Obstacle){
						this.cases[i][j].bas = this.cases[i+1][j];
					}
					if (i > 0 && this.cases[i-1][j] instanceof Obstacle){
						this.cases[i][j].haut = this.cases[i-1][j];
					}
					if (j < this.getWidth()-1 && this.cases[i][j+1] instanceof Obstacle){
						this.cases[i][j].droite = this.cases[i][j+1];
					}
					if (j >0 && this.cases[i][j-1] instanceof Obstacle){
						this.cases[i][j].gauche = this.cases[i][j-1];
					}

				}
			}
		}
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.getCases()[i][j] instanceof Obstacle){
					int total=0;
					total += getCases()[i][j].haut instanceof Obstacle ?8:0;
					total += getCases()[i][j].droite instanceof Obstacle ?4:0;
					total += getCases()[i][j].bas instanceof Obstacle ?2:0;
					total += getCases()[i][j].gauche instanceof Obstacle ?1:0;
					this.getCases()[i][j].setImage_Fg(oblstacleImages[total]);
				}

			}


		}
	}

	/**
	 * Cette fonction verifie si tout les blocks on fusionnés
	 * @return true
	 */
	public boolean checkAllFusionne(){
		Block tmp = null;
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block && tmp == null){
					tmp = (Block)this.cases[i][j];
					continue;
				}
				else if (this.cases[i][j] instanceof Block && !tmp.listeBlock.contains(this.cases[i][j])) return false;
			}
		}
		return true;
	}



	/**
	 * Cette fonction deplace les Block sur la map dans une direction
	 * @param direction direction de deplacement des blocks (droite, gauche, haut, bas)
	 */
	public void moveBlock(String direction){
		switch (direction){
			case "droite" :
				Deplacement.moveRight(this, this.getWidth(), this.getHeight());
				break;
			case "gauche" :
				Deplacement.moveLeft(this, this.getWidth(), this.getHeight());
				break;
			case "haut" :
				Deplacement.moveUp(this, this.getWidth(), this.getHeight());
				break;
			case "bas" :
				Deplacement.moveDown(this, this.getWidth(), this.getHeight());
				break;

		}
		checkFusionBlocks();
	}


	/*GET*/

	public Case[][] getCases() {
		return cases;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	/*SET*/

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setName(String name) {
		this.name = name;
	}


}
