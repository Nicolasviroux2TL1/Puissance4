package model;
/**
 * @author Viroux Nicolas & Filipp Shatskiy
 * 2TL1 
 * Projet Puissance 4 
 */
public class CaseJeu extends GrilleJeu {
 private String couleur; // declare couleur
 
 /**
  * Constructeur de CaseJeu
  */
 public CaseJeu() { 
  super();
  this.couleur = "VIDE"; // une case du jeu doit etre rempli par une couleur 
  					  // simplifier 
 }
 
 /**
  * Constructeur de CaseJeu avec une couleur en parametre
  * @param couleur a inserer dans une case
  */
 public CaseJeu(String couleur){
	 this.couleur = couleur;
 }
 /**
  * verifie si la case est vide
  * @return vrai ou faux selon la case est vide ou pas
  */
 public boolean caseVide(){
  return this.couleur == "VIDE"; 
 }

 /**
  * @return la couleur de la case
  */
 public String getCouleur() { // getter 
  return couleur;
 }
 
 /**
  * @param couleur a inserer dans la case
  */
 public void setCouleur(String couleur) { // setter
  this.couleur = couleur;
 }
}