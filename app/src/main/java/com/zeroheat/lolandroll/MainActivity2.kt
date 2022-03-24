package com.zeroheat.lolandroll

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.zeroheat.lolandroll.adapters.chatRecyclerViewAdapter
import com.zeroheat.lolandroll.databinding.ActivityMain2Binding
import com.zeroheat.lolandroll.datas.chatdata
import java.text.SimpleDateFormat
import java.util.*

class MainActivity2 : BaseActivity() {

    lateinit var binding : ActivityMain2Binding
    var messageCount = 0L //DB에 저장된 채팅 갯수를 담을 변수. Long타입으로 저장.


    val mchatlist = ArrayList<chatdata>()
    lateinit var  mAdapter: chatRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

//        realtimeDb의 항목중, message 항목에 변화가 생길떄.
        realtimeDB.getReference("message").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

//                파이어 베이스의 DB내용 변경 = > 이 함수를 실행시켜줌.

//                snapshot변수 : 현재 변경된 상태 => 자녀가 몇개인지 추출.

                messageCount = snapshot.childrenCount


//                snapshot => 마지막 자녀(최신 메세지) 추출 => chatdata변환 + 목록에추가
                mchatlist.add(chatdata(
                    snapshot.children.last().child("content").value.toString(),
                    snapshot.children.last().child("createdAt").value.toString()
                ))

                mAdapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


    }

    override fun setValues() {

        mAdapter = chatRecyclerViewAdapter(mContext, mchatlist)
        binding.chattingRecyclerView.adapter = mAdapter
        binding.chattingRecyclerView.layoutManager = LinearLayoutManager(mContext)

////        DB연결 -> 값 기록 연습.
//
//        val db = FirebaseDatabase.getInstance("https://lolandroll-543b4-default-rtdb.firebaseio.com/")
//
////        DB의 하위 정보(Reference) 설정
//        val testRef = db.getReference("test")
//
////        test 항목에, "Hello World!!" 기록
//
//        testRef.setValue("Hello World")

    }
}