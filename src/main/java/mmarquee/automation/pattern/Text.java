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
package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationException;
import mmarquee.automation.Element;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.TextUnit;
import mmarquee.uiautomation.IUIAutomationElementArray;
import mmarquee.uiautomation.IUIAutomationTextPattern;
import mmarquee.uiautomation.IUIAutomationTextPatternConverter;
import mmarquee.uiautomation.IUIAutomationTextRange;
import mmarquee.uiautomation.IUIAutomationTextRangeArray;
import mmarquee.uiautomation.IUIAutomationTextRangeArrayConverter;
import mmarquee.uiautomation.IUIAutomationTextRangeConverter;

/**
 * Wrapper for the text pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class Text extends BasePattern {

    /**
     * Constructor for the text pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Text(final Element element) throws AutomationException {
    	super(element);

        this.IID = IUIAutomationTextPattern.IID;
        this.patternID = PatternID.Text;
        this.availabilityPropertyID = PropertyID.IsTextPatternAvailable;
    }

    /**
     * The raw pattern.
     */
    IUIAutomationTextPattern rawPattern;

    /**
     * Converts the pointer to the text pattern interface.
     *
     * @param pUnknownA The raw pointer
     * @return Error in automation library
     */
    IUIAutomationTextRange convertPointerToTextRangeInterface(final PointerByReference pUnknownA) {
        return IUIAutomationTextRangeConverter.pointerToInterface(pUnknownA);
    }

    /**
     * Converts the raw pointer to the array text range array interface.
     *
     * @param pUnknownA The raw pointer
     * @return The converted interface
     */
    IUIAutomationTextRangeArray convertPointerToTextRangeArrayInterface(final PointerByReference pUnknownA) {
        return IUIAutomationTextRangeArrayConverter.pointerToInterface(pUnknownA);
    }
    
    /**
     * Converts the raw pointer to the text pattern interface.
     *
     * @param pUnknownA The raw pointer
     * @return The converted interface
     */
    IUIAutomationTextPattern convertPointerToTextPatternInterface(final PointerByReference pUnknownA) {
    	return IUIAutomationTextPatternConverter.pointerToInterface(pUnknownA);
    }
    
    /**
     * Gets the pattern.
     *
     * @return The pattern
     * @throws AutomationException Error in automation library
     */
    private IUIAutomationTextPattern getPattern() throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToTextPatternInterface);
    }
    
    // API METHODS
    public IUIAutomationTextRange getDocumentTextRange() throws AutomationException {
        PointerByReference pbr = new PointerByReference();
        final int res = this.getPattern().getDocumentRange(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }
        
        Unknown unkConditionA = makeUnknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();
        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationTextRange.IID), pUnknownA);
        
        if (COMUtils.SUCCEEDED(resultA)) {
            return convertPointerToTextRangeInterface(pUnknownA);
        } else {
            throw new AutomationException(resultA.intValue());
        }
    }
    
    /**
     * Gets the selection.
     *
     * @return String of the selection
     * @throws AutomationException Something has gone wrong
     */
    public String getSelection() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().getSelection(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        Unknown unkConditionA = makeUnknown(pbr.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        String selectionResult = "";

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationTextRangeArray.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationTextRangeArray selection = convertPointerToTextRangeArrayInterface(pUnknownA);

            // OK, now what?
            IntByReference ibr = new IntByReference();
            final int res1 = selection.getLength(ibr);
            if (res1 != 0) {
                throw new AutomationException(res1);
            }

            int count = ibr.getValue();

            for (int i = 0; i < count; i++) {
                PointerByReference pbr0 = new PointerByReference();

                final int res2 = selection.getElement(i, pbr0);
                if (res2 != 0) {
                    throw new AutomationException(res2);
                }

                Unknown unknown = makeUnknown(pbr0.getValue());
                PointerByReference pUnknown = new PointerByReference();
                WinNT.HRESULT result = unknown.QueryInterface(new Guid.REFIID(IUIAutomationTextRange.IID), pUnknown);
                
                if (COMUtils.SUCCEEDED(result)) {
                    IUIAutomationTextRange range = convertPointerToTextRangeInterface(pUnknown);

                    PointerByReference sr = new PointerByReference();
                    final int res3 = range.getText(-1, sr);
                    if (res3 != 0) {
                        throw new AutomationException(res3);
                    }

                    selectionResult += sr.getValue().getWideString(0);
                } else {
                    throw new AutomationException(result.intValue());
                }
            }
        } else {
            throw new AutomationException(resultA.intValue());
        }

        return selectionResult;
    }

    /**
     * Gets the text from the pattern.
     *
     * @return The text.
     * @throws AutomationException Something has gone wrong
     */
    public String getDocumentText() throws AutomationException {
        return getText(getDocumentTextRange());
    }
    
    public String getText(IUIAutomationTextRange textRange) throws AutomationException {
        PointerByReference elementResult = new PointerByReference();
        final int res1 = textRange.getText(-1, elementResult);
        if (res1 != 0) {
            throw new AutomationException(res1);
        }

        return elementResult.getValue().getWideString(0);
    }
    
    public int move(IUIAutomationTextRange textRange, TextUnit textUnit, int count) throws AutomationException {
        IntByReference intResult = new IntByReference();
        final int res1 = textRange.move(textUnit, count, intResult);
        if (res1 != 0) {
            throw new AutomationException(res1);
        }
        
        return intResult.getValue();
    }
    
    public void select(IUIAutomationTextRange textRange) throws AutomationException {
        final int res1 = textRange.select();
        if (res1 != 0) {
            throw new AutomationException(res1);
        }
    }
    
    public void expandToEnclosingUnit(IUIAutomationTextRange textRange, TextUnit textUnit) throws AutomationException {
        final int res1 = textRange.expandToEnclosingUnit(textUnit);
        if (res1 != 0) {
            throw new AutomationException(res1);
        }
    }

    public Element getEnclosingElement(IUIAutomationTextRange textRange) throws AutomationException {
        PointerByReference elementResult = new PointerByReference();
        final int res1 = textRange.getEnclosingElement(elementResult);
        if (res1 != 0) {
            throw new AutomationException(res1);
        }
        
        return new Element(getAutomationElementFromReference(elementResult));
    }
    
    public IUIAutomationElementArray getChildren(IUIAutomationTextRange textRange) throws AutomationException {
        PointerByReference elementResult = new PointerByReference();
        final int res1 = textRange.getChildren(elementResult);
        if (res1 != 0) {
            throw new AutomationException(res1);
        }
        
        return getAutomationElementArrayFromReference(elementResult);
    }

}
