**README.md**

# Word Search Application

## Overview
This Java application is designed to search for a specific word within a directory of files. It utilizes the ForkJoinPool framework to parallelize the search process, making it efficient for large directories.

## Usage
1. Clone the repository to your local machine.
2. Open the `App.java` file in your preferred Java development environment.
3. Set the `MAIN_DIRECTORY` variable to the path of the directory you want to search.
4. Compile and run the `App` class.

## Input
Upon running the application, it will prompt you to enter the word you want to search for.

## Output
The application will then perform a parallelized search for the specified word in the files within the designated directory. It will print the list of files containing the word and display the total number of files found.

## Execution Time
The application measures the time it takes to complete the search and displays the elapsed time in milliseconds.

## Author
Wilkinson