package skytils.skytilsmod.mixins;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import skytils.skytilsmod.events.CheckRenderEntityEvent;

@Mixin(Render.class)
public abstract class MixinRender<T extends Entity> {
    @Inject(method = "shouldRender", at = @At(value = "HEAD"), cancellable = true)
    private void shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ, CallbackInfoReturnable<Boolean> cir) {
        if (MinecraftForge.EVENT_BUS.post(new CheckRenderEntityEvent<T>(livingEntity, camera, camX, camY, camZ))) cir.setReturnValue(false);
    }
}
