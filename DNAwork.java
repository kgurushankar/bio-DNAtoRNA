public class DNAwork {

	// Note : X means STOP
	// U - 0 	C - 1	A - 2	G - 3
	private char[][][] table = new char[4][4][4];
	public DNAwork(){
		tablesetup();
	}
	
	private void tablesetup(){
		table [0][0][0] = 'F'; //UUU
		table [0][0][1] = 'F'; //UUC
		table [0][0][2] = 'L'; //UUA
		table [0][0][3] = 'L'; //UUG
		
		table [0][1][0] = 'S'; //UCU
		table [0][1][1] = 'S'; //UCC
		table [0][1][2] = 'S'; //UCA
		table [0][1][3] = 'S'; //UCG
		
		table [0][2][0] = 'Y'; //UAU
		table [0][2][1] = 'Y'; //UAC
		table [0][2][2] = 'X'; //UAA
		table [0][2][3] = 'X'; //UAG
		
		table [0][3][0] = 'C'; //UGU
		table [0][3][1] = 'C'; //UGC
		table [0][3][2] = 'X'; //UGA
		table [0][3][3] = 'W'; //UGG
		
		//***************************
		
		table [1][0][0] = 'L'; //CUU
		table [1][0][1] = 'L'; //CUC
		table [1][0][2] = 'L'; //CUA
		table [1][0][3] = 'L'; //CUG
		
		table [1][1][0] = 'P'; //CCU
		table [1][1][1] = 'P'; //CCC
		table [1][1][2] = 'P'; //CCA
		table [1][1][3] = 'P'; //CCG
		
		table [1][2][0] = 'H'; //CAU
		table [1][2][1] = 'H'; //CAC
		table [1][2][2] = 'Q'; //CAA
		table [1][2][3] = 'Q'; //CAG
		
		table [1][3][0] = 'R'; //CGU
		table [1][3][1] = 'R'; //CGC
		table [1][3][2] = 'R'; //CGA
		table [1][3][3] = 'R'; //CGG

		//***************************
		
		table [2][0][0] = 'I'; //AUU
		table [2][0][1] = 'I'; //AUC
		table [2][0][2] = 'I'; //AUA
		table [2][0][3] = 'M'; //AUG
		
		table [2][1][0] = 'T'; //ACU
		table [2][1][1] = 'T'; //ACC
		table [2][1][2] = 'T'; //ACA
		table [2][1][3] = 'T'; //ACG
		
		table [2][2][0] = 'N'; //AAU
		table [2][2][1] = 'N'; //AAC
		table [2][2][2] = 'K'; //AAA
		table [2][2][3] = 'K'; //AAG
		
		table [2][3][0] = 'S'; //AGU
		table [2][3][1] = 'S'; //AGC
		table [2][3][2] = 'R'; //AGA
		table [2][3][3] = 'R'; //AGG

		//***************************
		
		table [3][0][0] = 'V'; //GUU
		table [3][0][1] = 'V'; //GUC
		table [3][0][2] = 'V'; //GUA
		table [3][0][3] = 'V'; //GUG
		
		table [3][1][0] = 'A'; //GCU
		table [3][1][1] = 'A'; //GCC
		table [3][1][2] = 'A'; //GCA
		table [3][1][3] = 'A'; //GCG
		
		table [3][2][0] = 'D'; //GAU
		table [3][2][1] = 'D'; //GAC
		table [3][2][2] = 'E'; //GAA
		table [3][2][3] = 'E'; //GAG
		
		table [3][3][0] = 'G'; //GGU
		table [3][3][1] = 'G'; //GGC
		table [3][3][2] = 'G'; //GGA
		table [3][3][3] = 'G'; //GGG
		
	}
	
	public static void main(String[] args) {
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
	
	private String remove(String str, String one, String two, String three, String four){
		String val = (((str.replaceAll(one, "-")).replaceAll(two, "-")).replaceAll(three, "-")).replaceAll(four, "-");
		return val;
	}
	

			
	public char CodontoLetter (String Codon){
		// U - 0 	C - 1	A - 2	G - 3
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
		return table[index[0]][index[1]][index[2]];
	}

}
