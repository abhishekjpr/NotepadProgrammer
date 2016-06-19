import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JPuzzleGame implements ActionListener
{
	JDialog frame ;

	JButton ok;
	boolean flag = false;
	Font fn = new Font("Arial",Font.BOLD+Font.ITALIC,60);
	Font fn1 = new Font("Arial",Font.BOLD+Font.ITALIC,40);
	JButton one_But, two_But, three_But, four_But, five_But, six_But, seven_But, eight_But, blank_But;
	JButton nextButton;
	JButton ori;
	static int count = 0;
	public JPuzzleGame(JFrame fra)
	{
		frame = new JDialog(fra, "JPuzzleGame");
		frame.setResizable(false);
	
		frame.setBounds(300,110,700,500);
		frame.setLayout(new GridLayout(0,3));
		frame.setBackground(Color.pink);
		one_But = new JButton("1") ; 				frame.add(one_But);			one_But.addActionListener(this);		one_But.setBackground(Color.pink);
		one_But.setFont(fn);

		two_But = new JButton("2") ;		 		frame.add(two_But);			two_But.addActionListener(this);		two_But.setBackground(Color.pink);
		two_But.setFont(fn);

		three_But = new JButton("3") ;				frame.add(three_But);			three_But.addActionListener(this);		three_But.setBackground(Color.pink);
		three_But.setFont(fn);

		four_But = new JButton("4") ;				frame.add(four_But);			four_But.addActionListener(this);		four_But.setBackground(Color.pink);
		four_But.setFont(fn);

		five_But = new JButton("5");					frame.add(five_But);			five_But.addActionListener(this);		five_But.setBackground(Color.pink);
		five_But.setFont(fn);

		six_But = new JButton("6") ;					frame.add(six_But);			six_But.addActionListener(this);			six_But.setBackground(Color.pink);
		six_But.setFont(fn);

		seven_But = new JButton("7") ;				frame.add(seven_But);			seven_But.addActionListener(this);		seven_But.setBackground(Color.pink);
		seven_But.setFont(fn);

		eight_But = new JButton("8") ;				frame.add(eight_But);			eight_But.addActionListener(this);		eight_But.setBackground(Color.pink);
		eight_But.setFont(fn);

		blank_But = new JButton(" ") ;				frame.add(blank_But);			blank_But.addActionListener(this);		blank_But.setBackground(Color.pink);
		blank_But.setFont(fn);

		JLabel lab = new JLabel();				frame.add(lab);
		JLabel lab1 = new JLabel();				frame.add(lab1);
		JLabel lab2 = new JLabel();				frame.add(lab2);

		nextButton = new JButton("Start");		nextButton.setFont(new Font("Arial",Font.BOLD,15));		frame.add(nextButton,"South");			nextButton.addActionListener(this);
		nextButton.setFont(fn1);
		nextButton.setBackground(Color.pink);
		nextButton.setForeground(Color.black);

		JLabel lab3 = new JLabel();				frame.add(lab3);

		ori = new JButton("Reset");		ori.setFont(new Font("Arial",Font.BOLD,15));		frame.add(ori,"South");			ori.addActionListener(this);
		ori.setFont(fn1);
		ori.setBackground(Color.pink);
		ori.setForeground(Color.black);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == nextButton)
		{
			nextButton.setText("Restart");
			String s;
			if(count == 0)
			{
				s = one_But.getText();
				one_But.setText(three_But.getText());
				three_But.setText(s);

				s = two_But.getText();
				two_But.setText(four_But.getText());
				four_But.setText(s);

				s = five_But.getText();
				five_But.setText(seven_But.getText());
				seven_But.setText(s);

				s = six_But.getText();
				six_But.setText(eight_But.getText());
				eight_But.setText(s);

				s = two_But.getText();
				two_But.setText(blank_But.getText());
				blank_But.setText(s);
				count++;
			}
			else
			if(count == 1)
			{
				s = one_But.getText();
				one_But.setText(four_But.getText());
				four_But.setText(s);

				s = two_But.getText();
				two_But.setText(three_But.getText());
				three_But.setText(s);

				s = five_But.getText();
				five_But.setText(eight_But.getText());
				eight_But.setText(s);

				s = six_But.getText();
				six_But.setText(seven_But.getText());
				seven_But.setText(s);

				s = two_But.getText();
				two_But.setText(blank_But.getText());
				blank_But.setText(s);
				count++;
			}
			else
			{
				s = one_But.getText();
				one_But.setText(eight_But.getText());
				eight_But.setText(s);

				s = two_But.getText();
				two_But.setText(seven_But.getText());
				seven_But.setText(s);

				s = three_But.getText();
				three_But.setText(six_But.getText());
				six_But.setText(s);

				s = four_But.getText();
				four_But.setText(five_But.getText());
				five_But.setText(s);

				s = five_But.getText();
				five_But.setText(blank_But.getText());
				blank_But.setText(s);
				count -= 2;
			}
		}
		else
		if(ae.getSource() == one_But)
		{
			String getText = one_But.getText();
			if(two_But.getText().equals(" "))
			{
				one_But.setText(" ");		two_But.setText(getText);
			}
			else
			if(four_But.getText().equals(" "))
			{
				one_But.setText(" ");		four_But.setText(getText);
			}
		}
		else
		if(ae.getSource() == two_But)
		{
			String getText = two_But.getText();
			if(one_But.getText().equals(" "))
			{
				two_But.setText(" ");		one_But.setText(getText);
			}
			else
			if(three_But.getText().equals(" "))
			{
				two_But.setText(" ");		three_But.setText(getText);
			}
			else
			if(five_But.getText().equals(" "))
			{
				two_But.setText(" ");		five_But.setText(getText);
			}
		}
		else
		if(ae.getSource() == three_But)
		{
			String getText = three_But.getText();
			if(two_But.getText().equals(" "))
			{
				three_But.setText(" ");		two_But.setText(getText);
			}
			else
			if(six_But.getText().equals(" "))
			{
				three_But.setText(" ");		six_But.setText(getText);
			}
		}
		else
		if(ae.getSource() == four_But)
		{
			String getText = four_But.getText();
			if(one_But.getText().equals(" "))
			{
				four_But.setText(" ");		one_But.setText(getText);
			}
			else
			if(five_But.getText().equals(" "))
			{
				four_But.setText(" ");		five_But.setText(getText);
			}
			else
			if(seven_But.getText().equals(" "))
			{
				four_But.setText(" ");		seven_But.setText(getText);
			}
		}
		else
		if(ae.getSource() == five_But)
		{
			String getText = five_But.getText();
			if(two_But.getText().equals(" "))
			{
				five_But.setText(" ");		two_But.setText(getText);
			}
			else
			if(four_But.getText().equals(" "))
			{
				five_But.setText(" ");		four_But.setText(getText);
			}
			else
			if(six_But.getText().equals(" "))
			{
				five_But.setText(" ");		six_But.setText(getText);
			}
			else
			if(eight_But.getText().equals(" "))
			{
				five_But.setText(" ");		eight_But.setText(getText);
			}
		}
		else
		if(ae.getSource() == six_But)
		{
			String getText = six_But.getText();
			if(three_But.getText().equals(" "))
			{
				six_But.setText(" ");		three_But.setText(getText);
			}
			else
			if(five_But.getText().equals(" "))
			{
				six_But.setText(" ");		five_But.setText(getText);
			}
			else
			if(blank_But.getText().equals(" "))
			{
				six_But.setText(" ");		blank_But.setText(getText);
			}
		}

		else
		if(ae.getSource() == seven_But)
		{
			String getText = seven_But.getText();
			if(four_But.getText().equals(" "))
			{
				seven_But.setText(" ");		four_But.setText(getText);
			}
			else
			if(eight_But.getText().equals(" "))
			{
				seven_But.setText(" ");		eight_But.setText(getText);
			}
		}
		else
		if(ae.getSource() == eight_But)
		{
			String getText = eight_But.getText();
			if(five_But.getText().equals(" "))
			{
				eight_But.setText(" ");		five_But.setText(getText);
			}
			else
			if(seven_But.getText().equals(" "))
			{
				eight_But.setText(" ");		seven_But.setText(getText);
			}
			else
			if(blank_But.getText().equals(" "))
			{
				eight_But.setText(" ");		blank_But.setText(getText);
			}
		}
		else
		if(ae.getSource() == blank_But)
		{
			if(blank_But.getText().equals(" "))
			{

			}
			else
			{
				String getText = blank_But.getText();
				if(eight_But.getText().equals(" "))
				{
					blank_But.setText(" ");		eight_But.setText(getText);
				}
				else
				if(six_But.getText().equals(" "))
				{
					blank_But.setText(" ");		six_But.setText(getText);
				}
			}
		}
		else
		if(ae.getSource() == ori)
		{
			one_But.setText("1");		two_But.setText("2");		three_But.setText("3");		four_But.setText("4");
			five_But.setText("5");		six_But.setText("6");		seven_But.setText("7");		eight_But.setText("8");
												blank_But.setText(" ");
			nextButton.setText("Start");
			count =6;
		}

		if(one_But.getText().equals("1") && two_But.getText().equals("2") && three_But.getText().equals("3") && four_But.getText().equals("4") &&
		five_But.getText().equals("5") && six_But.getText().equals("6") && seven_But.getText().equals("7") && eight_But.getText().equals("8") &&
		blank_But.getText().equals(" "))
		{
			if(count == 6 || count == 0)
			{

			}
			else
			{
				System.out.println("Hello");
				int result = JOptionPane.showConfirmDialog(frame, "You Win The Game..Congratulation..!! Do You Want To Restart ?", "Win", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION)
				{
					actionPerformed(new ActionEvent(ori,15,""));
					actionPerformed(new ActionEvent(nextButton, 3, ""));
				}
				else
				{
					frame.setVisible(false);
					frame.dispose();
				}
				
			}
		}
	}
}
