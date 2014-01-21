package gui;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tests.xml.Entity;

public class Titre extends Entity {
	private Rectangle button;

	private String text;
	java.awt.Font font = new java.awt.Font("URW Bookman L", java.awt.Font.BOLD, 25);
	TrueTypeFont trueTypeFont = new TrueTypeFont(font, true);

	private float width = 200;
	private float height = 50;

	private Vector2f position;


	public Titre (String text, float x, float y){
		this.text = text;
		this.position = new Vector2f(x, y);
		this.button = new Rectangle(position.x, position.y, width, height);
	}

	public void update (GameContainer gc){


	}

	public void render (Graphics gr){

		int marginw = ((int) width - getTextWidth(text, trueTypeFont)) / 2;
		int marginh = ((int) height - trueTypeFont.getHeight(text)) / 2;

		gr.setFont(trueTypeFont);
		gr.setColor(Color.black);
		gr.drawString(text, button.getMinX() + marginw, button.getMinY()+marginh);
	}

	public void setName(String s){
		text = s;
	}

	public void setWidthAndHeight(int x,int y){
		this.width = x;
		this.height = y;
		this.button = new Rectangle(position.x, position.y, x, y);
	}


	private int getTextWidth (String text, Font font)
	{
		int width = 0;

		for (char ch : text.toCharArray())
			if (ch == ' ')
				width += 2;
			else
				width += font.getWidth(String.valueOf(ch));

		return width;
	}
}