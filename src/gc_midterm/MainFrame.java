/**
 * 
 */
package gc_midterm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @author Mariah
 *
 */
public class MainFrame extends JFrame implements ActionListener {
	public MainFrame(String title) {
		super(title);

		// set layout manager
		setLayout(new BorderLayout());

		// create swing component
		final JTextArea textArea = new JTextArea();
		JButton button = new JButton("Click me");

		// add swing components to content pane
		Container c = getContentPane();

		c.add(textArea, BorderLayout.CENTER);
		c.add(button, BorderLayout.SOUTH);

		// add behavior
		button.addActionListener(new MyActionListener());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
