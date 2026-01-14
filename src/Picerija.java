
/*
 * 
 * Balstoties uz Tevis paš izstrādātajām projekta UML diagrammām un pielietojot savas līdzšinējās
zināšanas (masīvi, metodes, darbs ar failiami, dinamiskās datu struktūras, OOP), izveido optimizētu,
pārdomātu un atkļūdotu programmu. Programmā picērijas darbiniekiem ļauj reģistrēt pasūtījumu
informāciju. Paturi prātā, ka picērijā iespējams iegādāties ne tikai picas, bet arī uzkodas un dzērienus.
Projektā iekļauj grafisko lietotāja saskarni (pogas, bildes, formas). Piemēram, programmā var fiksēt un
uzglabāt adresi, tālruņi, vārdu personai, kas pasūta picu. Tālāk seko vairāku soļu darbības: iespējams
izvēlēties picas lielumu, piedevas, mērces. Picas cena veidojas no izmantotajām sastāvdaļām un tās
izmēra. Ja klients picu izvēlas izņemt uz vietas ceptuvē, tad piegādes cena nav jāiekļauj. Jebkurā brīdī ir
iespējams apskatīt aktīvos un jau klientam nodotos pasūtījumus, tos vajadzētu glabāt teksta failā.

Realizētas projekta funkcionālās prasības (noformēt, apskatīt aktīvos / pabeigtos pasūtījumus,
aprēķināt cenu, saglabāt / nolasīt pasūtījumu vēsturi u.c.), kuras dotas uzdevuma aprakstā (10p);

Izstrādāta projekta klašu struktūra un funkcionalitāte atbilstoši UML diagrammām (6p);

Projekta izstrādes process jēgpilni tiek versēnēts ar Git, veikta izvetošana GitHub un pievienots
projektuu aprakstošs README fails, Releases sadaļā izvietots darbināms projekta JAR fails (6p);

Veikta pārdomāta projekta demonstrācija kursa biedriem, orientējas programmas kodā un spēj
sniegt atbildes uz uzdotajiem jautājumiem (6p);

Atklātas iespējamās kļūdas programmā, programma strādā bez kļūdu ziņojumiem un novērstas iespējamās
kļūdas picu izveides loģikā, cenas aprēķināšanā u.tml. (4p).
 * 
 * 
 * 
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Picerija  {
    public static String ievade;

    // Teksts drīkst saturēt burtus un ciparus
    static String virknesParbaude(String zinojums, String noklusejums) {
        String virkne;
        do {
            virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
            if (virkne == null)
                return null;

            virkne = virkne.trim();
        } while (virkne.isEmpty()); // nepieļauj tukšu
        return virkne;
    }

    // Skaitlis ar intervālu (ja vajadzēs vēlāk)
    public static int skaitlaParbaude(String zinojums, int min, int max) {
        String ievade;
        int skaitlis;

        while (true) {
            ievade = JOptionPane.showInputDialog(null, zinojums,
                    "Datu ievade", JOptionPane.INFORMATION_MESSAGE);

            if (ievade == null)
                return -1;

            try {
                skaitlis = Integer.parseInt(ievade);
                if (skaitlis < min || skaitlis > max) {
                    JOptionPane.showMessageDialog(null,
                            "Norādīts nederīgs intervāls",
                            "Nekorekti dati", JOptionPane.WARNING_MESSAGE);
                } else {
                    return skaitlis;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Netika ievadīts vesels skaitlis",
                        "Nekorekti dati", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Dati
    public static ArrayList<String> piegadesAdrese = new ArrayList<>();
    public static ArrayList<String> pircējaVards = new ArrayList<>();
    public static ArrayList<String> talrunis = new ArrayList<>();
    public static Queue<String> pasutijumi = new LinkedList<>();
    public static Queue<String> izpilditiePasutijumi = new LinkedList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Picērija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        JButton btn1 = new JButton("Jauns Pasūtījums");
        btn1.setBounds(50, 50, 150, 40);

        JButton btn2 = new JButton("Apskatīt Pasūtījumus");
        btn2.setBounds(50, 120, 150, 40);

        JButton btn3 = new JButton("Pabeigt Pasūtījumu");
        btn3.setBounds(50, 190, 150, 40);

        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.setVisible(true);

        // --- JAUNS PASŪTĪJUMS ---
        btn1.addActionListener(e -> {
            String vards = virknesParbaude("Ievadi pircēja vārdu", "Jānis Bērziņš");
            if (vards == null) return;
            pircējaVards.add(vards);

            String adrese = virknesParbaude("Ievadi piegādes adresi", "Brīvības iela 10, Rīga");
            if (adrese == null) return;
            piegadesAdrese.add(adrese);

            String telefons = virknesParbaude("Ievadi tālruņa numuru", "+371 24234242");
            if (telefons == null) return;
            talrunis.add(telefons);

            String izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies picas izmēru",
                    "Picas Izmērs", JOptionPane.QUESTION_MESSAGE,
                    null, new String[]{"Mazā", "Vidējā", "Lielā"}, "Vidējā");
            if (izvele == null) return;

            String piedevas = (String) JOptionPane.showInputDialog(null, "Izvēlies piedevas",
                    "Picas Piedevas", JOptionPane.QUESTION_MESSAGE,
                    null, new String[]{"Siers", "Šampinjoni", "Paprika", "Olīvas", "Kartupeļi"}, "Siers");
            if (piedevas == null) return;

            String pasutijums = vards + " | " + izvele + " pica, piedevas: " + piedevas;
            pasutijumi.add(pasutijums);
            int atbilde = JOptionPane.showConfirmDialog(
                    null,
                    "Vai vēlaties vēl kaut ko pievienot picai?",
                    "Piedevas",
                    JOptionPane.YES_NO_OPTION
            );
            if (atbilde == JOptionPane.YES_OPTION) {
                String papildusPiedevas = (String) JOptionPane.showInputDialog(null, "Izvēlies papildus piedevas",
						"Papildus Piedevas", JOptionPane.QUESTION_MESSAGE,
						null, new String[]{"Tomāti", "Gurķi", "Sīpoli", "Kukurūza"}, "Tomāti");
            } else if (atbilde == JOptionPane.NO_OPTION) {
               JOptionPane.showMessageDialog(null, "Pasūtījums veiksmīgi reģistrēts:\n" + pasutijums);
            } 
         
         int atbilde2 = JOptionPane.showConfirmDialog(
                 null,
                 "Vai velaties pasutit kaut kadu dzērienu?",
                 "Dzērieni",
                 JOptionPane.YES_NO_OPTION
         );
         if(atbilde2 == JOptionPane.YES_OPTION) {
			 String dzersieni = (String) JOptionPane.showInputDialog(null, "Izvēlies dzērienu",
					 "Dzērieni", JOptionPane.QUESTION_MESSAGE,
					 null, new String[]{"Kola", "Fanta", "Sprite", "Ūdens"}, "Kola");
			 //var izveleties daudzumu ml ar dropdown
			 String dzeriensMl = (String) JOptionPane.showInputDialog(null, "Izvēlies dzēriena daudzumu (ml)",
					 "Dzēriens", JOptionPane.QUESTION_MESSAGE,
					 null, new String[]{"250 ml ", "400 ml ", "500 ml "}, "250 ml ");
			 		 JOptionPane.showMessageDialog(null, "Pasūtījums veiksmīgi reģistrēts:\n" + pasutijums + "\nDzēriens: " + dzersieni + " " + dzeriensMl);
			 
		 } else if (atbilde2 == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Pasūtījums veiksmīgi reģistrēts:\n" + pasutijums);
		 }
        
         
         
        });

        // --- APSKATĪT PASŪTĪJUMUS ---
        btn2.addActionListener(e -> {
            if (pasutijumi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nav aktīvu pasūtījumu.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Aktīvie pasūtījumi:\n" + String.join("\n", pasutijumi));
            }
        });
        
        // --- PABEIGT PASŪTĪJUMU ---
        
        btn3.addActionListener(e -> {
        	System.exit(0);   });
    }
}