use DB_A6B8BC_javavirus
	
	
	DROP TABLE Record
	DROP TABLE InfectedPerson
	DROP TABLE Business
	DROP TABLE Person

	/*CREATE TABLES*/
	CREATE TABLE Person (
		UserID INT NOT NULL PRIMARY KEY,
		FirstName VARCHAR(20) NOT NULL,
		LastName VARCHAR(20) NOT NULL,
		Email VARCHAR(100) NOT NULL,
		PhoneNumber BIGINT CHECK (PhoneNumber > 0 AND PhoneNumber < 10000000000) NOT NULL,
		AgeCategory SMALLINT CHECK (AgeCategory >= 0 AND AgeCategory <= 3) NOT NULL
	)

	CREATE TABLE Business (
		BusinessID VARCHAR(8) NOT NULL PRIMARY KEY,
		Name VARCHAR(40) NOT NULL,
		SPACE FLOAT NOT NULL,
		BusinessType SMALLINT CHECK (BusinessType >= 0 AND BusinessType <= 4) NOT NULL 
	)

	CREATE TABLE InfectedPerson (
		UserID INT FOREIGN KEY REFERENCES Person
	)

	CREATE TABLE Record (
		BusinessID VARCHAR(8) FOREIGN KEY REFERENCES Business,
		UserID INT FOREIGN KEY REFERENCES Person,
		MaskType SMALLINT CHECK (MaskType >= 0 AND MaskType <= 3) NOT NULL,
		EntryDate SMALLDATETIME NOT NULL,
		ExitDate SMALLDATETIME NOT NULL
	)