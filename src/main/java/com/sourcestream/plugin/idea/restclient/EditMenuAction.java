/**
 * The MIT License
 *
 * Copyright (c) 2011 by Dustin R. Callaway
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sourcestream.plugin.idea.restclient;

import com.intellij.openapi.actionSystem.DataKeys;
import org.wiztools.restclient.ui.IdeaPlugin;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Handles the edit menu action by displaying a popup menu of edit choices and processing the user's selection.
 */
public class EditMenuAction extends BaseMenuAction
{
    private static final int VERTICAL_LOCATION = 25;

    /**
     * Constructs a new EditMenuAction object.
     */
    public EditMenuAction()
    {
        popup = new JPopupMenu();

        JMenuItem item = new JMenuItem("Clear Response");
        item.setActionCommand(IdeaPlugin.ACTION_CLEAR_RESPONSE);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Reset All");
        item.setActionCommand(IdeaPlugin.ACTION_RESET_ALL);
        item.addActionListener(this);
        popup.add(item);

        popup.addSeparator();

        item = new JMenuItem("Reset to Last Request-Response");
        item.setActionCommand(IdeaPlugin.ACTION_RESET_TO_LAST);
        item.addActionListener(this);
        popup.add(item);
    }

    /**
     * Invoked when the user clicks on a popup menu item.
     *
     * @param event Popup menu click event
     */
    public void actionPerformed(ActionEvent event)
    {
        IdeaPlugin plugin = IdeaPlugin.getInstance(ideaEvent.getData(DataKeys.PROJECT));

        if (event.getActionCommand().equals(IdeaPlugin.ACTION_CLEAR_RESPONSE))
        {
            plugin.clearResponse();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_RESET_ALL))
        {
            plugin.resetAll();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_RESET_TO_LAST))
        {
            plugin.resetToLast();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getVerticalLocation()
    {
        return VERTICAL_LOCATION;
    }
}
