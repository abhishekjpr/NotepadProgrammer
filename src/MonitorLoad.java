import javax.swing.JFrame;


public class MonitorLoad {
	static JFrame frame = null;
	public static void main(String[ ] args) {
		frame = new JFrame();
		frame.setBounds(500,200, 10,10);
		frame.add(new LoadMonitor());
		
		System.out.println("Hello");
	}
}