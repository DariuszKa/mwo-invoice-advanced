package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.math.BigInteger;

import pl.edu.agh.mwo.invoice.product.Product;

import java.util.HashMap;

public class Invoice {

	HashMap<Product, Integer> products = new HashMap<>();
	
	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if(quantity<=0) throw new IllegalArgumentException("Parameter 'quantity' cannot be zero or negative!");
		if(products.containsKey(product)){
			products.put(product, products.get(product) + quantity);
		}
		else products.put(product, quantity);
	}

	public BigDecimal getTotalNet() {
		BigDecimal value = new BigDecimal(0);
		for(HashMap.Entry<Product, Integer> pair : products.entrySet()){
			Product product = pair.getKey();
			value = value.add((product.getPriceWithoutTax()).multiply(BigDecimal.valueOf(pair.getValue()))); 
		}
		return value;
	}

	public BigDecimal getTax() {
		BigDecimal value = new BigDecimal(0);
		for(HashMap.Entry<Product, Integer> pair : products.entrySet()){
			Product product = pair.getKey();
			value = value.add(product.getPriceWithTax()).subtract(product.getPriceWithoutTax()).multiply(BigDecimal.valueOf(pair.getValue())); 
		}
		return value;
	}

	public BigDecimal getTotalGross() {
		BigDecimal value = new BigDecimal(0);
		for(HashMap.Entry<Product, Integer> pair : products.entrySet()){
			Product product = pair.getKey();
			value = value.add((product.getPriceWithTax()).multiply(BigDecimal.valueOf(pair.getValue()))); 
		}
		return value;
	}
}
