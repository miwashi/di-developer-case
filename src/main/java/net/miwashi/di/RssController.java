package net.miwashi.di;

import com.apptastic.rssreader.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("/rss")
public class RssController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RssController.class);

    @Autowired
    private RssCache cache;

    @GetMapping("/rss")
    public List<Item> read() {
        LOGGER.trace("->read");
        LOGGER.trace("<-read");
        return cache.stream()
                .sorted((Item item1, Item item2) ->  item1.getPubDate().compareTo(item2.getPubDate()))
                .limit(10)
                .collect(Collectors.toList())
                ;
    }
}
