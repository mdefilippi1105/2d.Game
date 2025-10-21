public class ComputePayment {

    void main() {
        int[] numbers = {10, 20, 30, 40, 50};

        System.out.println("First number: " + numbers[0]);

        numbers[2] = 99;

        for (int i = 0; i < numbers.length; i++) {
            System.out.println("numbers[" + i + "] = " + numbers[i]);
        }
    }
}
