package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Inputcontrolar {

	public static String inputcontrolar(int num, int num2) throws IOException {

		boolean bl = false;
		String inputStr = null;

		do {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			inputStr = br.readLine();

			switch (num) {
			case 1:
				// メニューナンバーチェック機能の呼び出し
				bl = InputCheck.InputMenuCheck(inputStr);
				break;
			case 2:
				// 社員名チェック機能の呼び出し
				bl = InputCheck.InputEmpNameCheck(inputStr);
				break;
			case 3:
				// 部署IDチェック機能の呼び出し
				if (num2 == 1) {
					// 更新の場合
					if (inputStr.isEmpty()) {
						bl = true;

					} else {
						bl = InputCheck.InputDeptIdCheck(inputStr);

					}
				} else {
					// 登録・検索の場合
					bl = InputCheck.InputDeptIdCheck(inputStr);

				}
				break;

			case 4:
				// 性別番号チェック機能の呼び出し
				if (num2 == 1) {
					// 更新の場合
					if (inputStr.isEmpty()) {
						bl = true;

					} else {
						bl = InputCheck.InputGenderCheck(inputStr);

					}
				} else {
					// 登録・検索の場合
					bl = InputCheck.InputGenderCheck(inputStr);
				}
				break;

			case 5:
				// 文字列チェック機能の呼び出し
				if (num2 == 1) {
					// 更新の場合
					if (inputStr.isEmpty()) {
						bl = true;

					} else {
						bl = InputCheck.InputEmpNameCheck(inputStr);

					}
				} else {
					// 登録・検索の場合
					bl = InputCheck.InputEmpNameCheck(inputStr);

				}
				break;
			case 6:
				// 日付けチェック機能の呼び出し
				if (num2 == 1) {
					// 更新の場合
					if (inputStr.isEmpty()) {
						bl = true;

					} else {
						bl = InputCheck.InputDayNumCheck(inputStr);

					}
				} else {
					// 登録・検索の場合
					bl = InputCheck.InputDayNumCheck(inputStr);

				}
				break;
			default:
				break;
			}
		} while (bl == false);
		return inputStr;
	}

}
