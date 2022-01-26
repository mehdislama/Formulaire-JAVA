package formulaire;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CandidatManager {
	
	Connection con=null;
	Statement st=null;
	
	CandidatManager()
	{
		/** ETape 1: chargement driver */

		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver chargé...");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur chargement driver "+ e.getMessage());
		}


		/** Etape 2: Connection à la base */ 

		String url="jdbc:mysql://127.0.0.1/candidat";
		String user="root";
		String mp="";



		try {

			con=DriverManager.getConnection(url,user,mp);
			System.out.println("connexion etablie...");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur de connexion "+e.getMessage());
		}

		/** Etape : Execution des requetes */

		if(con!=null){

			try {

				st=con.createStatement();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	int InsererCandidat(String nom,String prenom,String pseudo)
	{
		int a = 0;

		if(st!=null)
		{
			try {

				a = st.executeUpdate
						("insert into candidat (nom,prenom,pseudo) values ('"+nom+"','"+prenom+"','"+pseudo+"')");

				System.out.println("insertion avec success");


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("erreur ajout "+e.getMessage());

			}
		}

		return a;
	}
	

	int getNbCandidat(){
		// selection
			int result =0;
		try {

			ResultSet rs =st.executeQuery("SELECT COUNT(*) as c  FROM candidat");
			if(rs.next())
			return rs.getInt("c");
		return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erruer de selection "+e.getMessage());
			return 0;
		}
	}
	
	public int modifierPseudo( String pseudo , String id) {
		// TODO Auto-generated method stub
		int a=-1;
		
		String req = "UPDATE candidat set pseudo = '"+pseudo+"' where nom = '"+id+"'";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(req);	
			a=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	
	}
	public void DeleteAll() {
		
		String req = "DELETE FROM candidat";
		java.sql.PreparedStatement pr = null;
		try {
			pr =  con.prepareStatement(req);
			pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DeletePerson(String pseu) {
		
		String req = "DELETE FROM candidat WHERE pseudo = '"+pseu+"'";
		java.sql.PreparedStatement pr = null;
		try {
			pr =  con.prepareStatement(req);
			pr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int ChercherPseudo(String pseu) {
		// selection
			int result =0;
		try {

			ResultSet rs =st.executeQuery("SELECT COUNT(*) as c  FROM candidat WHERE pseudo = '"+pseu+"'");
			if(rs.next())
			return rs.getInt("c");
		return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erruer de selection "+e.getMessage());
			return 0;
		}
	}
	
	
}
