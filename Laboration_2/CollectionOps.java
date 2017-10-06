
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

public class CollectionOps {

    // Put your code for print here ...
    public static <T> void print(Collection<T> c){
      Iterator it = c.iterator();
      System.out.print("[");
      while(it.hasNext()){
        System.out.print(it.next().toString());
        if(it.hasNext())
          System.out.print(",");
      }
      System.out.print("]");
    }

    // Put your code for reverse here ...
    public static <T> List<T> reverse(List<T> l){
      Collections.reverse(l);
      return l;
    }

    // Put your code for less here ...
    @SuppressWarnings("unchecked")
    public static <T> boolean less(Collection<T> c1, Collection<T> c2, Comparator<T> comp){
      if(comp.compare(Collections.max(c1,comp),Collections.min(c2,comp))>= 0){
        return false;
      }
      return true;
    }

    public static <T,R> Collection<R> map(Function<T,R> f, Collection<T> c){
      @SuppressWarnings("rawtypes")
      Class<? extends Collection> cls = c.getClass();
      try{
        @SuppressWarnings("unchecked")
        Collection<R> result = (Collection<R>)cls.newInstance();

        for(T x : c)
          result.add(f.apply(x));
          return result;

      }catch (Exception e){
        e.printStackTrace();
        return null;
      }
    }

    // Put your code for filter here ...
    public static <T> Collection<T> filter(Predicate<T> p, Collection<T> c){
      @SuppressWarnings("rawtypes")
      Class<? extends Collection> cls = c.getClass();
      try{
        @SuppressWarnings("unchecked")
        Collection<T> result = (Collection<T>)cls.newInstance();

        for(T x : c)
          if(p.test(x)){
            result.add(x);
          }
          return result;
      }catch(Exception e){
        e.printStackTrace();
        return null;
      }
    }
}
