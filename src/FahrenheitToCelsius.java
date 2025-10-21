import java.util.Scanner;

public class FahrenheitToCelsius {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter a degree in Fahrenheit: ");
        double fahrenTemp = userInput.nextDouble();

        double celsTemp = (5.0 / 9) * (fahrenTemp - 32);
        System.out.println("Fahrenheit :" + fahrenTemp
         + " is " + celsTemp + " in Celsius.");

    }
}
