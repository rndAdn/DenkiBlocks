package gui;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tests.xml.Entity;

import java.awt.*;

public class Button extends Entity {
	private Rectangle button;
	private boolean buttonDown = false;
	private boolean buttonReleased = false;
	Image image = null;
	Image image_active = null;
	Image image_click = null;
	java.awt.Font afont = new java.awt.Font("Serif", java.awt.Font.BOLD, 15);

	//    private Image image = new Image("button image");
	private Image renderImage;

	private String text;
	private UnicodeFont font = null;
//    private Font font = (button's font);

	private float width;
	private float height;
	private Vector2f position;

	public Button (String text, float x, float y, float width, float height){
		try {
			this.image = new Image("data/images/menu.png") ;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			this.image_active = new Image("data/images/menuactiver.png") ;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			this.image_click = new Image("data/images/menuclick.png") ;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		font = new UnicodeFont(afont, afont.getSize(), afont.isBold(), afont.isItalic());
		this.text = text;
		this.width = width;
		this.height = height;
		this.position = new Vector2f(x, y);
		this.button = new Rectangle(position.x, position.y, width, height);
		this.renderImage = this.image.getSubImage(0, 0, this.image.getWidth(), this.image.getHeight() / 3);
	}

	public void update (GameContainer gc){
		buttonReleased = false;

		if (button.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())){
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				buttonDown = true;
				renderImage = image_click ;
			}
			else{
				if (buttonDown){
					buttonDown = false;
					buttonReleased = true;
				}

				renderImage = image_active;
			}
		}
		else{
			buttonDown = false;
			renderImage = image ;
		}
	}

	public void render (Graphics gr){
		renderImage.draw(position.x, position.y, width, height);

		//int margin = ((int) width - getTextWidth(text, font)) / 2;

		//gr.setFont(font);
		//gr.setColor(Color.black);
		gr.drawString(text, button.getMinX() /*+ margin*/, button.getMinY() + 5);
	}

	public void namer(String s){
		text = s;
	}
	public boolean isClicked(){
		if (buttonReleased){
			buttonReleased = false;
			return true;
		}
		return false;
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