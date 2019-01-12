/* File: triangle.c
Auteurs: Amine Boudraa - Yannick Gosset
crée le 07/01/2019
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "triangle.h"

int isEquilateral(float a,float b,float c){
  return (a == b && b == c );
}

int isIsocele(float a,float b,float c){
  return ( a == b || b == c || a == c);
}

int findType(float a,float b,float c){
  if(isEquilateral(a,b,c) == 1)return 3;
  else if(isIsocele(a,b,c) == 1)return 2;
  else return 1; // quelconque
}

int typeTriangle(float a,float b,float c){
  if( a <= 0 ||	b <= 0 ||	c<=0) return -1;

  //a est le côté le plus grand
  if( (a >= b) && (a >= c) ) {
      if( a <= b + c ) return findType(a,b,c);
      else return -1; // pas un triangle
  }

  //b est le côté le plus grand
  if( (b >= a) && ( b >= c) ) {
      if( b <= a + c ) return findType(a,b,c);
      else return -1; // pas un triangle
  }

  //c est le côté le plus grand
  if( (c >= a) && (c >= b) ) {
      if( c <= a + b ) return findType(a,b,c);
      else return -1; // pas un triangle
  } 

  return -1;
}

void fill_tab_error(float * tab){
  int i;
  for(i = 0; i < 3 ; i++)
    tab[i] = -1.0;
}


float *readData(char *filename){
  float *tab;
  tab = (float*) malloc ( sizeof(float) * 3 );
     
  fill_tab_error(tab);

  if(filename == NULL){
    fprintf(stderr, "filename is NULL\n");
    return(tab);
  }
  

  FILE * fp;
  char * line = NULL;
  size_t len = 0;
  ssize_t read;
  //https://www.tutorialspoint.com/c_standard_library/c_function_strchr.htm
  char *extension = strchr(filename,'.');
  if(extension == NULL){
    fprintf(stderr, "%s : pas d'extension de fichier\n", filename);
    return tab;
  }
  if (strcmp(extension, ".csv") != 0){
    fprintf(stderr, "%s : mauvaise extension de fichier (csv only)\n", filename);
    return tab;
  }

  fp = fopen(filename, "r");
  if (fp == NULL){
    fprintf(stderr, "%s introuvable.\n", filename);
    return tab;
  }
      
  int compteur = 0;
  while ((read = getline(&line, &len, fp)) != -1) {

    printf("line = %s\n", line);
    char delim[] = ",";
    char *temp = strtok(line,delim);
    

    while(temp != NULL)
  	{
      printf("temp = %s\n", temp);
      if(compteur > 2){
        fprintf(stderr, "%s : mauvais format de fichier. (3 value only)\n", filename);
        fill_tab_error(tab);
        fclose(fp);
        return tab;
      }

      tab[compteur] = atof(temp);
      /*
        atof returns 0.0 in case of wrong conversion.
        This implies wrong file content OR non-triangle file,
        in both cases typeTriangle's result won't change.
        So 0.0 value is considered as an error.
      */
      if(tab[compteur] == 0.0){
        fprintf(stderr, "%s : a 0 found or non-digits values found\n", filename);
        fill_tab_error(tab);
        fclose(fp);
        return tab; 
      }
  		temp = strtok(NULL, delim);
      compteur++;
  	}

  }
  /*
  1) quand on détecte 2 virgule de suite on veut retourner -1
  2) quand on voit qu'une longueur vaut 0 on retourne -1
  3) quand on voit que c'est autre chose que des floats genre des lettres
    on retourne -1 aussi
  */
  if(compteur < 3){
    fprintf(stderr, "%s : mauvais format de fichier. (3 value only)\n", filename);
    fill_tab_error(tab);
  }
  printf("closing file\n");
  fclose(fp);
  return tab;
}

/*
int main(){

  float *tab = readData("isocele.csv");

  int i ;
  for ( i = 0 ; i < 3 ; i++)
    printf("%lf\n", tab[i] );
  printf("triangle type = %d\n", typeTriangle(tab[0],tab[1],tab[2]));
  free(tab);
  return 0;
}
*/