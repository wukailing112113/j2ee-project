package com.soecode.lyf.web;

import com.soecode.lyf.kafka.IKafkaConsumer;
import com.soecode.lyf.kafka.IKafkaProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sawyer on 2018/2/10.
 */
@Controller
@RequestMapping("/kafka")
public class KafkaController {


    @Resource(name = "kafkaProducer")
    IKafkaProducer kafkaProducer;

    @Resource(name = "kafkaConsumer")
    IKafkaConsumer kafkaConsumer;

    @RequestMapping(value = "/welcome")
    public ModelAndView welcome() {
        System.out.println("--------welcome--------");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        return mv;
    }


    @RequestMapping(value = "/sendmessage", method = RequestMethod.GET)
    public ModelAndView sendMessage() {
        System.out.println("--------sendmessage--------");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(date);

        ModelAndView mv = new ModelAndView();
        mv.addObject("time", now);
        mv.setViewName("kafka_send");
        return mv;
    }
    @RequestMapping(value = "/onsend", method = RequestMethod.POST)
    public ModelAndView onsend(@RequestParam("message") String msg) throws Exception {
        System.out.println("--------卡夫卡开始发送消息--------");
        kafkaProducer.sendMessage(msg);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        return mv;
    }


    @RequestMapping(value = "/receive")
    public ModelAndView receive() {
        System.out.println("--------receive--------");

        String msg = kafkaConsumer.receive();

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", msg);
        mv.setViewName("kafka_receive");
        return mv;
    }

}
