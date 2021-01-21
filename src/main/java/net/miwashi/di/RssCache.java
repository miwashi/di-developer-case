package net.miwashi.di;

import com.apptastic.rssreader.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
@Scope("singleton")
public class RssCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(RssCache.class);
    private static ConcurrentHashMap<String, Item> cache = new ConcurrentHashMap<String,Item>();
    private RssCache(){};

    public static final RssCache instance = new RssCache();

    public static RssCache cache(){
        return instance;
    }


    public static void update(List<Item> articles){
        LOGGER.trace("->update");
        //Can be improved by overriding equals method!
        articles.remove(cache.values().remove(articles));
        for(Item item : articles){
            cache.put(item.getGuid().toString(), item);
        }
        LOGGER.trace("<-update");
    }

    public static Stream<Item> stream(){
        LOGGER.trace("->stream");
        LOGGER.trace("<-stream");
        return cache.values().stream();
    }
}
