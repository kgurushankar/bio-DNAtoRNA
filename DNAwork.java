import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DNAwork {

	// Note : X means STOP
	// U - 0 	C - 1	A - 2	G - 3
	private char[][][] letterTable = new char[4][4][4];
	private Map<String, String[]> codonTable = new HashMap<String, String[]>();
	
	public DNAwork(){
		setLetterTable();
		setCodonArr();
	}
	
	public static void main(String[] args) {
		System.out.println((int)('a'));
		
		DNAwork a = new DNAwork();
		Scanner in = new Scanner(System.in);
		String DNA = in.nextLine();
		in.close();
		String RNA = a.DNAtoRNA(DNA);
		String Letters = a.RNAtoLetters(RNA);
		System.out.println(RNA);
		System.out.println(Letters);

	}
	
	public String DNAtoRNA (String DNA){
		String str = DNA.toUpperCase();
		boolean n = (((remove(str,"A","C","T","G")).replaceAll("-", "").replaceAll(" ", "")).equals("")) ? true : false;
		if (n){
		
			char[] x = (DNA.toUpperCase()).toCharArray();
			String out="";
			for (int i = 0; i<x.length; i++){
				if 			(x[i]=='A'){
					out+="U";
				} else if 	(x[i]=='T'){
					out+="A";
				} else if 	(x[i]=='C'){
					out+="G";
				} else if 	(x[i]=='G'){
					out+="C";
				} else {
					out+=x[i];
				}
			}
			
			return out;
		}
		else {return "Error";}
		
	}
	
	public String RNAtoDNA(String RNA){
		String str = RNA.toUpperCase();
		boolean n = (((remove(str,"A","C","U","G")).replaceAll("-", "").replaceAll(" ", "")).equals("")) ? true : false;
		if (n){
			char[] x = (RNA.toUpperCase()).toCharArray();
			String out="";
			for (int i = 0; i<x.length; i++){
				if 			(x[i]=='U'){
					out+="A";
				} else if 	(x[i]=='A'){
					out+="T";
				} else if 	(x[i]=='C'){
					out+="G";
				} else if 	(x[i]=='G'){
					out+="C";
				} else {
					out+=x[i];
				}
			}
			
			return out;
		}
		else {return "Error";}
	}
	
	public String RNAtoLetters(String RNA){
		String str = (RNA.toUpperCase()).replaceAll(" ", "");
		boolean n = (((remove(str,"A","C","U","G")).replaceAll("-", "")).equals("")) ? true : false;
		if (n){
			String out="";
			int rby3 = (int)((str.length()/3.0)+0.66);
			int i;
			if (CodontoLetter(str.substring(0, 3)) == 'M'){
				i = 0;
			}else if (CodontoLetter(str.substring(1, 4)) == 'M'){
				i = 1;
			}else if (CodontoLetter(str.substring(2, 5)) == 'M'){
				i = 2;
			}else {i=0;}
				for (int j = 0; j<rby3; j++){
				String temp = str.substring((i+j*3), (i+j*3+3));
				char check = CodontoLetter(temp);
				out+= ((check=='X') ? "stop" : check);
				}
				return out;
				
			
		}
		else {return "Error";}
	}
	
	public String LetterstoRNA(String letters){
		char[] str = (letters.toUpperCase()).toCharArray();
		String out = "";
		for (int i = 0; i<str.length; i++){
			out += LettertoCodon(str[i]);
		}
		return out;
	}
	
	
	private String remove(String str, String one, String two, String three, String four){
		String val = (((str.replaceAll(one, "-")).replaceAll(two, "-")).replaceAll(three, "-")).replaceAll(four, "-");
		return val;
	}
		
	private char CodontoLetter (String Codon){
		// U - 0   C - 1  A - 2  G - 3
		int[] index = new int[3];
		for (int i = 0; i<3;i++){
			if (Codon.charAt(i)=='U'){
				index[i] = 0;
			} else if (Codon.charAt(i)=='C'){
				index[i] = 1;
			} else if (Codon.charAt(i)=='A'){
				index[i] = 2;
			} else if (Codon.charAt(i)=='G'){
				index[i] = 3;
			}
		}
		return letterTable[index[0]][index[1]][index[2]];
	}

	private String LettertoCodon(char letter){
		String str = "";
		str+=letter;
		str = str.toUpperCase();
		String[] set = codonTable.get(str);
		String out = RndfromArray(set);
		return out;
	}
	
	private String RndfromArray (String[] arr){
		int x = (int) (Math.random()*arr.length);
		String out = arr[x];
		return out;
	}

	private void setLetterTable(){
		letterTable [0][0][0] = 'F'; //UUU
		letterTable [0][0][1] = 'F'; //UUC
		letterTable [0][0][2] = 'L'; //UUA
		letterTable [0][0][3] = 'L'; //UUG
		
		letterTable [0][1][0] = 'S'; //UCU
		letterTable [0][1][1] = 'S'; //UCC
		letterTable [0][1][2] = 'S'; //UCA
		letterTable [0][1][3] = 'S'; //UCG
		
		letterTable [0][2][0] = 'Y'; //UAU
		letterTable [0][2][1] = 'Y'; //UAC
		letterTable [0][2][2] = 'X'; //UAA
		letterTable [0][2][3] = 'X'; //UAG
		
		letterTable [0][3][0] = 'C'; //UGU
		letterTable [0][3][1] = 'C'; //UGC
		letterTable [0][3][2] = 'X'; //UGA
		letterTable [0][3][3] = 'W'; //UGG
		
		//********************************
		
		letterTable [1][0][0] = 'L'; //CUU
		letterTable [1][0][1] = 'L'; //CUC
		letterTable [1][0][2] = 'L'; //CUA
		letterTable [1][0][3] = 'L'; //CUG
		
		letterTable [1][1][0] = 'P'; //CCU
		letterTable [1][1][1] = 'P'; //CCC
		letterTable [1][1][2] = 'P'; //CCA
		letterTable [1][1][3] = 'P'; //CCG
		
		letterTable [1][2][0] = 'H'; //CAU
		letterTable [1][2][1] = 'H'; //CAC
		letterTable [1][2][2] = 'Q'; //CAA
		letterTable [1][2][3] = 'Q'; //CAG
		
		letterTable [1][3][0] = 'R'; //CGU
		letterTable [1][3][1] = 'R'; //CGC
		letterTable [1][3][2] = 'R'; //CGA
		letterTable [1][3][3] = 'R'; //CGG

		//********************************
		
		letterTable [2][0][0] = 'I'; //AUU
		letterTable [2][0][1] = 'I'; //AUC
		letterTable [2][0][2] = 'I'; //AUA
		letterTable [2][0][3] = 'M'; //AUG
		
		letterTable [2][1][0] = 'T'; //ACU
		letterTable [2][1][1] = 'T'; //ACC
		letterTable [2][1][2] = 'T'; //ACA
		letterTable [2][1][3] = 'T'; //ACG
		
		letterTable [2][2][0] = 'N'; //AAU
		letterTable [2][2][1] = 'N'; //AAC
		letterTable [2][2][2] = 'K'; //AAA
		letterTable [2][2][3] = 'K'; //AAG
		
		letterTable [2][3][0] = 'S'; //AGU
		letterTable [2][3][1] = 'S'; //AGC
		letterTable [2][3][2] = 'R'; //AGA
		letterTable [2][3][3] = 'R'; //AGG

		//********************************
		
		letterTable [3][0][0] = 'V'; //GUU
		letterTable [3][0][1] = 'V'; //GUC
		letterTable [3][0][2] = 'V'; //GUA
		letterTable [3][0][3] = 'V'; //GUG
		
		letterTable [3][1][0] = 'A'; //GCU
		letterTable [3][1][1] = 'A'; //GCC
		letterTable [3][1][2] = 'A'; //GCA
		letterTable [3][1][3] = 'A'; //GCG
		
		letterTable [3][2][0] = 'D'; //GAU
		letterTable [3][2][1] = 'D'; //GAC
		letterTable [3][2][2] = 'E'; //GAA
		letterTable [3][2][3] = 'E'; //GAG
		
		letterTable [3][3][0] = 'G'; //GGU
		letterTable [3][3][1] = 'G'; //GGC
		letterTable [3][3][2] = 'G'; //GGA
		letterTable [3][3][3] = 'G'; //GGG
		
	}
	
	private void setCodonArr(){
		
		String[] A = {"GCU","GCC","GCA","GCG"};
		codonTable.put("A", A);
		String[] C = {"UGU","UGC"};
		codonTable.put("C", C);
		String[] D = {"GAU","GAC"};
		codonTable.put("D", D);
		String[] E = {"GAA","GAG"};
		codonTable.put("E", E);
		String[] F = {"UUU","UUU"};
		codonTable.put("F", F);
		String[] G = {"GGU","GGC","GGA","GGG"};
		codonTable.put("G", G);
		String[] H = {"CAU","CAC"};
		codonTable.put("H", H);
		String[] I = {"AUU","AUC","AUA"};
		codonTable.put("I", I);
		String[] K = {"AAA","AAG"};
		codonTable.put("K", K);
		String[] L = {"UUA","UUG","CUU","CUC","CUA","CUG"};
		codonTable.put("L", L);
		String[] M = {"AUG"};
		codonTable.put("M", M);
		String[] N = {"AAU","AAC"};
		codonTable.put("N", N);
		String[] P = {"CCU","CCC","CCA","CCG"};
		codonTable.put("P", P);
		String[] Q = {"CAA","CAG"};
		codonTable.put("Q", Q);
		String[] R = {"CGU","CGC","CGA","CGG","AGA","AGG"};
		codonTable.put("R", R);
		String[] S = {"UCU","UCC","UCA","UGC","AGU","AGC"};
		codonTable.put("S", S);
		String[] T = {"ACU","ACC","ACA","ACG"};
		codonTable.put("T", T);
		String[] V = {"GUU","GUC","GUA","GUG"};
		codonTable.put("V", V);
		String[] W = {"UGG"};
		codonTable.put("W", W);
		String[] X = {"UAA","UAG","UGA"};
		codonTable.put("X", X);
		String[] Y = {"UAU", "UAC"};
		codonTable.put("Y", Y);
		}

}
