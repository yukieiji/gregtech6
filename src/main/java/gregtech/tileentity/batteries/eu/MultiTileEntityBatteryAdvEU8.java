package gregtech.tileentity.batteries.eu;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.TileEntityBase08Battery;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBatteryAdvEU8 extends TileEntityBase08Battery {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return SIDES_HORIZONTAL[aSide] ? aRenderPass == 1 ? BlockTextureDefault.get(sTextures[3], mRGBa) : BlockTextureDefault.get(sTextures[2]) : aRenderPass == 1 ? null : BlockTextureDefault.get(sTextures[FACES_TBS[aSide]]);
	}
	
	public static IIconContainer sTextures[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/batteries/eu/advanced/8/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/batteries/eu/advanced/8/top"),
		new Textures.BlockIcons.CustomIcon("machines/batteries/eu/advanced/8/sides"),
		new Textures.BlockIcons.CustomIcon("machines/batteries/eu/advanced/8/bar"),
	};
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 1) {
			box(aBlock, PX_P[ 5]-0.002F, PX_P[ 1], PX_P[ 5]-0.002F, PX_N[ 5]+0.002F, PX_P[mDisplayedEnergy+1], PX_N[ 5]+0.002F);
			return T;
		}
		box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 8], PX_N[ 5]);
		return T;
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 8], PX_N[ 5]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 8], PX_N[ 5]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock,  PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 8], PX_N[ 5]);}
	
	@Override public byte getDisplayScaleMax() {return 4;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.battery.eu.adv.8";}
}