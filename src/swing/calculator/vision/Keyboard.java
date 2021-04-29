package swing.calculator.vision;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import swing.calculator.modelo.Memory;

@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener {
	
	private final Color DARK_GRAY_COLOR = new Color(68, 68, 68);
	private final Color LIGHT_GRAY_COLOR = new Color(99, 99, 99);
	private final Color ORANGE_COLOR = new Color(242, 163, 60);

	public Keyboard() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		c.gridwidth = 2;
		addButton("AC", DARK_GRAY_COLOR, c, 0, 0);
		c.gridwidth = 1;
		addButton("±", DARK_GRAY_COLOR, c, 2, 0);
		addButton("/", ORANGE_COLOR, c, 3, 0);
		
		addButton("7", LIGHT_GRAY_COLOR, c, 0, 1);
		addButton("8", LIGHT_GRAY_COLOR, c, 1, 1);
		addButton("9", LIGHT_GRAY_COLOR, c, 2, 1);
		addButton("*", ORANGE_COLOR, c, 3, 1);

		addButton("4", LIGHT_GRAY_COLOR, c, 0, 2);
		addButton("5", LIGHT_GRAY_COLOR, c, 1, 2);
		addButton("6", LIGHT_GRAY_COLOR, c, 2, 2);
		addButton("-", ORANGE_COLOR, c, 3, 2);

		addButton("1", LIGHT_GRAY_COLOR, c, 0, 3);
		addButton("2", LIGHT_GRAY_COLOR, c, 1, 3);
		addButton("3", LIGHT_GRAY_COLOR, c, 2, 3);
		addButton("+", ORANGE_COLOR, c, 3, 3);

		c.gridwidth = 2;
		addButton("0", LIGHT_GRAY_COLOR, c, 0, 4);
		c.gridwidth = 1;
		addButton(",", LIGHT_GRAY_COLOR, c, 2, 4);
		addButton("=", ORANGE_COLOR, c, 3, 4);
	}

	private void addButton(String text, Color color, 
			GridBagConstraints c, int x, int y) {
		
		c.gridx = x;
		c.gridy = y;
		Button button = new Button(text, color);
		button.addActionListener(this);
		add(button, c);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			Memory.getInstance().processCommand(button.getText());	
		}
	}
}
