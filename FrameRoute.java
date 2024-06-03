import javax.swing.*;
import java.awt.*;

public class FrameRoute extends JFrame{

	private JPanel		pnlTableau, pnlAjout;
	private JLabel 		lblTroncon, lblVilleDepart, lblVilleArrive;
	private JTextField 	txtTroncon;
	private String[] 	lstVille;
	private JComboBox 	ddlstVilleDepart, ddlstVilleArrive;
	private JButton 	btConfirmer;


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

		this.lblTroncon = new JLabel("Nombre de tronçon(s) :");
		this.lblVilleDepart = new JLabel("Ville de départ");
		this.lblVilleArrive = new JLabel("Ville d'arrivée");
		this.txtTroncon = new JTextField(20);
		this.lstVille = new String[]{"Choissez une ville","Lyon", "Paris", "Marseille"};
		this.ddlstVilleDepart = new JComboBox<String>(this.lstVille);
		this.ddlstVilleArrive = new JComboBox<String>(this.lstVille);
		this.btConfirmer = new JButton("Confirmer");

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

	public static void main(String[] args) {
		new FrameRoute();
	}
}	