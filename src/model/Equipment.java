package model;

public class Equipment extends Product {
	private String material;
	private String style;
	
	public Equipment() {
		
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
