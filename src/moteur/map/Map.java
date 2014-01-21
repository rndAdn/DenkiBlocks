package moteur.map;

import moteur.file.FileManager;

import java.util.ArrayList;

public class Map{

	private Case[][] cases;
	private int width;
	private int height;
	private String name;
	private Block[] blocksTab = new Block[5];

	public Map(){
		this.cases = null;
		this.width = 0;
		this.height = 0;
		this.name = "";
	}

	public Map(int level){
		initialisationMap(FileManager.LEVEL_FOLDER+level+".lvl");
	}
	public Map(String path){
		initialisationMap(FileManager.LEVEL_FOLDER+path+".lvl");
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

	private void loadImages(){
		/*Chargement du sol*/
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				this.getCases()[i][j].setImage_Bg(FileManager.loadBgImg());
			}

		}
		/*Chargement des blocks*/
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block){
					this.getCases()[i][j].setImage_Fg(FileManager.loadBlockImg(0, 0, 0, 0));
				}

			}

		}
		/*Chargement des obstacles*/
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Obstacle){
					this.getCases()[i][j].setImage_Fg(FileManager.loadObstacleImg(0, 0, 0, 0));
				}

			}

		}
		checkFusionBlocks();
		checkFusionObstacles();
	}

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
					int h = getCases()[i][j].haut instanceof Block ?1:0;
					int d = getCases()[i][j].droite instanceof Block ?1:0;
					int b = getCases()[i][j].bas instanceof Block ?1:0;
					int g = getCases()[i][j].gauche instanceof Block ?1:0;
					this.getCases()[i][j].setImage_Fg(FileManager.loadBlockImg(h, d, b, g));
				}

			}


		}
	}


	private void checkFusionObstacles(){
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
					int h = getCases()[i][j].haut instanceof Obstacle ?1:0;
					int d = getCases()[i][j].droite instanceof Obstacle ?1:0;
					int b = getCases()[i][j].bas instanceof Obstacle ?1:0;
					int g = getCases()[i][j].gauche instanceof Obstacle ?1:0;
					this.getCases()[i][j].setImage_Fg(FileManager.loadObstacleImg(h, d, b, g));
				}

			}


		}
	}

	/**
	 * Cette fonction verifie si tout les blocks on fusionnés
	 * @return true
	 */
	public boolean checkAllFusionne(){
		boolean seul = false;
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block && this.cases[i][j].bas == null && this.cases[i][j].haut == null && this.cases[i][j].gauche == null && this.cases[i][j].droite == null ) seul = true;
			}
		}
		return !seul;
	}

	private void canMoveDown(){
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				this.cases[i][j].moved = true;
			}
		}
		for(int i =this.getHeight()-1;i>=0; i--){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block && this.cases[i+1][j] instanceof Obstacle)this.cases[i][j].moved = false;
			}
		}
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block){
					boolean tmp = true;
					for (Block b : this.cases[i][j].listeBlock){
						  if (!b.moved) tmp = false;
					}
					if (!tmp){
						for (Block b : this.cases[i][j].listeBlock){
							b.moved = false;
						}
					}
				}
			}
		}

	}
	private void canMoveUp(){
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				this.cases[i][j].moved = true;
			}
		}
		for(int i =1;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block && this.cases[i-1][j] instanceof Obstacle)this.cases[i][j].moved = false;
			}
		}
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block){
					boolean tmp = true;
					for (Block b : this.cases[i][j].listeBlock){
						if (!b.moved) tmp = false;
					}
					if (!tmp){
						for (Block b : this.cases[i][j].listeBlock){
							b.moved = false;
						}
					}
				}
			}
		}

	}


	private void canMoveRight(){
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				this.cases[i][j].moved = true;
			}
		}
		for(int i =0;i<this.getHeight(); i++){
			for(int j =this.getWidth()-1;j>0; j--){
				if (this.cases[i][j] instanceof Block && this.cases[i][j+1] instanceof Obstacle)this.cases[i][j].moved = false;
			}
		}
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block){
					boolean tmp = true;
					for (Block b : this.cases[i][j].listeBlock){
						if (!b.moved) tmp = false;
					}
					if (!tmp){
						for (Block b : this.cases[i][j].listeBlock){
							b.moved = false;
						}
					}
				}
			}
		}

	}

	private void canMoveLeft(){
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				this.cases[i][j].moved = true;
			}
		}
		for(int i =0;i<this.getHeight(); i++){
			for(int j =1;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block && this.cases[i][j-1] instanceof Obstacle)this.cases[i][j].moved = false;
			}
		}
		for(int i =0;i<this.getHeight(); i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block){
					boolean tmp = true;
					for (Block b : this.cases[i][j].listeBlock){
						if (!b.moved) tmp = false;
					}
					if (!tmp){
						for (Block b : this.cases[i][j].listeBlock){
							b.moved = false;
						}
					}
				}
			}
		}

	}

	public boolean moveDown(){
		canMoveDown();
		for(int i =this.getHeight()-1;i>=0; i--){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block && this.cases[i][j].moved){
					this.cases[i+1][j] = this.cases[i][j];
					this.cases[i][j] = new Vide();
				}
			}
		}
		checkFusionBlocks();
		return true;
	}
	public boolean moveUp(){
		canMoveUp();
		for(int i =1;i<this.height; i++){
			for(int j =0;j<this.getWidth(); j++){
				if (this.cases[i][j] instanceof Block &&this.cases[i][j].moved){
					this.cases[i-1][j] = this.cases[i][j];
					this.cases[i][j] = new Vide();
				}
			}
		}
		checkFusionBlocks();
		return true;
	}
	public boolean moveRight(){
		canMoveRight();
		for(int i =1;i<this.height; i++){
			for(int j =this.getWidth()-1;j>0; j--){
				if (this.cases[i][j] instanceof Block && this.cases[i][j].moved){
					this.cases[i][j+1] = this.cases[i][j];
					this.cases[i][j] = new Vide();
				}
			}
		}
		checkFusionBlocks();
		return true;
	}
	public boolean moveLeft(){
		canMoveLeft();
		for(int i =this.getHeight()-1;i>0; i--){
			for(int j = 0;j<this.getWidth()-1; j++){
				if (this.cases[i][j] instanceof Block && this.cases[i][j].moved){
					this.cases[i][j-1] = this.cases[i][j];
					this.cases[i][j] = new Vide();
				}
			}
		}
		checkFusionBlocks();
		return true;
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
