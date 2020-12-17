//Class responsible for creating Frames with the basic components
//Its possible to add more manually afterwards

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	//a, b and c refer to the RGB numbers that describe Colors in Java
	
  public MyFrame (String title, MyLabel label, MyButton button, MyPanel panel, ImageIcon icon, int width, int height, int a, int b, int c) {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
		this.setResizable(false); //prevent frame from being resized
		this.setSize(width,height); //sets the x-dimension, and y-dimension of frame
		this.setBackground(new Color (a, b,c));
		this.setLayout(null); //Lets us to place the components wherever in the frame
		this.setVisible(true); //make frame visible
		this.add(label);
		this.add(button);
		if (icon != null) { //if the icon exists
			this.setIconImage(icon.getImage()); //Sets the icon of the top right of the Frame (replaces the Java logo)
		}
	
	}
}
