package com.sourcestream.plugin.idea.restclient;

import com.intellij.openapi.actionSystem.DataKeys;
import org.wiztools.restclient.server.TraceServer;
import org.wiztools.restclient.ui.IdeaPlugin;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Handles the tools menu action by displaying a popup menu of edit choices and processing the user's selection.
 */
public class ToolsMenuAction extends BaseMenuAction
{
    private String port = null;

    /**
     * Constructs a new ToolsMenuAction object.
     */
    public ToolsMenuAction()
    {
        popup = new JPopupMenu();

        JMenuItem item = new JMenuItem("Password Encoder/Decoder");
        item.setActionCommand(IdeaPlugin.ACTION_PASSWORD_ENCODER_DECODER);
        item.addActionListener(this);
        popup.add(item);

        popup.addSeparator();

        port = System.getProperty(TraceServer.SYS_PROPERTY_PORT);
        if (port == null)
        {
            port = String.valueOf(TraceServer.PORT);
        }

        item = new JMenuItem("Start Trace Server @ port " + port);
        item.setActionCommand(IdeaPlugin.ACTION_START_TRACE_SERVER);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Stop Trace Server");
        item.setActionCommand(IdeaPlugin.ACTION_STOP_TRACE_SERVER);
        item.addActionListener(this);
        popup.add(item);

        item = new JMenuItem("Insert Trace Server URL");
        item.setActionCommand(IdeaPlugin.ACTION_INSERT_TRACE_URL);
        item.addActionListener(this);
        popup.add(item);

        popup.addSeparator();

        item = new JMenuItem("Options");
        item.setActionCommand(IdeaPlugin.METHOD_OPTIONS);
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

        if (event.getActionCommand().equals(IdeaPlugin.ACTION_PASSWORD_ENCODER_DECODER))
        {
            plugin.passwordEncodeDecode();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_START_TRACE_SERVER))
        {
            plugin.startTraceServer();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_STOP_TRACE_SERVER))
        {
            plugin.stopTraceServer();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.ACTION_INSERT_TRACE_URL))
        {
            plugin.insertTraceServerUrl();
        }
        else if (event.getActionCommand().equals(IdeaPlugin.METHOD_OPTIONS))
        {
            plugin.options(event);
        }
    }
}
