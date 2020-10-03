package com.example.recycler_view_test_1004;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//Tools - FireBase - RealTimeDB연결 - 1단계 2단계 완료
//파이어베이스 사용 이유 _서버 구현 복잡하여 클라우드 서버를 빌림
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;

    //FireBase구문
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //recyclerView 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //User 객체를 담을 ArrayList(어댑터 쪽으로 날릴)


        database = FirebaseDatabase.getInstance();                   //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("User");     //실제 database의 이름을 의미 ["User"] (테이블과 비슷한듯)
                                                                    //DB 테이블 연결 -> firebase console 킴 -> 프로젝트 - realtime DB 만들기 TestMode
                                                                    //맨 처음에 [프로젝트이름] 옆 메모하지말고 + 버튼 -> [테이블명(ex_[User])] -> [+]버튼 -> [분류(ex_[User_01])]
                                                                    // -> [+]버튼[여러번하면 여러개의 속성에 값 입력] -> [세부Data ("속성 : 값"(ex_[id : wootang)] -> 추가
                                                                    // 이미지 : storage -> 만들기 -> 완료 -> 규칙[Rules] 탭으로 들어감  allow read, write: if true; 로 바꿔줌
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //FireBase의 Database 내 Data를 받아오는 곳
                arrayList.clear(); //기존 ArrayList가 존재하지 않게 초기화 _ 방지차원
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ //반복문으로 DataList 추출
                    User user = snapshot.getValue(User.class);                   //UserClass 내에 FireBase에서 가져온 것을 담는다. (만들어뒀던 User 객체에 데이터를 담는다.)
                    arrayList.add(user);                                        //담은 Data들을 배열 리스트에 넣고 recyclerVIew로 보낸다.
                }
                adapter.notifyDataSetChanged();                                 //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //DB 가져오던 중 에러 발생 시 띄우는 내용 [필수 입력사항 아님]
                Log.e("MainActivity",String.valueOf(databaseError.toException()));
            }
        });

        adapter = new CustomAdapter(arrayList,this);    //this자체가 ActivityOnCreate에서는 Context에 해당됨.
        recyclerView.setAdapter(adapter);                      //recyclerView에 어댑터 연결

    }
}
