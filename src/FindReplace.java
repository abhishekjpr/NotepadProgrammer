import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class FindReplace {
	
	JDialog fr;
	JTextField findWhatTextField, replaceWithTextField;
	JLabel findWhatLabel, replaceWithLabel;
	static JButton editMenuButtonFindAndReplaceNext, editMenuButtonReplace, editMenuButtonReplaceAll, editMenuButtonReplaceClose;
	int a, b;
	boolean fn = false;
	
	public FindReplace(final JTextArea textArea, final JFrame frame) 
	{	
		fr = new JDialog(frame, "Find And Replace");

		Font labFon = new Font("Ebrima", Font.BOLD, 12);
		Font fon = new Font("Ebrima", Font.BOLD, 12);
			
		GridBagLayout gbl = new GridBagLayout();
		fr.setLayout(gbl);
	
		GridBagConstraints gbc = new GridBagConstraints();

		findWhatLabel = new JLabel("Find What:");
		findWhatLabel.setFont(labFon);
		fr.setBounds(frame.getX()+100,frame.getY()+100,700,300);
		fr.setAlwaysOnTop(true);
	
		gbc.weightx = 1.0;	gbc.weighty = 1.0;
		gbc.gridx = 0 ;		gbc.gridy = 0;
		Insets i = new Insets(15,15,15,15);
		gbc.insets = i;
	
		fr.add(findWhatLabel,gbc);
	
		findWhatTextField = new JTextField(20);
		findWhatTextField.setFont(labFon);
		findWhatTextField.setForeground(Color.black);
		Insets i1 = new Insets(25,25,25,25);
		gbc.insets = i1;
		gbc.gridx = 1;
			
		fr.add(findWhatTextField,gbc);
	
	
		replaceWithLabel = new JLabel("Replace With:");
		replaceWithLabel.setFont(labFon);
	
		gbc.weightx = 1.0;	gbc.weighty = 1.0;
		gbc.gridx = 0 ;		gbc.gridy = 1;
		Insets i3 = new Insets(15,15,15,15);
		gbc.insets = i3;
	
		fr.add(replaceWithLabel,gbc);
		
		replaceWithTextField = new JTextField(20);
		replaceWithTextField.setFont(labFon);
		replaceWithTextField.setForeground(Color.black);
		Insets i2 = new Insets(25,25,25,25);
		gbc.insets = i2;
		gbc.gridx = 1;
			
		fr.add(replaceWithTextField,gbc);
	
	
		editMenuButtonFindAndReplaceNext = new JButton("Find Next");		
		editMenuButtonFindAndReplaceNext.setFont(fon);
		editMenuButtonFindAndReplaceNext.setForeground(Color.black);
		editMenuButtonFindAndReplaceNext.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String line = textArea.getText();
				String text = null;
				text = findWhatTextField.getText();
				if(text == null || text.equals(""))
				{
	
				}
				else
				{
					Pattern p = Pattern.compile("\\r");
					Matcher m = p.matcher(line);
					while(m.find())
					{
						line = m.replaceAll("");
					}
					p = Pattern.compile(text);
					m = p.matcher(line);
					if(m.find(b))
					{
						a = m.start();	b = m.end();
						textArea.select(a,b);
						frame.toFront();
						fn = true;
					}	
					else
					{
						if(fn == true)
						{
							JOptionPane.showMessageDialog(fr, "No More Occurence", "Editor", JOptionPane.INFORMATION_MESSAGE);
							a = b = 0;
							fn = false;
						}
						else
						{
							JOptionPane.showMessageDialog(fr, "Cannot Found \"" +findWhatTextField.getText()+"\"", "Editor", JOptionPane.INFORMATION_MESSAGE);
							a = b = 0;
							fn = false;
						}
					}
				}
			}
		});
	
		gbc.insets = i;
		gbc.gridx = 0;		gbc.gridy = 2;
		gbc.ipady = 5;
		fr.add(editMenuButtonFindAndReplaceNext,gbc);

		editMenuButtonReplace = new JButton("Replace");			
		editMenuButtonReplace.setFont(fon);
		editMenuButtonReplace.setForeground(Color.black);
	
		gbc.insets = i;
		gbc.gridx = 1;
		fr.add(editMenuButtonReplace,gbc);
		editMenuButtonReplace.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String str = textArea.getText();
				//String result = null;
				if(replaceWithTextField.getText() == null || replaceWithTextField.getText().equals("") || findWhatTextField.getText() == null || findWhatTextField.getText().equals(""))
				{
	
				}
				else
				{
					if(textArea.getSelectedText() == null || textArea.equals(""))
					{

					}
					else
					{
						Pattern p = Pattern.compile(findWhatTextField.getText());
						Matcher m = p.matcher(str);
						if(m.find())
						{
							if(a == 0 && b == 0)
							{
	
							}
							else
							{
								textArea.replaceRange(replaceWithTextField.getText(),a,b);
								a = b = 0;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(fr, "Cannot Find \""+findWhatTextField.getText()+"\"", "Editor", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});

		editMenuButtonReplaceAll = new JButton("Replace All");			
		editMenuButtonReplaceAll.setFont(fon);
		editMenuButtonReplaceAll.setForeground(Color.black);
		gbc.insets = i;
		gbc.gridx = 2;
		fr.add(editMenuButtonReplaceAll,gbc);
		editMenuButtonReplaceAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String str = textArea.getText();
				//System.out.println(str);
				String result = null;
				if(findWhatTextField.getText() == null || replaceWithTextField.getText() == null || findWhatTextField.getText().equals("") || replaceWithTextField.getText().equals(""))
				{
		
				}
				else
				{
					
					if(textArea.getText() == null || textArea.equals(""))
					{
					}
					else
					{
						Pattern p = Pattern.compile(findWhatTextField.getText());
						Matcher m = p.matcher(str);
						if(m.find())
						{
							result = m.replaceAll(replaceWithTextField.getText());
							System.out.println(result);
							textArea.setText(result);
						}
						else
						{
							JOptionPane.showMessageDialog(fr, "Cannot Find \""+findWhatTextField.getText()+"\"", "Editor", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
	
		editMenuButtonReplaceClose = new JButton("Close");		
		editMenuButtonReplaceClose.setFont(fon);
		editMenuButtonReplaceClose.setForeground(Color.black);
		gbc.insets = i1;

		gbc.gridx = 3;
		fr.add(editMenuButtonReplaceClose,gbc);
		editMenuButtonReplaceClose.addActionListener(new ActionListener()
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