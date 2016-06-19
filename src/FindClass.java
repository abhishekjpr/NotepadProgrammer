import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class FindClass {
	JDialog fr;
	JLabel editMenuLabelFind;
	JTextField editMenuTextField;
	JButton editMenuButtonFindNext, editMenuButtonClose;
	int a, b;
	boolean fn = false;
	
	public FindClass(final JTextArea textArea,final JFrame frame)
	{
		fr = new JDialog(frame, "Find");
	
		Font labFon = new Font("Ebrima", Font.BOLD, 12);
		GridBagLayout gbl = new GridBagLayout();
		fr.setBounds(frame.getX()+100,frame.getY()+100,700,300);
		fr.setLayout(gbl);
		fr.setAlwaysOnTop(true);
			
		GridBagConstraints gbc = new GridBagConstraints();
			
		editMenuLabelFind = new JLabel("Find :");
		editMenuLabelFind.setFont(labFon);
	
		gbc.weightx = 1.0;	gbc.weighty = 1.0;
		gbc.gridx = 0 ;		gbc.gridy = 0;
		Insets i = new Insets(15,15,15,15);
		gbc.insets = i;
	
		fr.add(editMenuLabelFind,gbc);
	
		editMenuTextField = new JTextField(20);
		editMenuTextField.setFont(labFon);
		editMenuTextField.setForeground(Color.black);
		Insets i1 = new Insets(25,25,25,25);
		gbc.insets = i1;
		gbc.gridx = 1;
		fr.add(editMenuTextField,gbc);
	
	
		editMenuButtonFindNext = new JButton("Find Next");		
		editMenuButtonFindNext.setFont(labFon);
		editMenuButtonFindNext.setForeground(Color.black);
		gbc.insets = i;
		gbc.gridx = 0;		gbc.gridy = 1;	gbc.ipadx = 20; 	gbc.ipady = 5;
		fr.add(editMenuButtonFindNext,gbc);
			
/*****************************Listener For Find Next Button********************************/

		editMenuButtonFindNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String line = textArea.getText();
				String text ;

				text = null;
				text = editMenuTextField.getText();
				if(text == null)
				{
					fn = false;
				}
				else
				{
					Pattern p = Pattern.compile("\\r");
					Matcher m = p.matcher(line);
					while(m.find())
					{
						line = m.replaceAll("");
					}
					p = Pattern.compile(editMenuTextField.getText());
					m = p.matcher(line);
					if(m.find(b))
					{
						a = m.start();	b = m.end();
						System.out.print("\na = " + a +"\n = "+b);
						textArea.select(a,b);
						frame.toFront();
						fn = true;
					}
					else
					{
						if(fn == true)
						{
							JOptionPane.showMessageDialog(fr, "No More Occurences", "Editor", JOptionPane.INFORMATION_MESSAGE);
							a = b = 0;
							fn = false;
						}
						else
						{
							JOptionPane.showMessageDialog(fr, "Cannot Found \"" +editMenuTextField.getText()+"\"", "Editor", JOptionPane.INFORMATION_MESSAGE);
							a = b = 0;
							fn = false;
						}
					}
				}
			}
		});
	/********************************************************************************************/
	
		editMenuButtonClose = new JButton("Close");				       	
		editMenuButtonClose.setFont(labFon);
		editMenuButtonClose.setForeground(Color.black);
		gbc.insets = i1;
		gbc.gridx = 1;
		fr.add(editMenuButtonClose,gbc);
		editMenuButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				a = b = 0;
				fn = false;	
				fr.setVisible(false);
				fr.dispose();
			}
		});
		fr.setVisible(true);	
	}
}