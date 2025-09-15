package stream.myStream;

@FunctionalInterface
public interface MyMapper<T, R> {
    R apply(T t);
}
