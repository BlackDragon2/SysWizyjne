package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algorithms.Algorithm;
import algorithms.CDL;
import algorithms.Disney;

public class CommandPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1534280986442777249L;
	private DrawPanel _drawPanel;
	private JFileChooser _fileChooser;
	private JButton _openButton;
	private JButton _startButton;
	private JTextField _textField;
	private JComboBox<Algorithm> _comboBox;
	
	private File[] _fileList;
	

	public CommandPanel(DrawPanel drawPanel) 
	{
		super();
		_drawPanel=drawPanel;
	}

	public void init() 
	{
		_fileChooser=new JFileChooser();		
		_openButton=new JButton("Open directory");
		_startButton=new JButton("Start");
		_textField=new JTextField();
		_comboBox=new JComboBox<Algorithm>();
		
		setView();
		
		_textField.setAutoscrolls(true);
		
		_fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		_comboBox.addItem(new Disney());
		_comboBox.addItem(new CDL());
		
		_openButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event) 
			{
	            int returnVal = _fileChooser.showOpenDialog(CommandPanel.this);
	            
	            if (returnVal == JFileChooser.APPROVE_OPTION) 
	            {
	                File dir = _fileChooser.getSelectedFile();
	                _textField.setText(dir.getPath());
	                
	            } 
	            else 
	            {
	            }			
			}
		});
		
		_startButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String dir=_textField.getText();
				if(dir.isEmpty())
					JOptionPane.showMessageDialog(CommandPanel.this, "Wrong or no path given","Path error", JOptionPane.WARNING_MESSAGE);
				else
				{
					File file=new File(dir);
					_fileList=file.listFiles();
					if(_fileList.length==0)
						JOptionPane.showMessageDialog(CommandPanel.this, "No files in choosen directory","No files error", JOptionPane.WARNING_MESSAGE);
					else
					{
						Algorithm algorithm=(Algorithm)_comboBox.getSelectedItem();
						_drawPanel.draw(algorithm.compute(_fileList));
					}
				}				
			}
			
		});
	}

	private void setView() 
	{
		GroupLayout layout=new GroupLayout(this);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(_openButton, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_startButton, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_textField, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_comboBox, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(_openButton, 0, 30, 50)
				.addComponent(_textField, 0, 30, 50)
				.addGap(40)
				.addComponent(_comboBox, 0, 30, 50)
				.addGap(60)
				.addComponent(_startButton, 0, 30, 50));
		this.setLayout(layout);
		
	}

}
