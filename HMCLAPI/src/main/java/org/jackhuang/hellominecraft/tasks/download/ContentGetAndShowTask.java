/*
 * Hello Minecraft! Launcher.
 * Copyright (C) 2013  huangyuhui <huanghongxun2008@126.com>
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
package org.jackhuang.hellominecraft.tasks.download;

import org.jackhuang.hellominecraft.utils.Event;
import org.jackhuang.hellominecraft.views.LogWindow;

/**
 *
 * @author huangyuhui
 */
public class ContentGetAndShowTask extends HTTPGetTask implements Event<String> {

    public ContentGetAndShowTask(String info, String changeLogUrl) {
        super(changeLogUrl);
        this.info = info;
    }

    @Override
    public void executeTask() throws Exception {
        tdtsl.register(this);
        super.executeTask();
    }

    String info;

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public boolean call(Object sender, String value) {
        LogWindow.instance.clean();
        LogWindow.instance.log(value);
        LogWindow.instance.setVisible(true);
        return true;
    }
}
