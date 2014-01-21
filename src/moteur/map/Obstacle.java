package moteur.map;
public class Obstacle extends Case{


	public Obstacle() {
		super(2);
		this.doite = null;
		this.haut = null;
		this.bas = null;
		this.gauche = null;
	}
	public Obstacle(Obstacle h,Obstacle d,Obstacle b,Obstacle g) {
		super(2);
		this.doite = d;
		this.haut = h;
		this.bas = b;
		this.gauche = g;
	}

}
