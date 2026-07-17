package dev.patika.librarymanagementapi;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigurationContractTests {

    @Test
    void productionDatabaseConfigurationIsExternalAndNonDestructiveByDefault() throws Exception {
        String configuration = Files.readString(Path.of("src/main/resources/application.yml"));

        assertTrue(configuration.contains("url: ${JDBC_URL}"));
        assertTrue(configuration.contains("username: ${DB_USER}"));
        assertTrue(configuration.contains("password: ${DB_PASSWORD}"));
        assertTrue(configuration.contains("ddl-auto: ${JPA_DDL_AUTO:validate}"));
        assertTrue(configuration.contains("mode: ${SQL_INIT_MODE:never}"));
        assertFalse(configuration.contains("password: postgres"));
    }
}
