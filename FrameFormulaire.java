package formulaire;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.w3c.dom.events.MouseEvent;


public class FrameFormulaire extends JFrame implements ActionListener,MouseListener,FocusListener{
	int i,j = 0;
	JLabel lb_nom,lb_prenom,lb_pseudo,lb_help,lb_bienv,lb_total;
	JTextField tx_nom,tx_prenom,tx_pseudo;
	JButton btn_valider;
	JPanel pn_saisie,pn_help,pn_formulaire,pn_helper,PanelF,pn_center;
	JSplitPane sp;
	JScrollPane sp1,sp2;
	JList liste;
	JTabbedPane tab;
	DefaultListModel model;
	JPopupMenu menu;
	JMenuItem itm_supp,itm_supp_t,itm_renommer;
	String pseudo_ac;
	JScrollPane sc_diff,sc_option;
	JLabel lb_choix_cat;
	JComboBox<String> comb_diff;
	JCheckBox c1,c2,c3,c4,c5,c6,o1,o2,o3,o4;
	JPanel pn1,pn2,pn11,pn111,pnn1,pnn2,pnnn1,pnnn2,pn_btn,pntime_saisie;
	JButton btn_valider_form;
	GetInfo[] Person = new GetInfo[200];
	GetInfo p;
	CandidatManager c;
	PDateHeure DateHeure;
	
	FrameFormulaire(){
				DateHeure = new PDateHeure();
				DateHeure.start();
		this.setTitle("Formulaire");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setLocation(240, 2);;


		lb_nom = new JLabel("Nom");
		tx_nom = new JTextField(15);
		tx_nom.setText("Tappez votre nom");
		tx_nom.addMouseListener(this);
		tx_nom.addFocusListener(this);
		

		lb_prenom = new JLabel("Prenom");
		tx_prenom = new JTextField(15);
		tx_prenom.setText("Tappez votre prenom");
		tx_prenom.addMouseListener(this);
		tx_prenom.addFocusListener(this);



		lb_pseudo = new JLabel("Pseudo");
		tx_pseudo = new JTextField(15);
		tx_pseudo.setText("Tappez votre pseudo");
		tx_pseudo.addFocusListener(this);
		tx_pseudo.addMouseListener(this);

		btn_valider = new JButton("Valider");
		btn_valider.addActionListener(this);
		btn_valider.addMouseListener(this);

		lb_help = new JLabel("Help :");
		pn_help = new JPanel(new BorderLayout());
		pn_help.setBackground(Color.gray);

		pn_help.add(lb_help,BorderLayout.WEST);


		pn_saisie = new JPanel();
		pn_saisie.setBackground(Color.gray);

		pntime_saisie = new JPanel();
		pn_saisie.add(lb_nom);pn_saisie.add(tx_nom);
		pn_saisie.add(lb_prenom);pn_saisie.add(tx_prenom);
		pn_saisie.add(lb_pseudo);pn_saisie.add(tx_pseudo);
		pn_saisie.add(btn_valider);
		pntime_saisie.add(pn_saisie);pntime_saisie.add(DateHeure.getHorloge(),BorderLayout.WEST);
		

		this.add(pntime_saisie,BorderLayout.NORTH);
		this.add(pn_help,BorderLayout.SOUTH);
		
		
		model = new DefaultListModel<>();	
		liste = new JList<>(model);
		liste.addMouseListener(this);

		tab = new JTabbedPane();

		sp = new JSplitPane();

		sp1 = new JScrollPane(liste);
		
		sp1.setPreferredSize(new Dimension(150,0));
		sp2 = new JScrollPane(tab);
		sp.setRightComponent(sp2);//partie droite JTabetPane
		sp.setLeftComponent(sp1);//partie a gauche JListe (pseudo )
		
		 c = new CandidatManager();
		
		lb_total = new JLabel("Le nombre total des inscriptions : "+c.getNbCandidat()) ;
		pn_center = new JPanel(new   BorderLayout());
		pn_center.add(sp,BorderLayout.CENTER);
		pn_center.add(lb_total,BorderLayout.SOUTH);

		
		this.add(pn_center,BorderLayout.CENTER);

		menu = new JPopupMenu();
		itm_supp = new JMenuItem("Supprimer");
		itm_supp.addActionListener(this);
		itm_supp_t = new JMenuItem("Supprimer tous");
		itm_supp_t.addActionListener(this);
		itm_renommer = new JMenuItem("Renommer");
		itm_renommer.addActionListener(this);

		menu.add(itm_supp);
		menu.add(itm_supp_t);
		menu.add(itm_renommer);
		
								
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==btn_valider) {
			
			if((!tx_pseudo.getText().equals("Tappez votre pseudo")&&!tx_pseudo.getText().equals(""))&&
					(!tx_nom.getText().equals("Tappez votre nom")&&!tx_nom.getText().equals(""))&&
					(!tx_prenom.getText().equals("Tappez votre prenom")&&!tx_prenom.getText().equals(""))) {
			
				if( c.InsererCandidat(tx_nom.getText(), tx_prenom.getText(),tx_pseudo.getText())>0) {
				Person[j]= new GetInfo();
				
				Person[j].setNom(tx_nom.getText());
				Person[j].setPrenom(tx_prenom.getText());
				Person[j].setPseudo(tx_pseudo.getText());
					
				
				model.addElement(Person[j].getPseudo());
				j++;
				
				lb_total.setText("Le nombre total des inscriptions : "+c.getNbCandidat()) ;
			    }else 				JOptionPane.showMessageDialog(this, "changer pseudo !");

				
				
				
			}else if ((tx_nom.getText().equals("Tappez votre nom")||tx_nom.getText().equals(""))) {
				JOptionPane.showMessageDialog(this, "Entrer ton Nom");
			}else if ((tx_prenom.getText().equals("Tappez votre prenom")||tx_prenom.getText().equals(""))) {
				JOptionPane.showMessageDialog(this, "Entrer ton Prenom");
			}
			else if ((tx_pseudo.getText().equals("Tappez votre pseudo")||tx_pseudo.getText().equals(""))) {
				JOptionPane.showMessageDialog(this, "Entrer ton pseudo");
			}
		}
		if(e.getSource()==itm_supp_t) {
			model.removeAllElements();
			tab.removeAll();
			c.DeleteAll();
			lb_total.setText("Le nombre total des inscriptions : "+c.getNbCandidat()) ;


		}
		
