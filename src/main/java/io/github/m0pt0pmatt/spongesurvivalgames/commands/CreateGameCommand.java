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

package io.github.m0pt0pmatt.spongesurvivalgames.commands;

import io.github.m0pt0pmatt.spongesurvivalgames.BukkitSurvivalGamesPlugin;
import io.github.m0pt0pmatt.spongesurvivalgames.SurvivalGame;
import org.bukkit.command.CommandSender;

import java.util.Map;

/**
 * Command to create a new game
 */
public class CreateGameCommand extends SurvivalGamesCommand {

    @Override
    public boolean execute(CommandSender sender, Map<String, String> arguments) {
        if (!super.execute(sender, arguments)) {
            return false;
        }

        if (!arguments.containsKey(CommandArgs.ID)) {
            sender.sendMessage("Survival Game ID is not present.");
            return false;
        }
        String id = arguments.get(CommandArgs.ID);

        if (BukkitSurvivalGamesPlugin.survivalGameMap.containsKey(id)) {
            sender.sendMessage("Survival Game ID already exists.");
            return false;
        }

        BukkitSurvivalGamesPlugin.survivalGameMap.put(id, new SurvivalGame(id));
        sender.sendMessage("Survival Game \"" + id + "\" created.");

        return true;
    }
}
