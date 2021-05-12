import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Triangle implements TwoDShape, Positionable {
    List<TwoDPoint> vertices;

    public Triangle(List<TwoDPoint> vertices) {
        // TODO done
        this.vertices = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            this.vertices.add(vertices.get(i));
        }
        isMember(vertices);
        setPosition(vertices);
    }

    /**
     * Sets the position of this triangle according to the first three elements in the specified list of points. The
     * triangle is formed on the basis of these three points taken in a clockwise manner on the two-dimensional
     * x-y plane. If the input list has more than three elements, the subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        // TODO
        for(int i = 0; i < 3; i++){
            this.vertices.set(i, (TwoDPoint) points.get(i));
        }
        vertices = this.vertices.stream().sorted(Comparator.comparingDouble((TwoDPoint::getX)).thenComparingDouble((TwoDPoint::getY))).collect(Collectors.toList());
        if(vertices.get(1).getY() < vertices.get(2).getY()){
           TwoDPoint temp = vertices.get(1);
           vertices.set(1, vertices.get(2));
           vertices.set(2, temp);
        }
    }

    /**
     * Retrieve the position of an object as a list of points. The points are be retrieved and added to the returned
     * list in a clockwise manner on the two-dimensional x-y plane, starting with the point with the least x-value. If
     * two points have the same least x-value, then the clockwise direction starts with the point with the lower y-value.
     *
     * @return the retrieved list of points.
     */
    @Override
    public List<? extends Point> getPosition() {
       // TODO
        return vertices;
    }

    /**
     * @return the number of sides of this triangle, which is always set to three
     */
    @Override
    public int numSides() {
        return 3;
    }

    /**
     * Checks whether or not a list of vertices forms a valid triangle. The <i>trivial</i> triangle, where all three
     * corner vertices are the same point, is considered to be an invalid triangle.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a triangle, and
     * <code>false</code> otherwise. For example, three vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices){
        // TODO done
        if(area() == 0){
            return false;
        }
        return true;
    }

    /**
     * This method snaps each vertex of this triangle to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant triangle will thus have all four
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * triangle becoming invalid (e.g., all corners collapse to a single point), then it is left unchanged. Snapping is
     * an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        // TODO done
        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setX(Math.round(vertices.get(i).getX()));
            vertices.get(i).setY(Math.round(vertices.get(i).getY()));
        }
    }

    /**
     * @return the area of this triangle
     */
    public double area() {
       // TODO done
        double area1 = (vertices.get(0).getX() * (vertices.get(1).getY() - vertices.get(2).getY()));
        double area2 = (vertices.get(1).getX() * (vertices.get(2).getY() - vertices.get(0).getY()));
        double area3 = (vertices.get(2).getX()  * (vertices.get(0).getY() - vertices.get(1).getY()));
        return Math.abs((area1 + area2 + area3)/2);
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this triangle
     */
    public double perimeter() {
        // TODO done
        return distance(vertices.get(0),vertices.get(1)) + distance(vertices.get(0), vertices.get(2)) + distance(vertices.get(1), vertices.get(2));
    }

    private double distance(TwoDPoint p1, TwoDPoint p2){
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + (Math.pow((p2.getY() - p1.getY()), 2)));
    }

    @Override
    public String toString() {
        return "Triangle" + vertices;
    }
}
