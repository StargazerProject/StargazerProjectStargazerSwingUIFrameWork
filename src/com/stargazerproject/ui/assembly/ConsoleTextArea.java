package com.stargazerproject.ui.assembly;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.text.Document;

import com.stargazerproject.ui.exception.ExceptionDispose;
import com.stargazerproject.ui.parameter.GetParamentByName;
import com.stargazerproject.ui.util.ColorUtil;
import com.stargazerproject.ui.util.FontUtil;
import com.stargazerproject.ui.util.LoopedStreams;

/**
 * 主界面控制台输出
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class ConsoleTextArea extends JTextArea {
	private static final long serialVersionUID = 7309857723035362456L;
	/**ConsoleTextArea单例**/
	private static ConsoleTextArea consoleTextArea;
	/**控制台输出字符字体类型**/
	private static Font ConsoleTextFont;
	/**控制台备用字体的绝对路径 在系统包含指定字体的情况下将不使用备用字体**/
	public static String Main_Frame_Console_StandbyFontPath;
	/**控制台指定字体名称**/
	public static String Main_Frame_Console_FontName;
	/**控制台输出字符字体颜色**/
	private static Color ConsoleText_FontColor;
	
	public static ConsoleTextArea getInstance(){
		if(consoleTextArea == null){
			consoleTextArea = new ConsoleTextArea();
			consoleTextArea.initConsoleTextArea();
		}
		return consoleTextArea;
	}
	
	private ConsoleTextArea() {
		Main_Frame_Console_StandbyFontPath = GetParamentByName.GetParamentByKeyInSystemMemory("Main_Frame_Console_StandbyFontPath");
		Main_Frame_Console_FontName = GetParamentByName.GetParamentByKeyInSystemMemory("Main_Frame_Console_FontName");
		ConsoleText_FontColor = ColorUtil.getColorFromSystemmParanment("ConsoleText_FontColor");
		ConsoleTextFont = FontUtil.getConsoleFont(Main_Frame_Console_FontName,Main_Frame_Console_StandbyFontPath);
		ConsoleTextFont.deriveFont(Font.BOLD, 13);
	}
	
	    public void initConsoleTextArea(){
	        LoopedStreams ls = null;
			try {
				ls = new LoopedStreams();
			} catch (IOException e) {
				ExceptionDispose.catchExceptionAndSaveToDatabase("StargazerSystem Error Report : "+"控制台输出 LoopedStreams 异常");
			}
	        PrintStream ps = new PrintStream(ls.getOutputStream());
	        System.setOut(ps);
	        System.setErr(ps);
	        consoleTextArea.startConsoleReaderThread(ls.getInputStream());
	        consoleTextArea.setOpaque(false);
	        consoleTextArea.setFont(ConsoleTextFont);
	        consoleTextArea.setForeground(ConsoleText_FontColor);
	        consoleTextArea.setBorder(BorderFactory.createEmptyBorder());
	    }
	    
	    private void startConsoleReaderThread(InputStream inStream) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
            new Thread(new Runnable() {
                public void run() {
                    StringBuffer sb = new StringBuffer();
                    try {
                        String s="";
                        int c;
                        int totalLenght = 0;
                        Document doc = getDocument();
                        while((c=br.read())!= -1) {
                            boolean caretAtEnd = false;
                            caretAtEnd = getCaretPosition() == doc.getLength() ? true : false;
                            sb.setLength(0);
                            char singlechar = (char) c;
                            s = singlechar+"";
                            int lenght = s.length();
                            if(lenght>90){
                            	System.out.println("拆分");
                            	String tempSOne = s.substring(0, 90);
                            	String tempSTwo = s.substring(90, lenght);
                            append(sb.append(tempSOne).append('\n').toString());
                           // sb = new StringBuffer();
                            	append(sb.append(tempSTwo).append('\n').toString());
                            }
                            else{
	                        append(sb.append(s).toString());
                            }

                            if(caretAtEnd)
                            setCaretPosition(doc.getLength());
                        }
                    }
                    catch(IOException e) {
                    	System.err.println(e.getMessage());
                    }
                }
            }).start();
        }
}