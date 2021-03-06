/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.artipie.rpm;

import com.jcabi.matchers.XhtmlMatchers;
import java.nio.file.Files;
import java.nio.file.Path;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.xembly.Directives;

/**
 * Test case for {@link Update}.
 *
 * @since 0.1
 */
public final class UpdateTest {

    /**
     * Temp folder for all tests.
     */
    @Rule
    @SuppressWarnings("PMD.BeanMembersShouldSerialize")
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * Fake storage works.
     * @throws Exception If some problem inside
     */
    @Test
    public void makesUpdateToXmlFile() throws Exception {
        final Path xml = this.folder.newFile("a.xml").toPath();
        new Update(xml).apply(new Directives().add("test").add("foo")).blockingAwait();
        MatcherAssert.assertThat(
            new String(Files.readAllBytes(xml)),
            XhtmlMatchers.hasXPath("/test/foo")
        );
    }

}
