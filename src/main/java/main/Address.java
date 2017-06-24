package main;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Murat on 24.06.2017.
 */
public class Address {
    static private AtomicInteger abonentIdCreator = new AtomicInteger();
    final private int abonentId;


    public Address() {
        this.abonentId = abonentIdCreator.incrementAndGet();
    }

    public int hashCode() {
        return abonentId;
    }
}
