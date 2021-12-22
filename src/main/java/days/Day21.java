package days;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 extends Day {

    static class Dice{
        static int face;
        static long rolls;

        static int rollDeterministic() {
            face++;
            if (face == 101) face = 1;
            rolls++;
            return face;
        }
    }

    static class Player{
        int id;
        long score;
        int position;

        public Player(int id, int start){
            this.id=id;
            this.position = start;
        }

        public Player(Player player){
            this.id=player.id;
            this.position = player.position;
            this.score = player.score;
        }

        public void rollDice() {
            int i = 3;
            int move = 0;
            while (i-- > 0) {
                move += Dice.rollDeterministic();
            }
            position = position + move;
            if (position % 10 == 0) position = 10;
            else position = position % 10;

            score += position;
        }

        public void rollDice(int roll) {

            position = position + roll;
            if (position % 10 == 0) position = 10;
            else position = position % 10;

            score += position;
        }


        @Override
        public String toString() {
            return "{" +
                    "position=" + position +
                    ", score=" + score +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player player = (Player) o;
            return id == player.id && score == player.score && position == player.position;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, score, position);
        }
    }

    static class State {
        Player player1;
        Player player2;

        public State(Player player1, Player player2) {
            this.player1 = player1;
            this.player2 = player2;
        }

        @Override
        public String toString() {
            return "{" + player1.toString() +
                    ", " + player2.toString() +
                    '}';
        }
    }

    public Day21(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        List<Player> players = new ArrayList<>();
        getPlayers(input, players);
        int winScore = 1000;
        boolean gameOver=false;
        while (!gameOver) {
            for (Player player : players) {
                player.rollDice();
                if(player.score>=winScore) {
                    gameOver=true;
                    break;
                }
            }
        }

        long result = Dice.rolls * Math.min(players.get(0).score,players.get(1).score);

        System.out.printf("Score of the losing player by the number of times the die was rolled: %d%n", result);
        return String.valueOf(result);
    }

    private void getPlayers(String[] input, List<Player> players) {
        Pattern pattern = Pattern.compile("Player (\\d) starting position: (\\d)");
        for (String line : input) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                players.add(new Player(Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2))));
            }
        }
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        List<Player> players = new ArrayList<>();
        getPlayers(input, players);

        HashMap<Integer, Integer> diceRolls = new HashMap<>();
        for (int x = 1; x <=3 ; x++) {
            for (int y = 1; y <=3 ; y++) {
                for (int z = 1; z <=3; z++) {
                    int sum = x + y + z;
                    diceRolls.put(sum, diceRolls.getOrDefault(sum, 0) + 1);
                }
            }
        }

        System.out.println(diceRolls);
        HashMap<String, long[]> cache = new HashMap<>();

        State initial = new State(players.get(0),players.get(1));

        long[] wins= play(initial,cache,diceRolls);
        long result = Math.max(wins[0],wins[1]);
        System.out.printf("Player that wins in more universes: %d%n",result );
        return String.valueOf(result);
    }

    private long[] play(State state, HashMap<String, long[]> cache, HashMap<Integer, Integer> diceRolls) {
        if(cache.containsKey(state.toString())) {
            return cache.get(state.toString());
        }
        long[] result=new long[]{0,0};

        for (Map.Entry<Integer,Integer> roll:diceRolls.entrySet()
             ) {
            Player newPlayer1 =new Player(state.player1);
            newPlayer1.rollDice(roll.getKey());
            if(newPlayer1.score>=21){
                result[0]+= roll.getValue();
            } else {
                long[] wins = play(new State(state.player2, newPlayer1),cache,diceRolls);
                result[0] += wins[1]* roll.getValue();
                result[1] += wins[0]* roll.getValue();
            }
        }
            cache.put(state.toString(),result);
        return result;
    }

}