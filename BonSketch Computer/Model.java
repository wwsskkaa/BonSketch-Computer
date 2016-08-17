

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Observable;
//// got from prof's mvc example 4
//HelloMVC: a simple MVC example
//the model is just a counter 
//inspired by code by Joseph Mack, http://www.austintek.com/mvc/

public class Model extends Observable {	
	
	// the data in the model, just a counter
	private Color[] colors={
			new Color(176, 224, 230),
			new Color(255, 192, 203),
			new Color(169, 169, 169),
			new Color(255, 165, 0),
			new Color(0, 0, 0),
			new Color(255, 255, 0),
			new Color(154, 205, 50),
			new Color(106, 90, 205)
			};
	private ArrayList<Canvas.CanvasShape> shapearray=new ArrayList<Canvas.CanvasShape>();
	private Buttons currentButton=Buttons.SELECTION;
	private int currentThickness;
	private boolean saved=false;
	private boolean selected=false;
	private int selectedindex=-1;
	private boolean scaled=false;
	//private double scalefactor = 1;
	Model() {
		setChanged();
	}
	public Color[] getColorArray()
	{
		return colors;
	}
	public Color getColor(int index)
	{
		return colors[index];
	}
	public void setLocation(int index,double d,double e,double f,double g)
	{		
		if(shapearray.get(index).getButton()!=Buttons.LINE)
		{
			shapearray.get(index).setXcoordinate(d);
			shapearray.get(index).setYcoordinate(e);
		}
		if(shapearray.get(index).getButton()==Buttons.LINE)
		{
			shapearray.get(index).setXcoordinate(d);
			shapearray.get(index).setYcoordinate(e);
			shapearray.get(index).setEndXcoordinate(f);
			shapearray.get(index).setEndYcoordinate(g);
		}
	}
	public void setColors(int index,Color newcolor)
	{
		colors[index]=newcolor;
		Modelupdate();
	}
	public Color getCurrentColor()
	{
		return colors[4];
	}
	public void setCurrentColor(Color newcolor)
	{
		colors[4]=newcolor;
		Modelupdate();
	}

	public int getCurrentThickness() {
		return currentThickness;
	}
	public void setCurrentThickness(int currentThickness) {
		this.currentThickness = currentThickness;
		Modelupdate();
	}
	public Buttons getCurrentButton() {
		return currentButton;
	}
	public void setCurrentButton(Buttons currentButton) {
		this.currentButton = currentButton;
		Modelupdate();
	}
	public ArrayList<Canvas.CanvasShape> getShapearray() {
		return shapearray;
	}
	public void modifycolor() {
		shapearray.get(getSelectedindex()).setColor(getCurrentColor());
		Modelupdate();

	}
	public void cleanArray()
	{
		shapearray.clear();
	}
	public void setShape(Canvas.CanvasShape newshape) {
		this.shapearray.add(newshape);
		Modelupdate();
	}
	
	public void removeShape(int i){
		if(shapearray.size()>i){
			if(selectedindex==i) {
				selectedindex = -1;
				selected = false;
			}
			shapearray.remove(i);
			Modelupdate();
		}
	}
	public void Modelupdate()
	{
		setChanged();
		notifyObservers();
	}
	public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
		Modelupdate();
	}
	public void renew() {
		saved = false;
		cleanArray();
		selected=false;
		selectedindex=-1;
		//scaled=false;
		Modelupdate();
	}
	public boolean isScaled() {
		return scaled;
	}
	public void setScaled(boolean scaled) {
		this.scaled = scaled;
		Modelupdate();

	}
	public void moveShape(int i) {
		
	}
	public String toString()
	{
		String output="";
		output+=shapearray.size()+"\n";
		for(Canvas.CanvasShape i:shapearray)
		{
			output+=shapeConvert(i.getButton())+" "+colorConvert(i.getColor())+" "+colorConvert(i.getFillcolor())+" "+i.getThick()+" "+i.getXcoordinate()+" ";
			output+=i.getYcoordinate()+" "+i.getW()+" "+i.getH()+" "+i.getEndXcoordinate()+" "+i.getEndYcoordinate()+"\n";
		}
		return output;
	}
	public String shapeConvert(Buttons i)
	{
		String output="";
		if(i==Buttons.OVAL)
		{output="OVAL";}
		else if(i==Buttons.SQUARE)
		{output="SQUARE";}
		else if(i==Buttons.LINE)
		{output="LINE";}
		return output;
	}
	public String colorConvert(Color i)
	{	if(i!=null)
		{
			return i.getRed()+" "+i.getGreen()+" "+i.getBlue();
		}
		else
		{
			return "-1 -1 -1";
		}
	}
	public void notifyThickness(int thick) {
		
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public int getSelectedindex() {
		return selectedindex;
	}
	public void setSelectedindex(int selectedindex) {
		this.selectedindex = selectedindex;
	}
	
	public void addShape(String buttonname, Color bordercolor, Color fillcolor, int thickness, double xcoordinate,
			double ycoordinate, double width, double height, double endXcoordinate, double endYcoordinate) {
		
		
		if(buttonname.equals("OVAL"))
		{
		Shape shape=new Ellipse2D.Double(xcoordinate, ycoordinate,width,height);
		shapearray.add(new Canvas.CanvasShape(Buttons.OVAL, shape,bordercolor, fillcolor, thickness, xcoordinate, ycoordinate, width, height, endXcoordinate, endYcoordinate));
		}
		else if(buttonname.equals("SQUARE"))
		{
		Shape shape=new Rectangle2D.Double(xcoordinate, ycoordinate,width,height);
		shapearray.add(new Canvas.CanvasShape(Buttons.SQUARE, shape,bordercolor, fillcolor, thickness, xcoordinate, ycoordinate, width, height, endXcoordinate, endYcoordinate));

		}
		else if(buttonname.equals("LINE"))
		{		Shape shape=new Line2D.Double(xcoordinate, ycoordinate,endXcoordinate,endYcoordinate);
		shapearray.add(new Canvas.CanvasShape(Buttons.LINE,shape,bordercolor, fillcolor, thickness, xcoordinate, ycoordinate, width, height, endXcoordinate, endYcoordinate));

		}
		Modelupdate();
				
	}


}
