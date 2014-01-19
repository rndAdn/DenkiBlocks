package moteur.map;


import moteur.file.FileManager;

public class Vide extends Case{


	public Vide() {
		super(2);
		this.setImage_Bg(FileManager.loadBgImg());
	}
}
