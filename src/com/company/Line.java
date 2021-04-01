package com.company;

public class Line {
   private Point2D head;
   private Point2D tail;

    public Line(Point2D head, Point2D tail) {
        if(head.getX()<tail.getX()) {                   //mozna podac punkty w losowej kolejnosci
            this.head = head;                           //linia musi miec poczatek x(head) mniejszy od konca
            this.tail = tail;                           //dla poprawnosci dzialania algorytmow
        }
        else {
            this.head = tail;
            this.tail = head;
        }
    }

   public Line(double x1, double y1, double x2, double y2) {
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

