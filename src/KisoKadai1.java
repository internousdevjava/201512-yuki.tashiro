import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KisoKadai1 {
	
	public static void main(String[] args) throws Exception {
		System.out.println("九九表を作ります。");
		String s = "x";
		int x = checkNumber(s);
		s = "y";
		int y = checkNumber(s);
		int keta = (int)(Math.log(x * y)/Math.log(10)) + 1;
		int yKeta = (int)(Math.log(y)/Math.log(10)) + 1;
		line(y, keta, yKeta, "=");
		checkKeta(yKeta,1);
		System.out.print(" |");
		for(int i = 0; i <= x; i++){
			if(i != 0){
				checkKeta(yKeta,i);
				System.out.print(i + "|");
			}
			for(int j = 1; j <= y; j++){
				if(i == 0){
					checkKeta(keta, j);
					System.out.print(j);
				}else{
					checkKeta(keta, i*j);
					System.out.print(i*j);
				}
			}
			System.out.println("");
			if(i == 0){
				line(y, keta, yKeta, "-");
			}
		}
		line(y, keta, yKeta, "=");	
	}
	public static int checkNumber(String s) throws Exception{
		boolean check = false;
		int strN = 0;
		System.out.println(s + "の出力最大値を設定してください。（最大値：100）");
		do{
			check = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = br.readLine();
			try{
				strN = Integer.parseInt(str);
			}catch(NumberFormatException e){
				System.out.println("数値を入力してください。");
				check = true;
			}
			if(!check){
				if(1 > strN || strN > 100){
					System.out.println("1～100の範囲で入力してください。");
					check = true;
				}else{
					break;
				}
			}
		}while(check);
		
		return strN;
	}
	public static void checkKeta(int keta, int number){
		int numberketa = (int)(Math.log(number)/Math.log(10)) + 1;
		int space = keta - numberketa;
		if(number == 1000){
			space--;
		}
		for(int i = 0; i <= space; i++){
			System.out.print(" ");
		}
	}
	public static void line(int y, int keta, int yKeta, String a){
		for(int k = 0; k < y * (keta + 1) + (yKeta + 3); k++){
			System.out.print(a);
		}
		System.out.println("");
	}
}