package IteratorPattern;

import DAO.DataAccessObject;
import Entities.Customer;

import java.util.List;

public class CustomerRepository implements Container {
    DataAccessObject DAO = new DataAccessObject();
    List<Customer> custs = (List<Customer>) DAO.getAllCustomer();

    @Override
    public Iterator getIterator() {
        return new CustomerIterator();
    }

    private class CustomerIterator implements Iterator {

        int index = 0;

        @Override
        public boolean hasNext() {
            if(index < custs.size()) { return true; }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()) {
                return custs.get(index++);
            }
            return null;
        }
    }
}
