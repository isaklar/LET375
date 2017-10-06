
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Maze extends Board {

    private ExtendedGraph graph;

    public Maze( int rows, int cols ) {
    	super(rows,cols);
      graph = new ExtendedGraph();
    }

    public void create() {
      DisjointSets setMaze = new DisjointSets(maxCell);

      Random rand = new Random();

      //knock down wall for entrance and exit
      Pair<Integer,Point.Direction> entrance = new Pair<>(0,Point.Direction.LEFT);
      setChanged();
      notifyObservers(entrance);
      Pair<Integer,Point.Direction> exit = new Pair<>(maxCell-1,Point.Direction.RIGHT);
      setChanged();
      notifyObservers(exit);

      //maze is not finished before all elements are part of one set and the height of the root should be equal to maxCell.
      while(setMaze.getHeight(0) != maxCell){

        int cellId = rand.nextInt(maxCell);
        Point startPoint = new Point(getRow(cellId),getCol(cellId));
        Point targetPoint = new Point(getRow(cellId),getCol(cellId));

        switch(rand.nextInt(4)){  // randomize a direction to tear down a wall between
          case 0:
            if(targetPoint.col != maxCol-1)
              targetPoint.move(Point.Direction.RIGHT);
            break;
          case 1:
            if(targetPoint.row != maxRow-1)
              targetPoint.move(Point.Direction.DOWN);
            break;
          case 2:
            if(targetPoint.col != 0)
              targetPoint.move(Point.Direction.LEFT);
            break;
          case 3:
            if(targetPoint.row != 0)
              targetPoint.move(Point.Direction.UP);
            break;
          default:
        }
        if(startPoint.getDirection(targetPoint) == Point.Direction.ERROR) // some point outside of grid no point to continue.
          continue;

        int adjacentCellId = getCellId(targetPoint);
        if(adjacentCellId >= maxCell || adjacentCellId < 0) // adjacentCell not allowed to be less than 0 or grater maxCell-1
          continue;


        //cells are not in the same set -> we make a union betwen the roots of cellId and adjecentCellId
        if(setMaze.find(cellId) != setMaze.find(adjacentCellId)){

          //add edges between adjecent cells with paths between eachother in an
          //unweighted and non directed graph
          //(all edges are added two times to simulate an nondirected graph)
          //(weight 1 is added to simulate an unweighted graph)
          graph.addEdge(cellId, adjacentCellId, 1);
          graph.addEdge(adjacentCellId, cellId, 1);

          setMaze.union(setMaze.find(cellId),setMaze.find(adjacentCellId));
          Pair<Integer,Point.Direction> pair = new Pair<>(cellId,startPoint.getDirection(targetPoint));
          setChanged();
          notifyObservers(pair);
        }

      }

    }

    public void search() {
      graph.unweighted(0);
      List <Integer> path = graph.getPath(maxCell-1);
      setChanged();
      notifyObservers(path);
    }

}
