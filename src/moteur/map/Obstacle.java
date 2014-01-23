package moteur.map;
public class Obstacle extends Case{


	public Obstacle() {
		super(2);
	}
	public Obstacle(Obstacle h,Obstacle d,Obstacle b,Obstacle g) {
		super(2);
		this.droite = d;
		this.haut = h;
		this.bas = b;
		this.gauche = g;
	}

}
