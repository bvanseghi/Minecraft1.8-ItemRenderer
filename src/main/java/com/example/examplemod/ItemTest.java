package com.example.examplemod;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTest extends Item
{
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
}
