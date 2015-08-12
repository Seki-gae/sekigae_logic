import java.util.ArrayList;

public class ChangeSeat {
	
	Globals globals;
	Member[] rightSeat = new Member[7];
	Member[] leftSeat = new Member[7];
	int[] seatOrderMen = {3,2,1,4,5};
	int[] seatOrderWomen = {3,2,1,4,5};
	int m_member_num;
	int w_member_num;
	Member empty = new Member("0");
	ArrayList<Member> likedMen = new ArrayList<>();
	ArrayList<Member> likedWomen = new ArrayList<>();
	
	public ChangeSeat(Globals globals){
		this.globals = globals;
	}

	public void doChange(){

		Member mA = globals.memberList.get(0);
		Member mB = globals.memberList.get(1);
		Member mC = globals.memberList.get(2);
		Member mD = globals.memberList.get(3);
		Member mE = globals.memberList.get(4);
		Member wA = globals.memberList.get(5);
		Member wB = globals.memberList.get(6);
		Member wC = globals.memberList.get(7);
		Member wD = globals.memberList.get(8);
		Member wE = globals.memberList.get(9);
		
		m_member_num = globals.m_member_num;
		w_member_num = globals.w_member_num;		

		//初期値としてemptyを代入
		for(int i=0; i<rightSeat.length; i++){
			rightSeat[i] = empty;
		}
		for(int i=0; i<leftSeat.length; i++){
			leftSeat[i] = empty;
		}

		int member_num = m_member_num + w_member_num;

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



		//気になるポイント２以上の人を探す
		
		for(int i=0; i<men.size(); i++){
			if(men.get(i).getLiked() > 1){
				likedMen.add(men.get(i));
			}
		}
		
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
		
		//出力
		System.out.println("＊＊＊①終了時の配置＊＊＊");
		for(int i=1; i<6; i++){
			System.out.println("左側　"+ leftSeat[i].getName() + "　　右側　" + rightSeat[i].getName());
		}

		//↑↑↑↑ここまでで①気になるポイント２以上の人の配置完了

		//↓↓↓↓②気になるポイント１の人を中心から順に配置していく
		
		//気になるポイント１の人を格納
		likedMen.clear();
		for(int i=0; i<men.size(); i++){
			if(men.get(i).getLiked() == 1){
				likedMen.add(men.get(i));
			}
		}
		likedWomen.clear();
		for(int i=0; i<women.size(); i++){
			if(women.get(i).getLiked() == 1){
				likedWomen.add(women.get(i));
			}
		}
		
		//ポイント１の人の配置処理を３回繰り返す
		for(int trial=0; trial<3; trial++){
			setPoint1Member();
		}
		
		setPoint1MemberRandom();
		
		//ポイント１の人の配置処理を３回繰り返す
		for(int trial=0; trial<3; trial++){
			setPoint1Member();
		}
		
		setPoint1MemberRandom();
		
		//ポイント１の人の配置処理を３回繰り返す
		for(int trial=0; trial<3; trial++){
			setPoint1Member();
		}
		
		//出力
		System.out.println("＊＊＊②終了時の配置＊＊＊");
		for(int i=1; i<6; i++){
			System.out.println("左側　"+ leftSeat[i].getName() + "　　右側　" + rightSeat[i].getName());
		}
		
		//↑↑↑↑ここまでで②気になるポイント１以上の人の配置完了


		//出力
		System.out.println("＊＊＊最終的な配置＊＊＊");
		for(int i=1; i<6; i++){
			System.out.println("左側　"+ leftSeat[i].getName() + "　　右側　" + rightSeat[i].getName());
		}
	}

	//隣の席の人が自分のことを好き、または自分が隣の席の人を好きな場合trueを返すメソッド
	public boolean checkLike(Member member, Member[] seat, int where){
		if(member.getLike() == seat[where-1] || member.getLike() == seat[where+1]){
			return true;
		}else if(seat[where-1].getLike() == member || seat[where+1].getLike() == member){
			return true;
		}else{
			return false;
		}
	}
	
	//ポイント１の人の配置処理
	public void setPoint1Member() {
		//ポイント１男を配置
		for(int i=0; i<likedMen.size(); i++){
			if(likedMen.get(i).getSit() == 0){
				for(int j=0; j<m_member_num; j++){
					if(j%2 == 0 && rightSeat[seatOrderMen[j]] == empty){
						if(checkLike(likedMen.get(i), rightSeat, seatOrderMen[j])){
							rightSeat[seatOrderMen[j]] = likedMen.get(i);
							likedMen.get(i).setSit(1);
							break;
						}
					}
					if(j%2 == 1 && leftSeat[seatOrderMen[j]] == empty){
						if(checkLike(likedMen.get(i), leftSeat, seatOrderMen[j])){
							leftSeat[seatOrderMen[j]] = likedMen.get(i);
							likedMen.get(i).setSit(1);
							break;
						}
					}
				}
			}
		}

		//ポイント１女を配置
		for(int i=0; i<likedWomen.size(); i++){
			if(likedWomen.get(i).getSit() == 0){
				for(int j=0; j<w_member_num; j++){
					if(j%2 == 1 && rightSeat[seatOrderWomen[j]] == empty){
						if(checkLike(likedWomen.get(i), rightSeat, seatOrderWomen[j])){
							rightSeat[seatOrderWomen[j]] = likedWomen.get(i);
							likedWomen.get(i).setSit(1);
							break;
						}
					}
					if(j%2 == 0 && leftSeat[seatOrderWomen[j]] == empty){
						if(checkLike(likedWomen.get(i), leftSeat, seatOrderWomen[j])){
							leftSeat[seatOrderWomen[j]] = likedWomen.get(i);
							likedWomen.get(i).setSit(1);
							break;
						}
					}
				}
			}
		}
	}
	
	//ポイント１の人の適当配置処理
	void setPoint1MemberRandom(){
		//真ん中が空いていればポイント１の人を配置
		boolean selected = false;
		for(int i=0; i<likedMen.size(); i++){
			if(selected) break;
			if(likedMen.get(i).getSit() == 0){
				for(int j=0; j<3; j++){
					if(j%2 == 0 && rightSeat[seatOrderMen[j]] == empty){
						rightSeat[seatOrderMen[j]] = likedMen.get(i);
						likedMen.get(i).setSit(1);
						selected = true;
						break;
					}
					if(j%2 == 1 && leftSeat[seatOrderMen[j]] == empty){
						leftSeat[seatOrderMen[j]] = likedMen.get(i);
						likedMen.get(i).setSit(1);
						selected = true;
						break;
					}
				}
			}
		}
		for(int i=0; i<likedWomen.size(); i++){
			if(selected) break;
			if(likedWomen.get(i).getSit() == 0){
				for(int j=0; j<3; j++){
					if(j%2 == 1 && rightSeat[seatOrderWomen[j]] == empty){
						rightSeat[seatOrderWomen[j]] = likedWomen.get(i);
						likedWomen.get(i).setSit(1);
						selected = true;
						break;
					}
					if(j%2 == 0 && leftSeat[seatOrderWomen[j]] == empty){
						leftSeat[seatOrderWomen[j]] = likedWomen.get(i);
						likedWomen.get(i).setSit(1);
						selected = true;
						break;
					}
				}
			}
		}
	}

}
