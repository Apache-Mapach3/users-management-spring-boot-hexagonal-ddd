package com.jcaa.usersmanagement;
import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;

public class Main {

  public static void main(String[] args) {
    try {
      // Instanciamos el contenedor manualmente
      DependencyContainer container = new DependencyContainer();

      // Obtenemos el entrypoint
      // UserController controller = container.userController();
      // UserManagementCli cli = new UserManagementCli(controller);
      // cli.run();

      System.out.println("Aplicación iniciada con Inyección de Dependencias Manual");

    } catch (Exception e) {
      System.err.println("Error fatal al iniciar la aplicación: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}