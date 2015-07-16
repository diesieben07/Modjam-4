package mod.badores.ore;

/**
 * @author diesieben07
 */
public class Uselessium extends AbstractOre {

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
    public boolean hasIngotBlock() {
        return false;
    }

    @Override
    public String getName() {
        return "uselessium";
    }
}
