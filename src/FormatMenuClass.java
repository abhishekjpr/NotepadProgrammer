import java.awt.*;
import javax.swing.JTextArea;

public class FormatMenuClass {
	public FormatMenuClass(JTextArea textArea,  String str) {
		
		if(str.equals("Bold              ")){
			textArea.setFont(new Font(null,Font.BOLD,15));
		}
		else
		if(str.equals("Italic")){
			textArea.setFont(new Font(null,Font.ITALIC,15));
		}
		else
		if(str.equals("Bold Italic")){
			textArea.setFont(new Font(null,Font.BOLD+Font.ITALIC,15));
		}
		else
		if(str.equals("Regular")){
			textArea.setFont(new Font(null,Font.PLAIN,15));
		}
		else
		if(str.equals("Word Wrap            ")){
			if(MyEditor.wordWrap.getState()){
				textArea.setLineWrap(true);
			}
			else
			{
					textArea.setLineWrap(false);
			}
		}
	}
}