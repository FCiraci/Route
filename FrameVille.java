import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrameVille extends JFrame implements ActionListener
{
	private JPanel     pnlAjout, pnlTableau, pnlDroit;
	private JLabel     lblNom, lblAbscisse, lblOrdonnee;
	private JTextField txtNom, txtAbscisse, txtOrdonnee;
	private JButton    btConfirmer, btModifier;
	private JTable     tblVilles;
	private String[]   nomCol = {"Numéro", "Nom", "x", "y"};
	private Object[][] data;

	private List<Ville> tabVilles;
	private Controleur controleur;

	public FrameVille(Controleur controleur)
	{
		this.controleur = controleur;

		this.setSize(900, 300);
		this.setLocation(50, 400);
		this.setName("Nouvelle Ville");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Définition des composants
		this.tabVilles  = Ville.getVilles();
		this.pnlDroit   = new JPanel(new BorderLayout());
		this.pnlTableau = new JPanel(new BorderLayout());
		this.pnlAjout   = new JPanel(new GridLayout(3, 2));

		this.lblNom      = new JLabel("nom : ");
		this.lblAbscisse = new JLabel("x : "  );
		this.lblOrdonnee = new JLabel("y : "  );

		this.txtNom      = new JTextField(50);
		this.txtAbscisse = new JTextField(5 );
		this.txtOrdonnee = new JTextField(5 );

		this.btConfirmer = new JButton("Confirmer");
		this.btConfirmer.addActionListener(this);

		this.btModifier = new JButton("Modifier");
		this.btModifier.addActionListener(this);

		// Ajout des composants

		this.pnlAjout.add(this.lblNom);
		this.pnlAjout.add(this.txtNom);

		this.pnlAjout.add(this.lblAbscisse);
		this.pnlAjout.add(this.txtAbscisse);

		this.pnlAjout.add(this.lblOrdonnee);
		this.pnlAjout.add(this.txtOrdonnee);

		this.updateTable();

		this.pnlTableau.add(new JScrollPane(tblVilles), BorderLayout.CENTER);
		this.pnlTableau.add(this.btModifier, BorderLayout.SOUTH);

		this.add(this.pnlTableau, BorderLayout.WEST);

		this.pnlDroit.add(this.pnlAjout, BorderLayout.CENTER);
		this.pnlDroit.add(this.btConfirmer, BorderLayout.SOUTH);

		this.add(this.pnlDroit, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	private void updateTable()
	{
		data = new Object[this.tabVilles.size()][4];
		for (int i = 0; i < this.tabVilles.size(); i++)
		{
			Ville ville = this.tabVilles.get(i);
			data[i][0] = ville.getNumero();
			data[i][1] = ville.getNom();
			data[i][2] = ville.getAbscisse();
			data[i][3] = ville.getOrdonnee();
		}
		tblVilles = new JTable(data, nomCol);
		if (this.pnlTableau != null)
		{
			this.pnlTableau.removeAll();
			this.pnlTableau.add(new JScrollPane(this.tblVilles), BorderLayout.CENTER);
			this.pnlTableau.add(this.btModifier, BorderLayout.SOUTH);
			this.pnlTableau.revalidate();
			this.pnlTableau.repaint();
		}
	}

	public void actionPerformed(ActionEvent e)
	{
        String action = e.getActionCommand();
        if (action.equals("Confirmer"))
		{
            this.Confirmer();
        }
		else if (action.equals("Modifier"))
		{
            this.Modifier();
        }
    }

    private void Confirmer()
	{
        String nom = this.txtNom.getText();
        String abscisseText = this.txtAbscisse.getText();
        String ordonneeText = this.txtOrdonnee.getText();

        if (nom.isEmpty() || abscisseText.isEmpty() || ordonneeText.isEmpty())
		{
            JOptionPane.showMessageDialog(this, "Tous les champs doivent être complets", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try 
		{
            int x = Integer.parseInt(abscisseText);
            int y = Integer.parseInt(ordonneeText);

            Ville ville = Ville.creerVille(nom, x, y);
            if (ville != null) {
                this.tabVilles = Ville.getVilles();
                updateTable();
                this.controleur.rafraichirCarte();
                dispose();
            }
        }
		catch (NumberFormatException ex)
		{
            JOptionPane.showMessageDialog(this, "Les coordonnées doivent être des entiers", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

	private void Modifier()
	{
		int selectedRow = tblVilles.getSelectedRow();
		if (selectedRow >= 0)
		{
			String nom = (String) tblVilles.getValueAt(selectedRow, 1);
			String abscisseText = tblVilles.getValueAt(selectedRow, 2).toString();
			String ordonneeText = tblVilles.getValueAt(selectedRow, 3).toString();
	
			if (nom.isEmpty() || abscisseText.isEmpty() || ordonneeText.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Tous les champs doivent être complets", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}
	
			try
			{
				int x = Integer.parseInt(abscisseText);
				int y = Integer.parseInt(ordonneeText);
	
				Ville ville = tabVilles.get(selectedRow);
				ville.setNom(nom);
				ville.setAbscisse(x);
				ville.setOrdonnee(y);
				updateTable();
				this.controleur.rafraichirCarte();
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Les coordonnées doivent être des entiers", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Sélectionnez une ville à modifier", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}	
}