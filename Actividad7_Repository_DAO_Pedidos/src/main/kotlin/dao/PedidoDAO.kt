package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class PedidoDAO(private val c: Connection)
{
    fun insertar(pedido: Pedido) {
        try {
            val prepareStatement = c.prepareStatement("INSERT INTO pedidos (id, precioTotal, idUsuario) VALUES (?, ?, ?)")
            prepareStatement.setInt(1, pedido.id)
            prepareStatement.setDouble(2, pedido.precioTotal)
            prepareStatement.setInt(3, pedido.idUsuario)
            prepareStatement.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun mostrar(idUsuario: Int):MutableList<Pedido> {
        val lista = mutableListOf<Pedido>()
        try {
            val prepareStatement = c.prepareStatement("SELECT * FROM pedidos WHERE idUsuario = ?")
            prepareStatement.setInt(1, idUsuario)
            val resultSet = prepareStatement.executeQuery()
            while (resultSet.next()) {
                val pedido = Pedido(
                    resultSet.getInt("id"),
                    resultSet.getDouble("precioTotal"),
                    resultSet.getInt("idUsuario")
                )
                lista.add(pedido)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return lista

    }

    fun eliminar(pedido: Pedido) {
        try {
            val prepareStatement = c.prepareStatement("DELETE FROM pedidos WHERE id = ?")
            prepareStatement.setInt(1, pedido.id)
            prepareStatement.executeUpdate()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}
