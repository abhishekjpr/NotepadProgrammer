import javax.swing.JTextArea;
import javax.swing.*;

public class TabInfo {
	JTextArea textArea;
	String text;
	String path;
	
	public TabInfo(JLabel label, String t)
	{
		
	}

	public TabInfo(JTextArea textArea, String text) {
		this.textArea = textArea;
		this.text = text;
	}

	public TabInfo(JTextArea textArea, String text, String path) {
		this.textArea = textArea;
		this.text = text;
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
