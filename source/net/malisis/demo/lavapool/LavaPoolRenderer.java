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

package net.malisis.demo.lavapool;

import net.malisis.core.renderer.MalisisRenderer;
import net.malisis.core.renderer.RenderParameters;
import net.malisis.core.renderer.animation.AnimationRenderer;
import net.malisis.core.renderer.animation.transformation.AlphaTransform;
import net.malisis.core.renderer.element.shape.Cube;
import net.malisis.core.util.BlockState;
import net.minecraft.client.renderer.RenderBlocks;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

/**
 * @author Ordinastie
 *
 */
public class LavaPoolRenderer extends MalisisRenderer
{
	RenderBlocks renderBlocks;
	LavaPoolBlock block;
	LavaPoolTileEntity tileEntity;
	AnimationRenderer ar = new AnimationRenderer();

	@Override
	protected void initialize()
	{
		renderBlocks = new RenderBlocks(LavaPoolDemo.lavaPool.multiBlock);
		shape = new Cube();
		rp = new RenderParameters();
	}

	@Override
	public void render()
	{
		int a = 155;
		enableBlending();
		tileEntity = (LavaPoolTileEntity) super.tileEntity;
		block = (LavaPoolBlock) super.block;
		if (tileEntity.startAnim)
		{
			ar.setStartTime();
			tileEntity.startAnim = false;
			rp.alpha.set(a);
			renderBlocks = new RenderBlocks(LavaPoolDemo.lavaPool.multiBlock);
		}

		AlphaTransform at = new AlphaTransform(a, 0).forTicks(40, 40);
		ar.animate(rp, at);
		rp.interpolateUV.set(false);

		for (BlockState state : block.multiBlock)
		{
			if (!state.matchesWorld(world))
			{
				GL11.glPushMatrix();
				GL11.glTranslated(0.5F, 0.5F, 0.5F);
				GL11.glTranslated(-x - 0.5F, -y - 0.5F, -z - 0.5F);

				GL11.glBlendFunc(GL11.GL_CONSTANT_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_ALPHA);
				GL14.glBlendColor(0, 0, 0, rp.alpha.get() / 255F);
				renderBlocks.renderBlockByRenderType(state.getBlock(), state.getX(), state.getY(), state.getZ());

				GL11.glPopMatrix();
			}
		}
	}
}
