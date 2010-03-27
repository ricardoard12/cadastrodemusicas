package gui.about;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class About extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2306521713890967794L;
	private JButton close;

     public About(JFrame frame)
     {
     	super(JOptionPane.getFrameForComponent(frame));
    	this.setTitle("Sobre");
    	JPanel container = new JPanel(new BorderLayout());
    	container.setBorder(new EmptyBorder(12,12,12,12));
    	this.setContentPane(container);

    	container.add(new AboutPanel(), BorderLayout.CENTER);

    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
   		panel.setBorder(new EmptyBorder(12,0,0,0));
    	close = new JButton("Ok");
    	close.setFocusPainted(false);
    	close.addActionListener(this);
    	this.getRootPane().setDefaultButton(close);
    	panel.add(close);
    	container.add(panel, BorderLayout.SOUTH);

    	this.pack();
    	this.setModal(true);
    	this.setLocationRelativeTo(frame);
    	this.setVisible(true);
  	}

  	public void actionPerformed(ActionEvent evento)
  	{
  		if (evento.getSource() == close)
  		{
  			this.dispose();
    	}
  	}
}