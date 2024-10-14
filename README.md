# Tree Fire Propagation

This project simulates the propagation of a fire between trees. A configuration file mention three parameters that will be the input of our application : The size of the grid since trees are in a forest that will be represented by a grid with dimension h * l , the initial burning cells, and the probability of fire propagation.

## Project Structure

- **src/main/java** : Contains the source code of the project.
    - **com.fire.propagation** : Main propagation process.
    - **com.fire.propagation.config** : Contains config file and ConfigurationLoader class that load the configuration file, in other words all classes related to a configuration.
    - **com.fire.propagation.model** : Contains model classes in our case  `PropagationStep`.
    - **com.fire.propagation.persistence** : Contains classes that are used for data persistence.
    - **com.fire.propagation.util** : Contains classes that may be used for generic tasks as our project evolve .
- **resources/config.csv** : CSV file that contains the configuration for grid size, initial burning trees, and fire propagation probability.

## How it works

### stack technique

- **Java 17**
- **Maven 3.8.6**
- **MySQL**

### About

project available on Github :
https://github.com/AymanSassi/FirePropagation/tree/master

### ToDo

a lot of things to do ...
