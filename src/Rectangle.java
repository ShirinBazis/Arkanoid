/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Rectangle is the class of the rectangle shape.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft is the upper left point of the rectangle
     * @param width     is the width left point of the rectangle
     * @param height    is the height left point of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * returns an array of all the edges of the rectangle.
     *
     * @return a line array of all the edges of the rectangle
     */
    public Line[] createEdgesArray() {
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + height);
        Point lowerLeft = new Point(upperLeft.getX(), lowerRight.getY());
        Line up = new Line(this.upperLeft, upperRight);
        Line right = new Line(upperRight, lowerRight);
        Line down = new Line(lowerRight, lowerLeft);
        Line left = new Line(lowerLeft, upperLeft);
        Line[] edges = {up, right, down, left};
        return edges;
    }

    /**
     * Returns a (possibly empty) List of intersection points with the specified line.
     *
     * @param line is the line which we check the collision points with
     * @return the list of all the intersection points with this line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Line[] edges = createEdgesArray();
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].intersectionWith(line) != null) {
                list.add(edges[i].intersectionWith(line));
            }
        }
        return list;
    }

    /**
     * returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * accessor.
     *
     * @return the color of the rectangle
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * sets the color of rectangle.
     *
     * @param c is the new color
     */
    public void setColor(Color c) {
        this.color = c;
    }
}
