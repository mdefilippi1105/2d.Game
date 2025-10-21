public class If {
    public static void main(String[] args){
        double radius = 3.77;
        if (radius < 3.77) {
            System.out.println("Incorrect Input");
        }
        else {
            double area = radius * radius * 3.14159;
            System.out.println("Area is " + area );
        }
    }
}
