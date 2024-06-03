import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class FrameVille extends JFrame
{
	private JLabel lblNom, lblAbscisse, lblOrdonnee;
	private JTextField txtNom, txtAbscisse, txtOrdonnee;
	private JButton btConfirmer;
	private JPanel formulaire;
	private JPanel panelTabVille;
	private JPanel mainPanel;

	private List<Ville> tabVilles = new ArrayList<>();

	public FrameVille()
	{
		this.setSize(300, 150);
		this.setLocation(50, 400);
		this.setName("Nouvelle Ville");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		// Définition des composants
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new GridLayout(1, 2));

		//Composants Tableau

		this.panelTabVille = new JPanel();
		this.panelTabVille.setLayout(new GridLayout(1, 1));

		//Composants formulaire

		this.formulaire = new JPanel();
		this.formulaire.setLayout(new GridLayout(3, 2));

		this.lblNom = new JLabel("nom : ");
		this.lblAbscisse = new JLabel("x : ");
		this.lblOrdonnee = new JLabel("y : ");

		this.txtNom = new JTextField(50);
		this.txtAbscisse = new JTextField(5);
		this.txtOrdonnee = new JTextField(5);

		this.btConfirmer = new JButton("Confirmer");

		// Ajout des composants

		this.formulaire.add(this.lblNom);
		this.formulaire.add(this.txtNom);

		this.formulaire.add(this.lblAbscisse);
		this.formulaire.add(this.txtAbscisse);

		this.formulaire.add(this.lblOrdonnee);
		this.formulaire.add(this.txtOrdonnee);

		this.add(this.formulaire, BorderLayout.CENTER);
		this.add(this.btConfirmer, BorderLayout.SOUTH);

		this.btConfirmer.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				String nom = txtNom.getText();
				int x = Integer.parseInt(txtAbscisse.getText());
				int y = Integer.parseInt(txtOrdonnee.getText());

				if (nom != "")
				{
					Ville ville = Ville.creerVille(nom, x, y);
					if (ville != null)
					{
						tabVilles.add(ville);
					}
				}
			}
		});
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		new FrameVille();
	}
	
}
