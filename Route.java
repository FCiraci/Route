public class Route
{
	private int    nbTroncons;
	private String villeDep  ;
	private String villeArr  ;

	public Route ajouterRoute(int nbTroncons, String villeDep,  String villeArr)
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

	private Route(int nbTroncons, String villeDep,  String villeArr)
	{
		this.nbTroncons = nbTroncons;
		this.villeDep   = villeDep;
		this.villeArr   = villeArr;
	}

	public String toString()
	{
		String sRet = "";
		sRet = ("Route de " + this.villeDep + " à " + this.villeArr + ", nombre de tronçons : " + this.nbTroncons);
		return sRet;
	}
}
