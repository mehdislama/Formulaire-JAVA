package formulaire;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PDateHeure extends Thread  {
	JPanel pn;
	JLabel horloge;
	
	public JLabel getHorloge() {
		return horloge;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 horloge = new JLabel();
       
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              horloge.setText(
                 DateFormat.getDateTimeInstance().format(new Date())
              );
            }
         });
        
         t.start();
         
	}


    public static void main(String[] args) {
		new PDateHeure().start();
		
	}

}
