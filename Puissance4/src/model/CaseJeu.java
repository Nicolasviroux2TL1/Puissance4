package model;
/**
 * @author Viroux Nicolas & Filipp Shatskiy
 * 2TL1 
 * Projet Puissance 4 
 */
public class CaseJeu extends GrilleJeu {
 private char couleur; // declare couleur
 
 /**
  * Constructeur de CaseJeu
  */
 public CaseJeu() { 
  super();
  this.couleur = 'V'; // une case du jeu doit etre rempli par une couleur 
  					  // simplifier 
 }
 
 /**
  * Constructeur de CaseJeu avec une couleur en parametre
  * @param couleur a inserer dans une case
  */
 public CaseJeu(char couleur){
	 this.couleur = couleur;
 }
 /**
  * verifie si la case est vide
  * @return vrai ou faux selon la case est vide ou pas
  */
 public boolean caseVide(){
  return this.couleur == 'V';  
 }

 /**
  * @return la couleur de la case
  */
 public char getCouleur() { // getter 
  return couleur;
 }
 
 /**
  * @param couleur2 a inserer dans la case
  */
 public void setCouleur(char couleur2) { // setter
  this.couleur = couleur2;
 }
}