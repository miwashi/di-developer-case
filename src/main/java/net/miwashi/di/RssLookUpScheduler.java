package net.miwashi.di;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.netty.handler.codec.rtsp.RtspHeaders.Values.URL;

@Component
public class RssLookUpScheduler {
    private static final int SCHEUDED_RATE = 5000;
    private static final Logger LOGGER = LoggerFactory.getLogger(RssLookUpScheduler.class);

    public static final String URL = "https://www.di.se/rss";

    @Autowired
    RssCache cache;

    @Scheduled(fixedRate = SCHEUDED_RATE)
    public void lookUpDiRss(){
        LOGGER.trace("->lookUpDiRss");
        LOGGER.debug("<-Checking RSS");
        try {
            cache.update((new RssReader()).read(URL)
                    .collect(Collectors.toList())
            );
        } catch (IOException e) {
            LOGGER.warn("Failed to read RSS!", e);
        }
        LOGGER.trace("<-lookUpDiRss");
    }
}
