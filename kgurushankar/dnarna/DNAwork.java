package kgurushankar.dnarna;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DNAwork {

	// Note : X means STOP
	// U - 0 C - 1 A - 2 G - 3
	private static char[][][] letterArr;

	private static Map<Character, String[]> codonTable;
	private static Map<Character, String> shortTable;

	public DNAwork() {
		letterArr = new char[4][4][4];
		codonTable = new HashMap<Character, String[]>();
		shortTable = new HashMap<Character, String>();
		setLetterArr();
		setCodonTable();
		setShortTable();
	}

	public static void main(String[] args) {

		DNAwork a = new DNAwork();
		System.out.println("Please select the option that corresponds to the text you will be entering");
		System.out.println("1 - DNA (template strand 3' - 5')");
		System.out.println("2 - RNA (template strand 5' - 3')");
		System.out.println("3 - Amino Acids in letter format (N to C terminus)");

		int option;
		Scanner in = new Scanner(System.in);

		while (true) {
			try {
				option = Integer.parseInt(in.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Please enter valid input...");
			}
		}
		String DNA = "", RNA = "", AA = "";
		switch (option) {
		case (1): {
			System.out.println("Please enter the DNA sequence");
			DNA = in.nextLine();
			RNA = a.DNAtoRNA(DNA);
			AA = a.RNAtoLetters(RNA);
			break;
		}
		case (2): {
			System.out.println("Please enter the RNA sequence");
			RNA = in.nextLine();
			DNA = a.RNAtoDNA(RNA);
			AA = a.RNAtoLetters(RNA);
			break;
		}
		case (3): {
			System.out.println("Please enter the Amino Acid sequence");
			AA = in.nextLine();
			RNA = a.LetterstoRNA(AA);
			DNA = a.RNAtoDNA(RNA);
			break;
		}
		}
		in.close();
		System.out.println("DNA     -     3'  " + DNA.replaceAll(" ", "") + "   5'");
		System.out.println("RNA     -     5'  " + RNA.replaceAll(" ", "") + "   3'");
		System.out.print("Amino Acids - N   ");
		// For alignment purposes
		for (int i = 0; i < AA.length(); i++) {
			System.out.print(" " + shortTable.get(AA.charAt(i)) + " ");
		}
		System.out.println("   C");
	}

	public String DNAtoRNA(String DNA) {
		String str = DNA.toUpperCase();
		boolean n = (((remove(str, "A", "C", "T", "G")).replaceAll("-", "").replaceAll(" ", "")).equals("")) ? true
				: false;
		if (n) {

			char[] x = (DNA.toUpperCase()).toCharArray();
			String out = "";
			for (int i = 0; i < x.length; i++) {
				if (x[i] == 'A') {
					out += "U";
				} else if (x[i] == 'T') {
					out += "A";
				} else if (x[i] == 'C') {
					out += "G";
				} else if (x[i] == 'G') {
					out += "C";
				} else {
					out += x[i];
				}
			}

			return out;
		} else {
			return "Error";
		}

	}

	public String RNAtoDNA(String RNA) {
		String str = RNA.toUpperCase();
		boolean n = (((remove(str, "A", "C", "U", "G")).replaceAll("-", "").replaceAll(" ", "")).equals("")) ? true
				: false;
		if (n) {
			char[] x = (RNA.toUpperCase()).toCharArray();
			String out = "";
			for (int i = 0; i < x.length; i++) {
				if (x[i] == 'U') {
					out += "A";
				} else if (x[i] == 'A') {
					out += "T";
				} else if (x[i] == 'C') {
					out += "G";
				} else if (x[i] == 'G') {
					out += "C";
				} else {
					out += x[i];
				}
			}

			return out;
		} else {
			return "Error";
		}
	}

	public String RNAtoLetters(String RNA) {
		String str = (RNA.toUpperCase()).replaceAll(" ", "");
		boolean n = (((remove(str, "A", "C", "U", "G")).replaceAll("-", "")).equals("")) ? true : false;
		if (n) {
			String out = "";
			int rby3;
			int i;
			if (CodontoLetter(str.substring(0, 3)) == 'M') {
				i = 0;
				rby3 = (int) ((str.length() / 3.0));
			} else if (CodontoLetter(str.substring(1, 4)) == 'M') {
				i = 1;
				rby3 = (int) ((str.length() / 3.0) - .33);
			} else if (CodontoLetter(str.substring(2, 5)) == 'M') {
				i = 2;
				rby3 = (int) ((str.length() / 3.0) - .66);
			} else {
				i = 0;
				rby3 = (int) ((str.length() / 3.0));
			}
			for (int j = 0; j < rby3; j++) {
				String temp = str.substring((i + j * 3), (i + j * 3 + 3));
				char check = CodontoLetter(temp);
				out += ((check == 'X') ? "stop" : check);
			}
			return out;

		} else {
			return "Error";
		}
	}

	public String LetterstoRNA(String letters) {
		char[] str = (letters.toUpperCase()).toCharArray();
		String out = "";
		for (int i = 0; i < str.length; i++) {
			out += LettertoCodon(str[i]);
		}
		return out;
	}

	private String remove(String str, String one, String two, String three, String four) {
		String val = (((str.replaceAll(one, "-")).replaceAll(two, "-")).replaceAll(three, "-")).replaceAll(four, "-");
		return val;
	}

	private char CodontoLetter(String Codon) {
		// U - 0 C - 1 A - 2 G - 3
		int[] index = new int[3];
		for (int i = 0; i < 3; i++) {
			if (Codon.charAt(i) == 'U') {
				index[i] = 0;
			} else if (Codon.charAt(i) == 'C') {
				index[i] = 1;
			} else if (Codon.charAt(i) == 'A') {
				index[i] = 2;
			} else if (Codon.charAt(i) == 'G') {
				index[i] = 3;
			}
		}
		return letterArr[index[0]][index[1]][index[2]];
	}

	private String LettertoCodon(char letter) {
		String[] set = codonTable.get(Character.toUpperCase(letter));
		String out = RndfromArray(set);
		return out;
	}

	private String RndfromArray(String[] arr) {
		int x = (int) (Math.random() * arr.length);
		String out = arr[x];
		return out;
	}

	private void setLetterArr() {
		letterArr[0][0][0] = 'F'; // UUU
		letterArr[0][0][1] = 'F'; // UUC
		letterArr[0][0][2] = 'L'; // UUA
		letterArr[0][0][3] = 'L'; // UUG

		letterArr[0][1][0] = 'S'; // UCU
		letterArr[0][1][1] = 'S'; // UCC
		letterArr[0][1][2] = 'S'; // UCA
		letterArr[0][1][3] = 'S'; // UCG

		letterArr[0][2][0] = 'Y'; // UAU
		letterArr[0][2][1] = 'Y'; // UAC
		letterArr[0][2][2] = 'X'; // UAA
		letterArr[0][2][3] = 'X'; // UAG

		letterArr[0][3][0] = 'C'; // UGU
		letterArr[0][3][1] = 'C'; // UGC
		letterArr[0][3][2] = 'X'; // UGA
		letterArr[0][3][3] = 'W'; // UGG

		// ********************************

		letterArr[1][0][0] = 'L'; // CUU
		letterArr[1][0][1] = 'L'; // CUC
		letterArr[1][0][2] = 'L'; // CUA
		letterArr[1][0][3] = 'L'; // CUG

		letterArr[1][1][0] = 'P'; // CCU
		letterArr[1][1][1] = 'P'; // CCC
		letterArr[1][1][2] = 'P'; // CCA
		letterArr[1][1][3] = 'P'; // CCG

		letterArr[1][2][0] = 'H'; // CAU
		letterArr[1][2][1] = 'H'; // CAC
		letterArr[1][2][2] = 'Q'; // CAA
		letterArr[1][2][3] = 'Q'; // CAG

		letterArr[1][3][0] = 'R'; // CGU
		letterArr[1][3][1] = 'R'; // CGC
		letterArr[1][3][2] = 'R'; // CGA
		letterArr[1][3][3] = 'R'; // CGG

		// ********************************

		letterArr[2][0][0] = 'I'; // AUU
		letterArr[2][0][1] = 'I'; // AUC
		letterArr[2][0][2] = 'I'; // AUA
		letterArr[2][0][3] = 'M'; // AUG

		letterArr[2][1][0] = 'T'; // ACU
		letterArr[2][1][1] = 'T'; // ACC
		letterArr[2][1][2] = 'T'; // ACA
		letterArr[2][1][3] = 'T'; // ACG

		letterArr[2][2][0] = 'N'; // AAU
		letterArr[2][2][1] = 'N'; // AAC
		letterArr[2][2][2] = 'K'; // AAA
		letterArr[2][2][3] = 'K'; // AAG

		letterArr[2][3][0] = 'S'; // AGU
		letterArr[2][3][1] = 'S'; // AGC
		letterArr[2][3][2] = 'R'; // AGA
		letterArr[2][3][3] = 'R'; // AGG

		// ********************************

		letterArr[3][0][0] = 'V'; // GUU
		letterArr[3][0][1] = 'V'; // GUC
		letterArr[3][0][2] = 'V'; // GUA
		letterArr[3][0][3] = 'V'; // GUG

		letterArr[3][1][0] = 'A'; // GCU
		letterArr[3][1][1] = 'A'; // GCC
		letterArr[3][1][2] = 'A'; // GCA
		letterArr[3][1][3] = 'A'; // GCG

		letterArr[3][2][0] = 'D'; // GAU
		letterArr[3][2][1] = 'D'; // GAC
		letterArr[3][2][2] = 'E'; // GAA
		letterArr[3][2][3] = 'E'; // GAG

		letterArr[3][3][0] = 'G'; // GGU
		letterArr[3][3][1] = 'G'; // GGC
		letterArr[3][3][2] = 'G'; // GGA
		letterArr[3][3][3] = 'G'; // GGG

	}

	private void setCodonTable() {

		codonTable.put('A', new String[] { "GCU", "GCC", "GCA", "GCG" });
		codonTable.put('C', new String[] { "UGU", "UGC" });
		codonTable.put('D', new String[] { "GAU", "GAC" });
		codonTable.put('E', new String[] { "GAA", "GAG" });
		codonTable.put('F', new String[] { "UUU", "UUU" });
		codonTable.put('G', new String[] { "GGU", "GGC", "GGA", "GGG" });
		codonTable.put('H', new String[] { "CAU", "CAC" });
		codonTable.put('I', new String[] { "AUU", "AUC", "AUA" });
		codonTable.put('K', new String[] { "AAA", "AAG" });
		codonTable.put('L', new String[] { "UUA", "UUG", "CUU", "CUC", "CUA", "CUG" });
		codonTable.put('M', new String[] { "AUG" });
		codonTable.put('N', new String[] { "AAU", "AAC" });
		codonTable.put('P', new String[] { "CCU", "CCC", "CCA", "CCG" });
		codonTable.put('Q', new String[] { "CAA", "CAG" });
		codonTable.put('R', new String[] { "CGU", "CGC", "CGA", "CGG", "AGA", "AGG" });
		codonTable.put('S', new String[] { "UCU", "UCC", "UCA", "UGC", "AGU", "AGC" });
		codonTable.put('T', new String[] { "ACU", "ACC", "ACA", "ACG" });
		codonTable.put('V', new String[] { "GUU", "GUC", "GUA", "GUG" });
		codonTable.put('W', new String[] { "UGG" });
		codonTable.put('X', new String[] { "UAA", "UAG", "UGA" });// Stop
		codonTable.put('Y', new String[] { "UAU", "UAC" });
	}

	private void setShortTable() {
		shortTable.put('A', "Ala");
		shortTable.put('C', "Cys");
		shortTable.put('D', "Asp");
		shortTable.put('E', "Glu");
		shortTable.put('F', "Phe");
		shortTable.put('G', "Gly");
		shortTable.put('H', "His");
		shortTable.put('I', "Ile");
		shortTable.put('K', "Lys");
		shortTable.put('L', "Leu");
		shortTable.put('M', "Met");
		shortTable.put('N', "Asn");
		shortTable.put('P', "Pro");
		shortTable.put('Q', "Gln");
		shortTable.put('R', "Arg");
		shortTable.put('S', "Ser");
		shortTable.put('T', "Thr");
		shortTable.put('V', "Val");
		shortTable.put('W', "Trp");
		shortTable.put('X', "Stop");
		shortTable.put('Y', "Tyr");
	}

}
