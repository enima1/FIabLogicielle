package magasin;

public class Magasin {
	/**
	 * @author: PR
	 * Cette classe implemente un magasin
	 * stock est un tableau d'Articles
	 * nbMaxArticle est la taille maximal du stock 
	 * nbArticle est le nombre d'Article effectivement 
	 * dans le stock.
	 * Le stock est tri√© par prix croissant puis num√©ro de nomenclature croissant
	 * 
	 */
	private int nbArticle;	
	private int nbMaxArticle;
	private Article [] stock;
	
	public Magasin(int nbart) {
		assert (nbart >= 0);
		this.stock = new Article [nbart];
		this.nbArticle = 0;
		this.nbMaxArticle = nbart;
	}
	
	public int getNbArticle() {
		return this.nbArticle;
	}
	public int getNbMaxArticle() {
		return this.nbMaxArticle;
	}
	
	public Article [] getStock() {
		return this.stock;
	}
	
	public void setStock( Article [] newStock) {
		int taille = newStock.length;
		assert (taille <= this.nbMaxArticle); //Brutal
		for (int i =0; i < taille; i++) {
			this.stock[i] = newStock[i];
		}
		nbArticle = taille;
	}
	/*
	 * recherche dichotomique de l'Article article dans le stock qui 
	 * est ordonn√© de mani√®re croissante 
	 * Erreur : i = 1 alors que i doit Ítre Ègal ‡ 0
	 * On continue d'itÈrer mÍme si on trouve
	 */
	public Boolean chercherDicho1(Article article) {
		int i, j, n, m;
		n = this.nbArticle;
		i = 1;
		j = n;
		m = 0;
		while (i != j) {
			m = (i + j) / 2;
			if (this.stock[m].getPrix() <= article.getPrix())  {
				i = m;
			}
			else {
				j = m;
			}
		}
		return article.getPrix() == this.stock[m].getPrix();		
	}
	
	/* version2 de la recherche
	 * Renvoie toujours vrai sans tenir compte de l'ordre du stock.
	 *  */
	public Boolean chercherDicho2(Article article) {
		int i, j, n, m;
		n = this.nbArticle;
		boolean found = false;
		i = 1;
		j = n;
		m = 0;
		while (i != j && !found) {
			m = (i + j) / 2;
			if (this.stock[m].getPrix() < article.getPrix())  {
				i = m + 1;
			}
			else {
				if (this.stock[m].getPrix() == article.getPrix()) {
					found = true;
				}
				else {
					j = m - 1;
				}
			}
		}
		return found;		
	}
	
	/* version 3 de la recherche 
	 * Erreur : i <= j On s'arrÍte une fois trop tÙt
	 * */
	public Boolean chercherDicho3(Article article) {
		int i, j, n, m;
		n = this.nbArticle;
		boolean found = false;
		i = 0;
		j = n - 1;
		m = 0;
		while (i <= j && !found) {
			m = (i + j) / 2;
			if (this.stock[m].getPrix() == article.getPrix())  {
				found = true;
			}
			else {
				if (this.stock[m].getPrix() < article.getPrix()) {
					j = m - 1;
				}
				else {
					i = m + 1;
				}
			}
		}
		return found;		
	}
	
	/*version 4 
	 * Ne tient pas compte de l'ordre du stock
	 * */
	public Boolean chercherDicho4(Article article) {
		int i, j, n, m;
		n = this.nbArticle;
		i = 0;
		j = n - 1;
		m = 0;
		while (i <= j) {
			m = (i + j) / 2;
			if (this.stock[m].getPrix() < article.getPrix())  {
				i = m + 1;
			}
			if (this.stock[m].getPrix() > article.getPrix()) {
				j = m - 1;
						}
			if (this.stock[m].getPrix() ==  article.getPrix()) {
				return true;
			}
			if (i > n - 1 || j < 0) {
				return false;
			}
				
		}
		return false;
	}
	
	/*
	 * Renvoie vrai si les prix sont diffÈrents !?
	 * 
	 * */
	public Boolean chercherDicho5(Article article) {
		int n = this.nbArticle;
		int i = 0;
		int j = n - 1;
		int m = 0; 
		while (i != j) {
			m = (i + j )/2;
			if (this.stock[m].getPrix() <= article.getPrix()) {	
				i = m;
				if (this.stock[i].getPrix() != article.getPrix()) {
					return true;
				}
			}
			else {
				j = m;
			}
		}
		return false;
	}
	
	public Boolean chercherDichoEtu(Article article) {
		if(article == null) return false;
		int n = this.nbArticle;
		int min = 0;
		int max = n;
		int m = 0; 
		while (min != max) {
			m = (min + max )/2;
			if (this.stock[m].getPrix() < article.getPrix()) {	
				min = m + 1;
			}
			else if (this.stock[m].getPrix() == article.getPrix()){
				return true;
			}
			else {
				max = m;
			}
			if (min > n - 1 || max < 0) {
				return false;
			}
		}
		return false;
	}
	
	public void afficherStock() {
		int nbArticle = this.nbArticle;
		System.out.println(this.nbMaxArticle);
		System.out.println(this.nbArticle);
		
		for (int i = 0; i < nbArticle; i++) {
			System.out.println(this.getStock()[i].getNumero());
		}
	}
	
	
	/* inserer article dans le magasin courant dont le stock 
	 * est ordonn√© par prix croissant*/
	public void insererArticle(Article article) {
		int i;
		assert (this.getStock().length == this.nbMaxArticle);
		int nbArticle = this.getNbArticle();
		/*insertion uniquement si stock non plein*/
		if (nbArticle < this.nbMaxArticle) {
			/*stock vide */
			if (nbArticle == 0) {
				this.stock[0] = article;
			}
			else {
				/*chercher indice insertion de article*/
				for (i = 0; i < nbArticle; i++) {
					if (this.stock[i].getPrix() > article.getPrix()) {
						break;
					}
				}
				/*insertion en milieu de tableau*/
				if (i <  nbArticle ) {
					/*decaler element un cran a droite */
					for (int j = nbArticle; j > i; j--) {
						this.stock[j] = this.stock[j - 1];
					}
					/*ajouter article en indice i*/
					System.out.println("indice insertion");;
					System.out.println(i);
					this.stock[i] = article;
				}
				/*prix d'article superieur a tous les autres donc
				 * insertion apres dernier element
				 * */
				else {
					this.stock[nbArticle] = article;
				}	
			}
			/*incrementer le nb d'articles present apr√®s une insertion*/
			this.nbArticle ++;
		}
		else {
			System.out.println("capacite atteinte");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("nbarticle = 10");
		Magasin magasin = new Magasin(10);
		String nom;
		double  prix;
		int numero;
		for (int i = 0; i < 10; i++) {
			nom = "";
			prix = 10 - i;
			numero = i;
			Article article = new Article("a", prix, numero);
			magasin.insererArticle(article);
		}
		System.out.println(magasin.getNbArticle());
		magasin.nbArticle = 10;
		System.out.print("done\n");
		magasin.afficherStock();

	}


}