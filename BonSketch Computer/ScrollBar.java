

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;

	
public class ScrollBar extends JScrollPane implements Observer {
	private Model model;

	ScrollBar(Model m)
	{
		
		model=m;
		setBackground(new Color(230, 230, 250));
		getViewport().setBackground(Color.DARK_GRAY);

}

	@Override
	public void update(Observable o, Object arg) {
		if (!model.isScaled()) {
			getViewport().setLayout(null);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setPreferredSize(null);
	} else {
		getViewport().setLayout(new BorderLayout());
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
}
	}
}