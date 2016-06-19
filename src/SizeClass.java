import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SizeClass implements AdjustmentListener {
	JFrame sFrame;
	JScrollBar sBar ;
	static int size = 0;
	static int value;
	JTextArea jt;
	int style;
	public SizeClass(JTextArea jt, String command, int style) {
			this.jt = jt;
			this.style = style;
			sFrame = new JFrame("Size");
			sFrame.setAlwaysOnTop(true);
			sFrame.setResizable(false);
			sFrame.setBounds(210,470,880, 60);
			sFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			if(command.equals("Text Size"))
			{
				if(size == 0)	{
					sBar = new JScrollBar(JScrollBar.HORIZONTAL,20,5,0,300);
					size++;
				}
				else {
					sBar = new JScrollBar(JScrollBar.HORIZONTAL,value,5,0,300);
				}
			}
			sBar.addAdjustmentListener(this);
			sFrame.add(sBar);
			sFrame.setVisible(true);
	}
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		if(e.getSource() == sBar)
		{
			value = sBar.getValue();
			jt.setFont(new Font("Arial",style,value));
		}
	}
}