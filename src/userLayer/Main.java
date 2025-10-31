package userLayer;

import javax.swing.JOptionPane;

import logicLayer.Cuenta;

public class Main {

	public static void main(String[] args) {
		
		String[] opciones = {"Login", "Registro", "Salir"};
		int opcion;
		
		do {
			
			opcion = JOptionPane.showOptionDialog(null, "elija opcion", null, 0, 0, null, opciones, opciones[0]);
			
			
			switch (opcion) {
			
			case 0:
				if(Cuenta.getCuentas().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay cuentas creadas");
				} else {
					
					
					String email = JOptionPane.showInputDialog(null, "Ingrese mail");
					String pin = JOptionPane.showInputDialog(null, "Ingrese pin");
					
					Cuenta logueada = Cuenta.login(email, pin);
					if(logueada == null) {
						JOptionPane.showMessageDialog(null, "No pudo ingresar");
					} else { 
						
						JOptionPane.showMessageDialog(null, "Bienvenido" + logueada);
						
						
						
						//ejemplos profe para guiarnos despues
						//logueada.transferencia(100);
						//logueda.transferencia(200);
						//JOptionPane.showMessageDialog(null, "Movimientos" + logueada.getMovimientos());

						
						
					}

					
				}
				
				
				break;
				
				
			case 1:
				Cuenta.registrarse();
				
				
				break;
				
				
			case 2:
				
				JOptionPane.showMessageDialog(null, "Gracias por usar el cajero!");
				break;
				
				
			
			}
			
		} while(opcion !=2);
		
		
		
	}

}

