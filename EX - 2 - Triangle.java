/**
 *
 *This class represents a triangle in the Euclidean space.
 *
 *@author Gal Ben Artzi
 */

public class Triangle {
    private Point _point1;
    private Point _point2;
    private Point _point3;
    static final double EPSILON = 0.001;

    /**
     * Create  a new Triangle (default constructor) from 3 default Points:(1,0),(-1,0),(0,1)
     */
    public Triangle() {
        setDefaultValue();
    }

    /**
     * Create a new Triangle (from 3 points)
     * @param p1 The first point
     * @param p2 The second point
     * @param p3 The third point
     */
    public Triangle(Point p1, Point p2, Point p3) {

        if (isValid(p1, p2, p3)){
            _point1 = new Point(p1);
            _point2 = new Point(p2);
            _point3 = new Point(p3);
        }
        else setDefaultValue();
    }

    /**
     * Create   a new Triangle (from 6 reals)
     * @param x1 The x coordinate of the first Point
     * @param y1 The y coordinate of the first Point
     * @param x2 The x coordinate of the second Point
     * @param y2 The y coordinate of the second Point
     * @param x3 The x coordinate of the third Point
     * @param y3 The y coordinate of the third Point
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        _point1 = new Point(x1, y1);
        _point2 = new Point(x2, y2);
        _point3 = new Point(x3, y3);
        if (!isValid(_point1, _point2, _point3))
            setDefaultValue();
    }

    /**
     * Copy constructor Creates a new triangle identical to the given triangle
     * @param other The triangle to be copied
     */
    public Triangle(Triangle other) {
        this(other._point1, other._point2, other._point3);
    }

    /**
     * Returns the x coordinate
     * @return The first point of the triangle
     */
    public Point getPoint1() {
        return new Point(_point1);
    }

    /**
     * Returns the x coordinate
     * @return The second point of the triangle
     */
    public Point getPoint2() {
        return new Point(_point2);
    }

    /**
     * Returns the x coordinate
     * @return The third point of the triangle
     */
    public Point getPoint3() {
        return new Point(_point3);
    }

    /**
     * Sets the first point of the triangle.
     * @param The new first point
     */
    public void setPoint1(Point p) {
        if (isValid(p, this._point2, this._point3))
            this._point1 = new Point(p);
    }

    /**
     * Sets the second point of the triangle.
     * @param The new second point
     */
    public void setPoint2(Point p) {
        if (isValid(this._point1,p,this._point3))
            this._point2 = new Point(p);
    }

    /**
     * Sets the third point of the triangle.
     * @param The new third point
     */
    public void setPoint3(Point p) {
        if (isValid(this._point1, this._point2, p))
            this._point3 = new Point(p);
    }

    /**
     * Checks if 3 points make a valid triangle. A triangle is valid if no sum of any two sides equals the third side (with precision EPSILON)
     * @param p1 The first point
     * @param p2 The second point
     * @param p3 The third point
     * @return True if the triangle(p1,p2,p3) is valid
     */
    public boolean isValid(Point p1, Point p2, Point p3) {
        return (!(Math.abs(getOtherSides(p1, p2, p3) - getMaxSide(p1, p2, p3)) < EPSILON));
    }

    /**
     * Returns a String representation of thetTriangle. The format of the string should be: {(x1,y1),(x2,y2),(x3,y3)}
     * @return A String representation of the triangle
     */
    public String toString() {
        return "{" + _point1.toString() + "," + _point2.toString() + "," + _point3.toString() + "}";
    }

    /**
     * Returns the triangle's perimeter
     * @return  The triangle's perimeter
     */
    public double getPerimeter() {
        return (this.getSide1() + this.getSide2() + this.getSide3());
    }

    /**
     * Returns the triangle's area (using Heron's formula).
     * @return  The triangle's area
     */
    public double getArea() {
        double s = this.getPerimeter() / 2;
        return (Math.sqrt(s * (s - this.getSide1()) * (s - getSide2()) * (s - getSide3())));
    }

    /**
     * Checks if the triangle is an isosceles triangle(with precision EPSILON).
     * @return True if the triangle is an isosceles triangle
     */
    public boolean isIsosceles() {
        return (Math.abs(this.getSide1() - this.getSide2()) < EPSILON || Math.abs(this.getSide1() - this.getSide3()) < EPSILON || Math.abs(this.getSide2() - this.getSide3()) < EPSILON);
    }

    /**
     * Checks if the triangle is a right-angled triangle.is an isosceles triangle(with precision EPSILON).
     * @return True if the triangle is a right-angled triangle
     */
    public boolean isPythagorean() {
        return (Math.abs(this.getMaxSide(this._point1, this._point2, this._point3)
                - (Math.sqrt(Math.pow(this.getMinSide(this._point1, this._point2, this._point3), 2) +
                        Math.pow(this.getMidSide(this._point1, this._point2, this._point3), 2)))) < EPSILON);

    }

    /**
     * Checks if the triangle is in a given circle
     * @param x The x value of the point which is the center of the circle
     * @param y The y value of the point which is the center of the circle
     * @param r The radius of the circle
     * @return True if the triangle is in a given circle.
     */
    public boolean isContainedInCircle(double x, double y, double r) {
        Point center = new Point(x, y);
        return (this._point1.distance(center) - r < EPSILON &&
            this._point2.distance(center) - r < EPSILON &&
            this._point3.distance(center) - r < EPSILON);
    }

