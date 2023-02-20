package de.freezy.mesystem.listener;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MeClickListener implements Listener {

    public static List<Player> craft = new ArrayList<>();


    @EventHandler
    public void onClick(org.bukkit.event.inventory.InventoryClickEvent event) throws IOException {
        if (event.getView().getTitle().equalsIgnoreCase("§8» §6MeSystem §8«")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            Player player = (Player) event.getWhoClicked();

            ItemStack itemStack = event.getCurrentItem();
            switch (itemStack.getType()) {
                case CHEST:
                    MeMenu.addItemsToMeSystem(player);
                    break;
                case ENDER_CHEST:
                    MeMenu.openMeItems(player, 1);
                    break;

            }

        } else if(event.getView().getTitle().contains("§8» §6Deine Items §7(§c")) {

            if (event.getCurrentItem() == null) {
                return;
            }
            Player player = (Player) event.getWhoClicked();

            ItemStack itemStack = event.getCurrentItem();
            switch (itemStack.getType()) {
                case CRAFTING_TABLE:
                    event.setCancelled(true);
                    //open crafting table
                    player.openWorkbench(null, true);
                    craft.add(player);
                    break;
                case BLACK_STAINED_GLASS_PANE:
                    event.setCancelled(true);
                    break;
                case ARROW:
                    event.setCancelled(true);
                    int page = Integer.parseInt(event.getView().getTitle().split("§8» §6Deine Items §7(§c\")[1].split(\"§7) §8«")[0]);



                    if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§9Vorherige Seite")) {
                        if(page != 1) {
                            MeMenu.openMeItems(player, page - 1);
                        }
                    } else if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§9Nächste Seite")) {
                        MeMenu.openMeItems(player, page+1);
                    }
                    break;

            }
        }


    }


    @EventHandler
    public void meSystemClose(org.bukkit.event.inventory.InventoryCloseEvent event) throws IOException, InvalidConfigurationException {
        System.out.println(event.getInventory().getType());
        if (event.getView().getTitle().equalsIgnoreCase("§8» §6Items einlagern §8«")) {
            Player player = (Player) event.getPlayer();
            File file = new File("plugins/MeSystem/Items/" + player.getUniqueId() + ".yml");
            FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(file);

            //load file

            List<ItemStack> list = (List<ItemStack>) cfg.getList("items");
            if(list == null) {
                list = new ArrayList<>();
            }


            //get all items from the inventory
            for (ItemStack itemStack : event.getInventory().getContents()) {
                if (itemStack != null) {
                    list.add(itemStack);
                }
            }


            //save the items
            cfg.set("items", list);

            cfg.save(file);


        } else if (event.getView().getTitle().contains("§8» §6Deine Items §7(§c")) {
            Player player = (Player) event.getPlayer();
            File file = new File("plugins/MeSystem/Items/" + player.getUniqueId() + ".yml");
            FileConfiguration cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(file);

            //load file

            List<ItemStack> list = (List<ItemStack>) cfg.getList("items");

            //get all items from the inventory
            //add the first 45 items to the inventory
            for (int i = 0; i < 45; i++) {
                if (event.getInventory().getItem(i) != null) {
                    list.add(event.getInventory().getItem(i));
                }
            }

            //save the items
            cfg.set("items", list);

            cfg.save(file);
        } else if(event.getInventory().getType().equals(InventoryType.WORKBENCH)) {
            if (craft.contains(event.getPlayer())) {
                craft.remove(event.getPlayer());
                Player player = (Player) event.getPlayer();
                player.closeInventory();
                MeMenu.openMeItems((Player) event.getPlayer(), 1);

            }
        }

    }
}
