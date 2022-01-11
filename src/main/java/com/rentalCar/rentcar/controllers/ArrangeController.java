package com.rentalCar.rentcar.controllers;

import com.rentalCar.rentcar.dao.models.Arrange;
import com.rentalCar.rentcar.dao.models.Client;
import com.rentalCar.rentcar.dao.models.Post;
import com.rentalCar.rentcar.dao.models.Role;
import com.rentalCar.rentcar.dao.repository.ArrangeRepository;
import com.rentalCar.rentcar.dao.repository.ClientRepository;
import com.rentalCar.rentcar.dao.repository.PostRepository;
import com.rentalCar.rentcar.dao.service.ArrangeService;
import com.rentalCar.rentcar.dao.service.CarService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ArrangeController {
    private PostRepository postRepository;
    private CarService carService;
    private ArrangeService arrangeService;
    private ArrangeRepository arrangeRepository;
    private ClientRepository clientRepository;
    private Principal principal;
    private Integer carId;

    public ArrangeController(CarService carService, ArrangeRepository arrangeRepository, PostRepository postRepository, ArrangeService arrangeService, ClientRepository clientRepository) {
        this.carService = carService;
        this.arrangeRepository = arrangeRepository;
        this.postRepository = postRepository;
        this.arrangeService = arrangeService;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/blog/order/admin")
    public String blogOrderAdmin(Model model) {
        // все значения получен из опред таблицы в бд
        Iterable<Arrange> arrange = arrangeRepository.findAll();
        model.addAttribute("arrange", arrange);
        return "blog-order";
    }

    @GetMapping("/blog/order")
    public String blogOrder( Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String client = auth.getName();
        String clientId = clientRepository.findIdByName(client);
        Iterable<Arrange> arrange = arrangeRepository.findArrangesById(clientId);
        model.addAttribute("arrange", arrange);
        return "blog-order";
    }

    @RequestMapping(value = "/user)", method = RequestMethod.GET)
    @ResponseBody
    public String curretUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/blog/{id}/arrange")
    public String getArrange(@PathVariable(value = "id") int id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        carId = id;
        model.addAttribute("post", post);
        return "arrange";
    }

    @PostMapping("/blog/{id}/arrange")
    public String blogArrangeAdd(@RequestParam String lastname, @RequestParam String firstname, @RequestParam String patronymic,
                                 @RequestParam String email, @RequestParam String licnumb, @RequestParam String licissue,
                                 @RequestParam String passnumb, @RequestParam String passissue, @RequestParam String country,
                                 @RequestParam String region, @RequestParam String city, @RequestParam String address,
                                 @RequestParam String orderdate, @RequestParam String returndate,
                                 @RequestParam String comments, @RequestParam String consideration, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String client = auth.getName();
        String clientId = clientRepository.findIdByName(client);
        String car = carId.toString();
        Arrange arrange = new Arrange(car, clientId, lastname, firstname, patronymic, email, licnumb, licissue, passnumb, passissue, country, region, city, address, orderdate, returndate, comments, consideration);
        arrangeRepository.save(arrange);
//        переадресация
        return "redirect:/blog/order";
    }


    @GetMapping("/blog/{id}/order-details/{car}/car")
    public String blogOrderDetails(@PathVariable(value = "id") int id,@PathVariable(value = "car") int car, Model model) {
        if (!arrangeRepository.existsById(id)) {
            return "redirect:/blog/order";
        }
        Optional<Arrange> arrange = arrangeRepository.findById(id);
        Optional<Post> post = postRepository.findById(car);
        ArrayList<Arrange> res = new ArrayList<>();
        ArrayList<Post> res1 = new ArrayList<>();
        arrange.ifPresent(res::add);
        post.ifPresent(res1::add);
        model.addAttribute("arrange", res);
        model.addAttribute("post", res1);
        return "blog-order-details";
    }

    @GetMapping("/blog/{id}/order-edit")
    public String blogOrderEdit(@PathVariable(value = "id") int id, Model model) {
        if (!arrangeRepository.existsById(id)) {
            return "redirect:/blog/order";
        }
        Optional<Arrange> arrange = arrangeRepository.findById(id);
        ArrayList<Arrange> res = new ArrayList<>();
        arrange.ifPresent(res::add);
        model.addAttribute("arrange", res);
        return "blog-order-edit";
    }

    @PostMapping("/blog/{id}/order-edit")
    public String blogArrangeUpdate(@PathVariable(value = "id") int id, @RequestParam String lastname, @RequestParam String firstname, @RequestParam String patronymic,
                                    @RequestParam String email, @RequestParam String licnumb, @RequestParam String licissue,
                                    @RequestParam String passnumb, @RequestParam String passissue, @RequestParam String country,
                                    @RequestParam String region, @RequestParam String city, @RequestParam String address,
                                    @RequestParam String orderdate, @RequestParam String returndate,
                                    @RequestParam String comments, @RequestParam String consideration, Model model) {
        Arrange arrange = arrangeRepository.findById(id).orElseThrow();
        arrange.setLastname(lastname);
        arrange.setFirstname(firstname);
        arrange.setPatronymic(patronymic);
        arrange.setEmail(email);
        arrange.setPassnumb(passnumb);
        arrange.setPassissue(passissue);
        arrange.setLicnumb(licnumb);
        arrange.setLicissue(licissue);
        arrange.setCountry(country);
        arrange.setRegion(region);
        arrange.setCity(city);
        arrange.setAddress(address);
        arrange.setComments(comments);
        arrange.setOrderdate(orderdate);
        arrange.setReturndate(returndate);
        arrange.setConsideration(consideration);
        arrangeRepository.save(arrange);
        return "redirect:/blog/order";
    }

    @PostMapping("/blog/{id}/order-remove")
    public String blogArrangeDelete(@PathVariable(value = "id") int id, Model model) {
        Arrange arrange = arrangeRepository.findById(id).orElseThrow();
        arrangeRepository.delete(arrange);
        return "redirect:/blog/order";
    }

    @GetMapping("/blog/{id}/order-admin-edit")
    public String blogOrderAdminEdit(@PathVariable(value = "id") int id, Model model) {
        if (!arrangeRepository.existsById(id)) {
            return "redirect:/blog/order";
        }
        Optional<Arrange> arrange = arrangeRepository.findById(id);
        ArrayList<Arrange> res = new ArrayList<>();
        arrange.ifPresent(res::add);
        model.addAttribute("arrange", res);
        return "blog-order-admin-edit";
    }

    @PostMapping("/blog/{id}/order-admin-edit")
    public String blogArrangeAdminUpdate(@PathVariable(value = "id") int id, @RequestParam String lastname, @RequestParam String firstname, @RequestParam String patronymic,
                                         @RequestParam String email, @RequestParam String licnumb, @RequestParam String licissue,
                                         @RequestParam String passnumb, @RequestParam String passissue, @RequestParam String country,
                                         @RequestParam String region, @RequestParam String city, @RequestParam String address,
                                         @RequestParam String orderdate, @RequestParam String returndate,
                                         @RequestParam String comments, @RequestParam String consideration, Model model) {
        Arrange arrange = arrangeRepository.findById(id).orElseThrow();
        arrange.setLastname(lastname);
        arrange.setFirstname(firstname);
        arrange.setPatronymic(patronymic);
        arrange.setEmail(email);
        arrange.setPassnumb(passnumb);
        arrange.setPassissue(passissue);
        arrange.setLicnumb(licnumb);
        arrange.setLicissue(licissue);
        arrange.setCountry(country);
        arrange.setRegion(region);
        arrange.setCity(city);
        arrange.setAddress(address);
        arrange.setOrderdate(orderdate);
        arrange.setReturndate(returndate);
        arrange.setComments(comments);
        arrange.setConsideration(consideration);
        arrangeRepository.save(arrange);
        return "redirect:/blog/order";
    }


}
