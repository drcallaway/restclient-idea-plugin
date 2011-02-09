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
}
