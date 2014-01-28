package moteur.map;
public class Block extends Case{

	public Block() {
		super(2);

		this.doite = null;
		this.haut = null;
		this.bas = null;
		this.gauche = null;
=======
>>>>>>> gui
	}
	public Block(Block h,Block d,Block b,Block g) {
		super(2);
		this.droite = d;
		this.haut = h;
		this.bas = b;
		this.gauche = g;
	}

<<<<<<< HEAD
	/*GET*/

	public static int getNombre_Blocks() {
		return nombre_blocks;
	}

	public int getID() {
		return ID;
	}



/*SET*/

	public static void setNombre_blockspIncr() {
		Block.nombre_blocks++;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
=======
>>>>>>> gui
}
