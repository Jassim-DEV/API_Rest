package org.upec.andric.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/banque")
public class MyRessources {
	
	private static final String APPLICATION_XML = null;

	ManagementdB mg = new ManagementdB();

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
      // JDBC driver name and database URL
      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      final String url="jdbc:mysql://localhost/TpClinique";

      //  Database credentials
      final String user = "root";
      final String passwd = "mysql";
      static Connection con = null;

	@Path("/home")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getRandom() {

		String s = "salut andric, affichage de toute tes requete ici";
		return s;
	}

	@Path("personnes")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Personne> getPersonnes()
	{
		//System.out.println("Voici le personne ayant un compte enregistrer dans notre banque");
		try {
			return mg.personeEnregistre();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
   }
	

	// on fait un get par rapport a l'id de la personne
	@Path("personne/{id}")
	@GET   
	@Produces(MediaType.APPLICATION_XML)
    public Personne getPersonne(@PathParam("id") int id) throws Exception
	{
		return mg.unePersonne(id);
	}

	
	// Insertion de nouvelles personnes
	@POST
	@Path("insertpersonne")
	//@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Personne instertPersonne(Personne post1) throws Exception
	{
		System.out.println(post1);
		mg.newPersonne(post1);
	return post1;	
	}
	
	
	@POST
	@Path("addcc")
	public Compte addcc(CompteCheque post2)
	{
		System.out.println(post2);
		mg.ajouterCompteCheque(post2);
		return post2;
	}
	
	@POST
	@Path("addce")
	public Compte addcc(CompteEpargne post3)
	{
		System.out.println(post3);
		mg.ajouterCompteEpargne(post3);
		return post3;
	}
	
	
	@GET
	@Path("1/accounts")
	@Produces(MediaType.APPLICATION_XML)
	public List<Personne> comptesId()
	{
		return mg.quelCompte(1);
	}	
	
}


	





