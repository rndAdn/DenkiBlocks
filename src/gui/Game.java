package gui;

import moteur.map.Map;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Game extends BasicGameState {
	public static final int ID = 1;
	public Map map;
	private int level;
	Game(int lvl){
		this.level = lvl;
	}

	@Override
	public int getID() {
		return ID;
	}
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		map = new Map(this.level);
		System.out.print("HELLO");
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Hello World", container.getWidth()/2, 5);
		for(int i =0;i<map.getHeight(); i++){
			for(int j =0;j<map.getWidth(); j++){
				map.getCases()[i][j].getImage_Bg().draw(j*(container.getWidth()/map.getWidth()),25+i*((container.getHeight()-25)/map.getHeight()),container.getWidth()/map.getWidth(),(container.getHeight()-25)/map.getHeight());
			}

		}
		for(int i =0;i<map.getHeight(); i++){
			for(int j =0;j<map.getWidth(); j++){
				if (map.getCases()[i][j].getImage_Fg() != null)map.getCases()[i][j].getImage_Fg().draw(j*(container.getWidth()/map.getWidth()),25+i*((container.getHeight()-25)/map.getHeight()),container.getWidth()/map.getWidth(),(container.getHeight()-25)/map.getHeight());
			}

		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// pour cette exemple, on a rien à mettre à jour.
		Input input = container.getInput();


		if (input.isKeyPressed(Keyboard.KEY_UP)) {
			System.out.println("UP");
			map.moveUp();
		}
		if (input.isKeyPressed(Keyboard.KEY_DOWN)) {
			System.out.println("DOWN");
			map.moveDown();
		}
		if (input.isKeyPressed(Keyboard.KEY_LEFT)) {
			System.out.println("LEFT");
			map.moveLeft();
		}
		if (input.isKeyPressed(Keyboard.KEY_RIGHT)) {
			System.out.println("RIGHT");
			map.moveRight();
		}
	}
}