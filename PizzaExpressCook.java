/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;
import java.util.logging.Level;
import java.util.logging.Logger;
import Cook;
import Ingredient;
import Order;
import Pizza;
import PizzaExpress;


/**
 * This is the class that handles the implementation of the cooks.
 * There are two types of cooks that this class supports.
 * @author 
 */
public class PizzaExpressCook extends Thread implements Cook {
    private final String name;
    private final PizzaExpress pizzeria;
    int cooktype;

    
    public PizzaExpressCook(String n, PizzaExpress p, int x){
        name = n;
        pizzeria = p;
        cooktype = x;
    }
    
    private void cookPizza(Pizza p, Order o){
        System.out.println(name + ": begins cooking pizza " + p.toString());
        try {
            Thread.sleep(p.getPrepTime());
        } catch (InterruptedException ex) {
            Logger.getLogger(PizzaExpressCook.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(name + ": pizza " + p.toString() + " is ready");
        if(cooktype==1){
            pizzeria.deliverOrder(o);
        } else{
            pizzeria.deliverEatInOrder(o);
        }
        
    }

    // in this prepare order method the cook attempts to gain a lock before getting the ingredients and cooking the pizza
    @Override
    public void prepareOrder(Order o) {
        for(Pizza p: o){
            try {
                pizzeria.accessShelf();
            } catch (InterruptedException ex) {
                Logger.getLogger(PizzaExpressCook.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Ingredient i : p.getIngredients()) {
                System.out.println(name + ": trying to fetch " + i);
                if (i.isSauce()) pizzeria.fetchSauce();
                if (i.isCheese()) pizzeria.fetchCheese();
                if (i.isHam()) pizzeria.fetchHam();
                if (i.isVeggies()) pizzeria.fetchVeggies();
                if (i.isPineapple()) pizzeria.fetchPineapple();
                System.out.println(name + ": fetched " + i);
            }
            pizzeria.closeShelf();
            cookPizza(p,o);
        }
    }

    public void run(){
        System.out.println("Thread running");
        while(true){
            if(pizzeria.getNumOfWaitingOrders()>0 && cooktype == 0){
                prepareOrder(pizzeria.getNextOrder());
            } else if(pizzeria.getNumOfWaitingOrders()>0 && cooktype == 1){
                prepareOrder(pizzeria.getNextEatInOrder());
            }
        }
    }  
}
