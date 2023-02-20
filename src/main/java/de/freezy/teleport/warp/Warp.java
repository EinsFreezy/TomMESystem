package de.freezy.teleport.warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Locale;

public class Warp implements Listener {

    //on click on command block
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType() == Material.COMMAND_BLOCK) {
                warpMenu(p);
            }
        }
    }


    @EventHandler
    public void onWarpItemClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if(p.getOpenInventory().getTitle().contains("§6§lWarp Menu")) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null) {
                return;
            }

            switch (event.getCurrentItem().getType()) {
                case EXPERIENCE_BOTTLE:
                    Location loc = new Location(Bukkit.getWorld("world_nether"), 119, 248, -667);
                    p.teleport(loc);
                    p.closeInventory();
                    break;
                case CREEPER_HEAD:
                    Location loc1 = new Location(Bukkit.getWorld("world"), 200, 230, 500);
                    p.teleport(loc1);
                    p.closeInventory();
                    break;
                case END_PORTAL_FRAME:
                    Location loc2 = new Location(Bukkit.getWorld("world"), 1374, -37, -2251);
                    p.teleport(loc2);
                    p.closeInventory();
                    break;
                case DARK_OAK_DOOR:
                    Location loc3 = new Location(Bukkit.getWorld("world"), 44, 64, -185);
                    p.teleport(loc3);
                    p.closeInventory();
                    break;
                case IRON_AXE:
                    Location loc4 = new Location(Bukkit.getWorld("world"), -169, 70, -457);
                    p.teleport(loc4);
                    p.closeInventory();
                    break;
                case ENDER_PEARL:
                    Location loc5 = new Location(Bukkit.getWorld("world_the_end"), 250, 71, 5);
                    p.teleport(loc5);
                    p.closeInventory();
                    break;
                case POISONOUS_POTATO:
                    Location loc6 = new Location(Bukkit.getWorld("world"), 1188, 151, 691);
                    p.teleport(loc6);
                    p.closeInventory();
                    break;
            }

        }

    }


    public static void warpMenu(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6§lWarp Menu");

        ItemStack warp1 = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta warp1Meta = warp1.getItemMeta();
        warp1Meta.setDisplayName("§aXP Farm");
        warp1.setItemMeta(warp1Meta);
        inv.addItem(warp1);

        ItemStack warp2 = new ItemStack(Material.CREEPER_HEAD);
        ItemMeta warp2Meta = warp2.getItemMeta();
        warp2Meta.setDisplayName("§aMob Farm");
        warp2.setItemMeta(warp2Meta);
        inv.addItem(warp2);

        ItemStack warp3 = new ItemStack(Material.END_PORTAL_FRAME);
        ItemMeta warp3Meta = warp3.getItemMeta();
        warp3Meta.setDisplayName("§aEnd Portal");
        warp3.setItemMeta(warp3Meta);
        inv.addItem(warp3);


        ItemStack warp4 = new ItemStack(Material.DARK_OAK_DOOR);
        ItemMeta warp4Meta = warp4.getItemMeta();
        warp4Meta.setDisplayName("§aBase Skrime");
        warp4.setItemMeta(warp4Meta);
        inv.addItem(warp4);

        ItemStack warp5 = new ItemStack(Material.IRON_AXE);
        ItemMeta warp5Meta = warp5.getItemMeta();
        warp5Meta.setDisplayName("§aBase Dominik");
        warp5.setItemMeta(warp5Meta);
        inv.addItem(warp5);


        ItemStack warp6 = new ItemStack(Material.ENDER_PEARL);
        ItemMeta warp6Meta = warp6.getItemMeta();
        warp6Meta.setDisplayName("§aEnderman Farm");
        warp6.setItemMeta(warp6Meta);
        inv.addItem(warp6);


        ItemStack warp7 = new ItemStack(Material.POISONOUS_POTATO);
        ItemMeta warp7Meta = warp7.getItemMeta();
        warp7Meta.setDisplayName("§aHexenfarm");
        warp7.setItemMeta(warp7Meta);
        inv.addItem(warp7);


        


        p.openInventory(inv);

    }


}
