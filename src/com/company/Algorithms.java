package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.StrictMath.*;

public class Algorithms {

                    //WYZNACZENIE ROWNANIA PROSTEJ
    public static double[] wyznacz_prosta(Line l) {
        double tab[]=new double[2];
        double a = (l.getHead().getY() - l.getTail().getY()) / (l.getHead().getX() - l.getTail().getX());
        double b = l.getHead().getY() - a * l.getHead().getX();
        //System.out.println("Rownanie prostej ma postac: y = "+a+"x+"+b);
        tab[0]=a;
        tab[1]=b;
        return tab;
    }


                //PRZESUNIECIE PROSTEJ O PUNKT
    public static void przesuniecie(Line l, int x, int y) {

        l.getHead().setX(l.getHead().getX()+x);
        l.getHead().setY(l.getHead().getY()+y);
        l.getTail().setX(l.getTail().getX()+x);
        l.getTail().setY(l.getTail().getY()+y);

    }


                //OBROT PROSTEJ O KAT ALE TYLKO WZGLEDEM POCZATKU UKLADU WSPOLRZEDNYCH
    public static void obrot(Point2D p, int kat) {
        double kat_w_radianach = (kat*Math.PI) / 180;
        System.out.println("Kat w radianach: "+kat_w_radianach);
        double stary_x = p.getX();
        double stary_y=p.getY();
        p.setX(stary_x*cos(kat_w_radianach)-stary_y*sin(kat_w_radianach));
        p.setY(stary_y*cos(kat_w_radianach)+stary_x*sin(kat_w_radianach));
        //System.out.println("Wspolrzedne punktu po obroceniu o kat "+kat+" wynosza\nx = "+p.getX()+" y = "+p.getY());
    }


                //ODBICIE PUNKTU WZGLEDEM OSI
    public static void odbicie(Point2D p, char os) {
        if (os == 'x')
            p.setY(-1*p.getY());
        else if (os == 'y')
            p.setX(-1*p.getX());
        else {
            System.out.println("Podano bledny znak osi obrotu");
            System.out.println("Dla obrotu wzgledem osi x wpisz 'x'");
            System.out.println("Dla obrotu wzgledem osi y wpisz 'y'");
        }
    }



    //								LAB 2





                            //WYZNACZENIE ROWNANIA PROSTEJ PRZECHODZACEJ PRZEZ DWA PUNKTY
    public static void calcFunc(Point2D p1, Point2D p2, double[] tab) {
        double a = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        double b = p1.getY() - a * p1.getX();
        tab[0] = a;
        tab[1] = b;
    }



                //SPRAWDZENIE PO KTOREJ STRONIE PROSTEJ LEZY PUNKT
    public static int whichSide(Line l1, Point2D p3) {
        double tab[]=new double[2];
        calcFunc(l1.getHead(), l1.getTail(), tab);
        double wynik = tab[0] * p3.getX() - p3.getY() + tab[1];
        if (wynik < 0) {
            System.out.println("Punkt lezy z lewej strony podanej prostej");
            return -1;
        }
        else if (wynik > 0) {
            System.out.println("Punkt lezy z prawej strony podanej prostej");
            return 1;
        }
        else if (wynik ==0) {
            System.out.println("Punkt lezy na podanej prostej");
            return 0;
        }
        return 0;
    }


                //OBLICZENIE WSPOLRZEDNYCH PUNKTU PRZECIECIA DWOCH PROSTYCH,KTORYCH ROWNANIA PODAWANE SA POPRZEZ WSPOLCZYNNIKI
    public static void crossingPointCramer(double A1, double B1, double C1, double A2, double B2, double C2) {
        double w = (A1*B2) - (B1*A2);
        double wx = (-1 * C1*B2) - (B1*(-1 * C2));
        double wy = (A1*(-1 * C2)) - ((-1 * C1)*A2);
        double x = wx / w;
        double y = wy / w;
        System.out.println("Wspolrzedne punktu przeciecia: \n x: "+x+"  y: "+y);
    }



