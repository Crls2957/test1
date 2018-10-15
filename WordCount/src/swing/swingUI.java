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
	JTabbedPane tabPane=new JTabbedPane();//ѡ�����
	Container conF=new Container();//����һ
	Container conS=new Container();//���ֶ�
	JLabel labF=new JLabel("ѡ���ļ�");
	JTextField textF=new JTextField();
	JButton buttonF=new JButton("ѡ��");
	JButton buttonS=new JButton("ȷ��");
	JTextArea textShow=new JTextArea();
	JFileChooser fileChooser=new JFileChooser();//�ļ�ѡ����
	public swingUI(){
		fileChooser.setCurrentDirectory(new File("D:\\eclipse\\eclipseworkdata"));//�ļ�ѡ��ĳ�ʼĿ¼
		double windowWidth=Toolkit.getDefaultToolkit().getScreenSize().getWidth();//��ȡ��ʾ�����
		double windowHight=Toolkit.getDefaultToolkit().getScreenSize().getHeight();//��ȡ��Ļ�߶�
		jframe.setLocation((int)((windowWidth/2)-150), (int)((windowHight/2)-150));//���ô���λ��
		jframe.setSize(350, 150);//���ô��ڳߴ�
		jframe.setContentPane(tabPane);//���ò���
		//�趨���λ�úͳߴ�,����벼��
		labF.setBounds(10,10,70,20);
		textF.setBounds(80,10,120,20);
		buttonF.setBounds(210,10,50,20);
		buttonS.setBounds(260,10,50,20);
		buttonF.setMargin(new Insets(0,0,0,0));
		buttonS.setMargin(new Insets(0,0,0,0));
		textShow.setBounds(10,10,310,60);
		textShow.setLineWrap(true);
		buttonF.addActionListener(this);//����¼�����
		buttonS.addActionListener(this);//����¼�����
		conF.add(labF);
		conF.add(textF);
		conF.add(buttonF);
		conF.add(buttonS);
		conF.add(fileChooser);
		conS.add(textShow);
		tabPane.add("�ļ�ѡ��", conF);//��Ӳ���
		tabPane.add("ͳ�ƽ��",conS);//��Ӳ���
		jframe.setVisible(true);//���ô��ڿɼ�
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ���
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(buttonF)) {
			fileChooser.setFileSelectionMode(0);//�趨ֻ��ѡ���ļ���
			int status=fileChooser.showOpenDialog(null);//���ļ�ѡ�����Ĵ������
			if(status==1) {
				return;
			}else {
				File f=fileChooser.getSelectedFile();//ѡ�񵽵��ļ�
				textF.setText(f.getAbsolutePath());
			}
		}
		if(e.getSource().equals(buttonS)) {
			if(textF.getText()!=null) {
				tabPane.setSelectedComponent(conS);
				String choicePath=textF.getText();
				int charNum=wc.char_num(choicePath);
				String resultChar=choicePath+","+"�ַ���:"+charNum;
				wc.saveResult(resultChar);
				int lineNum=wc.line_num(choicePath);
				String resultLine=choicePath+"�� ��:"+lineNum;
				wc.saveResult(resultLine);
				int wordNum=wc.word_num(choicePath);
				String resultWord=choicePath+"������:"+wordNum;
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
