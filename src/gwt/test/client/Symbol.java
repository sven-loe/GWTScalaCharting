package gwt.test.client;

import java.io.Serializable;

public class Symbol implements Serializable {

	private static final long serialVersionUID = 3690622136407157463L;
	
	private Long id;
	private String symbol;
	private String name;
	private Long value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
}
