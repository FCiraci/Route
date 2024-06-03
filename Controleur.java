import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controleur extends JFrame
{
	private JPanel panelCarte;

	public Controleur()
	{
		JFrame    frame       = new JFrame("Controleur");
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

		JPanel menu    = new JPanel();
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

		boutons.setLayout(new FlowLayout());
		boutons.add(ajtVille);
		boutons.add(ajtRoute);
		boutons.add(importer);
		boutons.add(exporter);


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
        return panelCarte;
	}

	private void dessinerVillesEtRoutes(Graphics g)
    {
        for (Ville ville : Ville.getVilles())
        {
            dessinerVille(g, ville);
        }

        for (Route route : Route.getRoutes())
        {
            dessinerRoute(g, route);
        }
    }
	
	private void dessinerVille(Graphics g, Ville ville)
    {
        g.setColor(Color.BLUE);
        g.fillOval(ville.getAbscisse(), ville.getOrdonnee(), 50, 50);
        g.setColor(Color.WHITE);
        g.drawString(ville.toString(), ville.getAbscisse() + 5, ville.getOrdonnee() + 25);
    }

	private void dessinerRoute(Graphics g, Route route)
    {
        g.setColor(Color.BLACK);
        g.drawLine(route.getVilleDep().getAbscisse() + 25, route.getVilleDep().getOrdonnee() + 25,
                   route.getVilleArr().getAbscisse() + 25, route.getVilleArr().getOrdonnee() + 25);
    }

	public void rafraichirCarte()
	{
        panelCarte.repaint();
    }
	public static void main(String[] args)
	{
		new Controleur();
	}

	
}