/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Ordinastie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.malisis.demo.test;

import java.util.List;

import net.malisis.core.MalisisCore;
import net.malisis.demo.MalisisDemos;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author Ordinastie
 * 
 */
public class TestBlock extends Block
{
	public static int currentPass = 0;
	private Block baseBlock;
	private IIcon overlay;

	protected TestBlock(String name, Block block)
	{
		super(block.getMaterial());
		baseBlock = block;
		setBlockName("testblock" + name);
		setCreativeTab(MalisisDemos.tabDemos);

	}

	public Block getBaseBlock()
	{
		return baseBlock;
	}

	public IIcon getIconOverlay()
	{
		return overlay;
	}

	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		overlay = register.registerIcon(MalisisDemos.modid + ":ice_overlay");
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int metadata)
	{
		MalisisCore.message("> " + metadata);

		return metadata;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		baseBlock.getSubBlocks(item, tab, list);
	}

	@Override
	public IIcon getIcon(int side, int metadata)
	{
		return currentPass == 0 ? baseBlock.getIcon(side, metadata) : overlay;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean canRenderInPass(int pass)
	{
		currentPass = pass;
		return true;
	}

}