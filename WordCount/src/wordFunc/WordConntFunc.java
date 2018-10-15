package wordFunc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WordConntFunc{
	static String resultPath="D:\\eclipse\\eclipseworkdata\\result.txt";
	static StringBuffer sbForSearch=new StringBuffer();
	public int char_num(String filePath){
		int charNum=0;
		String line;
		File file=new File(filePath);
		FileReader fr=null;
		try {
			fr=new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		try {
			line=br.readLine();
			while(line!=null) {
				sb.append(line);
				line=br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String info=sb.toString();
		charNum=info.length();
		return charNum;
	}
	public int line_num(String filePath) {
		int lineNum=0;
		File file=new File(filePath);
		BufferedReader bf=null;
		try {
			bf=new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(bf.readLine()!=null) {
				lineNum++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineNum;
	}
	public int word_num(String filePath) {
		int wordNum=0;
		String [] saveWord;
		File file=new File(filePath);
		FileReader fr=null;
		try {
			fr=new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bf=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		String line="";
		try {
			while((line=bf.readLine())!=null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str=sb.toString().replaceAll("[^a-zA-Z^]"," ");
		saveWord=str.split("\\s+");
		wordNum=saveWord.length;
		return wordNum;
	}
	public void saveResult(String result) {
		File file=new File(resultPath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedWriter bf=null;
		try {
			bf=new BufferedWriter(new FileWriter(file,true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bf.write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void show_file(File mainFile,List <String>searchPath) {
		if(mainFile.exists()) {
		File [] fileList=mainFile.listFiles();
			for(File f:fileList) {
				if(f.isFile()) {
					if(f.getName().contains(".c")) {
						searchPath.add(mainFile.getPath()+"\\"+f.getName());
					}
			}
				else {
					show_file(f,searchPath);
				}
		}
			/*for(int i=0;i<searchPath.size();i++) {
				System.out.println("展示文件名："+searchPath.get(i));
			}*/
	}else {
		System.out.println("文件不存在！");
	}
	}
	public String mult_num(String filePath) {
		String multNum="";
		int codeLineNum=0;
		int emptyLineNum=0;
		int noteLineNum=0;
		String line;
		File file=new File(filePath);
		FileReader fr=null;
		try {
			fr=new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br=new BufferedReader(fr);
		try {
			while((line=br.readLine())!=null) {
				//返回的字符串长度为0，则在文本结束前，本行判断为空行
				if(line.replaceAll("\\s","").length()<2) {
					emptyLineNum++;
				}
				//本次判读为判断单行注释，字符串以”//“开头，则为注释行
				else if(line.contains("//")) {
					noteLineNum++;
				}
				//其余则算作代码行
				else {
					codeLineNum++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		multNum=codeLineNum+"/"+emptyLineNum+"/"+noteLineNum;
		return multNum;
	}
	public int stop_num(String filePath,String stopListPath) {
		int stopNum=0;
		File file=new File(filePath);
		FileReader fr=null;
		try {
			fr=new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br=new BufferedReader(fr);
		String line="";
		StringBuffer sb=new StringBuffer();
		try {
			while((line=br.readLine())!=null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String [] saveword=sb.toString().replaceAll("[^a-zA-Z^]", " ").split("\\s+");
		stopNum=saveword.length;
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File fileStop=new File(stopListPath);
		FileReader frStop=null;
		try {
			frStop=new FileReader(fileStop);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bfStop=new BufferedReader(frStop);
		String lineStop="";
		StringBuffer sbStop=new StringBuffer();
		try {
			while((lineStop=bfStop.readLine())!=null) {
				sbStop.append(lineStop);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String [] savestop=sbStop.toString().replaceAll("[^a-zA-Z^]", " ").split("\\s+");
		try {
			bfStop.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<savestop.length;i++) {
			for(int j=0;j<saveword.length;j++) {
				if(savestop[i].equals(saveword[j])==true) {
					stopNum--;
				}
			}
		}
		//对于循环处理值，出现了一些问题
		/*List<String> fileList=new ArrayList<String>();
		fileList.add(filePath);
		fileList.add(stopListPath);
		for(String path:fileList){
			File file=new File(path);
			FileReader fr=null;
			String line="";
			try {
				fr=new FileReader(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader br=new BufferedReader(fr);
			StringBuffer sb=new StringBuffer();
			try {
				while((line=br.readLine())!=null){
					sb.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] saveword=sb.toString().replaceAll("[^a-zA-Z^]", " ").split("\\s");
			String saveResult=saveword.toString();
		}*/
		return stopNum;
	}
}
