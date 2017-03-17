package tangent65536.losm.picdown.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tangent65536.losm.picdown.EnumDownloaders;
import tangent65536.losm.picdown.util.DateTime;

public class Main
{
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args)
	{
		logger.info("Picture Downloader v0.2.1 made by Tangent65536");
		logger.info("(c) 2016 All Rights Reserved.");
		DataSelectingFrame s1f = new DataSelectingFrame();
		s1f.setVisible(true);
	}
	
	public static class DataSelectingFrame extends JFrame
	{
		private static final long serialVersionUID = 0L;
		
		private final DataCheckbox[] dcs = new DataCheckbox[EnumDownloaders.values().length];
		
		public DataSelectingFrame()
		{
			super("Please select products");
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			this.setLayout(new BorderLayout());
			
			Container c = this.getContentPane();
			
			JPanel panelBoxes = new JPanel();
			panelBoxes.setLayout(new GridLayout(EnumDownloaders.values().length, 1));
			
			EnumDownloaders[] eds = EnumDownloaders.values();
			for(int i = 0 ; i < eds.length ; i++)
			{
				this.dcs[i] = new DataCheckbox(eds[i]);
				panelBoxes.add(this.dcs[i]);
			}
			
			JPanel panelButtons = new JPanel();
			panelButtons.setLayout(new GridLayout(1, 2));
			
			SingleDownButton singleDown = new SingleDownButton(this);
			MultiDownButton multiDown = new MultiDownButton(this);
			panelButtons.add(singleDown);
			panelButtons.add(multiDown);
			
			c.add(panelBoxes, BorderLayout.NORTH);
			c.add(panelButtons, BorderLayout.SOUTH);
			
			this.setSize(400, 400);
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		}
		
		private ArrayList<EnumDownloaders> getChecked()
		{
			ArrayList<EnumDownloaders> list = new ArrayList<EnumDownloaders>();
			for(DataCheckbox dcb : this.dcs)
			{
				if(dcb.isSelected())
				{
					list.add(dcb.downloader);
				}
			}
			return list;
		}
	}
	
	public static class DataCheckbox extends JCheckBox
	{
		private static final long serialVersionUID = 0L;
		
		private final EnumDownloaders downloader;
		
		private DataCheckbox(EnumDownloaders ed)
		{
			this.downloader = ed;
			this.setText(this.downloader.downloader.name);
		}
	}
	
	public static abstract class DownloadTypeButton extends JButton implements ActionListener
	{
		private static final long serialVersionUID = 0L;
		
		private final DataSelectingFrame frame;
		private final Class<? extends JFrame> step2Frame;
		
		private DownloadTypeButton(String text, DataSelectingFrame s1f, Class<? extends JFrame> s2fClass)
		{
			this.frame = s1f;
			this.step2Frame = s2fClass;
			this.setText(text);
			this.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				ArrayList<EnumDownloaders> list = this.frame.getChecked();
				if(list.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "You selected nothing...");
					return;
				}
				JFrame s2f = this.step2Frame.getConstructor(ArrayList.class).newInstance(list);
				this.frame.setVisible(false);
				s2f.setVisible(true);
				this.frame.dispose();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static class SingleDownButton extends DownloadTypeButton
	{
		private static final long serialVersionUID = 0L;
		
		private SingleDownButton(DataSelectingFrame s1f)
		{
			super("Single Data", s1f, Step2FrameA.class);
		}
	}
	
	public static class MultiDownButton extends DownloadTypeButton
	{
		private static final long serialVersionUID = 0L;
		
		private MultiDownButton(DataSelectingFrame s1f)
		{
			super("Time Series", s1f, Step2FrameB.class);
		}
	}
	
	public static class TimeBlank extends JTextField
	{
		private static final long serialVersionUID = 0L;
		
		public TimeBlank(String s, JPanel panel)
		{
			this.setText(s);
			this.selectAll();
			panel.add(this);
		}
	}
	
	public static class Step2FrameA extends JFrame implements ActionListener
	{
		private static final long serialVersionUID = 0L;
		
		private final ArrayList<EnumDownloaders> downlist;
		
		private static final String[] blankTexts = new String[]{"year", "month", "day", "hour", "minute"};
		private static final JTextField[] blanks = new JTextField[blankTexts.length];
		
		public Step2FrameA(ArrayList<EnumDownloaders> list)
		{
			super("Set Time");
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			this.downlist = list;
			
			Container c = this.getContentPane();
			
			JLabel label = new JLabel("Set time:");
			c.add(label, BorderLayout.NORTH);
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(5, 1));
			
			for(int i = 0 ; i < blankTexts.length ; i++)
			{
				blanks[i] = new TimeBlank(blankTexts[i], panel);
			}
			
			c.add(panel, BorderLayout.CENTER);
			
			JButton button = new JButton();
			button.setText("Download");
			button.addActionListener(this);
			c.add(button, BorderLayout.SOUTH);
			
			this.setSize(200, 250);
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				int[] times = new int[blanks.length];
				for(int i = 0 ; i < blanks.length ; i++)
				{
					times[i] = Integer.parseInt(blanks[i].getText());
				}
				DateTime time = new DateTime(times);
				this.setVisible(false);
				for(EnumDownloaders ed : this.downlist)
				{
					ed.downloader.downloadFile(time);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invalid date/time!");
				return;
			}
			this.dispose();
			JOptionPane.showMessageDialog(null, "Done!");
			System.exit(0);
		}
	}
	
	public static class Step2FrameB extends JFrame implements ActionListener
	{
		private static final long serialVersionUID = 0L;
		
		private final ArrayList<EnumDownloaders> downlist;
		
		private static final String[] blankTexts = new String[]{"year", "month", "day", "hour", "minute"};
		private static final JTextField[] blanksStart = new JTextField[blankTexts.length];
		private static final JTextField[] blanksEnd = new JTextField[blankTexts.length];
		
		public Step2FrameB(ArrayList<EnumDownloaders> list)
		{
			super("Set Time");
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			this.downlist = list;
			
			Container c = this.getContentPane();
			
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(blanksStart.length + 1, 1));
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(blanksEnd.length + 1, 1));
			
			JLabel label1 = new JLabel("Start time:");
			panel1.add(label1);
			
			JLabel label2 = new JLabel("End time:");
			panel2.add(label2);
			
			for(int i = 0 ; i < blankTexts.length ; i++)
			{
				blanksStart[i] = new TimeBlank(blankTexts[i], panel1);
			}
			
			for(int i = 0 ; i < blankTexts.length ; i++)
			{
				blanksEnd[i] = new TimeBlank(blankTexts[i], panel2);
			}
			
			c.add(panel1, BorderLayout.WEST);
			c.add(panel2, BorderLayout.EAST);
			
			JButton button = new JButton();
			button.setText("Download");
			button.addActionListener(this);
			c.add(button, BorderLayout.SOUTH);
			
			this.setSize(250, 250);
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				int[] timesStart = new int[blanksStart.length];
				int[] timesEnd = new int[blanksEnd.length];
				
				for(int i = 0 ; i < blanksStart.length ; i++)
				{
					timesStart[i] = Integer.parseInt(blanksStart[i].getText());
				}
				for(int i = 0 ; i < blanksEnd.length ; i++)
				{
					timesEnd[i] = Integer.parseInt(blanksEnd[i].getText());
				}
				
				DateTime time1 = new DateTime(timesStart);
				DateTime time2 = new DateTime(timesEnd);
				
				this.setVisible(false);
				for(EnumDownloaders ed : this.downlist)
				{
					ed.downloader.downloadFiles(time1, time2);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invalid date/time!");
				return;
			}
			this.dispose();
			JOptionPane.showMessageDialog(null, "Done!");
			System.exit(0);
		}
	}
}
