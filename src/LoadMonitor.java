import javax.swing.*;
import java.awt.event.*;


public class LoadMonitor extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ProgressMonitor pbar;
	int counter = 0;
	public LoadMonitor() {
		pbar = new ProgressMonitor(this, "Loading Files. . .", "Initializing. . .", 0, 100);
		Timer t = new Timer(500, this);
		t.start();
	}
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Update());		// Or it is same as new Thread(new Update().start(), it is used when we implement Runnable Interface and in the main method while registering
											// the Thread Class Object We pass the class object which implements the runnable interface
	}
	class Update implements Runnable
	{
		public void run() {
			if(pbar.isCanceled()) // returns true if user click on the cancel button available on the progress monitor
			{
				pbar.close();	// it terminates the process..
				System.exit(1);
			}
			pbar.setProgress(counter);
			pbar.setNote("Operation Is " +counter+ " % Completed");
			counter += 10;
			if(counter == 110) {
				System.out.println("Load Files Completed..");
				new MyEditor();
			}
		}	
	}
}