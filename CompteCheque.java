package org.upec.andric.test;

public class CompteCheque extends Compte {
	 int numeroCarte;
	 int decouverteAutorisation;
	 Compte c;
	 int aNum= (int) (Math.random() * ( 49581343 - 0 ));
	 										   
	public  CompteCheque(int aNum,int aDec) {
		this.numeroCarte = aNum++;
		this.decouverteAutorisation = aDec;
		//System.out.printf("Le compte Numero � �t� supprim�\n");
	}

	//Surcharge de la fonction CompteCheque
	public CompteCheque(int aDec, Personne p) 
	{
		
		super();
		this.decouverteAutorisation = aDec; 
		this.nProprietaire = p.firstName + " " +  p.lastName;
		this.nSold = nSold;
		
		System.out.printf("Le compte Cheque Numero:" +" " + aNum + " " + "au nom de: " + " " 
							+ p.firstName + " " + p.lastName +" " +  " à été crée");
		
		// TODO Auto-generated constructor stub
	}

	public boolean estADecouvert() {
		return (this.nSold<0);
	}
	
}
