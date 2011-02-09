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
 * Handles the file menu action by displaying a popup menu of file choices and processing the user's selection.
 */
public class FileMenuAction extends BaseMenuAction
{
    /**
     * Constructs a new FileMenuAction object.
     */
    public FileMenuAction()
    {
        popup = new JPopupMenu();

        JMenuItem item = new JMenuItem("Open Request");
        item.setActionCommand(IdeaPlugin.METHOD_OPEN_REQUEST);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Open Response");
        item.setActionCommand(IdeaPlugin.METHOD_OPEN_RESPONSE);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Open Req-Res Archive");
        item.setActionCommand(IdeaPlugin.METHOD_OPEN_ARCHIVE);
        item.addActionListener(this);
        popup.add(item);

        popup.addSeparator();

        item = new JMenuItem("Save Request");
        item.setActionCommand(IdeaPlugin.ACTION_SAVE_REQUEST);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Save Response");
        item.setActionCommand(IdeaPlugin.ACTION_SAVE_RESPONSE);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Save Response Body");
        item.setActionCommand(IdeaPlugin.ACTION_SAVE_RESPONSE_BODY);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Save Req-Res Archive");
        item.setActionCommand(IdeaPlugin.ACTION_SAVE_REQ_RES_ARCHIVE);
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

        if (event.getActionCommand().equals(IdeaPlugin.METHOD_OPEN_REQUEST))
        {
            plugin.openRequest();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.METHOD_OPEN_RESPONSE))
        {
            plugin.openResponse();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.METHOD_OPEN_ARCHIVE))
        {
            plugin.openArchive();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_SAVE_REQUEST))
        {
            plugin.saveRequest();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_SAVE_RESPONSE))
        {
            plugin.saveResponse();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_SAVE_RESPONSE_BODY))
        {
            plugin.saveResponseBody();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_SAVE_REQ_RES_ARCHIVE))
        {
            plugin.saveReqResArchive();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getVerticalLocation()
    {
        return 0;
    }
}
