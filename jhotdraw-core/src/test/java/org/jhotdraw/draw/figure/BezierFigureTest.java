package org.jhotdraw.draw.figure;

import org.jhotdraw.geom.path.BezierPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import static org.junit.jupiter.api.Assertions.*;

public class BezierFigureTest {
    private BezierFigure bezierFigure;

    @BeforeEach
    public void setUp() {
        bezierFigure = new BezierFigure();
    }

    @Test
    public void testAddNode() {
        BezierPath.Node node = new BezierPath.Node(10, 10);
        bezierFigure.addNode(node);
        assertEquals(1, bezierFigure.getNodeCount());
    }

    @Test
    public void testRemoveNode() {
        BezierPath.Node node = new BezierPath.Node(10, 10);
        bezierFigure.addNode(node);
        bezierFigure.removeNode(0);
        assertEquals(0, bezierFigure.getNodeCount());
    }

    @Test
    public void testSetStartPoint() {
        Point2D.Double startPoint = new Point2D.Double(0, 0);
        bezierFigure.setStartPoint(startPoint);
        assertEquals(startPoint, bezierFigure.getStartPoint());
    }

    @Test
    public void testSetEndPoint() {
        Point2D.Double endPoint = new Point2D.Double(100, 100);
        bezierFigure.setEndPoint(endPoint);
        assertEquals(endPoint, bezierFigure.getEndPoint());
    }

    @Test
    public void testFindSegment() {
        BezierPath.Node node1 = new BezierPath.Node(0, 0);
        BezierPath.Node node2 = new BezierPath.Node(100, 100);
        bezierFigure.addNode(node1);
        bezierFigure.addNode(node2);
        int index = bezierFigure.findSegment(new Point2D.Double(50, 50), 5f);
        assertEquals(0, index);
    }
    @Test
    public void tstFindSegment() {
        BezierPath.Node node1 = new BezierPath.Node(0, 0);
        BezierPath.Node node2 = new BezierPath.Node(100, 100);
        bezierFigure.addNode(node1);
        bezierFigure.addNode(node2);
        int index = bezierFigure.findSegment(new Point2D.Double(50, 50), 5f);
        assertEquals(0, index);
    }
    @Test
    public void testSplitSegment() {
        BezierPath.Node node1 = new BezierPath.Node(0, 0);
        BezierPath.Node node2 = new BezierPath.Node(100, 100);
        bezierFigure.addNode(node1);
        bezierFigure.addNode(node2);
        int newIndex = bezierFigure.splitSegment(new Point2D.Double(50, 50), 5f);
        assertEquals(1, newIndex);
        assertEquals(3, bezierFigure.getNodeCount());
    }

}
