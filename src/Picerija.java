
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

import javax.swing.JOptionPane;

public class Picerija {
	public static String VirknesParbaude(String zinojums,String noklusejums) {
		String virkne ;
		
		do {
			virkne = JOptionPane.showInputDialog(zinojums,noklusejums);
			if(virkne == null)
				return null;
			
			virkne = virkne.trim();
			
		}while(!Pattern.matches("^[\\p{L} .]+$", virkne));
		return virkne;
	}
	public static int SkaitlaParbaude(String zinojums,int min,int max) {
		
		int skaitlis;
		while(true) {
			String ievade = JOptionPane.showInputDialog(null,zinojums,"Datu ievade",JOptionPane.INFORMATION_MESSAGE);
			if(ievade == null)
				return -1;
			
			try {
				skaitlis = Integer.parseInt(ievade);
				if(skaitlis <min || skaitlis > max) {
					JOptionPane.showMessageDialog(null, "Noradits nederigs intervals","Nekorekti dati",JOptionPane.WARNING_MESSAGE);
					continue;
				}
				return skaitlis;
				
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Netika ievadits vesels skaitlis","Nekorekti dati",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	static void main(String[] args) {
		String izvele;
		String [] darbibusarakst = {"Saglabāt piegādes adresi",
									"Saglaba pircēja vārdu","Saglabā talruni",
									"Aprēķina picas cenu",
									"Picas piegāde",
									"Apskatit pasūtijumu",
									"Apturet"};
		ArrayList<String> piegadesAdrese = new ArrayList<>();
		ArrayList<String> pircējaVards = new ArrayList<>();
		ArrayList<String> talrunis = new ArrayList<>();
		Queue<String> pasutijumi = new LinkedList<>();
		Queue<String> izpilditiePasutijumi = new LinkedList<>();
		do {
			izvele =(String) JOptionPane.showInputDialog(null,"Izvelies darbibu","Darbibu izvele",JOptionPane.QUESTION_MESSAGE,null,darbibusarakst,darbibusarakst[0]);
			if(izvele == null)
				izvele = "Apturet";
			switch(izvele) {
			case "Veikt pasutijumu": 
				
				break;
			
			}
		}while(!izvele.equals("Apturet"));

	}
	
	
}
