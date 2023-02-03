/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.automation.controls;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.Text;
import mmarquee.uiautomation.IUIAutomation;

/**
 * @author Mark Humphreys
 * Date 30/11/2016.
 */
public class DocumentTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetName_Gets_Name_From_Element() throws Exception {
        Element element = Mockito.mock(Element.class);
        Text pattern = Mockito.mock(Text.class);

        when(pattern.isAvailable()).thenReturn(true);
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        Document document = new Document(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        String name = document.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetSelection_From_Element() throws Exception {
        Element element = Mockito.mock(Element.class);
        Text pattern = Mockito.mock(Text.class);
        when(pattern.isAvailable()).thenReturn(true);

        when(pattern.getSelection()).thenReturn("SELECTION");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        Document document = new Document(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        String name = document.getSelection();

        assertTrue(name.equals("SELECTION"));
    }

    @Test
    public void testGetText_From_Element() throws Exception {
        Element element = Mockito.mock(Element.class);
        Text pattern = Mockito.mock(Text.class);

        when(pattern.isAvailable()).thenReturn(true);
        when(pattern.getDocumentText()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        Document document = new Document(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        String name = document.getText();

        assertTrue(name.equals("NAME"));
    }
}
