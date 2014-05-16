package mod.badores.oremanagement;

/**
 * @author diesieben07
 */
public enum OreForm {

	ORE,
	INGOT,
	HELMET,
	CHESTPLATE,
	LEGGINGS,
	BOOTS,
	PICKAXE,
	SHOVEL,
	AXE,
	HOE,
	SWORD;

	public boolean isArmor() {
		return this == HELMET || this == CHESTPLATE || this == LEGGINGS || this == BOOTS;
	}

	public boolean isTool() {
		return !isArmor() && this != ORE && this != INGOT;
	}

	public static OreForm fromArmor(ArmorType armorType) {
		switch (armorType) {
			case BOOTS:
				return BOOTS;
			case CHESTPLATE:
				return CHESTPLATE;
			case LEGGINGS:
				return LEGGINGS;
			case HELMET:
				return HELMET;
			default:
				throw new IllegalArgumentException();
		}
	}
}
