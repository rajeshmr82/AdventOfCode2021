package days;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day24 extends Day {

    static class Instruction{
        String operation;
        int lhs;
        String rhs=null;

        public Instruction(String[] instr) {
            operation = instr[0];
            lhs = instr[1].charAt(0)-'w';
            if (instr.length > 2)
                rhs = instr[2];
        }

        public boolean isInput(){
            return operation.equals("inp");
        }

        @Override
        public String toString() {
            return String.format("%s %s %s", lhs, operation, rhs);
        }
    }

    static class Expr {
        String operation;
        Expr left;
        Expr right;
        Integer value;

        public Expr(String opcode, Expr left, Expr right) {
            this.operation = opcode;
            this.left = left;
            this.right = right;
        }

        public Expr(String operation, int left) {
            this.operation = operation;
            this.left = new Expr(left);
        }

        public Expr(int value) {
            this.value = value;
        }

        public Expr() {

        }

        public boolean hasValue(){
            return value !=null;
        }

        public Expr add(Expr other
        ) {
            if(this.hasValue() && other.hasValue())
                return new Expr(this.value+ other.value);
            else
                return new Expr("add",this,other);
        }
    }

    static class Match {
        int indexA;
        int indexB;
        int offset;

        public Match(int indexA, int indexB, int offset) {
            this.indexA = indexA;
            this.indexB = indexB;
            this.offset = offset;
        }
    }

    static class ALU {

        int[] variables = new int[4]; //w,x,y,z
        Expr[] reg = new Expr[4];

        List<Instruction> instructionSet = new ArrayList<>();

        public ALU(String[] instructions) {
            for (String instr : instructions) {
                if(instr.matches("mul [xyzw] 1")) continue;
                if(instr.matches("div [xyzw] 1")) continue;
                if(instr.matches("add [xyzw] 0")) continue;
                this.instructionSet.add(new Instruction(instr.split(" ")));
            }
            for (int i = 0; i < reg.length; i++) {
                reg[i]= new Expr(0);
            }
        }

        public void addInstruction(String instruction) {
            this.instructionSet.add(new Instruction(instruction.split(" ")));
        }

        public boolean isValid() {
            return variables[3] == 0;
        }

        private List<Match> getConstraints() {
            List<Match> matches = new ArrayList<>();
            int index = 0;
            for (Instruction instruction : instructionSet) {
                if (instruction.operation.equals("inp")) {
                    reg[instruction.lhs] = new Expr("inp", index);
                    index++;
                } else {
                    Expr rhs;
                    if (NumberUtils.isNumber(instruction.rhs)) {
                        rhs = new Expr(Integer.parseInt(instruction.rhs));
                    } else {
                        rhs = reg[instruction.rhs.charAt(0) - 'w'];
                    }
                    reg[instruction.lhs] = simplify(new Expr(instruction.operation, reg[instruction.lhs], rhs), matches);
                }
            }
            return matches;
        }

        private Expr simplify(Expr expr, List<Match> matches) {
            if (expr.left.hasValue() && expr.right.hasValue()) {
                return numericOperation(expr);
            } else if (expr.left.hasValue() && expr.operation.matches("add|mul|eql")) {
                return simplify(new Expr(expr.operation, expr.right, expr.left), matches);
            }

            switch (expr.operation) {
                case "add":
                    if (expr.right != null && expr.right.hasValue() && expr.right.value == 0) {//x+0=x
                        return expr.left;
                    }

                    if (expr.left.operation.equals("add")) {//Associative
                        return new Expr("add", expr.left.left, expr.left.right.add(expr.right));
                    }
                    break;
                case "mul":
                    if (expr.right.value == 0) {//x*0=0
                        return new Expr(0);
                    }

                    if (expr.right.value == 1) {//x*1=x
                        return expr.left;
                    }
                    break;
                case "eql":
                    Integer offset = 0;
                    if (expr.left.right != null)
                        offset = expr.left.right.value;

                    // from input cases like (x % 26) + offset greater than 9 == input[i] will be false hence 0
                    if (expr.right.hasValue() || offset > 9) {
                        return new Expr(0);
                    }
                    //input[i] + offset == input[j] is true is what we want so adding that as matching value
                    matches.add(new Match(expr.left.left.left.value, expr.right.left.value, offset));
                    return new Expr(1);

                case "div":
                    if (expr.right.value == 1)
                        return expr.left;
                    else
                        return expr.left.left.left;
                case "mod":
                    if (expr.left.left.operation.equals("inp"))
                        return expr.left;
                    else
                        return expr.left.right;
            }

            return expr;
        }

        private Expr numericOperation(Expr expr) {
            switch (expr.operation) {
                case "add":
                    return new Expr(expr.left.value + expr.right.value);
                case "mul":
                    return new Expr(expr.left.value * expr.right.value);
                case "div":
                    return new Expr(Math.floorDiv(expr.left.value, expr.right.value));
                case "mod":
                    return new Expr(expr.left.value % expr.right.value);
                case "eql":
                    return new Expr(expr.left.value.equals(expr.right.value) ? 1 : 0);
                default:
                    return new Expr();
            }
        }

        public String maximize() {
            int[] number = new int[14];
            List<Match> matches = getConstraints();
            for (Match m : matches) {
                //Explained earlier, matches are found based on input[i]+offset ==input[j]. Maximising that value here
                number[m.indexA] = 9 - Math.max(m.offset, 0);
                number[m.indexB] = number[m.indexA] + m.offset;
            }
            return
                    Arrays.stream(number)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(""));
        }

        public String minimize() {
            int[] number = new int[14];
            List<Match> matches = getConstraints();
            for (Match m : matches) {
                number[m.indexA] = 1 - Math.min(m.offset, 0);
                number[m.indexB] = number[m.indexA] + m.offset;
            }
            return
                    Arrays.stream(number)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(""));
        }
    }

    public Day24(Boolean isTest) {
        super(isTest);
    }


    public String part1() {
        String[] input = this.getInput().split("\r\n");
        ALU alu= new ALU(input);

        String result = alu.maximize();
        System.out.printf("Largest model numbers: %s%n", result);
        return String.valueOf(result);
    }


    public String part2() {
        String[] input = this.getInput().split("\r\n");
        ALU alu= new ALU(input);
        String result = alu.minimize();
        System.out.printf("Smallest model number: %s%n", result);
        return String.valueOf(result);
    }
}