import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

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

        btnAdd.addActionListener(e -> {
            try {
                // --- Picas nosaukumi ---
                String[] picasNosaukums = {"Margarita - 6€", "Veģetāra - 7€", "Gaļas - 8€"};

                // --- Bildes no GitHub RAW links ---
                String[] urls = {
                        "https://raw.githubusercontent.com/SkyWhy14/Picerija/0cc1e73ecd6767f73bcb06807d67d173faf55a50/bildes/pica.png",
                        "https://raw.githubusercontent.com/SkyWhy14/Picerija/0cc1e73ecd6767f73bcb06807d67d173faf55a50/bildes/vegana.png",
                        "https://raw.githubusercontent.com/SkyWhy14/Picerija/0cc1e73ecd6767f73bcb06807d67d173faf55a50/bildes/galas.png"
                };

                ImageIcon[] picasBildes = new ImageIcon[urls.length];
                for (int i = 0; i < urls.length; i++) {
                    picasBildes[i] = new ImageIcon(new URL(urls[i]));
                }

                // Izvēle ar tekstu
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

                // Parāda izvēlēto picu ar GitHub bildi
                JOptionPane.showMessageDialog(
                        null,
                        "Izvēlēta pica: " + picasNosaukums[picIzvele],
                        "Pica",
                        JOptionPane.INFORMATION_MESSAGE,
                        picasBildes[picIzvele]
                );

                // Dzērieni
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

                // Piegāde
                int piegade1 = JOptionPane.showConfirmDialog(
                        null,
                        "Vai izmantot piegādi? (+3€)",
                        "Piegāde",
                        JOptionPane.YES_NO_OPTION
                );

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
                } else {
                    piegade = "Uz vietas";
                }

                // Sagatavo pasūtījumu tekstu
                String pasutijums = "Pica: " + picasNosaukums[picIzvele] +
                        "\nDzēriens: " + dz +
                        "\nPiegāde: " + piegade +
                        (piegade1 == JOptionPane.YES_OPTION ?
                                "\nVārds: " + klientaVards +
                                        "\nAdrese: " + adrese +
                                        "\nTelefons: " + telefons : "") +
                        "\nCena: " + String.format("%.2f", cena) + "€";

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
            }
        });

        btnExit.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Picerija aizvērta! Uz redzēšanos!");
            System.exit(0);
        });
    }
}
