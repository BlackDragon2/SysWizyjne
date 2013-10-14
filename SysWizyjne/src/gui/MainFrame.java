package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.w3c.dom.DOMException;

public class MainFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1414097905694258877L;

	public static final int COMMAND_PANEL_WIDTH=150;
	
	private DrawPanel _drawPanel;
	private CommandPanel _commandPanel;
	
	public MainFrame()
	{
		setName("Tworzenie modelu 3D");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
	}
	
	public void init() throws DOMException, Exception 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		
		JComponent contentPane=(JComponent) getContentPane();
		_drawPanel=new DrawPanel();
		_commandPanel=new CommandPanel(_drawPanel);
		_drawPanel.init();
		_commandPanel.init();
		contentPane.add(_drawPanel);
		contentPane.add(_commandPanel);
		setSize(screenSize);
		setVisible(true);
		
		GroupLayout layout=new GroupLayout(contentPane);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(_drawPanel, 0, width-COMMAND_PANEL_WIDTH, width-COMMAND_PANEL_WIDTH)
				.addComponent(_commandPanel, 0, COMMAND_PANEL_WIDTH, COMMAND_PANEL_WIDTH));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(_drawPanel, 0, height, height)
				.addComponent(_commandPanel, 0, height, height));
		contentPane.setLayout(layout);	
		_drawPanel.setVisible(true);
		_commandPanel.setVisible(true);
	}
}
