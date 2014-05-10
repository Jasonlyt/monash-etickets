package fb.demo;

import fb.dao.OrderDao;
import fb.dao.impl.OrderDaoImpl;
import fb.entity.Order;


public class Demo1 {
	public static void main(String[] args){
		Order order = new Order();
		order.setFid("CEA5102");
		order.setUsername("well");
		OrderDao dao = new OrderDaoImpl();
		if(!dao.isOrderExisted(order.getFid(), order.getUsername())){
			dao.insertNewOrder(order);
			System.out.println("Insert successfully!");
		}else{
			System.out.println("Order existed!");
		}
	}
	
}
