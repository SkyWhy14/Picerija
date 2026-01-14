import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Picerija {
    public static Queue<Pica> pasutijumi = new LinkedList<>();
    public static Queue<Pica> izpilditiePasutijumi = new LinkedList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Picērija");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.PINK);
        frame.setLayout(null);

        JButton btn1 = new JButton("Jauns Pasūtījums");
        btn1.setBounds(50, 50, 150, 40);

        JButton btn2 = new JButton("Apskatīt Pasūtījumus");
        btn2.setBounds(50, 120, 150, 40);

        JButton btn3 = new JButton("Pabeigt Pasūtījumu");
        btn3.setBounds(50, 190, 150, 40);

        JButton btn4 = new JButton("Iziet");
        btn4.setBounds(50, 260, 150, 40);

        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.add(btn4);
        frame.setVisible(true);

        // --- JAUNS PASŪTĪJUMS ---
        btn1.addActionListener(e -> {
            String izvele = (String) JOptionPane.showInputDialog(null,
                    "Izvēlies picas izmēru", "Picas Izmērs",
                    JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"Mazā", "Vidējā", "Lielā"}, "Vidējā");

            if (izvele == null) return;

            String piedevas = (String) JOptionPane.showInputDialog(null,
                    "Izvēlies piedevas", "Picas Piedevas",
                    JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"Siers", "Šampinjoni", "Paprika", "Olīvas", "Kartupeļi"}, "Siers");

            if (piedevas == null) return;

            String papildus = "";
            int atbilde = JOptionPane.showConfirmDialog(null,
                    "Vai vēlies papildus piedevas?", "Piedevas", JOptionPane.YES_NO_OPTION);
            if (atbilde == JOptionPane.YES_OPTION) {
                papildus = (String) JOptionPane.showInputDialog(null,
                        "Izvēlies papildus piedevas", "Papildus Piedevas",
                        JOptionPane.QUESTION_MESSAGE, null,
                        new String[]{"Tomāti", "Gurķi", "Sīpoli", "Kukurūza"}, "Tomāti");
            }

            double cena = 5.0; // pamata cena
            if (izvele.equals("Vidējā")) cena += 2;
            if (izvele.equals("Lielā")) cena += 4;

            Pica pica = new ParastaPica("Margherita", cena, izvele, piedevas, papildus);

            // Dzēriens
            String dz = "";
            int d = JOptionPane.showConfirmDialog(null,
                    "Vai vēlies dzērienu?", "Dzērieni", JOptionPane.YES_NO_OPTION);

            if (d == JOptionPane.YES_OPTION) {
                String dzersieni = (String) JOptionPane.showInputDialog(null,
                        "Dzēriens", "Piedāvājums",
                        JOptionPane.QUESTION_MESSAGE, null,
                        new String[]{"Kola", "Fanta", "Sprite", "Ūdens"}, "Kola");

                String ml = (String) JOptionPane.showInputDialog(null,
                        "Daudzums", "Izvēlies ml",
                        JOptionPane.QUESTION_MESSAGE, null,
                        new String[]{"250 ml", "400 ml", "500 ml"}, "250 ml");

                dz = " | Dzēriens: " + dzersieni + " " + ml;
            }

            // Saglabā pasūtījumu
            pasutijumi.add(new ParastaPica("Margherita" + dz, cena, izvele, piedevas, papildus));

            JOptionPane.showMessageDialog(null, "Pasūtījums veiksmīgi reģistrēts:\n" + pica + dz);
        });

        // --- APSKATĪT PASŪTĪJUMUS ---
        btn2.addActionListener(e -> {
            if (pasutijumi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav aktīvu pasūtījumu.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Pica p : pasutijumi) {
                    sb.append(p.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, "Aktīvie pasūtījumi:\n\n" + sb.toString());
            }
        });

        // --- PABEIGT PASŪTĪJUMU ---
        btn3.addActionListener(e -> {
            if (pasutijumi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav pasūtījumu, ko pabeigt.");
            } else {
                Pica izpildits = pasutijumi.poll();
                izpilditiePasutijumi.add(izpildits);
                JOptionPane.showMessageDialog(null, "Pabeigts: " + izpildits);
            }
        });

        // --- IZIEST ---
        btn4.addActionListener(e -> System.exit(0));
    }
}