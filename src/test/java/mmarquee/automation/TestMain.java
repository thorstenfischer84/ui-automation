/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.uiautomation.ToggleState;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by inpwt on 26/02/2016.
 */
public class TestMain {

    public void run () {
        UIAutomation automation = new UIAutomation();

        Logger logger = Logger.getLogger(AutomationBase.class.getName());

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            // Smother????
            logger.warn("Failed to find application", ex);
        }

        // Wait for the process to start
        application.waitForInputIdle(5000);

        AutomationWindow window = automation.getDesktopWindow("Form1");
        String name = window.name();
        logger.info(name);

        boolean val = window.isModal();

        java.lang.Object rect = window.getBoundingRectangle();

        // Interact with menus
        AutomationMainMenu menu = window.getMainMenu();
//		List<AutomationMenuItem> items = menu.getItems();

//		AutomationMenuItem item = items.get(0);
//		item.expand();

//		String name1 = item.name();

        AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
        exit.click();

        AutomationWindow popup = window.getWindow("Project1");
        Object val111 = popup.getBoundingRectangle();

        AutomationButton btn = popup.getButton("OK");
        Object val11 = btn.getBoundingRectangle();

        boolean val1 = popup.isModal();

        btn.click();

        AutomationTab tab = window.getTab(0);
        tab.selectTabPage("Last Tab");
        //	String tabName = tab.name();

        String text = tab.getEditBox(0).getValue();
        logger.info("Text for editBox1 is " + text);

        AutomationCheckbox check = window.getCheckbox(0);
        check.toggle();

        ToggleState state = check.getToggleState();

        AutomationRadioButton radio = window.getRadioButton(1);
        radio.selectItem();

        AutomationStatusBar statusbar = window.getStatusBar();

       // AutomationTextBox tb0 = statusbar.getTextBox(0);
        AutomationTextBox tb1 = statusbar.getTextBox(1);

       // String eb0Text = statusbar.getTextBox(0).getValue();
        String eb1Text = tb1.getValue();

        logger.info("Statusbar text = " + eb1Text);

        AutomationComboBox cb1 = window.getCombobox("AutomatedCombobox1");
        cb1.setText("Replacements");
        String txt = cb1.text();

        cb1.getClickablePoint();

        logger.info("Text for AutomatedCombobox1 is " + txt);

        AutomationComboBox cb0 = window.getCombobox("AutomatedCombobox2");
        cb0.expand();
        try {
            cb0.wait(750);
        } catch (Exception ex) {
            // Time out
        }
        List<AutomationListItem> litems = cb0.getList();

        // Now string grids
        AutomationStringGrid grid = window.getStringGrid(0);

        AutomationStringGridItem item1 = grid.getItem(0, 0);

        String itemName = item1.name();
        logger.info(itemName);
        item1.setName("This");
        logger.info(item1.name());

        AutomationTreeView tree = window.getTreeView(0);
        try {
            AutomationTreeViewItem treeItem = tree.getItem("Sub-SubItem");
            treeItem.select();
        } catch (ItemNotFoundException ex) {
            // Not found
        }

        AutomationList list = window.getListItem(0);
        try {
            AutomationListItem listItem = list.getItem("First (List)");
            listItem.select();
        } catch (ItemNotFoundException ex) {
            // Not found
        }

        AutomationHyperlink link = window.getHyperlink(0);
        link.click();
        AutomationWindow popup1 = window.getWindow("Project1");
        AutomationButton btn1 = popup1.getButton("OK");
        btn1.click();
    }
}
