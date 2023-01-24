package ejercicio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Ejecutable {
	public static void ejecutar() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/julenDB", "root", "");
		Statement st = con.createStatement();
		PreparedStatement insertar = con.prepareStatement(
				"insert into arboles (nombre_comun,nombre_cientifico,habitat,altura,origen) values (?,?,?,?,?)");
		PreparedStatement eliminar = con.prepareStatement("DELETE FROM arboles WHERE id = ?");
		PreparedStatement modificar = con.prepareStatement("UPDATE arboles SET ?=? WHERE ID=?");

		final int CREAR_ARBOL = 1;
		final int ELIMINAR_ARBOL = 2;
		final int MODIFICAR_ARBOL = 3;
		final int VISUALIZAR = 4;
		final int SALIR = 0;
		int opcion_menu;
		do {
			opcion_menu = Integer.parseInt(JOptionPane.showInputDialog(null,
					"------MENU-------\n" + CREAR_ARBOL + ". Insertar lineas\n" + ELIMINAR_ARBOL + ". Eliminar lineas\n"
							+ MODIFICAR_ARBOL + ". Modificar lineas\n" + VISUALIZAR + ". Mostrar lineas\n" + SALIR
							+ ". Salir\n" + "Elije una de las opciones"));
			// fin menú
			switch (opcion_menu) {
			case CREAR_ARBOL:
				Arbol crear_arbol = new Arbol(
						Integer.parseInt(JOptionPane.showInputDialog(null, "Inserta ID del arbol")),
						JOptionPane.showInputDialog(null, "Introduzca nombre comun del arbol"),
						JOptionPane.showInputDialog(null, "Introduzca nombre científico del arbol"),
						JOptionPane.showInputDialog(null, "Introduzca habitat del arbol"),
						Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca altura del arbol")),
						JOptionPane.showInputDialog(null, "Introduzca origen del arbol"));
				insertar.setString(1, crear_arbol.getNombre_comun());
				insertar.setString(2, crear_arbol.getNombre_cientifico());
				insertar.setString(3, crear_arbol.getHabitat());
				insertar.setInt(4, crear_arbol.getId());
				insertar.setString(5, crear_arbol.getOrigen());
				insertar.execute();

				break;

			case ELIMINAR_ARBOL:
				Arbol eliminar_arbol = new Arbol();
				eliminar_arbol
						.setId(Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca ID del arbol a borrar")));
				eliminar.setInt(1, eliminar_arbol.getId());
				eliminar.execute();
				break;

			case MODIFICAR_ARBOL:
				int opcion = 0;
				opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Introduzca ID del arbol que va a modificar"));
				Arbol modificar_arbol = new Arbol(0,
						JOptionPane.showInputDialog(null, "Introduzca nuevo nombre comun del arbol"),
						JOptionPane.showInputDialog(null, "Introduzca nuevo nombre científico del arbol"),
						JOptionPane.showInputDialog(null, "Introduzca nuevo habitat del arbol"),
						Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca nueva altura del arbol")),
						JOptionPane.showInputDialog(null, "Introduzca nuevo origen del arbol"));
				
//				UPDATE arboles SET nombre_comun=?,nombre_cientifico=?,habitat=?,altura=?,origen=? WHERE ID=?
				
				modificar.setString(1, modificar_arbol.getNombre_comun());
				modificar.setString(2, modificar_arbol.getNombre_cientifico());
				modificar.setString(3, modificar_arbol.getHabitat());
				modificar.setInt(4, modificar_arbol.getAltura());
				modificar.setString(5, modificar_arbol.getOrigen());
				modificar.setInt(6, opcion);
				modificar.execute();
				break;
			case VISUALIZAR:

				String sentenciaSelect = "SELECT * FROM arboles";
				ResultSet resultado = st.executeQuery(sentenciaSelect);
				String mensaje = "";
				ArrayList<Arbol> arboles = new ArrayList<Arbol>();
				while (resultado.next()) {
					Arbol arbol = new Arbol(
							resultado.getInt(1),
							resultado.getString(2),
							resultado.getString(3),
							resultado.getString(4),
							resultado.getInt(5),
							resultado.getString(6)
							);
					JOptionPane.showMessageDialog(null, arbol);
					arboles.add(arbol);
				}
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