// Author(s):Gabriel Lindeby Isak Einler Larsson
// Email:gablinde@student.chalmers.se, isaklar@student.chalmers.se
// Date: 2017-03-22
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class AngloTrainer {
	ArrayList<String> words;
	HashSet<String> possibleWords;
	Random randomGenerator;
	int maxLetters = 0;

	public AngloTrainer(String dictionaryFile) throws IOException {
	  	words = new ArrayList<String>();
			randomGenerator = new Random();
			loadDictionary(dictionaryFile);
			flow();
	}

	private void flow(){
		boolean wrongAnswer = false;
		String letters = randomLetters(maxLetters);
		generatePossibleWords(letters);
		System.out.println("the random letters are: "+letters+".\nTry to build as many words from these letters as you can!");
		Scanner sc = new Scanner(System.in);
		while(!wrongAnswer){
			if(possibleWords.contains(sc.nextLine())){
				System.out.println("OK!");
			}
			else{
				System.out.println("Your suggestion was not found in the dictionary.\nI found:");
				listPossibleWords();
				wrongAnswer = true;
			}
		}
	}

	private void listPossibleWords(){
		Iterator<String> it = possibleWords.iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}

	private void generatePossibleWords(String letters){
		possibleWords = new HashSet<String>();
		for(String word: words){
			if(includes(sort(letters),sort(word)))
				possibleWords.add(word);
		}
	}

	// use this to verify loadDictionary
	private void dumpDict() {
	  	for(String word: words)
				System.out.println(word);
	}

	private void loadDictionary( String fileName ) throws IOException{
		int wordCount = 0;
		Scanner sc = new Scanner(new File(fileName));
		while(sc.hasNextLine()){
			String word = sc.nextLine();
			words.add(word);
			wordCount++;
			if(maxLetters < word.length())
				maxLetters = word.length();
		}
		System.out.println(""+wordCount+" words loaded from "+fileName);
	}

	private String randomLetters( int length ) {
	    // this makes vovels a little more likely
	    String letters = "aabcdeefghiijklmnoopqrstuuvwxyyz";
	    StringBuffer buf = new StringBuffer(length);
	    for ( int i = 0; i < length; i++ )
		    buf.append( letters.charAt(randomGenerator.nextInt(letters.length())));

	    return buf.toString();
	}

	private String sort(String s){
		char[] charArray = s.toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}


	/* Def. includes
	 * Let #(x,s) = the number of occurrences of the charcter x in the string s.
	 * includes(a,b) holds iff for every character x in b, #(x,b) <= #(x,a)
	 *
	 * A neccessary precondition for includes is that both strings are sorted
	 * in ascending order.
	 */
	private boolean includes( String a, String b ) {
		if ( b == null || b.length() == 0 )
			return true;
		else if ( a == null || a.length() == 0 )
			return false;

		//precondition: a.length() > 0 && b.length() > 0
		int i = 0, j = 0;
		while ( j < b.length() ) {
			if (i >= a.length() || b.charAt(j) < a.charAt(i))
				return false;
			else if (b.charAt(j) == a.charAt(i)) {
				i++; j++;
			} else if (b.charAt(j) > a.charAt(i))
				i++;
		}
		//postcondition: j == b.length()
		return true;
	}

     // This is just for demonstration purposes.
	private void testIncludes() {
		//                                            expected value
		System.out.println(includes("abc",""));		//t
		System.out.println(includes("","abc"));		//f
		System.out.println(includes("abc","abc"));	//t
		System.out.println(includes("abc","bcd"));	//f
		System.out.println(includes("abc","a"));	//t
		System.out.println(includes("abc","b"));	//t
		System.out.println(includes("abc","c"));	//t
		System.out.println(includes("abc","ab"));	//t
		System.out.println(includes("abc","bc"));	//t
		System.out.println(includes("abc","ac"));	//t
		System.out.println(includes("abc","abcd"));	//f
		System.out.println(includes("abc","abd"));	//f
		System.out.println(includes("abc","d"));	//f
		System.out.println(includes("",""));		//t
		System.out.println(includes("abc","ca"));	//f
		System.out.println(includes("abc","bac"));	//f
		System.out.println(includes("abc","abbc"));	//f
		System.out.println(includes("abbc","abc"));	//t
		System.out.println(includes(null,null));    //t
		System.out.println(includes(null,""));	    //t
		System.out.println(includes(null,"abc"));	//f
		System.out.println(includes("",null));		//t
		System.out.println(includes("abc",null));   //t
	}

    public static void main(String[] args) {
			try{
				AngloTrainer a = new AngloTrainer("dictionary.txt");

			}catch(IOException e){
				System.out.println("problems with loading dictionary.txt");
			}

    }
}
