/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

/**
 * counter is the class of the remaining blocks counter.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     */
    public Counter() {
        this(0);
    }

    /**
     * constructor.
     *
     * @param startFrom is the number to start count from
     */
    public Counter(int startFrom) {
        this.counter = startFrom;
    }

    /**
     * adds number to current count.
     *
     * @param number a number to count
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtracts number from current count.
     *
     * @param number a number to subtract
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * gets current count.
     *
     * @return counter
     */
    public int getValue() {
        return this.counter;
    }
}
