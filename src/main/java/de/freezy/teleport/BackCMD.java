package de.freezy.teleport;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player) commandSender;

        if(strings.length == 0) {
            if(HomeCMD.homes.containsKey(p)) {
                Location loc = HomeCMD.homes.get(p);
                p.teleport(loc);
                p.sendMessage("§aDu wurdest zu deinem Todesort Teleportiert!");
                HomeCMD.homes.remove(p);
            } else {
                p.sendMessage("§cKein Teleportationspunkt gefunden!");
            }
        }

        return false;
    }
}
