package observer;

import model.Customer;

public interface Sender {
	void sendPromotion(Customer toSend);
	void confirmedPromotion(Customer customer);
}
