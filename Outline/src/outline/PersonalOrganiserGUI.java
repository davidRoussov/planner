package outline;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PersonalOrganiserGUI {	
	
	JFrame frame;
	JPanel panel;
	
	// the features of the software
	public static String[] mainFeatures = {"Today", "Leaving", "Mistakes", "Activities", "Goals", "To-learn"};
	
	public static void main(String[] args) {
		new PersonalOrganiserGUI();
	}
	
	public PersonalOrganiserGUI() {
		
		frame = new JFrame();
		
		frame.setFocusable(true);
		frame.setSize(1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Personal organiser");
		frame.getContentPane().setBackground(Style.colorOutputBackground);
		
		frame.setLayout(new BorderLayout());
		
     	createMenuBar(); // create standard menu bar
		
		panel = new JPanel();
		panel.setBackground(new Color(169,169,169));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel options = new JPanel();
		options.setName("options");
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
		ListenToAction actionListener = new ListenToAction(frame, panel);
		
		for (String buttonName : mainFeatures) {
			JButton button = Style.styleMainMenuButtons(buttonName);
			button.setName(buttonName);
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
		ListenToAction menuListener = new ListenToAction(frame, panel);
		
		JMenuBar bar =  new JMenuBar();
     	
     	JMenu menu = new JMenu("File");
     	bar.add(menu);
     	
     	JMenuItem menuItemExit = new JMenuItem("Exit");
     	menuItemExit.setName("menuItemExit");
     	menu.add(menuItemExit);
     	menuItemExit.addActionListener(menuListener);
     	
     	JMenu menuHelp = new JMenu("Help");
     	bar.add(menuHelp);
     	
     	JMenuItem menuItemManual = new JMenuItem("Manual");
     	menuItemManual.setName("menuItemManual");
     	menuHelp.add(menuItemManual);
     	menuItemManual.addActionListener(menuListener);
     	frame.setJMenuBar(bar);
	}
	
}