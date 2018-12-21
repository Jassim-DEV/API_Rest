package org.upec.andric.test;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagementdB 
{
    
    Connection con = null;
    
    public ManagementdB()
    {
    	String url = "jdbc:mysql://localhost/bank";
        String user = "root";
        String passwd = "mysql";
  
    	 try 
    	    {
    		      Class.forName("com.mysql.jdbc.Driver"); 
    		      Connection conn = DriverManager.getConnection(url, user, passwd);
    		      System.out.println("Driver O.K.");
    		      Statement state = conn.createStatement();
    	    }
    	        catch (Exception e) 
    	   	{
    	    	e.printStackTrace();
     	   	} 	 
    }
   
    
	public int ajouterPersonne(String prenom, String nom, String datenaissance, int numeroCompte)
	{
		String requete = "insert into personne(Prenom, Nom, dateNaissance) VALUES('"+prenom+"','"+nom+"','"+datenaissance+"','"+numeroCompte+"');";
		if (update(requete)==-1)
			return -1;
		else 
			return 0;
	}
	

	public int ajouterCompteCheque(int ColonnenNumero,int ColonnenProprietaire,float ColonnenSold, float decouvertAuth, int ColonnenumCarte, float taux)
	{
		String requete = "insert into compte (nNumero, nProprietaire, nSold, taux, decouvertAuth, numCarte ) VALUES("+ColonnenNumero+","+ColonnenProprietaire+"',"+ColonnenSold+","+taux+","+decouvertAuth+","+ColonnenumCarte+");";
		if (update(requete)==-1)
			return -1;
		else 
//		{
//			//requete = "update personne set nbcompte = nbcompte + 1 where id ="+ColonnenProprietaire+";";
//			update(requete);
			return 0;
//		}
	}
	
	public int ajouterCompteEpargne(int numero,
									int proprietaire_id,
									float solde, 
									float taux)
	{
		String requete = "insert into compte VALUES("+numero+","+proprietaire_id+",'Compte Epargne',"+solde+","+taux+",NULL,NULL);";
		envoieRequete(requete);
		requete = "update personne set nbcompte = nbcompte + 1  where id ="+proprietaire_id+";";
		envoieRequete(requete);
		return 0;
	}
	
	public int ajouterCredit(int anumCredit,int numCompte, double mensualite,double asommes,double ataux, int adureeEnMois, double sommesRestante)
	{
		String req = "INSERT INTO credit values ("
			  + ""+anumCredit        +","
				  +numCompte        +","
				  +asommes           +","
				  +ataux             +","
				  +adureeEnMois      +","
				  +mensualite+","   +sommesRestante+")  ;";
		System.out.println(req);
		update(req);
		return 0;
	}        
	
	
// ------------------------------------------------------------------------------------
	
	// Requete SQL pour recupérer les données
	public int persoEnregistre()
	{
		String requete = "SELECT * FROM personne;";
		envoieRequete(requete);
		return 0;
	}

	
	int creditCompte(int id_c)
	{
		String requete = "SELECT credit.* FROM credit, compte WHERE credit.comptenum=compte.num AND credit.comptenum="+id_c+";";
		envoieRequete(requete);
		return 0;
	}
	
	int envoieRequete(String r)
	{
		String url = "jdbc:mysql://localhost/bank";
        String user = "root";
        String passwd = "mysql";
        
	    try 
	    {
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Driver O.K.");
		      Statement state = conn.createStatement();
		      //L'objet ResultSet contient le résultat de la requête SQL
		      int res = state.executeUpdate(r);
		      //ResultSetMetaData data = res.getMetaData();
		      
		      
		      /*System.out.println("\n");
		      //On affiche le nom des colonnes
		      for(int i = 1; i <= data.getColumnCount(); i++)
		        System.out.print("\t\t" + data.getColumnName(i).toUpperCase() + "\t\t*");
		         
		      System.out.println("\n");
		         
	          while(res.next())
	          {         
	              for(int i = 1; i <= data.getColumnCount(); i++)
	              {
	            	  if (res.getObject(i)==null)
	            	  {
	            		  System.out.println("\t\tnull\t\t|");
	            		  continue;
	            	  }
	            	  System.out.print("\t\t" + res.getObject(i).toString() + "\t\t |");
	              }
	                
	                  
	              System.out.println("\n");
	          }*/
		      return 0;
	    }
	    catch (Exception e) 
	   	{
	    	e.printStackTrace();
	    	return -1;
    	} 
	}
	
	int update(String r)
	{
		String url = "jdbc:mysql://localhost/bank";
        String user = "root";
        String passwd = "mysql";
  
    	 try 
    	    {
    		  Class.forName("com.mysql.jdbc.Driver"); 
    		  Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Driver O.K.");
		      Statement state = conn.createStatement();
		      int res = state.executeUpdate(r);
		      return 0; 
	    }
	    catch (Exception e) 
	   	{
	    	e.printStackTrace();
	    	return -1;
    	} 
	}
	
	// API REST
	public List<Personne> personeEnregistre() throws Exception
	{
		String req = "SELECT * FROM personne";
		System.out.println(req + " tentative - >");
		
        List<Personne> arrayP = new ArrayList<>();
  
    	try 
    	    {
    		String url = "jdbc:mysql://localhost/Bank";
            String user = "root";
            String passwd = "mysql";
    		Class.forName("com.mysql.jdbc.Driver"); 
    		Connection con = DriverManager.getConnection(url, user, passwd);
    		Statement state = con.createStatement();
			ResultSet result = ((java.sql.Statement)state).executeQuery(req);
			System.out.println("requete executé avec succès");
		
			while (result.next()) 
			{
				Personne newPP = new Personne();
				
				newPP.setID(result.getInt("id"));
				newPP.setPrenom(result.getString("Prenom"));
				newPP.setNom(result.getString("Nom"));
				newPP.setDateNaissance(result.getString("dateNaissance"));
	
				arrayP.add(newPP); 
			}
		}
		catch (Exception e) {
			System.out.println("Impossible d'executer la requete -> "+ e);
		}		
		return arrayP;
	}
	
	// reutilisation du code  
	// on extrait une seul personne a partir de la base de donnée
	public Personne unePersonne (int id) throws Exception
	{
		String req = "SELECT * FROM personne where id= "+id;
		System.out.println(req + " tentative - >");

        Personne newPP = new Personne();
  
    	try 
    	    {
    		String url = "jdbc:mysql://localhost/Bank";
            String user = "root";
            String passwd = "mysql";
    		Class.forName("com.mysql.jdbc.Driver"); 
    		Connection con = DriverManager.getConnection(url, user, passwd);
    		Statement state = con.createStatement();
			ResultSet result = ((java.sql.Statement)state).executeQuery(req);
			System.out.println("requete executé avec succès");
			
			if(result.next()) 
			{
				newPP.setID(result.getInt("id"));
				newPP.setPrenom(result.getString("Prenom"));
				newPP.setNom(result.getString("Nom"));
				newPP.setDateNaissance(result.getString("dateNaissance"));
				System.out.println(result.getInt("id")+ result.getString("Prenom"));
			}
		}
		catch (Exception e)
    	{
			System.out.println("Impossible d'executer la requete -> "+ e);
		}	
    	// on retourn seulement une personne à la fois
		return newPP;
	}
	
	
	public void newPersonne(Personne Pers) throws Exception
	{
		String req = "insert into personne values (?,?,?,?)";
		System.out.println(req + " tentative - >");
    	try 
    	    {
    		String url = "jdbc:mysql://localhost/Bank";
            String user = "root"; 
            String passwd = "mysql";
    		Class.forName("com.mysql.jdbc.Driver"); 
    		
    		Connection con = DriverManager.getConnection(url, user, passwd);
    		PreparedStatement state = con.prepareStatement(req);
    		
    		state.setInt(1, Pers.getID() );
    		state.setString(2, Pers.getPrenom());
    		state.setString(3, Pers.getNom());
    		state.setString(4, Pers.getDateNaissance());
    		 
    		state.executeUpdate();
			System.out.println("requete executé avec succès");
		}
		catch (Exception e)
    	{
			System.out.println("Impossible d'executer la requete -> "+ e);
		}	
    	// on retourn seulement une personne à la fois

	}
	
	
	// reutilisation du code  
	// on extrait une seul personne a partir de la base de donnée
	
	
	public List<Personne> quelCompte(int id)
	{
		String req = "SELECT compte.* FROM personne, compte WHERE compte.ID=personne.id AND personne.id="+id+";";
		System.out.println(req + " tentative - >");

		List<Personne> arrayCompte = new ArrayList<>();      
  
    	try 
    	    {
    		String url = "jdbc:mysql://localhost/Bank";
            String user = "root";
            String passwd = "mysql";
    		Class.forName("com.mysql.jdbc.Driver"); 
    		Connection con = DriverManager.getConnection(url, user, passwd);
    		Statement state = con.createStatement();
			ResultSet result = ((java.sql.Statement)state).executeQuery(req);
			System.out.println("requete executé avec succès");
			
			if(result.next()) 
			{
				Personne newPP = new Personne();
				newPP.setID(result.getInt("id"));
				newPP.setPrenom(result.getString("Prenom"));
				newPP.setNom(result.getString("Nom"));
				newPP.setDateNaissance(result.getString("dateNaissance"));
				System.out.println(result.getInt("id")+ result.getString("Prenom"));
				
				arrayCompte.add(newPP); 
			}	
		}
		catch (Exception e)
    	{
			System.out.println("Impossible d'executer la requete -> "+ e);
		}	
    	// on retourn seulement une personne à la fois
		return arrayCompte ;
	}
	

	
	
	
	
	
}

