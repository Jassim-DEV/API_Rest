package org.upec.andric.test;

public class CompteEpargne extends Compte {
	double taux;
	Compte c;
	
	public  CompteEpargne(double nTaux, Personne pp) {	
		
		super();
		this.taux = nTaux;
		this.nProprietaire = pp.firstName + " " +  pp.lastName;
		System.out.println("Compte epargne crée au nom de: " + pp.firstName + ""  + " " + " à un taux d'interet de" +" " +nTaux);
		
	}
	
	
	public double calculInteret() {	
	
		this.taux = 1.10;
	  
		this.nSold=(this.nSold*this.taux);
		System.out.printf("Solde avec interets: %s\n", nSold);
	return nSold;
	}
	
	public double deposer(int inD) {
		
		nSold=nSold+inD;
		System.out.printf("\nSolde du compte épargne apres deposition: %s",nSold);
		
	return nSold;
		
	}
}
