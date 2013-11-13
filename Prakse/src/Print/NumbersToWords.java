package Print;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersToWords {

	// String[]
	// strWordsOfNumber={"viens","divi","trīs","četri","pieci","seši","septiņi","astoņi","deviņi","desmit",
	// "vienpadsmit","divpadsmit","trīspadsmit","četrpadsmit","piecpadsmit","sešpadsmit","septiņpadsmit","astoņpadsmit","deviņpadsmit",
	// "divdesmit","trīsdesmit","četrdesmit","piecdesmit","sešdesmit","septiņdesmit","astoņdesmit","deviņdesmit",
	// "simts","simti",
	// "tukstotis","tukstoši",
	// "miljons","miljoni"};
	ArrayList<String> wordsOfNumber = new ArrayList<String>(Arrays.asList(
			"viens", "divi", "trīs", "četri", "pieci", "seši", "septiņi",
			"astoņi", "deviņi", "desmit", "vienpadsmit", "divpadsmit",
			"trīspadsmit", "četrpadsmit", "piecpadsmit", "sešpadsmit",
			"septiņpadsmit", "astoņpadsmit", "deviņpadsmit", "divdesmit",
			"trīsdesmit", "četrdesmit", "piecdesmit", "sešdesmit",
			"septiņdesmit", "astoņdesmit", "deviņdesmit", // 26 element
			"simts", "simti", "tukstotis", "tukstoši", "miljons", "miljoni"));
	String word = "";
	ArrayList<Integer> constDecimals = new ArrayList<Integer>(Arrays.asList(
			1000000, 1000, 100, 10, 1));
	ArrayList<String> words = new ArrayList<>();
	ArrayList<String> words0 = new ArrayList<>();

	public ArrayList<String> getWordsOfNumber() {
		return wordsOfNumber;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public ArrayList<String> getWords0() {
		return words0;
	}
	public void clearAll(){
		words.removeAll(words);
		words0.removeAll(words0);
		word="";
	}

	// public String getWord() {
	// return word;
	// }

	int position = 0;

	public void getWordFromNumber(int number) {
		for (int x : constDecimals) {
			String word0 = "";
			if (number / x > 0) {
				int tmpX = x;
				int tmpNumber = number / x;
				int position = 0;
				while (tmpX > 9) {
					position++;
					tmpX /= 10;
					if (tmpNumber > 9) {
//						System.out.println(tmpNumber);
						if (tmpNumber - tmpNumber / 100 * 100 == 11) {
							tmpNumber = 2;
						} else {
							tmpNumber = tmpNumber - tmpNumber / 10 * 10;
						}
					}
				}
//				System.out.println(position);
				if (position > 5 && position <10) {
					position = 4;
				}
				if (position > 1) {
					if (tmpNumber == 1) {
						word0 = wordsOfNumber.get(26 + (position - 1) * 2 - 1)
								+ " ";
					} else {
						word0 = wordsOfNumber.get(26 + (position - 1) * 2)
								+ " ";
					}
				} else {
					word0 = "";
				}
				if (number < 20) {
					words.add(wordsOfNumber.get(number - 1));
					break;
				} else if (number < 100 && number > 19) {
					words.add(wordsOfNumber.get(20 + number / 10 - 3));
					number = number - number / 10 * 10;
				} else {
					getWordFromNumber(number / x);
					number -= (number / x) * x;
				}
			}
			if (!word0.equals("")) {
				words0.add(word0);
			}
		}
	}

	public String getWord(int number) {
		word="";
		getWordFromNumber(number);
		int i = 0;
		for (String s : words) {
			boolean dx = false;
			if (i < words0.size()) {
				for (int j = 19; j < 27; j++) {
					if (s.equals(wordsOfNumber.get(j))) {
						dx = true;
						break;
					}
				}
				if (!dx)
					word += s + " " + words0.get(i);
				else {
					word += s + " ";
				}
			} else {
				word += s + " ";
			}
			if (!dx)
				i++;
		}
		System.out.println(word);
		return word.toUpperCase();
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		int x = 101111161;
//		NumbersToWords n = new NumbersToWords();
//		System.out.println("word " + n.getWord(x));
//	}

}
