import gui.Fenetre;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.awt.*;

public class Main {

	public static void main(String[] args){
		/*String fonts[] =GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		for ( int i = 0; i < fonts.length; i++ ){
			System.out.println(fonts[i]);
		}*/

		try{
			AppGameContainer container = new AppGameContainer(new Fenetre());
			container.setDisplayMode(1200, 1200*9/16, false);
			container.setVSync(true);
			container.setTargetFrameRate(100);
			container.start();
		}
		catch (SlickException e) {e.printStackTrace();}
	}
}
