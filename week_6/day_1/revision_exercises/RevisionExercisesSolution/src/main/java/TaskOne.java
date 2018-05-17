import java.util.ArrayList;

public class TaskOne {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();

        names.add("Keith");
        names.add("Sian");
        names.add("Upul");
        names.add("Sandy");

        for(int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            System.out.print(name + " ");
        }
        System.out.println();

//  alternative using enhanced for loop
//        for (String name : names) {
//            System.out.print(name + " ");
//        }
//        System.out.println();

    }
}
