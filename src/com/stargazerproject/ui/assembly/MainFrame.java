package com.stargazerproject.ui.assembly;

import java.awt.Point;

import com.stargazerproject.ui.listener.LogoClickListener;
import com.stargazerproject.ui.listener.MouseAdapterListener;
import com.stargazerproject.ui.listener.MouseMotionAdapterListener;
import com.stargazerproject.ui.parameter.GetParamentByName;

/**
 * 混合主界面,启动界面
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class MainFrame {
	/**混合主界面**/
	private static BaseFrame baseFrame;
	/**控制台**/
	private static ConsoleTextArea consoleTextArea;
	/**主界面滚动条**/
	private static MainFrameJScrollPane jScrollPane;
	/**操纵图标头像位置**/
	private static MainFrameLogoJlabel mainFrameLogoJlabel;
	/**主界面背景**/
	private static MainFrameBackgroundJlabel mainFrameBackgroundJlabel;
	/**混合主界面布局**/
	private static MainFrameLayout mainFrameLayout;
	/**主界面坐标点，用于界面拖动**/
	private static Point origin;
	/**主界面鼠标点击事件**/
	private static LogoClickListener logoClickListener;
	/**获取主界面鼠标点击位置**/
	private static MouseAdapterListener mouseAdapterListener;
	/**主界面鼠标点击事件**/
	private static MouseMotionAdapterListener mouseMotionAdapterListener;
	
	public MainFrame(){
		baseFrame = BaseFrame.getInstance();
		consoleTextArea = ConsoleTextArea.getInstance();
		jScrollPane = MainFrameJScrollPane.getInstance(consoleTextArea);
		mainFrameLogoJlabel = MainFrameLogoJlabel.getInstance(GetParamentByName.GetParamentByKeyInSystemMemory("Main_Frame_Logo_Path"));
		mainFrameBackgroundJlabel = MainFrameBackgroundJlabel.getInstance(GetParamentByName.GetParamentByKeyInSystemMemory("MAIN_INTERFACE_BACKGROUND"));
		mainFrameLayout = MainFrameLayout.getInstance();
		origin = new Point();
		logoClickListener = LogoClickListener.getInstance(baseFrame);
		mouseAdapterListener = MouseAdapterListener.getInstance(origin);
		mouseMotionAdapterListener = MouseMotionAdapterListener.getInstance(origin,baseFrame);
		
		mainFrameLayout.initMainFrameLayout(baseFrame, jScrollPane);
		baseFrame.getLayeredPane().add(mainFrameBackgroundJlabel,new Integer(Integer.MIN_VALUE));
		baseFrame.getLayeredPane().add(mainFrameLogoJlabel,new Integer(Integer.MIN_VALUE)+1);
		mainFrameLogoJlabel.addMouseListener(logoClickListener);
		mainFrameLogoJlabel.addMouseListener(mouseAdapterListener);
		mainFrameLogoJlabel.addMouseMotionListener(mouseMotionAdapterListener);	
	}
	
	public void VisualMainFrame(){
		baseFrame.setVisible(Boolean.TRUE);
	}
}
