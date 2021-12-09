package days;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day08 extends Day {
    public Day08(Boolean isTest) {
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
            for (String number: sixPointDigits
                 ) {
                if(digits[4].chars().allMatch(c -> number.indexOf(c)>=0)){ //six points and matching all points with 4 must be 9
                    digits[9]= sortString(number);
                } else if(digits[7].chars().allMatch(c -> number.indexOf(c)>=0)){ // if not nine and matches all points with 7 then it must be 0
                    digits[0] = sortString(number);
                } else { //if not 9 or 0 then it must be 6
                    digits[6] = sortString(number);
                }
            }

            List<String> fivePointDigits = Arrays.stream(samples).filter(s -> s.length() == 5).collect(Collectors.toList());
            for (String number:fivePointDigits
                 ) {
                if(digits[1].chars().allMatch(c -> number.indexOf(c)>=0)){ //five points and matching all points with 1 must be 3
                    digits[3]= sortString(number);
                } else if(number.chars().allMatch(c -> digits[6].indexOf(c)>=0)){ // if not 3 and matches all points with 6 then it must be 5
                    digits[5] = sortString(number);
                } else { //if not 3 or 5 then it must be 2
                    digits[2] = sortString(number);
                }
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

    public String part2A(){
        String[] input = this.getInput().split("\r\n");
        int result=0;

        HashMap<String,Integer> numberMapping = new HashMap<String,Integer>(){{
            put("abcefg",0);
            put("cf",1);
            put("acdeg",2);
            put("acdfg",3);
            put("bcdf",4);
            put("abdfg",5);
            put("abdefg",6);
            put("acf",7);
            put("abcdefg",8);
            put("abcdfg",9);
        }};
        HashMap<Character,Integer> segmentCount= new HashMap<>();
        for (String num:numberMapping.keySet()
             ) {
            for (Character c: num.toCharArray()
                 ) {
                segmentCount.put(c,segmentCount.getOrDefault(c,0)+1);
            }
        }

        for (String line : input
        ) {
            String[] parts = line.split(Pattern.quote("|"));
            String[] samples = parts[0].split(" ");
            String[] output = parts[1].trim().split(" ");
            HashMap<Character,Integer> sampleSegmentCount= new HashMap<>();

            for (String sample: samples
                 ) {
                for (Character c: sample.toCharArray()
                ) {
                    sampleSegmentCount.put(c,sampleSegmentCount.getOrDefault(c,0)+1);
                }
            }

            HashMap<Character,List<Character>> wireMapping = new HashMap<>();



            System.out.println(segmentCount);
            System.out.println(sampleSegmentCount);
        }

        return String.valueOf(result);
    }

    private String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
