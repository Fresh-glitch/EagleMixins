package eaglemixins.mixin.otg.vanillagamerules;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.pg85.otg.forge.dimensions.OTGDerivedWorldInfo;
import net.minecraft.world.GameRules;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OTGDerivedWorldInfo.class)
public abstract class OTGDerivedWorldInfoGameRulesMixin {

    @Shadow(remap = false) private WorldInfo delegate;

    // OTG dimensions get their own GameRules instance that is rebuilt from scratch on every load and never read back from disk,
    // so /gamerule inside them never persists. Share the overworld's (level.dat) gamerules instead, like vanilla dimensions do.
    // getGameRulesInstance overrides vanilla WorldInfo (func_82574_x in production), so this target is remapped.
    @WrapMethod(method = "getGameRulesInstance")
    private GameRules eaglemixins_otgDerivedWorldInfo_useOverworldGameRules(Operation<GameRules> original) {
        return this.delegate.getGameRulesInstance();
    }
}
