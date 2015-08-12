

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ChangeSeat {

	public static void main(String[] args) {
		// mapにサンプル値代入
		HashMap<String,Member> map = new HashMap<String,Member>();
		
		int m_member_num = 4;
		int w_member_num = 4;
		int member_num = m_member_num + w_member_num;
		
		Member[] rightSeat = new Member[7];
		Member[] leftSeat = new Member[7];
		
		System.out.println(leftSeat.length);
		System.out.println(rightSeat.length);
		
		//10人分のメンバインスタンス生成。
		Member mA = new Member("mA");
		Member mB = new Member("mB");
		Member mC = new Member("mC");
		Member mD = new Member("mD");
		Member mE = new Member("0");
		Member wA = new Member("wA");
		Member wB = new Member("wB");
		Member wC = new Member("wC");
		Member wD = new Member("wD");
		Member wE = new Member("wE");
		Member empty = new Member("0");
		
		//初期値としてemptyを代入
		for(int i=0; i<rightSeat.length; i++){
			rightSeat[i] = empty;
		}
		for(int i=0; i<leftSeat.length; i++){
			leftSeat[i] = empty;
		}
		
		
		ArrayList<Member> men = new ArrayList<>();
		ArrayList<Member> women = new ArrayList<>();
		
		men.add(mA);
		men.add(mB);
		men.add(mC);
		if (m_member_num > 3) {
			men.add(mD);
			if (m_member_num > 4){
				men.add(mE);
			}
		}
		women.add(wA);
		women.add(wB);
		women.add(wC);
		if (w_member_num > 3) {
			women.add(wD);
			if (w_member_num > 4){
				women.add(wE);
			}
		}
		
		//サンプル値代入
		mA.setLike(wC);
		mB.setLike(wC);
		mC.setLike(wB);
		mD.setLike(wA);
		wA.setLike(mB);
		wB.setLike(mC);
		wC.setLike(mB);
		wD.setLike(mC);
		
		//気になるポイント２以上の人を探す
		ArrayList<Member> likedMen = new ArrayList<>();
		for(int i=0; i<men.size(); i++){
			if(men.get(i).getLiked() > 1){
				likedMen.add(men.get(i));
			}
		}
		ArrayList<Member> likedWomen = new ArrayList<>();
		for(int i=0; i<women.size(); i++){
			if(women.get(i).getLiked() > 1){
				likedWomen.add(women.get(i));
			}
		}
		
		//↓↓↓↓↓①ポイント２以上の人を全て配置する。
		
		//気になるポイント２以上の男が２人の場合の処理
		if(likedMen.size() == 2){
			rightSeat[3] = likedMen.get(0);
			likedMen.get(0).setSit(1);
			leftSeat[2] = likedMen.get(1);
			likedMen.get(0).setSit(1);
			
			//気になるポイント２以上の女がいる場合の処理
			if(likedWomen.size() > 0){
				
				//ポイント女①の気になる人がポイント男の場合
				if(likedWomen.get(0).getLike() == likedMen.get(0)){
					rightSeat[2] = likedWomen.get(0);
					likedWomen.get(0).setSit(1);
				}else if(likedWomen.get(0).getLike() == likedMen.get(1)){
					leftSeat[3] = likedWomen.get(0);
					likedWomen.get(0).setSit(1);
				}
				
				//ポイント女①の場所が決まっていない場合
				if(likedWomen.get(0).getSit() == 0){
					//ポイント男の気になる人がポイント女①の場合
					if(likedMen.get(0).getLike() == likedWomen.get(0)){
						rightSeat[2] = likedWomen.get(0);
						likedWomen.get(0).setSit(1);
					}else if(likedMen.get(1).getLike() == likedWomen.get(0)){
						leftSeat[3] = likedWomen.get(0);
						likedWomen.get(0).setSit(1);
					}
				}
				
				//気になるポイント２以上の女が２人いる場合の処理
				if(likedWomen.size() == 2){
					//ポイント女①の席が決まっている場合
					if(rightSeat[2] != empty){
						leftSeat[3] = likedWomen.get(1);
						likedWomen.get(1).setSit(1);
					}else if(leftSeat[3] != empty){
						rightSeat[2] = likedWomen.get(1);
						likedWomen.get(1).setSit(1);
					}
					//ポイント女①の席が決まっていない場合
					else {
						//ポイント女②の気になる人がポイント男の場合
						if(likedWomen.get(1).getLike() == rightSeat[3]){
							rightSeat[2] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}else if(likedWomen.get(1).getLike() == leftSeat[2]){
							leftSeat[3] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}
						//ポイント女②の席が決まっていない場合の処理
						else if(likedMen.get(0).getLike() == likedWomen.get(1)){
							rightSeat[2] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}else{
							leftSeat[3] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}
					}
				}
				//ポイント女①の席が決まっていない場合、強制的に配置
				if(likedWomen.get(0).getSit() == 0){
					if(leftSeat[3] == empty){
						leftSeat[3] = likedWomen.get(0);
						likedWomen.get(0).setSit(1);
					}else{
						rightSeat[2] = likedWomen.get(0);
						likedWomen.get(0).setSit(1);
					}
				}
			}
		}
		
		//気になるポイント２以上の男が１人の場合の処理
		if(likedMen.size() == 1){
			leftSeat[2] = likedMen.get(0);
			likedMen.get(0).setSit(1);
			
			//気になるポイント２以上の女がいる場合の処理
			if(likedWomen.size() > 0){
				//ポイント女①の気になる人がポイント男の場合 または、ポイント男がポイント女①を好きな場合
				if(likedWomen.get(0).getLike() == leftSeat[2] || leftSeat[2].getLike() == likedWomen.get(0)){
					leftSeat[3] = likedWomen.get(0);
					likedWomen.get(0).setSit(1);
				}else{
					rightSeat[2] = likedWomen.get(0);
					likedWomen.get(0).setSit(1);
				}
				
				//ポイント女が２人いる場合の処理
				if(likedWomen.size() == 2){
					if(leftSeat[3] == empty){
						leftSeat[3] = likedWomen.get(1);
						likedWomen.get(1).setSit(1);
					}else{
						rightSeat[2] = likedWomen.get(1);
						likedWomen.get(1).setSit(1);
					}
				}
			}
		}
		
		//気になるポイント２以上の男がいない場合の処理
		if(likedMen.size() == 0){
			if(likedWomen.size() > 0){
				leftSeat[3] = likedWomen.get(0);
				likedWomen.get(0).setSit(1);
				if(likedWomen.size() == 2){
					rightSeat[2] = likedWomen.get(1);
					likedWomen.get(1).setSit(1);
				}
			}
		}
		
		//↑↑↑↑ここまでで①気になるポイント２以上の人の配置完了
		
		//↓↓↓↓②気になるポイント１の人を中心から順に配置していく
		
		
		
		//出力
		for(int i=1; i<6; i++){
			System.out.println("左側　"+ leftSeat[i].getName() + "　　右側　" + rightSeat[i].getName());
		}
	}
}
