package game;

public class Cost{

	private RawMaterials[] materials;
	private ManufacturedGood[] goods;
	private int coins;

	public Cost(RawMaterials[] materials, ManufacturedGood[] goods) {
		this.materials = materials;
		this.goods = goods;
		this.coins = 0;
    }

    public Cost(int coins) {
		this.materials = null;
		this.goods = null;
		this.coins = coins;
	}

    public Cost(RawMaterials[] rawMaterials, ManufacturedGood[] manufacturedGoods, int i) {
    }

    public RawMaterials[] getMaterialsCost() {
		return this.materials;
	}

	public ManufacturedGood[] getGoodsCost() {
		return this.goods;
	}

	public int getCoinsCost() {
		return this.coins;
	}
}
