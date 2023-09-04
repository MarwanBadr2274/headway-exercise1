package RectangleProblem;

class Rectangle implements Comparable<Rectangle>{
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getArea(){
        double area = this.length * this.width;
        //System.out.println("The area of the rectangle is " + area + " m2");
        return area;
    }

    public double getPerimeter(){
        double perimeter = (2 * this.length) + (2 * this.width);
       //System.out.println("The perimeter of the rectangle is " + perimeter + " m");
        return perimeter;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public int compareTo(Rectangle o) {
        double thisArea = this.getArea();
        double otherArea = o.getArea();

        if(thisArea < otherArea){
            return -1;
        }
        else if(thisArea == otherArea){
            return 0;
        }
        else {
            return 1;
        }
    }

}
