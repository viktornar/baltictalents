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
package lt.viktornar.currdiff;

import lt.viktornar.currdiff.server.Starter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Used for creating and running embedded tomcat server.
 *
 * @author v.nareiko
 */
public class Main {

    /**
     * Main method.
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        int port = 8080;

        String PORT = System.getenv("PORT");
        if (PORT == null || PORT.isEmpty()) {
            PORT = "8080";
        }

        try {
            port = Integer.parseInt(PORT);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Running server with default port [8080]");
        }

        final Starter starter = new Starter(port);
        starter.start();
        System.in.read();
        starter.stop();
    }
}