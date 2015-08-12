

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DoChangeSeat {

	public static void main(String[] args) {
		// mapにサンプル値代入
		HashMap<String,Member> map = new HashMap<String,Member>();
		
		Globals globals = new Globals();
		
		globals.m_member_num = 4;
		globals.w_member_num = 4;
		
		
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

		
		//サンプル値代入
		mA.setLike(wC);
		mB.setLike(wC);
		mC.setLike(wB);
		mD.setLike(wA);
		wA.setLike(mB);
		wB.setLike(mA);
		wC.setLike(mB);
		wD.setLike(mC);
		
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
	
		ChangeSeat changeSeat = new ChangeSeat(globals);
		changeSeat.doChange();
		
	}
}
