package dictionary;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SaveableDictionary {

    private Map<String, String> dictionary;
    private String file;

    public SaveableDictionary() {
        this.dictionary = new HashMap<>();
    }

    public SaveableDictionary(String file) {
        this(); 
        this.file = file;
    }

    public boolean load() {
        try (Scanner fileReader = new Scanner(new File(this.file))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(":");   
                this.add(parts[0], parts[1]);
            }
            return true; 
        } catch (Exception e) {
            return false; 
        }
    }

    // New save method
    public boolean save() {
        // Try-with-resources automatically overwrites the file on initialization
        try (PrintWriter writer = new PrintWriter(new File(this.file))) {
            for (Map.Entry<String, String> entry : this.dictionary.entrySet()) {
                // Formats exactly as "key:value" line by line
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
            return true; // Changes successfully written to disk
        } catch (Exception e) {
            return false; // Returns false if the file path is invalid or unwritable
        }
    }

    public void add(String words, String translation) {
        if (!this.dictionary.containsKey(words) && !this.dictionary.containsValue(translation)) {
            this.dictionary.put(words, translation);
        }
    }

    public String translate(String word) {
        for (Map.Entry<String, String> check : this.dictionary.entrySet()) {
            if (check.getKey().equals(word)) {
                return check.getValue();
            }
            if (check.getValue().equals(word)) {
                return check.getKey();
            }
        }
        return null;
    }

    public void delete(String word) {
        if (this.dictionary.containsKey(word)) {
            this.dictionary.remove(word);
            return;
        }

        String keyToRemove = null;
        for (Map.Entry<String, String> check : this.dictionary.entrySet()) {
            if (check.getValue().equals(word)) {
                keyToRemove = check.getKey();
                break;
            }
        }

        if (keyToRemove != null) {
            this.dictionary.remove(keyToRemove);
        }
    }
}
