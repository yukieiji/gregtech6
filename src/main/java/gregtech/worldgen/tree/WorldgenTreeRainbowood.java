/**
 * Copyright (c) 2020 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.worldgen.tree;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.block.tree.BlockBaseSapling;
import gregapi.data.CS.BlocksGT;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenTreeRainbowood extends WorldgenObject {
	@SafeVarargs
	public WorldgenTreeRainbowood(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextInt(aBiomeNames.contains("Enchanted Forest") ? 4 : 32768) == 0 || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		int tX = aRandom.nextInt(16), tZ = aRandom.nextInt(16);
		for (int tY = aWorld.provider.hasNoSky ? 80 : aWorld.getHeight()-50; tY > 0; tY--) if (BlocksGT.plantableTrees.contains(aChunk.getBlock(tX, tY, tZ))) return ((BlockBaseSapling)BlocksGT.Sapling).grow(aWorld, aMinX + tX, tY+1, aMinZ + tZ, (byte)7, aRandom);
		return F;
	}
}
