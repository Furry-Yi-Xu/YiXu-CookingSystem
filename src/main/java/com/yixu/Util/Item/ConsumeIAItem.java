package com.yixu.Util.Item;

import com.yixu.Util.Message.MessageUtil;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ConsumeIAItem {

    public int getItemAmount(Player player, String namespaceID) {

        int amount = 0;

        for (ItemStack itemStack : player.getInventory().getContents()) {
            CustomStack customStack = CustomStack.byItemStack(itemStack);
            if (itemStack != null && customStack.byItemStack(itemStack) != null && customStack.getNamespacedID().equals(namespaceID)) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }

    public boolean checkItemEnough(Player player, String namespaceID, int consumeAmount) {
        int itemAmount = getItemAmount(player, namespaceID);

        if (itemAmount >= consumeAmount) {
            removeItemAmount(player, namespaceID, consumeAmount);
            return true;
        }

        MessageUtil.sendMessage(player, "farm.insufficient_item");
        return false;
    }

    public void removeItemAmount(Player player, String namespaceID, int consumeAmount) {

        int amount = consumeAmount;
        ItemStack[] inventory = player.getInventory().getContents();

        for (int i = 0; i < inventory.length; i++) {

            ItemStack item = inventory[i];
            CustomStack customStack = CustomStack.byItemStack(item);

            if (amount == 0) {
                break;
            }

            if (item != null && customStack != null && customStack.getNamespacedID().equals(namespaceID)) {
                if (item.getAmount() >= amount) {
                    item.setAmount(item.getAmount() - amount);
                    amount = 0;
                }
                else {
                    amount -= item.getAmount();
                    item.setAmount(0);
                }
            }
        }
    }
}


