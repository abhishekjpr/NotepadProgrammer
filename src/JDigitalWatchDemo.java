import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.*;

public class JDigitalWatchDemo implements Runnable
{
	JDialog frame;
	Thread thread=null;
	int hours=0, minutes=0, seconds=0;
	String timeString = "";
	JLabel b;

	public JDigitalWatchDemo(JFrame framee)	
	{
		frame=new JDialog(framee, "Watch");
		frame.setBounds(framee.getX() + 600,framee.getY()+50,300,150);
		thread = new Thread(this);
		thread.start();
		b=new JLabel();
		b.setFont(new Font("Chiller",Font.BOLD+Font.ITALIC, 40));
		frame.add(b);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setLayout(new GridBagLayout());
		frame.setVisible(true);
	}
 	public void run() 
 	{
 		try {
         			while (true) {

	            		Calendar cal = Calendar.getInstance();
			            hours = cal.get( Calendar.HOUR_OF_DAY );
			            if ( hours > 12 ) hours -= 12;
			            minutes = cal.get( Calendar.MINUTE );
			            seconds = cal.get( Calendar.SECOND );
			
			            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
			            Date date = cal.getTime();
			            timeString = formatter.format( date );
	
			            printTime();
			
			            Thread.sleep( 1000 );  // interval given in milliseconds
        			 }	
	      	}
      		catch (Exception e) { }
 	}
	public void printTime()
	{
		b.setText(timeString);
	}
}