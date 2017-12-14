package com.zj.controller;

import com.zj.model.AmazonProperties;
import com.zj.model.Book;
import com.zj.services.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/11
 * Time: 20:55
 * CopyRight: Zhouji
 */
@Controller
@RequestMapping("/reading")
public class ReadingListController {

    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private AmazonProperties amazonProperties;
    @Autowired
    private CounterService counterService;
    @Autowired
    private GaugeService gaugeService;

//    @Autowired
//    public ReadingListController(ReadingListRepository readingListRepository,
//                                 AmazonProperties amazonProperties,
//                                 CounterService counterService,
//                                 GaugeService gaugeService) {
//        this.readingListRepository = readingListRepository;
//        this.amazonProperties = amazonProperties;
//        this.counterService = counterService;
//        this.gaugeService = gaugeService;
//    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readerBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (!CollectionUtils.isEmpty(readingList)) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }
        return "readingList";
    }


    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        counterService.increment("books.saved");
        gaugeService.submit("books.last.saved", System.currentTimeMillis());
        return "redirect:/reading/{reader}";
    }


}
