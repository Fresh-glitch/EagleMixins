package eaglemixins.mixin.otg.vanillagamerules;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.pg85.otg.configuration.dimensions.DimensionConfig;
import com.pg85.otg.forge.dimensions.OTGDimensionManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OTGDimensionManager.class)
public abstract class OTGDimensionManagerGameRulesMixin {

    // OTG writes every gamerule from its WorldConfig.ini / Dimensions config onto the world on every load
    // (and whenever its dimension GUI saves), overwriting anything set via /gamerule. Skip that entirely.
    @WrapMethod(method = "ApplyGameRulesToWorld", remap = false)
    private static void eaglemixins_otgDimensionManager_skipApplyGameRules(World world, DimensionConfig dimConfig, Operation<Void> original) {
        //no op
    }
}
