package outline;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Leaving {
	
	JPanel leavingButtons, leavingInfo;
	
	public static String[] options = {"Common", "School", "Grocery shopping"};
	
	public void show(JFrame frame, JPanel panel) {
		
		PersonalOrganiserGUI.clearContent();
		PersonalOrganiserGUI.clearSubMenu();
		
		displayLeavingButtons(frame, panel);
		
		displayCommonLeaving(frame, panel, "common");
		
		frame.setVisible(true);
		
	}

	private void displayCommonLeaving(JFrame frame, JPanel panel, String association) {
		String[] allCommon = LeavingData.selectAllCommon();
		
		int numberRows = allCommon.length / 2 + 1;
		
		leavingInfo = new JPanel(new GridLayout(numberRows, 1));
		leavingInfo.setBackground(Style.colorOutputBackground);
		
		JLabel commonTitle = new JLabel(Character.toUpperCase(association.charAt(0)) + association.substring(1));
		commonTitle.setBorder(new EmptyBorder(30,80,5,5));
		leavingInfo.add(commonTitle);
		
		leavingInfo.add(Box.createRigidArea(new Dimension(2,0)));
		
			
		for (int i = 0; i < allCommon.length; i += 2) {
			
			if (!allCommon[i+1].equals(association))
				continue;
			else {
				JLabel checkItem = new JLabel(allCommon[i]);
				checkItem.setBorder(new EmptyBorder(1,80,0,0));
				checkItem.setForeground(Color.BLACK);
				checkItem.setSize(new Dimension(40,10));
				checkItem.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						JLabel source = (JLabel) e.getSource();
						
						Boolean strikethrough = (Boolean) source.getFont().getAttributes().get(TextAttribute.STRIKETHROUGH);
						if (strikethrough == null)
							strikethrough = false;
						
						if (!strikethrough) {
							Font font = source.getFont();
							@SuppressWarnings("unchecked")
							Map<TextAttribute, Boolean>  attributes = (Map<TextAttribute, Boolean>) font.getAttributes();
							attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
							
							source.setFont(new Font(attributes));
						}
						else {
							Font font = source.getFont();
							@SuppressWarnings("unchecked")
							Map<TextAttribute, Boolean>  attributes = (Map<TextAttribute, Boolean>) font.getAttributes();
							attributes.put(TextAttribute.STRIKETHROUGH, false);
							
							source.setFont(new Font(attributes));
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						JLabel source = (JLabel) e.getSource();
						
						Boolean strikethrough = (Boolean) source.getFont().getAttributes().get(TextAttribute.STRIKETHROUGH);
						if (strikethrough == null)
							strikethrough = false;
						
						if (source.getForeground().equals(Color.BLACK) && !strikethrough)
							source.setForeground(Color.GREEN);
						else
							source.setForeground(Color.RED);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						JLabel source = (JLabel) e.getSource();
						
						if (source.getForeground().equals(Color.GREEN) || source.getForeground().equals(Color.RED))
							source.setForeground(Color.BLACK);
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				leavingInfo.add(checkItem);
			}		
		}
		
		leavingInfo.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		leavingInfo.setAlignmentY(JFrame.BOTTOM_ALIGNMENT);
		panel.add(leavingInfo);
		frame.setVisible(true);
		
	}

	private void displayLeavingButtons(JFrame frame, JPanel panel) {
		leavingButtons = new JPanel();
		leavingButtons.setName("leavingPanel");
		leavingButtons.setBackground(Style.colorSubMenuBackground);
		
		ListenForMouse mouseListener = new ListenForMouse();
		ListenToAction actionListener = new ListenToAction(frame, panel);
		
		for (String name : options) {
			JButton button = Style.styleSubMenuButtons(name);
			button.setName(name + "Leaving");
			button.addActionListener(actionListener);
			button.addMouseListener(mouseListener);
			leavingButtons.add(button);
		}
			
		leavingButtons.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		leavingButtons.setAlignmentY(JFrame.BOTTOM_ALIGNMENT);
		panel.add(leavingButtons);
	}

}
