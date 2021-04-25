package swing;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Observer {

	public static void main(String[] args) {
		
		JFrame window = new JFrame("Observer");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(600, 200);
		window.setLayout(new FlowLayout());
		window.setLocationRelativeTo(null); // Centralize on the screen!
		
		JButton button = new JButton("To click!");
		window.add(button);

		button.addActionListener(e -> {
			System.out.println("Event occurred!!!");
		});
		
		window.setVisible(true);
	}
}
