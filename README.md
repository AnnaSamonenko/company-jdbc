# Subject:
Company develops several projects.
Several employees can work on one project.
One employee can work only on one project at a time, but he/she can switch to other projects while staying in the company.
Each project is managed by one Project Manager (PM).
One PM also can manage only one project at a time.
PMs are assigned to the project from the project start date.

Information available in database:
 1. Employees
    * name
    * contact information
    * projects they worked on and appropriate positions
    * start/end dates for each project they worked on
 2. Projects
    * title
    * description
    * start/end date of the project

Use Java, JDBC, Mysql to write a program that will solve tasks listed below.
1. Create database to represent all the data.
2. Fill the database with several records generated randomly.
3. Find the PM of the project with the highest count of java developers and print out his/her name and contact info.
4. List all the test engineers that currently work at the company, sorted by count of projects they participated in.
