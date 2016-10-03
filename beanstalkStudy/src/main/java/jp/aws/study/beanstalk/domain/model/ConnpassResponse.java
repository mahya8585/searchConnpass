package jp.aws.study.beanstalk.domain.model;

import lombok.Data;

/**
 * (必要な情報だけ抜粋しています。新しい情報が必要な場合は追加してください）
 * http://connpass.com/about/api/
 * Created by user on 2016/10/03.
 */
@Data
public class ConnpassResponse {
    private Integer results_returned;
    private Event[] events;
    private Integer results_available;
}
