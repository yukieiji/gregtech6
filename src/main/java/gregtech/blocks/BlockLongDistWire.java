package gregtech.blocks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.misc.BlockBaseMachineUpdate;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.render.IIconContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockLongDistWire extends BlockBaseMachineUpdate {
	public final byte[] mTiers;
	
	public BlockLongDistWire(String aUnlocalised, IIconContainer[] aIcons, byte[] aTiers) {
		super(null, aUnlocalised, Material.iron, soundTypeCloth, 16, aIcons, ~0);
		mTiers = aTiers;
		for (byte i = 0; i < 16; i++) LH.add(aUnlocalised+"."+i+".name" , "Long Distance Electric Wire ("+VN[mTiers[i]]+")");
	}
	
	@Override
	public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_VOLTAGE) + (VMAX[mTiers[aMeta]]) + " " + TD.Energy.EU.getLocalisedNameShort() + " (" + VN[mTiers[aMeta]] + ")");
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_AMPERAGE) + "UNLIMITED");
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_LOSS) + "0.125 " + TD.Energy.EU.getLocalisedNameShort() + "/m");
	}
	
	@Override public final int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return 150;}
	@Override public final int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return 150;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_cutter;}
	@Override public int getHarvestLevel(int aMeta) {return 3;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return T;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.iron_block.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(Entity aEntity, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {return 15;}
	@Override public float getExplosionResistance(Entity aEntity) {return 15;}
	@Override public float getExplosionResistance(int aMeta) {return 15;}
}