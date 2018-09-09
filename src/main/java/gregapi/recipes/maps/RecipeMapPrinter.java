package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.data.IL;
import gregapi.oredict.OreDictMaterial;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.tileentity.computer.ITileEntityUSBPort;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapPrinter extends RecipeMap {
	public RecipeMapPrinter(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		
		if (rRecipe != null || aInputs == null || aInputs.length <= 0 || aInputs[0] == null || aFluids == null || aFluids.length <= 0 || aFluids[0] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		
		ItemStack tUSB = null, tPaper = null;
		NBTTagCompound tData = null;
		for (ItemStack aInput : aInputs) if (aInput != null) {
			if (tData == null) {
				if (OM.is_(OD_USB_STICKS[1], aInput)) {
					if (!aInput.hasTagCompound()) return rRecipe;
					tUSB = aInput;
					tData = tUSB.getTagCompound().getCompoundTag(NBT_USB_DATA);
				} else if (OM.is_(OD_USB_CABLES[1], aInput)) {
					if (aTileEntity == null) return rRecipe;
					tUSB = aInput;
					for (byte tSide : ALL_SIDES_VALID_ONLY[tUSB.hasTagCompound() && tUSB.getTagCompound().hasKey(NBT_USB_DIRECTION) ? tUSB.getTagCompound().getByte(NBT_USB_DIRECTION) : SIDE_ANY]) {
						DelegatorTileEntity<TileEntity> tDelegator = aTileEntity.getAdjacentTileEntity(tSide);
						if (tDelegator.mTileEntity instanceof ITileEntityUSBPort) {
							tData = ((ITileEntityUSBPort)tDelegator.mTileEntity).getUSBData(tDelegator.mSideOfTileEntity, 1);
							if (tData != null) if (tData.hasNoTags()) tData = null; else break;
						}
					}
				} else {
					tPaper = aInput;
				}
			} else {
				tPaper = aInput;
			}
		}
		if (tData == null || tData.hasNoTags()) return rRecipe;
		if (tPaper != null && tUSB != null) {
			if (OM.is_("gt:canvas", tPaper)) {
				if (tData.hasKey(NBT_CANVAS_BLOCK) && (!tPaper.hasTagCompound() || !tPaper.getTagCompound().hasKey(NBT_CANVAS_BLOCK))) {
					rRecipe = new Recipe(F, F, F, new ItemStack[] {ST.amount(1, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {ST.amount(1, tPaper)}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Cyan], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Magenta], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Yellow], 1, 9, T)}, null, 64, 16, 0);
					NBTTagCompound tNBT = rRecipe.mOutputs[0].getTagCompound();
					if (tNBT == null) tNBT = UT.NBT.make();
					tNBT.setInteger(NBT_CANVAS_BLOCK, tData.getInteger(NBT_CANVAS_BLOCK));
					tNBT.setInteger(NBT_CANVAS_META, tData.getInteger(NBT_CANVAS_META));
					UT.NBT.set(rRecipe.mOutputs[0], tNBT);
					return rRecipe;
				}
				return rRecipe;
			}
			if (IL.Paper_Blueprint_Empty.equal(tPaper, F, T)) {
				ItemStack[] tBlueprint = UT.NBT.getBlueprintCrafting(tData);
				if (tBlueprint != ZL_IS) {
					ItemStack tCrafted = CR.getany(DW, tBlueprint);
					return new Recipe(F, F, F, new ItemStack[] {ST.amount(1, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {IL.Paper_Blueprint_Used.getWithNameAndNBT(1, tCrafted==null?null:tCrafted.getDisplayName(), UT.NBT.setBlueprintCrafting(UT.NBT.make(), tBlueprint))}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_White], 1, 9, T)}, null, 32, 16, 0);
				}
				return rRecipe;
			}
			if (IL.Paper_Punch_Card_Empty.equal(tPaper, F, T)) {
				if (UT.Code.stringValid(UT.NBT.getPunchCardData(tData))) {
					rRecipe = new Recipe(F, F, F, new ItemStack[] {ST.amount(1, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {IL.Paper_Punch_Card_Encoded.get(1)}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T)}, null, 32, 16, 0);
					UT.NBT.setPunchCardData(rRecipe.mOutputs[0], UT.NBT.getPunchCardData(tData));
					return rRecipe;
				}
				return rRecipe;
			}
			if (OM.is_("paperEmpty", tPaper)) {
				if (IL.GC_Schematic_1.exists() && tData.hasKey("gc_schematics_1")) return new Recipe(F, F, F, new ItemStack[] {ST.amount(8, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {IL.GC_Schematic_1.getWithMeta(1, tData.getShort("gc_schematics_1"))}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)}, null, 2048, 16, 0);
				if (IL.GC_Schematic_2.exists() && tData.hasKey("gc_schematics_2")) return new Recipe(F, F, F, new ItemStack[] {ST.amount(8, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {IL.GC_Schematic_2.getWithMeta(1, tData.getShort("gc_schematics_2"))}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)}, null, 2048, 16, 0);
				if (IL.GC_Schematic_3.exists() && tData.hasKey("gc_schematics_3")) return new Recipe(F, F, F, new ItemStack[] {ST.amount(8, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {IL.GC_Schematic_3.getWithMeta(1, tData.getShort("gc_schematics_3"))}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)}, null, 2048, 16, 0);
				if (IL.IE_Blueprint_Projectiles_Common.exists() && tData.hasKey("ie_blueprint")) return new Recipe(F, F, F, new ItemStack[] {ST.amount(3, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {IL.IE_Blueprint_Projectiles_Common.getWithMeta(1, tData.getShort("ie_blueprint"))}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Blue], 3, 1, T)}, null, 2048, 16, 0);
				ItemStack[] tBlueprint = UT.NBT.getBlueprintCrafting(tData);
				if (tBlueprint != ZL_IS) {
					ItemStack tCrafted = CR.getany(null, tBlueprint);
					return new Recipe(F, F, F, new ItemStack[] {ST.amount(1, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {IL.Paper_Blueprint_Used.getWithNameAndNBT(1, tCrafted==null?null:tCrafted.getDisplayName(), UT.NBT.setBlueprintCrafting(UT.NBT.make(), tBlueprint))}, null, null, new FluidStack[] {DYE_FLUIDS_CHEMICAL[DYE_INDEX_Blue]}, null, 128, 16, 0);
				}
				if (UT.Code.stringValid(UT.NBT.getBookTitle(tData)) && UT.Code.stringValid(UT.NBT.getBookAuthor(tData))) {
					NBTTagList tPages = tData.getTagList("pages", 8);
					if (tPages == null || tPages.tagCount() < 1) {
				    	String aMapping = UT.NBT.getBookMapping(tData);
				    	if (UT.Code.stringValid(aMapping)) {
				    		ItemStack tBook = UT.Books.getWrittenBook(aMapping, NI);
				    		if (tBook != null && tBook.hasTagCompound()) tPages = tBook.getTagCompound().getTagList("pages", 8);
				    	}
					}
					boolean tUseManyPages = (tPages != null && tPages.tagCount() > 50);
					rRecipe = new Recipe(F, F, F, new ItemStack[] {ST.amount(tUseManyPages?6:3, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {tUseManyPages?IL.Paper_Printed_Pages_Many.get(1):IL.Paper_Printed_Pages.get(1)}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, tUseManyPages?1:2, T)}, null, tUseManyPages?1024:512, 16, 0);
					UT.NBT.set(rRecipe.mOutputs[0], (NBTTagCompound)tData.copy());
					return rRecipe;
				}
				short tID = tData.getShort(NBT_REPLICATOR_DATA);
				if (tID > 0 && UT.Code.exists(tID, OreDictMaterial.MATERIAL_ARRAY)) {
					OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[tID];
					if (ST.valid(tMaterial.mDictionaryBook)) {
						boolean tUseManyPages = (ST.meta(tMaterial.mDictionaryBook) == 32003);
						rRecipe = new Recipe(F, F, F, new ItemStack[] {ST.amount(tUseManyPages?6:3, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {tUseManyPages?IL.Paper_Printed_Pages_Many.get(1):IL.Paper_Printed_Pages.get(1)}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, tUseManyPages?1:2, T)}, null, tUseManyPages?1024:512, 16, 0);
						UT.NBT.set(rRecipe.mOutputs[0], tMaterial.mDictionaryBook.getTagCompound());
						return rRecipe;
					}
					return rRecipe;
				}
				return rRecipe;
			}
			if (tPaper.getItem() == Items.map) {
				short tMapID = UT.NBT.getMapID(tData);
				if (tMapID >= 0) {
					return new Recipe(F, F, F, new ItemStack[] {ST.amount(1, tPaper), ST.amount(0, tUSB)}, new ItemStack[] {ST.make(Items.filled_map, 1, tMapID)}, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Cyan], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Magenta], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Yellow], 1, 9, T)}, null, 64, 16, 0);
				}
				return rRecipe;
			}
			return rRecipe;
		}
		
		/*
		if (rRecipe == null) {
			ItemStack
			tOutput = GT_ModHandler.getAllRecipeOutput(aTileEntity==null?null:aTileEntity.getWorld(), aInputs[0], aInputs[0], aInputs[0], aInputs[0], IL.DYE_ONLY_ITEMS[aDye.mIndex].get(1), aInputs[0], aInputs[0], aInputs[0], aInputs[0]);
			if (tOutput != null) return addRecipe(new Recipe(T, new ItemStack[] {ST.amount_(8, aInputs[0])}, new ItemStack[] {tOutput}, null, null, new FluidStack[] {new FluidStack(aFluids[0].getFluid(), (int)L)}, null, 256, 2, 0), F, F, T);
			
			tOutput = GT_ModHandler.getAllRecipeOutput(aTileEntity==null?null:aTileEntity.getWorld(), aInputs[0], IL.DYE_ONLY_ITEMS[aDye.mIndex].get(1));
			if (tOutput != null) return addRecipe(new Recipe(T, new ItemStack[] {ST.amount_(1, aInputs[0])}, new ItemStack[] {tOutput}, null, null, new FluidStack[] {new FluidStack(aFluids[0].getFluid(), (int)L)}, null,  32, 2, 0), F, F, T);
		}*/
		return rRecipe;
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return T;}
}