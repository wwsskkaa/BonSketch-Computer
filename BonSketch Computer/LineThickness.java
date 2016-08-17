

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;


public class LineThickness extends JToolBar implements Observer {
	private Model model;
			
	private class ThicknessChoice extends JPanel implements Observer{
		int thickness;
		ThicknessChoice(String imagename,int thickness){
			this.thickness=thickness;
			setLayout(new BorderLayout());
			this.setBackground(Color.white);
			add(new JLabel(new ImageIcon(imagename)),BorderLayout.CENTER);

			addMouseListener(new MouseAdapter()  {		
				public void mousePressed(MouseEvent e) {
					if(model.getCurrentThickness()!=thickness )
					{
						model.setCurrentThickness(thickness);
						setBackground(Color.gray);
					}
				
		
				}
			});
		}
		
		public void update(Observable o, Object arg) {
			if(model.getCurrentThickness()!=thickness )
			{	
				setBackground(Color.white);
			}
			else
			{
				setBackground(Color.gray);
			}
			
		}
	
	}
	
	LineThickness(Model m) 
	{
		this.model=m;
		setName("Thickness");
		setPreferredSize(new Dimension(100,100));
		setLayout(new GridLayout(3,1));
		setBackground(new Color(255, 255, 255));
		ThicknessChoice thickness=new ThicknessChoice("thinest.png",3);
		add(thickness);
		model.addObserver(thickness);
		ThicknessChoice thickness2=new ThicknessChoice("middle.png",8);
		add(thickness2);
		model.addObserver(thickness2);
		ThicknessChoice thickness3=new ThicknessChoice("thickest.png",14);
		add(thickness3);
		model.addObserver(thickness3);



	}
	public void update(Observable o, Object arg) {
		
	}
}
