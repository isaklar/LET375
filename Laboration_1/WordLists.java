import java.io.*;
import java.util.*;

// Author(s): Isak Einler Larsson & Gabriel Lindeby
// Email: gablinde@chalmers.student.se & isaklar@chalmers.student.se
// Date: 2017-03-21

	public class WordLists {
		private Reader in = null;
		TreeMap<String,Integer> occurs;
		TreeMap<Integer, TreeSet<String>> freqOrder;
		File file;
		String input;
		PrintWriter writer;


	public WordLists(String inputFileName) {
	    occurs = new TreeMap<String, Integer>();
			freqOrder = new TreeMap<Integer, TreeSet<String>>();
				try
				{
					in = new FileReader(new File(inputFileName));
				}
				catch (IOException e)
				{
					System.out.println("Exception: " + e.getStackTrace());
				}
	}

	private boolean isPunctuationChar(char c) {
	    final String punctChars = ",.:;?!";
	    return punctChars.indexOf(c) != -1;
	}

	private String getWord() throws IOException {
		int state = 0;
		int i;
		String word = "";
		while ( true ) {
			i = in.read();
			char c = (char)i;
			switch ( state ) {
			case 0:
				if ( i == -1 )
					return null;
				if ( Character.isLetter( c ) ) {
					word += Character.toLowerCase( c );
					state = 1;
				}
				break;
			case 1:
				if ( i == -1 || isPunctuationChar( c ) || Character.isWhitespace( c ) )
					return word;
				else if ( Character.isLetter( c ) )
					word += Character.toLowerCase( c );
				else {
					word = "";
					state = 0;
				}
			}
		}
	}

	private String reverse(String s) {
			char [] in = s.toCharArray();
			int start = 0;
			int end = in.length-1;
			char temp;
				while(end>start){
					temp = in[start];
					in[start] = in[end];
					in[end] = temp;
					end--;
					start++;
			}
			String reverse = new String(in);
			return reverse;
	}

	private void computeWordFrequencies() {
          try
					{
						writer = new PrintWriter("alfaSorted.txt");

						while((input = this.getWord()) != null){
							if(!occurs.containsKey(input)){
								occurs.putIfAbsent(input,1);
							}else{
								occurs.put(input,occurs.get(input)+1);
							}
						}


						Iterator<String> set = occurs.keySet().iterator();
						String string;
						while(set.hasNext()){
							string = set.next();
							writer.println(string + " " + occurs.get(string));
						}
						writer.close();
					}
					catch (IOException e)
					{
						System.out.println("Exception: " + e.getStackTrace());
					}
	}

	private void computeFrequencyMap() {
		try{
			writer = new PrintWriter("frequencySorted.txt");
			int fq;
			String word = "";
			Iterator<String> it = occurs.descendingKeySet().iterator();
			while(it.hasNext() && word != null){
						word = it.next().toString();
						fq = occurs.get(word);

						if(freqOrder.containsKey(fq)){
								freqOrder.get(fq).add(word); // lägger värde tillsammans med key
						}else{
							TreeSet<String> tm = new TreeSet<String>();
							tm.add(word);
							freqOrder.putIfAbsent(fq, tm);
						}
				}

				Iterator<Integer> printIterator = freqOrder.descendingKeySet().iterator();
				while(printIterator.hasNext()){
							fq = printIterator.next();
							Iterator<String> setWord = freqOrder.get(fq).iterator();
							writer.println(fq + ": ");

							while(setWord.hasNext()){
											String aWord = setWord.next();
											writer.println("		" + aWord);
							}
				}
				writer.close();
			}catch (IOException e){
					e.printStackTrace();
			}
	}


	private void computeBackwardsOrder() {
			Set<String> setBackwords = new TreeSet<String>(new ReverseStringComparator());
			try{
					BufferedWriter out = new BufferedWriter(new FileWriter("backwardsSorted.txt"));

					for(String s : occurs.keySet()){
						setBackwords.add(s);
					}

					for(String s : setBackwords){
							out.write(s+"\n");
					}
					out.close();
			}catch (IOException e){
				e.printStackTrace();
			}
	}

	private class ReverseStringComparator implements Comparator<String> {
		@Override
		public int compare(String arg0, String arg1) {
			return ((reverse(arg0)).compareTo(reverse(arg1)));
		}
	}

	public static void main(String[] args) throws IOException {
		WordLists wl = new WordLists(args[0]);  // arg[0] contains the input file name
		wl.computeWordFrequencies();
		wl.computeFrequencyMap();
		wl.computeBackwardsOrder();
		System.out.println("Finished!");
	}
}
