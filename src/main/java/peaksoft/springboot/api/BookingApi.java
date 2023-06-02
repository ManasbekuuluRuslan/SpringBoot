//package peaksoft.springboot.api;

//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import peaksoft.springboot.service.BookingService;
//import peaksoft.springboot.service.HouseService;

//@Controller
//@RequestMapping("/booking")
//@RequiredArgsConstructor
//public class BookingApi {
//
//    private final BookingService bookingService;
//    private final HouseService houseService;
//    @GetMapping
//    public String bookingInfo(Model model){
//        model.addAttribute("booking",bookingService.getAllBooking());
//        return "BookingPage";
//    }
//    @GetMapping("/new")
//    public String newBooking(Model model) {
//        model.addAttribute("newBooking",houseService.getAllHouses());
//        return "newBooking";
//    }

