package ejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class PreparedStatement {
	private static final String HOST = "localhost";
	private static final String BBDD = "zoo";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.println("introduce un nombre de animal");
//		String nombreAnimal = scan.nextLine();

		Class.forName("com.mysql.cj.jdbc.Driver"); // cargar driver
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/zoo", "root", ""); // abrir conexion
//		Statement st = con.createStatement(); //crear el ejecutor de sentencias

		// INSERT INTO animales (nombre) VALUES ('gato')
//		String sentenciaInsert = "INSERT INTO animales (nombre) VALUES ('" + nombreAnimal + "')";
//		st.execute(sentenciaInsert);
		java.sql.PreparedStatement preparedSt = con.prepareStatement("INSERT INTO animales (nombre) VALUES (?)");
		preparedSt.setString(1, "gato");
		preparedSt.execute();

		// UPDATE animales SET nombre='aaaa' WHERE id = 1;
//		String sentenciaUpdate = "UPDATE animales SET nombre='elefante' WHERE id = 2";
//		st.executeUpdate(sentenciaUpdate);
		preparedSt = con.prepareStatement("UPDATE animales SET nombre=? WHERE id = ?");
		preparedSt.setString(1, "elefante");
		preparedSt.setInt(2, 1);
		preparedSt.executeUpdate();

		// DELETE FROM animales WHERE nombre = 'gato'
//		String sentenciaDelete = "DELETE FROM animales WHERE nombre = 'gato'";
//		st.execute(sentenciaDelete);
		preparedSt = con.prepareStatement("DELETE FROM animales WHERE nombre = ?");
		preparedSt.setString(1, "gato");
		preparedSt.execute();

		// SELECT * FROM animales
		String sentenciaSelect = "SELECT * FROM animales";
		Statement st = con.createStatement();
		ResultSet resultado = st.executeQuery(sentenciaSelect);

		while (resultado.next()) {
			System.out.println(resultado.getInt(1) + " - " + resultado.getString(2));
		}

		con.close();

	}

}
