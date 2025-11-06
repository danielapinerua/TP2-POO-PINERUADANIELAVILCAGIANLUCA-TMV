package logicLayer;

import javax.swing.JOptionPane;

public abstract class Validar {
	
	public static int validarNumero(String mensaje) {	
		boolean flag;
		String ingreso;
		int numero;
		do {
			flag = true;
			do {
				ingreso = JOptionPane.showInputDialog(mensaje);
			} while (ingreso.isEmpty());
			for (int i = 0; i < ingreso.length(); i++) {
				//si caracter no es un digito
				if (!Character.isDigit(ingreso.charAt(i))) {
					flag = false;
					break;
				}
			}
	        if (flag) {
	            numero = Integer.parseInt(ingreso);
	            if (numero < 0) {
	                flag = false;
	            }
	        }
	        
		} while (flag==false);
		return Integer.parseInt(ingreso);
	}
	
	public static String validarLetras(String mensaje) {	
		boolean flag;
		String ingreso;
		do {
			flag = true;
				ingreso = JOptionPane.showInputDialog(mensaje);
				
		        if (ingreso.isEmpty()) {
		            flag = false;
		        } else {
		            for (int i = 0; i < ingreso.length(); i++) {
		                if (!Character.isAlphabetic(ingreso.charAt(i))) {
		                    flag = false;
		                    break;
		                }
		            }
		        }
		    
		} while (flag==false);
		return ingreso;
		}
	
	public static String validarCampo(String mensaje) {
		boolean flag;
		String ingreso;
		do {
			flag = true;
				ingreso = JOptionPane.showInputDialog(mensaje);
				
		        if (ingreso.isEmpty()) {
		       
		            flag = false;
		        }
		      
		    
		} while (flag==false);
		return ingreso;
		
		
	}
}
