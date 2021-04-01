package com.company;

public class Triangle {
    private Point2D w1;
    private Point2D w2;
    private Point2D w3;

    public Triangle(Point2D w1,Point2D w2,Point2D w3){
        this.w1=w1;
        this.w2=w2;
        this.w3=w3;
    }

    public Point2D getW1() {
        return w1;
    }

    public Point2D getW2() {
        return w2;
    }

    public Point2D getW3() {
        return w3;
    }
}
