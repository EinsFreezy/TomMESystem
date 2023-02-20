package de.freezy.teleport;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Furnace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class OnDeath implements Listener {

    @EventHandler
    public void onRespawn(PlayerDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            Location loc = p.getLocation();
            HomeCMD.homes.put(p, loc);
            p.sendMessage("Dein Todes Ort: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
        }
    }

    @EventHandler
    //on rocket use
    public void onRocket(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getItem() != null) {
                if (e.getItem().getType() == Material.FIREWORK_ROCKET) {
                    Player p = e.getPlayer();
                    //get block under player
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lInfinity Rocket")) {
                        Block b = p.getLocation().getBlock().getRelative(0, -1, 0);
                        if (b.getType() != Material.AIR) {
                            e.setCancelled(true);
                            if (!p.isInWater()) {
                                p.setVelocity(p.getLocation().getDirection().multiply(2).setY(1));
                            }
                            //cancel remove item
                            //set item in player hand to air
                            //p.getInventory().setItemInMainHand(rocket());
                        } else {
                            //check if player is elytra flying
                            if (p.isGliding()) {
                                item.setAmount(item.getAmount() + 1);

                            }

                            //p.getInventory().setItemInMainHand(rocket());
                            //add 1 to item in hand
                        }


                    }
                }
            }
        } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() != null) {
                if (e.getItem().getType() == Material.FIREWORK_ROCKET) {
                    Player p = e.getPlayer();
                    //get block under player

                    ItemStack item = p.getInventory().getItemInMainHand();
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lInfinity Rocket")) {
                        if (e.getClickedBlock().getType() != Material.WATER) {
                            e.setCancelled(true);
                            p.setVelocity(p.getLocation().getDirection().multiply(2).setY(1));
                        }
                    }
                }
            }
        }
    }


    public static ItemStack rocket(int power) {
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
        FireworkMeta meta = (FireworkMeta) item.getItemMeta();
        meta.setDisplayName("§6§lInfinity Rocket");
        meta.setPower(power);

        meta.addEnchant(org.bukkit.enchantments.Enchantment.ARROW_INFINITE, 1, true);
        item.setItemMeta(meta);

        return item;
    }
/*

    @EventHandler
    public void onFurnace(FurnaceBurnEvent e) {
        org.bukkit.block.Furnace f = (org.bukkit.block.Furnace) e.getBlock().getState();
        f.setBurnTime((short) 6000);
        f.setCookTime((short) 1);
        f.setCookTimeTotal((short) 1);
        e.setBurnTime(60000);
    }*/

/*
    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof PigZombie) {
            int x = 0;
            while (x <= 2) {
                PigZombie pig = (PigZombie) e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.ZOMBIFIED_PIGLIN);
                x++;
            }

        }

    }*/


    @EventHandler
    public void onTotem(EntityResurrectEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            //cancel totem removal
            if(e.getHand() == EquipmentSlot.OFF_HAND) {
                if(p.getName().equalsIgnoreCase("EinsFreezy")) {

                    p.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING));
                }
            }
        }
    }

}
