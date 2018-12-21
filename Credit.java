package org.upec.andric.test;

public class Credit  {
	
	int numCredit;
	double sommes;
	double taux;
	int dureeEnMois;
	double mensualite;
	double sommeRestante;
	int numCompte;
	CompteCheque a;
	
	public Credit(int anumCredit, CompteCheque a,double asommes,double ataux, int adureeEnMois) 
	{
		this.numCredit = anumCredit;
		this.sommes= asommes;
		this.taux = ataux;
		this.dureeEnMois = adureeEnMois;
		this.sommeRestante = asommes+asommes*ataux;
		this.mensualite = (float) this.sommeRestante/adureeEnMois;
		this.a = a; /*int anumCompte*/
		
		System.out.printf("\nCredit de %s sur une duree de %s mois avec un taux de %s\n",asommes,adureeEnMois,ataux);
		System.out.printf("Paiement mensuelle sur %s mois: %s\n", adureeEnMois,this.mensualite  );
	}
	
	public double payerMensualite() {
		
	this.a.retirer(this.mensualite);
	this.sommeRestante=this.sommeRestante-this.mensualite;
	
	return this.sommeRestante;
		
	}
	
	public double payerAnticipation(double sommeAnticiper) {
				
		this.sommeRestante = this.sommeRestante - sommeAnticiper;
		
		return this.sommeRestante;
		
	}

}
