package dev.beabueno.wisteriaessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    static final int HEALTH_FULL = 20;

    static final String PLAYER_FULL = "Your health bar has been filled up";

    static final String PERM_OWN = "carbonessentials.heal";
    static final String PERM_OTHERS = "carbonessentials.others";


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            if (args.length == 0) { //No player to heal
                Player p = (Player) sender;
                if (p.hasPermission(PERM_OWN)){
                    p.setHealth(HEALTH_FULL);
                    p.sendMessage(ChatColor.GREEN + PLAYER_FULL);
                    return true;
                } else {
                    p.sendMessage(CommandMessages.PLAYER_NOPERM);
                    return false;
                }
            } else if (args.length == 1) { //There's a player to heal
                Player target = Bukkit.getPlayer(args[0]);
                Player p = (Player) sender;
                if (p.hasPermission(PERM_OTHERS)) {
                    if (target != null) {
                        target.setHealth(HEALTH_FULL);
                        p.sendMessage(ChatColor.GREEN + PLAYER_FULL);
                        return true;
                    } else {
                        p.sendMessage(CommandMessages.PLAYER_NOTONLINE);
                        return false;
                    }
                } else {
                    p.sendMessage(CommandMessages.PLAYER_NOPERM);
                    return false;
                }
            } else {
                return false;
            }
        } else {
            System.out.println("You're console! You can't be healed.");
        }

        return false;
    }
}
