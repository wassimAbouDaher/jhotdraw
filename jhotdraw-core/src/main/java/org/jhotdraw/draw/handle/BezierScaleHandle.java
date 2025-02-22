/*
 * @(#)BezierScaleHandle.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw.handle;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import org.jhotdraw.draw.event.TransformRestoreEdit;
import org.jhotdraw.draw.figure.BezierFigure;
import org.jhotdraw.geom.Geom;

/**
 * A {@link Handle} which allows to interactively scale and rotate a BezierFigure.
 *
 * <p>Pressing the alt key or the shift key while manipulating the handle restricts the handle to
 * rotate the BezierFigure without scaling it.
 *
 * @author Werner Randelshofer.
 * @version $Id$
 */
public class BezierScaleHandle extends AbstractHandle {

    private Object restoreData;
  private AffineTransform transform;
  private Point2D.Double center;
  private double startTheta;
  private double startLength;

  public BezierScaleHandle(BezierFigure owner) {
    super(owner);
  }

  @Override
  public boolean isCombinableWith(Handle h) {
    return false;
  }

  /** Draws this handle. */
  @Override
  public void draw(Graphics2D g) {
    drawCircle(
        g,
        getEditor().getHandleAttribute(HandleAttributeKeys.SCALE_HANDLE_FILL_COLOR),
        getEditor().getHandleAttribute(HandleAttributeKeys.SCALE_HANDLE_STROKE_COLOR));
  }

  private BezierFigure getBezierFigure() {
    return (BezierFigure) getOwner();
  }

  @Override
  protected Point2D.Double getDrawingLocation() {
    // find a nice place to put handle
    // Need to pick a place that will not overlap with point handle
    // and is internal to polygon
    int handlesize = getHandlesize();
    // Try for one handlesize step away from outermost toward center
    Point2D.Double outer = getBezierFigure().getOutermostPoint();
    Point2D.Double ctr = getBezierFigure().getCenter();
    double len = Geom.length(outer.x, outer.y, ctr.x, ctr.y);
    if (len == 0) { // best we can do?
      return new Point2D.Double(outer.x - handlesize / 2, outer.y + handlesize / 2);
    }
    double u = handlesize / len;
    if (u > 1.0) { // best we can do?
      return new Point2D.Double((outer.x * 3 + ctr.x) / 4, (outer.y * 3 + ctr.y) / 4);
    } else {
      return new Point2D.Double(outer.x * (1.0 - u) + ctr.x * u, outer.y * (1.0 - u) + ctr.y * u);
    }
  }

  @Override
  public void trackStart(Point anchor, int modifiersEx) {
      restoreData = getBezierFigure().getTransformRestoreData();
    transform = new AffineTransform();
    center = getBezierFigure().getCenter();
    Point2D.Double anchorPoint = view.viewToDrawing(anchor);
    startTheta = Geom.angle(center.x, center.y, anchorPoint.x, anchorPoint.y);
    startLength = Geom.length(center.x, center.y, anchorPoint.x, anchorPoint.y);
  }

  @Override
  public void trackStep(Point anchor, Point lead, int modifiersEx) {
      Point2D.Double leadPoint = view.viewToDrawing(lead);
    double stepTheta = Geom.angle(center.x, center.y, leadPoint.x, leadPoint.y);
    double stepLength = Geom.length(center.x, center.y, leadPoint.x, leadPoint.y);
    double scaleFactor =
        (modifiersEx & (InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)) != 0
            ? 1d
            : stepLength / startLength;
    transform.setToIdentity();
    transform.translate(center.x, center.y);
    transform.scale(scaleFactor, scaleFactor);
    transform.rotate(stepTheta - startTheta);
    transform.translate(-center.x, -center.y);
    getOwner().willChange();
    getOwner().restoreTransformTo(restoreData);
    getOwner().transform(transform);
    getOwner().changed();
  }

  @Override
  public void trackEnd(Point anchor, Point lead, int modifiersEx) {
    view.getDrawing()
        .fireUndoableEditHappened(
            new TransformRestoreEdit(
                getOwner(), restoreData, getOwner().getTransformRestoreData()));
  }
}
