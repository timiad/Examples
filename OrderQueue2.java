/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;
import Order;
import java.util.Queue;
import java.util.LinkedList;

/**
 * this class queues the orders for the cooks
 * @author 
 */
public class OrderQueue {    
    private Queue<Order> orders = new LinkedList();
    
    public void placeNextOrder(Order o){
        orders.add(o);
    }
    
    public int getNumberOfOrders(){
        return orders.size();
    }
    
    public Order getNextOrder(){
        return orders.remove();
    }
}
