package days;

import java.util.*;

public class Day18 extends Day {
    static class Pair  {
        Integer leftValue;
        Integer rightValue;
        Pair leftPair;
        Pair rightPair;
        int depth;
        Pair parent;

        public Pair(int depth, Pair parent) {
            this.depth = depth;
            this.parent = parent;
        }

        public Pair() {

        }

        public Pair(int left, int right, Pair parent, int depth) {
            this.leftValue = left;
            this.rightValue = right;
            this.parent = parent;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return String.format("[%s,%s]", leftValue != null ? leftValue : leftPair, rightValue != null ? rightValue : rightPair);
        }

        public void increaseDepth() {
            this.depth++;
            if(this.leftPair!=null) this.leftPair.increaseDepth();

            if(this.rightPair!=null) this.rightPair.increaseDepth();
        }

        public void reduce() {
            while (true){
                boolean actionApplied = explode();
                if(actionApplied) continue;
                actionApplied = split();
                if(!actionApplied) break;
            }
        }

        private boolean split() {
            if(this.leftValue!=null && this.leftValue>9){
                int left = (int) Math.floor(this.leftValue/2);
                int right = (int) Math.ceil((double)this.leftValue/2);
                this.leftPair = new Pair(left,right,this,this.depth+1);
                this.leftValue = null;
                return true;
            }

            if(this.leftPair!=null){
                if(this.leftPair.split()) return true;
            }

            if(this.rightValue!=null && this.rightValue>9) {
                int left = (int) Math.floor(this.rightValue / 2);
                int right = (int) Math.ceil((double) this.rightValue / 2);

                this.rightPair = new Pair(left, right, this, this.depth + 1);
                this.rightValue = null;
                return true;
            }

            if(this.rightPair!=null){
                return this.rightPair.split();
            }

            return false;
        }

        private boolean explode() {
            if (this.depth >= 4 && leftValue != null && rightValue != null) {
                addLeftUp(leftValue);
                addRightUp(rightValue);

                if (this.parent.leftPair == this) {
                    this.parent.leftPair = null;
                    this.parent.leftValue = 0;
                } else if (this.parent.rightPair == this) {
                    this.parent.rightPair = null;
                    this.parent.rightValue = 0;
                } else {
                    return false;
                }

                return true;
            }

            if (this.leftPair != null) {
                if (this.leftPair.explode()) return true;

            }

            if (this.rightPair != null) {
                return this.rightPair.explode();
            }

            return false;
        }

        private void addRightUp(Integer value) {
            if(this.parent==null) return;

            if(this.parent.leftPair==this){
                if(this.parent.rightValue!=null){
                    this.parent.rightValue+=value;
                }else{
                    this.parent.rightPair.addRightDown(value);
                }
            }else if(this.parent.rightPair==this){
                this.parent.addRightUp(value);
            }
        }

        private void addRightDown(Integer value) {
            if (this.leftValue != null) {
                this.leftValue += value;
            } else {
                this.leftPair.addRightDown(value);
            }
        }

        private void addLeftUp(Integer value) {
            if(this.parent==null) return;

            if(this.parent.rightPair==this){
                if(this.parent.leftValue!=null){
                    this.parent.leftValue+=value;
                }else{
                    this.parent.leftPair.addLeftDown(value);
                }
            }else if(this.parent.leftPair==this ){
                this.parent.addLeftUp(value);
            }
        }

        private void addLeftDown(Integer value) {
            if(this.rightValue!=null){
                this.rightValue+=value;
            } else {
                this.rightPair.addLeftDown(value);
            }
        }

        public long magnitude() {
            long sum = 0;
            if (this.leftValue != null) sum += (3L * leftValue);
            else sum += (3* this.leftPair.magnitude());

            if (this.rightValue != null) sum += (2L * rightValue);
            else sum += (2*this.rightPair.magnitude());

            return sum;
        }
    }

    public Day18(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        LinkedList<Pair> numbers = new LinkedList<>();
        for (String line:input
             ) {
            numbers.add(parse(line,0,null));
        }

        Pair sum =numbers.getFirst();
        for (int i = 1; i < numbers.size(); i++) {
            sum = add(sum,numbers.get(i));
        }
        System.out.println(sum);
        long result = sum.magnitude();
        System.out.printf("Final Sum Magnitude: %d%n", result);
        return String.valueOf(result);
    }

    private Pair add(Pair pair1, Pair pair2) {
        Pair pair= new Pair();
        pair1.increaseDepth();
        pair1.parent = pair;

        pair2.increaseDepth();
        pair2.parent = pair;

        pair.leftPair = pair1;
        pair.rightPair = pair2;

        pair.reduce();

        return pair;
    }

    private Pair parse(String s,int depth,Pair parent) {
        Pair pair = new Pair(depth,parent);
        int idxAfterComma=3;
        if (Character.isDigit(s.charAt(1))) {
            pair.leftValue = Character.getNumericValue(s.charAt(1));
        } else {
            int brackets = 1;
            int i = 2;
            while (brackets != 0) {
                switch (s.charAt(i)) {
                    case '[':
                        brackets++;
                        break;
                    case ']':
                        brackets--;
                        break;
                }
                i++;
            }
            idxAfterComma = i+1;
            pair.leftPair = parse(s.substring(1, idxAfterComma -1), depth + 1, pair);

        }

        if (Character.isDigit(s.charAt(idxAfterComma))) {
            pair.rightValue = Character.getNumericValue(s.charAt(idxAfterComma));
        } else {
            pair.rightPair = parse(s.substring(idxAfterComma), depth + 1, pair);
        }
        return pair;
    }
    
    public String part2() {
        String[] input = this.getInput().split("\r\n");

        long maxMagnitude=0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if(i==j) continue;
                Pair sum = add(
                        parse(input[i], 0, null),
                        parse(input[j], 0, null)
                );
                long magnitude = sum.magnitude();
                if(magnitude>maxMagnitude) maxMagnitude = magnitude;
            }
        }

        System.out.printf("Largest magnitude of any sum two numbers: %d%n", maxMagnitude);
        return String.valueOf(maxMagnitude);
    }

}