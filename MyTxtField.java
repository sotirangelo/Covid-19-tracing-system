import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class MyTxtField extends JTextField{
	//Class responsible for creating text fields on frames or panels
	//a, b, c, d, e, f refer to the RGB numbers that describe Colors in Java
	public MyTxtField (int x, int y, int width, int lenght, String text, int columns, int a, int b, int c, String font, int fontSize, int d, int e, int f) {
		super(text, columns);
		this.setBounds(x, y, width, lenght);
		this.setBackground(new Color(a, b, c)); // background color
		this.setFont(new Font(font , Font.PLAIN, fontSize));
		this.setForeground (new Color(d,e,f)); // Font color
	}
	

}
