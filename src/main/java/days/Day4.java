package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends Day {
    public Day4(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        List<BingoCard> cards = createCards(input);
        String[] draw = input[0].split(",");
        for (String value : draw) {
            for (BingoCard card: cards) {
                if(card.update(value)){
                    System.out.printf("Final score: %d%n", card.getScore());
                    return String.valueOf(card.getScore());
                }
            }
        }

        return "";
    }

    private List<BingoCard> createCards(String[] input) {
        List<BingoCard> boards = new ArrayList<>();
        BingoCard newBoard = new BingoCard();
        for (int i = 2; i < input.length; i++) {
            if (!input[i].isEmpty()) {
                newBoard.addRow(Arrays.stream(input[i].split(" ")).filter(cell -> !cell.isEmpty()).collect(Collectors.toList()));
            } else {
                boards.add(newBoard);
                newBoard = new BingoCard();
            }
        }
        boards.add(newBoard);

        return boards;
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        List<BingoCard> cards = createCards(input);
        String[] draw = input[0].split(",");
        for (String value : draw) {
            int index=0;
            for (int i = 0; i < cards.size(); i++) {
                BingoCard card = cards.get(i);
                if(card.update(value)){
                    if(cards.size()!=1){
                        cards.remove(card);
                        i--;
                    } else {
                        System.out.printf("Final score: %d%n", card.getScore());
                        return String.valueOf(card.getScore());
                    }
                }
            }

        }


        return "";
    }
}
