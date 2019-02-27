package game;

public class Cost{

	private RawMaterials resource;
	private int nb;

	public Cost(RawMaterials res, int nombre) {
        	this.resource = res;
        	this.nb = nombre;
    }

	public RawMaterials getRes() {
		return resource;
    }
	
	public int getQuant() {
        	return nb;
    }

}