    //OBLICZENIE WSPOLRZEDNYCH PUNKTU PRZECIECIA DWOCH PROSTYCH,PROSTE PODAWANE SA JAKO OBIEKTY KLASY LINE
    public static double[] crossingPointCramer2(Line l1,Line l2) {
        double tab1[]=new double[2];
        calcFunc(l1.getHead(), l1.getTail(), tab1);
        double tab2[]=new double[2];
        calcFunc(l2.getHead(), l2.getTail(), tab2);

        double A1 = tab1[0];
        double B1 = -1;
        double C1 = tab1[1];

        double A2 = tab2[0];
        double B2 = -1;
        double C2 = tab2[1];

        double w = (A1*B2) - (B1*A2);
        double wx = (-1 * C1*B2) - (B1*(-1 * C2));
        double wy = (A1*(-1 * C2)) - ((-1 * C1)*A2);
        double x = wx / w;
        double y = wy / w;
        double[]punkt={x,y};
        System.out.println("Wspolrzedne punktu przeciecia: \n x: "+x+" y: "+y);
        return punkt;
    }



                    //OBLICZENIE POLA POWIERCZHNI TROJKATA
    public static double calculateAreaTriangle(Point2D p1, Point2D p2, Point2D p3) {
        double area =abs(0.5 * ((p2.getX() - p1.getX())*(p3.getY() - p1.getY()) - ((p3.getX() - p1.getX())*(p2.getY() - p1.getY()))));
        //System.out.println("Pole wynosi: "+area);
        return area;
    }



                    //OBLICZENIE PUNKTU PRZECIECIA SIE DWOCH ODCINKOW
    public static void crossingPointCramerLineSegment(LineSegment l1, LineSegment l2) {
        double tab1[]=new double[2];
        calcFunc(l1.getHead(), l1.getTail(), tab1);
        double tab2[]=new double[2];
        calcFunc(l2.getHead(), l2.getTail(), tab2);

        double A1 = tab1[0];
        double B1 = -1;
        double C1 = tab1[1];

        double A2 = tab2[0];
        double B2 = -1;
        double C2 = tab2[1];

        double w = (A1*B2) - (B1*A2);
        double wx = (-1 * C1*B2) - (B1*(-1 * C2));
        double wy = (A1*(-1 * C2)) - ((-1 * C1)*A2);			//mam policzony punkt przeciecia ale musze sprawdzic czy znajduje sie
        double x = wx / w;										//wewnatrz odcinka a nie po za nim bo punkt przeciecia jest policzony tak jak dla prostej
        double y = wy / w;										//wiec moze znajdowac sie na prostej na ktorej jest odcinek ale nie w nim

        if (x > l1.getHead().getX() && x<l1.getTail().getX() && x > l2.getHead().getX() && x < l2.getTail().getX()) {		//jesli punkt przeciecia znajduje sie na odcinku l1
            //jesli punkt przeciecia znajduje sie na odcinku l2
            System.out.println("Wspolrzedne punktu przeciecia: \n x: "+x+" y: "+y);
        }
        else
            System.out.println("Podane odcinki sie nie przecinaja");
    }


            //                      LAB 3

                    //SPRAWDZENIE CZY PUNKT LEZY WEWNATRZ TROJKATA
                    //NA PODSTAWIE POL
    public static boolean pointInTriangle(Point2D point,Triangle triangle){
        double poleTrojkata=calculateAreaTriangle(triangle.getW1(),triangle.getW2(),triangle.getW3());
        double pole1=calculateAreaTriangle(point,triangle.getW1(),triangle.getW2());
        double pole2=calculateAreaTriangle(point,triangle.getW2(),triangle.getW3());
        double pole3=calculateAreaTriangle(point,triangle.getW3(),triangle.getW1());

        if(poleTrojkata<(pole1+pole2+pole3))
            return false;
        else return true;
    }


    //                      SPRAWDZENIE CZY PUNKT LEZY WEWNATRZ WIELOKATU
    //                      METODA TESTU TROJKATOW - PROBLEM GDY PUNKT ZNAJDUJE SIE NA BOKU TROJKATA, WTEDY NALEZY JEDNOCZESNIE DO DWOCH TROJKATOW - BLEDNY WYNIK
    //                      STWORZYLEM ZNACZNIK KTORY USTAWIAM GDY PUNKT LEZY NA BOKU TROJKATA, GDY ZNACZNIK USTAWIONY TO JUZ DLA
    //                      DRUGIEGO TROJKATA NIE LICZE PRZYNALEZNOSCI PUNKTU

