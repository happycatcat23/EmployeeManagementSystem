package jp.co.sss.crud.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sss.crud.util.ConstantSQL;

/**
 * データベース操作用クラス
 */
public class DBController {
	/**
	 * 表示と検索
	 *
	 * @throws ClassNotFoundException
	 *             ドライバクラスが存在しない場合に送出
	 * @throws SQLException
	 *             データベース操作時にエラーが発生した場合に送出
	 */
	public static void findAll(int menuNo, String serchState) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();
			// 全件検索
			if (menuNo == 1) {
				// ステートメントを作成
				preparedStatement = connection.prepareStatement(ConstantSQL.SQL_FIND_ALL);
			}
			// 社員名検索
			else if (menuNo == 2) {
				// ステートメントを作成
				preparedStatement = connection.prepareStatement(ConstantSQL.SQL_FIND_EMPNAME);
				// 入力値をバインド
				preparedStatement.setString(1, "%" + serchState + "%");
			}
			// 部署ID検索
			else if (menuNo == 3) {
				// ステートメントを作成
				preparedStatement = connection.prepareStatement(ConstantSQL.SQL_FIND_DEPT_ID);
				// 入力値をバインド
				preparedStatement.setString(1, serchState);
			}

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			// レコードの行数を数えるための変数を用意
			int rowCount = 0;

			// レコードを出力
			System.out.println("\t社員ID\t\t社員名\t\t\t性別\t\t生年月日\t\t部署名\n");
			while (resultSet.next()) {
				System.out.print("\t" + resultSet.getString("emp_id") + "\t");
				System.out.print("\t\t" + resultSet.getString("emp_name") + "\t");

				int gender = Integer.parseInt(resultSet.getString("gender"));
				// 性別名の出力を分岐
				if (gender == 1) {
					System.out.print("\t男性\t");
				} else if (gender == 2) {
					System.out.print("\t女性\t");
				}

				System.out.print("\t" + resultSet.getString("birthday") + "\t");
				System.out.println("\t" + resultSet.getString("dept_name") + "\n");
				// 登録されればカウントを行う
				rowCount++;
			}

			// 行数が0の場合、レコードが登録されていない旨のメッセージを出力
			if (rowCount == 0 && menuNo == 1) {
				System.out.println("社員が登録されていません");
			} else if (rowCount == 0 && (menuNo == 2 || menuNo == 3)) {
				System.out.println("該当する社員は登録されていません。");
			}

			System.out.println("");
		} finally {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 登録
	 *
	 * @param empName
	 *            社員名
	 * @param gender
	 *            性別
	 * @param birthday
	 *            生年月日
	 * @param deptId
	 *            部署ID
	 * @throws ClassNotFoundException
	 *             ドライバクラスが存在しない場合に送出
	 * @throws SQLException
	 *             データベース操作時にエラーが発生した場合に送出
	 */
	public static void insert(String empName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setString(2, gender);
			preparedStatement.setString(3, birthday);
			preparedStatement.setString(4, deptId);

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println("社員情報を登録しました");
		} finally {
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 更新判定
	 *
	 * @param empId
	 *            社員ID
	 * @throws ClassNotFoundException
	 *             ドライバクラスが存在しない場合に送出
	 * @throws SQLException
	 *             データベース操作時にエラーが発生した場合に送出
	 */
	public static boolean updateSelect(String empId) throws SQLException, ClassNotFoundException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();
			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE_SELECT);
			// 入力値をバインド
			preparedStatement.setString(1, empId);
			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
		return false;
	}

	/**
	 * 更新
	 *
	 * @param empName
	 *            社員名
	 * @param gender
	 *            性別
	 * @param birthday
	 *            生年月日
	 * @param deptId
	 *            部署ID
	 * @throws ClassNotFoundException
	 *             ドライバクラスが存在しない場合に送出
	 * @throws SQLException
	 *             データベース操作時にエラーが発生した場合に送出
	 */
	public static void update(String empId, String empName, String gender, String birthday, String deptId)
			throws SQLException, ClassNotFoundException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();
			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);
			// 入力値をバインド
			preparedStatement.setString(5, empId);
			preparedStatement.setString(1, empName);
			preparedStatement.setString(2, gender);
			preparedStatement.setString(3, birthday);
			preparedStatement.setString(4, deptId);
			// SQL文の実行と更新人数の宣言
			int cnt = preparedStatement.executeUpdate();
			System.out.println(cnt + "件のデータを更新しました。");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 削除
	 *
	 * @param empId
	 *            社員ID
	 * @throws ClassNotFoundException
	 *             ドライバクラスが存在しない場合に送出
	 * @throws SQLException
	 *             データベース操作時にエラーが発生した場合に送出
	 */
	public static void delete(String empId) throws SQLException, ClassNotFoundException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();
			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);
			// 入力値をバインド
			preparedStatement.setString(1, empId);
			// SQL文の実行と削除人数の宣言
			int cnt = preparedStatement.executeUpdate();
			// 出力の分岐処理
			if (cnt > 0) {
				System.out.println(cnt + "件のデータを削除しました。");
			} else {
				System.out.println("該当する社員は登録されていません。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);

		}
	}

}