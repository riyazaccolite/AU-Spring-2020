CREATE DATABASE AUAsstDB;

USE AUAsstDB2;

CREATE TABLE Resume (
	id int NOT NULL AUTO_INCREMENT,
	person_name varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	phone varchar(15),
	address varchar(300),
	location varchar(50) NOT NULL,
	objective varchar(500),
	hobbies varchar(500),
	certifications varchar(500),
	added_date DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Project(
	id int NOT NULL auto_increment,
    resume_id int NOT NULL,
    proj_name varchar(50) NOT NULL,
    proj_description varchar(100),
    github_link varchar(100),
    technologies_used varchar(150),
    PRIMARY KEY(id),
    FOREIGN KEY (resume_id) REFERENCES Resume (id) ON DELETE CASCADE
);

CREATE TABLE WorkDetails(
	id int NOT NULL auto_increment,
    resume_id int NOT NULL,
    company_name varchar(50) NOT NULL,
    duration varchar(50) NOT NULL,
    work_description varchar(50),
    PRIMARY KEY (id),
    FOREIGN KEY (resume_id) REFERENCES Resume (id) ON DELETE CASCADE
);

CREATE TABLE Technologies(
	tech_name varchar(50) NOT NULL,
    resume_id int NOT NULL,
    PRIMARY KEY (tech_name, resume_id),
    FOREIGN KEY (resume_id) REFERENCES Resume (id) ON DELETE CASCADE
);

INSERT INTO Resume VALUES (
	NULL,
	"Harshit",
    "harshit.chhabra09@gmail.com",
    "+918125799791",
    "Ashok Nagar, Hyderabad",
    "Hyderabad",
    "Dummy objective",
    "hobbie1,hobbie2,hobbie3",
    "cert1,cert2,cert3",
    "2020-01-15"
);

INSERT INTO Project VALUES (
	NULL,
	(SELECT AUTO_INCREMENT
		FROM information_schema.TABLES
		WHERE TABLE_SCHEMA = "auasstdb"
		AND TABLE_NAME = "resume"
	)-1,
	"proj1Name",
    "proj1Desc",
    "githubLinkDummy",
    "tech1,tech2,tech3"
);

INSERT INTO workdetails VALUES (
	NULL,
    (SELECT AUTO_INCREMENT
		FROM information_schema.TABLES
		WHERE TABLE_SCHEMA = "auasstdb"
		AND TABLE_NAME = "resume"
	)-1,
    "company1",
    "2 years 3 months",
    "dummy desc"
); 

INSERT INTO technologies VALUES 
("HTML",(SELECT AUTO_INCREMENT
		FROM information_schema.TABLES
		WHERE TABLE_SCHEMA = "auasstdb"
		AND TABLE_NAME = "resume"
	)-1),
    ("Javascript",(SELECT AUTO_INCREMENT
		FROM information_schema.TABLES
		WHERE TABLE_SCHEMA = "auasstdb"
		AND TABLE_NAME = "resume"
	)-1),
    ("Angular",(SELECT AUTO_INCREMENT
		FROM information_schema.TABLES
		WHERE TABLE_SCHEMA = "auasstdb"
		AND TABLE_NAME = "resume"
	)-1);
    
INSERT INTO Resume VALUES 
(NULL, "name2", "email2", "phone2", "address2", "loc2", "objective3", "hobbie1,hobbie2,hobbie3", "cert1,cert2,cert3", "2020-01-14"),
(NULL, "name3", "email3", "phone3", "address3", "loc3", "objective3", "hobbie1,hobbie2,hobbie3", "cert1,cert2,cert3", "2020-01-13"),
(NULL, "name4", "email4", "phone4", "address4", "loc4", "objective4", "hobbie1,hobbie2,hobbie3", "cert1,cert2,cert3", "2020-01-15"),
(NULL, "name5", "email5", "phone5", "address5", "loc5", "objective5", "hobbie1,hobbie2,hobbie3", "cert1,cert2,cert3", "2020-01-14"),
(NULL, "name6", "email6", "phone6", "address6", "loc6", "objective6", "hobbie1,hobbie2,hobbie3", "cert1,cert2,cert3", "2020-01-13"),
(NULL, "name7", "email7", "phone7", "address7", "loc7", "objective7", "hobbie1,hobbie2,hobbie3", "cert1,cert2,cert3", "2020-01-15") ;

INSERT INTO project VALUES
(NULL,2,"proj1","desc1","github1","tech1,tech2"),
(NULL,2,"proj2","desc2","github2","tech1,tech2"),
(NULL,3,"proj1","desc1","github1","tech1,tech2"),
(NULL,4,"proj1","desc1","github1","tech1,tech2"),
(NULL,5,"proj1","desc1","github1","tech1,tech2"),
(NULL,5,"proj2","desc2","github2","tech1,tech2"),
(NULL,6,"proj1","desc1","github1","tech1,tech2"),
(NULL,7,"proj1","desc1","github1","tech1,tech2");

INSERT INTO technologies VALUES
("HTML",2),
("Javascript",3),
("Angular",4),
("Node",5),
("SQL",6),
("HTML",7),
("Javascript",2),
("HTML",3),
("Bootstrap",4),
("React",5),
("HTML",6),
("Angular",3),
("Javascript",5);

INSERT INTO workdetails VALUES
(NULL,3,"compname","2 years","random desc"),
(NULL,5,"compname","3 years","awesome work"),
(NULL,7,"compname","4 years",NULL);

SELECT * FROM resume WHERE location = "Hyderabad"
 AND DATEDIFF(added_date,(SELECT CURDATE()))<=90;

SELECT COUNT(*) as Count,location as City from resume r WHERE
 EXISTS (SELECT * from technologies WHERE r.id = resume_id AND tech_name = "HTML")
 AND
 EXISTS (SELECT * from technologies WHERE r.id = resume_id AND tech_name = "Javascript") GROUP BY City;

SELECT person_name as CandidateName, (SELECT COUNT(*) FROM technologies WHERE r.id = resume_id) 
as Total_No_Of_Tech_Known FROM resume r ORDER BY Total_No_Of_Tech_Known desc;

CREATE VIEW AddedYesterday AS 
SELECT person_name, email, phone, added_date FROM resume WHERE added_date = (SELECT DATE_SUB((SELECT CURDATE()), INTERVAL 1 DAY));

SELECT * FROM AddedYesterday;



