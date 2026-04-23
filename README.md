#  Bruno’s Boxing

Proyecto desarrollado por las alumnas de 1º DAM **Yoli** y **Dalila**.

# Descripción 

Brunos Boxing es un juego de simulación de combates de boxeo desarrollado en Java. El proyecto está estructurado con Gradle y simula diferentes tipos de rounds, puntuaciones y decisiones durante una pelea.

---

## Características

* Sistema de rounds de boxeo (regular y knockout)
* Puntuación por jueces (ScoreCard)
* Penalizaciones durante el combate
* Generación de rounds mediante fábrica (RoundFactory)
* Arquitectura modular y extensible

---

##  Estructura del proyecto

```
app/
 └── src/main/java/edu/teamrocket/brunosbox
     ├── App.java
     ├── Brunosbox.java
     ├── KnockdownRound.java
     ├── PointsDeducted.java
     ├── RegularRound.java
     ├── Round.java
     ├── RoundFactory.java
     ├── ScoreCard.java
 └── src/test/java/edu/teamrocket/brunosbox
     ├── AppTest.java
     ├── KnockdownRoundTest.java
     ├── PointsDeductedTest.java
     ├── RegularRoundTest.java
     ├── ScoreCardTest.java


---

##  Requisitos

* Java 17 o superior
* Gradle 9.4.1

---

##  Instalación y ejecución

Clona el repositorio:

```bash
git clone <URL_DEL_REPOSITORIO>
cd frank_brunos_boxing
```

Compila el proyecto:

```bash
./gradlew build
```

Ejecuta la aplicación:

```bash
./gradlew run
```

En Windows:

```bash
gradlew.bat run
```

---

##  Tests

Ejecuta las pruebas con:

```bash
./gradlew test
```

El proyecto usa **JUnit Jupiter** para testing.

---

##  Dependencias

* JUnit Jupiter (tests)
* Google Guava
* ASCII Table (`com.github.freva:ascii-table:1.2.0`)

---

##  Arquitectura

El juego está diseñado usando una estructura basada en objetos:

* `Round`: clase base de todos los rounds
* `RegularRound`: round estándar
* `KnockdownRound`: round con posibilidad de caída
* `RoundFactory`: crea rounds dinámicamente
* `ScoreCard`: gestiona puntuaciones
* `PointsDeducted`: maneja penalizaciones

---

## Objetivo del juego

Simular un combate de boxeo donde dos jugadores compiten por puntos a lo largo de varios rounds, incluyendo eventos aleatorios como knockdowns y deducciones de puntos.

---

---

##  Futuras mejoras

* Interfaz gráfica (GUI)
* Sistema de inteligencia artificial para luchadores
* Modo torneo
* Animaciones de combate

---

¡Disfruta del combate! 
