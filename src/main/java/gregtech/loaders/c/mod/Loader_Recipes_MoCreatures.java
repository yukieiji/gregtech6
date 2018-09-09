package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.ST;

public class Loader_Recipes_MoCreatures implements Runnable {
	@Override
	public void run() {
		if (MD.MoCr.mLoaded) {
			OUT.println("GT_Mod: Doing Mo'Creatures Recipes.");
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.MoCr, "fur", 5), ST.make(MD.MoCr, "furhelmet", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.MoCr, "fur", 8), ST.make(MD.MoCr, "furchest", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.MoCr, "fur", 7), ST.make(MD.MoCr, "furlegs", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.MoCr, "fur", 4), ST.make(MD.MoCr, "furboots", 1));
		    
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.MoCr, "reptilehide", 5), ST.make(MD.MoCr, "reptilehelmet", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.MoCr, "reptilehide", 8), ST.make(MD.MoCr, "reptileplate", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.MoCr, "reptilehide", 7), ST.make(MD.MoCr, "reptilelegs", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.MoCr, "reptilehide", 4), ST.make(MD.MoCr, "reptileboots", 1));
		    
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.MoCr, "hide", 5), ST.make(MD.MoCr, "hidehelmet", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.MoCr, "hide", 8), ST.make(MD.MoCr, "hidechest", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.MoCr, "hide", 7), ST.make(MD.MoCr, "hidelegs", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.MoCr, "hide", 4), ST.make(MD.MoCr, "hideboots", 1));
		    
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.MoCr, "chitin", 5), ST.make(MD.MoCr, "scorphelmetdirt", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.MoCr, "chitin", 8), ST.make(MD.MoCr, "scorpplatedirt", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.MoCr, "chitin", 7), ST.make(MD.MoCr, "scorplegsdirt", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.MoCr, "chitin", 4), ST.make(MD.MoCr, "scorpbootsdirt", 1));
		    
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.MoCr, "chitinblack", 5), ST.make(MD.MoCr, "scorphelmetcave", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.MoCr, "chitinblack", 8), ST.make(MD.MoCr, "scorpplatecave", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.MoCr, "chitinblack", 7), ST.make(MD.MoCr, "scorplegscave", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.MoCr, "chitinblack", 4), ST.make(MD.MoCr, "scorpbootscave", 1));
		    
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.MoCr, "chitinnether", 5), ST.make(MD.MoCr, "scorphelmetnether", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.MoCr, "chitinnether", 8), ST.make(MD.MoCr, "scorpplatenether", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.MoCr, "chitinnether", 7), ST.make(MD.MoCr, "scorplegsnether", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.MoCr, "chitinnether", 4), ST.make(MD.MoCr, "scorpbootsnether", 1));
		    
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.MoCr, "chitinfrost", 5), ST.make(MD.MoCr, "scorphelmetfrost", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.MoCr, "chitinfrost", 8), ST.make(MD.MoCr, "scorpplatefrost", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.MoCr, "chitinfrost", 7), ST.make(MD.MoCr, "scorplegsfrost", 1));
		    RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.MoCr, "chitinfrost", 4), ST.make(MD.MoCr, "scorpbootsfrost", 1));
		}
	}
}