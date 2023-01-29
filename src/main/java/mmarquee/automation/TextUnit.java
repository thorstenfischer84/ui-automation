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
package mmarquee.automation;

/**
 * Enumeration for text units.
 *
 * @author Thorsten Fischer
 * Date 25/01/2023.
 *
 * Text units - see https://learn.microsoft.com/en-us/windows/win32/api/uiautomationcore/ne-uiautomationcore-textunit
 */
public enum TextUnit {
	
	Character(0),
    
	Format(1),
    
	Word(2),
    
	Line(3),
    
	Paragraph(4),
    
	Page(5),
    
	Document(6);

    /**
     * The actual value.
     */
    private int value;

    /**
     * Gets the value.
     * @return The actual value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for the TextUnit.
     *
     * @param inValue The initial value.
     */
    TextUnit(final int inValue) {
        this.value = inValue;
    }

    /**
     * Gets the text attribute from the value.
     * @param textUnitValue The value
     * @return The text unit
     */
	public static TextUnit fromValue(int textUnitValue) {
		for (TextUnit textUnit: values()) {
			if (textUnit.getValue() == textUnitValue) {
				return textUnit;
			}
		}
		return null;
	}
	
}
