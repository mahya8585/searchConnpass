package jp.aws.study.beanstalk.domain.service;

import jp.aws.study.beanstalk.domain.enums.AnswerMessageEnum;
import jp.aws.study.beanstalk.domain.model.ConnpassResponse;
import jp.aws.study.beanstalk.domain.model.Event;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * connpass API とのやりとり
 * http://connpass.com/about/api/
 * Created by user on 2016/10/03.
 */
public class ConnpassService {
    private static final Logger logger = LoggerFactory.getLogger(ConnpassService.class);

    private static final String URL = "http://connpass.com/api/v1/event/?keyword_or=";

    private static final String MESSAGE_PREFIX = "<div class='message_box'><p class='name_cs'>コンシェルジュ</p><div class='message_cs'>";
    private static final String MESSAGE_SUFFIX = "</div>";
    private static final String NEW_LINE = "<br/>";

    public String createAnswerMessage(String messageHtml) {
        logger.debug("message: " + messageHtml);
        Document messageDocument = Jsoup.parse(messageHtml);

        //自分の発言の場合
        if (messageDocument.getElementById("user_name").text().equals("コンシェルジュ")) {
            return MESSAGE_PREFIX + AnswerMessageEnum.ASK.getMessage() + MESSAGE_SUFFIX + NEW_LINE;
        }

        //相手が入力した言葉をキーワードにconnpass検索を行う
        ConnpassResponse result = createConnpassData(messageDocument.getElementById("user_message").text());

        //表示テキストの作成
        return createReturnMessage(result);
    }

    /**
     * connpass API 実行
     *
     * @param keyword 検索条件
     * @return
     */
    public ConnpassResponse createConnpassData(String keyword) {
        return new RestTemplate().getForObject(URL + keyword, ConnpassResponse.class);
    }

    /**
     * メッセージの構築
     *
     * @param connpassData コンパス取得データ
     * @return
     */
    public String createReturnMessage(ConnpassResponse connpassData) {
        StringBuilder answerMessage = new StringBuilder();
        //レコード開始
        answerMessage.append(MESSAGE_PREFIX);

        //検索結果文
        answerMessage.append(AnswerMessageEnum.SEARCH_ANSWER.getMessage());

        for (Event event : connpassData.getEvents()) {
            answerMessage.append(NEW_LINE);

            answerMessage.append("<a href='")
                    .append(event.getEvent_url())
                    .append("' target='_blank'>")
                    .append(event.getTitle())
                    .append("[")
                    .append(event.getStarted_at().substring(0, 10))
                    .append("]</a>");
        }

        //レコードの終了
        answerMessage.append(MESSAGE_SUFFIX);
        return answerMessage.toString();
    }
}
