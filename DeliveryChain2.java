/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;
import task1.*;
import Pizza;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Order;
/**
 *  this class creates a list of orders to simulate deliveries
 * @author 
 */
public class DeliveryChain {
    private static final int MAX_DELIVERY_TIME = 10000;
    int deliveryID = 0;
    Queue<Order> deliverychain = new LinkedList<>();
    
    public void deliverPizza(Order o){
        deliverychain.add(o);
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(MAX_DELIVERY_TIME));
        } catch (InterruptedException ex) {
            Logger.getLogger(DeliveryChain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Pizza " + deliverychain.remove() + "  delivered");
    }
}
