import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Idée : Mettre 2 grandes images stylée de Naruto et Sasuke dans leurs Boutons Respectif. 
 */
public class SelectionView extends JPanel {
	
	final Panneau pan = new Panneau();
	final JButton b_jouer = new JButton("Jouer");
	
	final JLabel titre = new JLabel("Choisissez votre Personnage : ");
	
	//Je met l'icone dans mon Bouton (Problème : Je ne vois plus écris Naruto)
	final JToggleButton b_naruto = new JToggleButton("Naruto");// , icone);
	final JToggleButton b_sasuke = new JToggleButton("Sasuke"); //, icone2);
	final JToggleButton b_kakashi = new JToggleButton("Kakashi");
	
	SelectionView()
	{
		super();	
		initPanel();
	}
	
	public void initPanel()
	{
		
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		
		titre.setForeground(Color.WHITE);
		titre.setFont(new Font("Droid Serif", Font.ITALIC | Font.BOLD , 30));
		titre.setBounds(170, 5, 5000, 50);
		
		pan.setBounds(100, 60,600, 400);
		b_naruto.setBounds(20, 20, 270, 350);
		b_sasuke.setBounds(310, 20, 270, 350);
		b_jouer.setBounds(680, 480, 100, 20);
		
		this.add(pan);
		this.add(titre);
		this.add(b_jouer);
		
		pan.add(b_naruto);
		pan.add(b_sasuke);
	}
	
	public JButton getBoutonJouer()
	{
		return b_jouer;
	}
	
	public JToggleButton getBoutonNaruto()
	{
		return b_naruto;
	}
	
	public JToggleButton getBoutonSasuke()
	{
		return b_sasuke;
	}
	
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		//g.drawImage(icone, icone.getIconHeight() , icone.getIconWidth() , this);
	}
	
}
