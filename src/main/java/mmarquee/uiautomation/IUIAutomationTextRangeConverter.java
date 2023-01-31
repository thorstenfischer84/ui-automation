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

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.TextAttributeID;
import mmarquee.automation.TextPatternRangeEndpoint;
import mmarquee.automation.TextUnit;

/**
 * @author Mark Humphreys Date 05/06/2017.
 */
public class IUIAutomationTextRangeConverter {
	private static int METHODS = 21; // 0-2 IUnknown, 3-20 IUIAutomationTextRange

	public static IUIAutomationTextRange pointerToInterface(final PointerByReference ptr) {
		final Pointer interfacePointer = ptr.getValue();
		final Pointer vTablePointer = interfacePointer.getPointer(0);
		final Pointer[] vTable = new Pointer[METHODS];
		vTablePointer.read(0, vTable, 0, vTable.length);
		return new IUIAutomationTextRange() {
			// IUnknown
			@Override
			public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pbr) {
				Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
				int res = f.invokeInt(new Object[] { interfacePointer, byValue, pbr });
				return new WinNT.HRESULT(res);
			}

			@Override
			public int AddRef() {
				Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer });
			}

			@Override
			public int Release() {
				Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer });
			}

			// IUIAutomationTextRange
			@Override
			public int cloneText(PointerByReference clonedRange) {
				Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, clonedRange });
			}

			@Override
			public int compare(PointerByReference range, IntByReference areSame) {
				Function f = Function.getFunction(vTable[4], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, range, areSame });
			}

			@Override
			public int compareEndpoints(TextPatternRangeEndpoint srcEndPoint, PointerByReference range, TextPatternRangeEndpoint targetEndPoint, IntByReference compValue) {
				Function f = Function.getFunction(vTable[5], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, srcEndPoint.getValue(), range, targetEndPoint.getValue(), compValue });
			}

			@Override
			public int expandToEnclosingUnit(TextUnit textUnit) {
				Function f = Function.getFunction(vTable[6], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, textUnit.getValue() });
			}

			@Override
			public int findAttribute(int attr, int val, boolean backward, PointerByReference found) {
				Function f = Function.getFunction(vTable[7], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, attr, val, backward });
			}

			@Override
			public int findText(String text, boolean backward, boolean ignoreCase, PointerByReference found) {
				Function f = Function.getFunction(vTable[8], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, text, backward, ignoreCase, found});
			}

			@Override
			public int getAttributeValue(TextAttributeID attr, PointerByReference value) {
				Function f = Function.getFunction(vTable[9], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, attr.getValue(), value });
			}

			@Override
			public int getBoundingRectangles(PointerByReference boundingRects) {
				Function f = Function.getFunction(vTable[10], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, boundingRects });
			}

			@Override
			public int getEnclosingElement(PointerByReference enclosingElement) {
				Function f = Function.getFunction(vTable[11], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, enclosingElement });
			}

			@Override
			public int getText(Integer maxLength, PointerByReference text) {
				Function f = Function.getFunction(vTable[12], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, maxLength, text });
			}

			@Override
			public int move(TextUnit textUnit, Integer count, IntByReference moved) {
				Function f = Function.getFunction(vTable[13], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, textUnit.getValue(), count, moved });
			}

			@Override
			public int moveEndpointByUnit(TextPatternRangeEndpoint endpoint, TextUnit textUnit, int count, PointerByReference moved) {
				Function f = Function.getFunction(vTable[14], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, endpoint, textUnit.getValue(), count, moved });
			}

			@Override
			public int moveEndpointByRange(TextPatternRangeEndpoint srcEndPoint, PointerByReference range, TextPatternRangeEndpoint targetEndPoint) {
				Function f = Function.getFunction(vTable[15], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, srcEndPoint.getValue(), range, targetEndPoint.getValue() });
			}

			@Override
			public int select() {
				Function f = Function.getFunction(vTable[16], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer });
			}

			@Override
			public int addToSelection() {
				Function f = Function.getFunction(vTable[17], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer });
			}

			@Override
			public int removeFromSelection() {
				Function f = Function.getFunction(vTable[18], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer });
			}

			@Override
			public int scrollIntoView(boolean alignToTop) {
				Function f = Function.getFunction(vTable[19], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, alignToTop });
			}

			@Override
			public int getChildren(PointerByReference children) {
				Function f = Function.getFunction(vTable[20], Function.ALT_CONVENTION);
				return f.invokeInt(new Object[] { interfacePointer, children });
			}
		};
	}
}
