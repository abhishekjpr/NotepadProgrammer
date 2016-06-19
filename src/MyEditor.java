import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyEditor implements ActionListener {
	
	JFrame frame;
	
	JMenuBar menuBar;
	JMenu fileMenu, editMenu, buildMenu, formatMenu, coloringMenu, aboutMenu, gameMenu;
	JMenuItem item;
	static JCheckBoxMenuItem wordWrap;
	
	//String getText = null;
	String com, path, whatOpen;
	int style = Font.PLAIN;
	JLabel mousePoint ;
	
	JTextArea textArea, errorTextArea;
	JSplitPane splitPane;
	JTabbedPane tabbedPane;
	
	JPanel p;
	JLabel l1, l2;
	JTextField tf1, tf2;
	
	ArrayList<TabInfo> arrayList;
	JFileChooser saveFile, openFile;
	
	public static void main(String[] args)
	{
		new MyEditor();
	}
	public MyEditor(){
		
		/*******************************************************************************************
								 Frame Properties 
	****************************************************************************************************/

		arrayList = new ArrayList<TabInfo>();
		
		frame = new JFrame("Editor");
		try
		     {
      			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[3].getClassName());
	            SwingUtilities.updateComponentTreeUI(frame);
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
		frame.setBounds(200, 50, 900,600);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				frameClose();
				if (arrayList.isEmpty()) {
					frame.setVisible(true);
					frame.dispose();
					System.exit(1);
				}
			}
		});
		
		menuBar = new JMenuBar();
		try
		     {
      			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
	            SwingUtilities.updateComponentTreeUI(menuBar);
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
		frame.setJMenuBar(menuBar);
		
		p = new JPanel();
		
		l1 = new JLabel("X");			l2 = new JLabel("Y");
		tf1 = new JTextField(5);			tf2 = new JTextField(5);
		tf1.setEditable(false);			tf2.setEditable(false);

		
		p.add(l1);		p.add(tf1);		p.add(l2);		p.add(tf2);
		frame.add(p,"South");
		frame.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e){
				int x, y;
				x = e.getX();		y = e.getY();
				tf1.setText(x+"");		tf2.setText(y+"");
			}
	
		});
		
		/*******************************************************************************************
								 File Menu Properties 
	****************************************************************************************************/		 
		fileMenu = new JMenu("File ");
		menuBar.add(fileMenu);
		
		item = new JMenuItem("New                    ");		             fileMenu.add(item);			  	 item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));
		item = new JMenuItem("Open");			fileMenu.add(item);			  	 item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
		fileMenu.addSeparator();
		item = new JMenuItem("Save");		   	  fileMenu.add(item);				   item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
		item = new JMenuItem("Save As");			fileMenu.add(item);				item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_DOWN_MASK));
		fileMenu.addSeparator();
		item = new JMenuItem("Close Tab");			fileMenu.add(item);				item.addActionListener(this);
		item = new JMenuItem("Close All Tabs");			fileMenu.add(item);				item.addActionListener(this);
		fileMenu.addSeparator();
		item = new JMenuItem("Exit");			fileMenu.add(item);				     item.addActionListener(this);
		
		
		/*******************************************************************************************
								 Edit Menu Properties 
	****************************************************************************************************/
		 
		 editMenu = new JMenu("Edit ");					menuBar.add(editMenu);
		 editMenu.setEnabled(false);
		 item = new JMenuItem("Find");					 editMenu.add(item);				item.addActionListener(this);
		 item = new JMenuItem("Find and Replace");			 editMenu.add(item);				item.addActionListener(this);
		 editMenu.addSeparator();
		 item = new JMenuItem("Cut                   Ctrl + X");					  editMenu.add(item);				item.addActionListener(this);
		 item = new JMenuItem("Copy                Ctrl + C");					editMenu.add(item);				item.addActionListener(this);
		 item = new JMenuItem("Paste               Ctrl + V");					editMenu.add(item);				item.addActionListener(this);
		 editMenu.addSeparator();
		 item = new JMenuItem("Select All         Ctrl + A");					editMenu.add(item);				item.addActionListener(this);
		 item = new JMenuItem("Time/Date   ");					editMenu.add(item);				item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,0)); //F4
		
		/*******************************************************************************************
								 Build Menu Properties 
	****************************************************************************************************/
	
		buildMenu = new JMenu("Build ");					menuBar.add(buildMenu);
		buildMenu.setEnabled(false);
		item = new JMenuItem("Compile                ");				          buildMenu.add(item);				item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0)); //F5
		item = new JMenuItem("Run");				              buildMenu.add(item);				    item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7,0));  //F7
		
		/*******************************************************************************************
								 Format Menu Properties 
	****************************************************************************************************/
	
		formatMenu = new JMenu("Format ");					menuBar.add(formatMenu);
		formatMenu.setEnabled(false);
		wordWrap = new JCheckBoxMenuItem("Word Wrap            ");		    formatMenu.add(wordWrap);		wordWrap.addActionListener(this);
		formatMenu.addSeparator();
		
		item = new JMenuItem("Text Size");				formatMenu.add(item);					item.addActionListener(this);
		
		formatMenu.addSeparator();
		item = new JMenuItem("Bold              ");				        formatMenu.add(item);			item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke('B', KeyEvent.CTRL_DOWN_MASK));
		item = new JMenuItem("Italic");				     	          formatMenu.add(item);			 item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke('I', KeyEvent.CTRL_DOWN_MASK));
		item = new JMenuItem("Bold Italic");				      formatMenu.add(item);		        item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke('K', KeyEvent.CTRL_DOWN_MASK));
		item = new JMenuItem("Regular");				     	      formatMenu.add(item);		        item.addActionListener(this);
		item.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_DOWN_MASK));
		formatMenu.addSeparator();
		coloringMenu = new JMenu("Coloring");				formatMenu.add(coloringMenu);
		item = new JMenuItem("Text Color");					  coloringMenu.add(item);			item.addActionListener(this);
		item = new JMenuItem("TextArea Color         ");			         coloringMenu.add(item);			       item.addActionListener(this);
		item = new JMenuItem("Error TextArea Color");			         coloringMenu.add(item);			       item.addActionListener(this);
		item = new JMenuItem("Error Text Color");			         coloringMenu.add(item);			       item.addActionListener(this);
		
		gameMenu = new JMenu("Misc. ");					menuBar.add(gameMenu);
		item = new JMenuItem("Number Puzzle");				gameMenu.add(item);				item.addActionListener(this);
		item = new JMenuItem("Digital Watch");				gameMenu.add(item);				item.addActionListener(this);
		
		aboutMenu = new JMenu("About ");				menuBar.add(aboutMenu);
		item = new JMenuItem("About Editor");				aboutMenu.add(item);				item.addActionListener(this);
		
		/*******************************************************************************************
								 errorTextArea Properties 
	****************************************************************************************************/
	
		errorTextArea = new JTextArea();
	//	errorTextArea.setEditable(false);
		JScrollPane errorScroll = new JScrollPane(errorTextArea);
		errorTextArea.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent e){
				int x, y;
				x = e.getX();		y = e.getY();
				tf1.setText(x+"");		tf2.setText(y+"");
			}
		});

		/*******************************************************************************************
								 JSplitPane Properties 
	****************************************************************************************************/
		JLabel startPage = new JLabel(new ImageIcon("1.jpg"));
		tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent e){
				int x, y;
				x = e.getX();		y = e.getY();
				tf1.setText(x+"");		tf2.setText(y+"");
			}
		});
		tabbedPane.addTab("Start",startPage);
		arrayList.add(new TabInfo(startPage, null));
		
		tabbedPane.setBackground(Color.white);
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, errorScroll);
		
		
		frame.add(splitPane);

		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		String command = e.getActionCommand();
		System.out.println(command+" Was Pressed..!");
	/************************************** File Menu Item "New "*********************************************/
		if(command.equals("New                    ")){
			saveFile = new JFileChooser();
			saveFile.showSaveDialog(frame);
			path = saveFile.getSelectedFile()+"";
			try{
				if(path == null || path.equals("")){
					System.out.println("Cancel Pressed In Save Dialog Box");
				}
				else{
					File fileName = saveFile.getSelectedFile();
					String name = fileName.getName();
					try{
						File file = new File(path);
						if(file.createNewFile())
							System.out.println("File Successfully Created In " +path);
					}
					catch(Exception exception){	
					}
					textArea = new JTextArea();
					textArea.setFont(new Font("Corbel",Font.BOLD,15));
					arrayList.add(new TabInfo(textArea, textArea.getText()));
					JScrollPane pane = new JScrollPane(textArea);
					textArea.addMouseMotionListener(new MouseMotionAdapter()
					{
						public void mouseMoved(MouseEvent e){
							int x, y;
							x = e.getX();		y = e.getY();
							tf1.setText(x+"");		tf2.setText(y+"");
						}
					});
			//		getText = textArea.getText();
					com = "New";
					tabbedPane.addTab(name, null, pane, fileName+"");
					tabbedPane.setSelectedIndex(arrayList.size() - 1);
					editMenu.setEnabled(true);
					buildMenu.setEnabled(true);
					formatMenu.setEnabled(true);
				}
			}
			catch(Exception eee){
			}
		}
		else
		if(command.equals("Open")){
			openFile = new JFileChooser();
			openFile.showOpenDialog(frame);
			openFile.setVisible(true);
			int index = -1;
			String s = null;
			String fileName = openFile.getSelectedFile()+"";
			File file = new File(fileName);
			if(file.exists()){
				if(fileName == null || fileName.equals("")){
				}
				else{
					s = file.getName();
					try{
						for (TabInfo i : arrayList){
							if(fileName.equalsIgnoreCase(i.getPath())){
								index = arrayList.indexOf(i);
							}
						}
						if(index == -1){
							JTextArea textArea = new JTextArea();
							textArea.setFont(new Font("Corbel",Font.BOLD,15));
							
							JScrollPane pane = new JScrollPane(textArea);
							textArea.addMouseMotionListener(new MouseMotionAdapter()
							{
								public void mouseMoved(MouseEvent e){
									int x, y;
									x = e.getX();		y = e.getY();
									tf1.setText(x+"");		tf2.setText(y+"");
								}
							});
							tabbedPane.addTab(s, pane);
							BufferedReader reader = new BufferedReader(new FileReader(fileName));
							String line = null;
							while ((line = reader.readLine()) != null) {
								textArea.setText(textArea.getText() + "\n"+ line);
							}
							reader.close();
							whatOpen = "Open";
							com = "Open";
							arrayList.add(new TabInfo(textArea, textArea.getText(), fileName));
					//		getText = textArea.getText();
							tabbedPane.setSelectedIndex(arrayList.size() - 1);
							//setTitle(s);
						} 
						else {
							tabbedPane.setSelectedIndex(index);
						}
						editMenu.setEnabled(true);
						buildMenu.setEnabled(true);
						formatMenu.setEnabled(true);
					} 
					catch (Exception e1) {
					}
				}
			}	
		}
		else
		if(command.equals("Save")){
			try{
				int index = tabbedPane.getSelectedIndex();
				TabInfo tabInfo = arrayList.get(index);
				if(com.equals("Open"))
					path = tabInfo.getPath();
				String content = tabInfo.getTextArea().getText();
				System.out.println(path);
				if (path != null) {
					File file = new File(path);
					FileOutputStream fos = new FileOutputStream(file);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					String strrr = "";
					char[] c = content.toCharArray();
					for(int i=0; i< c.length; i++){
						fos.write(c[i]);
						strrr += c[i];
					}
					tabInfo.setText(strrr);
			//		getText = textArea.getText();
					bos.close();
					fos.close();
	
					tabInfo.setPath(path);
				}
			}
			catch(Exception ep){
			}
		}
		else
		if(command.equals("Save As")){
			try {
					saveFile = new JFileChooser();
					saveFile.showSaveDialog(frame);
					saveFile.setVisible(true);
					File filee = saveFile.getSelectedFile();
					if (filee != null) {
						path = saveFile.getSelectedFile()+"";
						int index = tabbedPane.getSelectedIndex();
						TabInfo tabInfo = arrayList.get(index);
						String content = tabInfo.getTextArea().getText();
						if (!(content.isEmpty())) {
							File file = new File(path);
							FileOutputStream fos = new FileOutputStream(file);
							BufferedOutputStream bos = new BufferedOutputStream(fos);
							String strrr = "";
							char[] c = content.toCharArray();
							for(int i=0; i < c.length; i++)
							{
								bos.write(c[i]);
								strrr += c[i];
							}
							tabInfo.setText(strrr);
					//		getText = textArea.getText();
							bos.close();
							fos.close();
							tabbedPane.setTitleAt(index, saveFile.getSelectedFile().getName());
						}
					}
			} 
			catch (Exception e11) {
				System.out.println("Exception e11");
			}
		}
		else
		if(command.equals("Compile                ")){
			actionPerformed(new ActionEvent("Save", 12, "Save"));
			errorTextArea.setText("");
			int index = tabbedPane.getSelectedIndex();
			path = arrayList.get(index).getPath();
			if (path != null && path.endsWith(".java")) {
				new CompileClass(frame, errorTextArea, path);
			}
		}
		else
		if(command.equals("Run")){

			int index = tabbedPane.getSelectedIndex();
			path = arrayList.get(index).getPath();
			if (path != null && path.endsWith(".java")) {
				RunClass rc = new RunClass(errorTextArea, path);
				rc.setDaemon(true);
				rc.start();
			}
		}
		else
		if(command.equals("Bold              ") || command.equals("Italic") || command.equals("Bold Italic") || command.equals("Regular") || command.equals("Word Wrap            ")){
			
			if(command.equals("Bold              "))
				style = Font.BOLD;
			else
				if(command.equals("Italic"))
					style = Font.ITALIC;
			else
				if(command.equals("Bold Italic"))
					style = Font.BOLD+Font.ITALIC;
			else
				style = Font.PLAIN;
					
			int index = tabbedPane.getSelectedIndex();
			TabInfo tabInfo = arrayList.get(index);
			JTextArea jt = tabInfo.getTextArea();
			new FormatMenuClass(jt, command);
		}
		else
		if(command.equals("Text Size")){
			int index = tabbedPane.getSelectedIndex();
			TabInfo tabInfo = arrayList.get(index);
			JTextArea jt = tabInfo.getTextArea();
		
			new SizeClass(jt, command, style);
			
		}
		else
		if(command.equals("Find") || command.equals("Find and Replace")){
			int index = tabbedPane.getSelectedIndex();
			TabInfo tabInfo = arrayList.get(index);
			JTextArea jt = tabInfo.getTextArea();
			if(command.equals("Find"))
				new FindClass(jt, frame);
			else
				new FindReplace(jt, frame);
		}
		else
		if(command.equals("Cut                   Ctrl + X") || command.equals("Copy                Ctrl + C") || command.equals("Paste               Ctrl + V")) {
			System.out.println("Hello");
			int index = tabbedPane.getSelectedIndex();
			TabInfo tabInfo = arrayList.get(index);
			JTextArea jt = tabInfo.getTextArea();
			System.out.println("Hello");
			System.out.println(jt.getText());
			if(jt != null){
				if(command.equals("Cut                   Ctrl + X")){
					jt.cut();
					System.out.println("Cut");
				}
				else
				if(command.equals("Copy                Ctrl + C")){
					jt.copy();
					System.out.println("Copy");
				}
				else{
					jt.paste();
					System.out.println("paste");
				}
			}
		}
		else
		if(command.equals("Select All         Ctrl + A") || command.equals("Time/Date   ")) {
			int index = tabbedPane.getSelectedIndex();
			TabInfo tabInfo = arrayList.get(index);
			JTextArea jt = tabInfo.getTextArea();
			if(jt != null) {
				if(command.equals("Select All         Ctrl + A")) {
					jt.selectAll();
				}
				else {
					Date d = new Date();
					jt.setText(jt.getText()+d);
				}
			}
		}
		else 
		if(command.equals("TextArea Color         ") || command.equals("Text Color") || command.equals("Error TextArea Color") || command.equals("Error Text Color")) {
			int index = tabbedPane.getSelectedIndex();
			TabInfo tabInfo = arrayList.get(index);
			JTextArea jt = tabInfo.getTextArea();
			if(jt!=null) {
				new ColoringClass(errorTextArea, jt, command, frame);
			}
		}
		else
		if(command.equals("About Editor")){
			new AboutEditor(frame);
		}
		else
		if(command.equals("Exit")) {
			frameClose();
			if (arrayList.isEmpty()) {
				frame.setVisible(true);
				frame.dispose();
				System.exit(1);
			}
		}
		else
		if(command.equals("Number Puzzle")) {
			new JPuzzleGame(frame);
		}
		else
		if(command.equals("Digital Watch")) {
			new JDigitalWatchDemo(frame);
		}
	
	/***************************************Close Tab*************************************************/
	
		else
		if(command.equals("Close Tab")){
			try
			{
				int index = tabbedPane.getSelectedIndex();
				if(index == 0)
				{
					frame.setVisible(false);
					frame.dispose();
					System.exit(1);
				}
				else
				{
					TabInfo tabInfo = arrayList.get(index);
					JTextArea jt = tabInfo.getTextArea();
					String text = tabInfo.getText();
					String checkText = jt.getText();
				
					if(checkText.equals(text))
					{
						tabbedPane.remove(index);
						arrayList.remove(index);
					}
					else
					{
						try{
							int result = JOptionPane.showConfirmDialog(frame, "Do You Want To Save The Changes in "+tabbedPane.getTitleAt(index)+" ?", "Editor", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.YES_OPTION) {
								if(com.equals("Open"))
										path = tabInfo.getPath();
								String content = tabInfo.getTextArea().getText();
								System.out.println(path);
								if (path != null) {
									System.out.println(index+ "Hello ");
									File file = new File(path);
									FileOutputStream fos = new FileOutputStream(file);
									BufferedOutputStream bos = new BufferedOutputStream(fos);
									char[] c = content.toCharArray();
									for(int i=0; i< c.length; i++){
										fos.write(c[i]);
									}
								//	getText = textArea.getText();
									System.out.println("OOPS");
									tabbedPane.remove(index);
									arrayList.remove(index);
									
									bos.close();
									fos.close();
								}
							}
							else
							if(result == JOptionPane.NO_OPTION) {
								tabbedPane.remove(index);
								arrayList.remove(index);
							}
						}
						catch(Exception ep){
						}
					}
				}
			}
			catch(Exception eq){
			}
		}
	/*********************************  close Tab End *********************************************/
		else
		if(command.equals("Close All Tabs")) {
			frameClose();
			if (arrayList.isEmpty()) {
				frame.setVisible(true);
				frame.dispose();
				System.exit(1);
			}
		}
	}
	public void frameClose() {
			int index = 1;
			int range = arrayList.size();
			for (index = 1; index < range; index++) {
				String text = arrayList.get(1).getText();
				if (text.equals(arrayList.get(1).getTextArea().getText())) {
					tabbedPane.remove(1);
					arrayList.remove(1);
				} else {
					int result = JOptionPane.showConfirmDialog(frame,
							"Do you want to Save the Changes ?",
							"Confirm", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.CANCEL_OPTION) {
						return;
					} 
					else if (result == JOptionPane.NO_OPTION) {
						tabbedPane.remove(1);
						arrayList.remove(1);
					} 
					else if (result == JOptionPane.YES_OPTION) {
						try {
							TabInfo tabInfo = arrayList.get(1);
							path = tabInfo.getPath();
							String content = tabInfo.getTextArea().getText();
							if (path != null && ! path.equals("Start")) {
							} 
							else {
								saveFile.setVisible(true);
								if (saveFile.getSelectedFile() != null
										&& saveFile.getSelectedFile().getName() != null) {
									path = saveFile.getSelectedFile()+"";
								}
							}
							if (path != null) {
								File file = new File(path);
								FileWriter fw = new FileWriter(file);
								BufferedWriter bw = new BufferedWriter(fw);
								bw.write(content);
								bw.close();
								fw.close();
								if (tabInfo.getPath() == null)
									tabbedPane.setTitleAt(index, saveFile.getSelectedFile().getName());
								tabInfo.setPath(path);
								tabInfo.setText(content);
							}
						} 
						catch (Exception e) {
							System.out.println("jvjvk");
						}
						tabbedPane.remove(1);
						arrayList.remove(1);
					}
			}
		}
		frame.setVisible(false);
		frame.dispose();
		System.exit(1);
	}
}