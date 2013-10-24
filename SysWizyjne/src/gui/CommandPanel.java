package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algorithms.Algorithm;
import algorithms.CDL;
import algorithms.Disney;
import algorithms.Position;

/**
 * Command panel
 * @author Bartek
 * @version 1.1
 */
public class CommandPanel extends JPanel
{

	private static final long serialVersionUID = 1534280986442777249L;
	
	private DrawPanel _drawPanel;
	private JFileChooser _fileChooser;
	private JButton _openButton;
	private JButton _startButton;
	private JTextField _textField;
	private JComboBox<Algorithm> _comboBox;
	private JCheckBox _horizontalPosition;
	private JCheckBox _verticalPosition;
	private JButton _createEPIButton;
	
	private File _directory;
	

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
		_horizontalPosition=new JCheckBox("Horizontal");
		_verticalPosition=new JCheckBox("Vertical");
		_createEPIButton=new JButton("Create EPI");
		
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
			}
		});
		
		_startButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				boolean status=true;
				String dir=_textField.getText();
				Position position=getPosition();
				if(dir.isEmpty())
					JOptionPane.showMessageDialog(CommandPanel.this, "Wrong or no path given","Path error", JOptionPane.WARNING_MESSAGE);
				else
				{
					_directory=new File(dir);
					if(position==Position.HORIZONTAL||position==Position.BOTH)
						status=status&&checkDir(new File(dir, Position.HORIZONTAL.toString()));
					if(position==Position.VERTICAL||position==Position.BOTH)
						status=status&&checkDir(new File(dir, Position.VERTICAL.toString()));
					if(!status)
						JOptionPane.showMessageDialog(CommandPanel.this, "No files in choosen directory","No files error", JOptionPane.WARNING_MESSAGE);
					else
					{
						Algorithm algorithm=(Algorithm)_comboBox.getSelectedItem();
						algorithm.compute(_directory, position);
					}
				}				
			}
			
		});
		
		_createEPIButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				boolean status=true;
				Position position=getPosition();
				String dir=_textField.getText();
				if(dir.isEmpty())
					JOptionPane.showMessageDialog(CommandPanel.this, "Wrong or no path given","Path error", JOptionPane.WARNING_MESSAGE);
				else
				{
					_directory=new File(dir);
					if(position==Position.HORIZONTAL||position==Position.BOTH)
						status=status&&checkDir(new File(dir, Position.HORIZONTAL.toString()));
					if(position==Position.VERTICAL||position==Position.BOTH)
						status=status&&checkDir(new File(dir, Position.VERTICAL.toString()));
					if(!status)
						JOptionPane.showMessageDialog(CommandPanel.this, "No files in choosen directory","No files error", JOptionPane.WARNING_MESSAGE);
					else
					{
						if(_horizontalPosition.isSelected())
							Algorithm.createEPI(_directory, Position.HORIZONTAL);
						if(_verticalPosition.isSelected())
							Algorithm.createEPI(_directory, Position.VERTICAL);
					}
				}	
				
			}
			
		});
	}

	protected boolean checkDir(File file) 
	{
		return file.listFiles().length!=0;		
	}

	protected Position getPosition() 
	{
		Position result=Position.BOTH;
		if(_horizontalPosition.isSelected()&&_verticalPosition.isSelected())
			result=Position.BOTH;
		else
			if(_horizontalPosition.isSelected())
				result=Position.HORIZONTAL;
			else
				if(_verticalPosition.isSelected())
					result=Position.VERTICAL;
				else
					JOptionPane.showMessageDialog(CommandPanel.this, "No position choosen","Using default BOTH", JOptionPane.WARNING_MESSAGE);
		return result;
	}

	private void setView() 
	{
		GroupLayout layout=new GroupLayout(this);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(_openButton, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_startButton, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_textField, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_comboBox, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_horizontalPosition, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_verticalPosition, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH)
				.addComponent(_createEPIButton, 0, MainFrame.COMMAND_PANEL_WIDTH, MainFrame.COMMAND_PANEL_WIDTH));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(_openButton, 0, 30, 50)
				.addComponent(_textField, 0, 30, 50)
				.addGap(40)
				.addComponent(_horizontalPosition, 0, 30, 50)
				.addComponent(_verticalPosition, 0, 30, 50)
				.addComponent(_createEPIButton, 0, 30, 50)
				.addGap(40)
				.addComponent(_comboBox, 0, 30, 50)
				.addGap(60)
				.addComponent(_startButton, 0, 30, 50));
		this.setLayout(layout);
		
	}

}
