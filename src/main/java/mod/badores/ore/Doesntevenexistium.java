package mod.badores.ore;

import mod.badores.oremanagement.OreBookPage;
import mod.badores.util.I18n;

/**
 * @author diesieben07
 */
public enum Doesntevenexistium implements OreBookPage {

	INSTANCE;

    @Override
	public String getDisplayName() {
		return I18n.translate("badores.doesntevenexistium");
	}

	@Override
	public String getDescriptionText() {
		return I18n.translate("badores.doesnteventexistium.description");
	}
}
