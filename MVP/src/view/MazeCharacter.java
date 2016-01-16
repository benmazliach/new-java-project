package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class MazeCharacter<T> {
	
	private T character;
	private Image image;
	
	public MazeCharacter(T character) {
		this.character = character;
		this.image = null;
	}
	
	public MazeCharacter(Image image) {
		this.character = null;
		this.image = image;
	}
	
	public MazeCharacter(T character,Image image) {
		this.character = character;
		this.image = image;
	}
	
	public void draw(PaintEvent e,int srcX,int srcY,int srcWidth,int srcHeight,int destX,int destY,int destWidth,int destHeight)
	{
		e.gc.drawImage(image, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight);
	}

	public T getCharacter() {
		return character;
	}

	public void setCharacter(T character) {
		this.character = character;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getCharacter().equals(((MazeCharacter<T>)obj).getCharacter());
	}
	
	public boolean equals(MazeCharacter<T> obj){
		return this.getCharacter().equals(obj.getCharacter());
	}

	@Override
	public String toString() {
		return this.character.toString();
	}
}
