import java.util.Collection;
import java.util.*;
import java.util.stream.*;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
         ArrayList<String> names = new ArrayList<String>();

         // Test print for an empty list
         CollectionOps.print(names); System.out.println();

         // Test print for a list containing one element
         names.add("a");
         CollectionOps.print(names); System.out.println();

         // Test print for a list containing more than one elment
         names.add("b");
         names.add("c");
         CollectionOps.print(names); System.out.println();

         // Test the return value from reverse
         CollectionOps.print(CollectionOps.reverse(names));
         System.out.println();
        // Test that reverse mutates its argument
         CollectionOps.print(names);
         System.out.println();

         // Assignment 4: Write code to test less here

         IntegerComparator intComp = new IntegerComparator();
         ArrayList<Integer> numbers1 = new ArrayList<Integer>();
         ArrayList<Integer> numbers2 = new ArrayList<Integer>();
         numbers1.add(1);
         numbers1.add(2);
         numbers1.add(3);
         numbers2.add(4);
         numbers2.add(5);
         numbers2.add(6);
         System.out.println(CollectionOps.less(numbers1,numbers2,intComp));
         System.out.println(CollectionOps.less(numbers2,numbers1,intComp));
         System.out.println(CollectionOps.less(numbers2,numbers2,intComp));

         StringComparator stringComp = new StringComparator();
         ArrayList<String> letters1 = new ArrayList<String>();
         ArrayList<String> letters2 = new ArrayList<String>();
         letters1.add("a");
         letters1.add("b");
         letters1.add("c");
         letters2.add("d");
         letters2.add("e");
         letters2.add("f");
         System.out.println(CollectionOps.less(letters1,letters2,stringComp));
         System.out.println(CollectionOps.less(letters2,letters1,stringComp));
         System.out.println(CollectionOps.less(letters2,letters2,stringComp));

         // Assignment 5: Write code to test map here
         List<Integer> l1 = new ArrayList<Integer>();
         l1.add(3);
         l1.add(-42);
         l1.add(88);
         l1.add(19);
         l1.add(-37);
         l1.add(0);
         l1.add(18);

         Collection<Integer> l2 = CollectionOps.filter(new isEven(), l1);
         CollectionOps.print(l2);

         // Assignment 5: Write code to test filter here

         ArrayList<Person> pl = new ArrayList<>();
         pl.add(new Person("Nisse","nisse@hipnet.moc","male",23));
         pl.add(new Person("Lisa","lisa@shipnet.sea","female",67));
         pl.add(new Person("Ada","ada@jahuu.vanu","female",49));
         pl.add(new Person("Kal","karl@gotnet.vg","male",78));
         pl.add(new Person("Beda","beda@fishnet.cod","female",102));

         // Assignment 6: Write code using lambdas here
        //test 1(denna ska troligtvis bort sen)
        List<Person> npl = pl.stream().filter(p -> p.getGender().equals("female") && p.getAge()>65).collect(Collectors.toList());
        CollectionOps.print( CollectionOps.map( p -> {return p.getName();}, npl));
        //test 2
        CollectionOps.print(CollectionOps.map(x -> x.getEmail(), CollectionOps.filter(x -> (x.getAge() > 65 && x.getGender().equals("female")), pl)));
      }

}
