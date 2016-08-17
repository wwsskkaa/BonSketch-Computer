
//got from prof's mvc example 4
//HelloMVC: a simple MVC example
//the model is just a counter 
//inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;	
import java.util.Observable;
import java.util.Observer;

class Toolkit2 extends JPanel implements Observer {
	// the view's main user interface
	// the model that this view is showing
	private Model model;
	Toolkit2(Model m) {

		this.setLayout(new GridLayout(3,1));
		model = m;
		
	} 

	// Observer interface 
	public void update(Observable arg0, Object arg1) {
	}
} 
