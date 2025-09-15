package stream.myStream;

@FunctionalInterface
public interface MyFilter<T> {
    boolean test(T t);
}
