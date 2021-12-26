package days;

import java.util.*;
import java.util.stream.IntStream;

public class Day23 extends Day {

    public static final char EMPTY = '.';

    private static class World implements Comparable<World> {
        public char[] hallway;
        public int rows;
        public char[][] rooms;
        public int energySpent;
        final int[] roomsIdx = new int[]{2, 4, 6, 8};

        final HashMap<Character, Integer> target = new HashMap<Character, Integer>() {{
            put('A', 2);
            put('B', 4);
            put('C', 6);
            put('D', 8);
        }};

        final HashMap<Character, Integer> energy = new HashMap<Character, Integer>() {{
            put('A', 1);
            put('B', 10);
            put('C', 100);
            put('D', 1000);
        }};

        public World(char[][] content) {
            rows = content.length;
            rooms = new char[4][rows];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < rows; j++) {
                    rooms[i][j] = content[j][i];
                }
            }
            hallway = ("...........").toCharArray();
        }

        public World(World world) {
            this.hallway = new char[world.hallway.length];
            System.arraycopy(world.hallway, 0, this.hallway, 0, hallway.length);
            this.rows = world.rows;
            this.rooms = new char[4][this.rows];
            for (int i = 0; i < world.rooms.length; i++) {
                System.arraycopy(world.rooms[i], 0, this.rooms[i], 0, world.rooms[0].length);
            }
            this.energySpent = world.energySpent;
        }

        private int minCost() {
            int cost = 0;
            for (int i = 0; i < hallway.length; i++) {
                char slot = hallway[i];
                if (slot != EMPTY) {
                    int distance = 1 + Math.abs(i - target.get(slot));
                    cost += distance * energy.get(slot);
                }
            }
            for (int r = 0; r < 4; r++) {
                for (int j = 0; j < rows; j++) {
                    char current = rooms[r][j];
                    int index = 2 + r * 2;
                    if (current != EMPTY && target.get(current) != index) {
                        int distance = 2 + Math.abs(index - target.get(current));
                        cost += distance * energy.get(current);
                    }
                }
            }
            return cost;
        }

        public boolean isCompleted() {
            return minCost() == 0;
        }

        private boolean canMoveToRoom(int source) {
            if (hallway[source] == EMPTY) return false;

            char amphipod = hallway[source];
            int dest = target.get(amphipod);
            int room = dest / 2 - 1;
            int start = source < dest ? source + 1 : dest;
            int end = source < dest ? dest : source - 1;
            for (int i = start; i <= end; i++) {
                if (hallway[i] != EMPTY) return false;
            }

            for (int i = 0; i < rows; i++) {
                if (rooms[room][i] != EMPTY && rooms[room][i] != amphipod)
                    return false;
            }

            return true;
        }

        private World pathToRoom(int source) {
            World world = new World(this);
            char amphipod = hallway[source];
            int targetCol = target.get(amphipod);
            int room = targetCol / 2 - 1;
            int depth = 0;
            for (int i = rows - 1; i >= 0; i--) {
                if (rooms[room][i] == EMPTY) {
                    depth = i;
                    break;
                }
            }
            world.rooms[room][depth] = amphipod;
            world.hallway[source] = EMPTY;
            int dest = 2 + room * 2;
            int distance = Math.abs(dest - source) + 1 + depth;
            world.energySpent += distance * energy.get(amphipod);

            return world;
        }

        private boolean shouldMoveOutOfRoom(int room, int dest) {
            if (Arrays.stream(roomsIdx).anyMatch(i -> i == dest)) //right above a room
                return false;

            if (IntStream.range(0, rows).allMatch(i -> rooms[room][i] == EMPTY))
                return false;

            int depth = 0;
            for (int i = 0; i < rows; i++) {
                if (rooms[room][i] != EMPTY) {
                    depth = i;
                    break;
                }
            }

            char amphipod = rooms[room][depth];
            int source = 2 + room * 2;
            if (source == target.get(amphipod)) {
                int finalDepth = depth;
                if (IntStream.range(0, rows).allMatch(d -> d < finalDepth || rooms[room][d] == amphipod)) return false;
            }

            int start = Math.min(source, dest);
            int end = Math.max(source, dest);
            for (int i = start; i <= end; i++) {
                if (hallway[i] != EMPTY)
                    return false;
            }

            return true;
        }

        private World pathOutOfRoom(int room, int dest) {
            World world = new World(this);
            int row = 0;
            for (int d = 0; d < rows; d++) {
                if (rooms[room][d] != EMPTY) {
                    row = d;
                    break;
                }
            }

            char amphipod = rooms[room][row];
            world.hallway[dest] = amphipod;
            world.rooms[room][row] = EMPTY;

            int source = 2 + room * 2;
            int distance = Math.abs(dest - source) + 1 + row;
            world.energySpent += distance * energy.get(amphipod);

            return world;
        }

        public HashSet<World> nextMoves() {
            HashSet<World> moves = new HashSet<>();
            for (int i = 0; i < hallway.length; i++) {
                if (canMoveToRoom(i)) {
                    moves.add(pathToRoom(i));
                }
            }
            for (int room = 0; room < 4; room++) {
                for (int i = 0; i < hallway.length; i++) {
                    if (shouldMoveOutOfRoom(room, i)) {
                        moves.add(pathOutOfRoom(room, i));
                    }
                }
            }
            return moves;
        }

        @Override
        public int compareTo(World other) {
            return Integer.compare(this.energySpent + this.minCost(),
                    other.energySpent + other.minCost());
        }

        @Override
        public String toString() {
            StringBuilder res= new StringBuilder("#############");
            res.append("\n");
            res.append(String.format("#%s#", new String(hallway)));
            res.append("\n");
            res.append("##");
            for (int i = 0; i < 4; i++) {
                res.append(String.format("#%c",rooms[i][0]));
            }
            res.append("###");
            res.append("\n");

            for (int i = 1; i < rows; i++) {
                res.append("  ");
                for (int j = 0; j < 4; j++) {
                    res.append(String.format("#%c",rooms[j][i]));
                }
                res.append("\n");
            }
            res.append("#############");
            res.append("\n");

            return res.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            World world = (World) o;
            return energySpent == world.energySpent && Arrays.equals(hallway, world.hallway) && Arrays.deepEquals(rooms, world.rooms);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(energySpent);
            result = 31 * result + Objects.hash(new String(hallway));
            result = 31 * result + Arrays.deepHashCode(rooms);
            return result;
        }
    }

    public Day23(Boolean isTest) {
        super(isTest);
    }

    private World findBestWorld(World initial) {
        PriorityQueue<World> queue = new PriorityQueue<>();
        queue.add(initial);

        HashSet<String> seen = new HashSet<>();

        while (!queue.isEmpty()) {
            World current = queue.poll();
            if(current.isCompleted())
                return current;
            else if (seen.contains(current.toString()))
                continue;
            else
                seen.add(current.toString());

            for (World w : current.nextMoves()) {
                if (!queue.contains(w))
                    queue.add(w);
            }
        }

        return null;
    }

    private World getFullWorld(String[] input) {
        char[][] world = new char[input.length-3][4];
        for (int i = 2; i < input.length-1; i++) {
            for (int j = 3; j <=9; j+=2) {
                world[i-2][(j-3)/2] = input[i].charAt(j);
            }
        }
        return new World(world);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        World initial = getFullWorld(input);
        World world = findBestWorld(initial);

        assert world != null;
        int result = world.energySpent;

        System.out.printf("Least energy required to organize the amphipods: %d%n", result);
        return String.valueOf(result);
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        String[] expanded = new String[input.length+2];
        System.arraycopy(input, 0, expanded, 0, 3);
        expanded[3]="  #D#C#B#A#";
        expanded[4]="  #D#B#A#C#";
        System.arraycopy(input, 3, expanded, 5, input.length - 3);
        World initial = getFullWorld(expanded);
        World world = findBestWorld(initial);

        assert world != null;
        int result = world.energySpent;
        System.out.printf("Number of on cubes: %d%n", result);
        return String.valueOf(result);
    }
}