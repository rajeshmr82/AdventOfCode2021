import days.Day1.Day1;

public class Application {

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Advent Of Code 2021\n");
        (new Day1()).run();
    }
}
