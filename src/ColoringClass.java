import javax.swing.*;
import java.awt.*;

public class ColoringClass {
	
	public ColoringClass(JTextArea errorTextArea, JTextArea jt, String command, JFrame frame) {		
		if(command.equals("TextArea Color         ")) {
			Color initialcolor=Color.RED;
			Color color=JColorChooser.showDialog(frame,"Select a color",initialcolor);
			jt.setBackground(color);
		}
		else
			if(command.equals("Text Color")) {
			Color initialcolor=Color.RED;
			Color color=JColorChooser.showDialog(frame,"Select a color",initialcolor);
			jt.setForeground(color);
		}
		else
			if(command.equals("Error TextArea Color")) {
				Color initialcolor=Color.RED;
				Color color=JColorChooser.showDialog(frame,"Select a color",initialcolor);
				errorTextArea.setBackground(color);
			}
		else {
			Color initialcolor=Color.RED;
			Color color=JColorChooser.showDialog(frame,"Select a color",initialcolor);
			errorTextArea.setForeground(color);
		}
	}
}