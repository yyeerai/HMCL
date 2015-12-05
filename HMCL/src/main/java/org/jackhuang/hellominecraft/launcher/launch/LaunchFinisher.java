/*
 * Hello Minecraft! Launcher.
 * Copyright (C) 2013  huangyuhui
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see {http://www.gnu.org/licenses/}.
 */
package org.jackhuang.hellominecraft.launcher.launch;

import java.util.List;
import org.jackhuang.hellominecraft.launcher.settings.LauncherVisibility;
import org.jackhuang.hellominecraft.launcher.views.MainFrame;
import org.jackhuang.hellominecraft.utils.Event;
import org.jackhuang.hellominecraft.utils.system.JavaProcessMonitor;
import org.jackhuang.hellominecraft.views.LogWindow;

/**
 *
 * @author huangyuhui
 */
public class LaunchFinisher implements Event<List<String>> {

    @Override
    public boolean call(Object sender, List<String> str) {
        final GameLauncher obj = (GameLauncher) sender;
        obj.launchEvent.register((sender1, p) -> {
            if (obj.getProfile().getLauncherVisibility() == LauncherVisibility.CLOSE && !LogWindow.instance.isVisible())
                System.exit(0);
            else if (obj.getProfile().getLauncherVisibility() == LauncherVisibility.KEEP)
                MainFrame.INSTANCE.closeMessage();
            else {
                if (LogWindow.instance.isVisible())
                    LogWindow.instance.setExit(() -> true);
                MainFrame.INSTANCE.dispose();
            }
            JavaProcessMonitor jpm = new JavaProcessMonitor(p);
            jpm.stoppedEvent.register((sender3, t) -> {
                if (obj.getProfile().getLauncherVisibility() != LauncherVisibility.KEEP && !LogWindow.instance.isVisible())
                    System.exit(0);
                return true;
            });
            jpm.start();
            return true;
        });
        obj.launch(str);
        return true;
    }
}
