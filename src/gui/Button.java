package gui;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tests.xml.Entity;

public class Button extends Entity {

	private Image image = null;
	private Image image_active = null;
	private Image renderImage;

	private Rectangle button;

	private boolean buttonDown = false;
	private boolean buttonReleased = false;


	private String text;
	private Font font = null;
	private java.awt.Font afont = new java.awt.Font("Serif", java.awt.Font.BOLD, 15);

	private float width = 250;
	private float height = 75;

	private Vector2f position;


	public Button (String text, float x, float y){
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

		font = new UnicodeFont(afont, afont.getSize(), afont.isBold(), afont.isItalic());
		this.text = text;
		this.position = new Vector2f(x, y);
		this.button = new Rectangle(position.x, position.y, width, height);
		this.renderImage = this.image.getSubImage(0, 0, this.image.getWidth(), this.image.getHeight() / 3);
	}

	public void update (GameContainer gc){
		buttonReleased = false;

		if (button.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())){
			renderImage = image_active;
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				buttonDown = true;
			}
			else{
				if (buttonDown){
					buttonDown = false;
					buttonReleased = true;
				}
			}
		}
		else{
			buttonDown = false;
			renderImage = image ;
		}

	}

	public void setBlockedImage(){
		try {
			this.image = new Image("data/images/menuNon.png") ;
			this.image_active = new Image("data/images/menuNon.png") ;
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render (Graphics gr){
		renderImage.draw(position.x, position.y, width, height);

		int marginw = ((int) width - getTextWidth(text, font)) / 2;
		int marginh = ((int) height - font.getHeight(text)) / 2;

		//gr.setFont(font);
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