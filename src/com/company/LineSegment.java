package com.company;

public class LineSegment {
    private Point2D head;
    private Point2D tail;

    public LineSegment(double x1,double y1,double x2,double y2){
        head = new Point2D(x1, y1);
        tail = new Point2D(x2, y2);
    }

    public Point2D getHead() {
        return head;
    }

    public Point2D getTail() {
        return tail;
    }

    public void setHead(Point2D head) {
        this.head = head;
    }

    public void setTail(Point2D tail) {
        this.tail = tail;
    }
}
