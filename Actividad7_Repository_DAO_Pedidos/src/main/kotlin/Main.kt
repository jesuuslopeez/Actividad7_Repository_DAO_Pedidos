package org.example

data class Pedido(val id: Int, val precioTotal: Double, val idUsuario: Int)
data class LineaPedido(val id: Int, val cantidad: Int, val precio:Double, val idPedido: Int, val idProducto:Int)


fun main() {
    val conexion = Conexion()

    if (conexion.c.isValid(10)) {
        conexion.c.use { conn ->
            val repo = PedidoRepository(conn)

            val pedido = Pedido(1, 20.0, 99)
            val lineas = mutableListOf(
                LineaPedido(1, 2, 5.0, 1, 101),
                LineaPedido(2, 1, 15.0, 1, 102)
            )

            repo.insertar(pedido, lineas)

            val pedidos = repo.mostrar(99)

            for (p in pedidos) {
                println(p.keys.first())
                for (l in p.values.first()) {
                    println(l)
                }
            }
        }
    } else {
        println("Error de conexión")
    }
}