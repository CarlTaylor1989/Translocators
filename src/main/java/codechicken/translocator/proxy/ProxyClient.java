package codechicken.translocator.proxy;

import codechicken.lib.internal.ModDescriptionEnhancer;
import codechicken.lib.packet.PacketCustom;
import codechicken.translocator.client.render.TileCraftingGridRenderer;
import codechicken.translocator.client.render.TileTranslocatorRenderer;
import codechicken.translocator.handler.CraftingGridKeyHandler;
import codechicken.translocator.init.ModBlocks;
import codechicken.translocator.init.ModItems;
import codechicken.translocator.network.TranslocatorCPH;
import codechicken.translocator.tile.TileCraftingGrid;
import codechicken.translocator.tile.TileItemTranslocator;
import codechicken.translocator.tile.TileLiquidTranslocator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ProxyClient extends Proxy {

    @Override
    public void preInit() {

        super.preInit();
        ModItems.initModels();
        ModBlocks.initModels();
    }

    public void init() {

        ModDescriptionEnhancer.enhanceMod("translocator");

        super.init();

        ClientRegistry.bindTileEntitySpecialRenderer(TileItemTranslocator.class, new TileTranslocatorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLiquidTranslocator.class, new TileTranslocatorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCraftingGrid.class, new TileCraftingGridRenderer());

        PacketCustom.assignHandler(TranslocatorCPH.channel, new TranslocatorCPH());

        MinecraftForge.EVENT_BUS.register(CraftingGridKeyHandler.instance);
        ClientRegistry.registerKeyBinding(CraftingGridKeyHandler.instance);
    }
}
