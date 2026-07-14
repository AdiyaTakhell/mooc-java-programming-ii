
import java.util.ArrayList;

public class HashMap<K, V> {

    private ArrayList<Pair<K, V>>[] values;
    private int firstFreeIndex; // Tracks the total number of key-value pairs stored

    @SuppressWarnings("unchecked")
    public HashMap() {
        // Initialize internal bucket array with 32 empty slots
        this.values = new ArrayList[32];
        this.firstFreeIndex = 0;
    }

    // 1. Retrieval Method
    public V get(K key) {
        int hashValue = Math.abs(key.hashCode() % this.values.length);
        if (this.values[hashValue] == null) {
            return null;
        }

        ArrayList<Pair<K, V>> valuesAtIndex = this.values[hashValue];

        for (int i = 0; i < valuesAtIndex.size(); i++) {
            // Using standard ArrayList .get(i) instead of .value(i)
            if (valuesAtIndex.get(i).getKey().equals(key)) {
                return valuesAtIndex.get(i).getValue();
            }
        }

        return null;
    }

    // 2. Add / Update Method
    public void add(K key, V value) {
        ArrayList<Pair<K, V>> valuesAtIndex = getListBasedOnKey(key);
        int index = getIndexOfKey(valuesAtIndex, key);

        if (index < 0) {
            valuesAtIndex.add(new Pair<>(key, value));
            this.firstFreeIndex++;

            // If the map crosses a load factor threshold (75% full), scale up
            if (1.0 * this.firstFreeIndex / this.values.length > 0.75) {
                grow();
            }
        } else {
            valuesAtIndex.get(index).setValue(value);
        }
    }

    // Helper: Finds or creates the ArrayList bucket for a key
    private ArrayList<Pair<K, V>> getListBasedOnKey(K key) {
        int hashValue = Math.abs(key.hashCode() % this.values.length);
        if (this.values[hashValue] == null) {
            this.values[hashValue] = new ArrayList<>();
        }

        return this.values[hashValue];
    }

    // Helper: Locates index of a specific key within an ArrayList bucket
    private int getIndexOfKey(ArrayList<Pair<K, V>> myList, K key) {
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;
    }

    // 3. Growing & Re-hashing Logic
    @SuppressWarnings("unchecked")
    private void grow() {
        // Create a new bucket array that is double the size of the old one
        ArrayList<Pair<K, V>>[] newValues = new ArrayList[this.values.length * 2];

        // Loop through the old array's buckets
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == null) {
                continue;
            }

            // Copy each element out of the current old bucket
            for (int j = 0; j < this.values[i].size(); j++) {
                Pair<K, V> value = this.values[i].get(j);

                // Re-hash using the NEW array's length boundary
                int hashValue = Math.abs(value.getKey().hashCode() % newValues.length);
                if (newValues[hashValue] == null) {
                    newValues[hashValue] = new ArrayList<>();
                }

                newValues[hashValue].add(value);
            }
        }

        // Swap the old internal layout with our freshly redistributed large array
        this.values = newValues;
    }
}
