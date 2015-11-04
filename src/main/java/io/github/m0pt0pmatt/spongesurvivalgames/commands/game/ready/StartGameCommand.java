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

package io.github.m0pt0pmatt.spongesurvivalgames.commands.game.ready;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import io.github.m0pt0pmatt.spongesurvivalgames.BukkitSurvivalGamesPlugin;
import io.github.m0pt0pmatt.spongesurvivalgames.exceptions.NoChestMidpointException;
import io.github.m0pt0pmatt.spongesurvivalgames.exceptions.NoChestRangeException;
import io.github.m0pt0pmatt.spongesurvivalgames.exceptions.NoExitLocationException;
import io.github.m0pt0pmatt.spongesurvivalgames.exceptions.NoWorldException;
import io.github.m0pt0pmatt.spongesurvivalgames.exceptions.NotEnoughSpawnPointsException;
import io.github.m0pt0pmatt.spongesurvivalgames.exceptions.TaskException;
import io.github.m0pt0pmatt.spongesurvivalgames.exceptions.WorldNotSetException;

/**
 * Command to set a game to the RUNNING state (start the game)
 */
public class StartGameCommand extends ReadyCommand {

    public StartGameCommand(Map<String, String> arguments){
        super(arguments);
    }

    @Override
    public boolean execute(CommandSender sender){

        if (!super.execute(sender)) {
            return false;
        }

        try {
            BukkitSurvivalGamesPlugin.survivalGameMap.get(id).start();
        } catch (NotEnoughSpawnPointsException e) {
            Bukkit.getLogger().warning("Survival Game \"" + id + "\" does not have enough spawn points.");
            return false;
        } catch (NoExitLocationException e) {
            Bukkit.getLogger().warning("Survival Game \"" + id + "\" does not have an exit location.");
            return false;
        } catch (WorldNotSetException e) {
            Bukkit.getLogger().warning("Survival Game \"" + id + "\" does not have a world assigned to it.");
            return false;
        } catch (NoWorldException e) {
            Bukkit.getLogger().warning("Survival Game \"" + id + "\"'s world does not exist.");
            return false;
        } catch (NoChestRangeException e) {
            Bukkit.getLogger().warning("Survival Game \"" + id + "\" does not have a chest range assigned to it");
            return false;
        } catch (NoChestMidpointException e) {
            Bukkit.getLogger().warning("Survival Game \"" + id + "\" does not have a chest midpoint assigned to it.");
            return false;
        } catch (TaskException e) {
            Bukkit.getLogger().warning(e.getMessage());
            return false;
        }

        Bukkit.getLogger().info("Survival Game \"" + id + "\" is now RUNNING.");
        return true;
    }
}
