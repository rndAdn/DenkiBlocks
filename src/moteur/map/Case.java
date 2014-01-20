package moteur.map;


import org.newdawn.slick.Image;

public abstract class Case {

	private Image image_bg;
	private Image image_fg;
	protected Case doite;
	protected Case haut;
	protected Case bas;
	protected Case gauche;
	public boolean movable = true;


	public Case(int type){
		// type : 0 case Vide
		// type : 1 case Obstacle
		// type : 2 case Vide & Block
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

	public boolean getCanMoveDroitRec(){
		if (this.doite == null) return this.isCanMove();
		else return (this.isCanMove() && this.doite.isCanMove() && this.getCanMoveBasRec() && this.getCanMoveGaucheRec() && this.getCanMoveHautRec());
	}

	public boolean getCanMoveBasRec(){
		if (this.bas == null) return this.isCanMove();
		else return (this.isCanMove() && this.bas.isCanMove());
	}
	public boolean getCanMoveGaucheRec(){
		if (this.gauche == null) return this.isCanMove();
		else return (this.isCanMove() && this.gauche.isCanMove());
	}
	public boolean getCanMoveHautRec(){
		if (this.haut == null) return this.isCanMove();
		else return (this.isCanMove() && this.haut.isCanMove());
	}


	public Image getImage_Bg(){return image_bg;}

	public Image getImage_Fg(){return image_fg;}
	public boolean isCanMove() {
		return movable;
	}


	/*SET*/

	public void setImage_Bg(Image image_bg){this.image_bg = image_bg;}

	public void setImage_Fg(Image image_fg){this.image_fg = image_fg;}
	public void setCanMove(boolean canMove) {
		this.movable = canMove;
	}
}
