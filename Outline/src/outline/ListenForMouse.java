package outline;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;

public class ListenForMouse implements MouseListener {

	public void mouseClicked(MouseEvent e) {	
	}

	public void mouseEntered(MouseEvent e) {
		
		AbstractButton source = ((AbstractButton) e.getSource());
		String sourceName = ((Component) e.getSource()).getName();
		
		for (String mainFeature : PersonalOrganiserGUI.mainFeatures) {
			if (sourceName.equals(mainFeature)) {
				source.setContentAreaFilled(true);
				source.setBackground(Style.colorMainButtonHighlight);
				return;
			}
		}
		
		
		for (String todayOption : Today.options) {
			if (sourceName.equals(todayOption + "Today")) {
				source.setContentAreaFilled(true);
				source.setBackground(Style.colorSubMainButtonHighlight);
				return;
			}
		}
		
		if (sourceName.equals("updateToday")) {
			source.setContentAreaFilled(true);
			source.setBackground(Style.colorSubMainButtonHighlight);
			return;
		}
		
		
		for (String leavingOption : Leaving.options) {
			if (sourceName.equals(leavingOption + "Leaving")) {
				source.setContentAreaFilled(true);
				source.setBackground(Style.colorSubMainButtonHighlight);
				return;
			}
		}

	}

	public void mouseExited(MouseEvent e) {
		AbstractButton source = ((AbstractButton) e.getSource());
		String sourceName = ((Component) e.getSource()).getName();
		
		for (String mainFeature : PersonalOrganiserGUI.mainFeatures) {
			if (sourceName.equals(mainFeature)) {
				source.setContentAreaFilled(false);
				return;
			}
		}
		
		
		for (String todayOption : Today.options) {
			if (sourceName.equals(todayOption + "Today")) {
				source.setContentAreaFilled(false);
				return;
			}
		}
		
		if (sourceName.equals("updateToday")) {
			source.setContentAreaFilled(false);
			return;
		}
		
		
		for (String leavingOption : Leaving.options) {
			if (sourceName.equals(leavingOption + "Leaving")) {
				source.setContentAreaFilled(false);
				return;
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}