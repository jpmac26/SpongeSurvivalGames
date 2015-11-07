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

package io.github.m0pt0pmatt.spongesurvivalgames;

import io.github.m0pt0pmatt.spongesurvivalgames.commands.SurvivalGamesCommand;
import io.github.m0pt0pmatt.spongesurvivalgames.util.LoadedTrieReturn;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

/**
 * Command Executor for the plugin
 */
public class SurvivalGamesCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].toLowerCase();
        }

        LoadedTrieReturn<String, SurvivalGamesCommand> trieReturn = BukkitSurvivalGamesPlugin.commandTrie.match(strings);

        if (trieReturn.data == null) {
            Bukkit.getLogger().warning("No command found");
            return false;
        }

        if (!trieReturn.data.execute(commandSender, trieReturn.leftoverMap)) {
            StringBuilder builder = new StringBuilder();
            builder.append("Usage: /ssg");
            trieReturn.matched.forEach(string -> builder.append(" ").append(string));
            for (String string : trieReturn.leftovers) builder.append(" ").append(string);
            commandSender.sendMessage(builder.toString());
        }

        return true;
    }
}