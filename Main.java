package formulaire;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.w3c.dom.events.MouseEvent;

public class Main extends JFrame implements ActionListener,KeyListener{
	JLabel lb,lb_pass;
	JPasswordField tx ; 
	JButton btn;
	JPanel pn;
		Main(){
			this.setTitle("Formulaire");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(400, 300);
			this.setLocation(600, 250);;
			
			this.setLayout(new FlowLayout());
			String url = "issat.png";
		      ImageIcon icone = new ImageIcon(url);
		      
		      JLabel img = new JLabel(icone);
		      this.add(img);
			
			lb = new JLabel("                         Bienvenue     ");
			
			lb.setBackground(Color.white);
			lb.setFont(new Font("Arial",Font.BOLD,18));
			lb.setHorizontalAlignment(JLabel.CENTER);
		
			
			
			lb_pass = new JLabel("Entrer mot de passe");
			lb_pass.setBackground(Color.red);
			
			
			
			tx = new JPasswordField(20);
			
			tx.addKeyListener(this);
			
			btn = new JButton("Valider");
			btn.addActionListener(this);
			
			pn = new JPanel(new FlowLayout());
			pn.add(lb_pass);
			pn.add(tx);

			
			this.add(lb);
			this.add(pn);
			this.add(btn);

			
			this.setVisible(true);

			
			
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main m = new Main();
		//FrameFormulaire f = new FrameFormulaire();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			if(tx.getText().equals("issatso")) {

				this.dispose();
				new FrameFormulaire().setVisible(true);
				
			}else {
				JOptionPane.showMessageDialog(this, "Mot de passe inccorect !");	 

			}

		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {		
			if(tx.getText().equals("issatso")) {
				this.dispose();
				new FrameFormulaire();
			}else {
				JOptionPane.showMessageDialog(this, "Mot de passe inccorect !");	 

			}

		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		

	}
}


