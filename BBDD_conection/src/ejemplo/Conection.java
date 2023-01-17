package ejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Conection {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/julenDB","root","");
		
		String nuevoAnimal = JOptionPane.showInputDialog(null, "Introduzca tipo de animal a añadir en la base de datos");
		
		Statement st = con.createStatement();
		st.execute("INSERT INTO animales (nombre) VALUES ('"+nuevoAnimal+"')");
		//INSERT INTO animales (ID, nombre) VALUES ('gato');
		
		con.close();
	}
}
