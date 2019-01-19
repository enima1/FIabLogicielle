package Recherche;

public class Recherche {

	
	public int chercherElt(int elt, int[] array) {
		
		if(array == null)
			return -1;
		if(array.length <= 0)
			return -1;
		for(int i = 0 ; i < array.length ; i++) 
			if( array[i] == elt)
				return i;
		return -1;
	}
		
	public boolean chercher1(int x, int []  tab){         
		/*recherche dichotomique 1*/
		int i,j,m;
		int n = tab.length;
		i = 0; j= n - 1; 
		while (i <= j) {
			m = (i + j) / 2;
			if (tab[m] < x){
				i = m + 1;
			}
			if (tab[m] > x) {
				j = m - 1;
			}
			if (tab[m] == x){
				return true;
			}
			if (i > n-1 || j < 0) {
				return false;
			}
		}
		return false;
	}
	
	public boolean chercher2(int x, int [] tab){ 
		/*recherche dichotomique 2*/
		int i,j,m;
		boolean found;
		i=1; 
		j= tab.length;
		m=0;
		found=false;
		while (!(i==j && !found)) {
			m=(i+j)/2;
			if (tab[m]<x){
				i=m+1;
			}    else {
				if(tab[x]==m) {
					found=true;
				}    else {
					j=m-1;
				}
			}
		}
		return found;
	}
	
	public boolean chercher3(int x, int [] tab){         
		/*recherche dichotomique 3*/
		int i,j,m;
		i = 1; 
		j = tab.length;
		m=0;
		while (i!=j) {
			m=(i+j)/2;
			if (tab[m]<=x) i=m;
			else j=m;
		}
		return (x==tab[m]);
	}
	
	public boolean chercher4(int x, int [] tab){  
		/*recherche dichotomique 4*/
		int i,j,m;
		i=1; 
		j = tab.length;
		m=0;
		boolean trouve = false;
		while (i!=j) {
			System.out.println("test");
			m=(i+j)/2;
			if (tab[m]<=x) i=m;
			if (tab[m] == x) trouve = true;
			else j=m;
		}
		System.out.println("test2");
		return trouve;
	}
	
	public boolean chercher5(int x, int [] tab){  
		/*recherche dichotomique 5*/
		int i,j,m;
		boolean trouve;	
		i=0; 
		j=tab.length - 1; 
		m=0;
		trouve=false;
		while ((i<=j)&&(!trouve) ){
			m=(i+j)/2;
			if (x==tab[m]){
				trouve=true;
			}
			else{ if (x < tab[m]){
				j=m-1;		
			} else {
				i=m+1;
			}
			}
		}
		return trouve;
	}	
}
