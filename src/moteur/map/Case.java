package moteur.map;


import org.newdawn.slick.Image;

import java.util.ArrayList;

public abstract class Case {

	private Image image_bg;
	private Image image_fg;
	protected ArrayList<Block> listeBlock;
	protected Case droite;
	protected Case haut;
	protected Case bas;
	protected Case gauche;
	public boolean moved = false;


	public Case(int type){
		listeBlock = new ArrayList<>();
		initialisationCase(type);

	}

	private void initialisationCase(int type){
		switch (type){
			case 0 :
				this.image_bg = null;
				this.image_fg = null;
				break;
			case 1 :
				this.image_bg = null;
				this.image_fg = null;

				break;
			case 2 :
				this.image_bg = null;
				this.image_bg = null;
				Block.setNombre_blockspIncr();
				break;
		}
	}

	/*GET*/

	public Image getImage_Bg(){return image_bg;}

	public Image getImage_Fg(){return image_fg;}

	/*SET*/

	public void setImage_Bg(Image image_bg){this.image_bg = image_bg;}

	public void setImage_Fg(Image image_fg){this.image_fg = image_fg;}
}
