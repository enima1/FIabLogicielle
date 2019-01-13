"""
    Fichier triangle.py
    Programme de test pour les fonctions du triangle
    Auteurs: Amine Boudraa - Yannick Gosset
    Utilisation : executer en ligne de commande python triangle.py

"""
import unittest
import csv
import os


def isEquilateral(a,b,c):
    if( a == b and b == c and a == c):
        return 1
    return 0

def isIsocele(a,b,c):
    if( a == b or b == c or a == c):
         return 1
    return 0

def findType(a,b,c):
  if ( isEquilateral(a,b,c) == 1 ) :
      return 3
  if ( isIsocele(a,b,c) == 1 ):
      return 2
  return 1;

def typeTriangle(a,b,c):
    if( a <= 0 or b <= 0 or	c<=0):
         return -1

    if( (a >= b) and (a >= c) ) :
         if( a <= b + c ) :
             return findType(a,b,c);
         return -1

    if( (b >= a) and (b >= c) ) :
         if( b <= a + c ) :
             return findType(a,b,c);
         return -1

    if( (c >= a) and (c >= b) ) :
         if( c <= a + b ) :
             return findType(a,b,c);
         return -1

def fill_tab_error(tab):
    tab = [-1,-1,-1]
    return tab

def readData(filename):
        tableau = []
        count = 0
        with open(filename, 'rb') as csvfile:
            spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')
            for row in spamreader:
                for i in range(0,len(row)):
                    tableau.append(row[i])
                
        if(len(row) > 3 or len(row) < 3):
            tableau = [-1,-1,-1]
        for i in range(0,len(row)):
            if(float(row[i]) <=0):
                tableau = [-1,-1,-1]
        return tableau

class typeTriangleTest(unittest.TestCase):
    def test_isEquilateral(self):
        result = isEquilateral(3,3,3)
        self.assertEqual(1,result)
    def test_isIsocele(self):
        result = isIsocele(1,2,2)
        self.assertEqual(1,result)
    def test_findType(self):
        result = findType(3,3,3)
        self.assertEqual(3,result)
    def test_typeTriangle_nul_val(self):
        result = typeTriangle(0,1,6)
        self.assertEqual(-1,result)
    def test_typeTriangle_negative_val(self):
        result = typeTriangle(1,-1,3)
        self.assertEqual(-1,result)
    def test_typeTriangle_not_a_triangle(self):
        result = typeTriangle(1,1,3)
        self.assertEqual(-1,result)

class readDataTest(unittest.TestCase):
    def test_negative_val(self):
        result_array = readData("python_test_files/negative_val.csv")
        self.assertEqual([-1,-1,-1],result_array)
    def test_nul_val(self):
        result_array = readData("python_test_files/nul_val.csv")
        self.assertEqual([-1,-1,-1],result_array)
    def test_too_few_elem(self):
        result_array = readData("python_test_files/too_few_elem.csv")
        self.assertEqual([-1,-1,-1],result_array)
    def test_too_much_elem(self):
        result_array = readData("python_test_files/too_much_elem.csv")
        self.assertEqual([-1,-1,-1],result_array)
    def test_right_file(self):
        result_array = readData("python_test_files/right_file.csv")
        self.assertEqual(['1.1','2.2','3.3'],result_array)


if __name__ == '__main__':
    unittest.main()
