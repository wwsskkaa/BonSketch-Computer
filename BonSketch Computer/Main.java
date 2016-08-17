
// got from prof's mvc example 4
//HelloMVC: a simple MVC example
// the model is just a counter 
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

/**
 *  Two views with integrated controllers.  Uses java.util.Observ{er, able} instead
 *  of custom IView.
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;	

public class Main{

	public static void main(String[] args){	
		JFrame frame = new JFrame("BonSketch");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		Model model = new Model();
		ScrollBar scroll=new ScrollBar(model);
		model.addObserver(scroll);
		Toolkit2 view = new Toolkit2(model);
		view.setBackground(new Color(255, 250, 205));
		model.addObserver(view);
		Canvas canvas = new Canvas(model);
		canvas.setBackground(new Color(255, 245, 238));
		model.addObserver(canvas);
		model.notifyObservers();
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		panel.add(view, BorderLayout.WEST);
		scroll.getViewport().add(canvas);
		panel.add(scroll, BorderLayout.CENTER);
		ToolButton tool=new ToolButton(model);
		JPanel toolpanel = new JPanel(new BorderLayout());
		JPanel palettepanel = new JPanel(new BorderLayout());
		JPanel thickpanel = new JPanel(new BorderLayout());
		toolpanel.setBackground(new Color(193,204,137));
		palettepanel.setBackground(new Color(193,204,137));
		thickpanel.setBackground(new Color(193,204,137));
		view.add(toolpanel);
		view.add(palettepanel);
		view.add(thickpanel);		
		colorPalette palette = new colorPalette(model);		
		LineThickness thick = new LineThickness(model);	
		toolpanel.add(tool);
		palettepanel.add(palette);
		thickpanel.add(thick);
		Menu menuBar = new Menu(model);
		menuBar.setBackground(new Color(230, 230, 250));
		panel.add(menuBar, BorderLayout.NORTH);
		frame.setPreferredSize(new Dimension(600,500));
		frame.setMinimumSize(new Dimension(600,500));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.pack();
		tool.setMaximumSize(new Dimension(view.getWidth(),view.getHeight()/3));
		palette.setMaximumSize(new Dimension(view.getWidth(),view.getHeight()/3));
		thick.setMaximumSize(new Dimension(view.getWidth(),view.getHeight()/3));
		canvas.setFocusable(true);
		canvas.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				canvas.setSize();
			   
			    
			}
		});
			
		

	} 
}
