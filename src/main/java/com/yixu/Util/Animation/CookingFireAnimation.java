package com.yixu.Util.Animation;

import com.yixu.CookingSystem;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class CookingFireAnimation {

    public static void startAnimation(Player player, int delayTicks) {
        new BukkitRunnable() {

            final String progressImage = PlaceholderAPI.setPlaceholders(player, "%img_ryjian%");
            final String pointerImage = PlaceholderAPI.setPlaceholders(player, "%img_lljian%");

            final int randomStep = ThreadLocalRandom.current().nextInt(3, 11);

            int step = 185;
            int totalStep = 0;

            boolean forward = false;

            @Override
            public void run() {

                if (step > 5 && !forward) {
                    step -= randomStep;
                } else {
                    forward = true;
                }

                if (step < 185 && forward) {
                    step += randomStep;
                } else {
                    forward = false;
                }

                showAnimationTitle(player, step, progressImage, pointerImage);

                totalStep += randomStep;

                if (totalStep >= 365) {
                    this.cancel();
                    return;
                }
            }

        }.runTaskTimer(CookingSystem.getInstance(), 0L, delayTicks);
    }

    private static void showAnimationTitle(Player player, int timer, String progressImage, String pointerImage) {

        TextComponent.Builder bar = Component.text();

        String offset = "%img_offset_-" + timer + "%";

        String offsetReplaced = PlaceholderAPI.setPlaceholders(player, offset);

        bar.append(Component.text(offsetReplaced));

        bar.append(Component.text(progressImage));

        bar.append(Component.text(offsetReplaced));

        bar.append(Component.text(pointerImage));

        Title title = Title.title(
                Component.text("&6看准时机，右键点击！"),
                bar.build(),
                Title.Times.times(Duration.ZERO, Duration.ofMillis(200), Duration.ZERO)
        );

        player.showTitle(title);
    }
}
