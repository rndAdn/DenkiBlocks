package moteur.map;

public class Block extends Case{

	public Block() {
		super(2);
	}
	public Block(Block h,Block d,Block b,Block g) {
		super(2);
		this.droite = d;
		this.haut = h;
		this.bas = b;
		this.gauche = g;
	}

}
