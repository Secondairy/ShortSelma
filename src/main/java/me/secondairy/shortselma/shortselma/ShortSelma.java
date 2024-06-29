package me.secondairy.shortselma.shortselma;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.Objects;

public class ShortSelma implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> ServerPlayConnectionEvents.JOIN.register((handler, sender, server1) -> {

            ServerPlayerEntity player = handler.player;
            Text PlayerName = player.getDisplayName();

            server1.execute(() -> {

                assert PlayerName != null;
                if (Objects.equals(PlayerName.getString(), "selmaganic")) {
                    System.out.println(ScaleTypes.HEIGHT.getScaleData(player).getScale());
                    if (ScaleTypes.HEIGHT.getScaleData(player).getScale() > 0.9) {
                        setPehkuiData(player, 0.8F);
                        server1.getPlayerManager().broadcast(Text.of("§cSelma Detected! Calculating lore-accurate Selma height..."), false);
                    }

                }
            });

        }));
    }

    public static void setPehkuiData(Entity entity, float scale) {
        ScaleTypes.BASE.getScaleData(entity).setScaleTickDelay(60);
        ScaleTypes.HEIGHT.getScaleData(entity).setScaleTickDelay(60);
        ScaleTypes.WIDTH.getScaleData(entity).setScaleTickDelay(60);
        ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale(scale);
        ScaleTypes.WIDTH.getScaleData(entity).setTargetScale(scale);
        ScaleTypes.BASE.getScaleData(entity).setPersistence(Boolean.TRUE);
        ScaleTypes.WIDTH.getScaleData(entity).setPersistence(Boolean.TRUE);
        ScaleTypes.HEIGHT.getScaleData(entity).setPersistence(Boolean.TRUE);
    }
}
