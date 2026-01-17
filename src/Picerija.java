import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Picerija {
    private static Queue<String> pasutijumi = new LinkedList<>();

    // Metode, lai pārbaudītu virkni (ne tukša)
    public static String VirknesParbaude(String zinojums, String noklusejums) {
        String virkne;
        do {
            virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
            if (virkne == null) return null;
            virkne = virkne.trim();
        } while (virkne.isEmpty());
        return virkne;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Picērija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.getContentPane().setBackground(Color.PINK);
        frame.setLayout(null);

        JButton btnAdd = new JButton("Jauns Pasūtījums");
        btnAdd.setBounds(50, 50, 400, 40);

        JButton btnView = new JButton("Skatīt Aktīvos");
        btnView.setBounds(50, 110, 400, 40);

        JButton btnDone = new JButton("Pabeigt nākamo");
        btnDone.setBounds(50, 170, 400, 40);
        JButton btnExit = new JButton("Iziet");
        btnExit.setBounds(50, 230, 400, 40);

        frame.add(btnAdd);
        frame.add(btnView);
        frame.add(btnDone);
        frame.add(btnExit);
        frame.setVisible(true);

        // --- JAUNS PASŪTĪJUMS ---
        btnAdd.addActionListener(e -> {
            // --- Picas un bildes GIF ---
            String[] picasNosaukums = {"Margarita - 6€", "Veģetāra - 7€", "Gaļas - 8€"};
            ImageIcon[] picasBildes = {
                    new ImageIcon(".//bildes//pica.png"),
                    new ImageIcon(".//bildes//vegana.png"),
                    new ImageIcon(".//images//galas.gif")
            };

            // Izvēle ar tekstu pogām
            int picIzvele = JOptionPane.showOptionDialog(
                    null,
                    "Izvēlies picu:",
                    "Pica",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    picasNosaukums,
                    picasNosaukums[0]
            );
            if (picIzvele < 0) return;

            // Cena pēc izvēles
            double cena = (picIzvele == 0 ? 6 : picIzvele == 1 ? 7 : 8);

            // Parāda izvēlēto picu GIF
            JOptionPane.showMessageDialog(null,
                    "Izvēlēta pica: " + picasNosaukums[picIzvele],
                    "Pica",
                    JOptionPane.INFORMATION_MESSAGE,
                    picasBildes[picIzvele]);
            //Papildus piedavas
            String [] piedavas = {"Nav","Paprika +0.5€","Sēnes +0.5€","Extra siers +1€","Olīvas +0.5€"};
            int piedavasIzvele = JOptionPane.showOptionDialog(null,
					"Izvēlies papildus piedevas:",
					"Piedevas",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, piedavas, piedavas[0]);
            			if(piedavasIzvele <0)
            				return;
            			if(piedavasIzvele ==1 || piedavasIzvele ==2 || piedavasIzvele ==4)
            				cena +=0.5;
            			if (piedavasIzvele ==3)
            				cena +=1;
            			

            // --- Dzērieni ---
            String[] drinks = {"Nav", "Cola +2€", "Sula +2€", "Tēja +1€"};
            int dzIzvele = JOptionPane.showOptionDialog(null,
                    "Izvēlies dzērienu:",
                    "Dzērieni",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, drinks, drinks[0]);
            if (dzIzvele < 0) return;

            String dz = drinks[dzIzvele];
            if (dzIzvele == 1 || dzIzvele == 2) cena += 2;
            if (dzIzvele == 3) cena += 1;

            // --- Piegāde ---
            int delivery = JOptionPane.showConfirmDialog(
                    null,
                    "Vai izmantot piegādi? (+3€)",
                    "Piegāde",
                    JOptionPane.YES_NO_OPTION
            );

            String piegade;
            String klientaVards = "";
            String adrese = "";
            String telefons = "";

            if (delivery == JOptionPane.YES_OPTION) {
                cena += 3;
                piegade = "Piegāde";

                klientaVards = VirknesParbaude("Ievadi klienta vārdu:", "Jānis Bērziņš");
                if (klientaVards == null) return;

                adrese = VirknesParbaude("Ievadi piegādes adresi:", "Brīvības iela 10, Rīga");
                if (adrese == null) return;

                telefons = VirknesParbaude("Ievadi telefona numuru:", "+371 24234242");
                if (telefons == null) return;

            } else {
                piegade = "Uz vietas";
            }

            // --- Sagatavo pasūtījuma tekstu ---
            String pasutijums = "Pica: " + picasNosaukums[picIzvele] +
					"\nPapildus piedevas: " + piedavas[piedavasIzvele] +
					"\nDzēriens: " + dz +
					"\nPiegāde: " + piegade +
					(delivery == JOptionPane.YES_OPTION ?
							"\nVārds: " + klientaVards +
									"\nAdrese: " + adrese +
									"\nTelefons: " + telefons : "") +
					"\n\nKopējā cena: " + String.format("%.2f", cena) + " EUR";

            pasutijumi.add(pasutijums);

            JOptionPane.showMessageDialog(null,
                    "Rindā pievienots!\n" + pasutijums,
                    "Pasūtījums",
                    JOptionPane.INFORMATION_MESSAGE,
                    picasBildes[picIzvele]);
        });

        // --- APSKATĪT PASŪTĪJUMUS ---
        btnView.addActionListener(e -> {
            if (pasutijumi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav aktīvu pasūtījumu.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (String p : pasutijumi) {
                sb.append("• ").append(p).append("\n\n");
            }

            JOptionPane.showMessageDialog(null, sb.toString(), "Aktīvie Pasūtījumi",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // --- PABEIGT PASŪTĪJUMU ---
        btnDone.addActionListener(e -> {
            if (pasutijumi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav ko pabeigt!");
            } else {
                String done = pasutijumi.poll();
                JOptionPane.showMessageDialog(null,
                        "Pasūtījums izpildīts!\n\n" + done);
            }
        });
        // --- IZEJA ---
        btnExit.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Picerija aizvērta! Uz redzēšanos!");
			System.exit(0);
		});
    }
}
