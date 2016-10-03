package jp.aws.study.beanstalk.domain.model;

import lombok.Data;

/**
 * (必要な情報だけ抜粋しています。新しい情報が必要な場合は追加してください）
 * http://connpass.com/about/api/
 * Created by user on 2016/10/03.
 */
@Data
public class Event {
    private String event_url;
    private String started_at;
    private String title;
    private String address;
    private String place;
}
