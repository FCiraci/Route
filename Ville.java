import java.util.ArrayList;
import java.util.List;

public class Ville 
{
	private static int nbVilles = 0;
	
	private int    numero;
	private String nom   ;
	private int    x     ;
	private int    y     ;
	private List   routes;


	public Ville ajouterVille(String nom, int x,  int y)
	{
		if (x >= 0 && x <= 1000 && y >= 0 && y <= 800)
		{
			Ville ville = new Ville(nom, x, y);
			return ville;
		}
		else
		{
			return null;
		}

	}

	private Ville(String nom, int x, int y)
	{
		this.numero = ++Ville.nbVilles;
		this.nom    = nom;
		this.x      = x;
		this.y      = y;
		this.routes = new ArrayList<Ville>();
	}

	public String toString()
	{
		String sRet = "";
		sRet = ("Ville " + this.numero + " : " + this.nom + ", x : " + this.x + ", y : " + this.y + this.routes);
		return sRet;
	}
}
