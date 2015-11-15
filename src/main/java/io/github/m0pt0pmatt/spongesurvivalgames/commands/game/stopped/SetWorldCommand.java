/*
 * This file is part of SpongeSurvivalGamesPlugin, licensed under the MIT License (MIT).
 *
 * Copyright (c) Matthew Broomfield <m0pt0pmatt17@gmail.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.m0pt0pmatt.spongesurvivalgames.commands.game.stopped;

import io.github.m0pt0pmatt.spongesurvivalgames.commands.CommandArgs;
import io.github.m0pt0pmatt.spongesurvivalgames.exceptions.NoWorldException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Command to set the world where the game will be played
 */
public class SetWorldCommand extends StoppedCommand {

    @Override
    public boolean execute(CommandSender sender, Map<CommandArgs, String> arguments) {

        if (!super.execute(sender, arguments)) {
            return false;
        }

        String worldName;
        if (!arguments.containsKey(CommandArgs.WORLDNAME)) {
            if (sender instanceof Player) {
                worldName = ((Player) sender).getWorld().getName();
            } else {
                sender.sendMessage("World name was not present.");
                return false;
            }
        } else {
            worldName = arguments.get(CommandArgs.WORLDNAME);
        }
        try {
            game.setWorldName(worldName);
        } catch (NoWorldException e) {
            sender.sendMessage("World \"" + worldName + "\" does not exist.");
            return false;
        }

        sender.sendMessage("World for game \"" + game.getID() + "\" is set to \"" + worldName + "\".");
        return true;
    }
}
