package jp.aws.study.beanstalk.domain.enums;

/**
 * Created by user on 2016/10/03.
 */
public enum AnswerMessageEnum {
    ASK("検索キーワードを入力してください。"),
    SEARCH_ANSWER("検索結果の上位を表示します。");

    private final String message;

    private AnswerMessageEnum(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
