/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import java.util.List;

/**
 * Line is the class of lines.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructors.
     *
     * @param start the start x and y of the point
     * @param end   the end x and y of the point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructors.
     *
     * @param x1 the start x of the point
     * @param y1 the start y of the point
     * @param x2 the start x of the end
     * @param y2 the start y of the end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        // func = new Function(start, end);
    }

    /**
     * this function returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * this function returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * this function returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }


    /**
     * this function returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * this function returns the smaller point of the line.
     *
     * @return the smaller point of the line
     */
    public Point smaller() {
        // return the point with the smaller x
        if (this.start.getX() < this.end.getX()) {
            return new Point(this.start.getX(), this.start.getY());
        }
        if (this.start.getX() > this.end.getX()) {
            return new Point(this.end.getX(), this.end.getY());
        }
        // if the x's are equal- return the point with the smaller y
        if (this.start.getX() == this.end.getX()) {
            if (this.start.getY() < this.end.getY()) {
                return new Point(this.start.getX(), this.start.getY());
            }
        }
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * this function returns the bigger point of the line.
     *
     * @return the bigger point of the line
     */
    public Point bigger() {
        // if start is the smaller- return end
        if (this.start.equals(start())) {
            return this.end;
        }
        return this.start;
    }

    /**
     * this function returns true if one of the lines or both of them don't
     * have curvature, false otherwise.
     *
     * @param other is the line we want to check if the current line intersection with
     * @return true if one of the lines or both of them don't have curvature, false otherwise
     */
    private boolean areMsExist(Line other) {
        return (((this.start.getX() == this.end.getX()) && (other.start().getX() == other.end().getX()))
                || ((this.start.getX() == this.end.getX()) && (other.start().getX() != other.end().getX()))
                || ((this.start.getX() != this.end.getX()) && (other.start().getX() == other.end().getX())));
    }

    /**
     * returns the curvature m of the line.
     *
     * @return the curvature m of the line
     */
    private double calculateM() {
        double m = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX());
        if (Double.isInfinite(m) && m < 0) {
            m = -m;
        }
        return m;
    }

    /**
     * returns the constant b of the line.
     *
     * @param m is the curvature of the line
     * @return is the constant b of the line
     */
    private double calculateConstantB(double m) {
        return this.start().getY() - this.start().getX() * m;

    }

    /**
     * returns the y of the x on this line.
     *
     * @param x is the x of the point which we check if on the line
     * @param m is the curvature of the line
     * @param b is the constant b of the line
     * @return the y of the x on this line
     */
    private double calculateY(double x, double m, double b) {
        return m * x + b;
    }

    /**
     * this function returns true if the smaller y of the first line is between the points of the second line and the
     * smaller y of the second line is between the points of the first line, false otherwise.
     *
     * @param bigY1    is the bigger y of the first line
     * @param littleY1 is the smaller y of the first line
     * @param bigY2    is the bigger y of the second line
     * @param littleY2 is the smaller y of the second line
     * @return true if the smaller y of the first line is between
     * the points of the second line and the smaller y of the second line is between the points of the first line,
     * false otherwise
     */
    private boolean isMergeInfinityCase(double bigY1, double littleY1, double bigY2, double littleY2) {
        return (littleY1 <= bigY2 && littleY1 >= littleY2) || (littleY2 <= bigY1 && littleY2 >= littleY1);
    }

    /**
     * this function returns true if the two lines merge with each other, false otherwise.
     *
     * @param other is the line we want to check if the current line intersection with
     * @return true if the two lines merge with each other, false otherwise
     */
    private boolean isMerge(Line other) {
        //if "this" and "other" lines are the same line
        if (this.equals(other)) {
            return true;
        }
        //if m is infinity (there is no curvature)
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX() && this.start.getX()
                == other.end.getX()) {
            if (this.start.getY() > this.end.getY() && other.start.getY() > other.end.getY()) {
                return isMergeInfinityCase(this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY());
            }
            if (this.end.getY() > this.start.getY() && other.end.getY() > other.start.getY()) {
                return isMergeInfinityCase(this.end.getY(), this.start.getY(), other.end.getY(), other.start.getY());
            }
            if (this.end.getY() > this.start.getY() && other.start.getY() > other.end.getY()) {
                return isMergeInfinityCase(this.end.getY(), this.start.getY(), other.start.getY(), other.end.getY());
            }
            if (this.start.getY() > this.end.getY() && other.end.getY() > other.start.getY()) {
                return isMergeInfinityCase(this.start.getY(), this.end.getY(), other.end.getY(), other.start.getY());
            }
        }
        //if only one of the lines has infinity m, then m is different and therefore they're not merge
        if (areMsExist(other)) {
            return false;
        }
        double m1 = calculateM();
        double m2 = other.calculateM();
        if (m1 == m2) {
            return (this.start().getX() <= other.end().getX() && this.start().getX() >= other.start().getX())
                    || (this.end().getX() <= other.end().getX() && this.end().getX() >= other.start().getX());
        }
        return false;
    }

    /**
     * compares between this function and the other for an intersection point.
     *
     * @param other a second function
     * @return the intersection point
     */
    private Point getIntersectionPoint(Line other) {
        // checks if both function have the same curvature
        if (this.calculateM() == other.calculateM()) {
            return null;
        }
        double m1 = calculateM();
        double m2 = other.calculateM();
        double b1 = calculateConstantB(m1);
        double b2 = other.calculateConstantB(m2);
        // calculates the x of the intersection
        double foundX = (b1 - b2) / (m2 - m1);
        // calculates the y of the intersection
        double foundY = calculateY(foundX, m1, b1);
        // returns the intersection point
        return new Point(foundX, foundY);
    }

    /**
     * this function returns true if the two lines intersecting, false otherwise.
     *
     * @param other is the line we want to check if the current line intersection with
     * @return true if the two lines intersecting, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // if the lines are merge- they're intersecting
        if (this.isMerge(other)) {
            return true;
        }
        // if the two lines are actually points and are equal
        if (start.equals(end) && other.start().equals(other.end()) && start.equals(other.start())) {
            return true;
        }
        // if one of the lines is actually a point
        if (this.start.equals(this.end) || other.start().equals(other.end())) {
            // if the point is on the other line- they're intersecting
            return this.isPointOnLine(other.start()) || other.isPointOnLine(this.start);
        }
        // if the two lines have the same curvature
        if (this.calculateM() == other.calculateM()) {
            // if this line ends when the other line start, or the opposite- they're intersecting
            return (this.bigger().equals(other.smaller()) || this.smaller().equals(other.bigger()));
        }

        // if one of the lines has no curvature
        if (Double.isInfinite(this.calculateM()) || Double.isInfinite(other.calculateM())) {
            // calculates the y of this start x, by the curvature and the constant of the other line
            double yThis = calculateY(start().getX(), other.calculateM(),
                    other.calculateConstantB(other.calculateM()));
            // calculates the y of the other start x, by the curvature and the constant of this line
            double yOther = calculateY(other.start().getX(), this.calculateM(), this.calculateConstantB(calculateM()));
            Point thisXPoint = new Point(this.start.getX(), yThis);
            Point otherXPoint = new Point(other.start().getX(), yOther);
            // returns if one of the points is on the other line
            return (this.isPointOnLine(thisXPoint) && other.isPointOnLine(thisXPoint))
                    || (this.isPointOnLine(otherXPoint) && other.isPointOnLine(otherXPoint));
        }
        // the intersection point of the lines
        Point point = this.getIntersectionPoint(other);
        // if the point is on the two lines- the lines are intersecting
        return this.isPointOnLine(point) && other.isPointOnLine(point);
    }

    /**
     * this function returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other is the line we want to check if the current line intersection with
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // checks if the lines intersect and not merge
        if (!this.isMerge(other) && this.isIntersecting(other)) {
            // if other line is actually a point- it's the intersection point with this line
            if (other.start().equals(other.end())) {
                return other.start();
            }
            // if this line is actually a point- it's the intersection point with other line
            if (this.start.equals(this.end)) {
                return this.start;
            }
            // if the two lines have the same curvature
            if (this.calculateM() == other.calculateM()) {
                // if this line ends where the other line starts- the intersection point is the end of this line
                if (this.bigger().equals(other.smaller())) {
                    return this.bigger();
                }
                return this.smaller();
            }

            /* if the other line has no curvature- return the intersection point by the calculating of the curvature
            and the constant of this line*/
            if (Double.isInfinite(other.calculateM())) {
                return new Point(other.start().getX(), this.calculateY(other.start().getX(), this.calculateM(),
                        this.calculateConstantB(this.calculateM())));
            }

            /* if this line has no curvature- return the intersection point by the calculating of the curvature
            and the constant of the other this line*/
            if (Double.isInfinite(this.calculateM())) {
                return new Point(this.start.getX(), other.calculateY(start().getX(), other.calculateM(),
                        other.calculateConstantB(other.calculateM())));
            }
            // returns the intersection point of the lines
            return getIntersectionPoint(other);
        }
        return null;
    }


    /**
     * this function returns true if the lines are equal, false otherwise.
     *
     * @param other is the line we want to check if the current line intersection with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return other.smaller().equals(this.smaller()) && other.bigger().equals(this.bigger());
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect is the rectangle of the
     * @return the closest intersection point to the start of the line, or null- if there is no intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // if there is no intersection point
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.size() != 0) {
            Point closestP = intersectionPoints.get(0);

            /* if there are one or more intersection points- return the closest one to the start of the line*/
            for (int i = 1; i < intersectionPoints.size(); i++) {
                Point currentP = intersectionPoints.get(i);
                if (start.distance(currentP) < start.distance(closestP)) {
                    closestP = currentP;
                }
            }
            return closestP;
        }
        return null;
    }

    /**
     * gets a point and checks if the point is on this line.
     *
     * @param p is the point we want to check
     * @return true if the point is on this line, false otherwise
     */
    public boolean isPointOnLine(Point p) {
        // if this line is actually a point
        if (this.start().getX() == this.end().getX()) {
            return this.start().getX() == p.getX() && middle().distance(p) <= length() / 2;
        }
        // check if the point is on this line
        double y = calculateY(p.getX(), this.calculateM(), this.calculateConstantB(calculateM()));
        Point newPoint = new Point(p.getX(), y);
        return newPoint.equals(p) && (middle().distance(newPoint) <= length() / 2);
    }
}