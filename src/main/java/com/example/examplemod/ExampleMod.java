package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID    = "examplemod";
    public static final String VERSION  = "1.0";

    public static ItemTest     itemTest = (ItemTest) new ItemTest().setFull3D().setUnlocalizedName("test").setCreativeTab(CreativeTabs.TOOLS).setRegistryName("test");

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);

        GameRegistry.register(itemTest);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemTest, 0, new ModelResourceLocation("examplemod:test", "inventory"));

        // ICustomModelLoader is needed to register this
        // ModelLoader.setCustomModelResourceLocation(itemTest, 0, new ModelResourceLocation(new ResourceLocation("examplemod:test"), "normal"));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event)
    {
        event.getModelRegistry().putObject(new ModelResourceLocation("examplemod:test", "inventory"), new RenderItemActualM41A());
    }
}
