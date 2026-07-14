
public class List<Type> {

    private Type[] values;
    private int firstFreeIndex;

    @SuppressWarnings("unchecked")
    public List() {
        this.values = (Type[]) new Object[10];
        this.firstFreeIndex = 0;
    }

    public void add(Type value) {
        if (this.firstFreeIndex == this.values.length) {
            grow();
        }
        this.values[this.firstFreeIndex] = value;
        this.firstFreeIndex++;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int newSize = this.values.length + this.values.length / 2;
        Type[] newValues = (Type[]) new Object[newSize];
        System.arraycopy(this.values, 0, newValues, 0, this.values.length);
        this.values = newValues;
    }

    public boolean contains(Type value) {
        return indexOfValue(value) >= 0;
    }

    public void remove(Type value) {
        boolean found = false;
        for (int i = 0; i < this.firstFreeIndex; i++) {
            if (found) {
                this.values[i - 1] = this.values[i];
            } else if (value == this.values[i] || (this.values[i] != null && this.values[i].equals(value))) {
                this.firstFreeIndex--;
                found = true;
                // Shift the current item left immediately if found
                if (i < this.firstFreeIndex + 1 && i > 0) {
                    this.values[i - 1] = this.values[i];
                }
            }
        }
        // Clear the now-unused last element to prevent memory leaks
        if (found) {
            this.values[this.firstFreeIndex] = null;
        }
    }

    public int indexOfValue(Type value) {
        for (int i = 0; i < this.firstFreeIndex; i++) {
            if (value == this.values[i] || (this.values[i] != null && this.values[i].equals(value))) {
                return i;
            }
        }
        return -1;
    }

    // Fixed missing size method
    public int size() {
        return this.firstFreeIndex;
    }

    // Fixed missing value retrieval method
    public Type value(int index) {
        if (index < 0 || index >= this.firstFreeIndex) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return this.values[index];
    }

    // Added a main method to properly execute your test code
    public static void main(String[] args) {
        List<String> myList = new List<>();
        myList.add("hello");
        myList.add("world");

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.value(i));
        }
    }
}
