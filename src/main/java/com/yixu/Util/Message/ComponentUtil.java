package com.yixu.Util.Message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ComponentUtil {

    public static Component formatString(String text) {

        if (text == null) {
            return Component.empty();
        }
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }

    public static List<Component> formatList(List<String> lore) {

        if (lore == null) {
            return new ArrayList<Component>();
        }

        List<Component> loreComponents = new ArrayList<>();
        for (String line : lore) {
            Component component = MiniMessage.miniMessage().deserialize(line);
            loreComponents.add(component);
        }
        return loreComponents;
    }

    public static Component replacedComponent(Component replaceTarget, Component replaceContent) {

        Component replacedComponent = replaceTarget.replaceText(new Consumer<TextReplacementConfig.Builder>() {
            @Override
            public void accept(TextReplacementConfig.Builder builder) {
                builder.match("%material_itemstack%").replacement(replaceContent);
            }
        });

        return replacedComponent;
    }

}