    public static boolean pointInPolygon(Point2D point,Polygon polygon) {
        if(polygon.getPoints().size()>2) {
            int iloscTrojkatowWKtorychJestPunkt = 0;
            Point2D srodekWachlarza = polygon.getPoints().get(0);
            int ileRazyNaprostej = 0;
            Point2D w1;
            Point2D w2;

            //sprawdzenie czy punkt znajduje sie wewnatrz bryly brzegowej - przyspiesza algorytm

            double x_min;
            double x_max;
            double y_min;
            double y_max;
            x_max = x_min = polygon.points.get(0).getX();           //inicjalizacja wartosci poczatkowych
            y_max = y_min = polygon.points.get(0).getY();
            for (int i = 0; i < polygon.getPoints().size(); i++) {
                if (polygon.getPoints().get(i).getX() < x_min)
                    x_min = polygon.getPoints().get(i).getX();
                else if (polygon.getPoints().get(i).getX() > x_max)
                    x_max = polygon.getPoints().get(i).getX();
                if (polygon.getPoints().get(i).getY() < y_min)
                    y_min = polygon.getPoints().get(i).getY();
                else if (polygon.getPoints().get(i).getY() > y_max)
                    y_max = polygon.getPoints().get(i).getY();
            }


            //ALGORYTM DZIALA TYLKO GDY PUNKT ZNAJDUJE SIE W OBSZARZE
            if (point.getX() >= x_min && point.getX() <= x_max && point.getY() >= y_min && point.getY() <= y_max) {

                for (int i = 1; i + 1 < polygon.getPoints().size(); i++) {
                    w1 = polygon.getPoints().get(i);
                    w2 = polygon.getPoints().get(i + 1);

                    if (pointInTriangle(point, new Triangle(srodekWachlarza, w1, w2)) == true)
                        if (whichSide(new Line(srodekWachlarza, w1), point) == 0 || whichSide(new Line(srodekWachlarza, w2), point) == 0) {
                            if (ileRazyNaprostej == 0)
                                iloscTrojkatowWKtorychJestPunkt++;
                            ileRazyNaprostej++;
                        } else iloscTrojkatowWKtorychJestPunkt++;
                }
                if (iloscTrojkatowWKtorychJestPunkt % 2 == 0)
                    return false;
                else return true;
            }
        }
        return false;
    }



    public static int crossingPointsCircle(Line line,double r,Point2D point){
        double x1=line.getHead().getX();
        double y1=line.getHead().getY();
        double x2=line.getTail().getX();
        double y2=line.getTail().getY();
        double x0=point.getX();
        double y0=point.getY();

        double a=(x1*x1)+(y1*y1)+(x2*x2)+(y2*y2)-2*((x1*x2)+(y1*y2));
        double b=2*(x0*(x2-x1)+y0*(y2-y1)+(x1*x2)+(y1*y2)-(x2*x2)-(y2*y2));
        double c=-1*(r*r)+(x2*x2)+(y2*y2)+(x0*x0)+(y0*y0)-2*(x0*x2+y0*y2);

        double delta=(b*b)-1*(4*a*c);


        if(delta>0)
            return 2;
        else if(delta==0)
            return 1;
        else
            return 0;
    }



    //WYZNACZENIE ILOSCI PUNKTOW PRZECIEC PROSTEJ Z OKREGIEM O PODANYM PROMIENIU I SRODKU, NA PODSTAWIE ODLEGLOSCI PROSTEJ OD SRODKA PUNKTU (SRODKA OKREGU)

    public static int crossingPointsCircle2(Line line,double r,double xsrodka,double ysrodka){         //xsrodak i y srodka to wspol srodka okregu
    double[] wsp=wyznacz_prosta(line);
    double odlodsrodka=(abs(wsp[0]*xsrodka-1*ysrodka+wsp[1]))/sqrt(pow(wsp[0],2)+1);
    //System.out.println("Odleglosc: "+odlodsrodka);
    if(odlodsrodka<r)
        return 2;
    else if(odlodsrodka==r)
        return 1;
    else return 0;
    }

