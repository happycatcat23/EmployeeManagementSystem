package jp.co.sss.crud.main;

import jp.co.sss.crud.db.DBController;

/**
 * 社員管理システム実行用クラス
 */
public class MainSystem {
	/**
	 * メイン処理
	 *
	 * @param args
	 *            コマンドライン引数
	 */
	public static void main(String[] args) {

		int menuNo = 0;
		String empId = null;
		String deptId = null;
		String gender = null;
		String empName = null;
		String birthday = null;

		try {
			do {
				// メニューの表示
				System.out.println("=== 社員管理システム ===");
				System.out.println("1. 全件表示");
				System.out.println("2. 社員名検索");
				System.out.println("3. 部署ID検索");
				System.out.println("4. 登録");
				System.out.println("5. 更新");
				System.out.println("6. 削除");
				System.out.println("7. 終了");
				System.out.print("メニュー番号を入力してください:");

				// メニュー番号の入力
				String menuNoStr = Inputcontrolar.inputcontrolar(1, 0);
				menuNo = Integer.parseInt(menuNoStr);

				// 機能の呼出
				switch (menuNo) {
				case 1:
					// 全件表示機能の呼出
					DBController.findAll(menuNo, null);
					break;
				case 2:
					// 社員名検索
					System.out.println("社員名を入力してください\t:");
					empName = Inputcontrolar.inputcontrolar(5, 0);
					// 社員名検索機能の呼出
					DBController.findAll(menuNo, empName);
					break;
				case 3:
					// 部署ID検索
					System.out.println("部署ID(1:営業部、2:経理部、3:総務部)を入力してください\t:");
					deptId = Inputcontrolar.inputcontrolar(3, 0);

					// 部署ID検索機能の呼出
					DBController.findAll(menuNo, deptId);
					break;
				case 4:
					// 登録する値を入力
					System.out.print("社員名:");
					empName = Inputcontrolar.inputcontrolar(5, 0);
					System.out.print("性別(1:男性, 2:女性):");
					gender = Inputcontrolar.inputcontrolar(4, 0);
					System.out.print("生年月日(西暦年/月/日):");
					birthday = Inputcontrolar.inputcontrolar(6, 0);
					System.out.print("部署ID(1:営業部、2:経理部、3:総務部):");
					deptId = Inputcontrolar.inputcontrolar(3, 0);

					// 登録機能の呼出
					DBController.insert(empName, gender, birthday, deptId);
					break;
				case 5:
					// 更新
					System.out.println("社員を更新します");
					System.out.println("社員IDを入力してください。");
					empId = Inputcontrolar.inputcontrolar(2, 0);
					boolean resultSelect = DBController.updateSelect(empId);

					if (resultSelect == true) {
						System.out.println("社員名を入力してください。");
						empName = Inputcontrolar.inputcontrolar(5, 1);
						System.out.println("性別を入力してください(男性 : 1、女性 : 2)。");
						gender = Inputcontrolar.inputcontrolar(4, 1);
						System.out.println("生年月日を入力してください(西暦/月/日)。");
						birthday = Inputcontrolar.inputcontrolar(6, 1);
						System.out.println("部署IDを入力してください。");
						deptId = Inputcontrolar.inputcontrolar(3, 1);
						// 更新機能の呼出
						DBController.update(empId, empName, gender, birthday, deptId);

					} else {
						System.out.println("該当する社員は登録されていません。");
					}
					break;
				case 6:
					// 削除
					System.out.println("社員を削除します。");
					System.out.println("社員IDを入力してください。");
					empId = Inputcontrolar.inputcontrolar(2, 0);
					// 削除機能の呼出
					DBController.delete(empId);
					break;
				}

			} while (menuNo != 7);
		} catch (Exception e) {
			System.out.println("システムエラーが発生しました");
			e.printStackTrace();
		}
		System.out.println("システムを終了します。");
	}
}