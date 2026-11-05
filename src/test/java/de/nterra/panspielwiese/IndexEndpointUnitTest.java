package de.nterra.panspielwiese;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Plain unit test for {@link PanSpielwieseApplication#index()}, without
 * bootstrapping the Spring context.
 */
class IndexEndpointUnitTest {

    private final PanSpielwieseApplication application = new PanSpielwieseApplication();

    @Test
    void returnsExpectedStaticFields() {
        Map<String, Object> result = application.index();

        assertThat(result)
            .containsEntry("status", "UP")
            .containsEntry("application", "pan-spielwiese")
            .containsEntry("message", "Hello from STACKIT & Spring Boot!");
    }

    @Test
    void returnsParsableTimestamp() {
        Map<String, Object> result = application.index();

        Object timestamp = result.get("timestamp");
        assertThat(timestamp).isInstanceOf(String.class);
        assertThatCode(() -> Instant.parse((String) timestamp))
            .doesNotThrowAnyException();
    }

    @Test
    void timestampReflectsCallTime() {
        Instant before = Instant.now();
        Map<String, Object> result = application.index();
        Instant after = Instant.now();

        Instant timestamp;
        try {
            timestamp = Instant.parse((String) result.get("timestamp"));
        } catch (DateTimeParseException e) {
            throw new AssertionError("timestamp is not a valid ISO-8601 instant", e);
        }

        assertThat(timestamp).isBetween(before, after);
    }
}
