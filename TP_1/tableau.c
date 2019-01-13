()/* File: triangle.c
Auteurs: Amine Boudraa - Yannick Gosset
crée le 12/01/2019
*/

#include "tableau.h"

int chercherElt(int elt, Tableau tab){
	if(tab.getValues == NULL) return -1;
	if(tab.getDim <= 0) return -1;
	for(int i = 0; i < tab.getDim; ++i){
		if(&(tab.getValues[i]) == NULL)return -1;
		if(elt == tab.getValues[i]) return i;
	}
	return -1;
}
/*
int main(int argc, char const *argv[])
{
	int taille = 5;
	int *valeurs = (int*) malloc ( sizeof(int) * taille );
	for (int i = 0; i < taille; ++i)
	{
		valeurs[i] = i;
	}
	Tableau t;
	t.getDim = taille;
	t.getValues = valeurs;
	free(valeurs);
	return 0;
}*/
