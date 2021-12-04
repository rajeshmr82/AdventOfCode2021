package days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class BingoCard {
    List<List<String>> cells = new ArrayList<>();
    HashMap<String, Integer[]> map = new HashMap<>();
    int score = 0;

    int[] rows = new int[]{5, 5, 5, 5, 5};
    int[] cols = new int[]{5, 5, 5, 5, 5};

    public void addRow(List<String> row) {
        cells.add(row);
        for (int i = 0; i < row.size(); i++) {
            map.put(row.get(i), new Integer[]{cells.size() - 1, i});
        }
    }

    public boolean update(String draw) {
        if (!map.containsKey(draw)) return false;

        Integer[] cell = map.get(draw);
        map.remove(draw);
        if (--rows[cell[0]] == 0 || --cols[cell[1]] == 0) {
            int sum = 0;
            for (String value : map.keySet()) {
                sum += Integer.parseInt(value);
            }
            score = sum * Integer.parseInt(draw);
            return true;
        }

        return false;
    }

    public int getScore() {
        return score;
    }
}
