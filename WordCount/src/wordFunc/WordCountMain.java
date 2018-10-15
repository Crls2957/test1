package wordFunc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import swing.swingUI;
public class WordCountMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordConntFunc wc=new WordConntFunc();
		String result="";
		String stopListPath="";
		boolean searchSign=false;
		List<String> argsSign=new ArrayList<>();
		List<String> filePath=new ArrayList<>();
		List<String> resultPath=new ArrayList<>();
		List<String> searchPath=new ArrayList<>();
		/*String search="";*/
		for(int i=0;i<args.length;i++) {
			if(args[i].startsWith("-")) {
				argsSign.add(args[i]);
			}
			/*else if(args[i].endsWith(".c")) {
				search=args[i];
			}*/
			else if(args[i].contains("stop")){
				stopListPath=args[i].toString();
			}
			else if(args[i].contains("*.c")) {
				searchSign=true;
			}
			else {
				filePath.add(args[i]);
			}
		}
		for(int i=0;i<argsSign.size();i++) {
			System.out.println(argsSign.get(i));
			if(!argsSign.contains("-o")) {
				if(argsSign.contains("-x")) {
					new swingUI();
					break;
				}
				if(argsSign.contains("-s")&&searchSign==true) {
					File mainFile=new File("D:\\eclipse\\eclipseworkdata");
					wc.show_file(mainFile,searchPath);
					for(int j=0;j<searchPath.size();j++) {
						System.out.println("这是search："+searchPath.get(j));
						switch(argsSign.get(i)) {
						case "-c":
							result=searchPath.get(j)+","+"字符数:"+wc.char_num(searchPath.get(j));
							break;
						case "-l":
							result=searchPath.get(j)+","+"行 数:"+wc.line_num(searchPath.get(j));
							break;
						case "-w":
							result=searchPath.get(j)+","+"单词数:"+wc.word_num(searchPath.get(j));
							break;
						case "-a":
							result=searchPath.get(j)+","+"代码行/空行/注释行"+wc.mult_num(searchPath.get(j));
							break;
						case "-e":
							result=searchPath.get(j)+","+"单词数:"+wc.stop_num(searchPath.get(j), stopListPath);
							break;
						case "-s":
							break;
							default:
								System.out.println("输入有误！");
								break;
						}
					}
				}
			    for(int j=0;j<filePath.size();j++) {
			    	System.out.println("这是普通"+filePath.get(j));
			    	/*if(argsSign.contains("-a")) {
			    		String searchSign=filePath.get(j)+search;
			    		String saveFile=wc.show_file(filePath.get(j), searchSign);
			    		String [] fileResults=saveFile.split("\\s+");
			    		for(int k=0;k<fileResults.length;k++) {
			    			switch(argsSign.get(i)) {
							case "-c":
								result=fileResults[i]+","+"字符数:"+wc.char_num(fileResults[i]);
								break;
							case "-l":
								result=fileResults[i]+","+"行 数:"+wc.line_num(fileResults[i]);
								break;
							case "-w":
								result=fileResults[i]+","+"单词数:"+wc.word_num(fileResults[i]);
								break;
								default:
									System.out.println("输入有误！");
									break;
			    		}
			    	}
				}
			    	else {*/
			    		switch(argsSign.get(i)) {
						case "-c":
							result=filePath.get(j)+","+"字符数:"+wc.char_num(filePath.get(j));
							break;
						case "-l":
							result=filePath.get(j)+","+"行 数:"+wc.line_num(filePath.get(j));
							break;
						case "-w":
							result=filePath.get(j)+","+"单词数:"+wc.word_num(filePath.get(j));
							break;
						case "-a":
							result=filePath.get(j)+","+"代码行/空行/注释行"+wc.mult_num(filePath.get(j));
							break;
						case "-e":
							result=filePath.get(j)+","+"单词数:"+wc.stop_num(filePath.get(j), stopListPath);
							break;
							default:
								System.out.println("输入有误！");
								break;
						}
			    	}
				wc.saveResult(result);
			}
			else {
				wc.resultPath=filePath.get(filePath.size()-1);
				for(int j=0;j<filePath.size()-1;j++) {
					switch(argsSign.get(i)) {
					case "-c":
						result=filePath.get(j)+","+"字符数:"+wc.char_num(filePath.get(j));
						break;
					case "-l":
						result=filePath.get(j)+","+"行     数:"+wc.line_num(filePath.get(j));
						break;
					case "-w":
						result=filePath.get(j)+","+"单词数:"+wc.word_num(filePath.get(j));
						break;
					case "-a":
						result=filePath.get(j)+","+"代码行/空行/注释行"+wc.mult_num(filePath.get(j));
						break;
						default:
							System.out.println("输入有误！");
							break;
					}
				}
				wc.saveResult(result);
			}
		}
	}
}
