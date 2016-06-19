import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;




public class AboutEditor {
	
	JDialog frameAbout ;
	public AboutEditor(JFrame frame) {
			
			Font fn2 = new Font("Arial",Font.BOLD+Font.ITALIC,15);
			frameAbout = new JDialog(frame, "About Notepad");
			frameAbout.setSize(400,300);
			frameAbout.setBounds(400,240,400,300);			

			frameAbout.setLayout(new GridLayout(5,0));
			frameAbout.setResizable(false);
			JLabel l00 = new JLabel("******************************************************************************");
			frameAbout.add(l00);

			JLabel label = new JLabel("            JEditor ® Bodacious ",null, SwingConstants.CENTER);
			label.setFont(fn2);
			frameAbout.add(label);

			JLabel l2 = new JLabel("      Version 1.0 ",null, SwingConstants.CENTER);
			l2.setFont(fn2);
			frameAbout.add(l2);
			
			JLabel l11 = new JLabel("      Bodacious IT Hub",null, SwingConstants.CENTER);
			l11.setFont(fn2);
			frameAbout.add(l11);
			
			JLabel l01 = new JLabel("******************************************************************************");
			frameAbout.add(l01);

			frameAbout.setVisible(true);
	}
}