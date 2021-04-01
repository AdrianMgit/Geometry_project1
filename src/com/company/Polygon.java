package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Polygon {
    ArrayList<Point2D> points;

    public Polygon(){
        points=new ArrayList<>();
    }

    public Polygon(ArrayList<Point2D> points){
        this.points=points;
    }

    public void addVertex(Point2D point){
        points.add(point);
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }
}
