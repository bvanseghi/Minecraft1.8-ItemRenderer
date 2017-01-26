package com.example.examplemod;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.example.examplemod.render.ItemRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class RenderItemActualM41A extends ItemRenderer
{
	public static final ModelM41A model = new ModelM41A();

	public RenderItemActualM41A()
	{
		super(model, ModelM41A.texture);
	}

	@Override
	public ModelM41A getModel()
	{
		return (ModelM41A) super.getModel();
	}

	public String getAmmoCountDisplayString()
	{
		int ammoCount = (int) (Minecraft.getMinecraft().theWorld.getWorldTime() % 99);
		return (ammoCount < 10 ? "0" + ammoCount : String.valueOf(ammoCount));
	}

    @Override
    public void renderThirdPersonLeft(ItemStack itemstack, EntityLivingBase entity, TransformType cameraTransformType)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void renderThirdPersonRight(ItemStack itemstack, EntityLivingBase entity, TransformType cameraTransformType)
    {
//      PlayerResource player = resourceManager.createPlayerResource(((EntityPlayer) data[1]).getCommandSenderName());
//      this.resource = downloadResource(String.format(AliensVsPredator.settings().getUrlSkinM41a(), player.getUUID()), resourceLocation);

//      if (player != null)
        {
            GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(130.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.28F, -0.77F, 0.85F);
            float glScale = 1.3F;
            GlStateManager.scale(glScale, glScale, glScale);
            RenderUtil.bindTexture(getResourceLocation());
            this.getModel().render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
        }        
    }

    @Override
    public void renderFirstPersonLeft(ItemStack itemstack, EntityLivingBase entity, TransformType cameraTransformType)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void renderFirstPersonRight(ItemStack itemstack, EntityLivingBase entity, TransformType cameraTransformType)
    {
        float displayScale = 0.005F;
        float glScale = 0.6F;

        if (RenderUtil.firstPersonRenderCheck(Minecraft.getMinecraft().thePlayer))
        {
            if (Mouse.isButtonDown(0) && mc.inGameHasFocus)
            {
                GlStateManager.translate(-0.1F, 1.44F, -0.595F);
                GlStateManager.rotate(102F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(115.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(79F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0.027F, 0F, 0F);
            }
            else
            {
                GlStateManager.translate(0.1F, 1.55F, 0.2F);
                GlStateManager.rotate(95.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(120.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(79.0F, 0.0F, 0.0F, 1.0F);
            }

            GL11.glDisable(GL11.GL_CULL_FACE);
            GlStateManager.scale(glScale, glScale, glScale);
            RenderUtil.bindTexture(getResourceLocation());
            this.getModel().render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);

            if (itemstack != null && itemstack.getItem() instanceof ItemTest)
            {
                GL11.glDisable(GL11.GL_LIGHTING);
                GlStateManager.translate(-0.3439F, 0.6F, 0.04F);
                GlStateManager.scale(displayScale, displayScale, displayScale);
                GlStateManager.rotate(90F, 0F, 1F, 0F);
                Gui.drawRect(-2, -2, 16, 11, 0xFF000000);
                GlStateManager.translate(0F, 0F, -0.01F);
                GlStateManager.disableLighting();
                Minecraft.getMinecraft().fontRendererObj.drawString(getAmmoCountDisplayString(), 0, 0, 0xFFFF0000);
                GL11.glEnable(GL11.GL_LIGHTING);
                GlStateManager.color(1F, 1F, 1F, 1F);
            }
        }
    }

    @Override
    public void renderInInventory(ItemStack itemstack, EntityLivingBase entity, TransformType cameraTransformType)
    {
        GlStateManager.rotate(0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-40F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0F, -5.77F, -20.85F);
        GlStateManager.scale(20F, 20F, 20F);
        GL11.glDisable(GL11.GL_CULL_FACE);
        RenderUtil.bindTexture(getResourceLocation());
        this.getModel().render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
        GlStateManager.color(1F, 1F, 1F, 1F);
    }

    @Override
    public void renderInWorld(ItemStack itemstack, EntityLivingBase entity, TransformType cameraTransformType)
    {
        GlStateManager.translate(0.3F, 1F, 0F);
        GlStateManager.scale(1F, -1F, 1F);
        GL11.glDisable(GL11.GL_CULL_FACE);
        RenderUtil.bindTexture(getResourceLocation());
        this.getModel().render(null, 0, 0, 0, 0, 0, RenderUtil.DEFAULT_BOX_TRANSLATION);
    }
}
