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
package mmarquee.uiautomation;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.TextAttributeID;
import mmarquee.automation.TextPatternRangeEndpoint;
import mmarquee.automation.TextUnit;

/**
 * @author Mark Humphreys
 * Date 13/07/2016.
 */
public interface IUIAutomationTextRange extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{A543CC6A-F4AE-494B-8239-C814481187A8}");

    int cloneText(PointerByReference clonedRange);
    int compare(PointerByReference range, IntByReference areSame);
    int compareEndpoints(TextPatternRangeEndpoint srcEndPoint, PointerByReference range, TextPatternRangeEndpoint targetEndPoint, IntByReference compValue);
    int expandToEnclosingUnit(TextUnit textUnit);
    int findAttribute(int attr, int val, boolean backward, PointerByReference found);
    int findText(String text, boolean backward, boolean ignoreCase, PointerByReference found);
    int getAttributeValue(TextAttributeID attr, PointerByReference value);
    int getBoundingRectangles(PointerByReference boundingRects);
    int getEnclosingElement(PointerByReference enclosingElement);
    int getText(Integer maxLength, PointerByReference text);
    int move(TextUnit textUnit, Integer count, IntByReference moved);
    int moveEndpointByUnit(TextPatternRangeEndpoint endpoint, TextUnit textUnit, int count, PointerByReference moved);
    int moveEndpointByRange(TextPatternRangeEndpoint srcEndPoint, PointerByReference range, TextPatternRangeEndpoint targetEndpoint);
    int select();
    int addToSelection();
    int removeFromSelection();
    int scrollIntoView(boolean alignToTop);
    int getChildren(PointerByReference children);
}