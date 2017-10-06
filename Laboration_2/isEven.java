public class isEven implements Predicate<Integer>{
  public boolean test(Integer x){
    return x % 2 == 0;
  }
}
