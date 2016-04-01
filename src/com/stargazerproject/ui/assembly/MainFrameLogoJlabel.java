package com.stargazerproject.ui.assembly;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.stargazerproject.ui.parameter.GetParamentByName;

/**
 *操控头像 
 *
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 **/
public class MainFrameLogoJlabel extends JLabel {
	private static final long serialVersionUID = 5010683231088848230L;
	/**操控头像的位置几大小**/
	private static int logoLocation[];
	/**单例模式**/
	private static MainFrameLogoJlabel mainFrameLogoJlabel;
	
	public static MainFrameLogoJlabel getInstance(String backgroundLogo){
		if(mainFrameLogoJlabel == null){
			mainFrameLogoJlabel = new MainFrameLogoJlabel(new ImageIcon(backgroundLogo));
			mainFrameLogoJlabel.InitMainFrameLogoJlabel();
		}
		return mainFrameLogoJlabel;
	}
	
	private MainFrameLogoJlabel(ImageIcon backgroundLogo) {
		super(backgroundLogo);
		logoLocation = GetParamentByName.GetParamentByKeyInSystemMemoryTransformNormallArray("Main_Frame_LogoLocation");
	}
	
	private void InitMainFrameLogoJlabel(){
		mainFrameLogoJlabel.setBounds(logoLocation[0], logoLocation[1], logoLocation[2], logoLocation[3]);
	}
}
