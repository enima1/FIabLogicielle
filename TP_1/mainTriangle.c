/* File: mainTriangle.c
Auteurs: Amine Boudraa - Yannick Gosset
crée le 08/01/2019
*/

#include <stdio.h>
#include <stdlib.h>
#include "triangle.h"

int main(){

  float *tab = readData("isocele.csv");

  int i ;
  for ( i = 0 ; i < 3 ; i++)
    printf("%lf\n", tab[i] );
  printf("triangle type = %d\n", typeTriangle(tab[0],tab[1],tab[2]));
  free(tab);
  return 0;
}
