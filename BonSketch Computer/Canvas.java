
//got from prof's mvc example 4
//HelloMVC: a simple MVC example
//the model is just a counter 
//inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Canvas extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	private int CANVAS_SIZE=500;
	private Point start,end;
	private CanvasShape currentShape;
	private int currentSelected=-1;
	private double scalefactor=1;
	private double preX, preY;
	final static float dash1[] = {10.0f};
     static BasicStroke dashed =
        new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);

	public static class CanvasShape implements Observer {
		
		private Color bordercolor;
		private Color fillcolor;
		private Shape shape;
		private Buttons button;
		private int thick;
		private boolean selected=false;
		private double Xcoordinate;
		private double Ycoordinate;
		private double endXcoordinate;
		private double endYcoordinate;
		private double w;
		private double h;

		CanvasShape(Color c,Buttons b,int t)
		{
			bordercolor=c;
			button=b;
			thick=t;
		}
		CanvasShape(Buttons button,Shape shape,Color bordercolor,Color fillcolor,int thick,double Xcoordinate, double Ycoordinate,double w,double h,double endXcoordinate,double endYcoordinate)
		{
			this.button=button;
			this.shape=shape;
			this.bordercolor=bordercolor;
			this.fillcolor=fillcolor;
			this.thick=thick;
			this.Xcoordinate=Xcoordinate;
			this.Ycoordinate=Ycoordinate;
			this.endXcoordinate=endXcoordinate;
			this.endYcoordinate=endYcoordinate;
			this.w=w;
			this.h=h;
		}
	
		public void loadDraw()
		{
			if(button==Buttons.SQUARE)
			{shape=new Rectangle2D.Double(Xcoordinate, Ycoordinate,w,h);}
			else if(button==Buttons.OVAL)
			{shape=new Ellipse2D.Double(Xcoordinate, Ycoordinate,w,h);}
			else if(button==Buttons.LINE)
			{shape=new Line2D.Double(Xcoordinate, Ycoordinate,endXcoordinate,endYcoordinate);}
		}
		
		public void setShape(Point start, Point end){
			double width=Math.abs(start.getX()-end.getX());
			double height=Math.abs(start.getY()-end.getY());
			double x0 = Math.min(start.getX(), end.getX());
			double y0 = Math.min(start.getY(), end.getY());
			double diameter=Math.min(width, height);
			
			if(button==Buttons.SQUARE)
			{
				shape=new Rectangle2D.Double(x0, y0,width,height);
				Xcoordinate=x0;
				Ycoordinate=y0;
				setEndXcoordinate(-1);
				setEndYcoordinate(-1);
				setW(width);
				setH(height);
				
			}
			else if(button==Buttons.OVAL)
			{
				if(end.getX()>=start.getX())
				{
					shape=new Ellipse2D.Double(x0, y0,diameter,diameter);
					Xcoordinate=x0;
					Ycoordinate=y0;
					setEndXcoordinate(-1);
					setEndYcoordinate(-1);
					setW(diameter);
					setH(diameter);
				}
				else
				{
					if(end.getY()<=start.getY())
					{
					shape=new Ellipse2D.Double(start.getX()-diameter, start.getY()-diameter,diameter,diameter);
					Xcoordinate=start.getX()-diameter;
					Ycoordinate=start.getY()-diameter;
					setEndXcoordinate(-1);
					setEndYcoordinate(-1);
					setW(diameter);
					setH(diameter);
					}
					else
					{
					shape=new Ellipse2D.Double(start.getX()-diameter, start.getY(),diameter,diameter);
					Xcoordinate=start.getX()-diameter;
					Ycoordinate=start.getY();
					setEndXcoordinate(-1);
					setEndYcoordinate(-1);
					setW(diameter);
					setH(diameter);

					}
				}
			}
			else if(button==Buttons.LINE)
			{
				shape=new Line2D.Double(start.getX(), start.getY(),end.getX(),end.getY());
				Xcoordinate=start.getX();
				Ycoordinate=start.getY();
				setEndXcoordinate(end.getX());
				setEndYcoordinate(end.getY());
				setW(-1);
				setH(-1);
				
			}
		}

		public void drawFrame(Graphics2D g2) {
			dashed =
			        new BasicStroke(getThick(),
			                        BasicStroke.CAP_BUTT,
			                        BasicStroke.JOIN_MITER,
			                        10.0f, dash1, 0.0f);
		    g2.setStroke(dashed);
			g2.setColor(Color.WHITE);
			 if(getColor()==Color.WHITE)
			    {g2.setColor(Color.gray);}
			g2.draw(getShape());

			
		}
		
		
		public void drawShape(Graphics2D g2) {
						
			if(fillcolor!=null)
			{
				g2.setColor(getFillcolor());
				g2.fill(getShape());
			}

			g2.setColor(getColor());
			g2.setStroke(new BasicStroke(getThick()));
			g2.draw(getShape());
			if(selected)
			{
				drawFrame(g2);
			}			
			
		}
		
		@Override
		public void update(Observable o, Object arg) {
			
		}
		public Color getColor() {
			return bordercolor;
		}

		public void setColor(Color color) {
			this.bordercolor = color;
		}
		public Buttons getButton(){
			return button;
		}

		public Shape getShape() {
			return shape;
		}

		public void setShape(Shape shape) {
			this.shape = shape;
		}

		public int getThick() {
			return thick;
		}

		public void setThick(int thick) {
			this.thick = thick;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		
		}


		public Color getFillcolor() {
			return fillcolor;
		}


		public void setFillcolor(Color fillcolor) {
			this.fillcolor = fillcolor;
		}


		public double getXcoordinate() {
			return Xcoordinate;
		}


		public void setXcoordinate(double xcoordinate) {
			Xcoordinate = xcoordinate;
		}


		public double getYcoordinate() {
			return Ycoordinate;
		}


		public void setYcoordinate(double ycoordinate) {
			Ycoordinate = ycoordinate;
		}


		public double getEndXcoordinate() {
			return endXcoordinate;
		}


		public void setEndXcoordinate(double endXcoordinate) {
			this.endXcoordinate = endXcoordinate;
		}


		public double getEndYcoordinate() {
			return endYcoordinate;
		}


		public void setEndYcoordinate(double endYcoordinate) {
			this.endYcoordinate = endYcoordinate;
		}


		public double getW() {
			return w;
		}


		public void setW(double w) {
			this.w = w;
		}


		public double getH() {
			return h;
		}


		public void setH(double h) {
			this.h = h;
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.scale(scalefactor, scalefactor);
		for(CanvasShape i:model.getShapearray())
		{
			i.drawShape(g2);
		}
		if(currentShape!=null&&start!=null&&end!=null)
		{
			currentShape.drawShape(g2);
		}
		
	}
	private boolean lineIntersect(Shape s,Point p,double t)
	{
		
		double x=p.getX()-t/2;
		double y=p.getY()-t/2;
		return s.intersects(x, y, t, t);
	}
	Canvas(Model model_) {
		model = model_;
		
	
		
		
		
		setFocusable(true);
		requestFocus();
		addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ESCAPE)
				
				{
					if(currentSelected!=-1)
					{
						currentSelected=-1;
						for(int j=model.getShapearray().size()-1;j>=0;j--)
						{
								
								model.getShapearray().get(j).setSelected(false);


						}
						model.setSelected(false);
						model.setSelectedindex(-1);
						repaint();
						
					}
			}
			}
		});	
		addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent e) {
				end=new Point((int) (e.getX()/scalefactor), (int) (e.getY()/scalefactor));
				if(model.getCurrentButton()!=Buttons.SELECTION)
				{
					if(currentShape!=null)
					{
						currentShape.setShape(start,end);
						repaint();
					}
				}
				else
				{
					if(currentSelected!=-1)
					{
						//if (!pressOut)
						 updateLocation(currentSelected,e);
					}
				}
				
			}
		});

		
	
		
		addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					start = e.getPoint();
					
					double newx=start.getX()/scalefactor;
					double newy=start.getY()/scalefactor;
					
					
					
					start = new Point((int) newx, (int) newy);
					
					
					if(model.getCurrentButton()==Buttons.SQUARE||model.getCurrentButton()==Buttons.OVAL||model.getCurrentButton()==Buttons.LINE)
					{
						if(model.getCurrentThickness()!=0)
						{
							currentShape = new CanvasShape(model.getCurrentColor(), model.getCurrentButton(), model.getCurrentThickness());
						}

					}
					else if (model.getCurrentButton()==Buttons.ERASE)
					{
						ArrayList<Canvas.CanvasShape> shapes=model.getShapearray();
						for(int i=shapes.size()-1;i>=0;i--)
						{
							if((shapes.get(i).getButton()==Buttons.LINE&&lineIntersect(shapes.get(i).getShape(),start,10))||shapes.get(i).getShape().contains(start))
							{
								model.removeShape(i);
								repaint();
								break;
							}
						}
					}
					else if (model.getCurrentButton()==Buttons.SELECTION)
					{
						ArrayList<Canvas.CanvasShape> shapes=model.getShapearray();
						for(int i=shapes.size()-1;i>=0;i--)
						{
							if((shapes.get(i).getButton()==Buttons.LINE&&lineIntersect(shapes.get(i).getShape(),start,10))||shapes.get(i).getShape().contains(start))
							{
								//model.moveShape(i);
								currentSelected=i;
								model.setSelected(true);
								model.setSelectedindex(i);
								shapes.get(i).setSelected(true);
								for(int j=shapes.size()-1;j>=0;j--)
								{
									if(j!=i)
									{
										shapes.get(j).setSelected(false);
									}
								}
								if(shapes.get(i).getFillcolor()!=null)
								{
									model.setCurrentColor(shapes.get(i).getFillcolor());
								}
								else
								{
									model.setCurrentColor(shapes.get(i).getColor());
								}
								model.setCurrentThickness(shapes.get(i).getThick());
								
								
						
					
								preX = shapes.get(i).getShape().getBounds().getX() - start.getX(); 
								preY = shapes.get(i).getShape().getBounds().getY() - start.getY();

								
								
								
								repaint();
								break;
							}else{currentSelected=-1;
								model.setSelected(false);
								model.setSelectedindex(-1);}
							
						}
					}
					else if (model.getCurrentButton()==Buttons.FILL)
					{

						ArrayList<Canvas.CanvasShape> shapes=model.getShapearray();
						for(int i=shapes.size()-1;i>=0;i--)
						{
							if((shapes.get(i).getButton()==Buttons.LINE&&lineIntersect(shapes.get(i).getShape(),start,10)))
							{
								shapes.get(i).setFillcolor(model.getCurrentColor());
								shapes.get(i).setColor(model.getCurrentColor());
								model.Modelupdate();
								//repaint();
								break;
							}
							else if((shapes.get(i).getButton()==Buttons.OVAL||shapes.get(i).getButton()==Buttons.SQUARE)&&shapes.get(i).getShape().contains(start))
							{
								shapes.get(i).setFillcolor(model.getCurrentColor());
								model.Modelupdate();
								//repaint();
								break;
							}
						}
						repaint();

					}
				}
				public void mouseReleased(MouseEvent e) {
					if(model.getCurrentButton()!=Buttons.SELECTION)
					{
						if(currentShape!=null&&start!=null&&end!=null){
							model.setShape(currentShape);
							currentShape = null;
						}
						
						start=null;
						end=null;
					}

					
				}
				
		});
	}

	public void updateLocation(int i ,MouseEvent e) {
		AffineTransform at = new AffineTransform();
		CanvasShape sh = model.getShapearray().get(i);
		double originalstartx=sh.getXcoordinate();
		double originalstarty=sh.getYcoordinate();
		double originalendx=sh.getEndXcoordinate();
		double originalendy=sh.getEndYcoordinate();
		double boundsX = sh.getShape().getBounds().getX();
		double boundsY = sh.getShape().getBounds().getY();
	
		at.translate( preX + e.getX()/scalefactor - boundsX , preY + e.getY()/scalefactor - boundsY);
		
		
		sh.setShape(at.createTransformedShape(sh.getShape()));
		double newboundX=sh.getShape().getBounds().getX();
		double newboundY=sh.getShape().getBounds().getY();
		double xdiff=boundsX-newboundX;
		double ydiff=boundsY-newboundY;
		double newstartx=originalstartx-xdiff;
		double newstarty=originalstarty-ydiff;
		double newendx=originalendx-xdiff;
		double newendy=originalendy-ydiff;
		model.setLocation(i, newstartx,newstarty,newendx,newendy);
		repaint();
	  }
	
	public void setSize() {
		
		if(model.isScaled()) {
			setLayout(new BorderLayout());
			
			int parentwidth=getParent().getParent().getWidth();
			int parentheight=getParent().getParent().getHeight();

			if(parentwidth<=parentheight)
			{
				setSize(parentwidth, parentwidth);
			}
			else
			{
				setSize(parentheight, parentheight);
			}
		} else {
			setLayout(null);
			setSize(new Dimension(CANVAS_SIZE,CANVAS_SIZE));
			setPreferredSize(new Dimension(CANVAS_SIZE,CANVAS_SIZE));
		}
		scalefactor=(double)getHeight()/(double)CANVAS_SIZE;
		repaint();
	}
	
	public void update(Observable o, Object arg) {
		setSize();
		setFocusable(true);
		requestFocus();
	
        validate();
        repaint();
}}
