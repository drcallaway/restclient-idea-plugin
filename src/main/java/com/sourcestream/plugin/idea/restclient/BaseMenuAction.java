package com.sourcestream.plugin.idea.restclient;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import org.wiztools.restclient.ui.IdeaPlugin;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Base menu class.
 */
public abstract class BaseMenuAction extends AnAction implements ActionListener
{
    protected JPopupMenu popup;
    protected AnActionEvent ideaEvent;

    /**
     * Invoked when the user clicks on the menu icon. Displays a popup menu of choices.
     *
     * @param event IDEA action event
     */
    public void actionPerformed(AnActionEvent event)
    {
        ideaEvent = event;
        IdeaPlugin restPlugin = IdeaPlugin.getInstance(event.getData(DataKeys.PROJECT));
        popup.show(restPlugin.getPluginPanel(), 31, 0);
    }
}
