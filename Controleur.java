import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controleur extends JFrame
{
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
				new FrameRoute();
			}
		});

		ajtVille.setPreferredSize(tailleBouton);
		ajtVille.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				new FrameVille();
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
		JPanel carte = new JPanel();

		return carte;
	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}