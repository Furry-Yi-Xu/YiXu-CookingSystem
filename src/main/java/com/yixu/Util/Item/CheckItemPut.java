package com.yixu.Util.Item;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.yixu.Config.ConfigManager;
import com.yixu.Model.RecipeIngredient;
import com.yixu.Model.SlotAndAmount;
import com.yixu.Util.Message.MessageUtil;
import dev.lone.itemsadder.api.CustomStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckItemPut {

    public Map<Integer, List<SlotAndAmount>> findMaterialInInventory(Player player, List<RecipeIngredient> recipeIngredientList) {

        Map<Integer, List<SlotAndAmount>> matchResultMap = new HashMap<>();
        ArrayList<SlotAndAmount> slotAndAmounts = null;

        int mapIndex = 0;

        for (RecipeIngredient ingredient : recipeIngredientList) {

            String materialType = ingredient.getType();
            String materialName = ingredient.getMaterial();
            int requiredAmount = ingredient.getAmount();


            switch (materialType) {
                case "itemsadder":
                    slotAndAmounts = findIAItemSlot(player, materialName, requiredAmount);
                    break;
                default:
                    slotAndAmounts = findVanillaItemSlot(player, materialName, requiredAmount);
            }

            if (slotAndAmounts == null) {

                Map<String, String> variables = null;

                switch (materialType) {
                    case "itemsadder":
                        String displayName = CustomStack.getInstance(materialName).getDisplayName();
                        variables = Map.of(
                                "item", displayName
                        );
                        MessageUtil.sendMessage(player, "cooking.insufficient_item", variables);
                        break;
                    default:
                        Map<String, TranslatableComponent> variablesVanilla = null;
                        Material material = Material.matchMaterial(materialName.toUpperCase());
                        ItemStack itemStack = new ItemStack(material);
                        TranslatableComponent translationItemStack = Component.translatable(itemStack.translationKey());
                        variables = Map.of(
                                "item", ""
                        );
                        MessageUtil.sendMessage(player, "cooking.insufficient_item", variables, translationItemStack);
                }

                return null;
            }

            matchResultMap.put(mapIndex, slotAndAmounts);
            mapIndex++;

        }

        return matchResultMap;
    }


    public ArrayList<SlotAndAmount> findIAItemSlot(Player player, String namespaceID, int requireAmount) {

        ArrayList<SlotAndAmount> matchedSlotAndAmount = new ArrayList<>();

        int foundAmount = 0;

        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack itemStack = player.getInventory().getItem(i);

            if (itemStack == null) {
                continue;
            }

            CustomStack customStack = CustomStack.byItemStack(itemStack);

            if (customStack != null && customStack.getNamespacedID().equals(namespaceID)) {

                int amount = itemStack.getAmount();
                matchedSlotAndAmount.add(new SlotAndAmount(i, amount));
                foundAmount += amount;

                if (foundAmount >= requireAmount) {
                    return matchedSlotAndAmount;
                }
            }
        }

        return null;
    }

    public ArrayList<SlotAndAmount> findVanillaItemSlot(Player player, String itemName, int requireAmount) {

        ArrayList<SlotAndAmount> matchedSlotAndAmount = new ArrayList<>();

        int foundAmount = 0;

        for (int i = 0; i < player.getInventory().getSize(); i++) {

            ItemStack itemStack = player.getInventory().getItem(i);

            if (itemStack == null) {
                continue;
            }

            Material matchMaterial = Material.matchMaterial(itemName.toUpperCase());

            if (itemStack.getType() == matchMaterial) {

                int amount = itemStack.getAmount();
                matchedSlotAndAmount.add(new SlotAndAmount(i, amount));
                foundAmount += amount;

                if (foundAmount >= requireAmount) {
                    return matchedSlotAndAmount;
                }
            }
        }

        return null;
    }

}
