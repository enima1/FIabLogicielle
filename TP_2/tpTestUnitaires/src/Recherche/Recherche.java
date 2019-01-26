/**
 * @author Amine Boudraa
 * @author Yannick Gosset
 * @file Recherche.java
 */

package Recherche;

public class Recherche {

	public int chercherElt(int elt, int[] array) {
		int pos = -1;
		
		if (array == null)
			return -1;
		if (array.length <= 0)
			return -1;
		if(array[0] == elt) pos = 0;
		for (int i = 1; i < array.length; i++) {
			if(array[i-1] > array[i]) return -1;//Pas trié
			if (array[i] == elt && pos == -1) pos = i;
		}
		return pos;
	}

	public boolean chercher1(int x, int[] tab) {
		/* recherche dichotomique 1 */
		int i, j, m;
		int n = tab.length;
		i = 0;
		j = n - 1;
		while (i <= j) {
			m = (i + j) / 2;
			if (tab[m] < x) {
				i = m + 1;
			}
			if (tab[m] > x) {
				j = m - 1;
			}
			if (tab[m] == x) {
				return true;
			}
			if (i > n - 1 || j < 0) {
				return false;
			}
		}
		return false;
	}

	public boolean chercher2(int x, int[] tab) {
		/* recherche dichotomique 2 */
		int i, j, m;
		boolean found;
		i = 1;
		j = tab.length;
		m = 0;
		found = false;
		while (!(i == j && !found)) {//Mauvaise condition
			m = (i + j) / 2;
			if (tab[m] < x) {
				i = m + 1;
			} else {
				if (tab[x] == m) {
					found = true;
				} else {
					j = m - 1;
				}
			}
		}
		return found;
	}

	public boolean chercher3(int x, int[] tab) {
		/* recherche dichotomique 3 */
		int i, j, m;
		i = 1;
		j = tab.length;
		m = 0;
		while (i != j) {
			m = (i + j) / 2;
			if (tab[m] <= x)//m +/- 1
				i = m;
			else
				j = m;
		}
		return (x == tab[m]);
	}

	public boolean chercher4(int x, int[] tab) {
		/* recherche dichotomique 4 */
		int i, j, m;
		i = 1;
		j = tab.length;
		m = 0;
		boolean trouve = false;
		while (i != j) {
			m = (i + j) / 2;
			if (tab[m] <= x)// erreur, ça doit être inférieur strict
				i = m;
			if (tab[m] == x)
				trouve = true;
			else
				j = m;
		}
		return trouve;
	}

	public boolean chercher5(int x, int[] tab) {
		/* recherche dichotomique 5 */
		int i, j, m;
		boolean trouve;
		i = 0;
		j = tab.length - 1;
		m = 0;
		trouve = false;
		while ((i <= j) && (!trouve)) {
			m = (i + j) / 2;
			if (x == tab[m]) {
				trouve = true;
			} else {
				if (x < tab[m]) {
					j = m - 1;
				} else {
					i = m + 1;
				}
			}
		}
		return trouve;
	}
}
