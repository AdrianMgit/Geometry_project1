package GUI;

import KDTree.KdTree;
import com.company.Algorithms;
import com.company.Point2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Guii extends JFrame {
    private JPanel rootPanel;
    JButton positionCheckLineButton;
    JButton positionCheckTriangleButton;
    JButton positionCheckPolygonButton;
    JButton circleCrossButton;
    JButton calculateTriangleAreaButton;
    JButton calcFuncLineButton;
    JButton crossingPointButton;
    JButton rotateLineButton;
    JButton otoczkaButton;
    JButton KDTreeButton;
    DrawGui drawClass;

    int podzialka=20;
    Data data;
    int licznik;
    int ilPunktow=-1;
    int operacja=-1;


    public Guii() {

        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("GUI");
        setLocationRelativeTo(null);
        createComponents();



                                     //PRZYCISKI

        positionCheckLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();

                JOptionPane.showMessageDialog(null,"Podaj punkt do sprawdzenia i rysuj prosta");
                licznik=0;
                ilPunktow=3;                //3 punkty bo 1 to punkt a dwa nastepne do wyznaczenia prostej
                operacja=0;
            }
        });


        positionCheckTriangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();

                JOptionPane.showMessageDialog(null,"Podaj punkt do sprawdzenia i rysuj trojkat");
                licznik=0;
                ilPunktow=4;                // 1 to punkt a 3 nastepne wierzcholki trojkata
                operacja=1;
            }
        });

        positionCheckPolygonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();
                operacja = -1;
                ilPunktow=0;

                JTextField fieldWierzcholki = new JTextField();
                Object[] fields = {"Podaj punkt do sprawdzenia i rysuj wielokat: ", "Ilosc wierzcholkow: ", fieldWierzcholki,};
                JOptionPane.showConfirmDialog(null, fields, "Information", JOptionPane.OK_CANCEL_OPTION);

                if (fieldWierzcholki.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Nie podano ilosci wierzcholkow");
                else {
                    try {
                        licznik = 0;
                        ilPunktow = Integer.parseInt(fieldWierzcholki.getText()) + 1;                // 1 to punkt a nastepne to wierzcholki
                        operacja = 2;
                    } catch (NumberFormatException exceptionDouble) {
                        JOptionPane.showMessageDialog(null, "Ilosc wiercholkow musi byc liczba");

                    }
                }
            }
        });


        circleCrossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();
                operacja = -1;
                ilPunktow=0;

                JTextField fieldRay=new JTextField();
                Object[] fields = {"Podaj srodek okregu i rysuj prosta: ","Promien : ", fieldRay, };
                JOptionPane.showConfirmDialog(null, fields, "Information", JOptionPane.OK_CANCEL_OPTION);

                if (fieldRay.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Nie podano promienia okregu");
                else {
                    try {
                        licznik = 0;
                        ilPunktow = 3;                // 1 to srodek a dwa do wyznaczenia prostej
                        data.setCircleRay(Double.parseDouble(fieldRay.getText()));
                        operacja = 3;
                    }
                    catch (NumberFormatException exceptionDouble) {
                        JOptionPane.showMessageDialog(null, "Promien okregu musi byc liczba");

                    }
                }
            }
        });

        calculateTriangleAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();

                JOptionPane.showMessageDialog(null,"Zaznacz wiercholki trojkata");
                licznik=0;
                ilPunktow=3;
                operacja=4;
            }
        });


        calcFuncLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();

                JOptionPane.showMessageDialog(null,"Podaj dwa punkty na prostej");
                licznik=0;
                ilPunktow=2;
                operacja=5;
            }
        });

        crossingPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();

                JOptionPane.showMessageDialog(null,"Narysuj dwie proste");
                licznik=0;
                ilPunktow=4;
                operacja=6;
            }
        });

        rotateLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();
                operacja = -1;
                ilPunktow=0;

                JTextField fieldKat=new JTextField();
                Object[] fields = {"Podaj kat i narysuj polprosta: \n","Kat : ", fieldKat, };
                JOptionPane.showConfirmDialog(null, fields, "Information", JOptionPane.OK_CANCEL_OPTION);
                if (fieldKat.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Nie podano kata obrotu");
                else {
                        try {
                            licznik = 0;
                            ilPunktow = 1;
                            operacja = 7;
                            data.setKat(Integer.parseInt(fieldKat.getText()));
                        }
                        catch (NumberFormatException exceptionDouble){
                            JOptionPane.showMessageDialog(null,"Kat musi byc liczba");
                        }
                }
            }
        });

        otoczkaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();
                operacja = -1;
                ilPunktow=0;

                JTextField fieldPoints=new JTextField();
                Object[] fields = {"Podaj ilosc punktow i rysuj punkty: \n","Ilosc punktow: ", fieldPoints, };
                JOptionPane.showConfirmDialog(null, fields, "Information", JOptionPane.OK_CANCEL_OPTION);

                if (fieldPoints.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Nie podano ilosci punktow");
                else {
                    try {

                        licznik = 0;
                        ilPunktow = Integer.parseInt(fieldPoints.getText());
                        operacja = 8;
                    } catch (NumberFormatException exceptionDouble) {
                        JOptionPane.showMessageDialog(null, "Ilosc punktow musi byc liczba");
                    }
                }
            }
        });

        KDTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                data.zerujData();
                operacja = -1;
                ilPunktow=0;

                JTextField fieldPoints=new JTextField();
                Object[] fields = {"Podaj ilosc punktow i rysuj punkty: \n","Ilosc punktow: ", fieldPoints, };
                JOptionPane.showConfirmDialog(null, fields, "Information", JOptionPane.OK_CANCEL_OPTION);

                if (fieldPoints.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Nie podano ilosci punktow");
                else {
                    try {

                        licznik = 0;
                        ilPunktow = Integer.parseInt(fieldPoints.getText());
                        operacja = 9;
                    }
                    catch (NumberFormatException exceptionDouble){
                        JOptionPane.showMessageDialog(null,"Ilosc punktow musi byc liczba");
                    }
                }
            }
        });




        //ZAZNACZANIE PUNKTOW
        drawClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(licznik<ilPunktow) {
                    licznik++;
                    System.out.println("Licznik " + licznik);
                    int przekszxnazerzero = e.getX() - (drawClass.width / 2);
                    int przekszynazerzero = -(e.getY() - (drawClass.height / 2));

                    System.out.println("X: " + e.getX() + "  Y: " + e.getY());
                    System.out.println("X: " + (przekszxnazerzero) + "  Y: " + (przekszynazerzero));
                    data.addPointToList(new Point2D(przekszxnazerzero, przekszynazerzero));
                    drawClass.repaint();
                    rootPanel.repaint();
                }
                if(licznik==ilPunktow){
                    switch(operacja){
                        case 0:
                            sidePointLine();
                            break;
                        case 1:
                            sidePointTriangle();
                            break;
                        case 2:
                            sidePolygonTriangle();
                            break;
                        case 3:
                            crossCircle();
                            break;
                        case 4:
                            calcTrianArea();
                            break;
                        case 5:
                            calcFunLine();
                            break;
                        case 6:
                            findCrossingPoint();
                            break;
                        case 7:
                            rotateLine();
                            break;
                        case 8:
                            otoczka();
                            break;
                        case 9:
                            KDtr();
                            break;
                    }
                    drawClass.repaint();
                    rootPanel.repaint();
                }
            }
        });

    }

    private void sidePointLine(){
        data.setPoint(data.getPunkciki().get(0));
        data.setLine();
        data.addLine(data.getPunkciki().get(1),data.getPunkciki().get(2));
        int location=Algorithms.whichSide(data.getLine().get(0), data.getPoint());
        if(location==-1)
            JOptionPane.showMessageDialog(null,"Punkt lezy po lewej stronie prostej");
        else if(location==0){
            JOptionPane.showMessageDialog(null,"Punkt lezy na stronie prostej");
        }
        else if(location==1){
            JOptionPane.showMessageDialog(null,"Punkt lezy po prawej stronie prostej");
        }

    }


    private void sidePointTriangle(){
        data.setPoint(data.getPunkciki().get(0));
        data.setTriangle(data.getPunkciki().get(1),data.getPunkciki().get(2),data.getPunkciki().get(3));
        boolean inside=Algorithms.pointInTriangle(data.getPoint(),data.getTriangle());
        if(inside==true)
            JOptionPane.showMessageDialog(null,"Punkt lezy wewnatrz trojkata");
        else
            JOptionPane.showMessageDialog(null,"Punkt lezy poza trojkatem");
    }

    private void sidePolygonTriangle(){
        data.setPoint(data.getPunkciki().get(0));
        ArrayList<Point2D> pp=new ArrayList<>();
        for(int i=1;i<data.getPunkciki().size();i++){
            pp.add(data.getPunkciki().get(i));
        }
        data.setPolygon(pp);
        boolean inside=Algorithms.pointInPolygon(data.getPoint(),data.getPolygon());
        if(inside==true)
            JOptionPane.showMessageDialog(null,"Punkt lezy wewnatrz wielokata");
        else
            JOptionPane.showMessageDialog(null,"Punkt lezy poza wielokatem");

    }

    private void crossCircle(){
        data.setPointCircle(data.getPunkciki().get(0));
        data.setLine();
        data.addLine(data.getPunkciki().get(1),data.getPunkciki().get(2));
        int crossingPointCount=Algorithms.crossingPointsCircle(data.getLine().get(0),data.getCircleRay(),data.getPointCircle());
        if(crossingPointCount==2)
            JOptionPane.showMessageDialog(null,"Prosta przecina okrag w dwoch punktach");
        else if(crossingPointCount==1){
            JOptionPane.showMessageDialog(null,"Prosta przecina okrag w jednym punkcie");
        }
        else
            JOptionPane.showMessageDialog(null,"Prosta nie przecina okregu");
    }

    private void calcTrianArea(){
        data.setTriangle(data.getPunkciki().get(0),data.getPunkciki().get(1),data.getPunkciki().get(2));
        double area=Algorithms.calculateAreaTriangle(data.getPunkciki().get(0),data.getPunkciki().get(1),data.getPunkciki().get(2));
        JOptionPane.showMessageDialog(null,"Pole trojkata wynosi:  "+area);

    }

    private void calcFunLine(){
        data.setLine();
        data.addLine(data.getPunkciki().get(0),data.getPunkciki().get(1));
        double []tabWsp=new double[2];
        Algorithms.calcFunc(data.getPunkciki().get(0),data.getPunkciki().get(1),tabWsp);
        JOptionPane.showMessageDialog(null,"Rownanie prostej:  "+tabWsp[0]+"x+"+tabWsp[1]);

    }


