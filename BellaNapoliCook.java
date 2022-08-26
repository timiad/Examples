/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;
import java.util.logging.Level;
import java.util.logging.Logger;
import Cook;
import Ingredient;
import Order;
import Pizza;
import Pizzeria;


/**
 * This is an implementation of a cook
 * @author 
 */
public class BellaNapoliCook implements Cook{
    private final String name;
    private final Pizzeria pizzeria;
    
    public BellaNapoliCook(String n, Pizzeria p){
        name = n;
        pizzeria = p;
    }
    
    private void cookPizza(Pizza p, Order o){
        //wait some time
        System.out.println(name + ": begins cooking pizza " + p.toString());
        try {
            Thread.sleep(p.getPrepTime());
        } catch (InterruptedException ex) {
            Logger.getLogger(BellaNapoliCook.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(name + ": pizza " + p.toString() + " is ready");
        pizzeria.deliverOrder(o);
        
    }

    @Override
    public void prepareOrder(Order o) {
        for(Pizza p: o){
            for (Ingredient i : p.getIngredients()) {
                System.out.println(name + ": trying to fetch " + i);
                if (i.isSauce()) pizzeria.fetchSauce();
                if (i.isCheese()) pizzeria.fetchCheese();
                if (i.isHam()) pizzeria.fetchHam();
                if (i.isVeggies()) pizzeria.fetchVeggies();
                if (i.isPineapple()) pizzeria.fetchPineapple();
                System.out.println(name + ": fetched " + i);
            }
            cookPizza(p,o);
        }
    }
    
    
}
