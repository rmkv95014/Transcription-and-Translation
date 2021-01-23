package challengeFive;

import java.util.ArrayList;
import java.util.Scanner;

public class DNATranscription {

	public static void main(String[] args) 
	{
		String strand = askForGene(); //runs the askForGene() method, assigns the return value to variable strand
		char[] transcribedBases = transcribingDNA(strand);
		if(transcribedBases != null) translatingBases(transcribedBases, strand);
	}

	private static String askForGene()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a valid gene");
		String strand = scan.nextLine();
		return strand;
	}

	private static char[] transcribingDNA(String gene) 
	{
		char[] bases = gene.toLowerCase().toCharArray();
		char[] transcribedBases = new char[bases.length];
		
		for(int i = 0; i < bases.length; i++)
		{
			char base = bases[i];
			if(base != 'a' && base != 'c' && base != 'g' && base != 't') 
			{
				System.out.println("Not a valid gene"); 
				return null;
			}
			
			if(base == 'a') transcribedBases[i] = 'U';
			if(base == 'c') transcribedBases[i] = 'G';
			if(base == 'g') transcribedBases[i] = 'C';
			if(base == 't') transcribedBases[i] = 'A';
		}	
		System.out.println("The mRNA copy of the gene: " + gene.toUpperCase() + " would be " + new String(transcribedBases));
		return transcribedBases;
	}

	private static void translatingBases(char[] mRNA, String inputStrand)
	{
		ArrayList<AminoAcids> polypeptide = new ArrayList<AminoAcids>();
		String mRNAStrand = new String(mRNA);
		int ogstrandLen = mRNAStrand.length();
		int strandLen = ogstrandLen - ogstrandLen % 3;
		for(int i = 0; i < strandLen; i += 3)
		{
			String codon = mRNAStrand.substring(i, i + 3);
			System.out.println("The codon is " + codon);
			
			switch(codon)
			{
				case "UAG":
				case "UAA":
					polypeptide.add(AminoAcids.stop);
					break;
				case "UAC":
				case "UAU":
					polypeptide.add(AminoAcids.tyr);
					break;
				case "UCA":
				case "UCC":
				case "UCG":
				case "UCU":
					polypeptide.add(AminoAcids.ser);
					break;

				case "UGU":
				case "UGC":
					polypeptide.add(AminoAcids.cys);
					break;

				case "UGG":
					polypeptide.add(AminoAcids.trp);
					break;

				case "UGA":
					polypeptide.add(AminoAcids.stop);
					break;
				case "UUA":
				case "UUG":
					polypeptide.add(AminoAcids.leu);
					break;

				case "UUC":
				case "UUU":
					polypeptide.add(AminoAcids.phe);
					break;

					
				case "GAA":
				case "GAG":
					polypeptide.add(AminoAcids.glu);
					break;
				case "GAC":
				case "GAU":
					polypeptide.add(AminoAcids.asp);
					break;
				case "GCA":
				case "GCG":
				case "GCU":
				case "GCC" :
					polypeptide.add(AminoAcids.ala);
					break;
				case "GGA":
				case "GGC":
				case "GGG":
				case "GGU":
					polypeptide.add(AminoAcids.gly);
					break;
				case "GUA":
				case "GUC":
				case "GUG":
				case "GUU":
					polypeptide.add(AminoAcids.val);
					break;
				case "AAA":
				case "AAG":
					polypeptide.add(AminoAcids.lys);
					break;
				case "AAC":
				case "AAU":
					polypeptide.add(AminoAcids.asp);
					break;
				case "ACA":
				case "ACU":
				case "ACG":
				case "ACC":
					polypeptide.add(AminoAcids.thr);
					break;
				case "AGA":
				case "AGG":
					polypeptide.add(AminoAcids.arg);
					break;
				case "AGU":
				case "AGC":
					polypeptide.add(AminoAcids.ser);
					break;
				case "AUA":
				case "AUC":
				case "AUU":
					polypeptide.add(AminoAcids.ile);
					break;
				case "AUG":
					polypeptide.add(AminoAcids.met);
					break;
				case "CGG":
				case "CGA":
				case "CGC":
				case "CGU":
					polypeptide.add(AminoAcids.arg);
					break;
				case "CAA":
				case "CAG":
					polypeptide.add(AminoAcids.gln);
					break;
				case "CAU":
				case "CAC":
					polypeptide.add(AminoAcids.his);
					break;
				case "CCA":
				case "CCC":
				case "CCG":
				case "CCT":
					polypeptide.add(AminoAcids.pro);
					break;
				case "CUA":
				case "CUC":
				case "CUG":
				case "CUU":
					polypeptide.add(AminoAcids.leu);
					break;
			}
			System.out.println("The amino acid for the codon : " + codon + " would be " + polypeptide.get(polypeptide.size() - 1).toString());
			
		}
		System.out.println("The final amino acid chain is " + polypeptide.toString());
		ArrayList<AminoAcids> startAndStop = new ArrayList<AminoAcids>();
		startAndStop.add(AminoAcids.met);
		startAndStop.add(AminoAcids.stop);
		if(!polypeptide.containsAll(startAndStop))
		{
			System.out.println("WARNING: Your mRNA does not contain either the start codon AUG (Methionine) or any of the stop codons, or both");
		}
		
		if(ogstrandLen % 3 != 0)
		{
			System.out.println("WARNING: You also have lagging nucleotides that cannot be fit into a codon..being the " + inputStrand.substring(strandLen) +
					" nucleotide");
		}
	}
	
}
