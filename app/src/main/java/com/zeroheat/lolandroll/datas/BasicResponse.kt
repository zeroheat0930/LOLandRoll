package com.zeroheat.lolandroll.datas

import java.io.Serializable

// 서버가 주는 응답의 제일 기본형태인 code, message, data를 파싱해주는 클래스
// 레트로핏과 연계하면, 파싱이 자동 진행됨.

class BasicResponse(
    var data: DataResponse,
    var accountId: String,
    var profileIconId: Int,
    var revisionDate: Long,
    var name: String,
    var puuid: String,
    var id: String,
    var summonerLevel: Long
) :Serializable{
}