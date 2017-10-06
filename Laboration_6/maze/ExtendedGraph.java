import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ExtendedGraph extends Graph{
  public List<Integer> getPath(int destName){
    Vertex w = vertexMap.get(destName);
    if( w == null )
        throw new NoSuchElementException( "Destination vertex not found" );
    else if(w.dist == INFINITY)
      System.out.println(destName +" is unreachable ");
    else{
      return getPath(w);
    }
    return null;
  }

  public List<Integer> getPath(Vertex dest){
    List<Integer> vertexList = new ArrayList<Integer>();
    if(dest.prev != null){
      vertexList.addAll(getPath(dest.prev)); //merge list from Previous recursion
    }
    vertexList.add(dest.name);
    return vertexList;
  }
}
