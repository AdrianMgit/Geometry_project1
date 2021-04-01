package KDTree;
import com.company.Point2D;

import java.util.*;



public class KdTree {

    public Node Root;

    public KdTree(List<Point2D> points){
        Root=makeTree(points,0);
    }


    public Node findNearestPoint(Point2D point) {
        if (Root != null)
            return NearestPoint.find(Root, point);
        else return null;
    }



    private Node makeTree(List<Point2D> points, int depth) {
        if (points.isEmpty()) {         //gdy nie ma punktow na liscie to wezel jest lisciem
            return null;
        }

        int osSortowania = depth % 2;           //wspolrzedne punktu po ktorych je sortuje, naprzemian raz x raz y
        //0 to x,y to 1

        Collections.sort(points, new Comparator<Point2D>() {        //sortuje punkty ze wzgledu na dana os
                    @Override
                    public int compare(Point2D o1, Point2D o2) {
                        return o1.getCoords()[osSortowania].compareTo(o2.getCoords()[osSortowania]);
                    }
                }
        );

        int ilPunktow = points.size();
        int mediana = ilPunktow / 2;

        Point2D punktMediany = points.get(mediana);          //punkt ktory jest mediana w danym wywolaniu

        List<Point2D> lewaMediana = points.subList(0, mediana);     //punkty znajdujacae sie na lewo od mediany
        Node left = makeTree(lewaMediana, depth + 1);      //najpierw tworze lewe poddrzewa az do liscia
        //potem w gore wstawiam prawe poddrzewa
        List<Point2D> prawaMediana = points.subList(mediana + 1, ilPunktow);
        Node right;
        if(ilPunktow>1)                    //jesli zostal mi jeden punkt to ustawiam prawego syna na null
            right =makeTree(prawaMediana, depth + 1);                    //a ten punkt wrzucam do aktualnego wezla
        else
            right =null;

        return new Node(left, right, punktMediany, depth);
    }
}

