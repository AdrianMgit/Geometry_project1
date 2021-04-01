package KDTree;



import com.company.Point2D;

import java.util.*;


public class NearestPoint {

    private static class Distance {
        Node nodeDist;
        Double distance;

        Distance(double distance, Node node) {
            nodeDist = node;
            this.distance = distance;
        }
    }





    public static Node find(Node root, Point2D point) {
        if (root != null) {
            Double[] coordNowPoint;                             //odwiedzone wezly z zapisana odlegloascia
            Queue queue = new Queue();
            queue.add(new Distance(0, root));
            Distance nearestNode = new Distance(Double.MAX_VALUE, root);

            while (!queue.isEmpty()) {
                Distance nowDistance = queue.poll();            //nowDistance to wezle o njamn.odleglosci od obecnego

                if (nowDistance.distance >= nearestNode.distance) {        //gdy mam juz znaleziony blizszy punkt
                    return nearestNode.nodeDist;                                     //to koncze
                }

                Node nowNode = nowDistance.nodeDist;
                double fromNowNode = Math.sqrt(Math.pow(point.getX() - nowNode.getValue().getX(), 2) + Math.pow(point.getY() - nowNode.getValue().getY(), 2));
                //licze odl. obecnego punktu
                if (fromNowNode < nearestNode.distance) {                        //jesli jest mniesza niz najlepsza
                    nearestNode.nodeDist = nowNode;                                       //to jest to nowy najlepszy
                    nearestNode.distance = fromNowNode;
                }
                Node wezelDalej;
                Node wezelBlizej;
                coordNowPoint = point.getCoords();
                int os = nowNode.getDepth() % 2;
                double odl = coordNowPoint[os] - nowNode.getCoords()[os];     //porownuje wspol.
                if (odl > 0) {
                    wezelDalej = nowNode.getLeft();
                    wezelBlizej = nowNode.getRight();
                } else {
                    wezelDalej = nowNode.getRight();
                    wezelBlizej = nowNode.getLeft();
                }

                if (wezelDalej != null) {
                    queue.add(new Distance(odl, wezelDalej));
                }

                if (wezelBlizej != null) {
                    queue.add(new Distance(0, wezelBlizej));
                }
            }

            return nearestNode.nodeDist;
        }
        else
            return null;
    }


    private static class Queue extends PriorityQueue<Distance> {
        private static Comparator<Distance> mComparator = new Comparator<Distance>() {
            @Override
            public int compare(Distance o1, Distance o2) {
                return o1.distance.compareTo(o2.distance);
            }
        };

        Queue() {
            super(11, mComparator);
        }
    }



}


