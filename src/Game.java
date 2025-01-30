import java.util.*;

class Game implements Comparable<Game> {
    private List<Player> players;
    private List<Integer> boxes;
    private Player winner;

    public Game(List<Player> players, List<Integer> boxes) {
        this.players = players;
        this.boxes = new ArrayList<>(boxes);
    }

    public void play() {
        while (!boxes.isEmpty() && players.stream().anyMatch(p -> p.getBoxesQuantity() < boxes.size())) {
            for (Player player : players) {
                if (boxes.isEmpty()) break;
                int chosenBoxIndex = player.chooseBox(boxes);
                int money = boxes.remove(chosenBoxIndex);
                player.addMoney(money);
                
            }
        }

        winner = players.stream()
                .max(Comparator.comparingInt(Player::getTotalMoney))
                .orElse(null);

        displayResults();
    }

    private void displayResults() {
        if (winner != null) {
            System.out.println("Winner: " + winner.getName() + " with " + winner.getTotalMoney() + " money.");
        }

        players.forEach(player -> {
            OptionalInt maxMoney = player.getOpenedBoxes().stream().mapToInt(Integer::intValue).max();
            OptionalInt minMoney = player.getOpenedBoxes().stream().mapToInt(Integer::intValue).min();

            maxMoney.ifPresent(money -> System.out.println(player.getName() + " opened the most valuable box with " + money + " money."));
            minMoney.ifPresent(money -> System.out.println(player.getName() + " opened the least valuable box with " + money + " money."));
        });

        int totalPrize = players.stream().mapToInt(Player::getTotalMoney).sum();
        System.out.println("Total prize pool: " + totalPrize);

        players.sort((p1, p2) -> Integer.compare(p2.getTotalMoney(), p1.getTotalMoney()));
        System.out.println("Players sorted by total money:");
        players.forEach(player -> System.out.println(player.getName() + " earned " + player.getTotalMoney()));
    }

    public Player getWinner() {
        return winner;
    }

    public int getTotalReward() {
        return players.stream().mapToInt(Player::getTotalMoney).sum();
    }

    @Override
    public int compareTo(Game other) {
        return Integer.compare(this.getTotalReward(), other.getTotalReward());
    }

    @Override
    public String toString() {
        return "Game result: " + winner.getName() + " won with " + winner.getTotalMoney() + " money.";
    }
}
