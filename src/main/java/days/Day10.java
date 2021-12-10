package days;

import java.util.*;

public class Day10 extends Day {
    public Day10(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int score=0;
        HashMap<Character,Integer> scoringTable = new HashMap<Character,Integer>(){{
            put(')',3);
            put(']',57);
            put('}',1197);
            put('>',25137);
        }};

        HashMap<Character,Character> bracketPair = new HashMap<Character,Character>(){{
            put(')','(');
            put(']','[');
            put('}','{');
            put('>','<');
        }};

        for (String line: input
             ) {
            Stack<Character> stack = new Stack<>();
            for (char c :line.toCharArray()
                 ) {
                switch (c){
                    case '{':
                    case '(':
                    case '[':
                    case '<':
                        stack.push(c);
                        break;
                    case '}':
                    case ')':
                    case ']':
                    case '>':
                        if(!stack.pop().equals(bracketPair.get(c))) {
                            score+= scoringTable.get(c);
                            break;
                        }
                        break;
                    default:
                }
            }
        }

        System.out.printf("Total syntax error score: %d%n", score);
        return String.valueOf(score);
    }


    public String part2() {
        String[] input = this.getInput().split("\r\n");

        HashMap<Character,Integer> scoringTable = new HashMap<Character,Integer>(){{
            put('(',1);
            put('[',2);
            put('{',3);
            put('<',4);
        }};

        HashMap<Character,Character> bracketPair = new HashMap<Character,Character>(){{
            put(')','(');
            put(']','[');
            put('}','{');
            put('>','<');
            put('(',')');
            put('[',']');
            put('{','}');
            put('<','>');
        }};
        List<Long> scores = new ArrayList<>();
        for (String line: input
        ) {
            boolean incorrect=false;
            Stack<Character> stack = new Stack<>();
            for (char c :line.toCharArray()
            ) {
                switch (c){
                    case '{':
                    case '(':
                    case '[':
                    case '<':
                        stack.push(c);
                        break;
                    case '}':
                    case ')':
                    case ']':
                    case '>':
                        if(!stack.pop().equals(bracketPair.get(c))) {
                            incorrect = true;
                        }
                        break;
                    default:
                }
            }

            if(!incorrect){
                long lineScore =0;
                while (!stack.isEmpty()){
                    Character c = stack.pop();
                    lineScore = (lineScore*5)+ scoringTable.get(c);
                }
                scores.add(lineScore);
            }
        }

        Collections.sort(scores);
        long result=scores.get((scores.size()/2));
        System.out.printf("Middle score: %d%n", result);
        return String.valueOf(result);
    }

}
