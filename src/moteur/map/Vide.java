package moteur.map;

import moteur.file.FileManager;

public class Vide extends Case{


	public Vide() {
		super(2);
		//
		// this.setImage_Bg(FileManager.loadSolImg("normal"));
	}
	public Vide(String theme) {
		super(2);

		this.setImage_Bg(FileManager.loadSolImg(theme));
	}
}
