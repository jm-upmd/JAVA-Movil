package telefonos;

import java.util.*;
import telefonos.Movil;

public class Telefonos {
	
	public static void main(String[] args) {

		// Variables temporales para recoger los datos introducidos
		String numTelefono, imei, marca, so, operador, permanencia, usuario;
		numTelefono = imei = marca = so = operador = permanencia = usuario = null;

		
		boolean datoOK = false;
		
		final String TXT_TERMINAR = "\n(o TERMINAR para salir del programa)";
		
		String[] mes = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
				"octubre", "noviembre", "diciembre" };
		
		Scanner teclado = new Scanner(System.in);

		// Recogida y validación de datos para Numero de telefono

		while (!datoOK) {
			System.out.println("\nIntroduce número de teléfono:" + TXT_TERMINAR);
			numTelefono = teclado.nextLine();
			checkTerminar(numTelefono);
			if (numTelefono.length() != 9) {
				System.out.print(" Su número no es correcto\n");
			} else if (esNumeroEntero(numTelefono)) {
				datoOK = true;
			} else {
				System.out.println("\nIntroduzca sólo números\n");

			}
		}

		// Recogida y validadión IMEI - 15 digitos numericos
		datoOK = false;
		while (!datoOK) {
			System.out.println("\nIntroduce IMEI:" + TXT_TERMINAR);
			imei = teclado.nextLine();
			checkTerminar(imei);
			if (imei.length() != 15)
				System.out.print(" IMEI no es correcto");
			else if (esNumeroEntero(imei))
				datoOK = true;
			else
				System.out.println("\nIntroduzca sólo números");
		}

		// Recogida operador, sistema operativo y marca del teléfono.
		// Aquí no hay restricciones ni formatos que validar.

		System.out.println("\nIntroduce la marca de tu teléfono:" + TXT_TERMINAR);
		marca = teclado.nextLine();
		checkTerminar(marca);

		System.out.println("\nIntroduce sistema operativo:" + TXT_TERMINAR);
		so = teclado.nextLine();
		checkTerminar(so);

		System.out.println("\nIntroduce operador:" + TXT_TERMINAR);
		operador = teclado.nextLine();
		checkTerminar(operador);

		// Recogida y validación del DNI usuario.
		// No solo vamos a validar que el formato del número es el correcto sino que además
		// vamos a verificar si la letra realmente corresponde al número del dni (ya hicmos
		// ejercicio que hacía esto)

		datoOK = false;
		while (!datoOK) {
			
			System.out.println("\nIntroduce DNI usuario:" + TXT_TERMINAR);
			usuario = teclado.nextLine();
			checkTerminar(usuario);
			if (usuario.length() != 9) // si itiene mas de 9 digitos no tiene el formato de DNI
				System.out.println("Formato incorrecto de DNI. Debe tener 9 dígitos.");
			else if (esNumeroEntero(usuario.substring(0, 8))) { // Si los 8 primeros caracteres son numéricos
				// Si el noveno caracter es la letra que corresponde al número
				// del dni, entonces la validacion es correcta
				
				// Obtengo la letra de dni correcta que corresponde el número del dni.
				// El método LetraDNI recibe el número del dni como tipo numérico long.
				String letraOKdni = letraDNI(Long.parseLong(usuario.substring(0, 8))); 
				
				// Extraigo la letra del dni introducido por consola convirtiendola en mayúscula
				String letraDniEntrada = usuario.substring(8).toUpperCase();
				
				// Comparo si letra correcta calculada y la letra introducida coinciden
				if (letraDniEntrada.equals(letraOKdni)) {
					datoOK = true;
				} else {
					System.out.println("La letra del DNI no es correcta");
				}
			} else {
				System.out.println("DNI debe estar formado por 8 números y una letra.");
			}
		}

		// Recogida y validación de permanencia numero entre 0 y 48
		datoOK = false;
		while (!datoOK) {
			System.out.println("\nIntroduce meses de permanencia:" + TXT_TERMINAR);
			permanencia = teclado.nextLine();
			checkTerminar(permanencia);
			if (esNumeroEntero(permanencia)) { // si es numérico
				int meses = Integer.parseInt(permanencia);
				if (meses >= 0 && meses <= 48) { // si esta entre 0 y 48 meses
					datoOK = true; // Recogida y validación correcta
				} else {
					System.out.println("Número de meses no valido. Introduzca valores de 0 a 48 meses");
				}

			} else {
				System.out.println("\nDato no válido. Introduzca un número entre 0 y 48");

			}
		}

		
		// Si se ha llegado hasta aquí sin haber salido antes del programa
		// es que todas las validaciones han sido ok.
		// Ahora creo el objeto con el constructor que recibe todos los parametros

		Movil mov = new Movil(Integer.parseInt(numTelefono), Long.parseLong(imei), marca, so, operador,
				Integer.parseInt(permanencia), usuario);

		// Imprime por pantalla los valores del objeto mov

		System.out.println("\nResumen características de su móvil:\n" + mov.toString());

		

		// Bucle que simula el paso de 12 meses y hace portabilidad a Vodafone todos los
		// meses pares
		final int MESES_SIMULACION = 12; // Usando una constante queda mas legible
		System.out.println("\n\nRealizando simulación de " + MESES_SIMULACION + " meses");
		for (int i = 0; i < MESES_SIMULACION; i++) {
			System.out.println("-------------------------------------");
			System.out.println("Mes: " + mes[i]);
			mov.pasaunMes(); // Primero hacemos pasar un mes.
			if ((i + 1) % 2 == 0) {
				System.out.println("Mes par");
				if (mov.portabilidad()) { // Si es mes par y ha podido hacer portabilidad
					mov.setPermanencia(12); // Establece permanencia a 12 meses
					System.out.println("Portabilidad realizada");
				} else { // Si mes es par pero no ha podido hacer la portabilidad
					System.out.println("La portabilidad no ha podido realizarse");
					System.out.println("Aún le quedan " + mov.getPermanencia() + " meses de permanencia");
				}
			} else {
				System.out.println("Mes impar. No intento hacer portabilidad.");
			}
		} // fin for

		System.out.println("\n...Simulación finalizada\n\n");

		System.out.println("La nueva información del teléfono es:\n" + mov.toString());
		teclado.close();
	} // fin main()


	// Método que comprueba si el string del parámetro formal representa un número.
	private static boolean esNumeroEntero(String str) {
		try {
			@SuppressWarnings("unused") // Esto es para que no de warning por no usar d (no es obligatorio)
			long d = Long.parseLong(str); // utilizamos entero de mayor rango (long).
		} catch (NumberFormatException e) {  // Si parseLong no puede convertirlo se produce una excepción
			return false;                    // de tipo NumberFormatException. En cuyo caso devolvemos false
		}
		return true; // La conversión ha ido OK. Devolvemos true.
	}

	// @@jm: Método que te calcula la letra de un dni. Ya lo hicimos en un ejercicio
	// de clase

	private static String letraDNI(long numDNI) {
		final String STRING_ASOCIACION_NIF = "TRWAGMYFPDXBNJZSQVHLCKE";
		int pos = (int) (numDNI % 23);
		return STRING_ASOCIACION_NIF.substring(pos, pos + 1);

	}

	// Chequea si el parámetro de entra es "TERMINAR" en cuyo caso termina la ejecución del programa.
	private static void checkTerminar(String st) {
		if (st.toUpperCase().equals("TERMINAR")) {
			System.out.println("\n...Programa finalizado.");
			System.exit(0);
		}

	}

}
