package game.utils;

public class Tuple {
    public int first;
    public int second;

    public Tuple(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public void setBoth(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public Tuple clone() {
        return new Tuple(this.first, this.second);
    }

    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }
}
