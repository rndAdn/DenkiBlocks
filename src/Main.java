import gui.Fenetre;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	public static void main(String[] args){
		try{
			AppGameContainer container = new AppGameContainer(new Fenetre());
			container.setDisplayMode(900, 1024*9/16, false);
			container.setTargetFrameRate(60);
			container.start();
		}
		catch (SlickException e) {e.printStackTrace();}
	}
}