		if(liste.getSelectedIndex()>-1) {
			System.out.println(liste.getSelectedIndex());

		if(e.getSource()==itm_supp) {
			c.DeletePerson((String) liste.getSelectedValue());
			if(tab.getTabCount()>0) tab.remove(liste.getSelectedIndex());
			model.remove(liste.getSelectedIndex());	
			lb_total.setText("Le nombre total des inscriptions : "+c.getNbCandidat()) ;


			
		}if(e.getSource()==itm_renommer) {
			String pseudoN =JOptionPane.showInputDialog(pn_formulaire,"Nouveau Pseudo",liste.getSelectedValue());
			model.set(liste.getSelectedIndex(),pseudoN);
			Person[liste.getSelectedIndex()].setPseudo(pseudoN);
			tab.setTitleAt(liste.getSelectedIndex(), pseudoN);
			c.modifierPseudo(pseudoN,Person[liste.getSelectedIndex()].getNom());

		}

	}}

	@Override
	public void mousePressed (java.awt.event.MouseEvent e) {
		
			
		if( e.getSource()==liste && e.getClickCount()==2) {
				
				
				if(tab.indexOfTab((String) liste.getSelectedValue())>=0&&  tab.indexOfTab((String) liste.getSelectedValue())==liste.getSelectedIndex()) 
					tab.setSelectedIndex(tab.indexOfTab((String) liste.getSelectedValue()));
				
				else tab.addTab(Person[liste.getSelectedIndex()].getPseudo(),Person[liste.getSelectedIndex()].getPn());

					
			}
		if (e.getButton() == 3&& e.getSource()==liste) {//Click doit
			menu.show(liste,e.getX(),e.getY());
			}
		}
	

	@Override
	public void  mouseClicked (java.awt.event.MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		pn_helper = new JPanel();
		if(e.getSource()==tx_nom) {
			lb_help.setText("Help : Veuillez ecrire votre nom");
		}
		if(e.getSource()==tx_prenom) {
			lb_help.setText("Help : Veuillez ecrire votre prenom");
		}
		if(e.getSource()==tx_pseudo) {
			lb_help.setText("Help : Veuillez ecrire votre pseudo-name");
		}
		if(e.getSource()==btn_valider) {
			lb_help.setText("Help : Valider vos informations");
		}
		if(!liste.isSelectionEmpty()) {
			
				liste.setToolTipText(Person[liste.getSelectedIndex()].getNom()+" "+Person[liste.getSelectedIndex()].getPrenom());
			
		
		}
		
	}

	

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		lb_help.setText("Help :");
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==tx_pseudo&& tx_pseudo.getText().equals("Tappez votre pseudo"))  {
			tx_pseudo.setText(null);
		}
		if(e.getSource()==tx_prenom&& tx_prenom.getText().equals("Tappez votre prenom")) {
			tx_prenom.setText(null);
		}
		if(e.getSource()==tx_nom&& tx_nom.getText().equals("Tappez votre nom")) {
			tx_nom.setText(null);

		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tx_nom && tx_nom.getText().equals("")) {
			tx_nom.setText("Tappez votre nom");
		}
		if(e.getSource()==tx_prenom && tx_prenom.getText().equals("")) {
			tx_prenom.setText("Tappez votre prenom");
		}
		if(e.getSource()==tx_pseudo && tx_pseudo.getText().equals("")) {
			tx_pseudo.setText("Tappez votre pseudo");

		}
		
	
	}



}

