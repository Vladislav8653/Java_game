import java.io.*;
import java.util.*;

public class Symulator {

    private static List<Player> players = new ArrayList<>();
    private static Player winner = null;

    public static void main(String[] args) {
        // Загрузка игроков
        loadPlayersFromFile("players.txt");

        // Симуляция игры
        simulateGames(100);

        // Генерация отчета
        generateReport();
    }

    public static void loadPlayersFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 5) {
                    String firstName = data[0];
                    String lastName = data[1];
                    int birthYear = Integer.parseInt(data[2]);
                    int birthMonth = Integer.parseInt(data[3]);
                    int birthDay = Integer.parseInt(data[4]);
                    Player player = new Player(firstName, lastName, birthYear, birthMonth, birthDay);
                    players.add(player);
                } else {
                    System.out.println("Неверный формат строки: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void simulateGames(int numberOfGames) {
        Random rand = new Random();
        for (int i = 0; i < numberOfGames; i++) {
            if (players.size() > 0) {
                int randomIndex = rand.nextInt(players.size());
                Player selectedPlayer = players.get(randomIndex);
                int prize = rand.nextInt(1000) + 100;  // Сумма приза от 100 до 1000
                selectedPlayer.addMoney(prize);
            }
        }
    }

    public static void generateReport() {
        if (players.size() > 0) {
            winner = Collections.max(players, Comparator.comparingInt(Player::getTotalMoney));
            System.out.println("Победитель: " + winner.getName());
            System.out.println("Общий призовой фонд: " + winner.getTotalMoney());

            System.out.println("\nИгроки, отсортированные по выигранным деньгам:");
            players.sort(Comparator.comparingInt(Player::getTotalMoney).reversed());
            for (Player player : players) {
                System.out.println(player + " - " + player.getTotalMoney() + " у.е.");
            }
        } else {
            System.out.println("Нет игроков для игры.");
        }
    }
}
