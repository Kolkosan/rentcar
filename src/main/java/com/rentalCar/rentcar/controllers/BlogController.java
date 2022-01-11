package com.rentalCar.rentcar.controllers;

import com.rentalCar.rentcar.dao.models.Post;
import com.rentalCar.rentcar.dao.repository.ArrangeRepository;
import com.rentalCar.rentcar.dao.repository.ClientRepository;
import com.rentalCar.rentcar.dao.repository.PostRepository;
import com.rentalCar.rentcar.dao.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Controller
public class BlogController {

    private PostRepository postRepository;
    private CarService carService;
    private ArrangeRepository arrangeRepository;
    private ClientRepository clientRepository;


    public BlogController(PostRepository postRepository, CarService carService,ArrangeRepository arrangeRepository,ClientRepository clientRepository) {
        this.postRepository = postRepository;
        this.carService = carService;
        this.arrangeRepository = arrangeRepository;
        this.clientRepository = clientRepository;
    }

    //    все записи в шаблон


    @GetMapping("/blog")
    public String blogMain(Model model) {
        // все значения получен из опред таблицы в бд
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-mail";
    }
    @GetMapping("/home")
    public String blogHome(Model model) {
        // все значения получен из опред таблицы в бд
        Iterable<Post> posts1 = postRepository.findTop4All();
        model.addAttribute("posts1", posts1);
        Iterable<Post> posts2 = postRepository.findTop8All();
        model.addAttribute("posts2", posts2);
        Iterable<Post> posts3 = postRepository.findTop12All();
        model.addAttribute("posts3", posts3);
        return "home";
    }

    //    просто перехот по урл адрес getmapping
    @GetMapping("/blog/add")
    public String blogadd(Model model) {
        return "blog-add";
    }

    //    postmapping для обработки и вноса
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String brand,@RequestParam String carcl,@RequestParam String body,
                               @RequestParam String transmission,@RequestParam String color,@RequestParam String photo,
                               @RequestParam String seats, @RequestParam String prod_date,@RequestParam String cost_day,
                               @RequestParam String deposit, @RequestParam String comments,Model model) {
        Post post = new Post(brand, carcl,body,transmission, color,photo,seats,prod_date,cost_day,deposit,comments);
        postRepository.save(post);
//        переадресация
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}/details")
    public String blogDetails(@PathVariable(value = "id") int id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") int id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") int id,@RequestParam String brand,@RequestParam String carcl,@RequestParam String body,
                                 @RequestParam String transmission,@RequestParam String color,@RequestParam String photo,
                                 @RequestParam String seats, @RequestParam String prod_date,@RequestParam String cost_day,
                                 @RequestParam String deposit, @RequestParam String comments,Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setBrand(brand);
        post.setCarcl(carcl);
        post.setBody(body);
        post.setTransmission(transmission);
        post.setColor(color);
        post.setPhoto(photo);
        post.setSeats(seats);
        post.setProd_date(prod_date);
        post.setCost_day(cost_day);
        post.setDeposit(deposit);
        post.setComments(comments);
        postRepository.save(post);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") int id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{brand}/brand")
    public String getPostBrand(@PathVariable(value = "brand") String brand,Model model) {
        Iterable<Post> post = carService.findAllBrand(brand);
        model.addAttribute("posts", post);
        return "blog-carBrand";
    }
    @GetMapping("/blog/{body}/body")
    public String getPostByBody(@PathVariable(value = "body") String body, Model model) {
        List<Post> carByBody = carService.findAllBody(body);
        model.addAttribute("posts", carByBody);
        return "blog-carBody";
    }
    @GetMapping("/blog/{transmission}/transmission")
    public String getPostByTransmission(@PathVariable(value = "transmission") String transmission, Model model) {
        List<Post> carByTransmission = carService.findAllTransmission(transmission);
        model.addAttribute("posts", carByTransmission);
        return "blog-carTransmission";
    }
    @GetMapping("/blog/{carcl}/car_class")
    public String getPostByCarClass(@PathVariable(value = "carcl") String carcl, Model model) {
        List<Post> carByCarcl = carService.findAllCarcl(carcl);
        model.addAttribute("posts", carByCarcl);
        return "blog-Car_class";
    }
}
