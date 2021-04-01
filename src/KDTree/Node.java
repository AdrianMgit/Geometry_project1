package KDTree;


import com.company.Point2D;

public class Node {

    private Node left;
    private Node right;
    private final int depth;
    private final Point2D value;



    public Node(Node left, Node right, Point2D value, int depth) {
        this.left=left;
        this.right=right;
        this.value=value;
        this.depth=depth;
    }

    public int getDepth() {
        return depth;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Double[] getCoords() {
        return value.getCoords();
    }

    public void setLeft(Node node) {
        left = node;
    }

    public void setRight(Node node) {
        right = node;
    }

    public Point2D getValue() {
        return value;
    }

}

