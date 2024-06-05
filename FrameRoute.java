import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class FrameRoute extends JFrame implements ActionListener
{
	private JPanel			 pnlTableau, pnlAjout, pnlDroit;
	private JLabel 			 lblTroncon, lblVilleDepart, lblVilleArrive;
	private JTextField 		 txtTroncon;
	private JComboBox<Ville> ddlstVilleDepart, ddlstVilleArrive;
	private JButton 		 btConfirmer;
	private JTable			 tblRoutes;
	private String[]		 nomCol = {"Ville Dep", "Ville Arr", "nb Tronçon"};
	private Object[][]	 	 data;

	private List<Route> tabRoutes;
	private Controleur controleur;

	public FrameRoute(Controleur controleur)
	{
		this.controleur = controleur;

		this.setSize(800,300);
		this.setLocation(50,50);
		this.setTitle("Nouvelle Route");
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//ACTIVATION DES COMPOSANTS;

		this.tabRoutes  = Route.getRoutes();
		this.pnlDroit   = new JPanel(new BorderLayout());
		this.pnlAjout   = new JPanel(new GridLayout(3,2));
		this.pnlTableau = new JPanel(new BorderLayout());

		this.lblTroncon     = new JLabel("Nombre de tronçon(s) :");
		this.lblVilleDepart = new JLabel("Ville de départ : "    );
		this.lblVilleArrive = new JLabel("Ville d'arrivée : "    );

		this.txtTroncon       = new JTextField(20);
		this.ddlstVilleDepart = new JComboBox<>(Ville.getVilles().toArray(new Ville[0]));
		this.ddlstVilleArrive = new JComboBox<>(Ville.getVilles().toArray(new Ville[0]));

		this.btConfirmer = new JButton("Confirmer");
		this.btConfirmer.addActionListener(this);

		//PLACEMENT DES COMPOSANTS
		this.pnlAjout.add(this.lblTroncon);
		this.pnlAjout.add(this.txtTroncon);

		this.pnlAjout.add(this.lblVilleDepart);
		this.pnlAjout.add(this.ddlstVilleDepart);

		this.pnlAjout.add(this.lblVilleArrive);
		this.pnlAjout.add(this.ddlstVilleArrive);

		this.updateTable();

		this.pnlTableau.add(new JScrollPane(this.tblRoutes), BorderLayout.CENTER);

		this.add(this.pnlTableau, BorderLayout.WEST);

		this.pnlDroit.add(this.btConfirmer, BorderLayout.SOUTH);
		this.pnlDroit.add(this.pnlAjout, BorderLayout.CENTER);

		this.add(pnlDroit, BorderLayout.CENTER);

		this.setVisible(true);

	}

	private void updateTable()
	{
		this.data = new Object[this.tabRoutes.size()][3];
		for (int i = 0; i < this.tabRoutes.size(); i++)
		{
			Route route = this.tabRoutes.get(i);
			this.data[i][0] = route.getVilleDep().getNom();
			this.data[i][1] = route.getVilleArr().getNom();
			this.data[i][2] = route.getNbTroncons();
		}
		this.tblRoutes = new JTable(this.data, this.nomCol);
		if (this.pnlTableau != null)
		{
			this.pnlTableau.removeAll();
			this.pnlTableau.add(new JScrollPane(this.tblRoutes), BorderLayout.CENTER);
			this.pnlTableau.revalidate();
			this.pnlTableau.repaint();
		}
	}

	public void actionPerformed(ActionEvent e)
	{
	    String action = e.getActionCommand();
	    if (action.equals("Confirmer"))
	    {
	        Ville villeDep = (Ville) this.ddlstVilleDepart.getSelectedItem();
	        Ville villeArr = (Ville) this.ddlstVilleArrive.getSelectedItem();
	        String tronconText = this.txtTroncon.getText().trim();

	        if (tronconText.isEmpty())
	        {
	            JOptionPane.showMessageDialog(this, "Veuillez saisir le nombre de tronçons", "Erreur", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

			if (villeDep == null || villeArr == null)
	        {
	            JOptionPane.showMessageDialog(this, "Les Villes de départs et d'arrivées ne peuvent pas être nulles", "Erreur", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

			if (villeArr == villeDep)
	        {
	            JOptionPane.showMessageDialog(this, "La ville de départ et la ville d'arrivée doivent être différentes", "Erreur", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        try
	        {
	            int nbTroncon = Integer.parseInt(tronconText);
	            Route route = Route.ajouterRoute(nbTroncon, villeDep, villeArr);
	            if (route != null)
	            {
	                updateTable();
	                this.controleur.rafraichirCarte();
	                dispose();
	            }
	        }
	        catch (NumberFormatException ex)
	        {
	            JOptionPane.showMessageDialog(this, "Veuillez saisir un nombre valide pour le nombre de tronçons", "Erreur", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
}	