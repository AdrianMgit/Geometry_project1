package GUI;

import com.company.Point2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class DrawGui extends JPanel {


    int width;
    int height;
    int podzialka;
    int srodekX;
    int srodekY;

    private Ellipse2D paintedpoint;
    private Point2D getedPoint;
    private String etykieta;
    private double coord_x;
    private double coord_y;
    private Data dataDraw;
    int wielkoscpunktu=10;
    Point2D pointt1;
    Point2D pointt2;

    public DrawGui(int width, int height, int podzialka, Data data){
        this.width=width;
        this.height=height;
        this.srodekX=height/2;
        this.srodekY=width/2;
        this.podzialka=podzialka;
        this.dataDraw=data;

    }

    protected void paintComponent(Graphics g) {

        System.out.println(getHeight());
        width=getWidth();
        height=getHeight();
        this.srodekX=height/2;
        this.srodekY=width/2;

        Point sw = new Point(width-20, srodekX);
        Point ne = new Point(width-30, srodekX);
        ((Graphics2D) g).drawString("X",width-30,srodekX+20);
        ((Graphics2D) g).drawString("Y",srodekY+10,60);

        //SKALA
        ((Graphics2D) g).drawString(""+podzialka,srodekY+podzialka-6,srodekX+20);
        ((Graphics2D) g).drawString(""+podzialka,srodekY+10,srodekX-podzialka);

        //SIATKA PIONOWA
        for(int i=0;i*podzialka<width;i++){
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(srodekY+i*podzialka,0,srodekY+i*podzialka,height);
            g.drawLine(srodekY-i*podzialka,0,srodekY-i*podzialka,height);
        }

        //SIATKA POZIOMA
        for(int i=0;i*podzialka<height;i++){
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(0,srodekX+i*podzialka,width,srodekX+i*podzialka);
            g.drawLine(0,srodekX-i*podzialka,width,srodekX-i*podzialka);
        }

        //GLOWNE OSIE I PODPISY
        g.setColor(Color.BLACK);
        g.drawLine(0,srodekX,width,srodekX);          //linia iksow
        g.drawLine(srodekY,0,srodekY,height);          //lina y

        drawArrowHead(g, new Point2D(width - 20, srodekX), new Point2D(width - 30, srodekX), Color.BLACK);    //x    }

        drawArrowHead(g, new Point2D(srodekY, 15), new Point2D(srodekY, 60), Color.BLACK);




       if(dataDraw.getPoint()!=null)
           drawPoint(g);

       if(dataDraw.getPunkciki().size()>0) {
           drawPoints(g);
       }

       if(dataDraw.getLine()!=null)
           drawLine(g);

        if(dataDraw.getTriangle()!=null)
            drawTriangle(g);

        if(dataDraw.getPolygon()!=null)
            drawPolygon(g);
        if(dataDraw.getPointCircle()!=null)
            drawCircle(g);
    }








    //--------------------PRYWATNE FUNKCJE RYSUJACE PUNKTY I DANE FIGURY PRZECHOWYWANE W OBIEKCIE DATADROW-------------


    private void drawArrowHead(Graphics g2, Point2D tip, Point2D tail, Color color) {
        double phi = Math.toRadians(40);
        int barb = 20;

        g2.setColor(color);
        double dy = tip.getY() - tail.getY();
        double dx = tip.getX() - tail.getX();
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + phi;
        for (int j = 0; j < 2; j++) {
            x = tip.getX() - barb * Math.cos(rho);
            y = tip.getY() - barb * Math.sin(rho);


            Graphics2D g2d = (Graphics2D)g2;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Shape l = new Line2D.Double(tip.getX(), tip.getY(), x, y);
            g2d.draw(l);
            rho = theta - phi;
        }

    }


    private void drawTriangle(Graphics g){
        //RYSOANIE LINI POTRZEBNA KONWERCJA NA GRAPHICS2D ABY NARYSOWAC LINIE Z DOUBLE
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Shape lin;
        lin=new Line2D.Double(srodekY+dataDraw.getTriangle().getW1().getX(),srodekX-dataDraw.getTriangle().getW1().getY(),srodekY+dataDraw.getTriangle().getW2().getX(),srodekX-dataDraw.getTriangle().getW2().getY());
        g2d.draw(lin);
        lin=new Line2D.Double(srodekY+dataDraw.getTriangle().getW2().getX(),srodekX-dataDraw.getTriangle().getW2().getY(),srodekY+dataDraw.getTriangle().getW3().getX(),srodekX-dataDraw.getTriangle().getW3().getY());
        g2d.draw(lin);
        lin=new Line2D.Double(srodekY+dataDraw.getTriangle().getW3().getX(),srodekX-dataDraw.getTriangle().getW3().getY(),srodekY+dataDraw.getTriangle().getW1().getX(),srodekX-dataDraw.getTriangle().getW1().getY());
        g2d.draw(lin);
    }




    private void drawPoint(Graphics g){
        g.setColor(Color.RED);
        coord_x= dataDraw.getPoint().getX();
        coord_y=dataDraw.getPoint().getY();
        paintedpoint=new Ellipse2D.Double(coord_x+srodekY-wielkoscpunktu/2,srodekX-wielkoscpunktu/2-coord_y,wielkoscpunktu,wielkoscpunktu);
        ((Graphics2D) g).fill(paintedpoint);

        g.setColor(Color.BLACK);
        etykieta="("+coord_x+","+coord_y+")";
        ((Graphics2D) g).drawString(etykieta,(float)coord_x+srodekY-wielkoscpunktu/2-10,srodekX-wielkoscpunktu/2-10-(float)coord_y);
    }

    private void drawPoints(Graphics g){

        //RYSOWANIE PUNKTOW
        for(int i=0;i<dataDraw.getPunkciki().size();i++){
            g.setColor(Color.BLUE);
            getedPoint=dataDraw.getPunkciki().get(i);
            coord_x=getedPoint.getX();
            coord_y=getedPoint.getY();
            // System.out.println("x: "+coord_x+", y: "+coord_y);

            paintedpoint=new Ellipse2D.Double(coord_x+srodekY-wielkoscpunktu/2,srodekX-wielkoscpunktu/2-coord_y,wielkoscpunktu,wielkoscpunktu);
            ((Graphics2D) g).fill(paintedpoint);

            g.setColor(Color.BLACK);
            etykieta="("+coord_x+","+coord_y+")";
            ((Graphics2D) g).drawString(etykieta,(float)coord_x+srodekY-wielkoscpunktu/2-10,srodekX-wielkoscpunktu/2-10-(float)coord_y);
        }
    }


    private void drawLine(Graphics g){
        //RYSOANIE LINI POTRZEBNA KONWERSJA NA GRAPHICS2D ABY NARYSOWAC LINIE Z DOUBLE
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Shape lin;
        for(int i=0;i<dataDraw.getLine().size();i++) {
            double x1 = dataDraw.getLine().get(i).getHead().getX();
            double y1 = dataDraw.getLine().get(i).getHead().getY();
            double x2 = dataDraw.getLine().get(i).getTail().getX();
            double y2 = dataDraw.getLine().get(i).getTail().getY();

            lin = new Line2D.Double(srodekY + x1, srodekX - y1, srodekY + x2, srodekX - y2);
            g2d.draw(lin);
        }
    }


    private void drawPolygon(Graphics g) {

        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Shape lin;


        for (int i = 0; i < dataDraw.getPolygon().getPoints().size(); i++) {
            pointt1 = dataDraw.getPolygon().getPoints().get(i);
            pointt2 = dataDraw.getPolygon().getPoints().get((i + 1) % dataDraw.getPolygon().getPoints().size());      //modulo po to zeby polaczylo ostatni z pierwszym
            lin = new Line2D.Double(srodekY + pointt1.getX(), srodekX - pointt1.getY(), srodekY + pointt2.getX(), srodekX - pointt2.getY());
            g2d.draw(lin);
        }
    }



   private void drawCircle(Graphics g){
       //RYSOANIE LINI POTRZEBNA KONWERCJA NA GRAPHICS2D ABY NARYSOWAC LINIE Z DOUBLE
       g.setColor(Color.RED);
       Graphics2D g2d = (Graphics2D)g;
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
       Shape paintedPoint;

       coord_x= dataDraw.getPointCircle().getX();
       coord_y=dataDraw.getPointCircle().getY();
       paintedPoint=new Ellipse2D.Double(coord_x+srodekY-dataDraw.getCircleRay(),srodekX-dataDraw.getCircleRay()-coord_y,2*dataDraw.getCircleRay(),2*dataDraw.getCircleRay());
       ((Graphics2D) g).draw(paintedPoint);
   }



    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.

    }


}
