# UADE: Algoritmos Y Estructuras De Datos II - Programación II

**License:** MIT

Este repositorio contiene todo lo necesario para poder seguir la materia desde el inicio hasta el final.

## Tabla de contenidos

- [Requisitos](#requisitos)
- [Comprobar requisitos](#comprobar-requisitos)
- [Estándares de código](#estándares-de-código)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Resumen de TDAs incluidos](#resumen-de-tdas-incluidos)

## Requisitos

Para poder realizar el curso necesitas tener instalado en tu computadora las siguientes herramientas:

- Java
- Maven
- Git

Si no tienes algunas de estas herramientas instaladas en tu computadora, sigue las instrucciones en la documentación oficial de cada herramienta o los videos creados como guía para esta materia.

## Comprobar requisitos

Verifica si todo funciona bien ejecutando los siguientes comandos:

**Java:**
```sh
java -version
```
Ejemplo de salida:
```
openjdk 21.0.2 2024-01-16
OpenJDK Runtime Environment GraalVM CE 21.0.2+13.1 (build 21.0.2+13-jvmci-23.1-b30)
OpenJDK 64-Bit Server VM GraalVM CE 21.0.2+13.1 (build 21.0.2+13-jvmci-23.1-b30, mixed mode, sharing)
```

**Maven:**
```sh
mvn --version
```
Ejemplo de salida:
```
Apache Maven 3.8.3
Maven home: /usr/share/maven
```

**Git:**
```sh
git --version
```
Ejemplo de salida:
```
git version 2.34.1
```

## Estándares de código

Java tiene una serie de reglas definidas para desarrollar algo, ya sea una aplicación o una librería, y que todos los desarrolladores usen los mismos criterios. Existen las siguientes formas de validar si se está siguiendo el formato correcto:

- **Code conventions:** Documento creado en 1997 para definir los estándares de código que debería seguir cualquier desarrollador que usa Java.
- **SonarLint:** Plugin que ayuda a que el código fuente siga los estándares de Java y reduzca los bugs. Tiene soporte para IntelliJ, Eclipse y Visual Studio Code.

## Estructura del proyecto

El proyecto tiene la siguiente estructura:

```
src/
  └── main
      └── java
          └── org
              └── uade
                  ├── structure
                  │   ├── algorythm
                  │   ├── definition
                  │   ├── exception
                  │   ├── implementation
                  │   │   ├── dynamic
                  │   │   │   └── DynamicLlLinkedList
                  │   │   └── fixed
                  │   │       └── StaticLlLinkedList
                  │   ├── node
                  │   └── utils
                  └── resources
```

## Resumen de TDAs incluidos

El proyecto incluye las siguientes implementaciones de Tipos Abstractos de Datos (TDAs):

- **Listas enlazadas:** LinkedListADT, DynamicLinkedListADT, StaticLinkedListADT, LlLinkedListADT
- **Pilas:** StackADT, DynamicStackADT, StaticStackADT
- **Colas:** QueueADT, DynamicQueueADT, StaticQueueADT
- **Conjuntos:** SetADT, DynamicSetADT, StaticSetADT, StringSetADT
- **Diccionarios simples y múltiples:** SimpleDictionaryADT, MultipleDictionaryADT, DynamicSimpleDictionaryADT, StaticSimpleDictionaryADT, DynamicMultipleDictionaryADT, StaticMultipleDictionaryADT
- **Colas de prioridad:** PriorityQueueADT, DynamicPriorityQueueADT, StaticPriorityQueueADT, PriorityNodeLinkedListADT
- **Árboles binarios:** BinaryTreeADT, DynamicBinaryTree
- **Grafos:** GraphADT, DynamicGraphADT
- **Nodos auxiliares:** Node, LlNode, PNodeDLinkedList, PriorityNode, EdgeNode, GraphNode
- **Excepciones personalizadas para los TDAs**

Cada TDA cuenta con su respectiva interfaz y, en la mayoría de los casos, implementaciones tanto dinámicas como estáticas.
