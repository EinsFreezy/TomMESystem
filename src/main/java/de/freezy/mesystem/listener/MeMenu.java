package de.freezy.mesystem.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MeMenu {


    public static void openMeMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8» §6MeSystem §8«");
        ItemStack itemStack = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§9Items einlagern");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(15, itemStack);


        ItemStack itemStack1 = new ItemStack(Material.ENDER_CHEST);
        ItemMeta itemMeta1 = itemStack1.getItemMeta();
        itemMeta1.setDisplayName("§9Deine Items");
        itemStack1.setItemMeta(itemMeta1);
        inventory.setItem(11, itemStack1);


        player.openInventory(inventory);
    }


    public static void openMeItems(Player player, int page) throws IOException {

        File file = new File("plugins/MeSystem/Items/" + player.getUniqueId() + ".yml");
        FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(file);

        List<ItemStack> list = (List<ItemStack>) cfg.getList("items");

        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Deine Items §7(§c" + page + "§7) §8«");

        int start = (page - 1) * 45;
        int end = page * 45;
        //add the items to inventory
        for (int i = start; i < end; i++) {
            if (i < list.size()) {
                inventory.addItem(list.get(i));
            }
        }

        //remove the items from inventory




        //get the number of pages
        int pages = list.size() / 45;

        ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta paneMeta = pane.getItemMeta();
        paneMeta.setDisplayName("§8");
        pane.setItemMeta(paneMeta);
        for (int i = 45; i < 54; i++) {
            inventory.setItem(i, pane);
        }

        ItemStack next = new ItemStack(Material.ARROW);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName("§9Nächste Seite");
        next.setItemMeta(nextMeta);
        inventory.setItem(53, next);

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§9Vorherige Seite");
        back.setItemMeta(backMeta);
        inventory.setItem(45, back);


        //49
        ItemStack craft = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta craftMeta = craft.getItemMeta();
        craftMeta.setDisplayName("§9Craften");
        craft.setItemMeta(craftMeta);
        //inventory.setItem(49, craft);


        player.openInventory(inventory);


        //remove items in inventory from list
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) {
                list.remove(inventory.getItem(i));
            }
        }
        cfg.set("items", list);
        cfg.save(file);

    }


    public static void addItemsToMeSystem(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8» §6Items einlagern §8«");

        player.openInventory(inventory);


    }


}
