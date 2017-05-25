create database GLBank;

use GLBank;

create table Employees (idEmp INTEGER AUTO_INCREMENT, 
						firstname VARCHAR(20) not null, 
						lastname VARCHAR(20) not null, 
						email VARCHAR(20) not null unique, 
						position CHAR(1) not null default 'C',
						PRIMARY KEY (idEmp));
						
create table LoginEmployee (id INTEGER AUTO_INCREMENT,
							idEmp INTEGER,
							login VARCHAR(15),
							password VARCHAR(60),
							PRIMARY KEY (id),
							FOREIGN KEY (idEmp) REFERENCES Employees(idEmp) ON DELETE CASCADE ON UPDATE RESTRICT);
							
create table HistoryLoginEmployee (id INTEGER AUTO_INCREMENT,
								   idEmp INTEGER,
								   loginDate DATETIME,
								   PRIMARY KEY (id),
								   FOREIGN KEY (idEmp) REFERENCES Employees(idEmp) ON DELETE CASCADE ON UPDATE RESTRICT);

create table Clients (idc INTEGER AUTO_INCREMENT,
					 firstname VARCHAR(20) NOT NULL,
					 lastname VARCHAR(20) NOT NULL,				 
					 disable CHAR(1) DEFAULT 'F',
					 PRIMARY KEY (idc));
					 
create table ClientDetails (idcd INTEGER AUTO_INCREMENT,
							idc INTEGER NOT NULL,
							street VARCHAR(30) NOT NULL,
							housenumber INTEGER NOT NULL,
							postcode CHAR(5) NOT NULL,
							dob DATE NOT NULL,
							city VARCHAR(40) NOT NULL,
							email VARCHAR(30),
							PRIMARY KEY(idcd),
							FOREIGN KEY (idc) REFERENCES Clients(idc) ON DELETE CASCADE ON UPDATE RESTRICT);
							
create table LoginClient (idlc INTEGER AUTO_INCREMENT,
						  idc INTEGER NOT NULL,
						  login VARCHAR(20) NOT NULL UNIQUE,
						  password VARCHAR(60) NOT NULL,
						  blocked CHAR(1) DEFAULT 'F',
						  PRIMARY KEY (idlc),
						  FOREIGN KEY (idc) REFERENCES Clients(idc) ON DELETE CASCADE ON UPDATE RESTRICT);
						  
create table Accounts (idacc BIGINT,
					   idc INTEGER NOT NULL,
					   balance FLOAT(10,2),
					   PRIMARY KEY (idacc),
					   FOREIGN KEY (idc) REFERENCES Clients(idc) ON DELETE CASCADE ON UPDATE RESTRICT);						  
					   
create table BankTransactions (idbt INTEGER AUTO_INCREMENT,
							   amount FLOAT(10,2) NOT NULL,
							   transdatetime DATETIME DEFAULT CURRENT_TIMESTAMP,
							   description VARCHAR(140),
							   idemp INTEGER DEFAULT 0,
							   srcacc BIGINT NOT NULL,
							   destacc BIGINT NOT NULL,
							   srcbank INTEGER NOT NULL,
							   destbank INTEGER NOT NULL,
							   PRIMARY KEY (idbt),
							   FOREIGN KEY (idemp) REFERENCES Employees(idEmp));		

create table CashTransactions (idct INTEGER AUTO_INCREMENT,
							   idemp INT NOT NULL,
							   amount FLOAT(10,2) DEFAULT 0,
							   idacc BIGINT NOT NULL,
							   cashdatetime DATETIME DEFAULT CURRENT_TIMESTAMP,
							   PRIMARY KEY (idct),
							   FOREIGN KEY (idemp) REFERENCES Employees(idemp),
							   FOREIGN KEY (idacc) REFERENCES Accounts(idacc));						   
							   
create table BankCards (idcard INTEGER AUTO_INCREMENT,
						cardnumber BIGINT UNIQUE NOT NULL,
						idacc BIGINT NOT NULL,
						blocked CHAR(1) DEFAULT 'F',
						pincode INTEGER NOT NULL DEFAULT 0,
						incorrect_pincode_attempts INTEGER DEFAULT 0,
						PRIMARY KEY (idcard),
						FOREIGN KEY (idacc) REFERENCES Accounts(idacc) ON DELETE CASCADE ON UPDATE RESTRICT);	

create table Atmwithdrawals (idatmw INTEGER AUTO_INCREMENT,
							 amount FLOAT(10,2),
							 atmdatetime DATETIME DEFAULT CURRENT_TIMESTAMP,
							 idAtm INTEGER,
							 idcard INTEGER,
							 PRIMARY KEY (idatmw),
							 FOREIGN KEY (idcard) REFERENCES BankCards(idcard) ON DELETE CASCADE ON UPDATE RESTRICT);							 