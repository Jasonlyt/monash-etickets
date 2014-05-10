package fb.dao;

import fb.entity.Order;

public interface OrderDao {
	
	public boolean isOrderExisted(String fid,String username);
	
	public boolean insertNewOrder(Order order);
}
