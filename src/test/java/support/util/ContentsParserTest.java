package support.util;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


public class ContentsParserTest {

    @Test
    public void parseReference() {
        String s = "@할 일@ @@@1 @333@4@65";
        assertThat(ContentsParser.parseReference(s).get(0)).isEqualTo(1);
        assertThat(ContentsParser.parseReference(s).get(1)).isEqualTo(333);
        assertThat(ContentsParser.parseReference(s).get(2)).isEqualTo(4);
        assertThat(ContentsParser.parseReference(s).get(3)).isEqualTo(65);
    }
}