package com.hwloser.astar;

import com.sun.javafx.geom.Edge;
import java.util.List;

public class Node {
  // id for readability of result purposes
  private static int idCounter = 0;
  public int id;

  public Node parent = null; /* parent in the path */

  public List<Edge> neighbors;

  public double f = Double.MAX_VALUE;
  public double g = Double.MAX_VALUE;

  // hardcoded heuristic
  public double h;



}
