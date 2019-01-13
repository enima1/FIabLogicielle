/* File: triangle.py
Auteurs: Amine Boudraa - Yannick Gosset
crée le 12/01/2019
*/

/* Programme de tests pour les fonctions du triangle  */
/*Utilisation : executer en ligne de commande : python triangle.py
*/
import unittest


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