    /**
     * Returns the highest vertex of the triangle, If there are two highest points it will return the left one between them
     * @return  The highest Vertex
     */
    public Point highestPoint() {
        if (this._point1.isAbove(this._point2)) {
            if (this._point1.isAbove(this._point3))
                return new Point(_point1);
            else if (this._point1.getY() == this._point3.getY()) {
                if (this._point1.isLeft(_point3))
                    return new Point(_point1);
                else
                    return new Point(_point3);
            }
            else return new Point(_point3);
        }
        else if (this._point1.getY() == this._point2.getY()){
            if (this._point1.isAbove(_point3)){
                if (this._point1.isLeft(_point2))
                    return new Point(_point1);
                else return new Point(_point2);
            }
            else return new Point(_point3);
        }
        else if (this._point2.isAbove(_point3))
            return new Point(_point2);
        else if (this._point2.getY() == this._point3.getY()) {
            if (this._point2.isLeft(this._point3))
                return new Point(_point2);
            else return new Point(_point3);
        }
        else return new Point(_point3);
    }

    /**
     * Returns the lowest vertex of the triangle, If there are two lowest points it will return the left one between them
     * @return  The lowest Vertex
     */
    public Point lowestPoint() {
        if (this._point1.isUnder(this._point2)) {
            if (this._point1.isUnder(this._point3))
                return new Point(_point1);
            else if (this._point1.getY() == this._point3.getY()) {
                if (this._point1.isLeft(_point3))
                    return new Point(_point1);
                else
                    return new Point(_point3);
            }
            else return new Point(_point3);
        }
        else if (this._point1.getY() == this._point2.getY()){
            if (this._point1.isUnder(_point3)){
                if (this._point1.isLeft(_point2))
                    return new Point(_point1);
                else return new Point(_point2);
            }
            else return new Point(_point3);
        }
        else if (this._point2.isUnder(_point3))
            return new Point(_point2);
        else if (this._point2.getY() == this._point3.getY()) {
            if (this._point2.isLeft(this._point3))
                return new Point(_point2);
            else return new Point(_point3);
        }
        else return new Point(_point3);
    }

    /**
     * Checks if the triangle is entirely in one quadrant.
     * @return True if  the triangle is entirely in one quadrant
     */
    public boolean isLocated() {
        return (this._point1.quadrant() == this._point2.quadrant() &&
            this._point1.quadrant() == this._point3.quadrant()
            && this._point1.quadrant() != 0);
    }

    /**
     * Checks if this triangle is completely above a received triangle.
     * @param other The triangle to check if this triangle is above
     * @return True if this triangle is above the other triangle
     */
    public boolean isAbove(Triangle other) {
        return (this.lowestPoint().isAbove(other.highestPoint()));
    }

    /**
     * Checks if this triangle is completely below a received triangle.
     * @param other The triangle to check if this triangle is below
     * @return True if this triangle is below the other triangle
     */
    public boolean isUnder(Triangle other) {
        return (other.isAbove(this));
    }

    /**
     * Checks if the given triangle is equal to this triangle.
     * @param other The triangle we are checking equality with
     * @return True if the triangle other is equal to this triangle
     */
    public boolean equals(Triangle other) {
        return (this._point1.equals(other._point1) && this._point2.equals(other._point2) && this._point3.equals(other._point3));
    }

    /**
     * Checks if the given triangle is congruent to this triangle.
     * @param other The triangle we are checking congruency with
     * @return True if the triangle other is congruent to this triangle
     */
    public boolean isCongruent(Triangle other) {
        return (this.getMaxSide(this._point1, this._point2, this._point3) == other.getMaxSide(other._point1, other._point2, other._point3)) &&
        this.getMidSide(this._point1, this._point2, this._point3) == other.getMidSide(other._point1, other._point2, other._point3) &&
        this.getMinSide(this._point1, this._point2, this._point3) == other.getMinSide(other._point1, other._point2, other._point3);
    }

    private double getSide1() {
        return this._point1.distance(this._point2);
    }

    private double getSide2() {
        return this._point2.distance(this._point3);
    }

    private double getSide3() {
        return this._point1.distance(this._point3);
    }

    private double getMaxSide(Point p1, Point p2, Point p3) {
        return (Math.max(Math.max(p1.distance(p2),p1.distance(p3)),p2.distance(p3)));
    }

    private double getMinSide(Point p1, Point p2, Point p3) {
        return (Math.min(Math.min(p1.distance(p2), p1.distance(p3)),p2.distance(p3)));
    }

    private double getMidSide(Point p1, Point p2, Point p3) {
        return (getOtherSides(p1, p2, p3) - getMinSide(p1, p2, p3));
    }

    private double getOtherSides(Point p1, Point p2, Point p3) {
        return (p1.distance(p2) + p2.distance(p3) + p3.distance(p1) - getMaxSide(p1, p2, p3));
    }

    private void setDefaultValue(){
        _point1 = new Point(1, 0);
        _point2 = new Point(-1, 0);
        _point3 = new Point(0, 1);
    }
}

