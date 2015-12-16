import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class KisoKadai2 {
	
	public static void main(String[] args) throws Exception {
		Random rnd = new Random();
		int answer = rnd.nextInt(99) + 1;
		//System.out.println(answer);
		System.out.println("数当てゲームです。1～100の範囲で数値を入力してください。");
        int maxNumber = 100;
        int minNumber = 1;
		int inputNumber = checkNumber(maxNumber, minNumber);
		int answerTimes = 1;
		do{
			if(inputNumber < answer){
				System.out.println("残念！もっと大きい数です。");
				System.out.println("現在の候補は" + (inputNumber + 1) + "～" + maxNumber + "です。");
				minNumber = inputNumber + 1;
				inputNumber = checkNumber(maxNumber, minNumber);
			}else if(inputNumber > answer){
				System.out.println("残念！もっと小さい数です。");
				System.out.println("現在の候補は" + minNumber + "～" + (inputNumber - 1) + "です。");
				maxNumber = inputNumber - 1;
				inputNumber = checkNumber(maxNumber, minNumber);
			}else{
				System.out.println("正解！あなたは" + answerTimes + "回で当てました。");
				break;
			}
			answerTimes++;
		}while(true);
	}
	public static int checkNumber(int maxNumber, int minNumber) throws Exception{
		boolean check = false;
		int strN = 0;
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
				if(minNumber > strN || strN > maxNumber){
					System.out.println("現在の候補は" + minNumber + "～" + maxNumber + "です。");
					check = true;
				}else{
					break;
				}
			}
		}while(check);
		
		return strN;
	}
}
