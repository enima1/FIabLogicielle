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

float *readData(char *filename){
  float tab[3] = { -1 , -1 , -1};

      FILE * fp;
      char * line = NULL;
      size_t len = 0;
      ssize_t read;

      fp = fopen("filename", "r");
      if (fp == NULL)
          exit(EXIT_FAILURE);

      while ((read = getline(&line, &len, fp)) != -1) {

          printf("%s", line);
          char delim[] = ",";
          char *temp = strtok(line,delim);
          int compteur = 0;

        	while(temp != NULL)
        	{
            
            if(temp == ""){
              printf("test");
              fill_tab_error(tab);
              break;
            }
            if(compteur > 2){
              fill_tab_error(tab);
              break;
            }
            tab[compteur] = atof(temp);
        		temp = strtok(NULL, delim);
            compteur++;
        	}
          int i;
          for(i = 0; i< 3; i++)
            printf("%lf \n", tab[i]);
      }




      fclose(fp);



   return tab;
}


int main(){

  float *tab = readData("filename");
}
