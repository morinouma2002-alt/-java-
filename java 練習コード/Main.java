import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    String date;
    String category;
    int amount;
    String memo;
}

class Food{
	int food;
	
	public static void exFood(ArrayList<Transaction>a1){
		int food =0;
		for(int i=0;i<a1.size();i++){
			if(a1.get(i).category.equals("食費")){
				food+=a1.get(i).amount;
			}
			}
			System.out.println("食費の合計は:"+food+"円です。");
	}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Transaction> a1 = new ArrayList<>();

        int count = sc.nextInt();

        for (int i = 0; i < count; i++) {
            Transaction t = new Transaction();

            t.date = sc.next();
            t.category = sc.next();
            t.amount = sc.nextInt();
            t.memo = sc.next();

            a1.add(t);
        }
		
		
		Food f1 =new Food(a1);
		a1.exFood();

        int sum = 0;
        int income = 0;
        int expense = 0;

        for (int i = 0; i < a1.size(); i++) {
            sum += a1.get(i).amount;

            if (a1.get(i).amount >= 0) {
                income += a1.get(i).amount;
            } else if (a1.get(i).amount < 0) {
                expense += a1.get(i).amount;
            }
        }

        System.out.println("収入:"+income+"円");
        System.out.println("支出:"+Math.abs(expense)+"円");
		System.out.println("残高:"sum+"円");

        sc.close();
    }
}
