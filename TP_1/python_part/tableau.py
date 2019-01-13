"""
    Fichier tableau.py
    Programme de test pour la fonction ChercherElt
    Auteurs: Amine Boudraa - Yannick Gosset
    Utilisation : executer en ligne de commande python tableau.py

"""

import unittest

class Chercher:
    global tabElement
    def __init__(self, tabElement = []):
         self.tabElement = tabElement
    def ChercherElement(self,elt):
        if(self.tabElement is None):
            return -1
        if(len(self.tabElement) <= 0):
            return -1
        for i in range(0,len(self.tabElement)):
            if( self.tabElement[i] is None):
                return -1
            if( elt == self.tabElement[i] ):
                return i
        return -1


class ChercherElement(unittest.TestCase):
    def test_tab_nul(self):
        nul_tab = Chercher(None)
        result = Chercher.ChercherElement(nul_tab,1)
        self.assertEqual(-1,result)
    def test_empty_tab(self):
        empty_tab = Chercher([])
        result = Chercher.ChercherElement(empty_tab,3)
        self.assertEqual(-1,result)
    def test_element_not_found(self):
        tab = Chercher([1,3,4,5])
        result = Chercher.ChercherElement(tab,6)
        self.assertEqual(-1,result)
    def test_element_found(self):
        tab = Chercher([1,3,4,5])
        result = Chercher.ChercherElement(tab,1)
        self.assertEqual(0,result)



if __name__ == '__main__':
    unittest.main()
