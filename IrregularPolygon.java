import java.awt.geom.*;
import java.util.ArrayList;
import gpdraw.*;

public class IrregularPolygon {
    private ArrayList<Point2D.Double> vertices;

    public IrregularPolygon() {
        vertices = new ArrayList<>();
    }

    public void add(Point2D.Double point) {
        vertices.add(point);
    }

    public double perimeter() {
        if (vertices.size() < 2) return 0;
        double totalLength = 0.0;
        Point2D.Double previous = vertices.get(vertices.size() - 1);
        for (Point2D.Double current : vertices) {
            totalLength += previous.distance(current);
            previous = current;
        }
        return totalLength;
    }

    public double area() {
        if (vertices.size() < 3) return 0;
        double sumA = 0.0, sumB = 0.0;
        int size = vertices.size();
        for (int i = 0; i < size; i++) {
            Point2D.Double current = vertices.get(i);
            Point2D.Double next = vertices.get((i + 1) % size);
            sumA += current.x * next.y;
            sumB += current.y * next.x;
        }
        return Math.abs(sumA - sumB) * 0.5;
    }

    public void draw() {
        try {
            if (vertices.isEmpty()) return;
            DrawingTool drawer = new DrawingTool(new SketchPad(500, 500));
            drawer.up();
            Point2D.Double first = vertices.get(0);
            drawer.move(first.x, first.y);
            drawer.down();
            for (Point2D.Double point : vertices) {
                drawer.move(point.x, point.y);
            }
            drawer.move(first.x, first.y);
        } catch (java.awt.HeadlessException e) {
            System.out.println("Error: No graphics support available.");
        }
    }
}
