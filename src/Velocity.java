/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

/**
 * The Velocity program implements an application that creates an object Velocity,
 * that specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private static final double THIRTY_PERCENT = 0.3;

    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx is the x of the change in position
     * @param dy is the y of the change in position
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * this function returns a new velocity presented by dx and dy.
     *
     * @param angle is the angle of the velocity
     * @param speed is the speed of the velocity
     * @return a new velocity presented by dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        double dy = -speed * Math.cos(angle);
        return new Velocity(dx, dy);
    }

    /**
     * this function returns the angle of the velocity by calculating according to dx and dy.
     *
     * @return the angle of the velocity
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(dx, -dy));
    }

    /**
     * this function returns the speed of the velocity by calculating according to dx and dy.
     *
     * @return the speed of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * this function returns the x value of this point.
     *
     * @return the x value of this point
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * this function returns the y value of this point.
     *
     * @return the y value of this point
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * changes the dx value of this point to a new one.
     *
     * @param newDx is the new dy
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * changes the dy value of this point to a new one.
     *
     * @param newDy is the new dy
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * this function takes a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p is a point to apply
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * calculates the ratio of 2 lines.
     *
     * @param shorter is the smaller line
     * @param longer  is the longer line
     * @return the ratio of the 2 lines
     */
    public double calculateRatio(Line shorter, Line longer) {
        //the smaller line (null case) / the longer line (hit point case)
        return THIRTY_PERCENT * (shorter.length() / longer.length());
    }

    /**
     * apply the point to a close point.
     *
     * @param p  is a point to apply
     * @param l1 is the first line
     * @param l2 is the second line
     * @return a new point with position (x + ratio * dx, y + ratio * dy)
     */
    public Point applyToClosePoint(Point p, Line l1, Line l2) {
        double r = calculateRatio(l1, l2);
        return new Point(p.getX() + r * dx, p.getY() + r * dy);
    }
}