class GetInfo implements ActionListener {
	int i=0;
	JLabel lb_bienv;
	JPanel PanelFinal,pn_formulaire;
	JScrollPane sp1,sp2;
	JScrollPane sc_diff,sc_option;
	JLabel lb_choix_cat;
	JComboBox<String> comb_diff;
	JCheckBox c1,c2,c3,c4,c5,c6,o1,o2,o3,o4;
	JPanel pn1,pn2,pn11,pn111,pnn1,pnn2,pnnn1,pnnn2,pn_btn;
	JButton btn_valider_form;
	String nom,pseudo,prenom;
	String cat1="" ,cat2="",cat3="",cat4="",cat5="",cat6="";
	JPanel pn;
	 PDateHeure DateHeure;
		
		GetInfo(){
			DateHeure = new PDateHeure();
			DateHeure.start();
		}
	
	public JPanel getPn() {
		PanelFinal= new JPanel(new GridLayout(3,0));
		pn1 = new JPanel(new GridLayout(2,0));
		pn11 = new JPanel(new FlowLayout());
		pn111 = new JPanel(new FlowLayout());

		pnn1 = new JPanel(new FlowLayout());
		pnn2 = new JPanel(new FlowLayout());
		
		pnnn1 = new JPanel(new FlowLayout());
		pnnn2 = new JPanel(new FlowLayout());

	
	String[] niveau= {"Facile","Intermediaire","Difficile"};
	comb_diff = new JComboBox<>(niveau);
	comb_diff.addActionListener(new ActionListener() {
							// classe interne anonyme
		//******************************************************************************
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==comb_diff) {
				if(comb_diff.getSelectedItem()=="Facile") {
					c1.setEnabled(true);c2.setEnabled(true);
					
					c3.setEnabled(false);c4.setEnabled(false);
					
					c5.setEnabled(false);c6.setEnabled(false);
				}
				if(comb_diff.getSelectedItem()=="Intermediaire") {
					c1.setEnabled(false);c2.setEnabled(false);
					c1.setSelected(true);c2.setSelected(true);
					c3.setEnabled(true);c4.setEnabled(true);
					c5.setEnabled(false);c6.setEnabled(false);
				}
				if(comb_diff.getSelectedItem()=="Difficile") {
					c1.setEnabled(false);c2.setEnabled(false);
					c1.setSelected(true);c2.setSelected(true);
					
					c3.setEnabled(false);c4.setEnabled(false);
					c3.setSelected(true);c4.setSelected(true);
					
					c5.setEnabled(true);c6.setEnabled(true);
				}
			}
			
		}
		//******************************************************************
	});
	
	lb_choix_cat = new JLabel("Choisir le/les categories");
	
	pn111.add(comb_diff);
	pn1.add(pn111);
	
	pn11.add(lb_choix_cat);
	
		c1 = new JCheckBox("1");
		pn11.add(c1);
		c2 = new JCheckBox("2");
		pn11.add(c2);
		c3 = new JCheckBox("3");c3.setEnabled(false);
		pn11.add(c3);
		c4 = new JCheckBox("4");c4.setEnabled(false);
		pn11.add(c4);
		c5= new JCheckBox("5");c5.setEnabled(false);
		pn11.add(c5);
		c6 = new JCheckBox("6");c6.setEnabled(false);
		pn11.add(c6);
		pn1.add(pn11);
		sc_diff = new JScrollPane(pn1);
		sc_diff.setBorder(BorderFactory.createTitledBorder("Diffecutè"));
		sc_diff.setPreferredSize(new Dimension(500,100));
		pnn1.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		pnn1.add(sc_diff);
		pnnn1.add(pnn1);
		PanelFinal.add(pnnn1);
		
		pn2 = new JPanel(new FlowLayout());
		o1 = new JCheckBox("Emettre son");
		o1.addActionListener(this);
		pn2.add(o1);
		o2 = new JCheckBox("Afficher score");
		o2.addActionListener(this);
		pn2.add(o2);
		o3 = new JCheckBox("Plein ecran");
		o3.addActionListener(this);
		pn2.add(o3);
		o4 = new JCheckBox("Ombre");
		o4.addActionListener(this);
		pn2.add(o4);
		

		
		sc_option = new JScrollPane(pn2);
		sc_option.setPreferredSize(new Dimension(400,90));
		sc_option.setBorder(BorderFactory.createTitledBorder("Option : "+i ));
		pnnn2.setPreferredSize(new Dimension(350,100));
		pnn2.add(sc_option);
		pnnn2.add(pnn2);
		pnn2.setBorder(BorderFactory.createLineBorder(Color.red, 1));	
		PanelFinal.add(pnnn2);
		
		
		btn_valider_form = new JButton("Valider"); 
		btn_valider_form.addActionListener(this);
		pn_btn = new JPanel(new FlowLayout());
		pn_btn.add(btn_valider_form);
		PanelFinal.add(pn_btn);

			pn_formulaire = new JPanel(new BorderLayout());
			pn_formulaire.setPreferredSize(new Dimension(450,400));

			lb_bienv = new JLabel("Bienvenue "+prenom+" "+nom);
			 lb_bienv.setOpaque(true);
			lb_bienv.setPreferredSize(new Dimension(200,100));
			lb_bienv.setBackground(Color.green);
			lb_bienv.setFont(new Font("Arial",Font.BOLD,25));
			lb_bienv.setHorizontalAlignment(JLabel.CENTER);
			pn_formulaire.add(lb_bienv,BorderLayout.NORTH);
			pn_formulaire.add(PanelFinal,BorderLayout.CENTER);

		return pn_formulaire;
	}
	public void CreeHtml() {
		String content = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<h1>Formulaire    <div align=\"right\">" +DateHeure.getHorloge().getText()+"</div> </h1>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ " <fieldset>\r\n"
				+ " <legend> Information personnelle</legend>\r\n"
				+ "  Nom: "+nom+"   <br>\r\n"
				+ "  Prenom:"+prenom+"<br>\r\n"
				+ "  Pseudo:"+pseudo+"<br>\r\n"
				+ " </fieldset>\r\n"
				+ "\r\n"
				+ "<fieldset>\r\n"
				+ "<legend> Configuration</legend>\r\n"
				+ " <fieldset>\r\n"
				+ " <legend> Difficultè : "+comb_diff.getSelectedItem()+" </legend>\r\n"+
				cat1+cat2+cat3+cat4+cat5+cat6+
				"\r\n"
				+ " </fieldset>\r\n"
				+ "  <fieldset>\r\n"
				+ " <legend> Options : "+i+"</legend>\r\n"
				+ "  Son:"+ o1.isSelected()+"  <br>\r\n"
				+ "  Affichage score: "+o2.isSelected()+"<br>\r\n"
				+ "  Plein ecran:  "+o3.isSelected()+"<br>\r\n"
				+ "  Ombre : "+o4.isSelected()+"<br>\r\n"
				+ " </fieldset>\r\n"
				+ "</fieldset>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>";

		   File file = new File(nom+" "+prenom+".htm");
	        Desktop d = Desktop.getDesktop();

		   
		   try {
			   // créer le fichier s'il n'existe pas
		   if (!file.exists()) {
		    file.createNewFile();
		    d.open(file);
		   }

		   FileWriter fw = new FileWriter(file.getAbsoluteFile());
		   BufferedWriter bw = new BufferedWriter(fw);
		   bw.write(content);
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("erreur File"+e1.getMessage());
		}

	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn_valider_form) {
			
			if(c1.isSelected()) {
				cat1=" categorie 1<br>";
			}if(c2.isSelected()) {
				cat2=" categorie 2<br>";
			}if(c3.isSelected()) {
				cat3=" categorie 3<br>";
			}if(c4.isSelected()) {
				cat4=" categorie 4<br>";
			}if(c5.isSelected()) {
				cat5=" categorie 5<br>";
			}if(c6.isSelected()) {
				cat6=" categorie 6<br>";
			}
	        

			this.CreeHtml();
			JOptionPane.showMessageDialog(pn_formulaire, "Enregistrer avec Succees");  
		}
		if(e.getSource()==o1) {
			
			if(o1.isSelected()) {
				i++;
				
			}else if(!o1.isSelected()) {
				i--;}
			}if(e.getSource()==o2) {
			if(o2.isSelected()) {
				i++;
				
			}else if(!o2.isSelected()) {
				i--;}
			}if(e.getSource()==o3) {
			if(o3.isSelected()) {
				i++;
				
			}else if(!o3.isSelected()) {
				i--;}
			}if(e.getSource()==o4) {
			if(o4.isSelected()) {
				i++;
				
			}else if(!o4.isSelected()) {
				i--;}
			}
			sc_option.setBorder(BorderFactory.createTitledBorder("Option : " +i));
		}
		
	}
	  



