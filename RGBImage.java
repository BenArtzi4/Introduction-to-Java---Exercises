
/**
 * This class represents an image
 * The image consists of a two-dimensional array of pixels.
 * Each pixel containing numerical values of three colors.
 *
 * @author (Gal Ben Artzi)
 */
public class RGBImage
{

    public RGBColor [] [] _image; 

    /**
     * Create  a new RGBImage object
     */
    public RGBImage ( int rows, int cols){
        _image = new RGBColor [rows] [cols];
        for (int i = 0 ; i < rows ; i++){
            for (int j = 0 ; j < cols ; j++){
                this._image [i] [j] = new RGBColor();
            }
        }
    }

    /**
     *constructor - Create  a new RGBImage object identical to the given array
     */
    public RGBImage (RGBColor[][] pixels){
        _image = new RGBColor [pixels.length] [pixels[0].length];
        for (int i = 0 ; i < pixels.length ; i++){
            for (int j = 0 ; j < pixels[0].length ; j++)
                this._image [i] [j] = new RGBColor (pixels [i] [j]);
        }

    }

    /**
     * Copy constructor - Create  a new RGBImage object identical to the given RGBImage
     */
    public RGBImage (RGBImage other){
        this(other._image);                    // dont delete!!!!~~~~~~~~~~~~~~~~~~~~~~`&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
    }

    /**
     * This method returns iamge's height (in pixels)
     * 
     * @return Returns the image height
     */
    public int getHeight(){
        return this._image.length;
    }

    /**
     * This method returns iamge's width (in pixels)
     * 
     * @return Returns the image width
     */
    public int getWidth(){
        return this._image[0].length;
    }

    /**
     * This method returns the color's values of the pixel accordig to the given coordinates, if the coordinates are out of the image, a black pixel will be returned
     * 
     * @param row The location of the pixel at the height of the image
     * @param col The location of the pixel at the width of the image
     * @return The color values of the pixel
     */
    public RGBColor getPixel (int row, int col){
        if (row >= this._image.length || col >= this._image[0].length || row < 0 || col < 0){   //Checks whether the coordinates are out of the image
            return new RGBColor();
        }
        else 
            return new  RGBColor(this._image [row] [col]);
    }

    /**
     * This method sets the given color's values to a pixel at given coordinates
     * 
     * @param row The location of the pixel at the height of the image
     * @param col The location of the pixel at the width of the image
     * @param pixal RBG object that has three colors and according to which the new color's values is determined
     */
    public void setPixel (int row, int col, RGBColor pixel){
        if (row >= this._image.length || col >= this._image[0].length || row < 0 || col < 0){  //Checks whether the coordinates are out of the image
            return;
        }
        else this._image [row] [col] = new RGBColor(pixel);
    }

    /**
     * This method checks if the given image equals to this image
     * 
     * @param other The image we are checking equality with
     * @return Return true if the other image iquals to this image
     */
    public boolean equals (RGBImage other){
        if (this.getHeight() == other.getHeight() && this.getWidth() == other.getWidth()){
            for (int i = 0 ; i < this._image.length ; i++){
                for (int j = 0 ; j < this._image[i].length ; j++){
                    if (!(this._image[i] [j].equals(other._image [i] [j])))
                        return false;
                }
            }
            return true;
        }
        else return false;
    }

    /**
     * This method flips the image aruond the horizontal axis
     * 
     */
    public void flipHorizontal(){
        for (int i = 0 ; i < this._image.length ; i++){
            for (int j = 0 ; j < this._image[0].length/2 ; j++){
                RGBColor temp = new RGBColor(this._image[i][j]);  //Creates a pixel identical to the pixel in which the loop is currently located
                this._image [i] [j] = new RGBColor(this._image [i] [this._image[i].length -j -1]);  //Inserts the correct pixel that should be after the flip
                this._image [i] [this._image[i].length -j -1] = new RGBColor (temp);  //Inserts the correct pixel that should be after the flip
            }
        }
    }

    /**
     * This method flips the image aruond the vertical axis
     * 
     */
    public void flipVertical(){   
        for (int i = 0 ; i < this._image.length/2 ; i++){
            for (int j = 0 ; j < this._image[0].length ; j++){
                RGBColor temp = new RGBColor(this._image[i][j]);  //Creates a pixel identical to the pixel in which the loop is currently located
                this._image [i] [j] = new RGBColor (this._image[this._image.length -i -1] [j]);  //Inserts the correct pixel that should be after the flip
                this._image  [this._image.length -i -1] [j] = new RGBColor (temp);  //Inserts the correct pixel that should be after the flip

            }
        }
    }

    /**
     * This method turns all the colors of the pixels into their complementary color
     * 
     */
    public void invertColors(){
        for (int i = 0 ; i < this._image.length ; i++){
            for (int j = 0 ; j < this._image[0].length ; j++){
                this._image [i] [j].invert();  //Sub-pixel to pixel with complementary colors
            }
        }
    }

