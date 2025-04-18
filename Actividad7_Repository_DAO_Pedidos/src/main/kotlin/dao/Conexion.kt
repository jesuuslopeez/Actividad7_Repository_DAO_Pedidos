package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Conexion {
    lateinit var c: Connection
    private val url = "jdbc:h2:./DataBase/mydb"
    private val usuario = "user"
    private val contraseña = "password"

    init {
        try {
            c = DriverManager.getConnection(url, usuario, contraseña)
            println("Conexión correcta")
            crearTablas()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    private fun crearTablas() {
        val tablas = c.createStatement()

        tablas.executeUpdate("DROP TABLE IF EXISTS lineas;")
        tablas.executeUpdate("DROP TABLE IF EXISTS pedidos;")

        tablas.executeUpdate(
            """
            CREATE TABLE IF NOT EXISTS pedidos (
                id INT PRIMARY KEY,
                precioTotal DOUBLE,
                idUsuario INT
            );
            """.trimIndent()
        )

        tablas.executeUpdate(
            """
            CREATE TABLE IF NOT EXISTS lineas (
                id INT PRIMARY KEY,
                cantidad INT,
                precio DOUBLE,
                idPedido INT,
                idProducto INT
            );
            """.trimIndent()
        )

        println("Tablas creadas")
    }

}