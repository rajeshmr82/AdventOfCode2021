package days;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 extends Day {

    public Day14(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        String polymer = input[0];
        Map<String, String> rules = new HashMap<>();
        Pattern pattern = Pattern.compile("(^[A-Z]{2}) -> ([a-zA-Z]+)");
        for (int i = 1; i < input.length; i++) {
            Matcher m = pattern.matcher(input[i]);
            if (m.find()) {
                rules.put(m.group(1), m.group(2));
            }
        }

        long[] frequency = new long[26];
        for (int i = 0; i < polymer.length(); i++) {
            frequency[polymer.charAt(i) - 'A']++;
        }
        Map<String, Long> pairFreq = new HashMap<>();

        for (int j = 0; j < polymer.length() - 1; j++) {
            String pair = polymer.substring(j, j + 2);
            pairFreq.put(pair,pairFreq.getOrDefault(pair,0L)+1);
        }
        int steps = 10;
        frequency = getFrequency(rules, frequency, pairFreq, steps);
        long result = frequency[frequency.length - 1] - frequency[0];
        System.out.printf("Most common element - Least common element: %d%n", result);
        return String.valueOf(result);
    }

    private long[] getFrequency(Map<String, String> rules, long[] frequency, Map<String, Long> pairFreq, int steps) {
        for (int i = 0; i < steps; i++) {
            Iterator<Map.Entry<String, Long>> itr = pairFreq.entrySet().iterator();
            Map<String, Long> newFreq = new HashMap<>();
            while (itr.hasNext()) {
                Map.Entry<String, Long> entry = itr.next();
                String key = entry.getKey();
                if (rules.containsKey(key)) {
                    String[] newPairs = new String[]{key.charAt(0) + rules.get(key), rules.get(key) + key.charAt(1)};

                    frequency[rules.get(key).charAt(0) - 'A'] += entry.getValue();
                    for (String pair : newPairs
                    ) {
                        newFreq.put(pair, newFreq.getOrDefault(pair, 0L) + entry.getValue());
                    }
                } else {
                    newFreq.put(entry.getKey(), newFreq.getOrDefault(entry.getKey(), 0L) + entry.getValue());
                }
            }
            pairFreq = newFreq;
        }
        frequency = Arrays.stream(frequency).filter(f -> f != 0).toArray();

        Arrays.sort(frequency);
        return frequency;
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        String polymer = input[0];
        Map<String, String> rules = new HashMap<>();
        Pattern pattern = Pattern.compile("(^[A-Z]{2}) -> ([a-zA-Z]+)");
        for (int i = 1; i < input.length; i++) {
            Matcher m = pattern.matcher(input[i]);
            if (m.find()) {
                rules.put(m.group(1), m.group(2));
            }
        }

        long[] frequency = new long[26];
        for (int i = 0; i < polymer.length(); i++) {
            frequency[polymer.charAt(i) - 'A']++;
        }
        Map<String, Long> pairFreq = new HashMap<>();

        for (int j = 0; j < polymer.length() - 1; j++) {
            String pair = polymer.substring(j, j + 2);
            pairFreq.put(pair,pairFreq.getOrDefault(pair,0L)+1);
        }
        int steps = 40;
        frequency = getFrequency(rules, frequency, pairFreq, steps);
        long result = frequency[frequency.length - 1] - frequency[0];
        System.out.printf("Most common element - Least common element: %d%n", result);
        return String.valueOf(result);
    }
}
