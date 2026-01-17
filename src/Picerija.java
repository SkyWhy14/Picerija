import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Picerija {
    private static Queue<String> pasutijumi = new LinkedList<>();
    private static ArrayList<String> adreses = new ArrayList<>(); // Saglabā piegādes adreses
    private static ArrayList<String > numurs = new ArrayList<>();
    private static ArrayList<String> gatavie = new ArrayList<>();
    private static ArrayList<String> saglabat = new ArrayList<>();
    public static String VirknesParbaude(String zinojums, String noklusejums) {
        String virkne;
        do {
            virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
            if (virkne == null) return null;
            virkne = virkne.trim();
        } while (virkne.isEmpty());
        return virkne;
    }
    public static void atskanotZvanu() {
        try {
            File f = new File("sound/zvans.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(f);
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Neizdevās atskaņot skaņu!");
        }
    }

    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Picērija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.getContentPane().setBackground(Color.PINK);
        frame.setLayout(null);

        JButton btnAdd = new JButton("Jauns Pasūtījums");
        btnAdd.setBounds(30, 30, 200, 40);
        JButton btnView = new JButton("Skatīt Aktīvos");
        btnView.setBounds(30, 80, 200, 40);
        JButton btnDone = new JButton("Pabeigt nākamo");
        btnDone.setBounds(30, 130, 200, 40);
        JButton btnAdreses = new JButton("Apskatīt adreses");
        btnAdreses.setBounds(30, 180, 200, 40);
        JButton btnNumurs = new JButton("Apskatīt numurus");
        btnNumurs.setBounds(30, 240, 200, 40);
        JButton btngatavie = new JButton("Gatavie pasūtījumi");
        btngatavie.setBounds(250, 30, 200, 40);
        JButton saglabatGatavie = new JButton("Saglabāt gatavos");
        saglabatGatavie.setBounds(250, 80, 200, 40);
        JButton btnExit = new JButton("Iziet");
        btnExit.setBounds(30, 290, 200, 40);

        frame.add(btnAdd);
        frame.add(btnView);
        frame.add(btnDone);
        frame.add(btnAdreses);
        frame.add(btnNumurs);
        frame.add(btngatavie);
        frame.add(saglabatGatavie);
        frame.add(btnExit);
        frame.setVisible(true);

        btnAdd.addActionListener(e -> {
            try {
                String[] picasNosaukums = {"Margarita - 6€", "Veģetāra - 7€", "Gaļas - 8€"};
                String[] urls = {
                        "https://raw.githubusercontent.com/SkyWhy14/Picerija/0cc1e73ecd6767f73bcb06807d67d173faf55a50/bildes/pica.png",
                        "https://raw.githubusercontent.com/SkyWhy14/Picerija/0cc1e73ecd6767f73bcb06807d67d173faf55a50/bildes/vegana.png",
                        "https://raw.githubusercontent.com/SkyWhy14/Picerija/a509ed866c63a19b6b4df6e8b3d129acae70759b/bildes/galas.png"
                };
                ImageIcon[] picasBildes = new ImageIcon[urls.length];
                for (int i = 0; i < urls.length; i++) {
                    try {
                        picasBildes[i] = new ImageIcon(new URL(urls[i]));
                    } catch (Exception ex) {
                        picasBildes[i] = null;
                    }
                }

                int picIzvele = JOptionPane.showOptionDialog(
                        null,
                        "Izvēlies picu:",
                        "Pica",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        picasNosaukums,
                        picasNosaukums[0]
                );
                if (picIzvele < 0) return;

                double cena = (picIzvele == 0 ? 6 : picIzvele == 1 ? 7 : 8);

                if (picasBildes[picIzvele] != null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Izvēlēta pica: " + picasNosaukums[picIzvele],
                            "Pica",
                            JOptionPane.INFORMATION_MESSAGE,
                            picasBildes[picIzvele]
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Izvēlēta pica: " + picasNosaukums[picIzvele],
                            "Pica",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }


                String[] piedevas = {"Nav", "Sēnes +1€", "Paprika +1€", "Olīvas +1€", "Extra siers +2€"};
                int piedIzvele = JOptionPane.showOptionDialog(null, "Izvēlies papildus piedevas:", "Piedevas",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, piedevas, piedevas[0]);
                if (piedIzvele < 0) return;
                if (piedIzvele >= 1 && piedIzvele <= 3) cena += 1;
                if (piedIzvele == 4) cena += 2;

                String[] merci = {"Nav", "Kečups +0.50€", "Čili mērce +0.50€", "Barbekjū mērce +0.50€"};
                int mercIzvele = JOptionPane.showOptionDialog(null, "Izvēlies mērci:", "Mērces",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, merci, merci[0]);
                if (mercIzvele < 0) return;
                if (mercIzvele != 0) cena += 0.5;

                String[] dzerieni = {"Nav", "Cola +2€", "Sula +2€", "Tēja +1€"};
                int dzIzvele = JOptionPane.showOptionDialog(null, "Izvēlies dzērienu:", "Dzērieni",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, dzerieni, dzerieni[0]);
                if (dzIzvele < 0) return;
                String dz = dzerieni[dzIzvele];
                if (dzIzvele == 1 || dzIzvele == 2) cena += 2;
                if (dzIzvele == 3) cena += 1;

                int piegade1 = JOptionPane.showConfirmDialog(null, "Vai izmantot piegādi? (+3€)",
                        "Piegāde", JOptionPane.YES_NO_OPTION);
                String piegade;
                String klientaVards = "";
                String adrese = "";
                String telefons = "";

                if (piegade1 == JOptionPane.YES_OPTION) {
                    cena += 3;
                    piegade = "Piegāde";

                    klientaVards = VirknesParbaude("Ievadi klienta vārdu:", "Jānis Bērziņš");
                    if (klientaVards == null) return;

                    adrese = VirknesParbaude("Ievadi piegādes adresi:", "Brīvības iela 10, Rīga");
                    if (adrese == null) return;

                    telefons = VirknesParbaude("Ievadi telefona numuru:", "+371 24234242");
                    if (telefons == null) return;

                    adreses.add(adrese);
                    numurs.add(telefons);
                } else {
                    piegade = "Uz vietas";
                }

                String pasutijums = "Pica: " + picasNosaukums[picIzvele] +
                        "\nPiedevas: " + piedevas[piedIzvele] +
                        "\nMērce: " + merci[mercIzvele] +
                        "\nDzēriens: " + dz +
                        "\nPasūtījuma veids: " + piegade;
                if (piegade1 == JOptionPane.YES_OPTION) {
                    pasutijums += "\nKlienta vārds: " + klientaVards +
                            "\nAdrese: " + adrese +
                            "\nTelefons: " + telefons;
                }
                pasutijums += String.format("\nKopējā cena: %.2f EUR", cena);

                pasutijumi.add(pasutijums);

                JOptionPane.showMessageDialog(null,
                        "Rindā pievienots!\n" + pasutijums,
                        "Pasūtījums",
                        JOptionPane.INFORMATION_MESSAGE,
                        picasBildes[picIzvele]);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Kļūda ielādējot bildes!");
            }
        });

        btnView.addActionListener(e -> {
            if (pasutijumi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav aktīvu pasūtījumu.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (String p : pasutijumi) sb.append("• ").append(p).append("\n\n");
            JOptionPane.showMessageDialog(null, sb.toString(), "Aktīvie Pasūtījumi",
                    JOptionPane.INFORMATION_MESSAGE);
            
            
        });

        btnDone.addActionListener(e -> {
            if (pasutijumi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav ko pabeigt!");
            } else {
                String done = pasutijumi.poll();
                JOptionPane.showMessageDialog(null, "Pasūtījums izpildīts!\n\n" + done);

                // Atskaņo skaņu
                atskanotZvanu();

                gatavie.add(done);
            }
        });

        btnAdreses.addActionListener(e -> {
            if (adreses.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav saglabātu adreses pasūtījumiem.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < adreses.size(); i++) {
                    sb.append((i + 1)).append(". ").append(adreses.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Piegādes adreses",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnNumurs.addActionListener(e -> {
			if (numurs.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nav saglabātu telefona numurus pasūtījumiem.");
			} else {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < numurs.size(); i++) {
					sb.append((i + 1)).append(". ").append(numurs.get(i)).append("\n");
				}
				JOptionPane.showMessageDialog(null, sb.toString(), "Telefona numuri",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
        btngatavie.addActionListener(e -> {
            if (gatavie.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav gatavo pasūtījumu.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (String p : gatavie) sb.append("• ").append(p).append("\n\n");
            JOptionPane.showMessageDialog(null, sb.toString(), "Gatavie Pasūtījumi",
                    JOptionPane.INFORMATION_MESSAGE);

            int choice = JOptionPane.showConfirmDialog(null, 
                    "Vai vēlaties saglabāt gatavos pasūtījumus?",
                    "Saglabāt", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                saglabat.addAll(gatavie);
                gatavie.clear();
                JOptionPane.showMessageDialog(null, "Gatavie pasūtījumi saglabāti!");
            } else {
                JOptionPane.showMessageDialog(null, "Gatavie pasūtījumi nav saglabāti.");
            }
        });
			
		  	
	
      saglabatGatavie.addActionListener(e -> {
    if (saglabat.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Nav saglabātu gatavo pasūtījumu.");
    	return;
    	  								
   }else{
	   StringBuilder sb = new StringBuilder();
		for (String p : saglabat) sb.append("• ").append(p).append("\n\n");
		JOptionPane.showMessageDialog(null, sb.toString(), "Saglabātie Gatavie Pasūtījumi",
		JOptionPane.INFORMATION_MESSAGE);
		  				
		}
    	});
        btnExit.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Picerija aizvērta! Uz redzēšanos!");
            System.exit(0);
        });
    }
}
