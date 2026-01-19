import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Picerija {
    private static Queue<Pica> pasutijumi = new LinkedList<>();
    private static ArrayList<Pica> gatavie = new ArrayList<>();
    private static ArrayList<Pica> saglabat = new ArrayList<>();
    private static ArrayList<String> adreses = new ArrayList<>();
    private static ArrayList<String> numurs = new ArrayList<>();

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
            URL zvansURL = Picerija.class.getResource("/sound/zvans.wav");
            if (zvansURL == null) return;
            AudioInputStream ais = AudioSystem.getAudioInputStream(zvansURL);
            Clip c = AudioSystem.getClip(); c.open(ais); c.start();
        } catch (Exception ignored) {}
    }
    public static void atskanotMainMenuSound() {
        try {
            URL zvansURL = Picerija.class.getResource("/sound/MainMenu.wav");
            if (zvansURL == null) return;
            AudioInputStream ais = AudioSystem.getAudioInputStream(zvansURL);
            Clip c = AudioSystem.getClip(); c.open(ais); c.start();
        } catch (Exception ignored) {}
    }

    static DefaultTableModel tableModel;
    static JTable table;

    public static void raditTabulu(String nosaukums, ArrayList<Pica> dati) {
        JFrame frame = new JFrame(nosaukums);
        frame.setSize(950, 400);

        String[] columns = {"Klients","Adrese","Tel","Pica","Izm.","Mērce",
                "Dzēriens","Tilpums","Uzkoda","Piedevas","Cena","Piegāde"};

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        for (Pica p : dati) {
            tableModel.addRow(new Object[]{
                    p.getVards(), p.getAdrese(), p.getTel(),
                    p.getNosaukums(), p.getIzmers(),
                    p.getMauce(), p.getDrinks(),
                    p.getTilpums(), p.getUzkoda(),
                    p.getPiedevas(), p.getCena(),
                    p.getPiegade()
            });
        }

        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(230, 240, 230));

        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Picērija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.getContentPane().setBackground(Color.PINK);
        frame.setLayout(null);
        frame.setResizable(false);

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
        frame.add(btnAdreses); frame.add(btnNumurs); frame.add(btnRemake);
        frame.add(btngatavie); frame.add(saglabatGatavie); frame.add(btnExit);
        frame.setVisible(true);

        atskanotMainMenuSound();

        btnAdd.addActionListener(e -> {
            try {
                // Picas nosaukumi un bildes
                String[] picasNosaukums = {"Margarita - 6€", "Veģetāra - 7€", "Gaļas - 8€"};
                String[] urls = {"/bildes/pica.png", "/bildes/vegana.png", "/bildes/galas.png"};
                ImageIcon[] picasBildes = new ImageIcon[urls.length];

                for (int i = 0; i < urls.length; i++) {
                    URL u = Picerija.class.getResource(urls[i]);
                    if (u != null) {
                        picasBildes[i] = new ImageIcon(u);
                        // Samazinām attēlu, lai ietilptu dialogā
                        picasBildes[i] = new ImageIcon(
                                picasBildes[i].getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)
                        );
                    } else {
                        picasBildes[i] = null;
                    }
                }

                // Izvēlamies picu
                int picIzvele = JOptionPane.showOptionDialog(
                        null, "Izvēlies picu:", "Pica",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, picasNosaukums, picasNosaukums[0]
                );
                if (picIzvele < 0) return;

                // Rāda izvēlēto picu ar bildi
                if (picasBildes[picIzvele] != null) {
                    JOptionPane.showMessageDialog(null,
                            "Izvēlētā pica: " + picasNosaukums[picIzvele],
                            "Pica", JOptionPane.INFORMATION_MESSAGE, picasBildes[picIzvele]);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Izvēlētā pica: " + picasNosaukums[picIzvele]);
                }

                // Cena pēc izvēles
                double cena = picIzvele == 0 ? 6 : picIzvele == 1 ? 7 : 8;

                // Izmērs
                String[] izmeri = {"Mazā (22cm) - 0€","Vidējā (30cm) +2€","Lielā (40cm) +4€"};
                int izmIzvele = JOptionPane.showOptionDialog(
                        null, "Izvēlies izmēru:", "Izmērs",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, izmeri, izmeri[0]
                );
                if (izmIzvele == 1) cena += 2;
                if (izmIzvele == 2) cena += 4;

                // Piedevas
                String[] piedevas = {"Nav","Sēnes +1€","Paprika +1€","Olīvas +1€","Extra siers +2€"};
                int piedIzvele = JOptionPane.showOptionDialog(
                        null, "Izvēlies piedevas:", "Piedevas",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, piedevas, piedevas[0]
                );
                if (piedIzvele >= 1 && piedIzvele <= 3) cena += 1;
                if (piedIzvele == 4) cena += 2;

                // Izveidojam Pica objektu
                Pica p;
                if (piedIzvele == 0) {
                    p = new ParastaPica(picasNosaukums[picIzvele], cena, izmeri[izmIzvele], piedevas[piedIzvele]);
                } else {
                    p = new PicaArPiedevas(picasNosaukums[picIzvele], cena, izmeri[izmIzvele], piedevas[piedIzvele], piedevas[piedIzvele]);
                }

                // Mērce
                String[] merci = {"Nav","Kečups +0.50€","Čili +0.50€","BBQ +0.50€"};
                int mercIzvele = JOptionPane.showOptionDialog(
                        null, "Izvēlies mērci:", "Mērce",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, merci, merci[0]
                );
                if (mercIzvele > 0) cena += 0.5;
                p.setMerce(merci[mercIzvele]);

                // Dzēriens
                String[] drinks = {"Nav","Cola +2€","Sula +2€","Tēja +1€"};
                int dzIzv = JOptionPane.showOptionDialog(
                        null, "Dzēriens:", "Dzērieni",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, drinks, drinks[0]
                );
                double dzCena = (dzIzv == 3 ? 1 : (dzIzv == 1 || dzIzv == 2 ? 2 : 0));
                p.setDrinks(drinks[dzIzv]);

                // Tilpums
                String tilp = "Nav";
                if (dzIzv != 0) {
                    String[] ml = {"0.25L +0.25€","0.33L +0.50€","0.50L +0.75€"};
                    int mlIzv = JOptionPane.showOptionDialog(
                            null, "Tilpums:", "Tilpums",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, ml, ml[0]
                    );
                    tilp = ml[mlIzv];
                    if (mlIzv == 1) dzCena += 0.25;
                    if (mlIzv == 2) dzCena += 0.50;
                    if (mlIzv == 3) dzCena += 0.75;
                }
                p.setTilpums(tilp);
                cena += dzCena;

                // Uzkoda
                String[] uzkoda = {"Nav","Čipsi +1€","Frī +1.50€","Grauzdiņi +2€"};
                int uzkIzv = JOptionPane.showOptionDialog(
                        null, "Uzkoda:", "Uzkodas",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, uzkoda, uzkoda[0]
                );
                if (uzkIzv == 1) cena += 1;
                if (uzkIzv == 2) cena += 1.5;
                if (uzkIzv == 3) cena += 2;
                p.setUzkoda(uzkoda[uzkIzv]);

                // Piegāde un klienta info
                int pieg = JOptionPane.showConfirmDialog(null,"Piegāde? (+3€)","Piegāde",JOptionPane.YES_NO_OPTION);
                if (pieg == JOptionPane.YES_OPTION) {
                    cena += 3;
                    String v = VirknesParbaude("Klienta vārds:","Jānis"); if(v == null) return;
                    String a = VirknesParbaude("Adrese:","Brīvības 10"); if(a == null) return;
                    String t = VirknesParbaude("Telefons:","+371 0000000"); if(t == null) return;
                    p.setKlientaInfo(v,a,t,"Piegāde");
                    adreses.add(a); numurs.add(t);
                } else {
                    p.setKlientaInfo("Uz vietas","-","-","Uz vietas");
                }

                pasutijumi.add(p);
                JOptionPane.showMessageDialog(null,"Pasūtījums pievienots!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        btnView.addActionListener(e -> {
            if(pasutijumi.isEmpty()) {JOptionPane.showMessageDialog(null,"Nav aktīvu!");return;}
            raditTabulu("Aktīvie", new ArrayList<>(pasutijumi));
        });

        btnDone.addActionListener(e -> {
            if(pasutijumi.isEmpty()){JOptionPane.showMessageDialog(null,"Nav!");return;}
            Pica p = pasutijumi.poll();
            gatavie.add(p);
            JOptionPane.showMessageDialog(null,"Pabeigts!");
            atskanotZvanu();
        });

        btnAdreses.addActionListener(e -> {
            if(adreses.isEmpty()) {JOptionPane.showMessageDialog(null,"Nav datu");return;}
            JOptionPane.showMessageDialog(null,String.join("\n",adreses));
        });

        btnNumurs.addActionListener(e -> {
            if(numurs.isEmpty()) {JOptionPane.showMessageDialog(null,"Nav datu");return;}
            JOptionPane.showMessageDialog(null,String.join("\n",numurs));
        });

        btngatavie.addActionListener(e -> {
            if(gatavie.isEmpty()) {JOptionPane.showMessageDialog(null,"Nav gatavo");return;}
            raditTabulu("Gatavie", gatavie);
            if(JOptionPane.showConfirmDialog(null,"Saglabāt?")==JOptionPane.YES_OPTION){
                saglabat.addAll(gatavie); gatavie.clear();
            }
        });

        saglabatGatavie.addActionListener(e -> {
            if(saglabat.isEmpty()) {JOptionPane.showMessageDialog(null,"Nav saglabātu");return;}
            raditTabulu("Saglabātie", saglabat);
        });

        btnRemake.addActionListener(e -> {
            if(pasutijumi.isEmpty()){JOptionPane.showMessageDialog(null,"Nav rediģējamo");return;}
            pasutijumi.poll();
            btnAdd.doClick();
        });

        btnExit.addActionListener(e -> System.exit(0));
    }
}
