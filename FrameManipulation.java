import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FrameManipulation {
    private final static HashMap<Character, Integer> letterScores = new HashMap<>();
    private final static ArrayList<Character> letter= new ArrayList<>();
    
    static {
// add entries to the letterScores map with the keys and their values
letterScores.put('A', 1);
letterScores.put('B', 8);
letterScores.put('C', 4);
letterScores.put('D', 4);
letterScores.put('E', 1);
letterScores.put('F', 8);
letterScores.put('G', 1);
letterScores.put('H', 8);
letterScores.put('I', 1);
letterScores.put('K', 2);
letterScores.put('L', 3);
letterScores.put('M', 3);
letterScores.put('N', 1);
letterScores.put('O', 10);
letterScores.put('P', 1);
letterScores.put('R', 2);
letterScores.put('S', 1);
letterScores.put('T', 1);
letterScores.put('W', 2);
letterScores.put('X', 8);
letterScores.put('Y', 10);
letterScores.put('Z', 10);

//add elements to the list
letter.add('A');
letter.add('B');
letter.add('C');
letter.add('D');
letter.add('E');
letter.add('F');
letter.add('G');
letter.add('H');
letter.add('I');
letter.add('K');
letter.add('L');
letter.add('M');
letter.add('N');
letter.add('O');
letter.add('P');
letter.add('R');
letter.add('S');
letter.add('T');
letter.add('W');
letter.add('X');
letter.add('Y');
letter.add('Z');
}

public static int getLetterScore(char letter) {
    // this method takes a character input, and returns the corresponding value from the letterScores map
    return letterScores.get(letter);
}

public static boolean isValid(String word, ArrayList<String> str) {
    // this method checks if the input word is present in the input ArrayList of strings
    return str.contains(word);
}

public static int calculateWordScore(String word) {
    // this method calculates the score of a word based on the scores of its individual letters
    int score = 0;
    for (int i = 0; i < word.length(); i++) {   
        score += getLetterScore(word.charAt(i));
    }
    return score;
}

public static String fill(ArrayList<Character> letters, int i){
    // this method takes an ArrayList of characters and an index as input, and returns a string of the form "<html>{letter}<sub>{score}</sub></html>"
    String str = null;
    while(i<letters.size()){
        str = "<html>" + letters.get(i)+ "<sub>" + getLetterScore(letters.get(i)) + "</sub></html>";
        break;
    }
    return str;
}

public static String fill(int i, ArrayList<String> letters){
    // this method takes an index and an ArrayList of strings as input, and returns the string at that index
    String str = null;
    while(i<letters.size()){
        str = letters.get(i);
        break;
    }
    return str;
}

public static String fill_random(){
    // this method returns a random letter from the list of letters
    Random random = new Random();
    String str =  fill(letter,random.nextInt(22));
    return str;
}

public static String fill_question_mark(String text){
    // this method takes a string as input and returns a string of the form "<html>{text}<sub>{score}</sub></html>"
    String str;           
    str = "<html>" + text + "<sub>" + getLetterScore(text.charAt(0)) + "</sub></html>";
    return str;
}

public static ArrayList<String> get_capital_letters(ArrayList<String> check){
    // this method takes an ArrayList of strings as input, and returns an ArrayList of all the capital letters in the input list
    ArrayList<String> c = new ArrayList<>();
    int i=0;
    while(i<check.size()){
        for(int index=0; index<check.get(i).length(); index++){
            if (Character.isUpperCase(check.get(i).charAt(index))){
                c.add(Character.toString(check.get(i).charAt(index)));
            }
        }
        i++;
    }
    return c;
}
}