package net.malisis.demo.minty;

import net.malisis.core.renderer.MalisisRenderer;
import net.malisis.core.renderer.RenderParameters;
import net.malisis.core.renderer.RenderType;
import net.malisis.core.renderer.element.Shape;
import net.malisis.core.renderer.element.shape.Cube;
import net.malisis.demo.minty.ArmoryOre.OreType;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.client.MinecraftForgeClient;

public class MintyOreRenderer extends MalisisRenderer
{
	private Shape shape;
	private RenderParameters rp;

	@Override
	protected void initialize()
	{
		//this method is called only once when the renderer is first called.
		//set up shapes, models and parameters here
		shape = new Cube();
		rp = new RenderParameters();
	}

	@Override
	public void render()
	{
		//when reaching this method, everything is already set up for rendering :
		//ISBRH already has the tessellator translated, TESR has the openGl states set up etc..
		//this.block holds the block instance, this.pos, the coordinates, this.titeEntity the TE

		OreType oreType = null;
		if (renderType == RenderType.ITEM)
			oreType = ((ItemBlockArmoryOre) itemStack.getItem()).getOreType(itemStack);
		if (renderType == RenderType.BLOCK)
			oreType = (OreType) blockState.getValue(ArmoryOre.oreTypeProperty);

		//Note : rp is RenderParameters and hold all the parameters available to tweak the rendering
		//reset the parameters so you don't use unwanted bleeding data
		rp.reset();

		if (renderType == RenderType.ITEM || MinecraftForgeClient.getRenderLayer() == EnumWorldBlockLayer.SOLID)
		{
			//titanium doesn't have its own brightness
			if (oreType != OreType.Titanium)
			{
				//do not automatically calculate brightness for the block
				rp.calculateBrightness.set(false);
				rp.useEnvironmentBrightness.set(false);
				//use this brightness instead
				rp.brightness.set(oreType.brightness);
			}

			//apply a general color for the block
			rp.colorMultiplier.set(oreType.color);
			//draw first layer : shape is preset to be a simple cube.
			drawShape(shape, rp);
		}

		if (renderType == RenderType.ITEM || MinecraftForgeClient.getRenderLayer() == EnumWorldBlockLayer.CUTOUT)
		{
			//reset the state to its original state because rendering may alter the shape
			shape.resetState();
			//reset parameters to their defaults so the brightness of this layer will be calculated
			rp.reset();
			//draw second layer
			drawShape(shape, rp);
		}

	}
}
