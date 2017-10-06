// Author(s): Isak Einler Larsson & Gabriel Lindeby
// Email: gablinde@chalmers.student.se & isaklar@chalmers.student.se
// Date: 2017-04-02

public class SingleBuffer<E>{
  private E e = null;


  public boolean put(E x){
    if(! isEmpty){
      return false;
    }else{
      return true;
    }
  }

  public E get(){
    E temp = e;
    e = null;
    return temp;
  }

  public boolean isEmpty(){
    return element == null;
  }
}
