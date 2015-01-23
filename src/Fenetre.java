import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Fenetre extends JFrame implements ActionListener  { //, KeyListener  {
	
	public int indice = 0;
	private int frame_per_second;
	private static int choix;
	
	JOptionPane jop = new JOptionPane();
	JMenuBar menubar = new JMenuBar();
	JMenu menu_fichier = new JMenu("Fichier");
    JMenu menu_jouer = new JMenu("Jouer");
    JMenu menu_aide = new JMenu("?");
    JMenuItem item_quitter = new JMenuItem("Quitter");
    JMenuItem item_retour = new JMenuItem("Retour");
    JMenuItem item_naruto = new JMenuItem("Avec Naruto");
    JMenuItem item_sasuke = new JMenuItem("Avec Sasuke");
    JMenuItem item_aide = new JMenuItem("Aide");
    
    AccueilView accueil = new AccueilView();
    SelectionView selection = new SelectionView();
    OptionsView options = new OptionsView();
    JeuView jeu = new JeuView();
    AideView aide = new AideView();
    
	Fenetre()
	{
		super("Naruto");
		
		this.setBackground(Color.BLACK);
		this.setSize(new Dimension(800, 540));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initMenu();
		this.setVisible(true);	
		this.frame_per_second = 150;
		changerPanneau(0);
		
		jeu.setFocusable(true);
		jeu.requestFocus();
		
		accueil.getBoutonJouer().addActionListener(this);
		accueil.getBoutonOptions().addActionListener(this);
		accueil.getBoutonAide().addActionListener(this);
		
		selection.getBoutonJouer().addActionListener(this);
		selection.getBoutonNaruto().addActionListener(this);
		selection.getBoutonSasuke().addActionListener(this);
		
		item_retour.addActionListener(this);
		item_quitter.addActionListener(this);
		item_naruto.addActionListener(this);
		item_sasuke.addActionListener(this);
		item_aide.addActionListener(this);
		
		animation();
	}
	
	public void initMenu()
	{
		menu_fichier.add(item_retour);
		menu_fichier.add(item_quitter);
		
		menu_jouer.add(item_naruto);
		menu_jouer.add(item_sasuke);
		
		menu_aide.add(item_aide);
		
		menubar.add(menu_fichier);
		menubar.add(menu_jouer);
		menubar.add(menu_aide);
		
		this.setJMenuBar(menubar);
	}
	
	public void changerPanneau(int PanneauSelectionne){
        
        //On change de panel en fonction du nombre passé en paramètre
        switch(PanneauSelectionne){
         
        //Panneau 0 : Accueil de Base
        case 0:
            this.setContentPane(accueil);
            this.indice=0;
            this.repaint();
            this.revalidate();
            break;
        //Panneau 1 : Jouer (Selection des Personnages)
        case 1:
            this.setContentPane(selection);
            this.repaint();
            this.revalidate();
            this.indice=1;
            break;
        //Panneau 2 :Options
        case 2:
        	this.setContentPane(options);
            this.repaint();
            this.revalidate();
            this.indice=2;
            break;
        //Panneau 3 : Aide
        case 3:
        	this.setContentPane(aide);
            this.repaint();
            this.revalidate();
            this.indice=3;
            break;
        //Panneau 4 :Vue principal du Jeu
        case 4:
        	this.setContentPane(jeu);
            this.repaint();
            this.revalidate();
            this.indice=4;
            this.setFocusable(true);
    		this.requestFocus();
    		this.addKeyListener(jeu);
            break;
        //En cas d'erreur  
        default:
            System.err.println("Panneau selectionne non valide");
            break;
        }
    }
	
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource() == this.getItemRetour())
		{
			this.getFenetre().changerPanneau(0);
		}
		
		else if(evt.getSource() == this.getItemQuitter())
		{
			quitter();
		}
		
		if(evt.getSource() == this.getItemNaruto())
		{
			jeu.personnage = Personnage.NARUTO;
			this.getFenetre().changerPanneau(4);
		}
		
		else if(evt.getSource() == this.getItemSasuke())
		{
			jeu.personnage = Personnage.SASUKE;
			this.getFenetre().changerPanneau(4);
		}
		
		else if(evt.getSource() == this.getItemAide())
		{
			this.getFenetre().changerPanneau(3);
		}
		
		else if(evt.getSource() == accueil.getBoutonJouer())
		{
			System.out.println("Clique Bouton jouer");
			this.getFenetre().changerPanneau(1);
		}
		
		else if(evt.getSource() == accueil.getBoutonOptions())
		{
			System.out.println("Clique Bouton Options");
			this.getFenetre().changerPanneau(2);
		}
		
		else if(evt.getSource() == accueil.getBoutonAide())
		{
			System.out.println("Clique Bouton Aide");
			this.getFenetre().changerPanneau(3);
		}
		
		else if(evt.getSource() == selection.getBoutonJouer()&& !(selection.getBoutonSasuke().isSelected()) && !(selection.getBoutonNaruto().isSelected()) )
		{
			System.out.println("Il faut choisir un Personnage");
			choisirPersonnage();
		}
		
		else if(evt.getSource() == selection.getBoutonJouer())
		{
			this.getFenetre().changerPanneau(4);
		}
		
		else if(evt.getSource() == selection.getBoutonNaruto() && selection.getBoutonNaruto().isSelected())
		{
			System.out.println("Naruto select");
			jeu.personnage = Personnage.NARUTO;
			selection.b_sasuke.setSelected(false);
		}
		
		else if(evt.getSource() == selection.getBoutonSasuke() && selection.getBoutonSasuke().isSelected())
		{
			System.out.println("Sasuke select");
			jeu.personnage = Personnage.SASUKE;
			selection.b_naruto.setSelected(false);
		}
	}
	
	private void animation()
	{
		while(true)
		{
			jeu.repaint();
			jeu.frame++;
			jeu.frame %= 127;
			try
			{
				Thread.sleep(frame_per_second);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	
	}
    	public void keyTyped(KeyEvent event) {} 
    	
    	public Fenetre getFenetre()
    	{
    		return this;
    	}
    	
    	public void quitter()
    	{
    		choix = this.afficherChoixMessage("Êtes-vous sur de vouloir quitter ?", "Quitter ");
            if(choix == JOptionPane.YES_OPTION)
                System.exit(0);
    	}
    	
    	public int afficherChoixMessage(String message, String entete)
    	{
    		return jop.showConfirmDialog(null, message, entete, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
    	}
    	
    	public int afficherChoixMessage2(String message, String entete)
    	{
    		return jop.showConfirmDialog(null, message, entete, JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
    	}
    	
    	public void choisirPersonnage()
    	{
    		choix = this.afficherChoixMessage2("Choisissez un Personnage", "");
    	}
    	

    	public JMenuItem getItemRetour()
    	{
    		return item_retour;
    	}
    	
    	public JMenuItem getItemQuitter()
    	{
    		return item_quitter;
    	}
    	
    	public JMenuItem getItemNaruto()
    	{
    		return item_naruto;
    	}
    	
    	public JMenuItem getItemSasuke()
    	{
    		return item_sasuke;
    	}
    	
    	public JMenuItem getItemAide()
    	{
    		return item_aide;
    	}
    	
    	
    	public static void main(String[] args)
    	{
    		Fenetre naruto = new Fenetre();
    	}
	
}
	
