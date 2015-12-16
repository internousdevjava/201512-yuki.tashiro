import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class KisoKadai3 {
	public static void main(String[] args) throws Exception {
		int cmd = 0;
		int maxcmd = 4;
		int mincmd = 1;
		boolean b = true;
		do{
			System.out.println("===================================================");
			System.out.println();
			System.out.println();
			System.out.println("1-テキストファイルを新規作成");
			System.out.println("2-テキストファイルを読み込み");
			System.out.println("3-テキストファイルを編集");
			System.out.println("4-テキストファイルを削除");
			System.out.println("1～4を選択してください。");
			System.out.println("終了する場合はzを入力してください。");
			cmd = checkNumber(mincmd, maxcmd);
			do{
				b = true;
				switch(cmd){
				case 1:
					String folder = findFolder(false);
					if(!folder.equals("z")){
						int selectTarget = selectTarget(true);
						if(selectTarget == 1){
							b = createFolder(folder);
						}else if(selectTarget == 2){
							b = createFile(folder);
						}
					}
					break;
				case 2:
					String file = findFolder(true);
					if(!file.equals("z")){
						b = readFile(file);
					}
					break;
				case 3:
					String file1 = findFolder(true);
					if(!file1.equals("z")){
						b = updateFile(file1);
					}
					break;
				case 4:
					int selectTarget = selectTarget(false);
					String file2 = null;
					if(selectTarget == 1){
						file2 = findFolder(false);
					}else if(selectTarget == 2){
						file2 = findFolder(true);
					}else{
						break;
					}
					if(!file2.equals("z")){
						b = deleteFile(file2);
					}
				}
			}while(!b);
		}while(cmd != 0);
		System.out.println("ご利用ありがとうございました。");
	}
	public static int checkNumber(int mincmd,int maxcmd){
		boolean check = false;
		int strN = 0;
		do{
			check = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = null;
			try {
				str = br.readLine();
			} catch (IOException e1) {
				System.out.println(e1);
			}
			if(str.equals("z")){
				strN = 0;
				break;
			}
			if(str.equals("0")){
				strN = -1;
				break;
			}
			try{
				strN = Integer.parseInt(str);
			}catch(NumberFormatException e){
				System.out.println("数値を入力してください。");
				check = true;
			}
			if(!check){
				if(mincmd > strN || strN > maxcmd){
					System.out.println(mincmd + "～" + maxcmd + "で指定してください。");
					check = true;
				}else{
					break;
				}
			}
		}while(check);
		return strN;
	}
	public static String findFolder(boolean f){
		int cmd = 0;
		int dirNum = 0;
		int cmdNumber = 1;
		String path = "C:\\";
		do{
			do{
				System.out.println("===================================================");
				System.out.println();
				System.out.println();
				System.out.println("現在のフォルダは"+ path + "です。");
				System.out.println("開きたいフォルダの番号を指定してください。");
				File dir = new File(path);
				System.out.println("");
				System.out.println("--" + path + "内のフォルダ一覧--");
				File[] files1 = dir.listFiles();
				List<File> dirlist = new ArrayList<File>();
				cmdNumber = 1;
				try{
					for (int i = 0; i < files1.length; i++) {
						File file = files1[i];
						if (file.isDirectory() && !file.isHidden() ){
							System.out.println(cmdNumber + "-" + file);
							dirlist.add(file);
							cmdNumber++;
						} 
					}
				}catch(NullPointerException e){
					System.out.println("そのフォルダは開けませんでした。");
					cmd = -1;
					path = "C:\\";
					break;
				}
				List<File> filelist = new ArrayList<File>();
				if(f){
					System.out.println("");
					System.out.println("--" + path + "内のテキストファイル一覧--");
					for (int i = 0; i < files1.length; i++) {
						File file = files1[i];
						if (file.isFile() && file.canWrite()){
							if(file.getName().indexOf(".txt") > -1){
								System.out.println(cmdNumber + "-" + file);
								filelist.add(file);
								cmdNumber++;
							}
						} 
					}
				}
				System.out.println("");
				System.out.println("");
				if(!f){
					System.out.println(cmdNumber + "-フォルダの決定");        	
				}else{
					cmdNumber--;
				}
				System.out.println("0-Cドライブに戻る");
				System.out.println("z-メニューに戻る");
				cmd = checkNumber(0, cmdNumber);
				if(f){
					cmdNumber++;
				}
				dirNum = dirlist.size();
				if(0 < cmd && dirNum >= cmd){
					path += "\\" + dirlist.get(cmd - 1).getName();
				}else if(0 < cmd && cmdNumber > cmd){
					path += "\\" + filelist.get(cmd - 1 - dirNum).getName();
				}else if(cmd == -1){
					path = "C:\\";
				}
			}while(false);
		}while(0 != cmd && cmd <= dirNum);
		if(cmd == 0){
			return "z";
		}else{
			return path;
		}
	}
	public static boolean createFile(String folder){
		boolean b = true;
		boolean o = false;
		do{
				o = false;
				System.out.println("===================================================");
				System.out.println();
				System.out.println();
				System.out.println(folder + "に作成したいファイル名を入力してください。(.tｘｔは必要ありません)");
				System.out.println("z-メニューに戻る");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String str = null;
				try {
					str = br.readLine();
				} catch (IOException e1) {
					System.out.println("ファイルを読み取れませんでした。");
					b = false;
				}
				if(str.equals("z")){
					break;
				}
				File newfile = new File(folder + "\\" + str + ".txt");
				if (newfile.exists()){
				    System.out.println("ファイルは存在します");
				    o = true;
				}else{
					try {
						if(newfile.createNewFile()){
							System.out.println("ファイルの作成に成功しました");
						}else{
							System.out.println("ファイルの作成に失敗しました");
							b = false;
						}
					} catch (IOException e) {
						System.out.println("ファイルの作成に失敗しました");
						b = false;
					}
				}
		}while(o);
		return b;
	}
	public static boolean createFolder(String folder){
		boolean b = true;
		boolean o = false;
		do{
				o = false;	
				System.out.println("===================================================");
				System.out.println();
				System.out.println();
				System.out.println(folder + "に作成したいフォルダ名を入力してください。");
				System.out.println("z-メニューに戻る");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String str = null;
				try {
					str = br.readLine();
				} catch (IOException e1) {
					System.out.println("フォルダ名を読み取れませんでした。");
					b = false;
				}
				if(str.equals("z")){
					break;
				}
				File newfile = new File(folder + "\\" + str);
				if (newfile.exists()){
				    System.out.println("フォルダは存在します");
				    o = true;
				}else{
					if(newfile.mkdir()){
						System.out.println("フォルダの作成に成功しました");
					}else{
						System.out.println("フォルダの作成に失敗しました");
						b = false;
					}
				}
		}while(o);
		return b;
	}
	public static boolean readFile(String file){
		boolean b = true;
		BufferedReader inFile = null;
		System.out.println("===================================================");
		System.out.println();
		System.out.println();
		try {
			inFile = new BufferedReader(new InputStreamReader(new FileInputStream(file),"MS932"));
		} catch (FileNotFoundException e) {
			System.out.println("ファイルを取得できませんでした。");
			b = false;
		} catch (UnsupportedEncodingException e) {
			System.out.println("サポートされていない文字コードです。");
			e.printStackTrace();
			b = false;
		}
		try {
			while (inFile.ready()) { 
				System.out.println(inFile.readLine()); 
			}
			inFile.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("そのファイルは開けませんでした。");
			b = false;
		}
		return b;
	}
	public static boolean updateFile(String file){
		int cmd = 0;
		boolean f = true;
		boolean b = true;
		System.out.println("===================================================");
		System.out.println();
		System.out.println();
		System.out.println("編集方法を指定してください。");
		System.out.println("1-上書き");
		System.out.println("2-追記");
		System.out.println("z-メニューに戻る");
		do{
			cmd = checkNumber(1,2);
			if(cmd == 1){
				f = false;
			}
			if(cmd == 0){
				break;
			}
			File targetfile = new File(file);
			FileWriter filewriter = null;
			try {
				filewriter = new FileWriter(targetfile, f);
			} catch (IOException e) {
				System.out.println("ファイルに書き込めません");
				b = false;
			}
			System.out.println("===================================================");
			System.out.println();
			System.out.println();
			System.out.println("テキストを入力してください。");
			System.out.println("z-メニューに戻る");
			try {
			do{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String str = br.readLine();
				if(str.equals("z")){
					break;
				}
				filewriter.write(str + "\n");
				f = true;
				System.out.println("次の行を入力してください。");
				System.out.println("z-メニューに戻る");
			}while(true);	
				filewriter.close();
			}catch (IOException e) {
				e.printStackTrace();
				b = false;
			}	
		}while(false);
		return b;
	}
	public static boolean deleteFile(String file){
		boolean b = true;
		File newfile = new File(file);
		System.out.println(file + "を削除してもいいですか？");
		System.out.println("1-はい");
		System.out.println("2-いいえ");
		System.out.println("z-メニューに戻る");
		switch(checkNumber(1,2)){
		case 1:
			if(newfile.delete()){
				System.out.println("削除しました。");
			}else{
				System.out.println("削除失敗しました。");
				b = false;
			}
			break;
		case 2:
			b = false;
			break;
		}
		return b;
	}
	public static int selectTarget(boolean f){
		String s = "作成";
		if(!f){
			s = "削除";
		}
		System.out.println("===================================================");
		System.out.println();
		System.out.println();
		System.out.println(s + "対象を選択してください。");
		System.out.println("1-フォルダ");
		System.out.println("2-ファイル");
		System.out.println("z-メニューに戻る");
		return checkNumber(1,2);
	}
}
