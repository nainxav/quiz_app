package com.springboot.projekPBO; // Mendeklarasikan bahwa file ini adalah bagian dari paket com.springboot.projekPBO

import org.springframework.boot.SpringApplication; // Mengimpor kelas SpringApplication dari Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Mengimpor anotasi SpringBootApplication dari Spring Boot

@SpringBootApplication // Menandakan bahwa ini adalah aplikasi Spring Boot, mengaktifkan konfigurasi otomatis, pemindaian komponen, dan konfigurasi berbasis Spring
public class ProjekPboApplication { // Mendeklarasikan kelas publik ProjekPboApplication

    public static void main(String[] args) { // Mendefinisikan metode utama yang akan dijalankan saat aplikasi dimulai
        SpringApplication.run(ProjekPboApplication.class, args); // Menjalankan aplikasi Spring Boot, mengambil kelas ProjekPboApplication sebagai argumen dan meneruskan argumen baris perintah
    }

}
