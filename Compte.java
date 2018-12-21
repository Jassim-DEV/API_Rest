package org.upec.andric.test;

public class Compte {
	 String nProprietaire;
	 int compteNumero;
	 double nSold;
	 Personne infopersonne;
	 public int numero;
	
	public Compte()
	{
		System.out.println("Compte crée");
		this.compteNumero = 0;
		this.nProprietaire = "";
		this.nSold = 0;
	}

	public Compte(String pProprietaire, int pCompteNumero, double pSold, Personne c)
	{
		//this.nProprietaire = pProprietaire;
		this.compteNumero = pCompteNumero;
		this.nSold =  pSold;
		
		System.out.println("Creation d'un nouveau compte bancaire: ");
		System.out.println("Proprietaire:" + infopersonne.firstName + " " + infopersonne.lastName 
						+ "\t Numero du compte:" + compteNumero 
						+ "\t Solde du compte: " + nSold +" "+ "Euros\n"); 
		//System.out.print(nNumero);
		//System.out.print(nSold);
	}
	  
	
	// méthode deposer()
	public double deposer(int inDeposit)
	{
		this.nSold=nSold;
		nSold=nSold+inDeposit;
		System.out.printf("\nSolde du compte apres deposition: %s",nSold);
	return nSold;
	}
	

	// méthode retirer()
	public double retirer(double withdraw)
	{	
		nSold=nSold-withdraw;
		System.out.printf("\nSolde du compte apres avoir retirer: %s",nSold);
	return nSold;
	}
}
