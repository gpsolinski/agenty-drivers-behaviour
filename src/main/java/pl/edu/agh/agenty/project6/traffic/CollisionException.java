package pl.edu.agh.agenty.project6.traffic;

/**
 * Created by grzegorz on 2015-07-02.
 */
public class CollisionException extends RuntimeException {
    public CollisionException() {super();}

    public CollisionException(int position) {
        super("Collision at position " + position);
    }
}
