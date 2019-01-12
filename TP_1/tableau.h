#include <stdio.h>
#include <stdlib.h>

typedef struct Tableau {
	int getDim;
	int *getValues;
}Tableau;

int chercherElt(int elt, Tableau tab);
