import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Picerija {
    private static Queue<String> pasutijumi = new LinkedList<>();
    private static ArrayList<String> adreses = new ArrayList<>();
    private static ArrayList<String> numurs = new ArrayList<>();
    private static ArrayList<String> gatavie = new ArrayList<>();
    private static ArrayList<String> saglabat = new ArrayList<>();

    // Teksta ievades drošība
    public static String VirknesParbaude(String zinojums, String noklusejums) {
        String virkne;
        do {
            virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
            if (virkne == null) return null;
            virkne = virkne.trim();
        } while (virkne.isEmpty());
        return virkne;
    }

    // Skaņas atskaņošana no jar resursiem
    public static void atskanotZvanu() {
        try {
            URL zvansURL = Picerija.class.getResource("/sound/zvans.wav");
            if (zvansURL == null) {
                JOptionPane.showMessageDialog(null, "Zvanu fails nav atrasts jar!");
                return;
            }

            AudioInputStream ais = AudioSystem.getAudioInputStream(zvansURL);
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Neizdevās atskaņot skaņu!");
            e.printStackTrace();
        }
    }
    public static void atskanotMainMenuSound() {
        try {
            URL zvansURL = Picerija.class.getResource("/sound/MainMenu.wav");
            if (zvansURL == null) {
                JOptionPane.showMessageDialog(null, "Zvanu fails nav atrasts jar!");
                return;
            }

            AudioInputStream ais = AudioSystem.getAudioInputStream(zvansURL);
            Clip c = AudioSystem.getClip();
            c.open(ais);
            c.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Neizdevās atskaņot skaņu!");
            e.printStackTrace();
        }
    }
    private static String getField(String text, String key) {
        int start = text.indexOf(key);
        if (start < 0) return "";
        start += key.length();
        int end = text.indexOf("\n", start);
        if (end < 0) end = text.length();
        return text.substring(start, end).trim();
    }
    static DefaultTableModel tableModel;
    static JTable table;
    public static void raditTabulu(String nosaukums, ArrayList<String> dati) {
    	 JFrame frame = new JFrame(pasutijumi.isEmpty() ? "Nav aktīvo pasūtījumu" : nosaukums);
    	    frame.setSize(900, 400);

    	    String[] columns = {"Klients", "Adrese", "Tel", "Pica", "Mērce", "Dzēriens", "Tilpums", "Uzkodas","Piedevas", "Cena (EUR)"};
    	    tableModel = new DefaultTableModel(columns, 0);
    	    table = new JTable(tableModel);

    	    table.setRowHeight(25);
    	    table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    	    table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
    	    table.getTableHeader().setBackground(new Color(230, 240, 230));

    	    for (String p : dati) {
    	        String vards = getField(p, "Klienta vārds:");
    	        String adrese = getField(p, "Adrese:");
    	        String tel = getField(p, "Telefons:");
    	        String pica = getField(p, "Pica:");
    	        String merce = getField(p, "Mērce:");
    	        String dzeriens = getField(p, "Dzēriens:");
    	        String tilpums = getField(p, "Tilpums:");
    	        String uzkoda = getField(p, "Uzkoda:");
    	        String piedevas = getField(p, "Piedevas:");
    	        String cena = getField(p, "Kopējā cena:");

    	    
    	        tableModel.addRow(new Object[]{vards, adrese, tel, pica, merce, dzeriens, tilpums,uzkoda, piedevas, cena});
    	    }

    	    JScrollPane pane = new JScrollPane(table);
    	    frame.add(pane);
    	    frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Picērija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.getContentPane().setBackground(Color.PINK);
        frame.setLayout(null);
        
        // Pogas
        JButton btnAdd = new JButton("Jauns Pasūtījums"); btnAdd.setBounds(30, 30, 200, 40);
        JButton btnView = new JButton("Skatīt Aktīvos"); btnView.setBounds(30, 80, 200, 40);
        JButton btnDone = new JButton("Pabeigt nākamo"); btnDone.setBounds(30, 130, 200, 40);
        JButton btnAdreses = new JButton("Apskatīt adreses"); btnAdreses.setBounds(30, 180, 200, 40);
        JButton btnNumurs = new JButton("Apskatīt numurus"); btnNumurs.setBounds(30, 240, 200, 40);
        JButton btnRemake = new JButton("Rediget pasūtījumu"); btnRemake.setBounds(250, 130, 200, 40);
        JButton btngatavie = new JButton("Gatavie pasūtījumi"); btngatavie.setBounds(250, 30, 200, 40);
        JButton saglabatGatavie = new JButton("Apskatit saglabatos gatavos"); saglabatGatavie.setBounds(250, 80, 200, 40);
        JButton btnExit = new JButton("Iziet"); btnExit.setBounds(30, 290, 200, 40);

        frame.add(btnAdd); frame.add(btnView); frame.add(btnDone);
        frame.add(btnAdreses); frame.add(btnNumurs);
        frame.add(btnRemake);
        frame.add(btngatavie); frame.add(saglabatGatavie); frame.add(btnExit);
        frame.setVisible(true);

        // Jauns pasūtījums
        atskanotMainMenuSound();
        btnAdd.addActionListener(e -> {
            try {
                String[] picasNosaukums = {"Margarita - 6€", "Veģetāra - 7€", "Gaļas - 8€"};
                String[] urls = {"/bildes/pica.png", "/bildes/vegana.png", "/bildes/galas.png"};
                ImageIcon[] picasBildes = new ImageIcon[urls.length];
                for (int i = 0; i < urls.length; i++) {
                    URL u = Picerija.class.getResource(urls[i]);
                    if (u != null) picasBildes[i] = new ImageIcon(u);
                    else picasBildes[i] = null;
                }

                int picIzvele = JOptionPane.showOptionDialog(
                        null,"Izvēlies picu:","Pica",JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,null,picasNosaukums,picasNosaukums[0]);
                if (picIzvele < 0) return;
                double cena = (picIzvele==0?6:picIzvele==1?7:8);

                if (picasBildes[picIzvele]!=null) {
                    JOptionPane.showMessageDialog(null,"Izvēlēta pica: "+picasNosaukums[picIzvele],"Pica",
                            JOptionPane.INFORMATION_MESSAGE,picasBildes[picIzvele]);
                } else {
                    JOptionPane.showMessageDialog(null,"Izvēlēta pica: "+picasNosaukums[picIzvele]);
                }

                String[] piedevas = {"Nav","Sēnes +1€","Paprika +1€","Olīvas +1€","Extra siers +2€"};
                int piedIzvele = JOptionPane.showOptionDialog(null,"Izvēlies papildus piedevas:","Piedevas",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,piedevas,piedevas[0]);
                if (piedIzvele>=1 && piedIzvele<=3)cena+=1;
                if (piedIzvele==4)cena+=2;

                String[] merci = {"Nav","Kečups +0.50€","Čili mērce +0.50€","Barbekjū mērce +0.50€"};
                int mercIzvele = JOptionPane.showOptionDialog(null,"Izvēlies mērci:","Mērces",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,merci,merci[0]);
                if (mercIzvele>0)cena+=0.5;

                String[] drinks = {"Nav","Cola +2€","Sula +2€","Tēja +1€"};
                int dzIzvele = JOptionPane.showOptionDialog(null,"Izvēlies dzērienu:","Dzērieni",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,drinks,drinks[0]);
                String dz = drinks[dzIzvele];
                String tilpums = "Nav"; // noklusējums
                double dzCena = 0;

                if(dzIzvele==1||dzIzvele==2) dzCena+=2;
                if(dzIzvele==3) dzCena+=1;

                if(dzIzvele != 0){
                    String[] ml = {"Nav","0.250L + 0.25€","330L + 0.50€","500L + 0.75€"};
                    int mlIzvele = JOptionPane.showOptionDialog(null,"Izvēlies tilpumu:","Tilpums",
                            JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ml,ml[0]);
                    tilpums = ml[mlIzvele]; // saglabājam atsevišķi
                    if(mlIzvele==1) dzCena+=0.25;
                    if(mlIzvele==2) dzCena+=0.50;
                    if(mlIzvele==3) dzCena+=0.75;
                }
                cena += dzCena;
                
             // Uzkodas
                String[] uzkoda = {"Nav","Čipsi +1€","Kartupeļu frī +1.50€","Grauzdiņi +2€"};
                int uzkIzvele = JOptionPane.showOptionDialog(null,"Izvēlies uzkodu:","Uzkodas",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,uzkoda,uzkoda[0]);
                String uzk = uzkoda[uzkIzvele];
                if(uzkIzvele==1) cena+=1;
                if(uzkIzvele==2) cena+=1.5;
                if(uzkIzvele==3) cena+=2;
                				
                
               

                int piegade1 = JOptionPane.showConfirmDialog(null,"Vai izmantot piegādi? (+3€)","Piegāde",JOptionPane.YES_NO_OPTION);
                String piegade; String klientaVards=""; String adrese=""; String telefons="";

                if (piegade1==JOptionPane.YES_OPTION){
                    cena+=3; piegade="Piegāde";
                    klientaVards=VirknesParbaude("Ievadi klienta vārdu:","Jānis Bērziņš"); if(klientaVards==null) return;
                    adrese=VirknesParbaude("Ievadi piegādes adresi:","Brīvības iela 10, Rīga"); if(adrese==null) return;
                    telefons=VirknesParbaude("Ievadi telefona numuru:","+371 24234242"); if(telefons==null) return;
                    adreses.add(adrese); numurs.add(telefons);
                } else piegade="Uz vietas";

                String pasutijums = "Pica: "+picasNosaukums[picIzvele]+
                        "\nPiedevas: "+piedevas[piedIzvele]+
                        "\nMērce: "+merci[mercIzvele]+
                        "\nDzēriens: "+dz+
                        "\nTilpums: "+tilpums+
                        "\nUzkoda: "+uzk+
                        "\nPasūtījuma veids: "+piegade;
                if(piegade1==JOptionPane.YES_OPTION)
                    pasutijums += "\nKlienta vārds: "+klientaVards+"\nAdrese: "+adrese+"\nTelefons: "+telefons;

                pasutijums += String.format("\nKopējā cena: %.2f EUR", cena);

                pasutijumi.add(pasutijums);

                JOptionPane.showMessageDialog(null,"Rindā pievienots!\n"+pasutijums,
                        "Pasūtījums",JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Kļūda pasūtījuma procesā!");
                ex.printStackTrace();
            }
        });

        // Skatīt aktīvos pasūtījumus
        btnView.addActionListener(e -> {
            if (pasutijumi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav aktīvu pasūtījumu.");
                return;
            }
            raditTabulu("Aktīvie pasūtījumi", new ArrayList<>(pasutijumi));
        });

        // Pabeigt nākamo
        btnDone.addActionListener(e -> {
            if(pasutijumi.isEmpty()){JOptionPane.showMessageDialog(null,"Nav ko pabeigt!"); return;}
            String done=pasutijumi.poll();
            JOptionPane.showMessageDialog(null,"Pasūtījums izpildīts!\n\n"+done);
            atskanotZvanu();
            gatavie.add(done);
        });

        // Adreses
        btnAdreses.addActionListener(e -> {
            if(adreses.isEmpty()){JOptionPane.showMessageDialog(null,"Nav saglabātu adreses pasūtījumiem."); return;}
            StringBuilder sb=new StringBuilder(); for(int i=0;i<adreses.size();i++) sb.append((i+1)+". "+adreses.get(i)+"\n");
            JOptionPane.showMessageDialog(null,sb.toString(),"Piegādes adreses",JOptionPane.INFORMATION_MESSAGE);
        });

        // Telefona numuri
        btnNumurs.addActionListener(e -> {
            if(numurs.isEmpty()){JOptionPane.showMessageDialog(null,"Nav saglabātu telefona numurus pasūtījumiem."); return;}
            StringBuilder sb=new StringBuilder(); for(int i=0;i<numurs.size();i++) sb.append((i+1)+". "+numurs.get(i)+"\n");
            JOptionPane.showMessageDialog(null,sb.toString(),"Telefona numuri",JOptionPane.INFORMATION_MESSAGE);
        });

        // Gatavie pasūtījumi
        btngatavie.addActionListener(e -> {
            if (gatavie.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav gatavo pasūtījumu.");
                return;
            }
            raditTabulu("Gatavie pasūtījumi", gatavie);

            int cho = JOptionPane.showConfirmDialog(null,
                    "Vai saglabāt gatavos?",
                    "Saglabāt",
                    JOptionPane.YES_NO_OPTION);

            if (cho == JOptionPane.YES_OPTION) {
                saglabat.addAll(gatavie);
                gatavie.clear();
                JOptionPane.showMessageDialog(null, "Saglabāts!");
            }
        });
        // Rediģēt pasūtījumu
        btnRemake.addActionListener(e -> {
			if (pasutijumi.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nav aktīvo pasūtījumu ko rediģēt.");
				return;
			}
			String[] pasutijumuMasivs = pasutijumi.toArray(new String[0]);
			int izvele = JOptionPane.showOptionDialog(
					null,
					"Izvēlies pasūtījumu ko rediģēt:",
					"Rediģēt Pasūtījumu",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					pasutijumuMasivs,
					pasutijumuMasivs[0]);

			if (izvele < 0) return;
			btnAdd.doClick(); 
			String jaunaisPasutijums = pasutijumi.poll(); 
			pasutijumi.add(jaunaisPasutijums); 
			JOptionPane.showMessageDialog(null, "Pasūtījums rediģēts!");
		
		});

        // Saglabātie gatavie pasūtījumi
        saglabatGatavie.addActionListener(e -> {
            if (saglabat.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav saglabātu gatavo pasūtījumu.");
                return;
            }
            raditTabulu("Saglabātie pasūtījumi", saglabat);
        });

        // Iziet
        btnExit.addActionListener(e -> {JOptionPane.showMessageDialog(null,"Picerija aizvērta! Uz redzēšanos!         :SYBAU"); System.exit(0);});
    }
}
