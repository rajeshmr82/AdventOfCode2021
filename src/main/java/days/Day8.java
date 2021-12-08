package days;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day8 extends Day {
    public Day8(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int uniqueCount=0;

        for (String line: input
        ) {
            String[] parts = line.split(Pattern.quote("|"));
            String[] output = parts[1].trim().split(" ");
            uniqueCount+=(output.length - Arrays.stream(output).filter(o -> o.length()==5 || o.length()==6).count());
        }

        System.out.printf("How many times do digits 1, 4, 7, or 8 appear: %d%n", uniqueCount);
        return String.valueOf(uniqueCount);
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        int result = 0;

        for (String line : input
        ) {
            String[] parts = line.split(Pattern.quote("|"));
            String[] samples = parts[0].split(" ");
            String[] output = parts[1].trim().split(" ");
            String[] digits = new String[10];
            HashMap<String, Integer> map = new HashMap<>();
            for (String sample: samples
                 ) {
                switch (sample.length()) {
                    case 2:
                        digits[1] = sortString(sample);
                        break;
                    case 3:
                        digits[7] = sortString(sample);
                        break;
                    case 4:
                        digits[4] = sortString(sample);
                        break;
                    case 7:
                        digits[8] = sortString(sample);
                }
            }

            List<String> sixPointDigits = Arrays.stream(samples).filter(s -> s.length() == 6).collect(Collectors.toList());
            for (String s : sixPointDigits
            ) {
                String delta = removeIntersect(digits[8], s);
                if (digits[1].contains(delta)) {
                    sixPointDigits.remove(s);
                    digits[6] = sortString(s);
                    break;
                }
            }

            String top = removeIntersect(digits[7], digits[1]);

            for (String s : sixPointDigits
            ) {
                String delta = removeIntersect(digits[8],s);
                if (!delta.isEmpty() && !digits[4].contains(delta)) {
                    sixPointDigits.remove(s);
                    digits[9] = sortString(s);
                    break;
                }
            }

            if (sixPointDigits.size() == 1)
                digits[0] = sortString(sixPointDigits.get(0));

            String middle = removeIntersect(digits[8], digits[0]);
            String bottom = removeIntersect(digits[9], digits[4] + top);

            digits[3] = sortString(digits[1] + top + bottom + middle);
            List<String> fivePointDigits = Arrays.stream(samples).filter(s -> s.length() == 5).collect(Collectors.toList());
            for (String s : fivePointDigits
            ) {
                if (digits[3].equals(sortString(s))) {
                    fivePointDigits.remove(s);
                    break;
                }
            }
            for (String s : fivePointDigits
            ) {
                String delta = removeIntersect(s, digits[6]);
                if (delta.isEmpty()) {
                    fivePointDigits.remove(s);
                    digits[5] = sortString(s);
                    break;
                }
            }
            if (fivePointDigits.size() == 1) {
                digits[2] = sortString(fivePointDigits.get(0));
            }

            for (int i = 0; i < digits.length; i++) {
                if (digits[i] != null) {
                    map.put(digits[i], i);
                }
            }

            StringBuilder outputBuilder = new StringBuilder();
            for (String op : output
            ) {
                outputBuilder.append(map.get(sortString(op)));
            }

            result += Integer.parseInt(outputBuilder.toString());
        }


        System.out.printf("Sum of output : %d%n", result);
        return String.valueOf(result);
    }

    private String removeIntersect(String s1, String s2){
        HashSet<Character> h1 = new HashSet<>(), h2 = new HashSet<>();
        for(int i = 0; i < s1.length(); i++)
            h1.add(s1.charAt(i));
        for(int i = 0; i < s2.length(); i++)
        {
            h2.add(s2.charAt(i));
        }
        h1.retainAll(h2);
        Character[] res = h1.toArray(new Character[0]);
        StringBuilder builder = new StringBuilder();
        for (char c: s1.toCharArray()
             ) {
            if(Arrays.stream(res).noneMatch(r -> r.equals(c))){
                builder.append(c);
            }
        }

        return builder.toString();
    }

    private String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
