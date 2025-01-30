import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String firstName, String lastName, int birthYear, int birthMonth, int birthDay) {
        super(firstName, lastName, birthYear, birthMonth, birthDay);  
    }

    @Override
    public int chooseBox(List<Integer> boxes) {
        Random random = new Random();
        int chosenBoxIndex = random.nextInt(boxes.size());
        return chosenBoxIndex;
    }
}
