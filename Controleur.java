import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controleur extends JFrame
{
	private JPanel panelCarte;
	private Ville  selectedVille = null;
    private int    offsetX, offsetY;

	private JLabel infosVille = new JLabel("");

	public Controleur()
	{
		JFrame	frame	   	  = new JFrame("Controleur");
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(tailleEcran.width, tailleEcran.height);
		
		frame.add(mainPanel());
		
		frame.setVisible(true);
	}

	private JPanel mainPanel()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.add(panelCarte(), BorderLayout.CENTER);
		panel.add(panelMenu(), BorderLayout.EAST);

		return panel;
	}

	private JPanel panelMenu()
	{
		Dimension tailleBouton = new Dimension(150, 50);

		JPanel menu	= new JPanel();
		JPanel boutons = new JPanel();
		JPanel ville   = new JPanel();

		JButton ajtVille = new JButton("Nouvelle Ville");
		JButton ajtRoute = new JButton("Nouvelle Route");
		JButton exporter = new JButton("Exporter");
		JButton importer = new JButton("Importer");

		ajtRoute.setPreferredSize(tailleBouton);
		ajtRoute.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				new FrameRoute(Controleur.this);
			}
		});

		ajtVille.setPreferredSize(tailleBouton);
		ajtVille.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				new FrameVille(Controleur.this);
			}
		});


		exporter.setBackground(Color.RED);
		exporter.setForeground(Color.WHITE);
		exporter.setPreferredSize(tailleBouton);

		importer.setBackground(Color.BLUE);
		importer.setForeground(Color.WHITE);
		importer.setPreferredSize(tailleBouton);

		importer.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				JFileChooser ouvrirFichier = new JFileChooser("./Route");
				FileNameExtensionFilter Filtre = new FileNameExtensionFilter(".data", ".data");
				ouvrirFichier.setDialogTitle("Ouvrez votre carte");
				ouvrirFichier.addChoosableFileFilter(Filtre);
				ouvrirFichier.showOpenDialog(null);
			}
		});

		boutons.setLayout(new GridLayout(5, 1));
		boutons.add(ajtVille);
		boutons.add(ajtRoute);
		boutons.add(importer);
		boutons.add(exporter);
		boutons.add(infosVille);

		menu.setLayout(new GridLayout(2, 1));
		menu.add(boutons);
		menu.add(ville);

		return menu;
	}

	private JPanel panelCarte()
	{
		panelCarte = new JPanel() 
		{
			protected void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				dessinerVillesEtRoutes(g);
			}
		};

		panelCarte.addMouseListener(new MouseAdapter()
		{
            public void mousePressed(MouseEvent e)
			{
				for (Ville ville : Ville.getVilles()) 
				{
					int x = ville.getAbscisse();
					int y = ville.getOrdonnee();

					if (e.getX() >= x && e.getX() <= x + 50 && e.getY() >= y && e.getY() <= y + 50)
					{
						selectedVille = ville;
						offsetX = e.getX() - x;
						offsetY = e.getY() - y;
						
						String infoText = "Nom: " + ville.getNom() + ", X: " + ville.getAbscisse() + ", Y: " + ville.getOrdonnee();
						infosVille.setText(infoText);
						
						infosVille.revalidate();
						infosVille.repaint();
						
						break;
					}
				}
			}
            public void mouseReleased(MouseEvent e)
			{
                selectedVille = null;
            }
        });

		panelCarte.addMouseMotionListener(new MouseAdapter()
		{
            public void mouseDragged(MouseEvent e)
			{
                if (selectedVille != null)
				{
                    selectedVille.setAbscisse(e.getX() - offsetX);
                    selectedVille.setOrdonnee(e.getY() - offsetY);
                    rafraichirCarte();
                }
            }
        });

		return panelCarte;
	}

	private void dessinerVillesEtRoutes(Graphics g)
	{
		for (Route route : Route.getRoutes())
		{
			dessinerRoute(g, route);
		}
		
		for (Ville ville : Ville.getVilles())
		{
			dessinerVille(g, ville);
		}

		
	}
	
	private void dessinerVille(Graphics g, Ville ville)
	{
		g.setColor(Color.CYAN);
		g.fillOval(ville.getAbscisse(), ville.getOrdonnee(), 50, 50);
		g.setColor(Color.BLACK);
		g.drawString(ville.getNom(), ville.getAbscisse() + 25 - (ville.getNom().length() / 2) * 7, ville.getOrdonnee() + 25);
	}

	private void dessinerRoute(Graphics g, Route route)
	{
		g.setColor(Color.BLACK);

		int departx = route.getVilleDep().getAbscisse() + 25;
		int departy = route.getVilleDep().getOrdonnee() + 25;
		int pasx = (route.getVilleArr().getAbscisse() - route.getVilleDep().getAbscisse()) / route.getNbTroncons();
		int pasy = (route.getVilleArr().getOrdonnee() - route.getVilleDep().getOrdonnee())  / route.getNbTroncons();
		for (int i = 1; i < route.getNbTroncons(); i++)
		{
			g.drawLine(departx, departy, departx + pasx - pasx / 10, departy + pasy - pasy / 10);
			departx += pasx;
			departy += pasy;
		}
		g.drawLine(departx, departy, departx + pasx, departy + pasy);
	}

	public void rafraichirCarte()
	{
		this.panelCarte.repaint();
	}
	public static void main(String[] args)
	{
		new Controleur();
	}

	
}
