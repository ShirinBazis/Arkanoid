/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

/**
 * The Point program implements an application that creates an object of type point.
 */
public class Point {
    private static final double EPSILON = Math.pow(10, -10);

    private double x;
    private double y;


    /**
     * constructor.
     *
     * @param x is the x of the point
     * @param y is the y of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this function returns the distance of this point to the other point.
     *
     * @param other is the other point we want to check the distance to
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * this function returns true if the points are equal, false otherwise.
     *
     * @param other is the other point we want to check the distance to
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        // this checks if a-b is closer to 0 than epsilon, if true, then a probably equals b
        return (Math.abs(other.getX() - this.x) <= EPSILON) && (Math.abs(other.getY() - this.y) <= EPSILON);
    }

    /**
     * this function returns the x value of this point.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * this function returns the y value of this point.
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * set a new x to the point.
     *
     * @param newX is the new x
     */
    public void setX(double newX) {
        x = newX;
    }

    /**
     * set a new y to the point.
     *
     * @param newY is the new y
     */
    public void setY(double newY) {
        y = newY;
    }
}

