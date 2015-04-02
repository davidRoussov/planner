package outline;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PersonalOrganiserGUI {	
	
	// the color for the background of the main output area
	Color colorOutputBackground = new Color(230,230,250);
	
	public static void main(String[] args) {
		new PersonalOrganiserGUI();
	}
	
	public PersonalOrganiserGUI() {
		
		JFrame frame = new JFrame();
		
		ListenForKey keyListener = new ListenForKey();
		frame.setFocusable(true);
		frame.addKeyListener(keyListener);
		
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Personal organiser");
		frame.getContentPane().setBackground(colorOutputBackground);
		
		frame.setLayout(new BorderLayout());
		
     	createMenuBar(); // create standard menu bar
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(169,169,169));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel options = new JPanel();
		options.setBackground(new Color(169,169,169));
		
		displayMainMenuButtons(options);
		
		options.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		options.setAlignmentY(JFrame.BOTTOM_ALIGNMENT);
		panel.add(options);
		
		frame.add(panel, BorderLayout.NORTH);
		frame.setVisible(true);
	}
	
	public void displayMainMenuButtons(JPanel mainPanel) {
		ListenForMouse mouseListener = new ListenForMouse();
		ListenToAction actionListener = new ListenToAction();
		
		String[] allButtonNames = {"Today", "Leaving", "Mistakes", "Activities", "Goals"};
		
		for (String buttonName : allButtonNames) {
			JButton button = styleMainMenuButtons(buttonName);
			button.setFocusable(false);
			button.addMouseListener(mouseListener);
			button.addActionListener(actionListener);
			mainPanel.add(button);
		}
	}

	/**
	 * This method creates a standard menu at the top of the window 
	 */
	public void createMenuBar() {
		ListenToAction menuListener = new ListenToAction();
		
		JMenuBar bar =  new JMenuBar();
     	
     	JMenu menu = new JMenu("File");
     	bar.add(menu);
     	
     	JMenuItem menuItemExit = new JMenuItem("Exit");
     	menu.add(menuItemExit);
     	menuItemExit.addActionListener(menuListener);
     	
     	JMenu menuHelp = new JMenu("Help");
     	bar.add(menuHelp);
     	
     	JMenuItem menuItemManual = new JMenuItem("Manual");
     	menuHelp.add(menuItemManual);
     	menuItemManual.addActionListener(menuListener);
     	frame.setJMenuBar(bar);
	}
	
	/**
	 * For editing and removing rows from the database a button is used to do this. This
	 * button is styled here.
	 * @param name The name of the JButton needed
	 * @param buttonIdName The name of the button we need to set
	 * @param buttonId the id of the button that gets appended to buttonIdName
	 * @return a styled, named JButton
	 */
	private JButton styleUpdateButtons(String name, String buttonIdName, int buttonId) {
		JButton button = new JButton(name);
		button.setName(buttonIdName + buttonId);
		button.setContentAreaFilled(false);
		button.setRequestFocusEnabled(false);
		button.addActionListener(new ListenToAction());
		return button;
	}
	
	/**
	 * Styling for the buttons that appear in the sub-menu
	 * @param name the name that the button will have
	 * @return a styled JButton
	 */
	private JButton styleSubMenuButton(String name) {
		JButton button = new JButton(name);
		
		button.setFont(fontForMenu);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(110,30));
		
		return button;
	}
	
	/**
	 * Styling for the buttons that appear in the main menu
	 * @param name the name that the button will have
	 * @return a styled JButton
	 */
	private JButton styleMainMenuButtons(String name) {
		JButton button = new JButton(name);
		
		button.setFont(fontForMenu);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setPreferredSize(new Dimension(80,30));
		
		return button;
	}
	
	/**
	 * Creates a new JFrame and hence window that contains a string that describes 
	 * how to use the application
	 */
	private void outputManual() {
		JFrame manualFrame = new JFrame();
		manualFrame.setTitle("Manual");
		manualFrame.setLocationRelativeTo(menuHelp);
		
		JPanel manualPanel = new JPanel();
		
		ListenForKey keyListener = new ListenForKey();
		manualFrame.setFocusable(true);
		manualFrame.addKeyListener(keyListener);
		
		String manual = "<html><h3>How to use this program</h3>"
				+ "- use the top menu bar to select which feature you want to use<br>"
				+ "- information will show up on the screen correponding to which feature you chose<br>"
				+ "- you can select what you want to do with the output by clicking the add/remove/edit buttons on the menu<br>"
				+ "- you can strike out to do list items by clicking on them, and you can undo that by clicking on them again<br>"
				+ "- application does not have scrolling capacbilities yet"
				+ "<h3>Shortcuts</h3>"
				+ "- Press ESC to quit prorgam<br>"
				+ "- Press c to see your contacts<br>"
				+ "- Press t to see your to-do list<br>"
				+ "- Press a to add<br>"
				+ "- Press r to remove<br>"
				+ "- Press e to edit</html>";
		
		JLabel manualLabel = new JLabel(manual);
		manualLabel.setBorder(new EmptyBorder(10,10,10,10));
		manualPanel.add(manualLabel);
		
		manualFrame.add(manualPanel);
		manualFrame.pack();
		manualFrame.setVisible(true);
	}
	
	/*
	 * This listens to action events that then calls the relevant method for it
	 */
	private class ListenToAction implements ActionListener {

		/**
		 * When buttons are clicked, the identity of the button is determined and
		 * then the appropriate method is called to implement what the button represents
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == menuItemExit) {
				System.exit(0);
			}
			else if (e.getSource() == menuItemManual) {
				outputManual();
			}
		}		
	}
	
	/**
	 * This listens to mouse events and then calls the appropriate
	 * code based on which button is clicked.
	 */
	private class ListenForMouse implements MouseListener {

		@SuppressWarnings("unchecked")
		public void mouseClicked(MouseEvent e) {	
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/*
	 * This listens to keys pressed in relation to the main frame
	 */
	private class ListenForKey implements KeyListener {

		@Override
		public void keyPressed(KeyEvent ke) {
		}

		@Override
		public void keyReleased(KeyEvent ke) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent ke) {
			// TODO Auto-generated method stub
			
		}
		
	}

}