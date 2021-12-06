package days;

public class LanternFish {

    private int timer;

    public LanternFish(int timer) {
        this.timer = timer;
    }

    public boolean doCycle(){
        if(timer--==0){
            timer = 6;
            return true;
        }

        return false;
    }

    public double doCycles(int n){
        n = n-timer;
        return Math.pow((1+8),n);
    }
}
