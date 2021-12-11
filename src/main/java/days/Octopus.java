package days;

public class Octopus {
    private int energy;

    public Octopus(int energy) {
        this.energy = energy;
    }

    public boolean step(){
        if(++energy>9){
            energy=0;
            return true;
        }

        return false;
    }

    public int print(){
        return energy;
    }
}
