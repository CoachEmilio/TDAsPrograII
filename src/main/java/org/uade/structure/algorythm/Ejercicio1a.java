package org.uade.structure.algorythm;

/*
Dado un diccionario múltiple junto con un rango de valores (mínimo y máximo),
implementa un metodo que genere un único diccionario múltiple que solo contenga
los valores que estén dentro del rango. La restricción es
sobre los valores, no sobre las claves.
Se pide lo siguiente:
a. Describa la estrategia que va a utilizar.
*/

/*La estrategia que usaré consiste en iterar sobre cada clave del diccionario múltiple original,
// y para cada clave, iterar sobre sus valores. Si un valor está dentro del rango especificado,
// se añadirá a un nuevo diccionario múltiple. Si la clave ya existe en el nuevo diccionario,
// se añadirá el valor a la lista de valores de esa clave. Si la clave no existe, se creará una nueva entrada.
*/

/*
b. Implemente el metodo que reciba dos diccionarios múltiples y se
genere otro.
//mergeDictionaries(MultipleDictionaryADT<K, V> dict1, MultipleDictionaryADT<K, V> dict2).
// El metodo recibirá dos parámetros: el diccionario múltiple original y un rango definido por un mínimo y un máximo.
// El metodo se llamará

c. Genere un metodo que muestre su contenido sin perderlo al realizar la operación.
//generateFilteredDictionary(MultipleDictionaryADT<K, V> original, int min, int max).
// El metodo devolverá un nuevo diccionario múltiple que contiene solo los valores dentro del rango especificado.

//d. Estime los costos de cada uno de sus métodos.

¿Qué hace?: Imprime todas las claves y sus valores.
<hr></hr>
Costos de los métodos
Supongamos:


( n ): cantidad de claves.
( m ): cantidad total de valores (sumando todos los valores de todas las claves).

filtrarPorRango
Costo: ( O(n + m) )
Por qué: Recorre todas las claves (( n )) y todos los valores (( m )).
unirFiltrando
Costo: ( O(n_1 + m_1 + n_2 + m_2) )
Por qué: Filtra ambos diccionarios (( n_1, m_1, n_2, m_2 )), luego recorre todas las claves y valores del segundo filtrado para agregarlos al resultado.
mostrarDiccionario
Costo: ( O(n + m) )
Por qué: Recorre todas las claves y todos los valores para imprimirlos.
<hr></hr>
¿Cómo entender la resolución?
Diccionario múltiple: Piensa en un Map<Integer, List<Integer>>.
Filtrar: Es como hacer un for sobre cada lista de cada clave y solo quedarte con los valores que cumplen la condición.
Unir: Es como juntar dos mapas, sumando los valores de las mismas claves.
Costos: Siempre piensa en cuántas veces recorres claves y valores.
<hr></hr>
Resumen
Iterar: Siempre recorres todas las claves y valores.
Filtrar: Solo agregas los valores que cumplen la condición.
Unir: Combinas los resultados de ambos diccionarios.
Mostrar: Solo recorres e imprimes.
Si entiendes cómo funcionan los bucles sobre claves y valores, y cómo se agregan elementos a un diccionario múltiple, puedes resolver cualquier variante de este ejercicio.
*/

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicMultipleDictionaryADT;

public class Ejercicio1a {
    public static void main(String[] args) {
        // Crear dos diccionarios múltiples con datos sintéticos
        MultipleDictionaryADT dict1 = new DynamicMultipleDictionaryADT();
        MultipleDictionaryADT dict2 = new DynamicMultipleDictionaryADT();

        // Diccionario 1: clave 1 -> 5, 10; clave 2 -> 15, 20
        dict1.add(1, 5);
        dict1.add(1, 10);
        dict1.add(2, 15);
        dict1.add(2, 20);

        // Diccionario 2: clave 1 -> 7, 12; clave 3 -> 18, 25
        dict2.add(1, 7);
        dict2.add(1, 12);
        dict2.add(3, 18);
        dict2.add(3, 25);

        int min = 10, max = 20;

        // Unir y filtrar por rango
        MultipleDictionaryADT resultado = unirFiltrando(dict1, dict2, min, max);

        // Mostrar resultado
        System.out.println("Diccionario resultado (valores entre " + min + " y " + max + "):");
        mostrarDiccionario(resultado);
    }
    public static MultipleDictionaryADT filtrarPorRango(MultipleDictionaryADT dict, int min, int max) {
        MultipleDictionaryADT resultado = new DynamicMultipleDictionaryADT();
        SetADT claves = dict.getKeys();
        while (!claves.isEmpty()) {
            int clave = claves.choose();
            int[] valores = dict.get(clave);
            for (int valor : valores) {
                if (valor >= min && valor <= max) {
                    resultado.add(clave, valor);
                }
            }
            claves.remove(clave);
        }
        return resultado;
    }
    public static MultipleDictionaryADT unirFiltrando(MultipleDictionaryADT dict1, MultipleDictionaryADT dict2, int min, int max) {
        MultipleDictionaryADT resultado = filtrarPorRango(dict1, min, max);
        MultipleDictionaryADT filtrado2 = filtrarPorRango(dict2, min, max);

        SetADT claves2 = filtrado2.getKeys();
        while (!claves2.isEmpty()) {
            int clave = claves2.choose();
            int[] valores = filtrado2.get(clave);
            for (int valor : valores) {
                resultado.add(clave, valor);
            }
            claves2.remove(clave);
        }
        return resultado;
    }
    public static void mostrarDiccionario(MultipleDictionaryADT dict) {
        SetADT claves = dict.getKeys();
        while (!claves.isEmpty()) {
            int clave = claves.choose();
            int[] valores = dict.get(clave);
            for (int valor : valores) {
                System.out.println("Clave: " + clave + " -> Valor: " + valor);
            }
            claves.remove(clave);
        }
    }
}
