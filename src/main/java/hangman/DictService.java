package hangman;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class DictService {

    private ArrayList<String> dictionary;
    private static final DictService INSTANCE = new DictService();

    private DictService() {
        dictionary = new ArrayList<String>();
    }

    public static DictService getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        DictService dictService = DictService.getInstance();
        dictService.importJson(2);
        for (String word : dictService.getDictionary()) {
            System.out.println(word);
        }
    }

    public void importJson(int numLetters) {
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader("dictionary.json");
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray wordList = (JSONArray) obj;
            dictionary = (ArrayList<String>) wordList.stream().filter(x -> x.toString().length() == numLetters).collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void clear() {
        this.dictionary.clear();
    }

    public int size () {
        return this.dictionary.size();
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    public ArrayList<String> reduceDictionary(String letter) {
        this.dictionary = (ArrayList<String>) this.dictionary.stream().filter(x -> !x.contains(letter)).collect(Collectors.toList());
        return this.dictionary;
    }

    public long countLetterInstances(String letter) {
        return this.dictionary.stream().filter(x -> x.contains(letter)).count();
    }

    public String mostCommonLetter() {
        Pair<Character, Long> max = new Pair(null, Long.MIN_VALUE);
        for (char letter = 'a'; letter <= 'z'; letter++) {
            long letterFrequency = countLetterInstances(String.valueOf(letter));
            if (letterFrequency > max.getValue()) {
                max = new Pair(letter,letterFrequency);
            }
        }
        return String.valueOf(max.getKey());
    }

    public String getRandomWord() {
        return this.dictionary.get(new Random().nextInt(dictionary.size()));
    }
}
