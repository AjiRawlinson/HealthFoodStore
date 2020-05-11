package IteratorPattern;

import DAO.DataAccessObject;
import Entities.Orders;

import java.util.List;

public class OrderRepository implements Container {
    DataAccessObject DAO = new DataAccessObject();
    List<Orders> orders = (List<Orders>) DAO.getAllOrders();

    @Override
    public Iterator getIterator() {
        return new OrderIterator();
    }

    private class OrderIterator implements Iterator {

        int index = 0;

        @Override
        public boolean hasNext() {
            if(index < orders.size()) { return true; }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()) {
                return orders.get(index++);
            }
            return null;
        }
    }

}
