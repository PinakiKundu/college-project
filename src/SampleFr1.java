/**
 * @(#)SampleFr1.java
 *
 *
 * @author Pinaki Kundu
 * @version 1.00 2008/5/18
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import javax.swing.event.*;

class SampleFr1 extends JFrame implements MouseListener,MouseMotionListener{//for the output window<<call the constructor of this class for graph
	
	JFrame frame;
    JPanel panel;
    int mousex=0,mousey=0,k,i;
    int diameter;
    int xf,yf,xt,yt;
    int flag;
    int xm,ym;
    String sssss;int mxmax,mxmin,mymax,mymin,mx,my;
	String str1[][]=new String[100][4];//for the node table....means(node,x-coordinate,y-coordinate,tag)
	String str2[][]=new String[100][3];
	Point[] p=new Point[100];//point used for the nodes(circles) coordinate
	Point[] pf=new Point[100];
	Point[] pt=new Point[100];
	int sc1,ss2;//sc1 for str1,ss2 for str2
	int arhx[]=new int[100];int arhy[]=new int[100];//for arrow head
		
	SampleFr1(String title,String s1[][],String s2[][],int s1cnt,int s2cnt){ //parameter of this constructor shpuld be changed as per requirment

		frame = new JFrame(title);	
		
		str1=s1;
		str2=s2;
		sc1=s1cnt;
		ss2=s2cnt;
		
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		int i;
		for(i=0;i<sc1;i++) {//i for collum
		System.out.println("samplefrm1 "+i);
			p[i]=new Point(Integer.parseInt(str1[i][1]),Integer.parseInt(str1[i][2]));//p[i]
		System.out.println(p[i]);
		}
	}
	public void mouseClicked(MouseEvent me){
	}
	public void mouseExited(MouseEvent me){
	}
	public void mouseEntered(MouseEvent me){
	}
	public void mousePressed(MouseEvent me){
		mousex=me.getX();
		mousey=me.getY();
		mxmax=mousex+25;
		mxmin=mousex-25;
		mymax=mousey;
		mymin=mousey-40;
		System.out.println (mousex+mxmax+mxmin);
		for(i=0;i<sc1;i++){
			mx=p[i].x;
			my=p[i].y;
			if(mxmin<=mx&&mx<=mxmax){
				if(mymin<=my&&my<=mymax){
				k=i;flag=1;
				System.out.println (i);}
			}
		}
	}
	public void mouseDragged(MouseEvent me){
			mousex=me.getX();
			mousey=me.getY();
			if(flag==1){
				p[k].x=mousex;
				p[k].y=mousey;
				frame.repaint();				
				mousex=mousey=0;
			}
		}
		public void mouseReleased(MouseEvent me){
			flag=0;k=0;frame.repaint();
			
		}
		public void mouseMoved(MouseEvent me){

		}
		public void makeUI() {
			panel = new JPanel() {
				
				public void paintComponent(Graphics g) { //main graph drawing part
				
				Graphics2D g2 = (Graphics2D)g;
				super.paintComponent(g2);
											
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				for(i=0;i<ss2;i++){//assigning the the values of FROM node and TO node
					pf[i]=p[Integer.parseInt(str2[i][0])];
					pt[i]=p[Integer.parseInt(str2[i][2])];
					if(pf[i].x==pt[i].x&&pf[i].y==pt[i].y){
						arhx[i]=10000;
						arhy[i]=10000;
					}
					else{
						arhx[i]=(pf[i].x*1+pt[i].x*9)/10;
						arhy[i]=(pf[i].y*1+pt[i].y*9)/10;
					}					
				}
				for(i=0;i<ss2;i++){//to print the value like a,b,c...
					xf=pf[i].x;
					yf=pf[i].y;
					xt=pt[i].x;
					yt=pt[i].y;
					xm=(xf+xt*3)/4;
					ym=((yf+yt*3)/4)-5;
					sssss=str2[i][1];
					g2.setColor(Color.black);
					if(xf==xt&&yf==yt){
						g2.drawArc(xf,yf,35,20,0,360);
						g2.drawString(sssss,xf+15,yf+12);
					}
					else{
						g2.drawLine(xf,yf,xt,yt);
						g2.setColor(Color.black);
						g2.drawString(sssss,xm,ym);
					}
				}
				for(i=0;i<sc1;i++) {//for loop to draw circles
	
					int   _x;
   			 		int   _y;
					_x=(p[i].x-10);
					_y=(p[i].y-10);
					if(str1[i][3].equals("1")){
						g2.setColor(Color.PINK);
						g2.fillOval(_x, _y,25,25);
					_x=0;_y=0;
				}
				else if(str1[i][3].equals("2")){
					g2.setColor(Color.CYAN);
					g2.fillOval(_x, _y,25,25);_x=0;_y=0;
				}
				else if(str1[i][3].equals("4")){
					g2.setColor(Color.YELLOW);
					g2.fillOval(_x, _y,25,25);_x=0;_y=0;
				}
				else{
					g2.setColor(Color.ORANGE);
					g2.fillOval(_x, _y,25,25);_x=0;_y=0;
				}
			}

			for(i=0;i<sc1;i++){//to put the node name like 1,2...i'll changed it to Q0,Q1,... later
				g2.setColor(Color.BLACK);
				g2.drawString(Integer.toString(i),p[i].x,p[i].y);
			}
			for(i=0;i<ss2;i++){
				g2.fillOval(arhx[i],arhy[i],7,7);
			}
		}
	};
	frame.setContentPane(panel);
    frame.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setLayout(new BorderLayout());
    JLabel lbl1=new JLabel("COLOR CODE:PINK for starting node,CYAN for ending node,YELLOW for general node,ORANGE for starting and ending both");
	lbl1.setForeground(Color.BLUE);
	frame.add("South",lbl1);
}
}

