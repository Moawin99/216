import java.util.*;
import java.util.stream.Collectors;

public class Quadrilateral implements TwoDShape, Positionable {
    List<TwoDPoint> vertices;

    public Quadrilateral(List<TwoDPoint> vertices) {
        // TODO
        this.vertices = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            this.vertices.add(vertices.get(i));
        }
        isMember(vertices);
        setPosition(vertices);
    }

    /**
     * Sets the position of this quadrilateral according to the first four elements in the specified list of points. The
     * quadrilateral is formed on the basis of these four points taken in a clockwise manner on the two-dimensional
     * x-y plane. If the input list has more than four elements, the subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        // TODO
        for(int i = 0; i < 4; i++){
            this.vertices.set(i, (TwoDPoint) points.get(i));
        }
        double avgX = vertices.stream().collect(Collectors.averagingDouble(TwoDPoint::getX));
        double avgY = vertices.stream().collect(Collectors.averagingDouble(TwoDPoint::getY));
        HashMap<Double, TwoDPoint> polar = new HashMap<Double, TwoDPoint>();
        for(int i = 0; i < 4; i++){
            polar.put(Math.atan2(vertices.get(i).getX() - avgX, vertices.get(i).getY() - avgY), vertices.get(i));
        }
        List<Double> sorted = polar.keySet().stream().sorted().collect(Collectors.toList());
        List<TwoDPoint> returnList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            returnList.add(polar.get(sorted.get(i)));
        }
        this.vertices = returnList;
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
     * @return the number of sides of this quadrilateral, which is always set to four
     */
    @Override
    public int numSides() {
        return 4;
    }

    /**
     * Checks whether or not a list of vertices forms a valid quadrilateral. The <i>trivial</i> quadrilateral, where all
     * four corner vertices are the same point, is considered to be an invalid quadrilateral.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a quadrilateral, and
     * <code>false</code> otherwise. For example, if three of the four vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) {
        // TODO
        if(vertices.size() < 4){
            return false;
        }
       else if(area() == 0){
           return false;
        }
        return true;
    }

    /**
     * This method snaps each vertex of this quadrilateral to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant quadrilateral will thus have all four
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * quadrilateral becoming invalid (e.g., all four corners collapse to a single point), then it is left unchanged.
     * Snapping is an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        // TODO
        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setX(Math.round(vertices.get(i).getX()));
            vertices.get(i).setY(Math.round(vertices.get(i).getY()));
        }
    }

    /**
     * @return the area of this quadrilateral
     */
    public double area() {
        // TODO
        List<TwoDPoint> triangle1 = new ArrayList<>();
        triangle1.add(vertices.get(0));
        triangle1.add(vertices.get(1));
        triangle1.add(vertices.get(2));
        List<TwoDPoint> triangle2 = new ArrayList<>();
        triangle2.add(vertices.get(0));
        triangle2.add(vertices.get(2));
        triangle2.add(vertices.get(3));
        return new Triangle(triangle1).area() + new Triangle(triangle2).area();
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this quadrilateral
     */
    public double perimeter() {
        // TODO
        return distance(vertices.get(0), vertices.get(1)) + distance(vertices.get(0), vertices.get(3))
                + distance(vertices.get(1), vertices.get(2)) + distance(vertices.get(2), vertices.get(3));
    }

    private double distance(TwoDPoint p1, TwoDPoint p2){
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + (Math.pow((p2.getY() - p1.getY()), 2)));
    }

    @Override
    public String toString() {
        return "Quadrilateral" + vertices;
    }
}
