package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.ResourceLocation;

public class RenderUtil
{
    private static final Minecraft MC                      = Minecraft.getMinecraft();
    public static final float      DEFAULT_BOX_TRANSLATION = 0.625F;

    public static void bindTexture(ResourceLocation resourceLocation)
    {
        MC.renderEngine.bindTexture(resourceLocation);
    }

    public static boolean firstPersonRenderCheck(Object o)
    {
        return o == MC.getRenderViewEntity() && MC.gameSettings.thirdPersonView == 0 && (!(MC.currentScreen instanceof GuiInventory) && !(MC.currentScreen instanceof GuiContainerCreative));
    }
}
