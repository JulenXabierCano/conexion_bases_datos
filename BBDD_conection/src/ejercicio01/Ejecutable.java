package ejercicio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Ejecutable {
	public static void ejecutar() throws SQLException, ClassNotFoundException {
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
				
				break;
			case MODIFICAR_INFORMACION:
				
				break;
			case VISUALIZAR:
				
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
