import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Pica {
	public static void main(String[] args) {
	    JFrame frame = new JFrame("Picērija");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400, 300);
	    frame.setLayout(new GridLayout(0, 1)); // auto stack buttons top → bottom

	    // Button texts
	    String[] labels = {
	            "Saglabāt piegādes adresi",
	            "Saglabāt pircēja vārdu",
	            "Saglabāt tālruni",
	            "Aprēķina picas cenu",
	            "Picas piegāde",
	            "Apskatīt pasūtījumu",
	            "Apturēt"
	    };

	    // Make a button for each label
	    for (String s : labels) {
	        JButton btn = new JButton(s);
	        frame.add(btn);

	        btn.addActionListener(e -> {
	            if (s.equals("Saglabāt pircēja vārdu")) {
	                System.out.println("TODO: Save name");
	            } else if (s.equals("Apturēt")) {
	                System.exit(0);
	            } else {
	                System.out.println("Clicked: " + s);
	            }
	        });
	    }

	    frame.setVisible(true);
	}

	  
}
