

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DoChangeSeat {

	public static void main(String[] args) {
		// mapにサンプル値代入
		HashMap<String,Member> map = new HashMap<String,Member>();
		
		Globals globals = new Globals();
		
		int m_member_num = 5;
		int w_member_num = 5;
		
		
		//10人分のメンバインスタンス生成。
		Member mA = new Member("mA");
		Member mB = new Member("mB");
		Member mC = new Member("mC");
		Member mD = new Member("mD");
		Member mE = new Member("mE");
		Member wA = new Member("wA");
		Member wB = new Member("wB");
		Member wC = new Member("wC");
		Member wD = new Member("wD");
		Member wE = new Member("wE");

		
		//サンプル値代入
		mA.setLike(wA);
		mB.setLike(wA);
		mC.setLike(wA);
		mD.setLike(wA);
		mE.setLike(wA);
		
		wA.setLike(mA);
		wB.setLike(mA);
		wC.setLike(mA);
		wD.setLike(mA);
		wE.setLike(mA);
		
		
		globals.memberList.add(mA);
		globals.memberList.add(mB);
		globals.memberList.add(mC);
		globals.memberList.add(mD);
		globals.memberList.add(mE);
		globals.memberList.add(wA);
		globals.memberList.add(wB);
		globals.memberList.add(wC);
		globals.memberList.add(wD);
		globals.memberList.add(wE);
	
		ChangeSeat changeSeat = new ChangeSeat();
		changeSeat.setMemberList(globals.memberList);
		changeSeat.setMemberNum(m_member_num, w_member_num);
		changeSeat.doChange();
		
		ArrayList<Member> leftSeat = changeSeat.getLeftSeat();
		ArrayList<Member> rightSeat = changeSeat.getRightSeat();
		
		//出力
		System.out.println("＊＊＊最終的な配置＊＊＊");
		if(leftSeat.size() > rightSeat.size()){
			for(int i=0; i<leftSeat.size(); i++){
				if(i<rightSeat.size()){
					System.out.println("左側　"+ leftSeat.get(i).getName() + "　　右側　" + rightSeat.get(i).getName());
				}else{
					System.out.println("左側　"+ leftSeat.get(i).getName());
				}
			}
		}else{
			for(int i=0; i<rightSeat.size(); i++){
				if(i<leftSeat.size()){
					System.out.println("左側　"+ leftSeat.get(i).getName() + "　　右側　" + rightSeat.get(i).getName());
				}else{
					System.out.println("          右側　"+ rightSeat.get(i).getName());
				}
			}
		}
		
	}
}
