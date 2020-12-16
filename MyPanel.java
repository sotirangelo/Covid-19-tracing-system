import java.awt.Color;
import javax.swing.JPanel;

//Class responsible for creating Panels in Frames.
public class MyPanel extends JPanel {
	//a, b and c refer to the RGB numbers that describe Colors in Java
	//x, y refer to the coordinates of the right top corner of the Panel
	public MyPanel(int a, int b, int c, int x, int y, int width, int height ) {
		this.setBackground(new Color (a, b, c));
		this.setBounds(x, y, width, height);
	}

}