package org.example

import java.sql.Connection
import java.sql.SQLException

class LineaDAO(private val c: Connection) {

    fun insertar(lineaPedido: LineaPedido) {
        try {
            val prepareStatement = c.prepareStatement("INSERT INTO lineas (id, cantidad, precio, idPedido, idProducto) VALUES (?, ?, ?, ?, ?)")
            prepareStatement.setInt(1, lineaPedido.id)
            prepareStatement.setInt(2, lineaPedido.cantidad)
            prepareStatement.setDouble(3, lineaPedido.precio)
            prepareStatement.setInt(4, lineaPedido.idPedido)
            prepareStatement.setInt(5, lineaPedido.idProducto)
            prepareStatement.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun mostrar(idPedido: Int): MutableList<LineaPedido> {
        val lista = mutableListOf<LineaPedido>()
        try {
            val prepareStatement = c.prepareStatement("SELECT * FROM lineas WHERE idPedido = ?")
            prepareStatement.setInt(1, idPedido)
            val resultSet = prepareStatement.executeQuery()
            while (resultSet.next()) {
                val linea = LineaPedido(
                    resultSet.getInt("id"),
                    resultSet.getInt("cantidad"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("idPedido"),
                    resultSet.getInt("idProducto")
                )
                lista.add(linea)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return lista
    }

    fun eliminar(idPedido: Int) {
        try {
            val prepareStatement = c.prepareStatement("DELETE FROM lineas WHERE idPedido = ?")
            prepareStatement.setInt(1, idPedido)
            prepareStatement.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}