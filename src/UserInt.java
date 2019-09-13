/**
 * @(#)UserInt.java
 *
 *
 * @author PINAKI KUNDU
 * @version 1.00 2008/5/18
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

class SampleFrame extends JFrame implements ActionListener {
	String str;
	int msg;
	TextField tf;

	SampleFrame(String title){
		super(title);
	//	MyWindowAdapter adapter=new MyWindowAdapter(this);
	//	addWindowListener(adapter);
		Container content=getContentPane();

		JPanel cp = new JPanel();
		int n;

		JButton b1=new JButton("Reset");
		b1.setToolTipText("Click this button to clear the field");
		JButton b=new JButton("OK");
		getRootPane().setDefaultButton(b);
		b.setToolTipText("Click this button to give an I/P");
		JLabel lbl=new JLabel("Automata Simulator");
		lbl.setForeground(Color.magenta);
		tf=new TextField(35);
		tf.setBackground(Color.LIGHT_GRAY);
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		setFont(new Font("Times new roman", Font.BOLD, 25));
		content.add(lbl);
		content.add(tf);
		content.add(b);
		content.add(b1);

		setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(cp);
		((JPanel)getContentPane()).setOpaque(false);
	    ImageIcon icon = new ImageIcon("Projectpic.jpeg");
	    JLabel backlabel = new JLabel(icon);
	    getLayeredPane().add(backlabel,new Integer(Integer.MIN_VALUE));
	    backlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

		b1.addActionListener(this);
		b.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed (ActionEvent ae)
	{
		String gac=ae.getActionCommand();
		if(gac.equals("Reset")){
			tf.setText("");
			tf.setForeground(Color.black);
		}
		else {
			str=tf.getText();
			int n=str.indexOf('^');
			if(str.equals(""))
			{
				tf.setForeground(Color.red);
				tf.setText("Enter any I/P,Then Press OK");
			}
			else if(n>=0){
				tf.setForeground(Color.red);
				tf.setText(str+"(You shouldnot use '^' in I/P string)");
			}
			else{
				InsrtDotOprtr ref=new InsrtDotOprtr(str);
			}
		}
	}
}

public class UserInt {
	public static void main(String args[]){

		SampleFrame f=new SampleFrame("Input");
		f.setSize(300,225);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
	}
}