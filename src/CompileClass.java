import java.awt.*;
import javax.swing.*;
import java.io.*;

public class CompileClass {
	public CompileClass(JFrame frame, JTextArea errorTextArea, String path){
		errorTextArea.setFont(new Font("Corbel",Font.BOLD,15));
		errorTextArea.setText("------------------------------------------ Cofiguration:  <Default>------------------------------------------------\n");
		try {
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec("javac \"" + path + "\"");
				

			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			System.out.println(error+"");
			String line = null;
			line = error.readLine();
					
			if(line != null)
			{
				errorTextArea.setText(errorTextArea.getText() + "\n" + line);
				while ((line = error.readLine()) != null) {
					errorTextArea.setText(errorTextArea.getText() + "\n" + line);
				}
			}
			else{
				errorTextArea.setText(errorTextArea.getText()+"\n"+"Process Completed");
			//	JOptionPane.showMessageDialog(frame, "Program Compiled Successfully..", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			error.close();
			}
			catch (Exception eu) {
			}
	}
}