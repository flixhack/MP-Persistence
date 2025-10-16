package model;

public class GunReplica extends Equipment {
	private String caliber;
	private String material;
	
	public GunReplica() {
		
	}

	public String getCaliber() {
		return caliber;
	}

	public void setCaliber(String caliber) {
		this.caliber = caliber;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
}
