import days.Day;

import java.lang.reflect.InvocationTargetException;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("Advent Of Code 2021");
        int[] days = new int[]{6};
        for (int day: days) {
            Day instance = (Day) Class.forName("days.Day" + day).getDeclaredConstructor(Boolean.class).newInstance(false);
            instance.run();
        }
    }
}
