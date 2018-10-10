package jp.co.sss.crud.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * メニュー番号のチェック
 *
 * ＠param menuNum
 */
public class InputCheck {

	public static boolean InputMenuCheck(String menuNum) {

		Pattern p = Pattern.compile("[1-7]");
		Matcher m = p.matcher(menuNum);

		if (m.find()) {
			return true;
		} else {
			System.out.println("半角1以上7以下の整数を入力してください：");
			return false;
		}
	}

	/**
	 * 社員IDのチェック
	 *
	 * ＠param empIdNum
	 */
	public static boolean InputEmpIdCheck(String empIdNum) {

		Pattern p = Pattern.compile("[1-9][0-9][0,4]$");
		Matcher m = p.matcher(empIdNum);

		if (m.find()) {
			return true;
		} else {
			System.out.println("半角1以上99999以下の整数を入力してください：");
			return false;
		}
	}

	/**
	 * 部署IDのチェック
	 *
	 * ＠param empIdNum
	 */
	public static boolean InputDeptIdCheck(String DeptIdNum) {

		Pattern p = Pattern.compile("[1-3]");
		Matcher m = p.matcher(DeptIdNum);

		if (m.find()) {
			return true;
		} else {
			System.out.println("半角1以上3以下の整数を入力してください：");
			return false;
		}

	}

	/**
	 * 性別番号のチェック
	 *
	 * ＠param empIdNum
	 */
	public static boolean InputGenderCheck(String genderNum) {

		Pattern p = Pattern.compile("[1-2]");
		Matcher m = p.matcher(genderNum);

		if (m.find()) {
			return true;
		} else {
			System.out.println("1:(男性) 2:(女性)の整数を入力してください：");
			return false;
		}
	}

	/**
	 * 社員名のチェック
	 *
	 * ＠param empIdNum
	 */
	public static boolean InputEmpNameCheck(String EmpName) {

		if (EmpName.length() > 1 && EmpName.length() <= 20) {
			return true;
		} else {
			System.out.println("1文字以上20文字以内で入力してください：");
			return false;
		}
	}

	/**
	 * @throws 日付けのチェック
	 *
	 *             ＠param dayNum
	 */
	public static boolean InputDayNumCheck(String dayNum) {

		// 検索する文字列を用意
		SimpleDateFormat hbd = new SimpleDateFormat("yyyy/MM/dd");
		hbd.setLenient(false);

		try {
			// 正規表現のパターンを作成
			Pattern p = Pattern.compile("^[0-9]{4}/[0-9]{2}/[0-9]{2}$");
			Matcher m = p.matcher(hbd.format(hbd.parse(dayNum)));

			if (m.find()) {
				return true;
			} else {
				System.out.println("正しい形式（西暦/月/日）で日付を入力してください：");
				return false;
			}
		} catch (ParseException e) {
			System.out.println("正しい形式（西暦/月/日）で日付を入力してください：");
			return false;
		}
	}
}
