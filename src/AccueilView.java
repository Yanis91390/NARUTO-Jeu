import java.util.Observer;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AccueilView extends JPanel 
{	
	Fenetre fenetre = null ;
	SelectionView selection = new SelectionView();
	
	final JButton b_jouer = new JButton("Jouer");
	final JButton b_options = new JButton("Options");
	final JButton b_aide = new JButton("Aide");
	
	ImageIcon icone;
    JLabel label;
    
   AccueilView()
   {
	   super();
	   this.setLayout(new BorderLayout());
	   this.icone = new ImageIcon("/home/yanislb/Bureau/NarutoRPG/images/fond_menu.jpg");
	   this.label = new JLabel(icone);
	   initPanel();
   }
	
	public void initPanel()
	{
		this.setBackground(Color.BLACK);
		
		b_jouer.setBounds(90, 440, 100, 50);
		b_options.setBounds(340 , 440 , 100 , 50);
		b_aide.setBounds(600, 440, 100, 50);

		this.add(b_jouer);
		this.add(b_options);
		this.add(b_aide);
		
		this.add(label);
	
	}
	
	public JButton getBoutonJouer()
	{
		return b_jouer;
	}

	
	public JButton getBoutonAide()
	{
		return b_aide;
	}

	public JButton getBoutonOptions()
	{
		return b_options;
	}
	
}