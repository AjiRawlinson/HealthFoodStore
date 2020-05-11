package IteratorPattern;

import DAO.DataAccessObject;
import Entities.Product;


import java.util.List;

public class ProductRepository implements Container {
    DataAccessObject DAO = new DataAccessObject();
    List<Product> prods = (List<Product>) DAO.getAllProducts();

    @Override
    public Iterator getIterator() {
        return new ProductIterator();
    }

    private class ProductIterator implements Iterator {

        int index = 0;

        @Override
        public boolean hasNext() {
            if(index < prods.size()) { return true; }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()) {
                return prods.get(index++);
            }
            return null;
        }
    }

}
