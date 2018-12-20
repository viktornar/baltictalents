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
package lt.viktornar.currdiff.config;

import lt.viktornar.currdiff.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.text.SimpleDateFormat;

/**
 * Used for spring based application configuration.
 *
 * @author v.nareiko
 */
@Configuration
@ComponentScan("lt.viktornar")
@PropertySources({
        @PropertySource("classpath:application.properties")
})
public class AppConfig {
    @Autowired
    private Environment env;

    /**
     * Helper bean to access some application configuration.
     *
     * @return The settings service.
     */
    @Bean
    public SettingsService getSettingsService() {
        SettingsService settingsService = new SettingsService();
        settingsService.setServiceUrl(env.getProperty("service.url"));
        return settingsService;
    }

    /**
     * Helper bean to access simple date format with custom date format.
     *
     * @return The settings service.
     */
    @Bean
    @Qualifier(value = "customSimpleDateFormat")
    public SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat format = new SimpleDateFormat(env.getProperty("date.format"));
        return  format;
    }
}
