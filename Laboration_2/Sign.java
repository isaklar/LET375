public class Sign implements Function<Double,Integer> {
    public Integer apply(Double x) {
        return x.compareTo(0.0d);
    }
}