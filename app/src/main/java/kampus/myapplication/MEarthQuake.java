package kampus.myapplication;

/**
 * Created by TanNT on 12/5/17.
 */

public class MEarthQuake {
    private double mag;
    private int tsunami;

    public MEarthQuake(double mag, int tsunami) {
        this.mag = mag;
        this.tsunami = tsunami;
    }

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public int getTsunami() {
        return tsunami;
    }

    public void setTsunami(int tsunami) {
        this.tsunami = tsunami;
    }
}
