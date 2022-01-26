package formulaire;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class S_Candidat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("Demmarage Client...");
		
			try {
			System.out.println("Je suis connectè");
			while(true) {			
				Socket s = new Socket("127.0.0.1",9000);

			DataInputStream in1 = new DataInputStream(s.getInputStream()	);
			String recu1 = in1.readUTF();
			System.out.println(recu1);
			
			Scanner sc = new Scanner(System.in);
			String msg = sc.next();
			//envoyer au serveur
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF(msg);
			//recuperer du serveur
			DataInputStream in = new DataInputStream(s.getInputStream()	);
			String recu = in.readUTF();
			System.out.println(recu);}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur adresse IP"+e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur connexion"+e.getMessage());
		
		}
		
	}

}