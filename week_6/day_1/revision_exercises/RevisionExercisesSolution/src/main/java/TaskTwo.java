import java.util.ArrayList;

public class TaskTwo {

    public static void printArrayList(ArrayList<Integer> numbers) {
        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        ArrayList<Integer> oddNumbers = new ArrayList<>();
        ArrayList<Integer> evenNumbers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                evenNumbers.add(i);
            } else {
                oddNumbers.add(i);
            }
        }

        System.out.println("Odd Numbers");
        printArrayList(oddNumbers);

        System.out.println("Even Numbers");
        printArrayList(evenNumbers);

    }

}
