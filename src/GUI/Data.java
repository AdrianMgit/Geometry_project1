package GUI;

import com.company.Line;
import com.company.Point2D;
import com.company.Polygon;
import com.company.Triangle;

import java.util.ArrayList;

public class Data {
    private com.company.Point2D point;
    private ArrayList<com.company.Point2D> punkciki;
    private ArrayList<Line> lines;
    private Triangle triangle;
    private Polygon polygon;
    private Point2D pointCircle;
    private double circleRay;
    private int kat;

    public Data(){
        this.punkciki=new ArrayList<>();
    }


    public void zerujData(){
        this.pointCircle=null;
        this.polygon=null;
        this.triangle=null;
        this.lines=null;
        this.point=null;
        this.punkciki=new ArrayList<>();


    }





    public void setKat(int kat){
        this.kat=kat;
    }

    public int getKat(){
        return kat;
    }


    public void setCircleRay(double ray){
        this.circleRay=ray;
    }

    public double getCircleRay(){
        return circleRay;
    }

    public Point2D getPointCircle() {
        return pointCircle;
    }

    public void setPointCircle(Point2D pointCircle) {
        this.pointCircle = pointCircle;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(ArrayList<Point2D> points) {
        this.polygon=new Polygon(points);
    }

    public Triangle getTriangle() {
        return triangle;
    }

    public void setTriangle(Point2D w1,Point2D w2,Point2D w3) {
        this.triangle = new Triangle(w1,w2,w3);
    }

    public void setPunkciki(ArrayList<Point2D> punkciki) {
        this.punkciki = punkciki;
    }

    public ArrayList<Line> getLine() {
        return lines;
    }

    public void setLine() {
        this.lines = new ArrayList<>();
    }

    public void addLine(Point2D head,Point2D tail) {
        this.lines.add(new Line(head,tail));
    }

    public com.company.Point2D getPoint() {
        return point;
    }

    public void setPoint(com.company.Point2D point) {
        this.point = point;
    }


    public ArrayList<com.company.Point2D> getPunkciki() {
        return punkciki;
    }

    public void addPointToList(Point2D point) {
        punkciki.add(point);
    }
}
