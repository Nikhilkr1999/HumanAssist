ALTER TABLE Counselor ALTER COLUMN id TYPE varchar(60);

ALTER TABLE Counselor ADD CONSTRAINT counselor_pkey PRIMARY KEY (id);