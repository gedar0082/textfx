package sample;

import java.util.Objects;

public class Dot {

    private int x;
    private double y;

    public Dot(int x, double y){
        this.x = x;
        this.y = y;
    }

    public Dot(){
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dot dot = (Dot) o;
        return x == dot.x &&
                y == dot.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
