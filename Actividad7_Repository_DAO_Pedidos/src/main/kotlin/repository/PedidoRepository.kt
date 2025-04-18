package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class PedidoRepository(private val c: Connection):IPedidoRepository
{
    private val pedidoDAO = PedidoDAO(c)
    private val lineaPedidoDAO = LineaDAO(c)

    override fun insertar(pedido: Pedido, lineas: MutableList<LineaPedido>) {
        pedidoDAO.insertar(pedido)
        for (lineaPedido in lineas) {
            lineaPedidoDAO.insertar(lineaPedido)
        }
    }

    override fun mostrar(idUsuario: Int):MutableList<Map<Pedido, MutableList<LineaPedido>>> {
        val pedidosCompletos = mutableListOf<Map<Pedido, MutableList<LineaPedido>>>()
        val pedidos = pedidoDAO.mostrar(idUsuario)

        for(p in pedidos) {
            val asociacion: Map<Pedido, MutableList<LineaPedido>> = mapOf(Pair(p, lineaPedidoDAO.mostrar(p.id)))
            pedidosCompletos.add(asociacion)
        }
        return pedidosCompletos

    }

    override fun eliminar(pedido: Pedido) {
        lineaPedidoDAO.eliminar(pedido.id)
        pedidoDAO.eliminar(pedido)
    }

}