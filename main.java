import javax.swing.JFrame;
import javax.swing.JViewport;

public class main {

	public static void main(String[] args) {
		JFrame frame= new JFrame();
		DisplayGame panel= new DisplayGame();
		frame.setVisible(true);
		frame.add(panel);
		frame.setSize(1000, 800);
		//frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

	}

}