private void findCrossingPoint(){
        data.setLine();
        data.addLine(data.getPunkciki().get(0),data.getPunkciki().get(1));
         data.addLine(data.getPunkciki().get(2),data.getPunkciki().get(3));
        double[] punkt=Algorithms.crossingPointCramer2(data.getLine().get(0),data.getLine().get(1));
        data.getPunkciki().add(new Point2D(punkt[0],punkt[1]));
    JOptionPane.showMessageDialog(null,"Linie przecinaja sie w punkcie:\n x: "+punkt[0]+"  y"+punkt[1]);

}

private void  rotateLine(){
        data.setLine();
    Point2D srodek=new Point2D(0,0);
    data.addLine(srodek,data.getPunkciki().get(0));
    Point2D head=new Point2D(data.getPunkciki().get(0).getX(),data.getPunkciki().get(0).getY());
    Algorithms.obrot(head,data.getKat());
    System.out.println("Kat odbicia: "+data.getKat());
    data.addLine(srodek,head);
    data.addPointToList(head);
}

private void  otoczka(){
        data.setLine();
        ArrayList<Point2D> punktyOtoczki=Algorithms.otoczka(data.getPunkciki());
    for(int i=0;i<punktyOtoczki.size()-1;i++){
        data.addLine(punktyOtoczki.get(i),punktyOtoczki.get(i+1));
    }
}

    private void KDtr(){
        data.setLine();
        //ArrayList<Point2D> points= new ArrayList<Point2D>(data.getPunkciki());
        //KdTree tree=new KdTree(data.getPunkciki());
        Point2D founded;
        for(int i=0; i<data.getPunkciki().size();i++) {
            ArrayList<Point2D> points= new ArrayList<Point2D>(data.getPunkciki());
            points.remove(i);
            KdTree tree=new KdTree(points);
            founded = tree.findNearestPoint(data.getPunkciki().get(i)).getValue();
            data.addLine(data.getPunkciki().get(i),founded);
        }
    }


