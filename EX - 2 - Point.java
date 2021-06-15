/**
 *
 *This class represents a 2-Dimensional point in the Euclidean space.
 *
 *@author Gal Ben Artzi
 */

public class Point {
    private double _x;
    private double _y;

    /**
     * Create a new point object
     * @param x Point's x coordinate
     * @param y Point's y coordinate
     */
    public Point (double x, double y) {
        this._x = x;
        this._y = y;
    }

    /**
     * Copy constructor - Create a new point object with the same values as the given point
     * @param other The Point to be copied
     */
    public Point (Point other){
        this._x = other._x;
        this._y = other._y;
    }

    /**
     * Returns the x coordinate
     * @return x coordinate
     */
    public double getX(){
        return this._x;
    }

    /**
     * Returns the y coordinate
     * @return y coordinate
     */
    public double getY(){
        return this._y;
    }

    /**
     * Sets the x coordinate of the point
     * @param num The new x coordinate
     */
    public void setX(double num){
        this._x =num;
    }

    /**
     * Sets the y coordinate of the point
     * @param num The new y coordinate
     */
    public void setY(double num){
        this._y =num;
    }

    /**
     * Returns a string representation of this Point. The format of the string should be: (x,y)
     * @return A string representation of a Point object
     */
    public String toString(){
        return "(" + this._x + "," + this._y + ")";
    }

    /**
     * Returns true if the given point is equal to this point.
     * @param other The point we are checking equality with
     * @return True if the point other equals this point
     */
    public boolean equals (Point other){
        return ((this._x == other._x) &&
            this._y == other._y);
    }

    /**
     * Check if this point is above the given point
     * @param other The point to check if this point is above
     * @return True if this point is above the given point
     */
    public boolean isAbove (Point other){
        return (this._y > other._y);
    }

    /**
     * Check if this point is below the given point
     * @param other The point to check if this point is below
     * @return True if this point is below the given point
     */
    public boolean isUnder(Point other){
        return (other.isAbove(this));
    }

    /**
     * Check if this point is to the left of the given point
     * @param other The point to check if this point is left of
     * @return True if this point is left to the given point
     */
    public boolean isLeft(Point other){
        return (this._x < other._x);
    }

    /**
     * Check if this point is to the right of the given point
     * @param other The point to check if this point is right of
     * @return True if this point is right to the given point
     */
    public boolean isRight (Point other){
        return (other.isLeft(this));
    }

    /**
     * Check the distance between this point and the given point
     * @param other The point to check the distance from
     * @return The distance between this point and the given point
     */
    public double distance (Point p){
        return  Math.sqrt((Math.pow((this._y - p._y),2)) +
            (Math.pow((this._x - p._x),2)));
    }

    /**
     * Returns number of quadrant or 0 if the point is on an axis
     * @return An int representing the quadrant number
     */
    public int quadrant() {
        if (this._x == 0 || this._y == 0)
            return  0;
        else if (this._x > 0 && this._y > 0)
            return 1;
        else if (this._x < 0 && this._y > 0)
            return 2;
        else if (this._x < 0 && this._y <0)
            return 3;
        else return  4;

    }
}

