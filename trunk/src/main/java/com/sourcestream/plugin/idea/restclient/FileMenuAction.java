package com.sourcestream.plugin.idea.restclient;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import org.wiztools.restclient.ui.IdeaPlugin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the file menu action by displaying a popup menu of file choices and processing the user's selection.
 */
public class FileMenuAction extends AnAction implements ActionListener
{
    private JPopupMenu popup;
    private AnActionEvent ideaEvent;

    public FileMenuAction()
    {
        popup = new JPopupMenu();

        JMenuItem item = new JMenuItem("Open Request");
        item.setActionCommand(RESTClientPlugin.METHOD_OPEN_REQUEST);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Open Response");
        item.setActionCommand(RESTClientPlugin.METHOD_OPEN_RESPONSE);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Open Req-Res Archive");
        item.addActionListener(this);
        popup.add(item);

        popup.addSeparator();

        item = new JMenuItem("Save Request");
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Save Response");
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Save Response Body");
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Save Req-Res Archive");
        item.addActionListener(this);
        popup.add(item);
    }

    /**
     * Invoked when the user clicks on the file menu icon. Displays a popup menu of file choices.
     *
     * @param event IDEA action event
     */
    public void actionPerformed(AnActionEvent event)
    {
        ideaEvent = event;
        IdeaPlugin restPlugin = IdeaPlugin.getInstance(event.getData(DataKeys.PROJECT));
        popup.show(restPlugin.getPluginPanel(), 31, 0);
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
            ReflectionUtil.invokeMethod(plugin.getRESTMain(), IdeaPlugin.METHOD_OPEN_REQUEST);
        }
        else if (event.getActionCommand().equals(IdeaPlugin.METHOD_OPEN_RESPONSE))
        {
            ReflectionUtil.invokeMethod(plugin.getRESTMain(), IdeaPlugin.METHOD_OPEN_RESPONSE);
        }
    }
}
