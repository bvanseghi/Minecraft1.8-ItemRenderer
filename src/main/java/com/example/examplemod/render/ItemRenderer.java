package com.example.examplemod.render;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.client.model.ISmartItemModel;

public abstract class ItemRenderer implements IPerspectiveAwareModel, ISmartItemModel, ISmartBlockModel
{
    public enum ItemRenderType
    {
        ENTITY, 
        EQUIPPED, 
        EQUIPPED_FIRST_PERSON, 
        INVENTORY, 
        FIRST_PERSON_MAP
    }
    
    protected static final float DEFAULT_BOX_TRANSLATION = 0.625F;
	private final Pair<? extends IFlexibleBakedModel, Matrix4f> selfPair;
	private static List<BakedQuad> quads = Collections.emptyList();
	protected Minecraft mc = Minecraft.getMinecraft();
	protected IBlockState blockstate;
	protected ItemStack itemstack;
	protected ResourceLocation resource;
	protected ModelBase model;
	private static final ItemCameraTransforms cameraTransforms = new ItemCameraTransforms(new ItemTransformVec3f(new Vector3f(-2.5F, -100.0F, 90.0F), new Vector3f(0.0125F, 0.3125F, -0.075F), new Vector3f(0.55F, 0.55F, 0.55F)), new ItemTransformVec3f(new Vector3f(-8.0F, 42.0F, 6.0F), new Vector3f(-0.1F, 0.075F, 0.05F), new Vector3f(0.7F, 1.0F, 1.0F)), new ItemTransformVec3f(new Vector3f(0.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.1F, -0.05F), new Vector3f(0.7F, 0.7F, 0.7F)), new ItemTransformVec3f(new Vector3f(0.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.1F, -0.05F), new Vector3f(0.7F, 0.7F, 0.7F)), new ItemTransformVec3f(new Vector3f(0.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.1F, -0.05F), new Vector3f(0.7F, 0.7F, 0.7F)), new ItemTransformVec3f(new Vector3f(0.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.1F, -0.05F), new Vector3f(0.7F, 0.7F, 0.7F)));
	private static final ItemCameraTransforms cameraTransformsSimple = new ItemCameraTransforms(new ItemTransformVec3f(new Vector3f(-2.5F, -100.0F, 90.0F), new Vector3f(-0.0125F, 0.3125F, -0.075F), new Vector3f(0.55F, 0.55F, 0.55F)), new ItemTransformVec3f(new Vector3f(-8.0F, 42.0F, 6.0F), new Vector3f(0.0F, 0.075F, 0.0F), new Vector3f(1.0F, 1.0F, 1.0F)), new ItemTransformVec3f(new Vector3f(0.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.1F, -0.05F), new Vector3f(0.7F, 0.7F, 0.7F)), new ItemTransformVec3f(new Vector3f(0.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.1F, -0.05F), new Vector3f(0.7F, 0.7F, 0.7F)), new ItemTransformVec3f(new Vector3f(0.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.1F, -0.05F), new Vector3f(0.7F, 0.7F, 0.7F)), new ItemTransformVec3f(new Vector3f(0.0F, -90.0F, 0.0F), new Vector3f(0.0F, -0.1F, -0.05F), new Vector3f(0.7F, 0.7F, 0.7F)));

	public ItemRenderer(ModelBase model, ResourceLocation resource)
	{
		this.selfPair = Pair.of(this, null);
		this.model = model;
		this.resource = resource;
	}

	@Override
	public Pair<? extends IFlexibleBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
	{
		Object data = new Object[] {};
		
		switch (cameraTransformType)
		{
			case FIRST_PERSON:
			{
				GlStateManager.pushMatrix();
				this.renderFirstPerson(itemstack, data);
				GlStateManager.popMatrix();
			}
				break;
			case GUI:
			{
				GlStateManager.pushMatrix();
				GlStateManager.rotate(-45, 1, 0, 0);
				GlStateManager.rotate(180, 0, 1, 0);
				GlStateManager.translate(-16, 0, 0);
				this.renderInInventory(itemstack, data);
				GlStateManager.popMatrix();
			}
				break;
			case THIRD_PERSON:
			{
				GlStateManager.pushMatrix();
				this.renderThirdPerson(itemstack, data);
				GlStateManager.popMatrix();
			}
				break;
				
			case GROUND:
			{
				GlStateManager.pushMatrix();
				this.renderInWorld(itemstack, data);
				GlStateManager.popMatrix();
			}
				break;
				
			default:
				break;
		}

		return selfPair;
	}

	public abstract void renderThirdPerson(ItemStack itemstack, Object... data);

	public abstract void renderFirstPerson(ItemStack itemstack, Object... data);

	public abstract void renderInInventory(ItemStack itemstack, Object... data);

	public abstract void renderInWorld(ItemStack itemstack, Object... data);

	public ModelBase getModel()
	{
		return model;
	}

	public ResourceLocation getResourceLocation()
	{
		return resource;
	}

	public void setResourceLocation(ResourceLocation resource)
	{
		this.resource = resource;
	}

	@Override
	public List<BakedQuad> getFaceQuads(EnumFacing facing)
	{
		return quads;
	}

	@Override
	public List<BakedQuad> getGeneralQuads()
	{
		return quads;
	}

	@Override
	public boolean isAmbientOcclusion()
	{
		return true;
	}

	@Override
	public boolean isGui3d()
	{
		return true;
	}

	@Override
	public boolean isBuiltInRenderer()
	{
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return null;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms()
	{
		return ItemCameraTransforms.DEFAULT;
	}

	@Override
	public VertexFormat getFormat()
	{
		return DefaultVertexFormats.ITEM;
	}

	@Override
	public IBakedModel handleItemState(ItemStack stack)
	{
		this.itemstack = stack;
		return this;
	}
	
	@Override
	public IBakedModel handleBlockState(IBlockState state)
	{
		this.blockstate = state;
		return null;
	}
	
	protected void bindTexture(ResourceLocation resourceLocation)
	{
		this.mc.renderEngine.bindTexture(resourceLocation);
	}
	
	public boolean firstPersonRenderCheck(Object o)
	{
		return o == mc.getRenderViewEntity() && mc.gameSettings.thirdPersonView == 0 && (!(mc.currentScreen instanceof GuiInventory) && !(mc.currentScreen instanceof GuiContainerCreative));
	}
}
