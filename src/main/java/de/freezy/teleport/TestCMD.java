package de.freezy.teleport;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (strings.length >= 1) {
                if (strings[0].equalsIgnoreCase("art")) {
                    //get the eye location of the player
                    Location eyeLocation = player.getEyeLocation();

                    //set painting with wither on wall
                    Painting painting = (Painting) player.getWorld().spawnEntity(eyeLocation, EntityType.PAINTING);
                    //get vector of player facing
                    BlockFace blockFace = player.getFacing();

                    painting.setFacingDirection(blockFace, true);


                    Random random = new Random();
                    //rendom num betwee 1 and 3
                    int num = random.nextInt(3) + 1;
                    if (num == 1) {
                        painting.setArt(Art.FIRE, true);
                    } else if (num == 2) {
                        painting.setArt(Art.VOID, true);
                    } else if (num == 3) {
                        painting.setArt(Art.BURNING_SKULL, true);
                    }
                } else if (strings[0].equalsIgnoreCase("rocket")) {
                    if (player.getName().equals("EinsFreezy")) {
                    /*ItemStack itemStack = player.getInventory().getItemInMainHand();
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setUnbreakable(true);
                    itemStack.setItemMeta(itemMeta);
                    player.getInventory().getItemInMainHand().setItemMeta(itemMeta);*/
                        player.getInventory().addItem(OnDeath.rocket(Integer.parseInt(strings[1])));
                    }


                    //get painting player is looking at

                } else if (strings[0].equalsIgnoreCase("painting")) {
                    //get entity player is looking at
                    for(Entity e : player.getNearbyEntities(2, 2, 2)){
                        //Example
                        if(e.getType() == EntityType.PAINTING){
                            Painting painting = (Painting) e;
                            player.sendMessage(painting.getArt().name());
                        }
                    }

                } else if (strings[0].equalsIgnoreCase("setpainting")) {
                    //get entity player is looking at
                    for(Entity e : player.getNearbyEntities(2, 2, 2)){
                        //Example
                        if(e.getType() == EntityType.PAINTING){
                            Painting painting = (Painting) e;
                            painting.setArt(Art.valueOf(strings[1].toUpperCase()));
                        }
                    }

                } else if(strings[0].equalsIgnoreCase("oprocket")){
                    ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
                    FireworkMeta meta = (FireworkMeta) item.getItemMeta();
                    meta.setDisplayName("§4§lOP Rocket");
                    meta.setPower(Integer.parseInt(strings[1]));

                    meta.addEnchant(org.bukkit.enchantments.Enchantment.ARROW_INFINITE, 1, true);
                    item.setItemMeta(meta);
                    player.getInventory().addItem(item);
                } else {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setUnbreakable(true);
                    item.setItemMeta(itemMeta);
                    player.getInventory().getItemInMainHand().setItemMeta(itemMeta);


                }
            }
        }


        return false;
    }




    private MerchantRecipe getDiamond() {
        ItemStack result = new ItemStack(Material.DRAGON_EGG);
        ItemMeta meta = result.getItemMeta();
        result.setItemMeta(meta);
        MerchantRecipe recipe = new MerchantRecipe(result, 10);
        recipe.addIngredient(new ItemStack(Material.EMERALD, 64));
        recipe.addIngredient(new ItemStack(Material.EMERALD, 64));
        return recipe;
    }






}
