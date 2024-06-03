import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class FrameRoute extends JFrame implements ActionListener{

	private JPanel		     pnlTableau, pnlAjout;
	private JLabel 		     lblTroncon, lblVilleDepart, lblVilleArrive;
	private JTextField 	     txtTroncon;
	private JComboBox<Ville> ddlstVilleDepart, ddlstVilleArrive;
	private JButton 	     btConfirmer;
	private JTable		     tblRoutes;
	private String[]         colNames = {"Ville Dep", "Ville Arr", "nb Tronçon"};
    private Object[][]       data;


	public FrameRoute()
	{
		this.setSize(600,300);
		this.setLocation(50,50);
		this.setTitle("Nouvelle Route");
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//ACTIVATION DES COMPOSANTS;

		this.pnlAjout = new JPanel(new GridLayout(3,2));
		this.pnlTableau = new JPanel(new BorderLayout());
		this.lblTroncon = new JLabel("Nombre de tronçon(s) :");
		this.lblVilleDepart = new JLabel("Ville de départ");
		this.lblVilleArrive = new JLabel("Ville d'arrivée");
		this.txtTroncon = new JTextField(20);

		List<Ville> villes = Ville.getVilles();
		Ville[] villesArray = villes.toArray(new Ville[0]);
		
		this.ddlstVilleDepart = new JComboBox<>(villesArray);
        this.ddlstVilleArrive = new JComboBox<>(villesArray);
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

		this.pnlTableau.add(new JScrollPane(tblRoutes), BorderLayout.CENTER);
        this.add(this.pnlTableau, BorderLayout.WEST);
        this.add(this.pnlAjout, BorderLayout.CENTER);
        this.add(this.btConfirmer, BorderLayout.SOUTH);

		this.setVisible(true);

	}

    private void updateTable()
    {
        List<Route> routes = Route.getRoutes();
        data = new Object[routes.size()][3];
        for (int i = 0; i < routes.size(); i++)
        {
            Route route = routes.get(i);
            data[i][0] = route.getVilleDep().getNom();
            data[i][1] = route.getVilleArr().getNom();
            data[i][2] = route.getNbTroncons();
        }
        tblRoutes = new JTable(data, colNames);
        if (pnlTableau != null)
        {
            pnlTableau.removeAll();
            pnlTableau.add(new JScrollPane(tblRoutes), BorderLayout.CENTER);
            pnlTableau.revalidate();
            pnlTableau.repaint();
        }
    }

	public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        if (action.equals("Confirmer"))
        {
            Ville villeDep = (Ville) this.ddlstVilleDepart.getSelectedItem();
            Ville villeArr = (Ville) this.ddlstVilleArrive.getSelectedItem();
            int nbTroncon = Integer.parseInt(this.txtTroncon.getText());
            
            if (villeDep != null && villeArr != null && villeDep != villeArr)
            {
                Route route = Route.ajouterRoute(nbTroncon, villeDep, villeArr);
                if (route != null)
                {
                    updateTable();
                    dispose();
                }
            }
        }
    }

	public static void main(String[] args) {
		new FrameRoute();
	}
}	