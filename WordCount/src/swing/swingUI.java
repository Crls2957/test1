package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import wordFunc.WordConntFunc;

import java.awt.*;
public class swingUI implements ActionListener{
	static String resultPath="D:\\eclipse\\eclipseworkdata\\result.txt";
	WordConntFunc wc=new WordConntFunc();
	JFrame jframe=new JFrame("WordCount");
	JTabbedPane tabPane=new JTabbedPane();//选项卡布局
	Container conF=new Container();//布局一
	Container conS=new Container();//布局二
	JLabel labF=new JLabel("选择文件");
	JTextField textF=new JTextField();
	JButton buttonF=new JButton("选择");
	JButton buttonS=new JButton("确认");
	JTextArea textShow=new JTextArea();
	JFileChooser fileChooser=new JFileChooser();//文件选择器
	public swingUI(){
		fileChooser.setCurrentDirectory(new File("D:\\eclipse\\eclipseworkdata"));//文件选择的初始目录
		double windowWidth=Toolkit.getDefaultToolkit().getScreenSize().getWidth();//获取显示屏宽度
		double windowHight=Toolkit.getDefaultToolkit().getScreenSize().getHeight();//获取屏幕高度
		jframe.setLocation((int)((windowWidth/2)-150), (int)((windowHight/2)-150));//设置窗口位置
		jframe.setSize(350, 150);//设置窗口尺寸
		jframe.setContentPane(tabPane);//设置布局
		//设定组件位置和尺寸,添加入布局
		labF.setBounds(10,10,70,20);
		textF.setBounds(80,10,120,20);
		buttonF.setBounds(210,10,50,20);
		buttonS.setBounds(260,10,50,20);
		buttonF.setMargin(new Insets(0,0,0,0));
		buttonS.setMargin(new Insets(0,0,0,0));
		textShow.setBounds(10,10,310,60);
		textShow.setLineWrap(true);
		buttonF.addActionListener(this);//添加事件处理
		buttonS.addActionListener(this);//添加事件处理
		conF.add(labF);
		conF.add(textF);
		conF.add(buttonF);
		conF.add(buttonS);
		conF.add(fileChooser);
		conS.add(textShow);
		tabPane.add("文件选择", conF);//添加布局
		tabPane.add("统计结果",conS);//添加布局
		jframe.setVisible(true);//设置窗口可见
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(buttonF)) {
			fileChooser.setFileSelectionMode(0);//设定只能选择到文件夹
			int status=fileChooser.showOpenDialog(null);//打开文件选择界面的触发语句
			if(status==1) {
				return;
			}else {
				File f=fileChooser.getSelectedFile();//选择到的文件
				textF.setText(f.getAbsolutePath());
			}
		}
		if(e.getSource().equals(buttonS)) {
			if(textF.getText()!=null) {
				tabPane.setSelectedComponent(conS);
				String choicePath=textF.getText();
				int charNum=wc.char_num(choicePath);
				String resultChar=choicePath+","+"字符数:"+charNum;
				wc.saveResult(resultChar);
				int lineNum=wc.line_num(choicePath);
				String resultLine=choicePath+"行 数:"+lineNum;
				wc.saveResult(resultLine);
				int wordNum=wc.word_num(choicePath);
				String resultWord=choicePath+"单词数:"+wordNum;
				wc.saveResult(resultWord);
				File f1=new File(resultPath);
				FileReader fr=null;
				try {
					fr=new FileReader(f1);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedReader br=new BufferedReader(fr);
				StringBuffer sb=new StringBuffer();
				String line="";
				try {
					while((line=br.readLine())!=null) {
						sb.append(line);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String choiceOutput=sb.toString();
				textShow.setText(choiceOutput);
			}
		}
	}
	/*public static void main(String[] args) {
		new swingUI();
	}*/
}
