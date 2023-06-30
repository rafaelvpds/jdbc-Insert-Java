package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;

		PreparedStatement ps = null;
		try {
			conn = DB.getConnection();

			ps = conn.prepareStatement(
					"INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			// COMANDOS PARA TROCAR OS ? PELOS VALORES QUE EU QUERO

			ps.setString(1, "Rafael");
			ps.setString(2, "rafael@gmail.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("09/11/1990").getTime()));
			ps.setDouble(4, 10000);
			ps.setInt(5, 4);
			// o resultado desta operação e um numero inteiro indicando quantas linhas foram
			// alteradas
			int rowsAffect = ps.executeUpdate();

			if (rowsAffect > 0) {
				// pegar o cd do novo registro inserido
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					// quero o valor da primeira coluna
					int id = rs.getInt(1);

					System.out.println("Done! Id=" + id);

				}
			} else {

				System.out.println("no rows affect");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}

	}

}
