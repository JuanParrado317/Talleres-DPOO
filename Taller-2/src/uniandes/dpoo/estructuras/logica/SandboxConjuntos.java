package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre conjuntos implementados usando un árbol (TreeSet).
 *
 * Todos los métodos deben operar sobre el atributo arbolCadenas que se declara como un NavigableSet.
 *
 * El objetivo de usar el tipo NavigableSet es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (TreeSet).
 *
 * A diferencia de un Set, en un NavigableSet existe una noción de orden que en este caso corresponde al órden lexicográfico.
 *
 * No pueden agregarse nuevos atributos.
 */
public class SandboxConjuntos
{
    /**
     * Un conjunto (set) de cadenas para realizar varias de las siguientes operaciones.
     *
     * Por defecto, los elementos del conjunto están ordenados lexicográficamente.
     */
    private NavigableSet<String> arbolCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxConjuntos( )
    {
        arbolCadenas = new TreeSet<String>( );
    }

    /**
     * Retorna una lista con las cadenas del conjunto ordenadas lexicográficamente
     * @return Una lista con las cadenas ordenadas
     */
    public List<String> getCadenasComoLista( )
    {
        List<String> copia = new ArrayList<String>( );
        for( String cadena : arbolCadenas )
        {
            copia.add( cadena );
        }
        return copia;
    }

    /**
     * Retorna una lista con las cadenas del conjunto, ordenadas lexicográficamente de mayor a menor.
     * @return Una lista con las cadenas ordenadas de mayor a menor
     */
    public List<String> getCadenasComoListaInvertida( )
    {
        List<String> copia = new ArrayList<String>( );
        for( String cadena : arbolCadenas )
        {
            copia.add( cadena );
        }

        List<String> invertida = new ArrayList<String>( );
        for( int i = copia.size( ) - 1; i >= 0; i-- )
        {
            invertida.add( copia.get( i ) );
        }

        return invertida;
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor en el conjunto de cadenas.
     *
     * Si el conjunto está vacío, debe retornar null.
     * @return La primera cadena del conjunto, o null si está vacío.
     */
    public String getPrimera( )
    {
        if( arbolCadenas.isEmpty( ) )
        {
            return null;
        }
        return arbolCadenas.first( );
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor en el conjunto de cadenas
     *
     * Si el conjunto está vacío, debe retornar null.
     * @return La última cadena del conjunto, o null si está vacío.
     */
    public String getUltima( )
    {
        if( arbolCadenas.isEmpty( ) )
        {
            return null;
        }
        return arbolCadenas.last( );
    }

    /**
     * Retorna una colección con las cadenas que hacen parte del conjunto de cadenas y son mayores o iguales a la cadena que se recibe por parámetro
     * @param cadena
     * @return Una colección de cadenas mayores a la cadena dada. Si la cadena hace parte del conjunto, debe hacer parte de la colección retornada.
     */
    public Collection<String> getSiguientes( String cadena )
    {
        List<String> siguientes = new ArrayList<String>( );
        for( String elemento : arbolCadenas )
        {
            if( elemento.compareTo( cadena ) >= 0 )
            {
                siguientes.add( elemento );
            }
        }
        return siguientes;
    }

    /**
     * Retorna la cantidad de valores en el conjunto de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
        return arbolCadenas.size( );
    }

    /**
     * Agrega un nuevo valor al conjunto de cadenas.
     *
     * Este método podría o no aumentar el tamaño del conjunto, dependiendo de si el número está repetido o no.
     *
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
        arbolCadenas.add( cadena );
    }

    /**
     * Elimina una cadena del conjunto de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
        arbolCadenas.remove( cadena );
    }

    /**
     * Elimina una cadena del conjunto de cadenas, independientemente de las mayúsculas o minúsculas
     * @param cadena La cadena que se va eliminar, sin tener en cuenta las mayúsculas o minúsculas
     */
    public void eliminarCadenaSinMayusculasOMinusculas( String cadena )
    {
        String encontrada = null;
        for( String elemento : arbolCadenas )
        {
            if( elemento.toLowerCase( ).equals( cadena.toLowerCase( ) ) )
            {
                encontrada = elemento;
                break;
            }
        }
        if( encontrada != null )
        {
            arbolCadenas.remove( encontrada );
        }
    }

    /**
     * Elimina la primera cadena del conjunto
     */
    public void eliminarPrimera( )
    {
        if( !arbolCadenas.isEmpty( ) )
        {
            String primera = arbolCadenas.first( );
            arbolCadenas.remove( primera );
        }
    }

    /**
     * Reinicia el conjunto de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     *
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarConjuntoCadenas( List<Object> objetos )
    {
        arbolCadenas.clear( );
        for( Object objeto : objetos )
        {
            arbolCadenas.add( objeto.toString( ) );
        }
    }

    /**
     * Modifica el conjunto de cadenas para que todas las cadenas estén en mayúsculas.
     *
     * Note que esta operación podría modificar el órden de los elementos dentro del conjunto.
     */
    public void volverMayusculas( )
    {
        NavigableSet<String> nuevoArbol = new TreeSet<String>( );
        for( String cadena : arbolCadenas )
        {
            nuevoArbol.add( cadena.toUpperCase( ) );
        }
        arbolCadenas = nuevoArbol;
    }

    /**
     * Construye un árbol de cadenas donde todas las cadenas están organizadas de MAYOR a MENOR.
     */
    /**
     * Nota: TreeSet siempre mantiene su propio orden interno según una regla de comparación
     * (natural o invertida). No hay forma de "insertar en orden inverso" y que se quede así,
     * porque TreeSet se reordena solo. Aquí usamos descendingSet(), que da una vista del
     * conjunto en orden invertido, y la copiamos a un TreeSet nuevo (que conserva ese orden).
     */
    public TreeSet<String> invertirCadenas( )
    {
        return new TreeSet<String>( arbolCadenas.descendingSet( ) );
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del conjunto de cadenas
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro del conjunto
     */
    public boolean compararElementos( String[] otroArreglo )
    {
        for( int i = 0; i < otroArreglo.length; i++ )
        {
            if( !arbolCadenas.contains( otroArreglo[ i ] ) )
            {
                return false;
            }
        }
        return true;
    }

}
