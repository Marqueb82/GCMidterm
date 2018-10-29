/**
 * 
 */
package gc_midterm;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Mariah
 *
 */
public class SwingApp {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainFrame("Grand Chirpus Library Terminal");

				frame.setSize(600, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

	}

}
