CREATE DATABASE puskesmas;
USE puskesmas;

-- Tabel Pasien
CREATE TABLE Pasien (
  IDPasien INT PRIMARY KEY,
  namaPasien VARCHAR(255),
  umur INT,
  jenisKelamin ENUM('L','P')
);

-- Tabel Dokter
CREATE TABLE Dokter (
  IDDokter INT PRIMARY KEY,
  namaDokter VARCHAR(255),
  spesialisasi VARCHAR(255)
);

-- Tabel AntrianRuangUmum
CREATE TABLE AntrianRuangUmum (
  nomorAntrian INT PRIMARY KEY,
  IDPasien INT,
  IDDokter INT,
  FOREIGN KEY (IDpasien) REFERENCES Pasien(IDPasien),
  FOREIGN KEY (IDDokter) REFERENCES Dokter(IDDokter)
);

-- Tabel AntrianRuangKhusus
CREATE TABLE AntrianRuangKhusus (
  nomorAntrian INT PRIMARY KEY,
  IDPasien INT,
  IDDokter INT,
  FOREIGN KEY (IDpasien) REFERENCES Pasien(IDPasien),
  FOREIGN KEY (IDDokter) REFERENCES Dokter(IDDokter)
);

INSERT INTO Pasien (IDPasien, namaPasien, umur, jenisKelamin) VALUES
(1, 'John Doe', 25, 'L' ),
(2, 'Jane Smith', 23, 'P' ),
(3, 'Michael Johnson', 20, 'L' ),
(4, 'Emily Davis', 19, 'P' ),
(5, 'David Brown', 29, 'L' );

INSERT INTO Dokter (IDDokter, namaDokter, spesialisasi) VALUES
(1, 'Dr. Anderson', 'Umum'),
(2, 'Dr. Lee', 'Gigi'),
(3, 'Dr. Rodriguez', 'Mata'),
(4, 'Dr. Wilson', 'Jantung'),
(5, 'Dr. Taylor', 'Kulit');

INSERT INTO AntrianRuangUmum (nomorAntrian, IDpasien, IDDokter) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 1),
(4, 4, 3),
(5, 5, 2);

INSERT INTO AntrianRuangKhusus (nomorAntrian, IDpasien, IDDokter) VALUES
(1, 3, 4),
(2, 5, 3),
(3, 1, 5),
(4, 4, 2),
(5, 2, 1);
