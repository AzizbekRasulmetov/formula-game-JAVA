// Student : RASULMETOV AZIZBEK
// Subject : System Implementation
// ID : 201953082052
// Game Project
// Formula 1

package game;
import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Need For Speed");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setSize(1100, 600);
		frame.add(new Road());
		frame.setVisible(true);
		
		
		
	}

}
