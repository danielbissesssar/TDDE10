package game;
import java.awt.Color;
import java.net.MalformedURLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import graphics.GamingWindow;



public class MyFrame extends JFrame {

	public MyFrame() {
		super("The most intense gameplay ever!");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) throws MalformedURLException {
		JFrame f = new MyFrame();
//		GamingWindow gamingWindow = new GamingWindow();
//		this.add(gamingWindow);


		f.setSize(600, 400);
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		JLabel imageLabel = new JLabel();		
		ImageIcon ii = new ImageIcon("giphyDude.gif");
		ImageIcon bii = new ImageIcon("knas.png");
//		AlphaImageIcon aii = new AlphaImageIcon(ii,(float) 0.5);
        imageLabel.setIcon(ii);
        imageLabel.setBackground(Color.PINK);
        imageLabel.setForeground(Color.MAGENTA);
//        imageLabel.setOpaque(true);

        f.add(imageLabel, java.awt.BorderLayout.NORTH);
        
        f.setVisible(true);
//	      add(component);
	      
	}
	
}