    /**
     * This method rotate the image 90 degrees clockwise
     * 
     */
    public void rotateClockwise(){
        RGBImage temp = new RGBImage (this._image [0].length , this._image.length);  //Creates a new image when the height and width values have been replaced
        for (int i = 0 ; i < temp._image.length ; i++){
            for (int j = 0 ; j < temp._image[0].length ; j++){
                temp._image [i] [j] = new RGBColor (this._image [this._image.length -1 - j] [i]);  //Inserts into the current pixel the new values as they should be after rotating the image
            }
        }
        this._image = temp._image; //Changes the image to an image after rotation
    }

    /**
     * This method rotate the image 90 degrees counterclockwise
     * 
     */
    public void rotateCounterClockwise(){     //Rotate the image 3 times clockwise so that the image will eventually rotate counterclockwise once
        this.rotateClockwise();    // dont delete!!!!~~~~~~~~~~~~~~~~~~~~~~`&&&&&&&&&&&&&&&&&&&&&&&&&&&&777&&&&&&&&&&&&&&&&&&777
        this.rotateClockwise();            // dont delete!!!!~~~~~~~~~~~~~~~~~~~~~~`&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77
        this.rotateClockwise();         // dont delete!!!!~~~~~~~~~~~~~~~~~~~~~~`&&&&&&&&&&&&777

    }

    /**
     * This method moves the image right or left according to a given number
     * 
     * @param offset The number of columns and the direction the image will move
     */
    public void shiftCol(int offset){
        if (offset == 0 || Math.abs(offset) > this._image[0].length)
            return;

        else if (offset > 0){    //Moves the columns if the given number is positive
            RGBImage temp = new RGBImage (this);  //Creates a new identical Object
            for (int i = 0 ; i < this._image.length ; i++){
                for (int j = 0 ; j < this._image[0].length ; j++){
                    if ((offset - j) <= 0){
                        this._image [i] [j] = new RGBColor(temp._image [i ] [j - offset]);  //Inserts the new values into the current pixel according to the movement of the image
                    }
                    else this._image [i] [j] = new RGBColor();  //Paint in black the cells that have been moved and have no value
                }
            }
        }
        else if ( offset < 0){    ////Moves the columns if the given number is negative
            RGBImage temp = new RGBImage (this);  //Creates a new identical array
            for (int i = 0 ; i < this._image.length ; i++){
                for (int j = 0 ; j < this._image[0].length ; j++){
                    if ((this._image [i].length - j) > (Math.abs(offset))){
                        this._image [i] [j] = new RGBColor(temp._image [i] [j+Math.abs(offset)]);  //Inserts the new values into the current pixel according to the movement of the image
                    }
                    else this._image [i] [j] = new RGBColor();   //Paint in black the cells that have been moved and have no value
                }
            }
        }
    }

    /**
     * This method moves the image right or left according to a given number
     * 
     * @param offset The number of columns and the direction the image will move
     */
    public void shiftRow(int offset){
        //Instead of moving the image up or down it is possible to rotate the image and move it to the sides according to the number given to the sides and then rotate it back      
        //The offset should be multiplied by -1
        offset *= -1;
        this.rotateClockwise();                           // dont delete!!!!~~~~~~~~~~~~~~~~~~~~~~`&&&&&&&&&&&&&&&&&&&&&&&&&
        this.rotateCounterClockwise();                         // dont delete!!!!~~~~~~~~~~~~~~~~~~~~~~`&&&&&&&&&&&&&&&&&&&&&&&&&&
    }

    /**
     * This method returns gray representation of the image
     * 
     * @return Returns gray representation of the image
     */
    public double [] [] toGrayscaleArray(){
        double [] [] grayScale = new double [this._image.length] [this._image [0].length];
        for (int i = 0 ; i < this._image.length ; i++){
            for (int j = 0 ; j < this._image[0].length ; j++){
                grayScale [i] [j] = _image[i] [j].convertToGrayscale();   //Adds a gray representation of the pixel to the new array
            }
        }
        return grayScale;
    }

    /**
     * This method returns String representation of the image
     * 
     * @return Returns gray representation of the image
     */
    public String toString(){
        String stringImage = "";
        for (int i = 0 ; i < (this._image.length) ; i++){
            for (int j = 0 ; j < (this._image[0].length - 1) ; j++){
                stringImage += this._image [i] [j].toString() + " ";
            }
            stringImage += this._image [i] [this._image[0].length -1].toString() + "\n";
        }
        return stringImage;
    }

    /**
     * This method returns a copy of the pixel array
     * 
     * @return Returns copy of the pixel array
     */
    public RGBColor [] [] toRGBColorArray(){
        RGBImage copy = new RGBImage(this);  //Creates a new object with the same attribute so that the new object can be returned
        return copy._image;
    }
}
