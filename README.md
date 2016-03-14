# Coursework 3: Contact Manager

The code here is my attempt at an [assignment][] set as coursework for the
['Programming in Java' course][] at [Birkbeck College][], London.

The [assignment][], in brief, is defined as follows:

> The purpose of this assignment it writing a program to keep track of contacts
> and meetings.
>
> The application will keep track of contacts, past and future meetings, etc.
> When the application is closed, all data must be stored in a text file called
> contacts.txt. This file must be read at startup to recover all data
> introduced in a former session (the format of the file if up to you: you can
> use XML, comma-separated values (CSV), or any other format).

[assignment]: https://moodle.bbk.ac.uk/pluginfile.php/474144/mod_resource/content/7/cw3-v1.1.pdf
['Programming in Java' course]: https://www.dcs.bbk.ac.uk/study-with-us/modules/programming-in-java/
[Birkbeck College]: http://www.bbk.ac.uk/

# How to run the tests

To run all tests, including verification tasks (e.g. PMD, FingBugs):

    ./gradlew check

To run unit tests only (faster):

    ./gradlew test
