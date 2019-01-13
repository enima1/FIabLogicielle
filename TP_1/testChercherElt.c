/* File: triangle.c
Auteurs: Amine Boudraa - Yannick Gosset
crée le 12/01/2019
*/

/* Programme de tests pour la fonction chercherElt de tableau.c */
/*Utilisation : compiler tableau pour obtenir tableau.o
                gcc -Wall -c tableau.c
                compiler testChercherElt.c en incluant la librairie pour avoir l'executable
                gcc -Wall -o testChercherElt testChercherElt.c tableau.o -lcunit
*/

#include "tableau.h"
#include <stdio.h>
#include <CUnit/CUnit.h>
#include <CUnit/Basic.h>

/*Test suites and clean up functions*/
int init_suite(void) {return 0;}
int clean_suite(void){return 0;}

/* les tests*/
void testNull(void){
	Tableau tab;
	tab.getValues = NULL;
	tab.getDim = 0;
  int elt = 42;
  CU_ASSERT_EQUAL(chercherElt(elt,tab), -1);
}

void testNullWithWrongDim(void){
	Tableau tab;
	int taille = 5;
	tab.getValues = NULL;
	tab.getDim = taille;
  int elt = 42;
  CU_ASSERT_EQUAL(chercherElt(elt,tab), -1);
}

void testEmpty(void){
	Tableau tab;
	int *valeurs = (int*) malloc ( sizeof(int) * 0 );
	tab.getValues = valeurs;
	tab.getDim = 0;
  int elt = 42;
  CU_ASSERT_EQUAL(chercherElt(elt,tab), -1);
}

void testDimTooBig(void){
	Tableau tab;
	int taille = 5;
	int *valeurs = (int*) malloc ( sizeof(int) * taille );
	tab.getValues = valeurs;
	tab.getDim = taille;
  int elt = 42;
  CU_ASSERT_EQUAL(chercherElt(elt,tab), -1);
}

void testNoElementFound(void){
	Tableau tab;
	int taille = 5;
	int *valeurs = (int*) malloc ( sizeof(int) * taille );
	for (int i = 0; i < taille; ++i){valeurs[i] = i;}
	tab.getValues = valeurs;
	tab.getDim = taille;
  int elt = 42;
  CU_ASSERT_EQUAL(chercherElt(elt,tab), -1);
}

void testElementFound(void){
	Tableau tab;
	int taille = 5;
	int *valeurs = (int*) malloc ( sizeof(int) * taille );
	for (int i = 0; i < taille; ++i){valeurs[i] = i;}
	tab.getValues = valeurs;
  int elt = 0;
  CU_ASSERT_EQUAL(chercherElt(elt,tab), 0);
}



/* Mise en place des tests via main */
int main(void){
  CU_pSuite pSuite = NULL;
  /*initialise le catalogue de tests*/
  printf("initialize test registry\n");
  if (CUE_SUCCESS != CU_initialize_registry()) {
    return CU_get_error();
  }
  printf("add suite to testregistry\n");
  /*ajoute une suite de tests au catalogue */
  /*la suite de test n'a qu'un seul test ici!!!*/
  pSuite = CU_add_suite("essaiTestSuite", init_suite, clean_suite);
  if (pSuite == NULL){
    CU_cleanup_registry();
    return CU_get_error();
  }

  /*ajouter les  tests à la suite */
  printf("add first test\n");
  if ((CU_add_test(pSuite,"testNull", testNull) == NULL)
      ||(CU_add_test(pSuite,"testNullWithWrongDim", testNullWithWrongDim) == NULL)
      ||(CU_add_test(pSuite,"testDimTooBig", testDimTooBig) == NULL)
      ||(CU_add_test(pSuite,"testEmpty", testEmpty) == NULL)
      ||(CU_add_test(pSuite,"testNoElementFound", testNoElementFound) == NULL)
      ||(CU_add_test(pSuite,"testElementFound", testElementFound) == NULL)
      ){
    CU_cleanup_registry();
    return CU_get_error();
  }

  /*Run all tests using the basic interface */
  printf("start execution\n");
  CU_basic_set_mode(CU_BRM_VERBOSE);
  CU_basic_run_tests();
  printf("termine\n\n");
  CU_basic_show_failures(CU_get_failure_list());

  /*Clean up registry and return*/
  CU_cleanup_registry();
  return CU_get_error();

}
