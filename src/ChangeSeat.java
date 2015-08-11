

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ChangeSeat {

	public static void main(String[] args) {
		// mapにサンプル値代入
		HashMap<String,Member> map = new HashMap<String,Member>();
		
		int m_member_num = 4;
		int w_member_num = 4;
		
		Member[] rightSeat = new Member[(m_member_num + w_member_num) / 2];
		Member[] leftSeat = new Member[m_member_num + w_member_num - rightSeat.length];
		
		//System.out.println(leftSeat.length);
		//System.out.println(rightSeat.length);
		
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
		Member dammy = new Member("0");
		
		//notNullを避けるため、初期値としてmEを代入
		for(int i=0; i<rightSeat.length; i++){
			rightSeat[i] = dammy;
		}
		for(int i=0; i<leftSeat.length; i++){
			leftSeat[i] = dammy;
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
		mC.setLike(wA);
		mD.setLike(wB);
		wA.setLike(mA);
		wB.setLike(mB);
		wC.setLike(mB);
		wD.setLike(mA);
		
		mA.setLiked(2);
		mB.setLiked(2);
		wA.setLiked(1);
		wB.setLiked(1);
		wC.setLiked(2);
		
		//気になるポイント２以上の人を探す
		ArrayList<Member> likedMen = new ArrayList<>();
		for(int i=0; i<men.size(); i++){
			if(men.get(i).getLiked() > 1){
				likedMen.add(men.get(i));
			}
		}
		ArrayList<Member> likedWomen = new ArrayList<>();
		for(int i=0; i<men.size(); i++){
			if(women.get(i).getLiked() > 1){
				likedWomen.add(women.get(i));
			}
		}
		//例の場合は　mA,mB　wC
		
		//気になるポイント２以上の男が２人の場合の処理
		if(likedMen.size() == 2){
			rightSeat[2] = likedMen.get(0);
			likedMen.get(0).setSit(1);
			leftSeat[1] = likedMen.get(1);
			likedMen.get(0).setSit(1);
			
			//気になるポイント２以上の女がいる場合の処理
			if(likedWomen.size() > 0){
				if(likedWomen.get(0).getLike() == likedMen.get(0)){
					rightSeat[1] = likedWomen.get(0);
					likedWomen.get(0).setSit(1);
				}else if(likedWomen.get(0).getLike() == likedMen.get(1)){
					leftSeat[2] = likedWomen.get(0);
					likedWomen.get(0).setSit(1);
				}
				
				//ポイント女の場所が決まっていない場合
				if(likedWomen.get(0).getSit() == 0){
					if(likedMen.get(0).getLike() == likedWomen.get(0)){
						rightSeat[1] = likedWomen.get(0);
						likedWomen.get(0).setSit(1);
					}else if(likedMen.get(1).getLike() == likedWomen.get(0)){
						leftSeat[2] = likedWomen.get(0);
						likedWomen.get(0).setSit(1);
					}
				}
				
				//気になるポイント２以上の女が２人いる場合の処理
				if(likedWomen.size() == 2){
					if(likedWomen.get(1).getLike() == likedMen.get(0)){
						if(rightSeat[1] != dammy){
							rightSeat[1] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}else{
							leftSeat[2] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}
					}else if(likedWomen.get(1).getLike() == likedMen.get(1)){
						if(leftSeat[2] != dammy){
							leftSeat[2] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}else{
							rightSeat[1] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}
					}
					
					//気になるポイント女②の場所が決まっていない場合の処理
					if(likedWomen.get(1).getSit() == 0){
						if(rightSeat[1] != dammy){
							leftSeat[2] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}else if(leftSeat[2] != dammy){
							rightSeat[1] = likedWomen.get(1);
							likedWomen.get(1).setSit(1);
						}else{
							//
						}
					}
				}
				
			}
		}
		
		//気になるポイント２以上の男が１人の場合の処理
		
		//出力
		for(int i=0; i<leftSeat.length; i++){
			System.out.println("左側　"+ leftSeat[i].getName());
		}
		for(int i=0; i<rightSeat.length; i++){
			System.out.println("右側　"+ rightSeat[i].getName());
		}
	}
}
