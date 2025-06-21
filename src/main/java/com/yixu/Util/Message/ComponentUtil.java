package com.yixu.Util.Message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ComponentUtil {
    public static Component formatString(String text) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }
}
