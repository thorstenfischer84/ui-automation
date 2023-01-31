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
 * Enumeration for text pattern range endpoints.
 *
 * @author Thorsten Fischer
 * Date 31/01/2023.
 *
 * Text units - see https://learn.microsoft.com/de-de/dotnet/api/system.windows.automation.text.textpatternrangeendpoint
 */
public enum TextPatternRangeEndpoint {
	
	Start(0),
    
	End(1);

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
     * Constructor for the TextPatternRangeEndpoint.
     *
     * @param inValue The initial value.
     */
    TextPatternRangeEndpoint(final int inValue) {
        this.value = inValue;
    }

    /**
     * Gets the text pattern range endpoint from the value.
     * @param endpointValue The value
     * @return The text unit
     */
	public static TextPatternRangeEndpoint fromValue(int endpointValue) {
		for (TextPatternRangeEndpoint endpoint: values()) {
			if (endpoint.getValue() == endpointValue) {
				return endpoint;
			}
		}
		return null;
	}
	
}
