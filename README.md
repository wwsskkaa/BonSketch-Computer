# BonSketch-Computer Version

Copyright (c) | 2016 | Shuang Wu | University of Waterloo 

1.OS: Windows 7
2.JDK: JAVA SE 1.8
3.IDE: ECLIPSE MARS

Running instruction:
call make to compile
call make clean to clean .class
call make run to run with default values

It is the computer version of Sketch app, you can find the mobile version in another repo.

1)Description:
MVC:
	MVC is used for this application, there are one model and so many views. 
	Below is more detailed class description:
		1)Main function:   Main.java
		2)Enum interface:  Buttons.java  
		3)Model:  Model.java
		4)Views and Controller:
			Canvas.java: responsible of the view of the entire canvas, including the mouse events and key events, drawing and dragging in canvas
			colorPalette.java: responsible of the view of color palette, it is observing the model because when a shape is selected, the middle panel of the palette which is the current color has to be adjusted to the certain color chosen.
			LineThickness.java:  responsible of the view of line thickness, it is observing the model because when a shape is selected, the certain thickness chosen gets to become grey.
			Menu.java:  responsible of the view of menu.
			ScrollBar.java: responsible of the view of the scrollbar, it is an observer, and updates the scrollbar status.
			ToolButton.java:  responsible of the view of tools such as selection, erase, line, it is observing the model and need to update selection status. 
			Toolkits2.java: it is the bottom panel which has a grid layout of 3 that contains the color palette, linethickness and tools panels.


1.Menu bar:
	When choose new file, everything got cleared and a new canvas appears, but the tool kits are not refreshed, they stay the old status.
	When choose load file, the canvas is going to be cleared,actually need to click the saved file directly.If the saved file is test.txt, need to have test.txt as the name.
	When choose save file, its going to save everything as a txt file, if want the name to be test.txt, just save as test, it will automatically add .txt to it.
	when choose full size, the canvas is a square canvas, and the initial height and width of the canvas is 500*500.every time we restart the program, fullsize is the default choice for viewing choice. So the scrollbar is there initially,but the policy of scrollbar is AS_NEEDED, so if there is no need to scroll, the scrollbar disappears.
	when choose fit to window, the scrollbar disappears, the canvas is going to be fit to window but still stay in square shape,so it will take the minimum of height and width and set that as the dimension in order to enforce uniform scaling. When the window gets larger, the canvas grows too and the shapes get scaled the line is going to be much thicker if stretched too big than original size.
	and once switched back to full size, its going to be bounced back to original size. if one choices between full size and fit to window is selected, it becomes disabled button and only can choose the other one.
2.	Tools:
	selection:user can select a shape that has been drawn, just click on it and the shape is going to have white dotted edges.(if the edge is already white, its going to be grey dotted edges) once you select it, you have to press esc to quit selecting, and also if selected, the color palette and line thickness is going to be automatically adjusted to the current thickness and color of the selected shape. And no matter what color you choose from the palette, the edge of the shape is going to be changed that color. You can select a shape and drag it around.
	line:there is a default color for drawing, but there is no default thickenss for drawing, so have to actually have to pick thickness to start draw, otherwise nothing on canvas.
	circle:its using the same behavior as graphical interface such as photoshop, so if user is not dragging the mouse on a perfectly square diagonal, we are taking the minimum between x and y to become the diameter of the circle.
	rectangle: just draw ranctangle
	fill: select this tool, and clock on a shape to fill the shape with currently selected color. the currently selected color is shown in the middle of color palette.
	erase:clich on this tool and then clock on a shape to delete it.
	color palette:I have 7 colors in the panel, but the middle one can also be editted, and the button on the right bottom corner  is a color chooser, after choosing it, the middle color is going to be switched into the color you choose.
	line thickness:there are three 3 line widths that user can select
3.	Handling overlapping shapes:
	The shapes are stored in a arraylist in the order of drawing, the later shape gets drawed, more prior it is. When I draw a line and on top of it I draw a circle, when I click the line, the circle is going to get selected.
4.	Layout:
	I used border layout and grid layout for this project, the canvas is added to a jscrollpane which is the center of border layout.
	The tool palettes evenly spaced on the left side of the frame and going to resize as canvas grows. 
	There is a minimum size for my application window which is 600*500 ,there is no limit to max size.

5. I also did Undockable toolbars: toolbars can be undocked to create floating palettes, 
  and if you want to go back to the original spot, just close the window, it will be back to place.
6. I did a Customizable color palette, every single color from the color palette can be replaced by other colors.
  All you have to do is right click on the color and there will be a color chooser popped up.

4)photo license and credit
http://www.flaticon.com/free-icon/cursor-arrow_60463#term=fill&page=1&position=25
http://www.flaticon.com/free-icon/diagonal-line_109604#term=line&page=1&position=58
http://www.flaticon.com/free-icon/square_136830#term=square&page=4&position=66
http://www.flaticon.com/free-icon/printing-colors-chart_29233#term=color choose&page=1&position=2
http://www.flaticon.com/free-icon/choosing-color-with-dropper-on-a-colors-wheel_29318#term=color choose&page=1&position=3
http://www.flaticon.com/free-icon/oval_136832#term=oval&page=1&position=2
http://www.flaticon.com/free-icon/eraser_106887#term=eraser&page=1&position=25
http://www.flaticon.com/free-icon/paint-bucket_67968#term=color&page=1&position=32
http://www.flaticon.com/free-icon/paint-board-and-brush_67745#term=color&page=1&position=3
http://www.flaticon.com/free-icon/cursor_99173#term=cursor&page=1&position=49
http://www.flaticon.com/free-icon/minus_130480#term=line&page=4&position=11
http://www.flaticon.com/free-icon/minus_151856#term=line&page=4&position=37
http://www.flaticon.com/free-icon/minus-symbol_7659#term=line&page=10&position=16
http://www.flaticon.com/free-icon/subtract_22336#term=line&page=11&position=60