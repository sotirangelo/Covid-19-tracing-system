//Class responsible for creating Frames with the basic components
//Its possible to add more manually afterwards

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	//Class responsible for creating Frames with the basic components
	//Its possible to add more manually
	//a, b and c refer to the RGB numbers that describe Colors in Java
	public MyFrame (String title, MyLabel label, MyButton button, MyPanel panel, ImageIcon icon, int width, int height, int a, int b, int c) {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
		this.setResizable(false); //prevent frame from being resized
		this.setSize(width,height); //sets the x-dimension, and y-dimension of frame
		this.setBackground(new Color (a, b,c));
		this.setLayout(null);
		this.setVisible(true); //make frame visible
		if (label !=null)
			this.add(label);
		if (button !=null)
			this.add(button);
		if (panel != null)
			this.add(panel);
		if (icon != null) {
			this.setIconImage(icon.getImage()); //Sets the icon of the top right of the Frame (replaces the Java logo)
		}
	
	}
}
