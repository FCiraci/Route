import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FrameVille extends JFrame
{
	private JLabel lblNom, lblAbscisse, lblOrdonnee;
    private JTextField txtNom, txtAbscisse, txtOrdonnee;
    private JButton btConfirmer;
    private JPanel panel;

	public FrameVille()
	{

        
		this.setSize(300, 150);
		this.setLocation(50, 400);
        this.setName("Nouvelle Ville");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        // Définition des composants

        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(3, 2));

        this.lblNom = new JLabel("nom : ");
        this.lblAbscisse = new JLabel("x : ");
        this.lblOrdonnee = new JLabel("y : ");

        this.txtNom = new JTextField(50);
        this.txtAbscisse = new JTextField(5);
        this.txtOrdonnee = new JTextField(5);

        this.btConfirmer = new JButton("Confirmer");


        // Ajout des composants

        this.panel.add(this.lblNom);
        this.panel.add(this.txtNom);

        this.panel.add(this.lblAbscisse);
        this.panel.add(this.txtAbscisse);

        this.panel.add(this.lblOrdonnee);
        this.panel.add(this.txtOrdonnee);

        this.add(this.panel, BorderLayout.CENTER);
        this.add(this.btConfirmer, BorderLayout.SOUTH);

        this.btConfirmer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                String nom = txtNom.getText();
                int x = Integer.parseInt(txtAbscisse.getText());
                int y = Integer.parseInt(txtOrdonnee.getText());
				Ville ville;

                if (nom != "")
                {
                    ville.ajouterVille(nom, x, y);
                }
            }
        });


        

        this.setVisible(true);

	}

    public static void main(String[] args) {
		new FrameVille();
	}
	
}
