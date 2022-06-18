/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package counter;

/**
 * Counte class.
 */
public class Counter {
    private int number;

    /**
     * Counte constructor.
     * @param number of the conter.
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * add number to current count.
     * @param number add to counter.
     */
    public void increase(int number) {
        this.number = this.number + number;
    }

    /**
     * subtract number from current count.
     * @param number decrease.
     */
    public void decrease(int number) {
        this.number = this.number - number;
    }

    /**
     * getValue, get current count.
     * @return the nember.
     */
    public int getValue() {
        return number;
    }
}