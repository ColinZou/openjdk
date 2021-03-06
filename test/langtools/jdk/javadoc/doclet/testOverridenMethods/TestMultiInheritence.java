/*
 * Copyright (c) 2003, 2016, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug      4933335
 * @summary  Make sure that all inherited methods from multiple extended
 *           interfaces are documented
 * @author   jamieh
 * @library  ../lib
 * @modules jdk.javadoc/jdk.javadoc.internal.tool
 * @build    JavadocTester
 * @run main TestMultiInheritence
 */

// TODO: should be TestMultiInheritance
public class TestMultiInheritence extends JavadocTester {

    public static void main(String... args) throws Exception {
        TestMultiInheritence tester = new TestMultiInheritence();
        tester.runTests();
    }

    @Test
    void test() {
        javadoc("-d", "out",
                "-sourcepath", testSrc,
                "pkg3");
        checkExit(Exit.OK);

        // Method foo() is inherited from BOTH I2 and I3

        checkOutput("pkg3/I1.html", true,
                "Methods inherited from interface&nbsp;pkg3."
                + "<a href=\"../pkg3/I2.html\" title=\"interface in pkg3\">"
                + "I2</a>",
                "Methods inherited from interface&nbsp;pkg3."
                + "<a href=\"../pkg3/I3.html\" title=\"interface in pkg3\">"
                + "I3</a>");

        checkOutput("pkg3/I0.html", true,
                "Methods inherited from interface&nbsp;pkg3."
                + "<a href=\"../pkg3/I2.html\" title=\"interface in pkg3\">"
                + "I2</a>",
                "Methods inherited from interface&nbsp;pkg3."
                + "<a href=\"../pkg3/I3.html\" title=\"interface in pkg3\">"
                + "I3</a>");

        // Method foo() is NOT inherited from I4 because it is overriden by I3.

        checkOutput("pkg3/I1.html", false,
                "Methods inherited from interface&nbsp;pkg3."
                + "<a href=\"../pkg3/I4.html\" title=\"interface in pkg3\">"
                + "I4</a>");

        checkOutput("pkg3/I0.html", false,
                "Methods inherited from interface&nbsp;pkg3."
                + "<a href=\"../pkg3/I4.html\" title=\"interface in pkg3\">"
                + "I4</a>");
    }
}
