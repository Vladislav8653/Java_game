import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String firstName, String lastName, int birthYear, int birthMonth, int birthDay) {
        super(firstName, lastName, birthYear, birthMonth, birthDay); 
    }

    @Override
    public int chooseBox(List<Integer> boxes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(firstName + " " + lastName + ", choose a box to open from " + boxes);
        int chosenBoxIndex = scanner.nextInt();
        scanner.close();
        return (chosenBoxIndex >= 0 && chosenBoxIndex < boxes.size()) ? chosenBoxIndex : -1;
    }
}
