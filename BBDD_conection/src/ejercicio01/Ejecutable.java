package ejercicio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Ejecutable {
	public static void ejecutar() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/julenDB", "root", "");
		Statement st = con.createStatement();
		PreparedStatement insertar = con.prepareStatement(
				"insert into arboles (nombre_comun,nombre_cientifico,habitat,altura,origen) values (?,?,?,?,?)");
		PreparedStatement eliminar = con.prepareStatement("DELETE FROM arboles WHERE nombre_cientifico = ?");
		PreparedStatement modificar = con.prepareStatement("UPDATE arboles SET ?=? WHERE nombre_cientifico=?");

		final int INSERTAR_DATOS = 1;
		final int ELIMINAR_DATOS = 2;
		final int MODIFICAR_INFORMACION = 3;
		final int VISUALIZAR = 4;
		final int SALIR = 0;
		int opcion_menu;
		do {
			opcion_menu = Integer.parseInt(JOptionPane.showInputDialog(null,
					"------MENU-------\n" + INSERTAR_DATOS + ". Insertar lineas\n" + ELIMINAR_DATOS
							+ ". Eliminar lineas\n" + MODIFICAR_INFORMACION + ". Modificar lineas\n" + VISUALIZAR
							+ ". Mostrar lineas\n" + SALIR + ". Salir\n" + "Elije una de las opciones"));
			// fin menú
			switch (opcion_menu) {
			case INSERTAR_DATOS:
				insertar.setString(1, JOptionPane.showInputDialog(null, "Introduzca nombre comun del arbol"));
				insertar.setString(2, JOptionPane.showInputDialog(null, "Introduzca nombre científico del arbol"));
				insertar.setString(3, JOptionPane.showInputDialog(null, "Introduzca habitat del arbol"));
				insertar.setInt(4, Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca altura del arbol")));
				insertar.setString(5, JOptionPane.showInputDialog(null, "Introduzca origen del arbol"));
				insertar.execute();
				break;

			case ELIMINAR_DATOS:
				eliminar.setString(1,
						JOptionPane.showInputDialog(null, "Introduzca nombre científico del arbol a borrar"));
				eliminar.execute();
				break;

			case MODIFICAR_INFORMACION:
				int opcion = 0;
				opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Introduzca dato a modificar\n" + "1 - Nombre comun\n" + "2 - Nombre cientifico\n"
								+ "3 - Habitat\n" + "4 - Altura\n" + "5 - Origen\n" + "0 - salir\n"));

				switch (opcion) {
				case 1:
					modificar.setString(3,
							JOptionPane.showInputDialog(null, "Introduzca nombre cientifico del arbol a modificar"));
					modificar.setString(2, JOptionPane.showInputDialog(null, "Introduzca nuevo nombre comun"));
					modificar.setString(1, "nombre_comun");
					modificar.executeUpdate();
					break;

				case 2:
					modificar.setString(3,
							JOptionPane.showInputDialog(null, "Introduzca nombre cientifico del arbol a modificar"));
					modificar.setString(2, JOptionPane.showInputDialog(null, "Introduzca nuevo nombre cientifico"));
					modificar.setString(1, "nombre_cientifico");
					modificar.executeUpdate();
					break;

				case 3:
					modificar.setString(3,
							JOptionPane.showInputDialog(null, "Introduzca nombre cientifico del arbol a modificar"));
					modificar.setString(2, JOptionPane.showInputDialog(null, "Introduzca nuevo nombre comun"));
					modificar.setString(1, "habitat");
					modificar.executeUpdate();
					break;

				case 4:
					modificar.setString(3,
							JOptionPane.showInputDialog(null, "Introduzca nombre cientifico del arbol a modificar"));
					modificar.setString(2, JOptionPane.showInputDialog(null, "Introduzca nueva altura"));
					modificar.setString(1, "altura");
					modificar.executeUpdate();
					break;

				case 5:
					modificar.setString(3,
							JOptionPane.showInputDialog(null, "Introduzca nombre cientifico del arbol a modificar"));
					modificar.setString(2, JOptionPane.showInputDialog(null, "Introduzca nuevo origen"));
					modificar.setString(1, "origen");
					modificar.executeUpdate();
					break;
				}

				break;
			case VISUALIZAR:

				String sentenciaSelect = "SELECT * FROM arboles";
				ResultSet resultado = st.executeQuery(sentenciaSelect);
				String mensaje = "";
				while (resultado.next()) {
					mensaje += resultado.getInt(1) + " - " + resultado.getString(2) + " - " + resultado.getString(3)
							+ " - " + resultado.getString(4) + " - " + resultado.getString(5) + "\n";
				}
				JOptionPane.showMessageDialog(null, mensaje);
				break;
			case SALIR:
				JOptionPane.showMessageDialog(null, "Adios");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcion incorrecta");
			}
		} while (opcion_menu != SALIR);

		con.close();
	}
}