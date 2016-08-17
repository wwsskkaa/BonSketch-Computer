

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;


public class colorPalette extends JToolBar implements Observer {
	private Model model;
	private JPanel panel_1,panel_2,panel_3,panel_4,currentcolor,panel_6,panel_7,panel_8;
	private JPanel[] colorPanel={
			panel_1,panel_2,panel_3,panel_4,currentcolor,panel_6,panel_7,panel_8
	};
	private Color[] colors={
			new Color(155,175,142),

			new Color(193,210,214),
			new Color(227,220,192),
			new Color(176,102,96),
			new Color(0,0,0),
			new Color(202,143,66),
			new Color(193,204,137),
			new Color(0,0,0)};

	private JButton chooserbutton;
	
	colorPalette(Model m) {
	model=m;
	m.addObserver(this);

	setName("Palette");
	setPreferredSize(new Dimension(100,100));
	setLayout(new GridLayout(3,2));
	setBackground(new Color(255, 255, 255));
	for(int i=0;i<8;i++)
	{
		
		colorPanel[i]=new JPanel();
		colorPanel[i].setBackground(colors[i]);
		if(i==4)
		{
			colorPanel[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		}
		add(colorPanel[i]);
	}

	
	colorPanel[0].addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(!SwingUtilities.isRightMouseButton(e))
			{
			colorPanel[4].setBackground(colors[0]);
			colors[4]=colors[0];
			model.setCurrentColor(colors[0]);
				if(model.isSelected())
				{	
					model.modifycolor();
				}
				
			}
			else
			{
				
				Color backgroundColor = JColorChooser.showDialog(chooserbutton,
			               "Choose your own color", Color.white);
			            if(backgroundColor != null){
			            	colors[0]=backgroundColor;
			    			colorPanel[0].setBackground(colors[0]);
			            	model.setColors(0,backgroundColor);
		}}
		}
	});
	colorPanel[1].addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(!SwingUtilities.isRightMouseButton(e))
			{
			colorPanel[4].setBackground(colors[1]);
			colors[4]=colors[1];
			model.setCurrentColor(colors[1]);
			if(model.isSelected())
			{	
				model.modifycolor();
			}
			}
			else
			{
				Color backgroundColor = JColorChooser.showDialog(chooserbutton,
			               "Choose your own color", Color.white);
			            if(backgroundColor != null){
			            	colors[1]=backgroundColor;
			    			colorPanel[1].setBackground(colors[1]);
			            	model.setColors(1,backgroundColor);
		}}
			
		}
	});
	colorPanel[2].addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(!SwingUtilities.isRightMouseButton(e))
			{
			colorPanel[4].setBackground(colors[2]);
			colors[4]=colors[2];
			model.setCurrentColor(colors[2]);
			if(model.isSelected())
			{	
				model.modifycolor();
			}
		}
			else
			{
				Color backgroundColor = JColorChooser.showDialog(chooserbutton,
			               "Choose your own color", Color.white);
			            if(backgroundColor != null){
			            	colors[2]=backgroundColor;
			    			colorPanel[2].setBackground(colors[2]);
			            	model.setColors(2,backgroundColor);
		}}
		}
	});
	colorPanel[3].addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(!SwingUtilities.isRightMouseButton(e))
			{
			colorPanel[4].setBackground(colors[3]);
			colors[4]=colors[3];
			model.setCurrentColor(colors[3]);
			if(model.isSelected())
			{	
				model.modifycolor();
			}
			}
			else
			{
				Color backgroundColor = JColorChooser.showDialog(chooserbutton,
			               "Choose your own color", Color.white);
			            if(backgroundColor != null){
			            	colors[3]=backgroundColor;
			    			colorPanel[3].setBackground(colors[3]);
			            	model.setColors(3,backgroundColor);
		}}
		}
	});
	colorPanel[4].addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(!SwingUtilities.isRightMouseButton(e))
			{
			colorPanel[4].setBackground(colors[4]);
			model.setCurrentColor(colors[4]);
			if(model.isSelected())
			{	
				model.modifycolor();
			}
		}
		else
		{
			Color backgroundColor = JColorChooser.showDialog(chooserbutton,
		               "Choose your own color", Color.white);
		            if(backgroundColor != null){
		            	colors[4]=backgroundColor;
		    			colorPanel[4].setBackground(colors[4]);
		            	model.setColors(4,backgroundColor);
	}}
		}
	});
	
	colorPanel[5].addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(!SwingUtilities.isRightMouseButton(e))
			{
			colorPanel[4].setBackground(colors[5]);
			colors[4]=colors[5];
			model.setCurrentColor(colors[5]);
			if(model.isSelected())
			{	
				model.modifycolor();
			}
			}
			else
			{
				Color backgroundColor = JColorChooser.showDialog(chooserbutton,
			               "Choose your own color", Color.white);
			            if(backgroundColor != null){
			            	colors[5]=backgroundColor;
			    			colorPanel[5].setBackground(colors[5]);
			            	model.setColors(5,backgroundColor);
		}}
		}
	});
	colorPanel[6].addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(!SwingUtilities.isRightMouseButton(e))
			{
			colorPanel[4].setBackground(colors[6]);
			colors[4]=colors[6];
			model.setCurrentColor(colors[6]);
			if(model.isSelected())
			{	
				model.modifycolor();
			}
			}
			else
			{
				Color backgroundColor = JColorChooser.showDialog(chooserbutton,
			               "Choose your own color", Color.white);
			            if(backgroundColor != null){
			            	colors[6]=backgroundColor;
			    			colorPanel[6].setBackground(colors[6]);
			            	model.setColors(6,backgroundColor);
		}}
        	}
	});
	colorPanel[7].addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(!SwingUtilities.isRightMouseButton(e))
			{
			colorPanel[4].setBackground(colors[7]);
			colors[4]=colors[7];
			model.setCurrentColor(colors[7]);
			if(model.isSelected())
			{	
				model.modifycolor();
			}
		}
		else
		{
			Color backgroundColor = JColorChooser.showDialog(chooserbutton,
		               "Choose your own color", Color.white);
		            if(backgroundColor != null){
		            	colors[7]=backgroundColor;
		    			colorPanel[7].setBackground(colors[7]);
		            	model.setColors(7,backgroundColor);
	}}
		}
	});

	
	 chooserbutton = new JButton();
	chooserbutton.setIcon(new ImageIcon("small.png"));

	chooserbutton.addMouseListener(new MouseAdapter()  {
		public void mousePressed(MouseEvent e) {

			Color backgroundColor = JColorChooser.showDialog(chooserbutton,
		               "Choose your own color", Color.white);
		            if(backgroundColor != null){
			model.setCurrentColor(backgroundColor);
			if(model.isSelected())
			{	
				model.modifycolor();
			}
		            }
		}
	});
	chooserbutton.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
	chooserbutton.setPreferredSize(new Dimension(132,60));
	chooserbutton.setForeground(new Color(70, 130, 180));
	add(chooserbutton);
	

	}
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		colors[4]=model.getCurrentColor();
		colorPanel[4].setBackground(model.getCurrentColor());
	}
}
