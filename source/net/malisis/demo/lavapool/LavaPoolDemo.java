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

import net.malisis.core.MalisisCore;
import net.malisis.demo.IDemo;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Ordinastie
 *
 */
public class LavaPoolDemo implements IDemo
{
	public static LavaPoolBlock lavaPool;
	public static FiniteLava finiteLava;

	@Override
	public void preInit()
	{
		lavaPool = new LavaPoolBlock();
		lavaPool.register();

		finiteLava = new FiniteLava();
		finiteLava.register();

		GameRegistry.registerTileEntity(LavaPoolTileEntity.class, "LavalPoolTe");
	}

	@Override
	public void init()
	{
		if (MalisisCore.isClient())
			new LavaPoolRenderer().registerFor(LavaPoolTileEntity.class);

	}
}
