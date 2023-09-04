package RectangleProblem;

public class Comparator {
    public static void main(String[] args){
        Rectangle rec1 = new Rectangle(3.0, 2.0);
        Rectangle rec2 = new Rectangle(3.5, 2.0);
        Rectangle rec3 = new Rectangle(2.5, 2.0);

        System.out.println(rec1.compareTo(rec3));
    }
}
