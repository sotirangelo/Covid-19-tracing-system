import javax.swing.*;

import java.awt.Color;
import java.awt.Font;

public class MyLabel extends JLabel {
	//Class responsible for creating Label objects
	
	// p1, p2 refer to the position of the label on the Frame
	// x, y refer to the size of the label
	// a, b, c, d, e and f refer to the RGB numbers that describe Colors in Java	 
	public MyLabel (String text,  int p1, int p2, int x, int y, int a, int b, int c, int d, int e, int f, String txtfont, int txtSize) { 
		super(text);
		this.setForeground(new Color (a, b, c));
		this.setBackground(new Color (d, e, f));
		this.setOpaque(true);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBounds(p1, p2, x, y);
		this.setFont(new Font(txtfont , Font.PLAIN, txtSize ));
	}

}
