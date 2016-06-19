
import javax.swing.*;
import java.io.*;

public class RunClass extends Thread {
	JTextArea errorTextArea;
	String path;
	public RunClass(JTextArea errorTextArea, String path) {
		this.errorTextArea = errorTextArea;
		this.path = path;
	}
	public void run() {
		errorTextArea.setText("");
		try{
			/******** Compiling A Program******************/
			Runtime runtime = Runtime.getRuntime();
			Process p = runtime.exec("javac \"" + path + "\"");
			System.out.println("javac \"" + path + "\"");

			BufferedReader error = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));
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
				errorTextArea.setText("------------------------------------------ Cofiguration:  <Default>------------------------------------------------\n");
				errorTextArea.setText(errorTextArea.getText()+"\n"+"Process Completed");
			}

		/********Running A Program ************************/
			runtime = Runtime.getRuntime();
		//	int substrIndex = path.lastIndexOf(".");
		//	String filePath = path.substring(0, substrIndex);
			String dir = path.substring(0, path.lastIndexOf(File.separator));
			System.out.println("dir :: "+dir);
			path = path.substring(path.lastIndexOf(File.separator) + 1,
					path.length());
			System.out.println("path1 :: "+path);
			path = path.substring(0, path.lastIndexOf("."));
			System.out.println("path2 :: "+path);
			File file = new File(dir);
			p = runtime.exec("cmd /c start cmd /k  java " + path, null, file);
			System.out.println("----- ----------------->");
			System.out.println("cmd /c start cmd /k  java " + path);
			System.out.println("*****");
			System.out.println(path+"\n"+file+"");

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
    			line = null;

    			while ((line = in.readLine()) != null) {
			        System.out.println(line);
			}
		}
		catch(Exception e12){
		}
	}
}