private void createComponents(){

    this.licznik=0;
    data=new Data();

    positionCheckLineButton = new JButton("Punkt-Prosta");
    positionCheckTriangleButton=new JButton("Punkt-Trojkat");
    positionCheckPolygonButton=new JButton("Punkt-Wielokat");
    circleCrossButton=new JButton("Polprosta-Okrag");
    calculateTriangleAreaButton=new JButton("Pole trojkata");
    calcFuncLineButton=new JButton("Rownanie prostej");
    crossingPointButton=new JButton("Punkt przeciecia");
    rotateLineButton=new JButton("Obrot polprostej");
    otoczkaButton=new JButton("Otoczka wypukla");
    KDTreeButton=new JButton("KDTree");


    rootPanel = new JPanel();   //glowny panel
    rootPanel.setLayout(new BorderLayout());

    drawClass = new DrawGui(800-160,600,podzialka,data);
    rootPanel.add(BorderLayout.CENTER,drawClass);


    JPanel toolPan = new JPanel();
    toolPan.setPreferredSize(new Dimension(160,600));
    FlowLayout g2=new FlowLayout();
    g2.setVgap(22);
    toolPan.setLayout(g2);

    Dimension buttonDimension=new Dimension(140,30);
    rootPanel.add(BorderLayout.EAST, toolPan);
    positionCheckLineButton.setPreferredSize(buttonDimension);
    positionCheckTriangleButton.setPreferredSize(buttonDimension);
    positionCheckPolygonButton.setPreferredSize(buttonDimension);
    circleCrossButton.setPreferredSize(buttonDimension);
    calculateTriangleAreaButton.setPreferredSize(buttonDimension);
    calcFuncLineButton.setPreferredSize(buttonDimension);
    crossingPointButton.setPreferredSize(buttonDimension);
    rotateLineButton.setPreferredSize(buttonDimension);
    otoczkaButton.setPreferredSize(buttonDimension);
    KDTreeButton.setPreferredSize(buttonDimension);

    toolPan.add(positionCheckLineButton);
    toolPan.add(positionCheckTriangleButton);
    toolPan.add(positionCheckPolygonButton);
    toolPan.add(circleCrossButton);
    toolPan.add(calculateTriangleAreaButton);
    toolPan.add(calcFuncLineButton);
    toolPan.add(crossingPointButton);
    toolPan.add(rotateLineButton);
    toolPan.add(otoczkaButton);
    toolPan.add(KDTreeButton);

    add(rootPanel);

}






}


