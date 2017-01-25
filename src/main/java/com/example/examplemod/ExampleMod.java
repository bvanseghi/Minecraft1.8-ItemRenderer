package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
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
	public static final String MODID = "examplemod";
	public static final String VERSION = "1.0";

	public static ItemTest itemTest = (ItemTest) new ItemTest().setFull3D().setUnlocalizedName("test").setCreativeTab(CreativeTabs.tabTools);

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
	
		GameRegistry.registerItem(itemTest, "test");
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemTest, 0, new ModelResourceLocation("examplemod:test", "inventory"));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onModelBake(ModelBakeEvent event)
	{
		event.modelRegistry.putObject(new ModelResourceLocation("examplemod:test", "inventory"), new RenderItemActualM41A());
	}
}
