package ejercicio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Ejecutable {
	public static void ejecutar() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/julenDB", "root", "");
		Statement st = con.createStatement();

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
				st.execute("INSERT INTO arboles (nombre_comun,nombre_cientifico,habitat,altura,origen) VALUES ('"
						+ JOptionPane.showInputDialog(null, "Introduzca nombre comun del arbol") + "','"
						+ JOptionPane.showInputDialog(null, "Introduzca nombre científico del arbol") + "','"
						+ JOptionPane.showInputDialog(null, "Introduzca habitat del arbol") + "','"
						+ JOptionPane.showInputDialog(null, "Introduzca altura del arbol") + "','"
						+ JOptionPane.showInputDialog(null, "Introduzca origen del arbol") + "')");
				break;
			case ELIMINAR_DATOS:
				st.execute("DELETE FROM arboles WHERE nombre_cientifico = '"
						+ JOptionPane.showInputDialog(null, "Introduzca nombre científico del arbol a borrar") + "'");

				break;
			case MODIFICAR_INFORMACION:

				String opcion = "";
				int id_arbol = 1;
				do {

					id_arbol = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca ID a modificar"));
					if (id_arbol==0) break;

					opcion = JOptionPane.showInputDialog(null,
							"Introduzca dato a modificar\n" + "1 - Nombre comun\n" + "2 - Nombre cientifico\n"
									+ "3 - Habitat\n" + "4 - Altura\n" + "5 - Origen\n" + "0 - salir\n");
					switch (opcion) {
					case "1":// cambiar nombre comun
						st.executeUpdate("UPDATE arboles SET nombre_comun='"
								+ JOptionPane.showInputDialog(null, "Introduzca nuevo nombre comun para el árbol")
								+ "' WHERE id = '" + id_arbol + "'");
						break;
					case "2":// cambiar nombre cientifico
						st.executeUpdate("UPDATE arboles SET nombre_comun='"
								+ JOptionPane.showInputDialog(null, "Introduzca nuevo nombre cientifico para el árbol")
								+ "' WHERE id = '" + id_arbol + "'");
						break;
					case "3":// cambiar habitat
						st.executeUpdate("UPDATE arboles SET nombre_comun='"
								+ JOptionPane.showInputDialog(null, "Introduzca nuevo habitat para el árbol")
								+ "' WHERE id = '" + id_arbol + "'");
						break;
					case "4":// cambiar altura
						st.executeUpdate("UPDATE arboles SET nombre_comun='"
								+ JOptionPane.showInputDialog(null, "Introduzca nuevo altura para el árbol")
								+ "' WHERE id = '" + id_arbol + "'");
						break;
					case "5":// cambiar origen
						st.executeUpdate("UPDATE arboles SET nombre_comun='"
								+ JOptionPane.showInputDialog(null, "Introduzca nuevo nombre para el árbol")
								+ "' WHERE id = '" + id_arbol + "'");
						break;
					case "0":
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opcion incorrecta");
						break;
					}
				} while (!opcion.equals("0"));

				break;
			case VISUALIZAR:

				String sentenciaSelect = "SELECT * FROM arboles";
				ResultSet resultado = st.executeQuery(sentenciaSelect);
				String mensaje = "";
				while (resultado.next()) {
					mensaje += resultado.getInt(1) + " - " + resultado.getString(2) + " - " + resultado.getString(3)
							+ " - " + resultado.getString(4) + " - " + resultado.getString(5)+"\n";
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