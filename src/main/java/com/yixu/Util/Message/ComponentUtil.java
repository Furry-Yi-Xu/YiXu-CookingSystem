package com.yixu.Util.Message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.List;

public class ComponentUtil {


    public static Component formatString(String text) {

        if (text == null) {
            return Component.empty();
        }
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }

    public static List<Component> formatList(List<String> lore) {

        if (lore == null) {
            return new ArrayList<>();
        }

        List<Component> loreComponents = new ArrayList<>();
        for (String line : lore) {
            Component component = MiniMessage.miniMessage().deserialize(line);
            loreComponents.add(component);
        }
        return loreComponents;
    }

}
