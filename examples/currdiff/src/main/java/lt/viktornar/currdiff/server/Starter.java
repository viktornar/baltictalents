/*
 This file is part of CurrDiff.
 CurrDiff is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 Subsonic is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU General Public License
 along with Subsonic.  If not, see <http://www.gnu.org/licenses/>.
 Copyright 2016 (C) Viktor Nareiko
 */
package lt.viktornar.currdiff.server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Used for creating embedded tomcat server.
 *
 * @author v.nareiko
 */
public class Starter {
    public static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "";
    private static final String WEB_APPDIR_LOCATION = "src/main/webapp";
    private int port;
    private Tomcat server;

    public Starter() {
        this(DEFAULT_PORT);
    }

    public Starter(int port) {
        server = new Tomcat();
        this.port = port;
    }

    public String getBaseUri() {
        return String.format("http://localhost:%d/%s", this.port, this.CONTEXT_PATH);
    }

    public void start() {
        server.setPort(this.port);
        try {
            server.addWebapp(this.CONTEXT_PATH, new File(WEB_APPDIR_LOCATION).getAbsolutePath());
            System.out.println("configuring app with basedir: " + new File("./" + WEB_APPDIR_LOCATION).getAbsolutePath());
            server.start();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
