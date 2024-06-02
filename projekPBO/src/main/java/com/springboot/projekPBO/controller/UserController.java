package com.springboot.projekPBO.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.projekPBO.entity.Users;
import com.springboot.projekPBO.service.UserService;

@RestController // Menandai kelas ini sebagai pengendali RESTful yang akan menangani permintaan web
@RequestMapping("/api/users") // Menetapkan jalur dasar untuk semua permintaan yang ditangani oleh kelas ini
public class UserController {
	private final UserService userService; // Mendeklarasikan variabel final userService sebagai instance dari UserService

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) { // Mendefinisikan metode untuk membuat pengguna baru
        return userService.createUser(user); // Membuat pengguna baru menggunakan userService
        
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }
}
