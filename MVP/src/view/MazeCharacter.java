package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class MazeCharacter {
	
	private Position character;
	private Image image;
	
	public MazeCharacter(Position character,Image image) {
		this.character = character;
		this.image = image;
	}
	
	public void draw(PaintEvent e)
	{
		
	}

	public Position getCharacter() {
		return character;
	}

	public void setCharacter(Position character) {
		this.character = character;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
