package com.springboot.projekPBO.controller; // Mendeklarasikan bahwa file ini adalah bagian dari paket com.springboot.projekPBO.controller

import java.util.List; // Mengimpor kelas List dari java.util untuk digunakan dalam manajemen daftar
import java.util.Map; // Mengimpor kelas Map dari java.util untuk digunakan dalam manajemen pasangan kunci-nilai

import org.springframework.beans.factory.annotation.Autowired; // Mengimpor anotasi Autowired dari Spring untuk injeksi dependensi
import org.springframework.web.bind.annotation.CrossOrigin; // Mengimpor anotasi CrossOrigin dari Spring untuk mengizinkan permintaan lintas asal
import org.springframework.web.bind.annotation.PostMapping; // Mengimpor anotasi PostMapping dari Spring untuk menangani permintaan POST
import org.springframework.web.bind.annotation.RequestBody; // Mengimpor anotasi RequestBody dari Spring untuk mengikat parameter metode ke badan permintaan web
import org.springframework.web.bind.annotation.RestController; // Mengimpor anotasi RestController dari Spring untuk menandai kelas ini sebagai pengendali RESTful

import com.springboot.projekPBO.entity.Users; // Mengimpor kelas Users dari paket entity dalam proyek
import com.springboot.projekPBO.repository.UserRepository; // Mengimpor antarmuka UserRepository dari paket repository dalam proyek

@RestController // Menandai kelas ini sebagai pengendali RESTful yang akan menangani permintaan web
@CrossOrigin(origins = "http://localhost:5173") // Mengizinkan permintaan lintas asal dari URL yang ditentukan
public class LoginController { // Mendeklarasikan kelas publik LoginController

    @Autowired // Menginjeksi UserRepository secara otomatis oleh Spring
    private UserRepository userRepo; // Mendeklarasikan variabel userRepo sebagai instance dari UserRepository

    @PostMapping("/login") // Menentukan bahwa metode ini akan menangani permintaan POST ke /login
    public String Login(@RequestBody Map<String, String> credential ) { // Mendefinisikan metode Login yang menerima badan permintaan dalam bentuk Map
        System.out.println("In Login"); // Mencetak "In Login" ke konsol (untuk tujuan debugging)
        String username = credential.get("username"); // Mendapatkan nilai "username" dari badan permintaan
        String password = credential.get("password"); // Mendapatkan nilai "password" dari badan permintaan
        List<Users> userByUname = userRepo.findByUsername(username); // Mencari pengguna berdasarkan username menggunakan userRepo
        for(Users user: userByUname) { // Mengiterasi daftar pengguna yang ditemukan
            if(password.equals(user.getPassword())) { // Memeriksa apakah password yang diberikan cocok dengan password pengguna
                if(user.isAdmin() == true) { // Memeriksa apakah pengguna adalah admin
                    return "admin"; // Mengembalikan string "admin" jika pengguna adalah admin
                } else { // Jika pengguna bukan admin
                    return "user"; // Mengembalikan string "user" jika pengguna bukan admin
                }
            }
        }

        return "invalid"; // Mengembalikan string "invalid" jika username atau password tidak cocok
    }
}
