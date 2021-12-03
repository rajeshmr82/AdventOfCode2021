package days.Day3;

import days.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends Day {
    public Day3() {
        inputFilePart1 = "src/main/java/days/Day3/day3.1.input";
        inputFilePart2 = "src/main/java/days/Day3/day3.1.input";
    }

    public void part1() {
        String input = this.getInputPart1();
        String[] lines = input.split("\r\n");
        int[] countOne = new int[lines[0].length()];
        int[] countZero = new int[lines[0].length()];
        StringBuilder gama = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for (String line : lines) {
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '1') {
                    countOne[j]++;
                } else {
                    countZero[j]++;
                }
            }
        }

        for (int i = 0; i < countOne.length; i++) {
            if (countOne[i] > countZero[i]) {
                gama.append("1");
                epsilon.append("0");
            } else {
                gama.append("0");
                epsilon.append("1");
            }
        }
        int gamaValue = Integer.parseInt(gama.toString(), 2);
        int epsilonValue = Integer.parseInt(epsilon.toString(), 2);
        System.out.printf("Gamma rate: %d%n", gamaValue);
        System.out.printf("Epsilon  rate: %d%n", epsilonValue);
        System.out.printf("Power consumption of the submarine: %d%n", gamaValue * epsilonValue);
    }

    public void part2() {
        String input = this.getInputPart2();
        String[] lines = input.split("\r\n");
        int n = lines[0].length();
        List<String> currLines = Arrays.asList(lines);
        int O2Rating = getRating(n, currLines, true);
        System.out.printf("O2 scrubber rating: %d%n", O2Rating);
        currLines = Arrays.asList(lines);
        int CO2Rating = getRating(n, currLines, false);

        System.out.printf("CO2 scrubber rating: %d%n", CO2Rating);
        System.out.printf("Life support rating of the submarine: %d%n", O2Rating * CO2Rating);
    }

    private int getRating(int n, List<String> currLines, boolean mostCommon) {
        for (int i = 0; i < n; i++) {
            int idx = i;
            int countZero = (int) currLines.stream().filter(l -> l.charAt(idx) == '0').count();
            int countOnes = currLines.size() - countZero;
            boolean metCriteria = mostCommon == (countZero > countOnes);
            if (metCriteria) {
                currLines = currLines.stream().filter(l -> l.charAt(idx) == '0').collect(Collectors.toList());
            } else {
                currLines = currLines.stream().filter(l -> l.charAt(idx) == '1').collect(Collectors.toList());
            }
            if (currLines.size() == 1) {
                return Integer.parseInt(currLines.get(0), 2);
            }
        }
        return 0;
    }

}
