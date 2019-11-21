package hangman;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DictService {

    private ArrayList<String> dictionary;
    private static final DictService INSTANCE = new DictService();

    private DictService() {
        this.dictionary = new ArrayList<String>();
    }

    public static DictService getInstance() {
        return INSTANCE;
    }

    public void importJson(int numLetters) {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader("dictionary.json");
            Object obj = jsonParser.parse(reader);

            JSONArray wordList = (JSONArray) obj;
            this.dictionary = (ArrayList<String>) wordList.stream().filter(x -> x.toString().length() == numLetters).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clear() {
        this.dictionary.clear();
    }

    public int size() {
        return this.dictionary.size();
    }

    public ArrayList<String> getDictionary() {
        return this.dictionary;
    }

    public void setDictionary(ArrayList<String> dictionary) { // testing only
        this.dictionary = dictionary;
    }

    public ArrayList<String> reduceDictionary(String letter) { // remove words with this letter
        this.dictionary = (ArrayList<String>) this.dictionary.stream().filter(x -> !x.contains(letter)).collect(Collectors.toList());

        return this.dictionary;
    }

    public ArrayList<String> restrictDictionary(String letter) { // include only words with this letter
        this.dictionary = (ArrayList<String>) this.dictionary.stream().filter(x -> x.contains(letter)).collect(Collectors.toList());

        return this.dictionary;
    }

    public long countLetterInstances(String letter) {
        return this.dictionary.stream().filter(x -> x.contains(letter)).count();
    }

    public TreeMap<Long,String> getLetterFrequencies(){
        TreeMap<Long,String> map = new TreeMap<>();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            long letterFrequency = countLetterInstances(String.valueOf(letter));

            map.put(letterFrequency,String.valueOf(letter));
        }
        return map;
    }

    public TreeMap<Long,String> getLetterFrequencies(HashSet<String> guesses){
        TreeMap<Long,String> map = new TreeMap<>();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            if (!guesses.contains(String.valueOf(letter))) {
                long letterFrequency = countLetterInstances(String.valueOf(letter));
                map.put(letterFrequency, String.valueOf(letter));
            }
        }
        return map;
    }

    public String getRandomWord() {
        return this.dictionary.get(new Random().nextInt(dictionary.size()));
    }
}
