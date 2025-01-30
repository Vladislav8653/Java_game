import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    protected String firstName;
    protected String lastName;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private int totalMoney;
    private List<Integer> boxes;

    public Player(String firstName, String lastName, int birthYear, int birthMonth, int birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.totalMoney = 0;
        this.boxes = new ArrayList<Integer>();
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void addMoney(int amount) {
        this.totalMoney += amount;
        boxes.add(amount);
    }

    public int getBoxesQuantity() {
        return boxes.size();
    }
   

    public int chooseBox(List<Integer> boxes) {
        Random random = new Random();
        return random.nextInt(boxes.size()); // return random index in bounds [0..size()]
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + birthYear + "-" + birthMonth + "-" + birthDay + ")";
    }

    public List<Integer> getOpenedBoxes() {
        return boxes;
    }
}
