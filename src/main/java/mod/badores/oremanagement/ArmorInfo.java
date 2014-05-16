package mod.badores.oremanagement;

/**
 * @author diesieben07
 */
public final class ArmorInfo {

	public final int durability;
	public final int[] reductionAmounts;
	public final int enchantability;

	public ArmorInfo(int durability, int[] reductionAmounts, int enchantability) {
		this.durability = durability;
		this.reductionAmounts = reductionAmounts;
		this.enchantability = enchantability;
	}
}
