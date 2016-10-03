package jp.aws.study.beanstalk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * WebSocketを使ったechoクラス
 * Created by maaya
 */
@Controller
public class WsMessageController {
    private static final Logger logger = LoggerFactory.getLogger(WsMessageController.class);

    /**
     * 開始ページの返却
     *
     * @return テンプレートのパス
     */
    @RequestMapping("/chat")
    public String showStartPage() {
        logger.debug("in chat");
        return "index";
    }

}
