package formulaire;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

//declarer un PORT 
public class S_Serveur {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.out.println("Demarrage...");
			int i=0;
			String reponse;
			try {	ServerSocket server = new ServerSocket(9000);

		while (true) {
		
			
				System.out.println("Serveur etat d'ecout");
				Socket s = server.accept();

				DataOutputStream out1 = new DataOutputStream(s.getOutputStream());
				out1.writeUTF("Saisir votre Pseudo : ");
				
				
				System.out.println("Client acceptè");
				//recuperer donnees du client
				DataInputStream recu = new DataInputStream(s.getInputStream());
				String pseudo = recu.readUTF();
				//traitement
				CandidatManager can = new CandidatManager();
				
				if(can.ChercherPseudo(pseudo)==1) reponse=  "Pseudo existe deja ! ";
				else reponse="Pseudo disponible ";
				
				//envoyer les donnees
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				out.writeUTF(reponse);}
				
				}
				
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur port"+e.getMessage());
			}
			
			
	}
	
	

}