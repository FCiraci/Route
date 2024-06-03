public class Route
{
	private int    nbTroncons;
	private Ville villeDep  ;
	private Ville villeArr  ;

	public Route ajouterRoute(int nbTroncons, Ville villeDep,  Ville villeArr)
	{
		if (nbTroncons >= 0 && nbTroncons <= 10)
		{
			Route route = new Route(nbTroncons, villeDep, villeArr);
			return route;
		}
		else
		{
			return null;
		}

	}

	private Route(int nbTroncons, Ville villeDep,  Ville villeArr)
	{
		this.nbTroncons = nbTroncons;
		this.villeDep   = villeDep;
		this.villeArr   = villeArr;
	}

	public Ville getVilleDep() { return this.villeDep; }

	public Ville getVilleArr() { return this.villeArr; }

	public int getNbTroncons() { return this.nbTroncons; }

	public String toString()
	{
		String sRet = "";
		sRet = ("Route de " + this.villeDep + " à " + this.villeArr + ", nombre de tronçons : " + this.nbTroncons);
		return sRet;
	}
}
