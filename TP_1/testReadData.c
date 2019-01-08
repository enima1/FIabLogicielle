/* Programme de tests pour la fonction readData de triangle.c */
/*Utilisation : compiler triangle pour obtenir triangle.o
                gcc -Wall -c triangle.c
                compiler testReadData.c en incluant la librairie pour avoir l'executable
                gcc -Wall -o testReadData testReadData.c triangle.o -lcunit
*/

#include "triangle.h"
#include <stdio.h>
#include <CUnit/CUnit.h>
#include <CUnit/Basic.h>

static float tabFaux[3] = {-1.0,-1.0,-1.0};
/*Test suites and clean up functions*/
int init_suite(void) { return 0;}
int clean_suite(void){return 0;}

/* les tests*/
void testNullFileName(void) {
  CU_ASSERT_EQUAL(readData(NULL), tabFaux);
}

void testFileNotFound(void) {
  char fileName[1024] = "testFiles/Introuvable.discret";
  CU_ASSERT_EQUAL(readData(fileName), tabFaux);
}

void testWrongExtension(void) {
  char fileName[1024] = "testFiles/monScriptMalicieux.py";
  CU_ASSERT_EQUAL(readData(fileName), tabFaux);
}

void testDoubleExtensionOne(void) {
  char fileName[1024] = "testFiles/virus.py.csv";
  CU_ASSERT_EQUAL(readData(fileName), tabFaux);
}

void testDoubleExtensionTwo(void) {
  char fileName[1024] = "testFiles/virus.csv.py";
  CU_ASSERT_EQUAL(readData(fileName), tabFaux);
}

void testWrongFormatLong(void) {
  char fileName[1024] = "testFiles/quatreVal.csv";
  CU_ASSERT_EQUAL(readData(fileName), tabFaux);
}

void testWrongFormatCourt(void) {
  char fileName[1024] = "testFiles/deuxVal.csv";
  CU_ASSERT_EQUAL(readData(fileName), tabFaux);
}

void testWrongFormatLettre(void) {
  char fileName[1024] = "testFiles/fichierSur.csv";
  CU_ASSERT_EQUAL(readData(fileName), tabFaux);
}

void testBinaryReject(void){
	char fileName[1024] = "testFiles/fichierBinaire";
  CU_ASSERT_EQUAL(readData(fileName), tabFaux);	
}

void testReadDataInt(void){
  float tabJuste[3] = {2.0,2.0,1.0};
	char fileName[1024] = "testFiles/iso.csv";
  CU_ASSERT_EQUAL(readData(fileName), tabJuste);	
}

void testReadDataFloat(void){
  float tabJuste[3] = {3.0,3.0,3.0};
	char fileName[1024] = "testFiles/equi.csv";
  CU_ASSERT_EQUAL(readData(fileName), tabJuste);
}

void testReadDataNeg(void){
  float tabJuste[3] = {-5.0,-4.0,3.0};
  char fileName[1024] = "testFiles/equi.csv";
  CU_ASSERT_EQUAL(readData(fileName), tabJuste);
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
  if ((CU_add_test(pSuite,"testFileNotFound", testFileNotFound) == NULL) 
      ||(CU_add_test(pSuite,"testNullFileName", testNullFileName) == NULL)
      ||(CU_add_test(pSuite,"testWrongExtension", testWrongExtension) == NULL)
      ||(CU_add_test(pSuite,"testWrongFormatLettre", testWrongFormatLettre) == NULL) 
      ||(CU_add_test(pSuite,"testWrongFormatLong", testWrongFormatLong) == NULL) 
      ||(CU_add_test(pSuite,"testWrongFormatCourt", testWrongFormatCourt) == NULL)
      ||(CU_add_test(pSuite,"testDoubleExtensionTwo", testDoubleExtensionTwo) == NULL)
      ||(CU_add_test(pSuite,"testDoubleExtensionOne", testDoubleExtensionOne) == NULL) 
      ||(CU_add_test(pSuite,"testReadDataInt", testReadDataInt) == NULL) 
      ||(CU_add_test(pSuite,"testReadDataFloat", testReadDataFloat) == NULL)
      ||(CU_add_test(pSuite,"testReadDataNeg", testReadDataNeg) == NULL)
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