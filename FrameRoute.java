import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameRoute extends JFrame implements ActionListener{

	private JPanel		pnlTableau, pnlAjout;
	private JLabel 		lblTroncon, lblVilleDepart, lblVilleArrive;
	private JTextField 	txtTroncon;
	private String[] 	lstEntetes, lstVille;
	private Object[][]	lstDonnee;
	private JComboBox 	ddlstVilleDepart, ddlstVilleArrive;
	private JButton 	btConfirmer;
	private JTable		tblDonnee;



	public FrameRoute()
	{
		this.setSize(400,200);
		this.setLocation(50,50);
		this.setTitle("Nouvelle Route");
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//ACTIVATION DES COMPOSANTS;

		this.pnlAjout = new JPanel();
		this.pnlAjout.setLayout(new GridLayout(3,2));
		this.pnlTableau = new JPanel();
		this.pnlTableau.setLayout(new BorderLayout());
		this.lblTroncon = new JLabel("Nombre de tronçon(s) :");
		this.lblVilleDepart = new JLabel("Ville de départ");
		this.lblVilleArrive = new JLabel("Ville d'arrivée");
		this.txtTroncon = new JTextField(20);
		
		this.ddlstVilleDepart = new JComboBox<Object>(this.lstVille);
		this.ddlstVilleArrive = new JComboBox<Object>(this.lstVille);
		this.btConfirmer = new JButton("Confirmer");
		this.btConfirmer.addActionListener(this);
		this.lstEntetes = new String[]{"Ville Dep", "Ville Arr", "nb Tronçon"};
		this.lstVille = new String[]{"Choissez une ville","Lyon", "Paris", "Marseille"};
		this.lstDonnee = new Object[][]{{}};
		
		


		//PLACEMENT DES COMPOSANTS

		this.pnlAjout.add(this.lblTroncon);
		this.pnlAjout.add(this.txtTroncon);
		this.pnlAjout.add(this.lblVilleDepart);
		this.pnlAjout.add(this.ddlstVilleDepart);
		this.pnlAjout.add(this.lblVilleArrive);
		this.pnlAjout.add(this.ddlstVilleArrive);

		this.add(this.pnlAjout, BorderLayout.CENTER);
		this.add(this.btConfirmer, BorderLayout.SOUTH);

		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e)
	{
		String action = e.getActionCommand(); 
		Object[] donnee = new Object[]{};
		int i = 0;
		String villeDep, villeArr, nbTroncon;

		if(action.equals("Confirmer"))
		{
			villeDep = (String)
		}
	}

	public static void main(String[] args) {
		new FrameRoute();
	}
}	