            //KAT POMIEDZY WEKTORAMI
    public static double calcVectorsAngle(Point2D point1,Point2D point2,Point2D point3){
        if((point1.getX()==point2.getX() && point1.getY()==point2.getY())|| (point1.getX()==point3.getX() && point1.getY()==point3.getY()) || (point2.getX()==point3.getX() && point2.getY()==point3.getY()) )
            return 0;

        else {
            double[] vectA = new double[2];
            double[] vectB = new double[2];
            double kat;
            vectA[0] = point1.getX() - point2.getX();
            vectA[1] = point1.getY() - point2.getY();
            vectB[0] = point3.getX() - point2.getX();
            vectB[1] = point3.getY() - point2.getY();

            double dlVecA = sqrt(pow(vectA[0], 2) + pow(vectA[1], 2));
            double dlVecB = sqrt(pow(vectB[0], 2) + pow(vectB[1], 2));

            kat = acos((vectA[0] * vectB[0] + vectA[1] * vectB[1]) / (dlVecA * dlVecB));
            kat = kat * 180 / Math.PI;                //przeliczenie z radianow na stopnie
            return kat;
        }
    }


            //OTOCZKA WYPUKLA
    public static ArrayList<Point2D> otoczka(ArrayList<Point2D> points){
        ArrayList<Point2D> punktyOtoczki=new ArrayList<>();
        if(points.isEmpty()==false) {
            //wyznaczenie punktu o najmniejszym y, jeslij jest ich wiecej to najmniejsza x
            Point2D p1 = new Point2D(points.get(0).getX(),points.get(0).getY());
            for (int i = 0; i < points.size(); i++) {
                    if(points.get(i).getY()<p1.getY())
                        p1=points.get(i);
                    if(points.get(i).getY()==p1.getY())
                        if(points.get(i).getX()<p1.getX())
                            p1=points.get(i);
            }

            punktyOtoczki.add(p1);


            double kat=0;
            Point2D p0=new Point2D(p1.getX()-10,p1.getY());
                //inicjuje punkt p2 ale nie moze on by punktem p1 bo wtedy nie wejde do while
            Point2D p2=new Point2D(0,0);
            if(points.get(0).getY()!=p1.getY() && points.get(0).getX()!=p1.getX()){
                p2.setX(points.get(0).getX());
                p2.setY(points.get(0).getY());

            }
            else{
                p2.setX(points.get(1).getX());
                p2.setY(points.get(1).getY());
            }

            int licznik=0;
            Point2D pp=new Point2D(p1.getX(),p1.getY());

            while( p2.getY()!=p1.getY() || p2.getX()!=p1.getX()){
                licznik++;
                kat=0;
                    if(licznik==1) {
                        for(int i=0;i<points.size();i++){
                        if(Algorithms.calcVectorsAngle(p0,pp,points.get(i))>kat){
                            kat=Algorithms.calcVectorsAngle(p0,pp,points.get(i));

                            p2.setX(points.get(i).getX());
                            p2.setY(points.get(i).getY());
                        }
                    }
                        punktyOtoczki.add(new Point2D(p2.getX(),p2.getY()));
                }
                    else{

                        p0.setX(pp.getX());
                        p0.setY(pp.getY());

                        pp.setX(p2.getX());
                        pp.setY(p2.getY());

                        for(int i=0;i<points.size();i++){

                            if(Algorithms.calcVectorsAngle(p0,pp,points.get(i))>kat){
                                kat=Algorithms.calcVectorsAngle(p0,pp,points.get(i));
                                p2.setX(points.get(i).getX());
                                p2.setY(points.get(i).getY());
                            }
                        }
                        punktyOtoczki.add(new Point2D(p2.getX(),p2.getY()));
                    }
            }

        }
        return punktyOtoczki;
    }

            //OBROT PUNKTU WZGLEDEM DRUGIEGO PUNKTU    P1 OBRACANY WZGLEDEM P2
    public static void obrot2(Point2D p1,Point2D p2, int kat) {
        double kat_w_radianach = (kat*Math.PI) / 180;
        System.out.println("Kat w radianach: "+kat_w_radianach);
        double stary_x = p1.getX();
        double stary_y=p1.getY();
        p1.setX((stary_x-p2.getX())*cos(kat_w_radianach)-(stary_y-p2.getY())*sin(kat_w_radianach)+p2.getX());
        p1.setX((stary_x-p2.getX())*sin(kat_w_radianach)+(stary_y-p2.getY())*cos(kat_w_radianach)+p2.getY());
        //System.out.println("Wspolrzedne punktu po obroceniu o kat "+kat+" wynosza\nx = "+p.getX()+" y = "+p.getY());
    }









}
