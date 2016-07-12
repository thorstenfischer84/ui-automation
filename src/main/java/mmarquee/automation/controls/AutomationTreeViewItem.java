package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;

/**
 * Created by inpwt on 20/02/2016.
 *
 * Wrapper for the TreeViewItem element.
 */
public class AutomationTreeViewItem extends AutomationBase {

    private SelectionItem selectItemPattern;
    private Invoke invokePattern;

    /**
     * Construct the AutomationTreeViewItem
     * @param element The element
     */
    public AutomationTreeViewItem(AutomationElement element) {
        super(element);

        try {
            this.selectItemPattern = this.getSelectItemPattern();
        } catch (PatternNotFoundException ex) {
            logger.info("Failed to find selectitem pattern");
        }

        try {
            this.invokePattern = this.getInvokePattern();
        } catch (PatternNotFoundException ex) {
            logger.info("Failed to find invoke pattern");
        }
    }

    /**
     * Select the item
     */
    public void select() {
        this.selectItemPattern.select();
    }

    /**
     * Click the item
     */
    public void click() {
        if (this.isInvokePatternAvailable()) {
            this.invokePattern.invoke();
        } else {
            logger.info("Invoke pattern is not available");
        }
    }
}
