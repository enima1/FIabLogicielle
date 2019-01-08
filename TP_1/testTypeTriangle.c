/* Programme de tests pour la fonction typeTriangle de triangle.c */
/*Utilisation : compiler triangle pour obtenir triangle.o
                gcc -Wall -c triangle.c
                compiler testTypeTriangle.c en incluant la librairie pour avoir l'executable
                gcc -Wall -o testTypeTriangle testTypeTriangle.c triangle.o -lcunit
*/

#include "triangle.h"
#include <stdio.h>
#include <CUnit/CUnit.h>
#include <CUnit/Basic.h>

/*Test suites and clean up functions*/
int init_suite(void) { return 0;}
int clean_suite(void){return 0;}

/* les tests*/
void testEquilateral(void) {
  float a = 5.0;
  CU_ASSERT_EQUAL(typeTriangle(a, a, a), 3);
}

void testIsocele(void) {
  float a = 5.0;
  float c = 3.0;
  CU_ASSERT_EQUAL(typeTriangle(a, a, c), 2);
}

void testRect(void) {
  float a = 5.0;
  float b = 4.0;
  float c = 3.0;
  CU_ASSERT_EQUAL(typeTriangle(a, b, c), 1);
}

void testScalene(void) {
  float a = 6.0;
  float b = 5.5;
  float c = 3.2;
  CU_ASSERT_EQUAL(typeTriangle(a, b, c), 1);
}

void testNegativeVal(void) {
  float a = -5.0;
  float b = 5.0;
  float c = 3.0;
  CU_ASSERT_EQUAL(typeTriangle(a, b, c), -1);
}

void testNonTriangle(void) {
  float a = 5.0;
  float b = 1.0;
  float c = 2.0;
  CU_ASSERT_EQUAL(typeTriangle(a, b, c), -1);
}

/*
	Ce test montre que même si deux valeurs sont égales,
	ce n'est pas forcément un triangle donc il n'est pas isocèle.
*/
void testNonTriangleIsocele(void) {
  float a = 10.0;
  float b = 1.0;
  float c = 1.0;
  CU_ASSERT_EQUAL(typeTriangle(a, b, c), -1);
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
  if ((CU_add_test(pSuite,"testEquilateral", testEquilateral) == NULL) 
      ||(CU_add_test(pSuite,"testIsocele", testIsocele) == NULL)
      ||(CU_add_test(pSuite,"testRect", testRect) == NULL) 
      ||(CU_add_test(pSuite,"testScalene", testScalene) == NULL)
      ||(CU_add_test(pSuite,"testNonTriangle", testNonTriangle) == NULL) 
      ||(CU_add_test(pSuite,"testNegativeVal", testNegativeVal) == NULL)
      ||(CU_add_test(pSuite,"testNonTriangleIsocele", testNonTriangleIsocele) == NULL) 
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