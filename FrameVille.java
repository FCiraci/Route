import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrameVille extends JFrame
{
	private JLabel lblNom, lblAbscisse, lblOrdonnee;
	private JTextField txtNom, txtAbscisse, txtOrdonnee;
	private JButton btConfirmer;
	private JPanel formulaire;
	private JPanel panelTabVille;
	private JPanel mainPanel;
    private JTable tblVilles;
	private String[] colNames = {"Numéro", "Nom", "x", "y"};
    private Object[][] data;

	private List<Ville> tabVilles;

	public FrameVille()
	{
		this.setSize(300, 150);
		this.setLocation(50, 400);
		this.setName("Nouvelle Ville");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

		// Définition des composants
		this.tabVilles = Ville.getVilles();
		this.mainPanel = new JPanel( new GridLayout(1, 2));
		this.panelTabVille = new JPanel(new GridLayout(1, 1));
		this.formulaire = new JPanel(new GridLayout(3, 2));

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

		this.updateTable();

		this.panelTabVille.add(new JScrollPane(tblVilles), BorderLayout.CENTER);
        this.mainPanel.add(this.panelTabVille, BorderLayout.WEST);
        this.mainPanel.add(this.formulaire, BorderLayout.CENTER);
		this.add(this.formulaire, BorderLayout.CENTER);
		this.add(this.btConfirmer, BorderLayout.SOUTH);

		this.btConfirmer.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{
				String nom = txtNom.getText();
				int x = Integer.parseInt(txtAbscisse.getText());
				int y = Integer.parseInt(txtOrdonnee.getText());

				if (!nom.isEmpty())
				{
					Ville ville = Ville.creerVille(nom, x, y);
					if (ville != null)
					{
						tabVilles.add(ville);
						updateTable();
						dispose();
					}
				}
			}
		});
		this.setVisible(true);
	}

	private void updateTable()
	{
        data = new Object[tabVilles.size()][4];
        for (int i = 0; i < tabVilles.size(); i++)
		{
            Ville ville = tabVilles.get(i);
            data[i][0] = ville.getNumero();
            data[i][1] = ville.getNom();
            data[i][2] = ville.getAbscisse();
            data[i][3] = ville.getOrdonnee();
        }
        tblVilles = new JTable(data, colNames);
        if (panelTabVille != null)
		{
            panelTabVille.removeAll();
            panelTabVille.add(new JScrollPane(tblVilles), BorderLayout.CENTER);
            panelTabVille.revalidate();
            panelTabVille.repaint();
        }
    }
	public static void main(String[] args)
	{
		new FrameVille();
	}
	
}
