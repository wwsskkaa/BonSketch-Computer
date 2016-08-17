

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Menu extends JMenuBar implements Observer {
	
	private Model model;
	private JFrame frame;
	private JMenu file, view;
	private JMenuItem newfile, loadfile, saveFile,fittowindow,full;
	private JFileChooser fileChooser;

	Menu(Model m)
	{
		
		model=m;
		setBackground(new Color(230, 230, 250));

		file = new JMenu("File");
		file.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		file.setForeground(new Color(70, 130, 180));
		file.setPreferredSize(new Dimension(60, 39));
		file.setBackground(new Color(250, 128, 114));
		file.setHorizontalAlignment(SwingConstants.CENTER);
		fileChooser = new JFileChooser();

		add(file);
		
		newfile = new JMenuItem("New File");
		newfile.setBackground(new Color(25, 25, 112));
		newfile.setForeground(new Color(248, 248, 255));
		newfile.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				model.renew();
			}
		});
		file.add(newfile);
		
		 saveFile = new JMenuItem("Save File");
		 saveFile.setBackground(new Color(135, 206, 235));
		 saveFile.setForeground(new Color(248, 248, 255));
		 saveFile.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					model.setSaved(true);
					fileChooser.setFileFilter(new FileNameExtensionFilter("Text (.txt)", "txt"));
					fileChooser.showSaveDialog(frame);
					try {
						PrintWriter out = new PrintWriter(fileChooser.getSelectedFile()+".txt", "UTF-8");
						out.print(model.toString());
						out.close();
					} catch (Exception ex) {}
				}
			});
		file.add(saveFile);
		
		 loadfile = new JMenuItem("Load File");
		 loadfile.setBackground(new Color(70, 130, 180));
			loadfile.setForeground(new Color(248, 248, 255));
			loadfile.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					model.renew();
					fileChooser.setFileFilter(new FileNameExtensionFilter("Text (.txt)", "txt"));
					fileChooser.showOpenDialog(frame);
					try {
						fileChooser.getSelectedFile().getName();
						Scanner scanner = new Scanner(new FileInputStream(fileChooser.getSelectedFile()));
						int shapeAmount = scanner.nextInt();
						for (int i=0; i<shapeAmount; i++) {
							String Buttonname=scanner.next();
							int borderR=scanner.nextInt();
							int borderG=scanner.nextInt();
							int borderB=scanner.nextInt();
							int fillR=scanner.nextInt();
							int fillG=scanner.nextInt();
							int fillB=scanner.nextInt();
							int thickness = scanner.nextInt();
							double Xcoordinate = scanner.nextDouble();
							double Ycoordinate = scanner.nextDouble();
							double width = scanner.nextDouble();
							double height = scanner.nextDouble();
							double endXcoordinate = scanner.nextDouble();
							double endYcoordinate = scanner.nextDouble();
							Color bordercolor = new Color(borderR, borderG, borderB);
							Color fillcolor=null;
							if(fillR!=-1&&fillG!=-1&&fillB!=-1)
							{
								fillcolor = new Color(fillR, fillG, fillB);
							}
							model.addShape(Buttonname,bordercolor,fillcolor,thickness,Xcoordinate,Ycoordinate,width,height,endXcoordinate,endYcoordinate);
						}
						
						
							scanner.close();
							
						
					} catch (Exception ex) {}
				}
			});
			
		file.add(loadfile);
		
		view = new JMenu("View");
		view.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		view.setForeground(new Color(70, 130, 180));
		view.setPreferredSize(new Dimension(60, 39));
		view.setHorizontalAlignment(SwingConstants.CENTER);

		add(view);
		
		 full = new JMenuItem("Full Size");
		 full.setFont(UIManager.getFont("RadioButtonMenuItem.font"));
		 full.setBackground(new Color(25, 25, 112));
		 full.setForeground(Color.WHITE);

			
		 full.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					full.setEnabled(false);
					fittowindow.setEnabled(true);
					model.setScaled(false);
				}
			});
		view.add(full);
		
		 fittowindow = new JMenuItem("Fit to Window");
		 fittowindow.setBackground(new Color(70, 130, 180));
		 fittowindow.setForeground(Color.WHITE);

			fittowindow.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					full.setEnabled(true);
					fittowindow.setEnabled(false);
					model.setScaled(true);
				}
			});

		view.add(fittowindow);
		full.setEnabled(false);
		fittowindow.setEnabled(true);
		model.setScaled(false);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
}
