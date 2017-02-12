package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) throws IllegalArgumentException {
		if (name=="" || price==null || name==null || (price.compareTo(BigDecimal.ZERO)==-1)){
			throw new IllegalArgumentException("brak parametru");
		}
		this.name = name;
		this.price = price;
		this.taxPercent = tax;
	}

	public String getName() throws IllegalArgumentException {
		return name;		
	}

	public BigDecimal getPriceWithoutTax() {
		return price;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		return price.multiply(taxPercent).add(price);		
	}
}
