

import java.util.ArrayList;

public class Globals {
	// グローバルに使用する変数
	ArrayList<String> nameList = new ArrayList<>();
	int m_member_num;
	int w_member_num;
	ArrayList<Member> memberList = new ArrayList<>();

	/**
	 * global変数を初期化します
	 */
	public void GlobalsAllInit() {
		if (this.nameList.size() != 0) {
			this.nameList.clear();
		}

	}
}