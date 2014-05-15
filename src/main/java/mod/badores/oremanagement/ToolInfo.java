package mod.badores.oremanagement;

/**
 * @author diesieben07
 */
public final class ToolInfo {

	public final int harvestLevel;

	public final int maxUses;

	public final float efficiency;

	public final float damage;

	public final int enchantability;

	public ToolInfo(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.damage = damage;
		this.enchantability = enchantability;
	}
}
