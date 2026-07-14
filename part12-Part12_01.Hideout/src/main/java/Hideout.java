
public class Hideout<T> {

    private T hiddenObject;

    // Parameterless constructor
    public Hideout() {
        this.hiddenObject = null;
    }

    // Puts an object into the hideout, replacing any existing one
    public void putIntoHideout(T toHide) {
        this.hiddenObject = toHide;
    }

    // Removes and returns the object, or returns null if empty
    public T takeFromHideout() {
        if (this.hiddenObject == null) {
            return null;
        }
        T previouslyHidden = this.hiddenObject;
        this.hiddenObject = null;
        return previouslyHidden;
    }

    // Checks if there is an object currently in the hideout
    public boolean isInHideout() {
        return this.hiddenObject != null;
    }
}
