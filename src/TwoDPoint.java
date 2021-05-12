import java.util.Comparator;
import java.util.List;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point, Comparable<TwoDPoint> {
    private double x;
    private double y;

    public TwoDPoint(double x, double y) {
        // TODO done?
        this.x = x;
        this.y = y;
    }

    /**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        // TODO
        return new double[] {this.x, this.y};
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of doubles. A valid argument must always
     * be an even number of doubles so that every pair can be used to form a single <code>TwoDPoint</code> to be added
     * to the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of doubles.
     */
    public static List<TwoDPoint> ofDoubles(double... coordinates) throws IllegalArgumentException {
        return null; // TODO
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int compareTo(TwoDPoint o) {
        if(distance(this) < distance(o)){
            return -1;
        }
        else if(distance(this) > distance(o)){
            return 1;
        }
        return 0;
    }

    private double distance(TwoDPoint p1){
        return Math.sqrt(Math.pow((0 - p1.getX()), 2) + (Math.pow((0 - p1.getY()), 2)));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
