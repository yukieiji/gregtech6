package gregapi.worldgen;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.ConfigsGT;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.WD;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenOresSmall extends WorldgenObject {
	public final short mMinY, mMaxY, mAmount;
	public final OreDictMaterial mMaterial;
	
	public WorldgenOresSmall(String aName, boolean aDefault, int aMinY, int aMaxY, int aAmount, OreDictMaterial aPrimary, List... aLists) {
		super(aName, aDefault, aLists);
		mMinY				= (short)					ConfigsGT.WORLDGEN.get(mCategory, "MinHeight"	, aMinY);
		mMaxY				= (short)Math.max(mMinY+1, 	ConfigsGT.WORLDGEN.get(mCategory, "MaxHeight"	, aMaxY));
		mAmount				= (short)Math.max(1,		ConfigsGT.WORLDGEN.get(mCategory, "Amount"		, aAmount));
		mMaterial			= 							ConfigsGT.WORLDGEN.get(mCategory, "Ore"			, aPrimary);
		
		if (mEnabled && mMaterial.mID > 0) OreDictManager.INSTANCE.triggerVisibility("ore"+mMaterial.mNameInternal);
		
		if (mMaterial.mID <= 0) {
			ERR.println("The Material is not valid for Ores: " + mMaterial);
			mInvalid = T;
		}
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (GENERATE_BIOMES && aWorld.provider.dimensionId == 0 && aMinX >= -96 && aMinX <= 80 && aMinZ >= -96 && aMinZ <= 80) return F;
		for (int i = 0, j = Math.max(1, mAmount/2 + aRandom.nextInt(1+mAmount)/2); i < j; i++) WD.setSmallOre(aWorld, aMinX+aRandom.nextInt(16), mMinY+aRandom.nextInt(Math.max(1, mMaxY-mMinY)), aMinZ+aRandom.nextInt(16), mMaterial.mID);
		return T;
	}
}