import java.io.*;
import java.util.*;

public class Symulator {

    private static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        loadPlayersFromFile("players.txt");

        simulateGames(100);

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
                    System.out.println("Invalid string format: " + line);
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
        try (PrintWriter writer = new PrintWriter(new FileWriter("report.txt", true))) { 
        if (players.size() > 0) {
            List<Player> winners = findWinners(players);
            int totalMoney = 0;
            for (Player winner : winners) {
                writer.println("Winners: " + winner.getName());
                totalMoney += winner.getTotalMoney();
            }
            writer.println("Total gain: " + totalMoney);
        } else {
            writer.println("No players");
        }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        System.out.println("Sorted by money players:");
        players.sort(Comparator.comparingInt(Player::getTotalMoney).reversed());
        for (Player player : players) {
            System.out.println(player + " - " + player.getTotalMoney());
        }
}

    private static List<Player> findWinners(List<Player> players) {
        if (players.isEmpty()) {
            return Collections.emptyList(); 
        }

        int maxMoney = players.stream()
                .mapToInt(Player::getTotalMoney)
                .max()
                .orElse(0); 

        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            if (player.getTotalMoney() == maxMoney) {
                winners.add(player);
            }
        }

        return winners;
    }
}
