package mod.badores;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import mod.badores.achievements.BOAchievementList;
import mod.badores.blocks.TileEntityBadOre;
import mod.badores.config.BOConfig;
import mod.badores.entities.EntityFleeingBlock;
import mod.badores.entities.EntityNosleeptonite;
import mod.badores.event.FMLEventHandler;
import mod.badores.event.ForgeEventHandler;
import mod.badores.items.BadOreBook;
import mod.badores.items.ItemBOIngot;
import mod.badores.network.PacketRandomTranslation;
import mod.badores.ore.*;
import mod.badores.oremanagement.BlockInfo;
import mod.badores.oremanagement.OreManager;
import mod.badores.worldgen.WorldGeneratorBadOres;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

/**
 * @author diesieben07
 */
@Mod(modid = BadOres.MOD_ID, name = BadOres.NAME, version = BadOres.VERSION, dependencies = "required-after:Forge@[10.13.4.1448,)")
public class BadOres {

    public static final String MOD_ID = "badores";
    public static final String VERSION = "0.2";
    public static final String NAME = "BadOres";

    @SidedProxy(clientSide = "mod.badores.client.BOClientProxy", serverSide = "mod.badores.server.BOServerProxy")
    public static BOProxy proxy;

    public static Logger logger;
    public static BOConfig config;

    public static boolean devEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    public static CreativeTabs creativeTab = new CreativeTabs("badores") {
        @Override
        public ItemStack getIconItemStack() {
            BlockInfo blockInfo = oreManager.getBlockInfo(oreManager.getOreByName("polite"));
            return blockInfo.asStack();
        }

        @Override
        public Item getTabIconItem() {
            return ingot;
        }
    };

    public static OreManager oreManager;

    public static ItemBOIngot ingot;
    public static Item marmiteBread;
    public static Item badOreBook;

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        oreManager = new OreManager();

        oreManager.registerOre(new Polite());
        oreManager.registerOre(new Wannafite());
        oreManager.registerOre(new Breakium());
        oreManager.registerOre(new Crashium());
        oreManager.registerOre(new Stonium());
        oreManager.registerOre(new Crappium());
        oreManager.registerOre(new Enderite());
        oreManager.registerOre(new Website());
        oreManager.registerOre(new Lite());
        oreManager.registerOre(new Misleadium());
        oreManager.registerOre(new Ghostium());
        oreManager.registerOre(new Amadeum());
        oreManager.registerOre(new BarelyGenerite());
        oreManager.registerOre(new Unobtainium());
        oreManager.registerOre(new Copper());
        oreManager.registerOre(new Uselessium());
        oreManager.registerOre(new Killium());
        oreManager.registerOre(new Movium());
        oreManager.registerOre(new Balancium());
        oreManager.registerOre(new Explodeitmite());
        oreManager.registerOre(new Marmite());
        oreManager.registerOre(new Shiftium());
        oreManager.registerOre(new Smite());
        oreManager.registerOre(new Wantarite());
        oreManager.registerOre(new Idlikeabite());
        oreManager.registerOre(new Meteorite());
        oreManager.registerOre(new Streetscum());
        oreManager.registerOre(new Fleesonsite());
        oreManager.registerOre(new Nopium());
        oreManager.registerOre(new Zombieunite());
        oreManager.registerOre(new Paintitwhite());
        oreManager.registerOre(new Iwontfite());
        oreManager.registerOre(new Lookslikediamondium());
        oreManager.registerOre(new Tauntum());
        oreManager.registerOre(new Kakkarite());
        oreManager.registerOre(new Pandaemonium());
        oreManager.registerOre(new Nosleeptonite());
        oreManager.registerOre(new Appetite());

        // Do this after the ores are resisted, so we can programmatically check the list of disabled ores.
        config = new BOConfig(event.getSuggestedConfigurationFile());

        ingot = new ItemBOIngot();
        GameRegistry.registerItem(ingot, "ingot");

        badOreBook = new BadOreBook().setUnlocalizedName("badOreBook").setTextureName(MOD_ID + ":badOreBook");
        GameRegistry.registerItem(badOreBook, "badOreBook");

        oreManager.createGameElements();

        marmiteBread = new ItemFood(6, 0.8F, false).setUnlocalizedName("marmiteBread").setTextureName(MOD_ID + ":marmiteBread");
        GameRegistry.registerItem(marmiteBread, "marmiteBread", MOD_ID);

        EntityRegistry.registerModEntity(EntityFleeingBlock.class, "fleeingBlock", 0, this, 64, 3, true);
        EntityRegistry.registerModEntity(EntityNosleeptonite.class, "nosleeptonite", 1, this, 64, 3, true);

        GameRegistry.registerTileEntity(TileEntityBadOre.class, "badOre");

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new WorldGeneratorBadOres(oreManager), 100);

        network = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
        network.registerMessage(PacketRandomTranslation.Handle.class, PacketRandomTranslation.class, 0, Side.CLIENT);

        FMLCommonHandler.instance().bus().register(FMLEventHandler.INSTANCE);
        MinecraftForge.EVENT_BUS.register(ForgeEventHandler.INSTANCE);

        proxy.init(event);

        addCrafting();
        BOAchievementList.init();
    }

    private void addCrafting() {
        ItemStack marmiteIngot = new ItemStack(ingot);
        ItemBOIngot.setOre(marmiteIngot, oreManager.getOreByName("marmite"));
        GameRegistry.addShapelessRecipe(new ItemStack(marmiteBread), Items.bread, marmiteIngot);

        ItemStack explodeitmiteOre = oreManager.getBlockInfo(oreManager.getOreByName("explodeitmite")).asStack();
        GameRegistry.addSmelting(explodeitmiteOre, new ItemStack(Items.gunpowder), 2);

        ItemStack liteOre = oreManager.getBlockInfo(oreManager.getOreByName("lite")).asStack();
        GameRegistry.addSmelting(liteOre, new ItemStack(Items.glowstone_dust), 2);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static String getTextureName(String name) {
        return MOD_ID + ":" + name;
    }
}
