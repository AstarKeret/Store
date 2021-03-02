package model;

public class Product {
	private String name;
	private int storeCost;
	private int customerPrice;
	private Customer customer;

	public Product(String name, int storeCost, int customerPrice, Customer customer) {
		this.name = name;
		this.storeCost = storeCost;
		this.customerPrice = customerPrice;
		this.customer = customer;
	}

	//default constructor
	public Product() {
	}

	//copy constructor
	public Product(Product product) {
		this(product.getName(), product.getStoreCost(), product.getCustomerPrice(), new Customer(product.getCustomer()));
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStoreCost(int storeCost) {
		this.storeCost = storeCost;
	}

	public void setCustomerPrice(int customerPrice) {
		this.customerPrice = customerPrice;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public int getStoreCost() {
		return storeCost;
	}

	public int getCustomerPrice() {
		return customerPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + customerPrice;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + storeCost;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (customerPrice != other.customerPrice)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (storeCost != other.storeCost)
			return false;
		return true;
	}
}
