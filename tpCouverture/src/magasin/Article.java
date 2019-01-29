package magasin;
/**
 * classe Article utilisée dans Magasin
 * 
 * @author PR
 *
 */
public class Article {
	private String nom;
	private double prix;
	private int numero;

	public Article(String s, double p, int n) {
		assert ( ! s.isEmpty()  && p >= 0 && n >=0);
		this.nom = s;
		this.prix = p;
		this.numero = n;
	}
	/* getters*/	
	public String getNom() {
		return this.nom;
	}
	public double getPrix() {
		return this.prix;
	}

	public double getNumero() {
		return this.numero;
	}

	/*tests égalité */
	public Boolean  isEqual(Article article) {
		return  this.nom.equals(article.getNom()) &&
				this.prix == article.getPrix()    &&
				this.numero == article.getNumero();
	}
	
	public Boolean smallerThan(Article article) {
		return this.prix < article.getPrix() ||
				(this.prix == article.getPrix() && this.numero < article.getNumero());
	}

}
