package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tdd.template.SimpleGenerator;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {
    private final SimpleGenerator generator = new SimpleGenerator();

    @Ignore
    @Test
    public void whenProduce() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you");
        String rsl = generator.produce(template, map);
        String expected = "I am a Petr Arsentev, Who are you?";
        assertThat(rsl, is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapHasNoKey() {
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you");
        String template = "I am a ${profession}, Who are ${subject}?";
        String rsl = generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapHasRedundantKey() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you", "profession", "doctor");
        String rsl = generator.produce(template, map);
    }
}
