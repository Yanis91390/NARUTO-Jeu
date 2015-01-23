import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension; 
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JeuView extends JPanel implements KeyListener {
	
	public byte frame;
	public Direction direction;
	public Action action;
	public Personnage personnage;
	public int x, y;
	public int pas;

	private Image naruto_gauche_arret;
	private Image naruto_gauche_marche1;
	private Image naruto_gauche_marche2;
	private Image naruto_droite_arret;
	private Image naruto_droite_marche1;
	private Image naruto_droite_marche2;
	private Image naruto_avant_arret;
	private Image naruto_avant_marche1;
	private Image naruto_avant_marche2;
	private Image naruto_arriere_arret;
	private Image naruto_arriere_marche1;
	private Image naruto_arriere_marche2;
	private Image naruto_pause;
	private Image map1_1;
	private Image sasuke;

	// CHARGEMENT DES COLLISIONS
	public static int map[][] = {

		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
		{1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
		{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
		{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
		{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}

	};

	public static int taille_tile = 16;

	public JeuView()
	{
    
		this.frame = 0;
		this.action = Action.ARRET;
		this.direction = Direction.DROITE;
		
		this.personnage = Personnage.NARUTO;
		
		this.x = -150;
		this.y = -160;
		this.pas = 2;
		
		/*Rend le Panneau Focusable pour qu'il puisse d'écouter lui même */
		this.setFocusable(true);
		this.requestFocus();
		
		addKeyListener(this);
		
		initPanel();
		
	}	
		public void initPanel()
		{
			try
			{
				// CHARGEMENT DES IMAGES
				this.naruto_gauche_arret = ImageIO.read(new File("/images/perso/narutoGauche1.png"));
				this.naruto_gauche_marche1 = ImageIO.read(new File("/images/perso/narutoGauche2.png"));
				this.naruto_gauche_marche2 = ImageIO.read(new File("/images/perso/narutoGauche3.png"));
				this.naruto_droite_arret = ImageIO.read(new File("/images/perso/narutoDroite1.png"));
				this.naruto_droite_marche1 = ImageIO.read(new File("/images/perso/narutoDroite2.png"));
				this.naruto_droite_marche2 = ImageIO.read(new File("/images/perso/narutoDroite3.png"));
				this.naruto_avant_arret = ImageIO.read(new File("/images/perso/narutoAvant1.png"));
				this.naruto_avant_marche1 = ImageIO.read(new File("/images/perso/narutoAvant2.png"));
				this.naruto_avant_marche2 = ImageIO.read(new File("/images/perso/narutoAvant3.png"));
				this.naruto_arriere_arret = ImageIO.read(new File("/images/perso/narutoArriere1.png"));
				this.naruto_arriere_marche1 = ImageIO.read(new File("/images/perso/narutoArriere2.png"));
				this.naruto_arriere_marche2 = ImageIO.read(new File("/images/perso/narutoArriere3.png"));
				this.naruto_pause = ImageIO.read(new File("/images/perso/narutoPause.png"));
				this.map1_1 = ImageIO.read(new File("/images/cartes/map1-1.png"));
				this.sasuke = ImageIO.read(new File("/images/perso/SASUKE.png"));
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}	    
		}


		public void afficheMap(Graphics g)
		{

			g.drawImage(map1_1, -this.x, -this.y, this);

		}

		public void paintComponent(Graphics g)
		{	    
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight()); 

			afficheMap(g);

			g.setColor(Color.black);
			g.drawString("X:"+this.x+"| Y:"+this.y, 10, 20);

			//SI ON CHOISIT NARUTO
			if(this.personnage == Personnage.NARUTO)
			{
				if(this.direction == Direction.GAUCHE)
			
				{
				if(this.action == Action.EN_MARCHE)
				{
        
					if(this.frame%2==0)
					g.drawImage(naruto_gauche_marche1, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
					else
					g.drawImage(naruto_gauche_marche2, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);

					if(map[((this.y+((this.getHeight()/2)))/taille_tile)][((this.x+((this.getWidth()/2)))/taille_tile)-1] == 0)
						this.x-=this.pas;
				}
				else 
				{
					g.drawImage(naruto_gauche_arret, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
				}
			}
			else if(this.direction == Direction.DROITE)
			{
				if(this.action == Action.EN_MARCHE)
				{
					
					if(this.frame%2==0)
					g.drawImage(naruto_droite_marche1, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
					else
					g.drawImage(naruto_droite_marche2, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);

					if(map[((this.y+((this.getHeight()/2)))/taille_tile)][((this.x+((this.getWidth()/2)))/taille_tile)+1] == 0)
					this.x+=this.pas;
				}
				else 
				{
					g.drawImage(naruto_droite_arret, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
				}
			}
			else if(this.direction == Direction.AVANT)
			{
				if(this.action == Action.EN_MARCHE)
				{
        
					if(this.frame%2==0)
						g.drawImage(naruto_avant_marche1, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
					else
						g.drawImage(naruto_avant_marche2, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);

					if(map[((this.y+((this.getHeight()/2)))/taille_tile)][((this.x+((this.getWidth()/2)))/taille_tile)] == 0)
						this.y+=this.pas;
				}	
				else 
				{
					g.drawImage(naruto_avant_arret, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
				}
			}
			else if(this.direction == Direction.ARRIERE)
			{
				if(this.action == Action.EN_MARCHE)
				{
        
					if(this.frame%2==0)
					g.drawImage(naruto_arriere_marche1, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
					else
						g.drawImage(naruto_arriere_marche2, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);

					if(map[((this.y+((this.getHeight()/2)))/taille_tile)-1][((this.x+((this.getWidth()/2)))/taille_tile)] == 0)
						this.y-=this.pas;
				}
				else 
				{
					g.drawImage(naruto_arriere_arret, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
				}
			}
			else if(this.direction == Direction.PAUSE)
			{
				g.drawImage(naruto_pause, (this.getWidth()/2)-10, (this.getHeight()/2)-10, this);
			}
		}     
			
			//SI ON CHOISIT SASUKE
			else if(this.personnage == Personnage.SASUKE)
			{
				if(this.direction == Direction.GAUCHE)
					
				{
				if(this.action == Action.EN_MARCHE)
				{
        
					if(this.frame%2==0)
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
					else
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);

					if(map[((this.y+((this.getHeight()/2)))/taille_tile)][((this.x+((this.getWidth()/2)))/taille_tile)-1] == 0)
						this.x-=this.pas;
				}
				else 
				{
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
				}
			}
			else if(this.direction == Direction.DROITE)
			{
				if(this.action == Action.EN_MARCHE)
				{
					
					if(this.frame%2==0)
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
					else
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);

					if(map[((this.y+((this.getHeight()/2)))/taille_tile)][((this.x+((this.getWidth()/2)))/taille_tile)+1] == 0)
					this.x+=this.pas;
				}
				else 
				{
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
				}
			}
			else if(this.direction == Direction.AVANT)
			{
				if(this.action == Action.EN_MARCHE)
				{
        
					if(this.frame%2==0)
						g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
					else
						g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);

					if(map[((this.y+((this.getHeight()/2)))/taille_tile)][((this.x+((this.getWidth()/2)))/taille_tile)] == 0)
						this.y+=this.pas;
				}	
				else 
				{
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
				}
			}
			else if(this.direction == Direction.ARRIERE)
			{
				if(this.action == Action.EN_MARCHE)
				{
        
					if(this.frame%2==0)
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
					else
						g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);

					if(map[((this.y+((this.getHeight()/2)))/taille_tile)-1][((this.x+((this.getWidth()/2)))/taille_tile)] == 0)
						this.y-=this.pas;
				}
				else 
				{
					g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-30, this);
				}
			}
			else if(this.direction == Direction.PAUSE)
			{
				g.drawImage(sasuke, (this.getWidth()/2)-10, (this.getHeight()/2)-10, this);
			}
			}
		}
		
		public JeuView getJeuView()
		{
			return this;
		}
		
		public void keyPressed(KeyEvent event)
    	{
    		if(this.direction != Direction.PAUSE && (event.getKeyCode() == event.VK_UP || event.getKeyCode() == event.VK_DOWN || event.getKeyCode() == event.VK_LEFT || event.getKeyCode() == event.VK_RIGHT))
    		{
    			if(event.getKeyCode() == event.VK_LEFT)
    			{	
    				System.out.println("Touche gauche");
    				this.direction = Direction.GAUCHE;
    			}
    			if(event.getKeyCode() == event.VK_RIGHT)
    			{
    				System.out.println("Touche droite");
    				this.direction = Direction.DROITE;
    			}
    			if(event.getKeyCode() == event.VK_DOWN)
    			{
    				System.out.println("Touche bas");
    				this.direction = Direction.AVANT;
    			}
    			if(event.getKeyCode() == event.VK_UP)
    			{
    				System.out.println("Touche haut");
    				this.direction = Direction.ARRIERE;
    			}
    			
    			this.action = Action.EN_MARCHE;  
    		}
    		if(event.getKeyCode() == event.VK_ESCAPE)
    		{    
    			if(this.direction != Direction.PAUSE)
    				this.direction = Direction.PAUSE;
    			else
    				this.direction = Direction.AVANT;
    		}
    }

		/*Naruto ne s'arrette pas si on appelle pas cette méthode*/
    	public void keyReleased(KeyEvent event) 
    	{
    		if(event.getKeyCode() == event.VK_UP || event.getKeyCode() == event.VK_DOWN || event.getKeyCode() == event.VK_LEFT || event.getKeyCode() == event.VK_RIGHT)
    		{
    			this.action = Action.ARRET;
    		}
    	}
    	
    	public void keyTyped(KeyEvent event) {} 
    	
}   
