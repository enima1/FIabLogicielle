#include "stdio.h"
#include "stdlib.h"
#include <CUnit/CUnit.h>



int typeTriangle(float a,float b,float c){



if( a <= 0 ||	b <= 0 ||	c<=0) return -1;

if( a == b == c ) return 3;

if( a == b || b == c || a == c) return 2; // isocèle



if( (a > b) && (a > c) ) {
    if( a <= b + c ) return 1; // quelquonque
    else return -1; // pas un triangle
}

if( (b > a) && ( b > c) ) {
    if( b <= a + c ) return 1; // quelquonque
    else return -1; // pas un triangle
}

if( (c > a) && (c  > b) ) {
    if( c <= a + b ) return 1; // quelquonque
    else return -1; // pas un triangle
}

}

void fill_tab_error(float * tab){
  int i;
  for(i = 0; i < 3 ; i++)
    tab[i] = -1;

}


float *readData(char *filename, float *tab){

      int i;
      for(i = 0 ; i < 3 ; i ++)
        tab[i] = -1;

      FILE * fp;
      char * line = NULL;
      size_t len = 0;
      ssize_t read;

      fp = fopen(filename, "r");
      if (fp == NULL)
          exit(EXIT_FAILURE);

      while ((read = getline(&line, &len, fp)) != -1) {

          printf("%s", line);
          char delim[] = ",";
          char *temp = strtok(line,delim);
          int compteur = 0;

          while(temp != NULL)
        	{

            if(compteur > 2){
              fill_tab_error(tab);
              break;
            }

            tab[compteur] = atof(temp);
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
        int j;
        for(j = 0; j < 3 ; j++)
          if(tab[i] < 0) fill_tab_error(tab);
        fclose(fp);

}


int main(){

  float tableau_main[3] ;
  readData("filename",tableau_main);


    int i ;
    for ( i = 0 ; i < 3 ; i++)
      printf("%lf\n", tableau_main[i] );


}
