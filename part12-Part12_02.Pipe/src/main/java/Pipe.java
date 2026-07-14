
import java.util.ArrayList;
import java.util.List;

public class Pipe<T> {

    private List<T> pipe;

    // Parameterless constructor
    public Pipe() {
        this.pipe = new ArrayList<>();
    }

    // Adds a new value to the end of the pipe
    public void putIntoPipe(T value) {
        this.pipe.add(value);
    }

    // Removes and returns the oldest value (index 0)
    public T takeFromPipe() {
        if (this.pipe.isEmpty()) {
            return null;
        }
        // Remove and return the very first element
        return this.pipe.remove(0);
    }

    // Returns true if the pipe contains elements, false if empty
    public boolean isInPipe() {
        return !this.pipe.isEmpty();
    }
}
