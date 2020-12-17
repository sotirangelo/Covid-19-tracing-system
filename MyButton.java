//THE BUTTON DOES NOTHING FOR THE TIME BEING! 

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MyButton extends JButton {
	//Class responsible for creating buttons on Frames or Panels. 
	
	// a, b, c, d, e, f, l, m and n refer to the RGB numbers that describe Colors in Java
	// The method setForeground sets the text's color
	
	public MyButton(int x, int y, int width, int lenght, String text, String txtFont, int txtSize, int m, int l , int n, int d, int e, int f,ImageIcon icon) {
		this.setBounds(x, y, width, lenght);
		this.setText(text);
		this.setFocusable (false);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font(txtFont, Font.PLAIN, txtSize));
		this.setBackground(new Color (m, l, n));
		this.setForeground(new Color (d , e, f));
		this.setBorder(BorderFactory.createEtchedBorder());//Added a border to make the button look nicer.
		this.setIcon(icon); // Puts an icon on the button
		
	}

}
