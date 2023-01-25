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
 * Enumeration for the control-type.
 *
 * @author Thorsten Fischer
 * Date 25/01/2023.
 *
 * Text types - see https://learn.microsoft.com/en-us/windows/win32/winauto/uiauto-textattribute-ids
 */
public enum TextType {
    /**
     * No text type.
     */
    None(0),
    
    AfterParagraphSpacing(40042),
    
    AnimationStyle(40000),
    
    AnnotationObjects(40032),
    
    AnnotationTypes(40031),
    
    BackgroundColor(40001),
    
    BeforeParagraphSpacing(40041),
    
    BulletStyle(40002),
    
    CapStyle(40003),
    
    CaretBidiMode(40039),
    
    CaretPosition(40038),
    
    Culture(40004),
    
    FontName(40005),
    
    FontSize(40006),
    
    FontWeight(40007),
    
    ForegroundColor(40008),
    
    HorizontalTextAlignment(40009),
    
    IndentationFirstLine(40010),
    
    IndentationLeading(40011),
    
    IndentationTrailing(40012),
    
    IsActive(40036),
    
    IsHidden(40013),
    
    IsItalic(40014),
    
    IsReadOnly(40015),
    
    IsSubscript(40016),
    
    IsSuperscript(40017),
    
    LineSpacing(40040),
    
    Link(40035),
    
    MarginBottom(40018),
    
    MarginLeading(40019),
    
    MarginTop(40020),
    
    MarginTrailing(40021),
    
    OutlineStyles(40022),
    
    OverlineColor(40023),
    
    OverlineStyle(40024),
    
    SelectionActiveEnd(40037),
    
    StrikethroughColor(40025),
    
    StrikethroughStyle(40026),
    
    StyleId(40034),
    
    StyleName(40033),
    
    Tabs(40027),
    
    TextFlowDirections(40028),
    
    UnderlineColor(40029),
    
    UnderlineStyle(40030);

    /*

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
     * Constructor for the TextType.
     *
     * @param inValue The initial value.
     */
    TextType(final int inValue) {
        this.value = inValue;
    }

    /**
     * Gets the text type from the value.
     * @param textTypeValue The value
     * @return The control type
     */
	public static TextType fromValue(int textTypeValue) {
		for (TextType type: values()) {
			if (type.getValue() == textTypeValue) {
				return type;
			}
		}
		return None;
	}
	
}
