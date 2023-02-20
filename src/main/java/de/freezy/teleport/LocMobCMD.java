package de.freezy.teleport;

import com.google.common.collect.Iterables;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocMobCMD implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        commandSender.sendMessage(getClosestPlayer( ((Player) commandSender),((Player) commandSender).getLocation(), EntityType.valueOf(strings[0].toUpperCase()))+"");

        return false;
    }


    //method that returns the location of the mob
    public static Location getClosestPlayer(Player p, Location location, EntityType entityType) {
        // From https://gist.github.com/fourohfour/8243657
        double closestDistance = Double.MAX_VALUE;

        Location loc = null;

        //con

        for (Entity ent : p.getWorld().getEntities()) {
            //check if the entity is a entitytype
            if (ent.getType() == entityType) {
                double distance = ent.getLocation().distance(location);
                if (closestDistance == Double.MAX_VALUE || distance < closestDistance) {
                    closestDistance = distance;
                    loc = ent.getLocation();

                }
            }
        }

        return loc;
    }


    //command tab completion




}
