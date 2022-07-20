package ru.job4j.cars.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@ThreadSafe
@Controller
public class AdController {

    private final AdService adService;
    private final CarService carService;
    private final MakeService makeService;
    private final ModelService modelService;
    private final EngineService engineService;
    private final BodyService bodyService;
    private final DriveService driveService;
    private final TransService transService;

    public AdController(AdService adService, CarService carService, MakeService makeService,
                        ModelService modelService, EngineService engineService, BodyService
                                bodyService, DriveService driveService, TransService transService) {
        this.adService = adService;
        this.carService = carService;
        this.makeService = makeService;
        this.modelService = modelService;
        this.engineService = engineService;
        this.bodyService = bodyService;
        this.driveService = driveService;
        this.transService = transService;
    }

    @GetMapping("/all")
    public String all(Model model, HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("ads", adService.findAll());
        return "all";
    }

    @GetMapping("filterPhoto")
    public String filterPhoto(Model model, HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("ads", adService.findByPhoto());
        return "filterPhoto";
    }

    @GetMapping("filterToday")
    public String filterToday(Model model, HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("ads", adService.findToday());
        return "filterToday";
    }

    @GetMapping("filterMakeForm")
    public String filterMakeForm(Model model, HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("makes", makeService.findAll());
        return "filterMakeForm";
    }

    @PostMapping("/filterMakeForm")
    public String filterMake(@ModelAttribute Car car) {
        String name = makeService.findById(car.getMake().getId()).getName();
        return String.format("redirect:/filterMake/%s", name);
    }

    @GetMapping("filterMake/{makeName}")
    public String filterMake(Model model,
                             @PathVariable("makeName") String name,
                             HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("ads", adService.findByMakeName(name));
        return "filterMake";
    }

    @GetMapping("/selectMake")
    public String selectMakeForm(Model model, HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("makes", makeService.findAll());
        return "selectMake";
    }

    @PostMapping("/selectMake")
    public String selectMake(@ModelAttribute Car car) {
        int id = car.getMake().getId();
        return String.format("redirect:/selectModel/%d", id);
    }

    @GetMapping("/selectModel/{makeId}")
    public String selectModelForm(Model model,
                                  @PathVariable("makeId") int id,
                                  HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("make", makeService.findById(id));
        model.addAttribute("models", modelService.findByMakeName(makeService.findById(id).getName()));
        model.addAttribute("engines", engineService.findAll());
        model.addAttribute("bodies", bodyService.findAll());
        model.addAttribute("drives", driveService.findAll());
        model.addAttribute("transmissions", transService.findAll());
        return "selectModel";
    }

    @PostMapping("/selectModel")
    public String selectModel(@ModelAttribute Car car) {
        carService.add(car);
        return String.format("redirect:/addAdv/%d", car.getId());
    }

    @GetMapping("/addAdv/{carId}")
    public String addAdvFrom(Model model,
                             @PathVariable("carId") int id,
                             HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("car", carService.findById(id));
        return "addAdv";
    }

    @PostMapping("/addAdv")
    public String addAdv(@ModelAttribute Advertisement ad,
                         @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            ad.setPhoto(file.getBytes());
        }
        adService.add(ad);
        return "redirect:/all";
    }

    @GetMapping("/description/{adId}")
    public String description(Model model,
                              @PathVariable("adId") int id,
                              HttpSession session) {
        model.addAttribute("user", currentUser(session));
        Advertisement ad = adService.findById(id);
        model.addAttribute("ad", ad);
        return "description";
    }

    @GetMapping("/photoAd/{adId}")
    public ResponseEntity<Resource> download(@PathVariable("adId") Integer adId) {
        Advertisement ad = adService.findById(adId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(ad.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(ad.getPhoto()));
    }

    @PostMapping("/makeSold/{adId}")
    public String makeSold(Model model, @PathVariable("adId") int id) {
        model.addAttribute("ad", adService.findById(id));
        adService.makeSold(id);
        return String.format("redirect:/description/%d", id);
    }

    @GetMapping("/edit/{adId}")
    public String editForm(Model model, @PathVariable("adId") int id, HttpSession session) {
        model.addAttribute("user", currentUser(session));
        model.addAttribute("ad", adService.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Advertisement ad) {
        adService.update(ad.getId(), ad);
        return String.format("redirect:/description/%d", ad.getId());
    }

    @PostMapping("/delete/{adId}")
    public String delete(@PathVariable("adId") int id) {
        adService.delete(id);
        return "redirect:/all";
    }

    private User currentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }
}
