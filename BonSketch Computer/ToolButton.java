
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;


public class ToolButton extends JToolBar implements Observer{
	private Model model;
	private LittleButton selectiontool,circledraw,erasetool,rect,linedrawing,fill;
	private class LittleButton extends JPanel implements Observer{
		private Buttons button;
		
		LittleButton(String imagename,Buttons button){
			this.button=button;
			this.setBackground(Color.white);
			setLayout(new BorderLayout());
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				add(new JLabel(new ImageIcon(imagename)),BorderLayout.CENTER);
				
			
			addMouseListener(new MouseAdapter()  {
				
				public void mousePressed(MouseEvent e) {
					if(button==model.getCurrentButton() ){
						model.setCurrentButton(null);
					} else {
						model.setCurrentButton(button);
						setBackground(Color.gray);

					}
					
				}
			});
		}

		@Override
		public void update(Observable o, Object arg) {
			if(button!=model.getCurrentButton() )
			{	setBackground(Color.white);
			}
		
			
		}
	};
	
	ToolButton(Model m) {
	this.model=m;
	setName("Toolkit");
	setPreferredSize(new Dimension(150,170));
	setLayout(new GridLayout(3,2));
	setBackground(new Color(255, 255, 255));
	
	 selectiontool = new LittleButton("cursor.png",Buttons.SELECTION);
	 model.addObserver(selectiontool);
	add(selectiontool);
	
	circledraw = new LittleButton("oval.png",Buttons.OVAL);
	 model.addObserver(circledraw);
	add(circledraw);
	
	erasetool = new LittleButton("eraser.png",Buttons.ERASE);
	 model.addObserver(erasetool);
	add(erasetool);

	rect = new LittleButton("square.png",Buttons.SQUARE);
	 model.addObserver(rect);
	add(rect);

	linedrawing = new LittleButton("diag.png",Buttons.LINE);
	 model.addObserver(linedrawing);
	add(linedrawing);
	
	fill = new LittleButton("fill.png",Buttons.FILL);
	 model.addObserver(fill);
	add(fill);

	}

	@Override
	public void update(Observable o, Object arg) {
	
	}
	
	
}
