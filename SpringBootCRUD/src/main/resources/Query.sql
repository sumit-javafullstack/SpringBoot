create database emp_db;
create database stud_db;
show databases;
use emp_db;
show tables;
create table Employee (empId int,empName varchar(255), empSalary numeric,empDob date,empStatus boolean);
create table Student (studId int primary key, studName varchar(255), studMarks numeric,studDob datetime, studBranch varchar(255));
ALTER TABLE Employee
ADD CONSTRAINT  PRIMARY KEY (empId);
desc Employee;

---------------------------------------------------------------------------------------------------
use stud_db;
create table Student (studId int primary key, studName varchar(255), studMarks numeric,studDob datetime, studBranch varchar(255));
desc Student;
create user 'fsdspid'@'localhost' identified by 'password';
grant all privileges on *.*  to 'fsdspid'@'localhost';


create table student ( id int, sname varchar(255), marks numeric, result boolean);