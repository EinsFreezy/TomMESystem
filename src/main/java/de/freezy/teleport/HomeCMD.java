package de.freezy.teleport;

import de.freezy.mesystem.instance.Instance;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeCMD implements CommandExecutor {

    public static HashMap<Player, Location> homes = new HashMap<Player, Location>();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if (strings.length == 0) {


            homes.put(p, p.getLocation());

            Location loc = p.getBedSpawnLocation();



            p.teleport(loc);

            p.sendMessage("§aDu wurdest zu deinem Home Teleportiert!");





        }
        return false;


    }


}
