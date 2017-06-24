package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Murat on 24.06.2017.
 */
public class AddressService {
    private Map<Class<?>, Address> addresses = new HashMap<Class<?>, Address>();

    public Address getAddress(Class<?> abonentClass) {
        return addresses.get(abonentClass);
    }

    public void setAddress(Abonent abonent) {
        addresses.put(abonent.getClass(), abonent.getAddress());
    }
}
