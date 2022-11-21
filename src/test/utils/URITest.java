package test.utils;

import main.utils.URI;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class URITest {
    @Test
    void validArguments() {
        Assertions.assertThatCode(() -> new URI("photos", "google", new String[]{"com", "br"}))
                .doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new URI("photos", "google", "com"))
                .doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new URI("google", new String[]{"com", "br"}))
                .doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new URI("google", "com"))
                .doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new URI("photos.google.com.br"))
                .doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new URI("photos.google.com"))
                .doesNotThrowAnyException();
        Assertions.assertThatCode(() -> new URI("google.com"))
                .doesNotThrowAnyException();
    }

    @Test
    void invalidArguments() {
        Assertions.assertThatThrownBy(() -> new URI(null, "google", new String[]{"com", ""}))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("", "google", new String[]{"com", ""}))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("photos", null, new String[]{"com", ""}))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("photos", "", new String[]{"com", ""}))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(
                () -> new URI("photos", "google", new String[]{"com", "net", "br"}))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(
                () -> new URI("photos", "google", new String[]{"com", null}))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("photos", "google", new String[]{"com", ""}))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("photos", "google", new String[]{}))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("photos", "google", (String[]) null))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("photos", "google", (String) null))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("photos", "google", ""))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI(null))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI(""))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new URI("www.photos.google.com.br"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void URItoString() {
        Assertions.assertThat(new URI("www", "google", "com").toString())
                .isEqualTo("www.google.com");
    }
}
